package api.zapi.zapi_utilities;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import api.APICall;
import helpers.ConfigurationHelper;

public class ZapiTestMgmt extends APICall {

    public String projectKey = "";
    private String issueType = "";
    private String projectVersion = "";
    private String testCycleName = "";
    private String zapiURI = "/public/rest/api/1.0/";
    private String jiraURI = "/rest/api/2/";
    private String projectId = "";
    private String versionId = "";
    private String cycleId = "";
    private Configuration config = null;
    private DateFormat dateFormat = null;
    private Date date = null;
    private int cycleIteration = 1;
    public boolean jiraUpdate = false;
    public boolean createBug = false;
    private static ZapiTestMgmt zapiTestMgmt = null;
    
    @SuppressWarnings("deprecation")
	private ZapiTestMgmt() throws Exception {
        try {
            ConfigurationFactory factory = new ConfigurationFactory("src/test/resources/testData/config.xml");
            config = factory.getConfiguration();
            if(System.getProperty("jira.update")!=null) {
            	jiraUpdate = Boolean.valueOf(System.getProperty("jira.update"));
            }else {
            	jiraUpdate = config.getBoolean("jira.update");
            }
            if (jiraUpdate) {
                jiraBaseURL = config.getString("jira.baseurl");
                projectKey = config.getString("jira.project.key");
                projectVersion = config.getString("zapi.version");
                testCycleName = config.getString("zapi.testcycle.name");
                jiraAuthorizationToken = "Basic " + config.getString("jira.authorization");
                zapiBaseURL = config.getString("zapi.baseurl");
                accessKey = config.getString("zapi.accesskey");
                secretKey = config.getString("zapi.secretkey");
                accountId = config.getString("jira.accountId");
                issueType = config.getString("jira.issueType");
                createBug = config.getBoolean("jira.createBug");
                projectId = getProjectId();
                versionId = getVersionId();
                dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                date = new Date();
                cycleId = getCycleId();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unable to load properties file");
        }
    }

    public static ZapiTestMgmt getInstance() throws Exception {
        if (zapiTestMgmt == null) {
            zapiTestMgmt = new ZapiTestMgmt();
        }
        return zapiTestMgmt;
    }

    private String getProjectId() throws Exception {
        String projectId = "";
        String responseString = getCall(jiraBaseURL + jiraURI + "project/" + projectKey);
        JSONObject obj = new JSONObject(responseString);
        projectId = obj.getString("id");
        if (projectId.isEmpty()) {
            throw new Exception("Unable to find the Project " + projectKey);
        }
        System.out.println("Jira project ID has been retrieved : "+projectId);
        return projectId;
    }

    private String getVersionId() throws Exception {
        String responseString = getCall(jiraBaseURL + jiraURI + "project/" + projectKey + "/versions");
        JSONArray arr = new JSONArray(responseString);
        for (Object object : arr) {
            JSONObject obj = (JSONObject) object;
            if (projectVersion.equals(obj.getString("name"))) {
                versionId = obj.getString("id");
            }
        }
        if (versionId.isEmpty()) {
            throw new Exception("Unable to find the release version " + projectVersion);
        }
        System.out.println("Jira version ID has been retrieved : "+versionId);
        return versionId;
    }

    public String getCycleId() throws Exception {
        String cycleName;
        if (testCycleName.contains("Platform")) {
            testCycleName = testCycleName.replaceAll("Platform", ConfigurationHelper.getPlatform().toString());
            testCycleName = testCycleName.replaceAll("Date", dateFormat.format(date).toString());
        }
        cycleName = testCycleName + cycleIteration;
        String responseString = getCall(
                zapiBaseURL + zapiURI + "cycles/search?projectId=" + projectId + "&versionId=" + versionId);
        JSONArray obj = new JSONArray(responseString);
        for (int i = 0; i < obj.length(); i++) {
            JSONObject jsonObject = obj.getJSONObject(i);
            if (cycleName.equals(jsonObject.getString("name"))) {
                cycleId = jsonObject.getString("id");
            }
        }
        if (!cycleId.isEmpty()) {
            cycleIteration++;
            cycleId = "";
            getCycleId();
        } else {
            cycleId = createTestCycle(cycleName);
        }
        return cycleId;
    }

    private StringEntity createCyclePayload(String cycleName) {
    	String cycleDescription = "This Cycle is created using Automation Script on "+new Date();
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
		return cycleJSON;
    }
    
    private String createTestCycle(String cycleName) throws Exception {        
    	String cycleId = ""; 
    	StringEntity cycleJSON = createCyclePayload(cycleName);
    	
		JSONObject obj = postCall(zapiBaseURL + zapiURI + "cycle?expand=&clonedCycleId=", cycleJSON);
        cycleId = obj.getString("id");
        System.out.println("Zephyr New Cycle - "+cycleName+" has been created");
        return cycleId;
    }

    public int getIssueId(String testCaseId) throws Exception {
        int issueId;
        String responseString = getCall(jiraBaseURL + jiraURI + "issue/" + testCaseId + "?fields=id");
        JSONObject obj = new JSONObject(responseString);
        issueId = Integer.valueOf(obj.getString("id"));
        System.out.println("Jira Testcase ID has been retrieved");
        return issueId;
    }

    private StringEntity createExecutionPayload(String cycleId,int issueId) {
    	JSONObject createExecutionObj = new JSONObject();
    	createExecutionObj.put("cycleId", cycleId);        			
    	createExecutionObj.put("issueId", issueId);			
    	createExecutionObj.put("projectId", projectId);
    	createExecutionObj.put("versionId", versionId);
    	createExecutionObj.put("assigneeType", "currentUser");
		StringEntity executionJSON = null;
		try {
			executionJSON = new StringEntity(createExecutionObj.toString());
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		return executionJSON;
    }
    
    private String createExecution(String cycleId, int issueId) throws Exception {
        StringEntity createExection = createExecutionPayload(cycleId, issueId);
        JSONObject obj = postCall(zapiBaseURL + zapiURI + "execution", createExection);
        String executionId = obj.getJSONObject("execution").getString("id");
        System.out.println("Zephyr cycle execution has been created");
        return executionId;
    }

    public void updateExecutionStatus(String status, String testCaseId, String jiraAssignee) throws Exception {
        try {
            int jiraIssueId = getIssueId(testCaseId);
        	String executionId = createExecution(cycleId, jiraIssueId);
        	int statusId;
            if (status.equalsIgnoreCase("passed")) {
                statusId = 1;
            } else if (status.equalsIgnoreCase("failed")) {
                statusId = 2;
            } else {
                statusId = -1;
            }
            String payload = "{\"status\":{\"id\":" + statusId + "},\"issueId\":" + jiraIssueId + ",\"projectId\":"
                    + projectId + ",\"id\":\"" + executionId + "\"}";
            putCall(zapiBaseURL + zapiURI + "execution/" + executionId, payload);
            System.out.println("Updated Zephyr cycle execution");
        } catch (Exception e) {
            throw new Exception("Zapi | Testcase ID seems to be wrong");
            // Seems the testcase Id is wrong and will continue with the next scenario.
        }
    }
    
    public void linkBugToExecution(String status, String testCaseId, String jiraAssignee) throws Exception {
        try {
            if (!jiraUpdate) {
                return;
            }
            int jiraIssueId = getIssueId(testCaseId);
        	String executionId = createExecution(cycleId, jiraIssueId);
        	int statusId;
            if (status.equalsIgnoreCase("passed")) {
                statusId = 1;
            } else if (status.equalsIgnoreCase("failed")) {
                statusId = 2;
            } else {
                statusId = -1;
            }
            String payload = "{\"status\":{\"id\":" + statusId + "},\"issueId\":" + jiraIssueId + ",\"projectId\":"
                    + projectId + ",\"id\":\"" + executionId + "\"}";
            putCall(zapiBaseURL + zapiURI + "execution/" + executionId, payload);
            System.out.println("Updated Zephyr cycle execution");
        } catch (Exception e) {
            throw new Exception("Zapi | Testcase ID seems to be wrong");
            // Seems the testcase Id is wrong and will continue with the next scenario.
        }
    }
    
    private String getAccountId(String UserName) throws Exception {
    	String accountId = null;
    	UserName = UserName.split("\\s+")[0];
    	String responseString = getCall(jiraBaseURL + jiraURI + "user/search?query=" + UserName);
    	JSONArray array = new JSONArray(responseString);
    	JSONObject obj = (JSONObject)  array.get(0);
    	accountId = (String) obj.get("accountId");
    	return accountId;
    }

    private StringEntity createBugPayload(String priorityId, String summary, String description,
            String assignee) throws JSONException, Exception {
    	
    	JSONObject createBugObj = new JSONObject();
    	JSONObject fieldObj = new JSONObject();
    	fieldObj.put("project", new JSONObject().put("id", projectId));        			
    	fieldObj.put("summary", summary);
    	fieldObj.put("issuetype", new JSONObject().put("id", issueType));
    	fieldObj.put("assignee", new JSONObject().put("id", getAccountId(assignee)));
    	fieldObj.put("priority", new JSONObject().put("id", priorityId));
    	fieldObj.put("description", description);
    	createBugObj.put("fields", fieldObj);
		StringEntity executionJSON = null;
		try {
			executionJSON = new StringEntity(createBugObj.toString());
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		return executionJSON;
    }
    
    public String createNewBug(String failureScreenshot,String videoPath, String priorityId, String summary, String description,
            String assignee) throws Exception {
        String bugId = "";
        StringEntity payload = createBugPayload(priorityId, summary, description, assignee);
        
        JSONObject response = postCall(jiraBaseURL + jiraURI + "issue", payload);
        bugId = response.getString("key");
        System.out.println("New Bug Created Key : " + bugId);
        attachScreenShotToIssue(failureScreenshot, bugId);
        attachVideoToIssue(videoPath, bugId);
        return bugId;
    }

    public void attachScreenShotToIssue(String attachmentPath, String testCaseId) {
        String curDir = System.getProperty("user.dir");
        File fileUpload = new File(curDir + File.separator + attachmentPath);
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost postRequest = new HttpPost(jiraBaseURL + jiraURI + "issue/" + testCaseId + "/attachments");
        postRequest.setHeader("Authorization", jiraAuthorizationToken);
        postRequest.setHeader("X-Atlassian-Token", "nocheck");
        MultipartEntityBuilder entity = MultipartEntityBuilder.create();
        entity.addPart("file", new FileBody(fileUpload));
        postRequest.setEntity(entity.build());
        HttpResponse response = null;
        try {
            response = httpClient.execute(postRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void attachVideoToIssue(String attachmentPath, String testCaseId) {
        File fileUpload = new File(attachmentPath);
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost postRequest = new HttpPost(jiraBaseURL + jiraURI + "issue/" + testCaseId + "/attachments");
        postRequest.setHeader("Authorization", jiraAuthorizationToken);
        postRequest.setHeader("X-Atlassian-Token", "nocheck");
        MultipartEntityBuilder entity = MultipartEntityBuilder.create();
        entity.addPart("file", new FileBody(fileUpload));
        postRequest.setEntity(entity.build());
        HttpResponse response = null;
        try {
            response = httpClient.execute(postRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
