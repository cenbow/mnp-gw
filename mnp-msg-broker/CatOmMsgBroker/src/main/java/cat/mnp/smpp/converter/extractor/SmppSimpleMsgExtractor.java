/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.smpp.converter.extractor;

import cat.mnp.clh.util.NpcMessageUtils;
import com.telcordia.inpac.ws.jaxb.MessageHeaderType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author HP-CAT
 */
public class SmppSimpleMsgExtractor implements SmppMsgExtractor {

    @Override
    public List<String> listAcceptMsisdn(MessageHeaderType messageHeader, List msgList) {
        ArrayList<String> msisdnList = new ArrayList<>();
        for (Object msgObject : msgList) {
            List<HashMap<String, String>> msgDataList = NpcMessageUtils.extractOtherMsgData(messageHeader, msgObject);
            for (HashMap<String, String> msgData : msgDataList) {
                msisdnList.add(msgData.get("msisdn"));
            }
        }
        return msisdnList;
    }

    @Override
    public List<String> listRejectMsisdn(MessageHeaderType messageHeader, List msgList) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
