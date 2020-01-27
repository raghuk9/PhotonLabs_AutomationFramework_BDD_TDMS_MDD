package api;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import api.zapi.ZFJCloudRestClient;
import api.zapi.jwt.jwt_core.JwtGenerator;

public class APICall {
    protected String jiraAuthorizationToken = "";
    protected String jiraBaseURL = "";
    protected String zapiBaseURL = "";
    protected String accessKey = "";
    protected String jiraUserName = "";
    protected String secretKey = "";

    protected String getCall(String url) throws Exception {
        Client client = ClientBuilder.newClient();
        Response response = null;
        if (url.contains(zapiBaseURL)) {
            ZFJCloudRestClient client1 = ZFJCloudRestClient.restBuilder(zapiBaseURL, accessKey, secretKey, jiraUserName)
                    .build();
            JwtGenerator jwtGenerator = client1.getJwtGenerator();
            URI uri = new URI(url);
            int expirationInSec = 360;
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

    protected String postCall(String url, String payload) throws Exception {
        Client client = ClientBuilder.newClient();
        Entity payloadEntity = Entity.json(payload);
        Response response = null;
        if (url.contains(zapiBaseURL)) {
            ZFJCloudRestClient client1 = ZFJCloudRestClient.restBuilder(zapiBaseURL, accessKey, secretKey, jiraUserName)
                    .build();
            JwtGenerator jwtGenerator = client1.getJwtGenerator();
            URI uri = new URI(url);
            int expirationInSec = 360;
            String jwt = jwtGenerator.generateJWT("POST", uri, expirationInSec);
            response = client.target(uri).request().header("Authorization", jwt).header("zapiAccessKey", accessKey)
                    .post(payloadEntity);
        } else {
            response = client.target(url).request(MediaType.APPLICATION_JSON_TYPE)
                    .header("Authorization", jiraAuthorizationToken).post(payloadEntity);
        }
        if (response.getStatus() != 200) {
            if (response.getStatus() != 201) {
                System.out.println("Response stauts : " + response.getStatus());
                System.out.println("Response : " + response.toString());
                throw new Exception("Unable to connect to JIRA");
            }
        }
        return response.readEntity(String.class);
    }

    protected String putCall(String url, String payload) throws Exception {
        Client client = ClientBuilder.newClient();
        Entity payloadEntity = Entity.json(payload);
        Response response = null;
        if (url.contains(zapiBaseURL)) {
            ZFJCloudRestClient client1 = ZFJCloudRestClient.restBuilder(zapiBaseURL, accessKey, secretKey, jiraUserName)
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
