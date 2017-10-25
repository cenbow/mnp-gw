package miw.util;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.mockito.stubbing.Answer;

import com.eviware.soapui.config.SoapuiProject;

public class SoapUIToXML {
	public static void main(String[] args) {
		try {

			File file = new File("misc/example/ClhWsNPCWebServiceDr-soapui-project-miw.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(SoapuiProject.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			SoapuiProject sp= (SoapuiProject) jaxbUnmarshaller.unmarshal(file);


	      } catch (JAXBException e) {
	        e.printStackTrace();
	      }
	}
}
