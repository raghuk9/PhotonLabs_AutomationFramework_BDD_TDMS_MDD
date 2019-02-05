import org.junit.runner.RunWith;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumber;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;
import cucumber.api.CucumberOptions;
//import cucumber.api.junit.Cucumber;


@RunWith(ExtendedCucumber.class)
@ExtendedCucumberOptions(jsonReport = "target/cucumber.json",
        retryCount = 0,
        detailedReport = true,
        detailedAggregatedReport = true,
        overviewReport = true,
        //coverageReport = true,
        jsonUsageReport = "target/cucumber-usage.json",
        usageReport = true,
        toPDF = true,
        excludeCoverageTags = {"@flaky" },
        includeCoverageTags = {"@passed" },
        outputFolder = "target/")

//@RunWith(Cucumber.class)
@CucumberOptions
		(
		plugin = { "html:target/cucumber-html-report", "json:target/cucumber.json", "pretty:target/cucumber-pretty.txt",   "usage:target/cucumber-usage.json", "junit:target/cucumber-results.xml","com.cucumber.listener.ExtentCucumberFormatter:target/AutomationExtentReport.html" },
		glue = "com/photon/framework/StepDefinition", 
		features = "src/test/java/com/photon/framework/demo/feature", 
		tags = { "@Wynn_Demo"},
		
		monochrome = true
		)
public class CucumberRunnerTest  {                                                                                                                                    // @Walgreens_HomeScreen_Scenario  //@JPMC_Login_Scenario

}

