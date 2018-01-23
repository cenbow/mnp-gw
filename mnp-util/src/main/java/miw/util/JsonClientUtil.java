package miw.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonClientUtil {
	public static void main(String[] args) {

	}

	// http://www.baeldung.com/httpclient-4-basic-authentication
	public static Map<String, Object> get(String url, String user, String pass) throws UnsupportedOperationException, IOException {

		CredentialsProvider provider = new BasicCredentialsProvider();
		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(user, pass);
		provider.setCredentials(AuthScope.ANY, credentials);

		HttpClient client = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build(); // request.addHeader("Authorization", "Basic ...");

		HttpGet request = new HttpGet(url);
		request.addHeader("Content-Type", "application/json;charset=UTF-8");

		HttpResponse response = client.execute(request);
		response.getEntity().getContent();
		String jsonStr = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
		Map<String, Object> jsonMap = new ObjectMapper().readValue(jsonStr, new TypeReference<Map<String, Object>>() {
		});
		return jsonMap;
	}
}
