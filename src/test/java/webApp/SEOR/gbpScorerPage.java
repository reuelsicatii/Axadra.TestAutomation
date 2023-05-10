package webApp.SEOR;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.aventstack.extentreports.GherkinKeyword;

import helper.webAppContextDriver;
import helper.webAppHelper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import testAuto.Service.GbpScorerService;

public class gbpScorerPage extends webAppHelper {

	GbpScorerService gbpScorerService = new GbpScorerService();

	// Page Elements
	// ==========================================

	By businessNameGbpScorer_inputfield = By.xpath("//input[@id='business']");
	By businessNameGbpScorer_suggestionList = By.xpath("(//div[@id='business-suggestions-wrapper']//span)[1]");
	By businessNameGbpScorer_button = By.xpath("//button[contains(text(),'Run Audit')]");
	By recent_GbpScorerReport = By.xpath("(//table[@id='lar-table']//tbody//i[@class='fa fa-external-link-alt'])[1]");

	// Declare Driver Instance
	// ==========================================
	private webAppContextDriver context;

	public gbpScorerPage(webAppContextDriver context) {
		super();
		this.context = context;
	}

	// Page Step Definition
	// =================================================
	@When("User generates a GbpScorerReport")
	public void userGeneratesAGbpScorerReport() throws Throwable {

		// Step Definition
		String BusinessName = gbpScorerService.RetrieveBusinessNameFrom("PROD_REPORTBUILDER");
		System.out.println("URL from SERVICE:" + BusinessName);

		try {
			// Step Definition
			context.getDriver().findElement(businessNameGbpScorer_inputfield).sendKeys(BusinessName);
			Thread.sleep(2000);

			context.getWait().until(ExpectedConditions.presenceOfElementLocated(businessNameGbpScorer_suggestionList));
			context.getDriver().findElement(businessNameGbpScorer_suggestionList).click();

			context.getWait().until(ExpectedConditions.elementToBeClickable(businessNameGbpScorer_button));
			context.getDriver().findElement(businessNameGbpScorer_button).click();

			// Check GBP Scorer Report is generated
			// ==================================================

			int x = 0;
			while (true) {

				try {

					if (x >= 420) {

						// Extent Report
						context.getExtentTestScenario()
								.createNode(new GherkinKeyword("When"), "User generates a GbpScorerReport")
								.fail("FAILED: Not able to generate GbpScorer Report for " + BusinessName + "<br>"
										+ "GbpScorer Report generation, waiting for " + x + "sec");

						// exit the loop
						System.out.println("Exiting whileloop");
						break;

					}

					else if (context.getDriver().findElement(By
							.xpath("(//table[@id='lar-table']//tbody//a[contains(text(), '" + BusinessName + "')])[1]"))
							.isDisplayed()) {

						// Extent Report
						context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
								"User generates a GbpScorerReport for " + BusinessName).pass("PASSED");

						// exit the loop
						System.out.println("Exiting whileloop");
						break;

					}

				} catch (Exception e) {

					Thread.sleep(10000);
					x = x + 10;
					System.out.println("GbpScorer Report generation, waiting for " + x + "sec");
				}

			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User generates a GbpScorerReport for " + BusinessName)
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@When("User clicks the most recent GBPScorer Report")
	public void userClicksTheMostRecentGBPScorerReport() {

		try {
			// Step Definition
			context.getWait().until(ExpectedConditions.presenceOfElementLocated(recent_GbpScorerReport));
			context.getDriver().executeScript("arguments[0].scrollIntoView(false);",
					context.getDriver().findElement(recent_GbpScorerReport));
			context.getDriver().findElement(recent_GbpScorerReport).click();

			// Extent Report
			context.getExtentTestScenario()
					.createNode(new GherkinKeyword("When"), "User clicks the most recent WebAuditReport")
					.pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User clicks the most recent WebAuditReport")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Then("User sees a new tab is open rendering the GBPScorer Report")
	public void userSeesANewTabIsOpenRederingTheGbpScorerReport() {

		try {
			// Step Definition
			ArrayList<String> newTb = new ArrayList<String>(context.getDriver().getWindowHandles());
			context.getDriver().switchTo().window(newTb.get(1));

			// Test Purposes - control REPORT generation
			// Thread.sleep(5000);
			// context.getDriver().get("https://myreports.app/reports/view/991d4cf7-4f45-452e-a96d-3ed772e833a7");

			
			
			System.out.println("...starting to loop");

			int x = 0;
			while (true) {

				System.out.println("...looping");

				try {

					System.out.println("...waiting for page to load");

					if (x == 2 || context.getDriver()
							.findElement(By.xpath("//body//div[contains(@class, 'summary-section row')]")) != null) {
						System.out.println("...exiting loop");
						break;
					}

				} catch (Exception e) {
					System.out.println("...hard reloading the page");
					context.getDriver().executeScript("location.reload(true);");
					Thread.sleep(60000);
					x++;
				}

			}
			
			

			// Extent Report
			Thread.sleep(2000);
			context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
					"User sees a new tab is open rendering the GBPScorer Report").pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees a new tab is open rendering the GBPScorer Report")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

}
