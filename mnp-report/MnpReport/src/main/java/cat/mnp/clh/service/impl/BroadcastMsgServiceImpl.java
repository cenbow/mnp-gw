/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.clh.service.impl;

import com.telcordia.inpac.ws.jaxb.MessageFooterType;
import com.telcordia.inpac.ws.jaxb.ClhGenericBroadcastMsgType;
import com.telcordia.inpac.ws.jaxb.NPCMessageData;
import com.telcordia.inpac.ws.jaxb.NPCDataType;
import com.telcordia.inpac.ws.jaxb.MessageHeaderType;
import com.telcordia.inpac.ws.jaxb.NPCMessageType;
import cat.mnp.clh.dao.BroadcastMsgDao;
import cat.mnp.clh.hibernate.NpChInMsgTrace;
import cat.mnp.om.hibernate.MnpPortComplete;
import cat.mnp.service.MnpMsgService;
import cat.mnp.service.impl.MnpMsgServiceImpl;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.xml.transform.stream.StreamSource;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;

/**
 *
 * @author HP-CAT
 */
public class BroadcastMsgServiceImpl extends MnpMsgServiceImpl implements MnpMsgService {

    private static Logger logger = LoggerFactory.getLogger(BroadcastMsgServiceImpl.class);
    private BroadcastMsgDao broadcastMsgDao;
    private Marshaller broadcastMsgMarshaller;
    private Unmarshaller broadcastMsgUnmarshaller;
    private String[] broadcastMsgDateFormat;
    private String baseOperator;
    private int orderIdToMvnoNameDigit;
    private Map<String, String> orderIdToMvnoNameMapper;

    public String getBaseOperator() {
        return baseOperator;
    }

    public void setBaseOperator(String baseOperator) {
        this.baseOperator = baseOperator;
    }

    public BroadcastMsgDao getBroadcastMsgDao() {
        return broadcastMsgDao;
    }

    public void setBroadcastMsgDao(BroadcastMsgDao broadcastMsgDao) {
        this.broadcastMsgDao = broadcastMsgDao;
    }

    public String[] getBroadcastMsgDateFormat() {
        return broadcastMsgDateFormat;
    }

    public void setBroadcastMsgDateFormat(String[] broadcastMsgDateFormat) {
        this.broadcastMsgDateFormat = broadcastMsgDateFormat;
    }

    public Marshaller getBroadcastMsgMarshaller() {
        return broadcastMsgMarshaller;
    }

    public void setBroadcastMsgMarshaller(Marshaller broadcastMsgMarshaller) {
        this.broadcastMsgMarshaller = broadcastMsgMarshaller;
    }

    public Unmarshaller getBroadcastMsgUnmarshaller() {
        return broadcastMsgUnmarshaller;
    }

    public void setBroadcastMsgUnmarshaller(Unmarshaller broadcastMsgUnmarshaller) {
        this.broadcastMsgUnmarshaller = broadcastMsgUnmarshaller;
    }

    public int getOrderIdToMvnoNameDigit() {
        return orderIdToMvnoNameDigit;
    }

    public void setOrderIdToMvnoNameDigit(int orderIdToMvnoNameDigit) {
        this.orderIdToMvnoNameDigit = orderIdToMvnoNameDigit;
    }

    public Map<String, String> getOrderIdToMvnoNameMapper() {
        return orderIdToMvnoNameMapper;
    }

    public void setOrderIdToMvnoNameMapper(Map<String, String> orderIdToMvnoNameMapper) {
        this.orderIdToMvnoNameMapper = orderIdToMvnoNameMapper;
    }

    public boolean filterOperator(String operator) {
        return operator.indexOf(baseOperator) > -1;
    }

