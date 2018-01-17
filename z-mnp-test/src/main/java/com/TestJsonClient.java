package com;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestJsonClient {
	public static void main(String[] args) throws ClientProtocolException, IOException {
		HttpClient client = HttpClientBuilder.create().build();

		HttpGet request = new HttpGet("http://localhost:8080/DummyWs//C1rtGw/api/subscribers/1111/info/summary");
		request.addHeader("Content-Type", "application/json;charset=UTF-8");
		request.addHeader("Authorization", "Basic ...");


		HttpResponse response = client.execute(request);
		Header contentType = response.getFirstHeader("Content-Type");
		String charset= contentType.getValue();
		System.out.println(charset);

		response.getEntity().getContent();
		String jsonStr = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
		System.out.println(jsonStr);
		Map<String, Object> m = new ObjectMapper().readValue(jsonStr, new TypeReference<Map<String, Object>>() {
		});
		System.out.println("result =" + m.get("ratingStateType"));
		System.out.println(m.get("rating")); // note: if use JSONObject will throw exception
	}
}
