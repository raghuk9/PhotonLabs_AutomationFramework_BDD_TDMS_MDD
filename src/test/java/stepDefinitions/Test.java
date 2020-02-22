/* D SOFTWARE INCORPORATED
 * Copyright 2007-2011 D Software Incorporated
 * All Rights Reserved.
 *
 * NOTICE: D Software permits you to use, modify, and distribute this 
file
 * in accordance with the terms of the license agreement accompanying 
it.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an “AS IS? BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or 
implied.
 */
/*
 * This is a sample of what can be done by using API's with Zephyr through 
the JAVA coding language.
 * By creating the .java files, you can import them 
into your workspace and then call them in your custom program. 
 * 
 * Eclipse Java EE IDE for Web Developers.
Version: Neon Release (4.6.0)
Build id: 20160613-1800
 * Java- Java JDK 1.8.0_101
 * 
 * Author: Swapna Kumar Vemula, Product Support Engineer, D Software Inc.
 */
package stepDefinitions;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import api.zapi.ZFJCloudRestClient;
import api.zapi.jwt.jwt_core.JwtGenerator;
import api.zapi.zapi_utilities.ZapiTestMgmt;


public class Test {
	
	public static void main(String[] args) throws Exception {
		
		ZapiTestMgmt.getInstance().getCycleId();
//		sample();
		
	}
	
	public static void sample() throws JSONException, URISyntaxException, IllegalStateException, IOException
	{
		// Replace zephyr baseurl <ZAPI_Cloud_URL> shared with the user for ZAPI Cloud
				String zephyrBaseUrl = "https://prod-api.zephyr4jiracloud.com/connect";
				// zephyr accessKey , we can get from Addons >> zapi section
				String accessKey = "OTUyZmQ5ZjMtMmNiMy0zMmQ4LWIwZjEtY2Q4MDViMGYwNTkxIDVlNDZhYWVmOTI0ZGIxMGU3NGI3N2Q3YiBVU0VSX0RFRkFVTFRfTkFNRQ";
				// zephyr secretKey , we can get from Addons >> zapi section
				String secretKey = "AsfqA5ZRCHV779QD1jlJ7c6HTl2QHJ3hsU3s2mFLV6g";
				String accountId = "557058:d99b15ce-fb96-40a1-b6e7-3ffdc632dc73";
				ZFJCloudRestClient client = ZFJCloudRestClient.restBuilder(zephyrBaseUrl, accessKey, secretKey, accountId)
						.build();

				/** Declare the Variables here **/
				int projectId = 10000;
				int versionId = 10000;
				String cycleName = "Test Cycle -- API DEMO";
				String cycleDescription = "Created by ZAPI CLOUD API";
				
				String createCycleUri = zephyrBaseUrl + "/public/rest/api/1.0/cycle?expand=&clonedCycleId=";
				
				/** Cycle Object created - DO NOT EDIT **/
				JSONObject createCycleObj = new JSONObject();
				createCycleObj.put("name", cycleName);        			
				createCycleObj.put("description", cycleDescription);			
				createCycleObj.put("startDate", System.currentTimeMillis());
				createCycleObj.put("projectId", projectId);
				createCycleObj.put("versionId", versionId);

				StringEntity cycleJSON = null;
				try {
					cycleJSON = new StringEntity(createCycleObj.toString());
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				Test cc = new Test();
				String cycleID = cc.createCycle(createCycleUri, client, accessKey, cycleJSON);
				System.out.println("Cycle Created with Cycle Id :" + cycleID);

				/**
				 * Add tests to Cycle IssueId's
				 * 
				 */
				
				String addTestsUri = zephyrBaseUrl + "/public/rest/api/1.0/executions/add/cycle/" + cycleID;
				String[] issueIds = { "SUP-1", "TUR-1" }; //Issue Id's to be added to Test Cycle, add more issueKeys separated by comma

				JSONObject addTestsObj = new JSONObject();
				addTestsObj.put("issues", issueIds);
				addTestsObj.put("method", "1");
				addTestsObj.put("projectId", projectId);
				addTestsObj.put("versionId", versionId);

				StringEntity addTestsJSON = null;
				try {
					addTestsJSON = new StringEntity(addTestsObj.toString());
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				String ID = cc.addTestsToCycle(addTestsUri, client, accessKey, addTestsJSON);
				System.out.println("Tests added successfully  ");
			}

			public String createCycle(String uriStr, ZFJCloudRestClient client, String accessKey, StringEntity cycleJSON)
					throws URISyntaxException, JSONException {

				URI uri = new URI(uriStr);
				int expirationInSec = 3600;
				JwtGenerator jwtGenerator = client.getJwtGenerator();
				String jwt = jwtGenerator.generateJWT("POST", uri, expirationInSec);
				System.out.println(uri.toString());
				System.out.println("createCycle : "+jwt);

				HttpResponse response = null;
				HttpClient restClient = new DefaultHttpClient();

				HttpPost createCycleReq = new HttpPost(uri);
				createCycleReq.addHeader("Content-Type", "application/json");
				createCycleReq.addHeader("Authorization", jwt);
				createCycleReq.addHeader("zapiAccessKey", accessKey);
				createCycleReq.setEntity(cycleJSON);
				

				try {
					response = restClient.execute(createCycleReq);
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				int statusCode = response.getStatusLine().getStatusCode();
				System.out.println(statusCode);
				String cycleId = "-1";
				if (statusCode >= 200 && statusCode < 300) {
					HttpEntity entity = response.getEntity();
					String string = null;
					try {
						string = EntityUtils.toString(entity);
						JSONObject cycleObj = new JSONObject(string);
						cycleId = cycleObj.getString("id");
					} catch (ParseException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

				} else {
					try {
						throw new ClientProtocolException("Unexpected response status: " + statusCode);
					} catch (ClientProtocolException e) {
						e.printStackTrace();
					}
				}
				return cycleId;
			}

			public String addTestsToCycle(String uriStr, ZFJCloudRestClient client, String accessKey, StringEntity addTestsJSON)
					throws URISyntaxException, JSONException, IllegalStateException, IOException {

				URI uri = new URI(uriStr);
				int expirationInSec = 360;
				JwtGenerator jwtGenerator = client.getJwtGenerator();
				String jwt = jwtGenerator.generateJWT("POST", uri, expirationInSec);
				System.out.println(uri.toString());
				System.out.println(jwt);

				HttpResponse response = null;
				HttpClient restClient = new DefaultHttpClient();

				HttpPost addTestsReq = new HttpPost(uri);
				addTestsReq.addHeader("Content-Type", "application/json");
				addTestsReq.addHeader("Authorization", jwt);
				addTestsReq.addHeader("zapiAccessKey", accessKey);
				addTestsReq.setEntity(addTestsJSON);

				try {
					response = restClient.execute(addTestsReq);
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				int statusCode = response.getStatusLine().getStatusCode();
				System.out.println(statusCode);
				System.out.println(response.toString());
				String string = null;
				if (statusCode >= 200 && statusCode < 300) {
					HttpEntity entity = response.getEntity();			
					try {
						string = EntityUtils.toString(entity);
						//System.out.println(string);
						JSONObject cycleObj = new JSONObject(entity);
						//System.out.println(cycleObj.toString());
					} catch (ParseException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

				} else {
					try {
						throw new ClientProtocolException("Unexpected response status: " + statusCode);
					} catch (ClientProtocolException e) {
						e.printStackTrace();
					}
				}
				return string;
	}
}
