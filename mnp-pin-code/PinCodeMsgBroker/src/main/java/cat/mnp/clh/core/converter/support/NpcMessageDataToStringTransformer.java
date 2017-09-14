/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.clh.core.converter.support;

import cat.mnp.clh.util.NpcMessageUtils;
import com.telcordia.inpac.ws.jaxb.NPCMessageData;
import java.io.IOException;
import org.springframework.integration.annotation.Transformer;
import org.springframework.oxm.Marshaller;

/**
 *
 * @author anuchitr
 */
public class NpcMessageDataToStringTransformer {
    
    private Marshaller jaxbMarshaller;

    public void setJaxbMarshaller(Marshaller jaxbMarshaller) {
        this.jaxbMarshaller = jaxbMarshaller;
    }
    
    @Transformer
    public String transform(NPCMessageData msg) throws IOException {
        String xml = NpcMessageUtils.marshal(jaxbMarshaller, msg);
        return xml;
    }
}
