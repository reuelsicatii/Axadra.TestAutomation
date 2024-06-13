package runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.CucumberOptions.SnippetType;




@CucumberOptions(
		
			
		features = {"featurefiles/webApp/seorPageLoad.feature"},
		dryRun = !true,
		snippets = SnippetType.CAMELCASE,
		monochrome = true,				
		glue = { "webApp.SEOR"},
		tags =  "@SEOR_Page_And_Lazy_Load",
		plugin =  {				
				"pretty",
				"html:reports/results.html",
				"json:reports/results.json",
				"junit:reports/results.xml"
		}
		

	)

public class runnerSEORPageLoad extends AbstractTestNGCucumberTests {	
	
	
	// Running Cucumber - Sequential Test
	// =========================================
	@Override
	@DataProvider (parallel = false)
	public Object[][] scenarios() {
		return super.scenarios();
	}

}
