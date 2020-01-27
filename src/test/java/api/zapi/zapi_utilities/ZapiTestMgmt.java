package api.zapi.zapi_utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
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
    private boolean jiraUpdate = false;
    public boolean duplicateBugCreation = false;
    private static ZapiTestMgmt zapiTestMgmt = null;

    private ZapiTestMgmt() throws Exception {
        try {
            ConfigurationFactory factory = new ConfigurationFactory("src/test/resources/testData/config.xml");
            config = factory.getConfiguration();
            jiraUpdate = config.getBoolean("jira.update");
            if (jiraUpdate) {
                jiraBaseURL = config.getString("jira.baseurl");
                projectKey = config.getString("jira.project.key");
                projectVersion = config.getString("zapi.version");
                testCycleName = config.getString("zapi.testcycle.name");
                jiraAuthorizationToken = "Basic " + config.getString("jira.authorization");
                zapiBaseURL = config.getString("zapi.baseurl");
                accessKey = config.getString("zapi.accesskey");
                secretKey = config.getString("zapi.secretkey");
                jiraUserName = config.getString("jira.userName");
                issueType = config.getString("jira.issueType");
                duplicateBugCreation = config.getBoolean("jira.duplicateBugCreation");
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
        System.out.println("Jira project ID has been retrieved");
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
        System.out.println("Jira version ID has been retrieved");
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

    private String createTestCycle(String cycleName) throws Exception {
        String cycleId = "";
        String payload = "{\"clonedCycleId\": \"\", \"name\": \"" + cycleName
                + "\", \"build\": \"\",\"environment\": \"\",\"description\": \"Test Cycle created for Automation Framework Test Execution on "
                + new Date() + "\",\"startDate\": \"\",\"endDate\": \"\",\"projectId\": \"" + projectId
                + "\",\"versionId\": \"" + versionId + "\"}";

        String responseString = postCall(zapiBaseURL + zapiURI + "cycle", payload);
        JSONObject obj = new JSONObject(responseString);
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

    private String createExecution(String cycleId, int issueId) throws Exception {
        String payload = "{\"cycleId\": \"" + cycleId + "\",\"issueId\": \"" + issueId + "\",\"projectId\": \""
                + projectId + "\",\"versionId\": \"" + versionId + "\",\"assigneeType\": \"currentUser\"}";
        String responseString = postCall(zapiBaseURL + zapiURI + "execution", payload);
        JSONObject obj = new JSONObject(responseString);
        String executionId = obj.getJSONObject("execution").getString("id");
        System.out.println("Zephyr cycle execution has been created");
        return executionId;
    }

    public void updateExecutionStatus(String status, String testCaseId, String jiraAssignee) throws Exception {
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
            String responseString = putCall(zapiBaseURL + zapiURI + "execution/" + executionId, payload);
            System.out.println("Updated Zephyr cycle execution");
        } catch (Exception e) {
            throw new Exception("Zapi | Testcase ID seems to be wrong");
            // Seems the testcase Id is wrong and will continue with the next scenario.
        }
    }

    public String createNewBug(String failureScreenshot, String priorityId, String summary, String description,
            String assignee) throws Exception {
        String bugId = "";
        String payload = "{\"fields\": {\"project\": {\"id\": \"" + projectId + "\"},\"summary\": \"" + summary
                + "\",\"issuetype\": {\"id\": \"" + issueType + "\"},\"assignee\": {\"name\": \"" + assignee
                + "\"},\"priority\": {\"id\": \"" + priorityId + "\"},\"description\": \"" + description + "\"}}";
        String response = postCall(jiraBaseURL + jiraURI + "issue", payload);
        JSONObject obj = new JSONObject(response);
        bugId = obj.getString("key");
        System.out.println("New Bug Created Key : " + bugId);
        attachScreenShotToIssue(failureScreenshot, bugId);
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
}
