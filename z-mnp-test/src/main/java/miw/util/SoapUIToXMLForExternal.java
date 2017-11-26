package miw.util;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.eviware.soapui.config.Call;
import com.eviware.soapui.config.Interface;
import com.eviware.soapui.config.Operation;
import com.eviware.soapui.config.SoapuiProject;

public class SoapUIToXMLForExternal {
	public static void main(String[] args) throws IOException {
		try {
			String baseDir = "misc/ClhWsNPCWebService_External";
			int fileCnt = 0;
			File file = new File("misc/soapui/ClhWsNPCWebServiceDr-soapui-project-miw-external.xml");
			System.out.println("SoaupUI ="+file);
			JAXBContext jaxbContext = JAXBContext.newInstance(SoapuiProject.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			SoapuiProject sp = (SoapuiProject) jaxbUnmarshaller.unmarshal(file);
			for (Object settingsOrInterfaceOrTestSuite : sp.getSettingsOrInterfaceOrTestSuite()) {
				if (settingsOrInterfaceOrTestSuite instanceof Interface) {
					Interface intf = (Interface) settingsOrInterfaceOrTestSuite;
					for (Object content : intf.getContent()) {
						if (content instanceof Operation) {
							Operation operation = (Operation) content;
							for (Object opContent : operation.getContent()) {
								if (opContent instanceof Call) {
									Call call = (Call) opContent;
									if (StringUtils.startsWithIgnoreCase(call.getName(), "rmv001 - 1001")) { // change for mvno internal test
										fileCnt++;
										String name = intf.getName() + "/" + operation.getName() + "/" + call.getName();
										File targetFile = new File(baseDir + "/" + name + ".xml");
										System.out.println(fileCnt + "." + name + "->" + targetFile);
										for (Object reqContent : call.getRequest().getContent()) {
											String header = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
											String data = header + "\n" + reqContent;
											FileUtils.writeStringToFile(targetFile, data, "utf-8");

										}
									}

								}
							}
						}
					}

				}
			}

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
