package runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.CucumberOptions.SnippetType;




@CucumberOptions(
		
		
		features = {"featurefiles/webAPi/reqresDummyUsers.feature"},
		dryRun = !true,
		snippets = SnippetType.CAMELCASE,
		monochrome = true,				
		glue = { "webApi.ApiName"},
		tags =  "@Reqres_Dummy_Users",
		plugin =  {
				
				"pretty",				
				"html:reports/results.html",
				"json:reports/results.json",
				"junit:reports/results.xml"
		}
		

	)

public class runnerReqresDummyUsers extends AbstractTestNGCucumberTests {	
	
	
	// Running Cucumber - Parallel Test
	// =========================================
	@Override
	@DataProvider (parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}

}
