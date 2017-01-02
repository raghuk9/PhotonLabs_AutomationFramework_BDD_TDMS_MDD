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
		plugin = { "html:target/cucumber-html-report", "json:target/cucumber.json", "pretty:target/cucumber-pretty.txt",   "usage:target/cucumber-usage.json", "junit:target/cucumber-results.xml" },
		glue = "com/photon/framework/StepDefinition", 
		features = "src/test/java/com/photon/framework/Feature", 
		//format = { "html:target/cucumber-htmlreport", "json:target/cucumber-report.json" }, 
		tags = { "@JCP_Demo"},
		//tags = { "@JCP_Login"}, 
		monochrome = true
		)
public class CucumberRunnerTest  {                                                                                                                                    // @Walgreens_HomeScreen_Scenario  //@JPMC_Login_Scenario

}

