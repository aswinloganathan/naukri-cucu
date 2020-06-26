package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features ="src/main/java/feature/naukri.feature", 
					glue = "steps", 
					dryRun = false,
					monochrome = true
					//plugin= {"html:report/WebReport", "json:report/jsonreport.json"},
					//tags = {"@featureFile"}
				)
public class NaukriRunner extends AbstractTestNGCucumberTests{

}