    private MnpPortComplete buildExternalPort(String portId, String msisdn, String recipient, String donor, int msgId, Date completionTime) throws Exception {
        if (baseOperator != null) {
            boolean doQuery = filterOperator(recipient) || filterOperator(donor);
            if (doQuery == false) {
                return portCompleteDao.create(portId, msisdn);
            }
        }

        MnpPortComplete mnpPortComplete;

        if (msgId == 3010) {
            mnpPortComplete = portCompleteDao.create(portId, msisdn);
        } else {
            logger.trace("selecting ({}, {}) from HPNP", portId, msisdn);
            mnpPortComplete = broadcastMsgDao.get(portId, msisdn);
            if (mnpPortComplete == null) {//not CATCDMA
                logger.error("cannot get orderId from ({}, {})", portId, msisdn);
                return portCompleteDao.create(portId, msisdn);
            }
        }

        mnpPortComplete.setCompletionTime(completionTime);
        //update mvnoName
        if (donor.equalsIgnoreCase(baseOperator) || msgId == 3010) {
            portCompleteDao.updateMvnoName(mnpPortComplete);
            logger.trace("selected[donor] portId: {}, mvnoName: {}", portId, mnpPortComplete.getMvnoName());
        } else if (recipient.equalsIgnoreCase(baseOperator)) {
            if (mnpPortComplete.getOrderId() == null) {
                logger.error("error[recipient] orderId is null, portId: {}", portId);
            } else {
                String digit = Character.toString(mnpPortComplete.getOrderId().charAt(orderIdToMvnoNameDigit));
                String mvnoName = orderIdToMvnoNameMapper.get(digit);

                mnpPortComplete.setMvnoName(mvnoName);
                logger.trace("selected[recipient] portId: {}, mvnoName: {}", portId, mvnoName);
            }
        }

        return mnpPortComplete;
    }

    private MnpPortComplete getExternalPort(MessageHeaderType messageHeaderType, ClhGenericBroadcastMsgType clhGenericBroadcastMsgType) throws Exception {
        Date completionTime = DateUtils.parseDateStrictly(messageHeaderType.getMsgCreateTimeStamp(), broadcastMsgDateFormat);
        int msgId = messageHeaderType.getMessageID().intValue();

        MnpPortComplete mnpPortComplete = buildExternalPort(clhGenericBroadcastMsgType.getPortId(), clhGenericBroadcastMsgType.getMSISDN(), clhGenericBroadcastMsgType.getRecipient(), clhGenericBroadcastMsgType.getDonor(), msgId, completionTime);
        mnpPortComplete.setDonor(clhGenericBroadcastMsgType.getDonor());
        mnpPortComplete.setRecipient(clhGenericBroadcastMsgType.getRecipient());
        mnpPortComplete.setCompletionTime(completionTime);
        mnpPortComplete.setMsgId(msgId);
        mnpPortComplete.setRoute(clhGenericBroadcastMsgType.getRoute());

        logger.trace("{}", mnpPortComplete);

        return mnpPortComplete;
    }

    private NPCMessageData getNPCMessageData(final NpChInMsgTrace npChInMsgTrace) throws Exception {
        if (npChInMsgTrace == null) {
            throw new NullPointerException("NpChInMsgTrace is null");
        }

        Clob messageData = npChInMsgTrace.getMessageData();
        if (messageData == null) {
            throw new NullPointerException(npChInMsgTrace + ".getMessageData() is null");
        }

        StreamSource source = new StreamSource(messageData.getCharacterStream());
        NPCMessageData npcMessageData = (NPCMessageData) broadcastMsgUnmarshaller.unmarshal(source);

        return npcMessageData;
    }

