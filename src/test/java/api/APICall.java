package api;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.openqa.selenium.json.Json;

import api.zapi.ZFJCloudRestClient;
import api.zapi.jwt.jwt_core.JwtGenerator;

public class APICall {
	protected String jiraAuthorizationToken = "";
	protected String jiraBaseURL = "";
	protected String zapiBaseURL = "";
	protected String accessKey = "";
	protected String accountId = "";
	protected String secretKey = "";

	protected String getCall(String url) throws Exception {
		Client client = ClientBuilder.newClient();
		Response response = null;
		if (url.contains(zapiBaseURL)) {
			ZFJCloudRestClient client1 = ZFJCloudRestClient.restBuilder(zapiBaseURL, accessKey, secretKey, accountId)
					.build();
			JwtGenerator jwtGenerator = client1.getJwtGenerator();
			URI uri = new URI(url);
			int expirationInSec = 3600;
			String jwt = jwtGenerator.generateJWT("GET", uri, expirationInSec);
			response = client.target(uri).request().header("Authorization", jwt).header("zapiAccessKey", accessKey)
					.get();
		} else {
			response = client.target(url).request(MediaType.APPLICATION_JSON_TYPE)
					.header("Authorization", jiraAuthorizationToken).get();
		}
		if (response.getStatus() != 200) {
			throw new Exception("Unable to connect to JIRA");
		}
		return response.readEntity(String.class);
	}

	protected JSONObject postCall(String url, StringEntity payload) throws Exception {
		Client client = ClientBuilder.newClient();
		HttpResponse response = null;
		HttpClient restClient = new DefaultHttpClient();
		HttpPost createCycleReq;
		JSONObject postObject = null;
		if (url.contains(zapiBaseURL)) {
			ZFJCloudRestClient client1 = ZFJCloudRestClient.restBuilder(zapiBaseURL, accessKey, secretKey, accountId)
					.build();
			JwtGenerator jwtGenerator = client1.getJwtGenerator();
			URI uri = new URI(url);
			int expirationInSec = 3600;
			String jwt = jwtGenerator.generateJWT("POST", uri, expirationInSec);

			createCycleReq = new HttpPost(uri);
			createCycleReq.addHeader("Content-Type", "application/json");
			createCycleReq.addHeader("Authorization", jwt);
			createCycleReq.addHeader("zapiAccessKey", accessKey);
			createCycleReq.setEntity(payload);
		} else {
			createCycleReq = new HttpPost(url);
			createCycleReq.addHeader("Content-Type", "application/json");
			createCycleReq.addHeader("Authorization", jiraAuthorizationToken);
			createCycleReq.setEntity(payload);
		}
		try {
			response = restClient.execute(createCycleReq);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int statusCode = response.getStatusLine().getStatusCode();
		HttpEntity entity = response.getEntity();
		String string = EntityUtils.toString(entity);
		if (statusCode >= 200 && statusCode < 300) {			
			try {
				postObject = new JSONObject(string);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			try {
				JSONObject Object = new JSONObject(string);
				System.out.println("Error Message : "+Object);
				throw new ClientProtocolException("Unexpected response status: " + statusCode);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			}
		}
		return postObject;
	}

	protected String putCall(String url, String payload) throws Exception {
		Client client = ClientBuilder.newClient();
		Entity payloadEntity = Entity.json(payload);
		Response response = null;
		if (url.contains(zapiBaseURL)) {
			ZFJCloudRestClient client1 = ZFJCloudRestClient.restBuilder(zapiBaseURL, accessKey, secretKey, accountId)
					.build();
			JwtGenerator jwtGenerator = client1.getJwtGenerator();
			URI uri = new URI(url);
			int expirationInSec = 360;
			String jwt = jwtGenerator.generateJWT("PUT", uri, expirationInSec);
			response = client.target(uri).request().header("Authorization", jwt).header("zapiAccessKey", accessKey)
					.put(payloadEntity);
		} else {
			response = client.target(url).request(MediaType.APPLICATION_JSON_TYPE)
					.header("Authorization", jiraAuthorizationToken).put(payloadEntity);
		}
		if (response.getStatus() != 200) {
			throw new Exception("Unable to connect to JIRA");
		}
		return response.readEntity(String.class);
	}
}
