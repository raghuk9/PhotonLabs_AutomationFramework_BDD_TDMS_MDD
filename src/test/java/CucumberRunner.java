import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions
		(
		glue = "com/photon/framework/StepDefinition", 
		features = "src/test/java/com/photon/framework/Feature", 
		format = { "html:target/cucumber-htmlreport", "json:target/cucumber-report.json" }, 
		tags = { "@Smoke_HomeScreen_Scenario-1"}, 
		monochrome = true
		)
public class CucumberRunner extends AbstractTestNGCucumberTests {                                                                                                                                    // @Walgreens_HomeScreen_Scenario  //@JPMC_Login_Scenario

}

