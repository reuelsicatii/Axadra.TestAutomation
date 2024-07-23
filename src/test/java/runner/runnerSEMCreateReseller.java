package runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.CucumberOptions.SnippetType;




@CucumberOptions(
		
			
		features = {"featurefiles/webApp/semCreateReseller.feature"},
		dryRun = !true,
		snippets = SnippetType.CAMELCASE,
		monochrome = true,				
		glue = { "webApp.SEM"},
		//tags =  "@PageAndLazyCampaignGBP or @PageAndLazyCampaignOverview",
		//tags =  "@PageAndLazyWebAuditReport",
		tags =  "@SEM_CreateReseller",
		plugin =  {				
				"pretty",
				"html:reports/results.html",
				"json:reports/results.json",
				"junit:reports/results.xml"
		}
		

	)

public class runnerSEMCreateReseller extends AbstractTestNGCucumberTests {	
	
	
	// Running Cucumber - Parallel Test
	// =========================================
	@Override
	@DataProvider (parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}

}
