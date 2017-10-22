/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.clh.util;

import com.telcordia.inpac.ws.jaxb.MessageHeaderType;
import com.telcordia.inpac.ws.jaxb.NPCDataType;
import com.telcordia.inpac.ws.jaxb.NPCMessageData;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Map;
import java.util.Objects;
import javax.xml.transform.stream.StreamSource;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;

/**
 *
 * @author HP
 */
public class NpcMessageValidationHandler {

    private static final Logger logger = LoggerFactory.getLogger(NpcMessageValidationHandler.class);
    private String validationCode;
    private String successResult;
    private Map<String, Map<String, String>> userMapper;
    private Map<String, String> errorCodeMapper;
    private String replaceString1;
    private String replaceString2;
    private Map<BigInteger, BigInteger> portTypeMapper;
    private String[] allowedMsgId;
    private NPCDataType npcDataType;
    private Unmarshaller jaxbUnMarshaller;
    private Marshaller jaxbMarshaller;

    public String getValidationCode() {
        return validationCode;
    }

    public void setValidationCode(String validationCode) {
        this.validationCode = validationCode;
    }

    public String getSuccessResult() {
        return successResult;
    }

    public void setSuccessResult(String successResult) {
        this.successResult = successResult;
    }

    public Map<String, Map<String, String>> getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(Map<String, Map<String, String>> userMapper) {
        this.userMapper = userMapper;
    }

    public Map<String, String> getErrorCodeMapper() {
        return errorCodeMapper;
    }

    public void setErrorCodeMapper(Map<String, String> errorCodeMapper) {
        this.errorCodeMapper = errorCodeMapper;
    }

    public Map<BigInteger, BigInteger> getPortTypeMapper() {
        return portTypeMapper;
    }

    public void setPortTypeMapper(Map<BigInteger, BigInteger> portTypeMapper) {
        this.portTypeMapper = portTypeMapper;
    }

    public String[] getAllowedMsgId() {
        return allowedMsgId;
    }

    public void setAllowedMsgId(String[] allowedMsgId) {
        this.allowedMsgId = allowedMsgId;
    }

    public String getReplaceString1() {
        return replaceString1;
    }

    public void setReplaceString1(String replaceString1) {
        this.replaceString1 = replaceString1;
    }

    public String getReplaceString2() {
        return replaceString2;
    }

    public void setReplaceString2(String replaceString2) {
        this.replaceString2 = replaceString2;
    }

    public NPCDataType getNpcDataType() {
        return npcDataType;
    }

    public void setNpcDataType(NPCDataType npcDataType) {
        this.npcDataType = npcDataType;
    }

    public Unmarshaller getJaxbUnMarshaller() {
        return jaxbUnMarshaller;
    }

    public void setJaxbUnMarshaller(Unmarshaller jaxbUnMarshaller) {
        this.jaxbUnMarshaller = jaxbUnMarshaller;
    }

    public Marshaller getJaxbMarshaller() {
        return jaxbMarshaller;
    }

    public void setJaxbMarshaller(Marshaller jaxbMarshaller) {
        this.jaxbMarshaller = jaxbMarshaller;
    }

    public boolean isValidationSuccess(String result) {
        return successResult.equals(result);
    }

    public String validate(String userId, String password, String xml) {
        String decodedPassword = new String(Base64.decodeBase64(password));

        String validationResult = successResult;

        if (!userMapper.containsKey(userId)) {
            validationCode = "NPC1009E";
            validationResult = errorCodeMapper.get(validationCode).replace(replaceString1, userId);
            logger.error("Error validating username: {}", validationResult);
            return validationResult;
        } else if (!decodedPassword.equals(userMapper.get(userId).get("password"))) {
            validationCode = "NPC1003E";
            validationResult = errorCodeMapper.get(validationCode);
            logger.error("Error validating password: {}", validationResult);
            return validationResult;
        }

        try {
            logger.info("Unmarshalling xml");
            NPCMessageData npcMessageData = unMarshal(getJaxbUnMarshaller(), xml);
            npcDataType = npcMessageData.getNPCData();
        } catch (Exception ex) {
            validationCode = "NPC1007E";
            validationResult = errorCodeMapper.get(validationCode).replace(replaceString1, ex.getMessage());
            logger.error("Exception validating schema: ", ex);
            return validationResult;
        }

        MessageHeaderType messageHeader = npcDataType.getMessageHeader();
        String msgId = messageHeader.getMessageID().toString();

        if (!ArrayUtils.contains(allowedMsgId, msgId)) {
            validationCode = "NPC1027E";
            validationResult = errorCodeMapper.get(validationCode).replace(replaceString1, userId).replace(replaceString2, msgId);
            logger.error("Error validating allowedMsgId: {}", msgId);
            return validationResult;
        }

        if (!Objects.equals(messageHeader.getReceiver(), userMapper.get(userId).getOrDefault("receiver", messageHeader.getReceiver()))) {
            validationCode = "NPC1034E";
            validationResult = errorCodeMapper.get(validationCode).replace(replaceString1, userMapper.get(userId).get("receiver"));
            logger.error("Error validating receiver: {}", messageHeader.getReceiver());
            return validationResult;
        }

        if (!Objects.equals(messageHeader.getSender(), userMapper.get(userId).getOrDefault("sender", messageHeader.getSender()))) {
            validationCode = "NPC1013E";
            validationResult = errorCodeMapper.get(validationCode).replace(replaceString1, userId).replace(replaceString2, userMapper.get(userId).get("sender"));
            logger.error("Error validating sender: {}", messageHeader.getSender()); // FIX: Done change to sender
            return validationResult;
        }

        if (!messageHeader.getPortType().equals(portTypeMapper.get(messageHeader.getMessageID()))) {
            validationCode = "NPC1024E";
            validationResult = errorCodeMapper.get(validationCode).replace(replaceString1, msgId).replace(replaceString2, messageHeader.getPortType().toString());
            logger.error("Error validating portType: {}", messageHeader.getPortType());
            return validationResult;
        }

        logger.info("Validation result {}: {}", validationResult, messageHeader);
        return validationResult;
    }

    public NPCMessageData unMarshal(Unmarshaller unMarshaller, String xml) throws IOException {
        StreamSource source = new StreamSource(IOUtils.toInputStream(xml));
        return (NPCMessageData) unMarshaller.unmarshal(source);
    }
}
