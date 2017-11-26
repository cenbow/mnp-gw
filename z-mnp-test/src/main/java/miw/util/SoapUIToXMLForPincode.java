package miw.util;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.FileUtils;

import miw.xsd.soapui.config.pincode.Call;
import miw.xsd.soapui.config.pincode.Interface;
import miw.xsd.soapui.config.pincode.Method;
import miw.xsd.soapui.config.pincode.Operation;
import miw.xsd.soapui.config.pincode.Request;
import miw.xsd.soapui.config.pincode.Resource;
import miw.xsd.soapui.config.pincode.SoapuiProject;

/**
 *
 * Better version than clh
 *
 */
public class SoapUIToXMLForPincode {
	public static void main(String[] args) throws IOException {
		try {
			String baseDir = "misc/PinCode";
			int fileCnt = 0;
			File file = new File("misc/soapui/PinCode-soapui-project.xml");
			System.out.println("SoaupUI =" + file);
			JAXBContext jaxbContext = JAXBContext.newInstance(SoapuiProject.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			SoapuiProject sp = (SoapuiProject) jaxbUnmarshaller.unmarshal(file);
			for (Interface intf : sp.getInterface()) {
				// for (Operation operation : intf.getOperation()) { // soap
				// for (Call call : operation.getCall()) {
				// String name = intf.getName() + "/" + operation.getName() + "/" + call.getName();
				// fileCnt++;
				// File targetFile = new File(baseDir + "/" + name + ".xml");
				// System.out.println(fileCnt + "." + name + "->" + targetFile);
				// for (Object reqContent : call.getRequest().getContent()) {
				// String header = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
				// String data = header + "\n" + reqContent;
				// FileUtils.writeStringToFile(targetFile, data, "utf-8");
				// }
				// }
				// }
				Resource res = intf.getResource(); // PortOut
				if (res != null) {
					System.out.println(res.getName()+": "+res.getPath());
					Method method = res.getMethod(); // POST
					System.out.println("\t"+method.getName());
					for (Request req : method.getRequest()) {
						String name = intf.getName() + "/" + res.getName() + "/" + req.getName();
						fileCnt++;
						System.out.println(name+"   " +req.getName());
						
					}
				}
			}

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