    private List<MnpPortComplete> listExternalPort(List<NpChInMsgTrace> listMsg) throws Exception {
        List<MnpPortComplete> mnpPortCompleteList = new ArrayList<MnpPortComplete>();
        ArrayList<NPCDataType> npcDataTypeList = new ArrayList<NPCDataType>();
        int totalMsisdn = 0;
        for (NpChInMsgTrace npChInMsgTrace : listMsg) {
            NPCDataType npcDataType = getNPCMessageData(npChInMsgTrace).getNPCData();
            npcDataTypeList.add(npcDataType);
            logger.debug("{}", npChInMsgTrace);

            MessageFooterType messageFooterType = npcDataType.getMessageFooter();
            totalMsisdn += messageFooterType.getChecksum().intValue();

            String reportStatusDateKey = DateFormatUtils.format(npChInMsgTrace.getMessageTimestamp(), reportStatusService.getKeyDateFormat());
            reportStatusService.setHashValue(reportStatusDateKey, MnpMsgService.TOTAL_MSG_KEY, totalMsisdn);
        }

        for (NPCDataType npcDataType : npcDataTypeList) {
            MessageHeaderType messageHeaderType = npcDataType.getMessageHeader();

            Date msgCreateTimeStamp = DateUtils.parseDateStrictly(messageHeaderType.getMsgCreateTimeStamp(), broadcastMsgDateFormat);
            String reportStatusDateKey = DateFormatUtils.format(msgCreateTimeStamp, reportStatusService.getKeyDateFormat());

            NPCMessageType npcMessages = npcDataType.getNPCMessages();

            for (ClhGenericBroadcastMsgType clhGenericBroadcastMsgType : npcMessages.getPortBroadcast()) {
                mnpPortCompleteList.add(getExternalPort(messageHeaderType, clhGenericBroadcastMsgType));
                reportStatusService.setHashValue(reportStatusDateKey, MnpMsgService.COUNT_LISTED_MSG, mnpPortCompleteList.size());
            }
            for (ClhGenericBroadcastMsgType clhGenericBroadcastMsgType : npcMessages.getPortBroadcastException()) {
                mnpPortCompleteList.add(getExternalPort(messageHeaderType, clhGenericBroadcastMsgType));
                reportStatusService.setHashValue(reportStatusDateKey, MnpMsgService.COUNT_LISTED_MSG, mnpPortCompleteList.size());
            }
            for (ClhGenericBroadcastMsgType clhGenericBroadcastMsgType : npcMessages.getPortReversalBroadcast()) {
                mnpPortCompleteList.add(getExternalPort(messageHeaderType, clhGenericBroadcastMsgType));
                reportStatusService.setHashValue(reportStatusDateKey, MnpMsgService.COUNT_LISTED_MSG, mnpPortCompleteList.size());
            }
            for (ClhGenericBroadcastMsgType clhGenericBroadcastMsgType : npcMessages.getNumReturnBroadcast()) {
                mnpPortCompleteList.add(getExternalPort(messageHeaderType, clhGenericBroadcastMsgType));
                reportStatusService.setHashValue(reportStatusDateKey, MnpMsgService.COUNT_LISTED_MSG, mnpPortCompleteList.size());
            }
        }
        return mnpPortCompleteList;
    }

    @Override
    public List listMsgByDate(Date date) throws Exception {
        try {
            String reportStatusDateKey = DateFormatUtils.format(date, reportStatusService.getKeyDateFormat());

            String currentStatus = (String) reportStatusService.getHashValue(reportStatusDateKey, MnpMsgService.STATUS_KEY);
            if (currentStatus == null || currentStatus.equals(MnpMsgService.STATUS_QUEUING)) {
                reportStatusService.setHashValue(reportStatusDateKey, MnpMsgService.STATUS_KEY, MnpMsgService.STATUS_INIT);
                List<NpChInMsgTrace> listMsg = broadcastMsgDao.listMsgByDate(date);
                reportStatusService.setHashValue(reportStatusDateKey, MnpMsgService.TOTAL_MSG_KEY, listMsg.size());

                reportStatusService.setHashValue(reportStatusDateKey, MnpMsgService.STATUS_KEY, MnpMsgService.STATUS_LISTING);
                List list = listExternalPort(listMsg);
                reportStatusService.setHashValue(reportStatusDateKey, MnpMsgService.STATUS_KEY, MnpMsgService.STATUS_LISTED);

                return list;
            } else {
                logger.error("Call listMsg while status: {}", currentStatus);
            }
        } catch (Exception e) {
            logger.error("Error:", e);
            throw e;
        }
        return null;
    }

    @Override
    public int saveMsg(List msgList, Date date) throws Exception {
        if (msgList == null) {
            return 0;
        }
        logger.info("Saving portCompleteDao size: {}", msgList.size());
        int savedCount = 0;
        String reportStatusDateKey = DateFormatUtils.format(date, reportStatusService.getKeyDateFormat());
        reportStatusService.setHashValue(reportStatusDateKey, MnpMsgService.STATUS_KEY, MnpMsgService.STATUS_SAVING);
        for (MnpPortComplete mnpPortComplete : (List<MnpPortComplete>) msgList) {
            savedCount += portCompleteDao.save(mnpPortComplete);
            reportStatusService.setHashValue(reportStatusDateKey, MnpMsgService.COUNT_SAVED_MSG, savedCount);
        }
        reportStatusService.setHashValue(reportStatusDateKey, MnpMsgService.STATUS_KEY, MnpMsgService.STATUS_DONE);
        logger.info("Saved portCompleteDao size: {}", savedCount);
        return savedCount;
    }
}