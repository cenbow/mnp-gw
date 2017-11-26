package miw.tc;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class WSClient {
	private static final Logger logger = LoggerFactory.getLogger(WSClient.class);
	private String wsURL;
	private String baseWSFileDirStr;

	public WSClient(String wsURL, String baseWSFileDirStr) {
		this.wsURL = wsURL;
		this.baseWSFileDirStr = baseWSFileDirStr;
	}

	public String send(String fileStr) throws Exception {
		logger.info(wsURL);
		File file = new File(baseWSFileDirStr + "/" + fileStr);
		logger.info(file.getAbsolutePath());

		// Code to make a webservice HTTP request
		String responseString = "";
		String outputString = "Soap Return: ";

		URL url = new URL(wsURL);
		URLConnection connection = url.openConnection();
		HttpURLConnection httpConn = (HttpURLConnection) connection;
		ByteArrayOutputStream bout = new ByteArrayOutputStream();

		String xmlInput = FileUtils.readFileToString(file, "UTF-8");
		// @formatter:on
		byte[] buffer = new byte[xmlInput.length()];
		buffer = xmlInput.getBytes();
		bout.write(buffer);
		byte[] b = bout.toByteArray();
		// Set the appropriate HTTP parameters.
		httpConn.setRequestProperty("Content-Length", String.valueOf(b.length));
		httpConn.setRequestProperty("Content-Type", "soap/xml; charset=utf-8");
		httpConn.setRequestMethod("POST");
		httpConn.setDoOutput(true);
		httpConn.setDoInput(true);
		OutputStream out = httpConn.getOutputStream();
		// Write the content of the request to the outputstream of the HTTP Connection.
		out.write(b);
		out.close();
		// Ready with sending the request.

		// Read the response.
		if (httpConn.getResponseCode() ==200) {
		InputStreamReader isr = new InputStreamReader(httpConn.getInputStream());
			BufferedReader in = new BufferedReader(isr);
			// Write the SOAP message response to a String.
			while ((responseString = in.readLine()) != null) {
				outputString = outputString + responseString;
			}
			logger.warn(outputString);
		}else {// read error
			InputStreamReader esr = new InputStreamReader(httpConn.getErrorStream());
			BufferedReader ein = new BufferedReader(esr);

			// Write the SOAP message response to a String.
			while ((responseString = ein.readLine()) != null) {
				outputString = outputString + responseString;
			}
			logger.error(outputString);
		}


		return outputString;
	}

	// format the XML in your String
	public String formatXML(String unformattedXml) {
		try {
			Document document = parseXmlFile(unformattedXml);
			OutputFormat format = new OutputFormat(document);
			format.setIndenting(true);
			format.setIndent(3);
			format.setOmitXMLDeclaration(true);
			Writer out = new StringWriter();
			XMLSerializer serializer = new XMLSerializer(out, format);
			serializer.serialize(document);
			return out.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Document parseXmlFile(String in) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(in));
			return db.parse(is);
		} catch (ParserConfigurationException e) {
			throw new RuntimeException(e);
		} catch (SAXException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) throws Exception {
		WSClient clhWs = new WSClient("http://localhost:8080/ClhWs/services/NPCWebService", "misc/ClhWsNPCWebServiceDr/NPCWebServiceSoap12Binding/processNPCMsg");
		clhWs.send("MIW_OM_1002.xml");
	}
}
