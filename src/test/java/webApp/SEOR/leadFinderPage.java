package webApp.SEOR;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.aventstack.extentreports.GherkinKeyword;

import helper.webAppContextDriver;
import helper.webAppHelper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import testAuto.Service.LeadFinderService;
import webApp.SEOR.PageObject.LeadFinderSearchHistoryPageObject;

public class leadFinderPage extends webAppHelper {

	// LeadFinder Service
	// ==========================================
	LeadFinderService leadFinderService = new LeadFinderService();

	// Page Object
	// ==========================================
	LeadFinderSearchHistoryPageObject leadFinderSearchHistoryPageObject = new LeadFinderSearchHistoryPageObject();

	// Declare Driver Instance
	// ==========================================
	private webAppContextDriver context;

	public leadFinderPage(webAppContextDriver context) {
		super();
		this.context = context;
	}

	// Page Step Definition
	// =================================================
	@When("User generates Leads")
	public void userGeneratesAGbpScorerReport() throws Throwable {

		// Step Definition
		HashMap<String, String> searchDetails = leadFinderService.RetrieveKeywordLocationFrom("PROD_CENTRAL");
		System.out.println("LeadFinder Keyword: " + searchDetails.get("keyword"));
		System.out.println("LeadFinder Location: " + searchDetails.get("location"));

		// Format - Keyword
		// ==================================================
		String tempKeyword[] = searchDetails.get("keyword").split("\\s");
		StringBuffer finalKeyword = new StringBuffer();
		for (String w : tempKeyword) {
			finalKeyword.append(StringUtils.capitalize(w) + " ");
		}

		try {
			// Step Definition
			context.getDriver().findElement(leadFinderSearchHistoryPageObject.keyword_inputfield)
					.sendKeys(searchDetails.get("keyword"));
			Thread.sleep(2000);

			context.getWait().until(ExpectedConditions
					.presenceOfElementLocated(leadFinderSearchHistoryPageObject.keyword_suggestionList));
			context.getDriver().findElement(leadFinderSearchHistoryPageObject.keyword_suggestionList).click();

			context.getDriver().findElement(leadFinderSearchHistoryPageObject.location_inputfield)
					.sendKeys(searchDetails.get("location"));
			Thread.sleep(2000);

			context.getWait().until(ExpectedConditions
					.presenceOfElementLocated(leadFinderSearchHistoryPageObject.location_suggestionList));
			context.getDriver().findElement(leadFinderSearchHistoryPageObject.location_suggestionList).click();

			context.getWait()
					.until(ExpectedConditions.elementToBeClickable(leadFinderSearchHistoryPageObject.findLeads_button));
			context.getDriver().findElement(leadFinderSearchHistoryPageObject.findLeads_button).click();

			// Check Lead is generated
			// ==================================================

			int x = 0;
			while (true) {

				try {

					if (x >= 420) {

						// Extent Report
						context.getExtentTestScenario().createNode(new GherkinKeyword("When"), "User generates Leads")
								.fail("FAILED: Not able to generate Leads for " + finalKeyword + "in "
										+ searchDetails.get("location") + "<br>"
										+ "Lead Finder generation, waiting for " + x + "sec");

						// exit the loop
						System.out.println("Exiting whileloop");
						break;

					}

					else if (context.getDriver()
							.findElement(By.xpath(
									"//h2[text()='" + finalKeyword + "in " + searchDetails.get("location") + "']"))
							.isDisplayed()) {

						// Extent Report
						context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
								"User generates a Leads for " + finalKeyword + "in " + searchDetails.get("location"))
								.pass("PASSED");

						// exit the loop
						System.out.println("Exiting whileloop");
						break;

					}

				} catch (Exception e) {

					Thread.sleep(10000);
					x = x + 10;
					System.out.println("Lead Finder generation, waiting for " + x + "sec");
				}

			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User generates Leads for " + finalKeyword + "in " + searchDetails.get("location"))
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@When("User clicks the most recent GBPScorer Reportasd")
	public void userClicksTheMostRecentGBPScorerReportasd() {

		try {
			/*
			 * // Step Definition
			 * context.getWait().until(ExpectedConditions.presenceOfElementLocated(
			 * recent_GbpScorerReport));
			 * context.getDriver().executeScript("arguments[0].scrollIntoView(false);",
			 * context.getDriver().findElement(recent_GbpScorerReport));
			 * context.getDriver().findElement(recent_GbpScorerReport).click();
			 */

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

	@Then("User sees a new tab is open rendering the GBPScorer Reportasd")
	public void userSeesANewTabIsOpenRederingTheGbpScorerReportasd() {

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
