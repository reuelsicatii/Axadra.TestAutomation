package runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.CucumberOptions.SnippetType;




@CucumberOptions(
		
		
		features = {"featurefiles/webApi/semLinksBuilt.feature"},
		dryRun = !true,
		snippets = SnippetType.CAMELCASE,
		monochrome = true,				
		glue = { "webApi.SEM"},
		tags =  "@LinksBuilt",
		plugin =  {
				
				"pretty",				
				"html:reports/results.html",
				"json:reports/results.json",
				"junit:reports/results.xml"
		}
		

	)

public class runnerSEMLinksBuilt extends AbstractTestNGCucumberTests {	
	
	
	// Running Cucumber - Parallel Test
	// =========================================
	@Override
	@DataProvider (parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}

}
