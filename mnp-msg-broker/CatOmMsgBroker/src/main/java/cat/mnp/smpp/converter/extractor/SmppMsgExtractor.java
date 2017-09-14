/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.smpp.converter.extractor;

import com.telcordia.inpac.ws.jaxb.MessageHeaderType;
import java.util.List;

/**
 *
 * @author HP-CAT
 */
public interface SmppMsgExtractor {

    public List<String> listAcceptMsisdn(MessageHeaderType messageHeader, List msgList);
    public List<String> listRejectMsisdn(MessageHeaderType messageHeader, List msgList);
}
