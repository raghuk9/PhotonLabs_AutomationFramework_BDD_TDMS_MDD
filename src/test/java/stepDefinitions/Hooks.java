package stepDefinitions;

import api.zapi.zapi_utilities.ZapiTestMgmt;
import cucumber.api.Scenario;
import cucumber.api.StepDefinitionReporter;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import helpers.*;
import platforms.WebPortal;

public class Hooks {
    private long start;
    private long end;
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
            ZapiTestMgmt.getInstance().updateExecutionStatus(scenario.getStatus(), testCaseId,
                    DataHelper.getCurrentData().get("jiraAssignee"));
            if (scenario.isFailed()) {
                String attachmentPath = CommunicationHelper.takeScreenShot(scenario);
                boolean duplicateBugCreation = ZapiTestMgmt.getInstance().duplicateBugCreation;
                String bugId = "";
                String projectKey = ZapiTestMgmt.getInstance().projectKey;
                boolean previousBug = true;
                bugId = DataHelper.getOrUpdateBugID(testCaseId, 2, bugId, false, projectKey);
                boolean creaNewBug = true;
                if (!duplicateBugCreation && bugId.length() > 0) {
                    creaNewBug = false;
                }
                if (creaNewBug) {
                    bugId = ZapiTestMgmt.getInstance().createNewBug(attachmentPath, "2",
                            testCaseId + " - " + scenario.getName(),
                            testCaseId + " - " + scenario.getName() + " got failed",
                            DataHelper.getCurrentData().get("jiraAssignee"));
                    previousBug = false;
                }

                DataHelper.getOrUpdateBugID(testCaseId, 2, bugId, true, projectKey);
                CommunicationHelper.sendEmail(DataHelper.getCurrentData().get("failureEmail"), scenario.getName(),
                        testCaseId, attachmentPath, bugId, previousBug);
                end = System.currentTimeMillis();
                timeTaken = end - start;
                System.out.println("Time Taken for overall execution of a scenario including Jira update : "+timeTaken/1000+" seconds.");
            }
            CommunicationHelper.closeApp();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
