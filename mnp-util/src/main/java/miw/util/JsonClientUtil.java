package miw.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpHeaders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonClientUtil {
	public static void main(String[] args) throws UnsupportedOperationException, IOException {
		String url = "http://c1rtgw.cattelecom.com/C1rtGw/api/subscribers/66882753318/info/summary";
		String user = "mnp-usr";
		String pass = "mnp-pwd";
		System.out.println(get(url, user, pass));
	}

	// http://www.baeldung.com/httpclient-4-basic-authentication
	public static Map<String, Object> get(String url, String user, String pass) throws UnsupportedOperationException, IOException {

		HttpGet request = new HttpGet(url);
		String auth = user + ":" + pass;
		byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("ISO-8859-1")));
		String authHeader = "Basic " + new String(encodedAuth);
		request.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
		request.addHeader("Content-Type", "application/json;charset=UTF-8");

		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = client.execute(request);

		String jsonStr = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
		Map<String, Object> jsonMap = new ObjectMapper().readValue(jsonStr, new TypeReference<Map<String, Object>>() {
		});
		return jsonMap;
	}

	// http://www.baeldung.com/httpclient-4-basic-authentication
	public static Map<String, Object> getDefault(String url, String user, String pass) throws UnsupportedOperationException, IOException {

		CredentialsProvider provider = new BasicCredentialsProvider();
		provider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(user, pass));

		HttpClient client = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build(); // request.addHeader("Authorization", "Basic ...");

		HttpGet request = new HttpGet(url);
		request.addHeader("Content-Type", "application/json;charset=UTF-8");

		HttpResponse response = client.execute(request);
		String jsonStr = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
		Map<String, Object> jsonMap = new ObjectMapper().readValue(jsonStr, new TypeReference<Map<String, Object>>() {
		});
		return jsonMap;
	}
}
