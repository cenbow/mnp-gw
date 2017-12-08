/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.ws;

import cat.mnp.clh.util.NpcMessageUtils;
import cat.mnp.mq.core.MsgHandlerBase;
import cat.mnp.ws.dao.NpcBatchIdGeneratorDao;

import com.netnumber.titan.view.soap.api.ViewSOAPProvisioning;
import com.netnumber.titan.view.soap.types.ChangeRequest;
import com.netnumber.titan.view.soap.types.ChangeResponse;
import com.telcordia.inpac.ws.NPCWebService;
import com.telcordia.inpac.ws.jaxb.MessageHeaderType;
import com.telcordia.inpac.ws.jaxb.NPCDataType;
import com.telcordia.inpac.ws.jaxb.NPCMessageData;
import com.telcordia.inpac.ws.jaxb.NPCMessageType;
import com.telcordia.inpac.ws.jaxb.PortBroadcastMsgType;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;


public class ViewSOAPProvisioningWsClient extends MsgHandlerBase {

	private static final Logger logger = LoggerFactory.getLogger(ViewSOAPProvisioningWsClient.class);
	private ViewSOAPProvisioning viewSOAPProvisioningWebService;

	public void setViewSOAPProvisioningWebService(ViewSOAPProvisioning viewSOAPProvisioningWebService) {
		this.viewSOAPProvisioningWebService = viewSOAPProvisioningWebService;
	}

	private File getFile() {
		String dateString = DateFormatUtils.format(new Date(), getFileDateFormat());
		String filename = String.format(getFilenameFormat(), dateString, "Error", "");
		return new File(FilenameUtils.concat(getFilePath(), filename));
	}

	private File getFile(MessageHeaderType messageHeader) {
		String dateString = DateFormatUtils.format(new Date(), getFileDateFormat());
		String filename = String.format(getFilenameFormat(), dateString, messageHeader.getMessageID(), messageHeader.getSoapRequestId());
		return new File(FilenameUtils.concat(getFilePath(), filename));
	}

	private File getFile(MessageHeaderType messageHeader, String result) {
		String dateString = DateFormatUtils.format(new Date(), getFileDateFormat());
		String filename = String.format(getFilenameFormat(), dateString, messageHeader.getMessageID(), messageHeader.getSoapRequestId() + "_" + result);
		return new File(FilenameUtils.concat(getFilePath(), filename));
	}

	// return Arrays.asList(new String[]{"msisdn='0813520685' mnp {routing_number='0688910813520685' donor=\"\" recipient=\"\"}"});
	private List<String> generateEntry(MessageHeaderType messageHeader, ArrayList<PortBroadcastMsgType> msgBroadcastMsgList) {
		List r = new ArrayList<>();
		for (PortBroadcastMsgType portBroadcastMsgType : msgBroadcastMsgList) {
			String enrty = "msisdn='" + portBroadcastMsgType.getMSISDN() + "' mnp{routing_number='" + portBroadcastMsgType.getRoute() + "' donor='" + portBroadcastMsgType.getDonor() + "' recipient='"
					+ portBroadcastMsgType.getRecipient() + "'}";
			r.add(enrty);
		}
		return r;
	}

	// <typ:ChangeRequest view="NP.MNP">
	// <typ:entry>msisdn='0813520685' mnp {routing_number='0688910813520685' donor="" recipient=""}</typ:entry>
	// </typ:ChangeRequest>
	private void doProcessMsg(List<String> msgStringList) throws Exception {
		ArrayList<PortBroadcastMsgType> broadcastMsgList = new ArrayList<>();
		ArrayList<String> entryList = new ArrayList<>();

		long startTime = System.currentTimeMillis();
		logger.info("Generating Entry from {} soaps", msgStringList.size());
		for (String msgString : msgStringList) {
			try {
				NPCMessageData npcMessageData = NpcMessageUtils.unMarshal(getJaxbUnMarshaller(), msgString);
				NPCDataType npcDataType = npcMessageData.getNPCData();

				MessageHeaderType messageHeader = npcDataType.getMessageHeader();
				NPCMessageType npcMessages = npcDataType.getNPCMessages();

				ArrayList<PortBroadcastMsgType> msgBroadcastMsgList = new ArrayList<>();
				msgBroadcastMsgList.addAll(NpcMessageUtils.listBroadcastMsg(npcMessages));
				broadcastMsgList.addAll(msgBroadcastMsgList);

				entryList.addAll(generateEntry(messageHeader, msgBroadcastMsgList));
			} catch (Exception ex) {
				File file = getFile();
				FileUtils.writeStringToFile(file, msgString, getFileEncoding());
				moveFileToDirectory(file, getErrorPath());
				throw new AmqpRejectAndDontRequeueException("Error while unmarshaling msg", ex);
			}
		}
		logger.info("Generated Entry from Broadcast size: {} to Entry size: {} in {} ms", broadcastMsgList.size(), entryList.size(), (System.currentTimeMillis() - startTime));

		// contruct msg from msg List
		ChangeRequest changeRequest = new ChangeRequest();
		changeRequest.setView("NP.MNP");
		changeRequest.getEntry().addAll(entryList);

		logger.debug("Calling ws, {}", changeRequest);
		ChangeResponse response = viewSOAPProvisioningWebService.change(changeRequest);

		String result = String.format("Ws Response: %s: %s ", response.getCode(), response.getMessage());
		logger.info(result);

		MessageHeaderType messageHeader = new MessageHeaderType();
		messageHeader.setMessageID(new BigInteger("0"));
		if (response.getMessage().equalsIgnoreCase("Success")) {
			if (getBackupPath() != null) {
				logger.debug("Writing xml log for {}", messageHeader);
				File file = getFile(messageHeader);
				FileUtils.writeStringToFile(file, entryList.toString(), getFileEncoding());
				moveFileToDirectory(file, getBackupPath());
			}
		} else {
			File file = getFile(messageHeader, result);
			FileUtils.writeStringToFile(file, entryList.toString(), getFileEncoding());
			moveFileToDirectory(file, getErrorPath());
		}
	}
	@Override
	public void processMsg(List<Message> msgList) throws Exception {
		ArrayList<String> msgStringList = new ArrayList<>();
		for (Message msg : msgList) {
			msgStringList.add(new String(msg.getBody()));
		}

		doProcessMsg(msgStringList);
	}
	public void precessMsg(String msgString) throws Exception {
		ArrayList<String> msgStringList = new ArrayList<>();
		msgStringList.add(msgString);

		doProcessMsg(msgStringList);
	}
}
