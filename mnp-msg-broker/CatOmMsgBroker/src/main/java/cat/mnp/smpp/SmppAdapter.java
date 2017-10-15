/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.smpp;

import cat.mnp.mq.core.MsgHandlerBase;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import org.jsmpp.InvalidResponseException;
import org.jsmpp.PDUException;
import org.jsmpp.bean.AlertNotification;
import org.jsmpp.bean.Alphabet;
import org.jsmpp.bean.BindType;
import org.jsmpp.bean.DataSm;
import org.jsmpp.bean.DeliverSm;
import org.jsmpp.bean.DeliveryReceipt;
import org.jsmpp.bean.ESMClass;
import org.jsmpp.bean.GSMSpecificFeature;
import org.jsmpp.bean.GeneralDataCoding;
import org.jsmpp.bean.IntermediateNotification;
import org.jsmpp.bean.MessageClass;
import org.jsmpp.bean.MessageMode;
import org.jsmpp.bean.MessageType;
import org.jsmpp.bean.NumberingPlanIndicator;
import org.jsmpp.bean.RegisteredDelivery;
import org.jsmpp.bean.SMEOriginatedAcknowledgement;
import org.jsmpp.bean.SMSCDeliveryReceipt;
import org.jsmpp.bean.TypeOfNumber;
import org.jsmpp.extra.NegativeResponseException;
import org.jsmpp.extra.ProcessRequestException;
import org.jsmpp.extra.ResponseTimeoutException;
import org.jsmpp.extra.SessionState;
import org.jsmpp.session.BindParameter;
import org.jsmpp.session.DataSmResult;
import org.jsmpp.session.MessageReceiverListener;
import org.jsmpp.session.SMPPSession;
import org.jsmpp.session.Session;
import org.jsmpp.session.SessionStateListener;
import org.jsmpp.util.InvalidDeliveryReceiptException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.integration.smpp.core.SmppConstants;
import org.springframework.util.Assert;

/**
 *
 * @author HP-CAT
 */
public class SmppAdapter extends MsgHandlerBase {

    private static final Logger logger = LoggerFactory.getLogger(SmppAdapter.class);
    private static final int MAX_MULTIPART_MSG_SEGMENT_SIZE_UCS2 = 134;
    private static final int MAX_SINGLE_MSG_SEGMENT_SIZE_UCS2 = 70;
    private static final int MAX_MULTIPART_MSG_SEGMENT_SIZE_7BIT = 153;
    private static final int MAX_SINGLE_MSG_SEGMENT_SIZE_7BIT = 160;
    private String host;
    private int port;
    private long timeout;
    private String systemId;
    private String password;
    private String serviceType = "CMT";
    private MessageConverter messageConverter;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public MessageConverter getMessageConverter() {
        return messageConverter;
    }

    public void setMessageConverter(MessageConverter messageConverter) {
        this.messageConverter = messageConverter;
    }

    private class SessionStateListenerImpl implements SessionStateListener {

        @Override
        public void onStateChange(SessionState newState, SessionState oldState, Object source) {
            logger.info("Session state changed from " + oldState + " to " + newState);
        }
    }

    private class MessageReceiverListenerImpl implements MessageReceiverListener {

        @Override
        public void onAcceptDeliverSm(DeliverSm deliverSm) throws ProcessRequestException {

            if (MessageType.SMSC_DEL_RECEIPT.containedIn(deliverSm.getEsmClass())) {
                // this message is delivery receipt
                try {
                    DeliveryReceipt delReceipt = deliverSm.getShortMessageAsDeliveryReceipt();

                    // lets cover the id to hex string format
                    long id = Long.parseLong(delReceipt.getId()) & 0xffffffff;
                    String messageId = Long.toString(id, 16).toUpperCase();

                    /*
                     * you can update the status of your submitted message on the
                     * database based on messageId
                     */

                    logger.info("Receiving delivery receipt for message '{}' from {} to {}: {}", messageId, deliverSm.getSourceAddr(), delReceipt);
                } catch (InvalidDeliveryReceiptException e) {
                    logger.error("Failed getting delivery receipt", e);
                }
            } else {
                // this message is regular short message

                /*
                 * you can save the incoming message to database.
                 */

                logger.info("Receiving message : {}", new String(deliverSm.getShortMessage()));
            }
        }

        @Override
        public void onAcceptAlertNotification(AlertNotification alertNotification) {
            logger.info("OnAcceptAlertNotification: SourceAddr: {}, EsmeAddr: {}", alertNotification.getSourceAddr(), alertNotification.getEsmeAddr());
        }

