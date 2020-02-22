package stepDefinitions;

import api.zapi.zapi_utilities.ZapiTestMgmt;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.no.Gitt;
import helpers.CommunicationHelper;
import helpers.DataHelper;

public class Hooks {
    private long start;
    private long end;
    private String jiraAssignee;
    private boolean jiraUpdate;
   
    @Before
    public void beforeScenario(Scenario scenario) throws Exception {
        DataHelper.setCurrentScenario(scenario);
        start = System.currentTimeMillis();
    }

    @After
    public void afterScenario(Scenario scenario) throws Exception {
        String testCaseId;
        try {
            testCaseId = DataHelper.getCurrentData().get("testCaseId");
            end = System.currentTimeMillis();
            long timeTaken = end - start;
            System.out.println("Time Taken for scenario execution : "+timeTaken/1000+" seconds.");
            jiraUpdate = ZapiTestMgmt.getInstance().jiraUpdate;
            if(jiraUpdate) {
            	jiraAssignee = DataHelper.getCurrentData().get("jiraAssignee"); 
            	ZapiTestMgmt.getInstance().updateExecutionStatus(scenario.getStatus(), testCaseId,
            			jiraAssignee);	
            }
            if (scenario.isFailed()) {
                String attachmentPath = CommunicationHelper.takeScreenShot(scenario);
                String projectKey = ZapiTestMgmt.getInstance().projectKey;
                if(ZapiTestMgmt.getInstance().createBug && jiraUpdate) {
                	System.out.println("Starting Bug Creation in Jira");
                    String bugId = "";
                    boolean previousBug = true;
                    bugId = DataHelper.getOrUpdateBugID(testCaseId, 2, bugId, false, projectKey);
                    boolean creaNewBug = true;
                    if (bugId.length() > 0) {
                        creaNewBug = false;
                        previousBug = true;
                        System.out.println("Already Bug Created for this hence ending this process");
                    }
                    if (creaNewBug) {
                    	System.out.println("Going to Create New bug");
                        bugId = ZapiTestMgmt.getInstance().createNewBug(attachmentPath, "2",
                                testCaseId + " - " + scenario.getName(),
                                testCaseId + " - " + scenario.getName() + " got failed",
                                jiraAssignee);
                        previousBug = false;
                        DataHelper.getOrUpdateBugID(testCaseId, 2, bugId, true, projectKey);
                        System.out.println("Bug Created Successfully and updated into spread sheet");
                    }
                    CommunicationHelper.sendEmail(DataHelper.getCurrentData().get("failureEmail"), scenario.getName(),
                            testCaseId, attachmentPath, bugId, previousBug,jiraAssignee);
                }else {
                	CommunicationHelper.sendEmail(DataHelper.getCurrentData().get("failureEmail"), scenario.getName(),
                            testCaseId, attachmentPath,jiraAssignee);
                }
                end = System.currentTimeMillis();
                timeTaken = end - start;
                System.out.println("Time Taken for overall execution of a scenario including Jira update : "+timeTaken/1000+" seconds.");
            }
            CommunicationHelper.closeApp();
        } catch (Exception e) {
        	CommunicationHelper.closeApp();
            e.printStackTrace();
        }
    }
}
