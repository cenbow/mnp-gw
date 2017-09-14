/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.smpp.converter.extractor;

import com.telcordia.inpac.ws.jaxb.MessageHeaderType;
import com.telcordia.inpac.ws.jaxb.NumTypeWithPinWithCLHFlag;
import com.telcordia.inpac.ws.jaxb.PortReqAckMsgType;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP-CAT
 */
public class SmppPortAckMsgExtractor implements SmppMsgExtractor {
    
    private BigInteger clhAccepted = BigInteger.ONE;

    @Override
    public List<String> listAcceptMsisdn(MessageHeaderType messageHeader, List msgList) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> listRejectMsisdn(MessageHeaderType messageHeader, List msgList) {
        ArrayList<String> msisdnList = new ArrayList<>();
        for (Object msgObject : msgList) {
            PortReqAckMsgType msg = (PortReqAckMsgType) msgObject;
            for (NumTypeWithPinWithCLHFlag child : msg.getNumberWithPinWithCLHFlag()) {
                if (!child.getCLHAccepted().equals(clhAccepted)) {
                    msisdnList.add(child.getMSISDN());
                }
            }
        }
        return msisdnList;
    }
}
