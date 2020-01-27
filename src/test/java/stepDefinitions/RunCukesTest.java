package stepDefinitions;

import java.io.File;
import java.io.IOException;

import com.cucumber.listener.ExtentProperties;
import helpers.CommunicationHelper;
import org.json.simple.parser.ParseException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", monochrome=true, plugin = {
        "html:target/cucumber-html-report", "json:target/cucumber.json","pretty", "com.cucumber.listener.ExtentCucumberFormatter:target/report/report.html"}, tags = { "@smoke" })

public class RunCukesTest {

    @AfterClass
    public static void generateReport() throws IOException, ParseException {
        Reporter.loadXMLConfig(new File("src/test/resources/extent-config.xml"));
        Reporter.setSystemInfo("user", System.getProperty("user.name"));
        Reporter.setSystemInfo("os", "windows");
        Reporter.setTestRunnerOutput("Jpmc test runner output message");
        CommunicationHelper.closeApp();
    }
}
