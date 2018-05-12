
package cat.sd.domain.api;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import cat.sd.domain.SubScriberCoreBalanceResponse;
import cat.sd.domain.SubscriberInfoRequest;
import cat.sd.domain.SubscriberInfoResponse;

/**
 * This class was generated by Apache CXF 3.2.1
 * 2561-05-12T23:30:56.829+07:00
 * Generated source version: 3.2.1
 * 
 */
public final class SubscriberInfoInterface_SubscriberInfoInterfacePort_Client {

    private static final QName SERVICE_NAME = new QName("http://domain.sd.cat/", "SubscriberInfoInterfaceService");

    private SubscriberInfoInterface_SubscriberInfoInterfacePort_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = SubscriberInfoInterfaceService.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        SubscriberInfoInterfaceService ss = new SubscriberInfoInterfaceService(wsdlURL, SERVICE_NAME);
        SubscriberInfoInterface port = ss.getSubscriberInfoInterfacePort();  
        
        {
        System.out.println("Invoking getInfo...");
        cat.sd.domain.SubscriberInfoRequest _getInfo_arg0 = null;
        cat.sd.domain.SubscriberInfoResponse _getInfo__return = port.getInfo(_getInfo_arg0);
        System.out.println("getInfo.result=" + _getInfo__return);


        }
        {
        System.out.println("Invoking getCoreBalance...");
        cat.sd.domain.SubscriberInfoRequest _getCoreBalance_arg0 = null;
        cat.sd.domain.SubScriberCoreBalanceResponse _getCoreBalance__return = port.getCoreBalance(_getCoreBalance_arg0);
        System.out.println("getCoreBalance.result=" + _getCoreBalance__return);


        }

        System.exit(0);
    }

}
