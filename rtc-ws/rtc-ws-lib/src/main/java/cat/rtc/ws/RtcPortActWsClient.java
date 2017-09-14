/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.rtc.ws;

import cat.dealer.ibacss.dao.CustomerDataDao;
import cat.dealer.ibacss.domain.BatchPackage;
import cat.dealer.ibacss.domain.BatchService;
import cat.dealer.ibacss.domain.BillingAccount;
import cat.dealer.ibacss.domain.CustomerAccount;
import cat.dealer.ibacss.domain.MnpRtcProvHeaders;
import cat.mnp.mq.core.MsgHandlerBase;
import com.google.common.base.Strings;
import java.io.File;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.rtcproject.ws.jaxb.BILLADDRESS;
import org.rtcproject.ws.jaxb.CORPORATION;
import org.rtcproject.ws.jaxb.PERSONAL;
import org.rtcproject.ws.jaxb.RTCACCOUNT;
import org.rtcproject.ws.jaxb.RTCACCOUNTCREATERESPONSE;
import org.rtcproject.ws.jaxb.RTCACCOUNTCREATETYPE;
import org.rtcproject.ws.jaxb.RTCOFFER;
import org.rtcproject.ws.jaxb.RTCSERVICE;
import org.rtcproject.ws.RtcActivateServiceInputObj;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

/**
 *
 * @author HP
 */
public class RtcPortActWsClient extends MsgHandlerBase {

    private static final Logger logger = LoggerFactory.getLogger(RtcPortActWsClient.class);
    private AmqpTemplate amqpTemplate;
    private AmqpTemplate errorAmqpTemplate;
    private MessageProperties msgProperties;

    private Map<String, Integer> accountTypeMap;
    private Map<String, Integer> accountCategoryMap;
    private Map<Integer, Integer> svcTypeMap;
    private Integer countryCode;

    private RtcActivateServiceInputObj inputObj;
    private String[] successResponseList;
    private String[] acceptCallWsBatchStatus;
    private CustomerDataDao customerDao;
    private String dateFormat;

    private static final int NEW_BA = 0;
    private static final int EXISTING_BA = 1;

    public AmqpTemplate getAmqpTemplate() {
        return amqpTemplate;
    }

    public void setAmqpTemplate(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public AmqpTemplate getErrorAmqpTemplate() {
        return errorAmqpTemplate;
    }

    public void setErrorAmqpTemplate(AmqpTemplate errorAmqpTemplate) {
        this.errorAmqpTemplate = errorAmqpTemplate;
    }

    public MessageProperties getMsgProperties() {
        return msgProperties;
    }

    public void setMsgProperties(MessageProperties msgProperties) {
        this.msgProperties = msgProperties;
    }

    public Map<String, Integer> getAccountTypeMap() {
        return accountTypeMap;
    }

    public void setAccountTypeMap(Map<String, Integer> accountTypeMap) {
        this.accountTypeMap = accountTypeMap;
    }

    public Map<String, Integer> getAccountCategoryMap() {
        return accountCategoryMap;
    }

    public void setAccountCategoryMap(Map<String, Integer> accountCategoryMap) {
        this.accountCategoryMap = accountCategoryMap;
    }

    public Map<Integer, Integer> getSvcTypeMap() {
        return svcTypeMap;
    }

    public void setSvcTypeMap(Map<Integer, Integer> svcTypeMap) {
        this.svcTypeMap = svcTypeMap;
    }

    public Integer getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Integer countryCode) {
        this.countryCode = countryCode;
    }

    public RtcActivateServiceInputObj getInputObj() {
        return inputObj;
    }

    public void setInputObj(RtcActivateServiceInputObj inputObj) {
        this.inputObj = inputObj;
    }

    public String[] getSuccessResponseList() {
        return successResponseList;
    }

    public void setSuccessResponseList(String[] successResponseList) {
        this.successResponseList = successResponseList;
    }

    public String[] getAcceptCallWsBatchStatus() {
        return acceptCallWsBatchStatus;
    }