        @Override
        public DataSmResult onAcceptDataSm(DataSm dataSm, Session source) throws ProcessRequestException {
            logger.info("OnAcceptDataSm: SourceAddr: {}, DestAddress: {}", dataSm.getSourceAddr(), dataSm.getDestAddress());
            return null;
        }
    }

    private byte[][] splitUnicodeMessage(byte[] aMessage, Integer maximumMultipartMessageSegmentSize) {

        final byte UDHIE_HEADER_LENGTH = 0x05;
        final byte UDHIE_IDENTIFIER_SAR = 0x00;
        final byte UDHIE_SAR_LENGTH = 0x03;

        // determine how many messages have to be sent
        int numberOfSegments = aMessage.length / maximumMultipartMessageSegmentSize;
        int messageLength = aMessage.length;
        if (numberOfSegments > 255) {
            numberOfSegments = 255;
            messageLength = numberOfSegments * maximumMultipartMessageSegmentSize;
        }
        if ((messageLength % maximumMultipartMessageSegmentSize) > 0) {
            numberOfSegments++;
        }

        // prepare array for all of the msg segments
        byte[][] segments = new byte[numberOfSegments][];

        int lengthOfData;

        // generate new reference number
        byte[] referenceNumber = new byte[1];
        new Random().nextBytes(referenceNumber);

        // split the message adding required headers
        for (int i = 0; i < numberOfSegments; i++) {
            if (numberOfSegments - i == 1) {
                lengthOfData = messageLength - i * maximumMultipartMessageSegmentSize;
            } else {
                lengthOfData = maximumMultipartMessageSegmentSize;
            }

            // new array to store the header
            segments[i] = new byte[6 + lengthOfData];

            // UDH header
            // doesn't include itself, its header length
            segments[i][0] = UDHIE_HEADER_LENGTH;
            // SAR identifier
            segments[i][1] = UDHIE_IDENTIFIER_SAR;
            // SAR length
            segments[i][2] = UDHIE_SAR_LENGTH;
            // reference number (same for all messages)
            segments[i][3] = referenceNumber[0];
            // total number of segments
            segments[i][4] = (byte) numberOfSegments;
            // segment number
            segments[i][5] = (byte) (i + 1);

            // copy the data into the array
            System.arraycopy(aMessage, (i * maximumMultipartMessageSegmentSize), segments[i], 6, lengthOfData);

        }
        return segments;
    }

    @Override
    public void processMsg(Message msg) throws Exception {
        Assert.notNull(messageConverter, "messageConverter could not be null");

        logger.debug("Converting msisdnList");
        List<String> msisdnList;
        try {
            msisdnList = (List<String>) messageConverter.fromMessage(msg);
        } catch(MessageConversionException | ClassCastException ex) {
            logger.error("Error while converting msg to object", ex);
            return;
        }

        String sourceMsisdn = (String) msg.getMessageProperties().getHeaders().get(SmppConstants.SRC_ADDR);
        String smsMsg = (String) msg.getMessageProperties().getHeaders().get(SmppConstants.SMS_MSG);

        MessageClass messageClass = MessageClass.CLASS1;

        if(true) {
    		logger.warn("Mockup SMS :"+smsMsg);  // TODO: MIW Test no send SMS
    		return;
    	}
        SMPPSession session = new SMPPSession();
        session.addSessionStateListener(new SessionStateListenerImpl());
        session.setMessageReceiverListener(new MessageReceiverListenerImpl());

        try {
            session.connectAndBind(host, port, new BindParameter(BindType.BIND_TRX, systemId, password, "cp", TypeOfNumber.UNKNOWN, NumberingPlanIndicator.UNKNOWN, null), timeout);
        } catch (IOException ex) {
            logger.error("Cannot connect to smsc server: ", ex);
            session.close();
            return;
        }

        // requesting delivery report
        final RegisteredDelivery registeredDelivery = new RegisteredDelivery();
        registeredDelivery.setIntermediateNotification(IntermediateNotification.REQUESTED);
        registeredDelivery.setSMSCDeliveryReceipt(SMSCDeliveryReceipt.SUCCESS_FAILURE);
        registeredDelivery.setSMEOriginatedAcknowledgement(SMEOriginatedAcknowledgement.DELIVERY);

        // configure variables acording to if message contains national
        // characters
        Alphabet alphabet;
        int maximumSingleMessageSize;
        int maximumMultipartMessageSegmentSize;
        byte[] byteSingleMessage;
        if (Gsm0338.isEncodeableInGsm0338(smsMsg)) {
            byteSingleMessage = smsMsg.getBytes();
            alphabet = Alphabet.ALPHA_DEFAULT;
            maximumSingleMessageSize = MAX_SINGLE_MSG_SEGMENT_SIZE_7BIT;
            maximumMultipartMessageSegmentSize = MAX_MULTIPART_MSG_SEGMENT_SIZE_7BIT;
        } else {
            byteSingleMessage = smsMsg.getBytes("UTF-16BE");
            alphabet = Alphabet.ALPHA_UCS2;
            maximumSingleMessageSize = MAX_SINGLE_MSG_SEGMENT_SIZE_UCS2;
            maximumMultipartMessageSegmentSize = MAX_MULTIPART_MSG_SEGMENT_SIZE_UCS2;
        }

        // check if message needs splitting and set required sending parameters
        byte[][] byteMessagesArray;
        ESMClass esmClass;
        if (smsMsg.length() > maximumSingleMessageSize) {
            // split message according to the maximum length of a segment
            byteMessagesArray = splitUnicodeMessage(byteSingleMessage, maximumMultipartMessageSegmentSize);
            // set UDHI so PDU will decode the header
            esmClass = new ESMClass(MessageMode.DEFAULT, MessageType.DEFAULT, GSMSpecificFeature.UDHI);
        } else {
            byteMessagesArray = new byte[][]{byteSingleMessage};
            esmClass = new ESMClass();
        }

        logger.info("Sending message {}", smsMsg);
        logger.info("Message is {} characters long and will be sent as {} messages with params: {} {} ", smsMsg.length(), byteMessagesArray.length, alphabet, messageClass);

        // submit all messages
        for (String msisdn : msisdnList) {
            for (int j = 0; j < byteMessagesArray.length; j++) {
                String messageId = submitMessage(session, byteMessagesArray[j], sourceMsisdn, msisdn, messageClass, alphabet, esmClass, registeredDelivery);
                logger.info("Message for msisdn {} is submitted, message_id: {}", msisdn, messageId);
            }
            try {
                logger.info("Waiting for message to be received, msisdn: {}", msisdn);
                Thread.sleep(5);
            } catch (InterruptedException e) {
                //ignore
            }
        }

        session.unbindAndClose();
    }

