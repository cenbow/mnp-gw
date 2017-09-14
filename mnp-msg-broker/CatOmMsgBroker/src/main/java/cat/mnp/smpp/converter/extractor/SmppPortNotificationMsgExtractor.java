/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.smpp.converter.extractor;

import com.telcordia.inpac.ws.jaxb.MessageHeaderType;
import com.telcordia.inpac.ws.jaxb.NumTypeWithFlagWithPortDate;
import com.telcordia.inpac.ws.jaxb.PortNotMsgType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP-CAT
 */
public class SmppPortNotificationMsgExtractor implements SmppMsgExtractor {
    
    private String numApprovedFlag = "11";

    @Override
    public List<String> listAcceptMsisdn(MessageHeaderType messageHeader, List msgList) {
        ArrayList<String> msisdnList = new ArrayList<>();
        for (Object msgObject : msgList) {
            PortNotMsgType msg = (PortNotMsgType) msgObject;
            for (NumTypeWithFlagWithPortDate child : msg.getNumberWithFlagWithPortDate()) {
                if (child.getNumApprovedFlag().equals(numApprovedFlag)) {
                    msisdnList.add(child.getMSISDN());
                }
            }
        }
        return msisdnList;
    }

    @Override
    public List<String> listRejectMsisdn(MessageHeaderType messageHeader, List msgList) {
        ArrayList<String> msisdnList = new ArrayList<>();
        for (Object msgObject : msgList) {
            PortNotMsgType msg = (PortNotMsgType) msgObject;
            for (NumTypeWithFlagWithPortDate child : msg.getNumberWithFlagWithPortDate()) {
                if (!child.getNumApprovedFlag().equals(numApprovedFlag)) {
                    msisdnList.add(child.getMSISDN());
                }
            }
        }
        return msisdnList;
    }
}
