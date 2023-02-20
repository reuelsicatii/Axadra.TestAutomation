package runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.CucumberOptions.SnippetType;




@CucumberOptions(
		
		
		features = {"featurefiles/webAudit.feature"},
		dryRun = !true,
		snippets = SnippetType.CAMELCASE,
		monochrome = true,				
		glue = { "webApp.AppName"},
		//tags =  "@One",
		plugin =  {
				
				"pretty",
				//"html:reports/results" + new SimpleDateFormat("yyyyMMdd_hhmmssa").format(new Date()) + ".html",
				"html:reports/results.html",
				"json:reports/results.json",
				"junit:reports/results.xml"
		}
		

	)

public class runnerWebApp extends AbstractTestNGCucumberTests {	
	
	
	// Running Cucumber - Parallel Test
	// =========================================
	@Override
	@DataProvider (parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}

}
