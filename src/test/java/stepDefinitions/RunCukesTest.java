package stepDefinitions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import helpers.AppiumServer;
import helpers.ConfigurationHelper;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", monochrome=true, plugin = {
        "html:target/cucumber-html-report", "json:target/cucumber.json","pretty", "com.cucumber.listener.ExtentCucumberFormatter:target/report/report.html"}, tags = { "@Smoke" })

public class RunCukesTest {
	static AppiumServer server = new AppiumServer();
	
	
	@BeforeClass
	public static void startAppiumServer() throws FileNotFoundException, IOException, ParseException {
		if(!ConfigurationHelper.getPlatform().toLowerCase().contains("webportal")) {
			try{
				server.startServer("127.0.0.1");
				ConfigurationHelper.setDriverUrl(server.serverUrl);
			}catch (Exception e) {
				System.out.println("Unable to Start the Server");
			}
		}
	}

    @AfterClass
    public static void generateReport() throws Exception {
        Reporter.loadXMLConfig(new File("src/test/resources/extent-config.xml"));
        Reporter.setSystemInfo("user", System.getProperty("user.name"));
        Reporter.setSystemInfo("os", "windows");
        Reporter.setTestRunnerOutput("Jpmc test runner output message");
        if(!ConfigurationHelper.getPlatform().toLowerCase().contains("webportal")) {
        	server.stopServer();
        }
    }
}