    public void setAcceptCallWsBatchStatus(String[] acceptCallWsBatchStatus) {
        this.acceptCallWsBatchStatus = acceptCallWsBatchStatus;
    }

    public CustomerDataDao getCustomerDao() {
        return customerDao;
    }

    public void setCustomerDao(CustomerDataDao customerDao) {
        this.customerDao = customerDao;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    protected RTCACCOUNTCREATERESPONSE callWs(RtcActivateServiceInputObj inputObj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void processMsg(Message msg) throws Exception {
        String msgString = new String(msg.getBody());
        String msgId = msg.getMessageProperties().getReceivedRoutingKey();

        Map<String, Object> mqHeaders = msg.getMessageProperties().getHeaders();
        logger.info("Received mqHeaders: {}", mqHeaders);

        Long custAcctId = (Long) mqHeaders.get(MnpRtcProvHeaders.CustomerAccountId.name());

        //GET customerAccountId 
        CustomerAccount ca = customerDao.get(custAcctId);
        BillingAccount ba = ca.getBillingAccount();
        Set<BatchService> services = ca.getBatchServices();
        int baType = NEW_BA;

        logger.info("Service size: {}", services.size());

        //find success provisioning
        for (BatchService service : services) {
            if (service.getBatchStatus().compareTo(CustomerAccount.CREATING_CA_STATUS) >= 0 && !service.isPrepaid()) {
                baType = EXISTING_BA;
                break;
            }
        }

        for (BatchService service : services) {
            if (!ArrayUtils.contains(acceptCallWsBatchStatus, service.getBatchStatus())) {
                logger.info("Ignore provisioning of orderId: {}, msisdn: {}, status: {}", ca.getReferenceOrderId(), service.getPropertyOne(), service.getBatchStatus());
                continue;
            }
            inputObj.setPORTINOPERATOR(service.getDonorOperator());

            RTCACCOUNTCREATETYPE rtcPortinAccount = new RTCACCOUNTCREATETYPE();
            inputObj.setRTCCREATEINACCOUNT(rtcPortinAccount);

            inputObj.setCRMDEALERCODE(service.getDealerCode());
            inputObj.setUSERNAME(service.getUserId());
            if (service.isPrepaid()) {//prepaid
                inputObj.setRTCMNPMODE(0);
                inputObj.setRTCDLMODE("0");
            } else {//postpaid & Existing BA
                rtcPortinAccount.setBILLINGACCOUNTID(ba.getCatBillAcctNumber());
                if (baType == EXISTING_BA) {
                    inputObj.setRTCMNPMODE(1);
                    inputObj.setRTCDLMODE("1");
                } else {
                    inputObj.setRTCMNPMODE(0);
                    inputObj.setRTCDLMODE("0");
                }
            }

            RTCACCOUNT rtcAccountInfo = new RTCACCOUNT();
            rtcPortinAccount.setRTCACCOUNTINFO(rtcAccountInfo);

            rtcAccountInfo.setACCOUNTTYPE(accountTypeMap.get(ca.getCustomerType()));
            rtcAccountInfo.setACCOUNTCATEGORY(accountCategoryMap.get(ca.getCustomerType()));
            rtcAccountInfo.setCARDTYPE(ca.getCatCardType());
            rtcAccountInfo.setCARDNO(ca.getCatCardNumber());
            rtcAccountInfo.setDATEACTIVE(DateFormatUtils.format(service.getStartDate(), dateFormat));

            BILLADDRESS billAddress = new BILLADDRESS();
            BILLADDRESS vatAddress = new BILLADDRESS();

            if (service.isPrepaid()) {//prepaid
                if (ca.isIndividual()) {//individual
                    PERSONAL personal = new PERSONAL();
                    if (ca.getCatThaiTitle() != null) {
                        personal.setTITLE(ca.getCatThaiTitle().toString());
                    } else {
                        personal.setTITLE("");
                    }

                    personal.setFNAME(ca.getFirstName());
                    personal.setLNAME(ca.getLastName());
                    billAddress.setPERSONAL(personal);
                    vatAddress.setPERSONAL(personal);
                } else {//corperate
                    CORPORATION corperate = new CORPORATION();
                    corperate.setTITLECOMPANY(ca.getCatThaiCorpType() != null ? ca.getCatThaiCorpType().toString() : null);
                    corperate.setCOMPANY(ca.getFullName());
                    billAddress.setCORPORATION(corperate);
                    vatAddress.setCORPORATION(corperate);
                }

            } else {//postpaid
                if (ca.isIndividual()) {//individual
                    PERSONAL personal = new PERSONAL();
                    if (ca.getCatThaiTitle() != null) {
                        personal.setTITLE(ca.getCatThaiTitle().toString());
                    } else {
                        personal.setTITLE("");
                    }
                    personal.setFNAME(ca.getFirstName());
                    personal.setLNAME(ca.getLastName());
                    billAddress.setPERSONAL(personal);
                    vatAddress.setPERSONAL(personal);
                } else {//corperate
                    CORPORATION corperate = new CORPORATION();
                    corperate.setTITLECOMPANY(ba.getCatBillCompSalutationLkp() != null ? ba.getCatBillCompSalutationLkp().toString() : null);
                    corperate.setCOMPANY(ba.getCatBillCompName());
                    billAddress.setCORPORATION(corperate);
                    vatAddress.setCORPORATION(corperate);
                }
            }

            RTCSERVICE rtcService = new RTCSERVICE();
            rtcPortinAccount.setRTCSERVICES(rtcService);

            rtcAccountInfo.setVATADDRESS(vatAddress);
            rtcAccountInfo.setBILLADDRESS(billAddress);

            if (service.isPrepaid()) {//prepaid
                String[] addr1 = {ca.getCatHouseNumber(),
                    ca.getCatMoo() != null ? "หมู่ที่ " + ca.getCatMoo() : null,
                    ca.getCatVillage()};
                String[] addr2 = {ca.getCatMoreInfo(), ca.getCatTrokSoi()};
                billAddress.setADDRESS1(Strings.emptyToNull(StringUtils.join(addr1, " ").trim()));
                billAddress.setADDRESS2(Strings.emptyToNull(StringUtils.join(addr2, " ").trim()));
                billAddress.setADDRESS3(ca.getCatRoad());

                billAddress.setKHET(ca.getCatKhet());
                billAddress.setKWANG(ca.getCatKwang());
                billAddress.setPROVINCE(ca.getCatProvince());
                billAddress.setPOSTALCODE(ca.getPostalCode());
                billAddress.setCOUNTRYCODE(countryCode);

                vatAddress.setKHET(ca.getCatKhet());
                vatAddress.setKWANG(ca.getCatKwang());
                vatAddress.setPROVINCE(ca.getCatProvince());
                vatAddress.setPOSTALCODE(ca.getPostalCode());
                vatAddress.setCOUNTRYCODE(countryCode);
                vatAddress.setADDRESS1(Strings.emptyToNull(StringUtils.join(addr1, " ").trim()));
                vatAddress.setADDRESS2(Strings.emptyToNull(StringUtils.join(addr2, " ").trim()));
                vatAddress.setADDRESS3(ca.getCatRoad());

            } else {
                billAddress.setKHET(ca.getCatKhet());
                billAddress.setKWANG(ca.getCatKwang());
                billAddress.setPROVINCE(ca.getCatProvince());
                billAddress.setPOSTALCODE(ca.getPostalCode());
                billAddress.setCOUNTRYCODE(countryCode);
                billAddress.setADDRESS1(ba.getBillAddrLine1());
                billAddress.setADDRESS2(ba.getBillAddrLine2());
                billAddress.setADDRESS3(ba.getBillAddrLine3());

                vatAddress.setKHET(ba.getVatAddrKhetAmphur());
                vatAddress.setKWANG(ba.getVatAddrLine4());
                vatAddress.setPROVINCE(ba.getVatAddrProvinceLkp());
                vatAddress.setPOSTALCODE(ba.getVatAddrPostCode());
                vatAddress.setCOUNTRYCODE(countryCode);
                vatAddress.setADDRESS1(ba.getBillAddrLine1());
                vatAddress.setADDRESS2(ba.getBillAddrLine2());
                vatAddress.setADDRESS3(ba.getBillAddrLine3());

            }
            rtcService.setSERVICETYPE(svcTypeMap.get(service.getCatSvcTypeLkp()));
            rtcService.setDATEACTIVE(DateFormatUtils.format(service.getStartDate(), dateFormat));
            rtcService.setSERVICESTATUS(1);
            rtcService.setMSISDN(countryCode + service.getPropertyOne());
            rtcService.setIMSI(service.getImsi());
            rtcService.setICCID(service.getPropertyTwo());
            rtcService.setINTERNALSALEREP(service.getUserId());

            // RTC_OFFER Tag
            for (BatchPackage batchPackage : service.getBatchPackages()) {
                String dateActive = DateFormatUtils.format(batchPackage.getStartDate(), dateFormat);
                RTCOFFER rtcOffer = new RTCOFFER();
                rtcService.getRTCOFFER().add(rtcOffer);

                rtcOffer.setOFFERID(new BigDecimal(batchPackage.getPoOfferId()));
                rtcOffer.setOFFERLEVEL(0);
                rtcOffer.setDATEACTIVE(dateActive);

                rtcOffer = new RTCOFFER();
                rtcService.getRTCOFFER().add(rtcOffer);

                rtcOffer.setOFFERID(new BigDecimal(batchPackage.getSoOfferId()));
                rtcOffer.setOFFERLEVEL(1);
                rtcOffer.setDATEACTIVE(dateActive);
            }

            //CALL RTC SERVICE
            msgProperties.getHeaders().clear();
            msgProperties.setHeader(MnpRtcProvHeaders.CustomerAccountId.name(), service.getCustomerAccountId());
            msgProperties.setHeader(MnpRtcProvHeaders.ServiceId.name(), service.getServiceId());
            msgProperties.setHeader(MnpRtcProvHeaders.Msisdn.name(), "0" + service.getPropertyOne());
            File file = null;

            try {
                logger.info("Calling rtc ws");
                RTCACCOUNTCREATERESPONSE response = callWs(inputObj);
                msgProperties.setHeader(MnpRtcProvHeaders.RtcTransId.name(), response.getRTCCREATETRANSID());
                msgProperties.setHeader(MnpRtcProvHeaders.RtcStatus.name(), response.getRTCTRANSSTATUS());
                msgProperties.setHeader(MnpRtcProvHeaders.RtcTransMsg.name(), response.getRTCTRANSMSG());

                // 3.Push in rabbitMQ (update IBACSS Status)
                Message newMsg = new Message(msgString.getBytes(), msgProperties);
                logger.debug("Ws response: {}", msgProperties.getHeaders());

                if (ArrayUtils.contains(successResponseList, response.getRTCTRANSSTATUS())) {//no error
                    baType = EXISTING_BA;
                    amqpTemplate.send(msgId, newMsg);
                } else {//has error
                    logger.error("Error detected after calling ws response: {}", msgProperties.getHeaders());
                    errorAmqpTemplate.send(msgId, newMsg);
                }
            } catch (Exception e) {
                msgProperties.setHeader(MnpRtcProvHeaders.RtcTransId.name(), "");
                msgProperties.setHeader(MnpRtcProvHeaders.RtcStatus.name(), e.getMessage());
                msgProperties.setHeader(MnpRtcProvHeaders.RtcTransMsg.name(), "");

                Message newMsg = new Message(msgString.getBytes(), msgProperties);
                logger.error(String.format("Error detected while calling rtc ws: %s", msgProperties.getHeaders()), e);
                errorAmqpTemplate.send(msgId, newMsg);
            }

        }

    }

}