    private String submitMessage(SMPPSession session, byte[] message, String sourceMsisdn, String destinationMsisdn, MessageClass messageClass, Alphabet alphabet, ESMClass esmClass, RegisteredDelivery registeredDelivery) {
        String messageId = null;
        try {
            messageId = session.submitShortMessage(serviceType, TypeOfNumber.NATIONAL, NumberingPlanIndicator.UNKNOWN,
                    sourceMsisdn, TypeOfNumber.UNKNOWN, NumberingPlanIndicator.UNKNOWN, destinationMsisdn, esmClass,
                    (byte) 0, (byte) 1, null, null, registeredDelivery,
                    (byte) 0, new GeneralDataCoding(alphabet, esmClass), (byte) 0, message);
        } catch (PDUException e) {
            // Invalid PDU parameter
            logger.error("Invalid PDU parameter", e);
        } catch (ResponseTimeoutException e) {
            // Response timeout
            logger.error("Response timeout", e);
        } catch (InvalidResponseException e) {
            // Invalid response
            logger.error("Receive invalid respose", e);
        } catch (NegativeResponseException e) {
            // Receiving negative response (non-zero command_status)
            logger.error("Receive negative response", e);
        } catch (IOException e) {
            logger.error("IO error occur", e);
        }
        return messageId;
    }
}

/**
 * Based on http://www.smsitaly.com/Download/ETSI_GSM_03.38.pdf
 */
class Gsm0338 {

    private static final short[] isoGsm0338Array = {64, 163, 36, 165, 232, 233, 249, 236, 242, 199, 10, 216, 248, 13,
        197, 229, 0, 95, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 198, 230, 223, 201, 32, 33, 34, 35, 164, 37, 38, 39, 40, 41,
        42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 161, 65, 66, 67,
        68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 196, 214, 209,
        220, 167, 191, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115,
        116, 117, 118, 119, 120, 121, 122, 228, 246, 241, 252, 224};
    private static final short[][] extendedIsoGsm0338Array = {{10, 12}, {20, 94}, {40, 123}, {41, 125},
        {47, 92}, {60, 91}, {61, 126}, {62, 93}, {64, 124}, {101, 164}};

    public static boolean isEncodeableInGsm0338(String isoString) {
        byte[] isoBytes = isoString.getBytes();
        outer:
        for (int i = 0; i < isoBytes.length; i++) {
            for (int j = 0; j < isoGsm0338Array.length; j++) {
                if (isoGsm0338Array[j] == isoBytes[i]) {
                    continue outer;
                }
            }
            for (int j = 0; j < extendedIsoGsm0338Array.length; j++) {
                if (extendedIsoGsm0338Array[j][1] == isoBytes[i]) {
                    continue outer;
                }
            }
            return false;
        }
        return true;
    }
}