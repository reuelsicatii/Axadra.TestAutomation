package webApp.SEOR;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.aventstack.extentreports.GherkinKeyword;

import helper.webAppContextDriver;
import helper.webAppHelper;
import io.cucumber.java.en.When;
import testAuto.Service.LeadFinderService;
import webApp.SEOR.PageObject.LeadFinderSearchHistoryPageObject;
import webApp.SEOR.PageObject.LeadFinderSearchResultPageObject;

public class leadFinderPage extends webAppHelper {

	// LeadFinder Service
	// ==========================================
	LeadFinderService leadFinderService = new LeadFinderService();

	// Page Object
	// ==========================================
	LeadFinderSearchHistoryPageObject leadFinderSearchHistoryPageObject = new LeadFinderSearchHistoryPageObject();
	LeadFinderSearchResultPageObject leadFinderSearchResultPageObject = new LeadFinderSearchResultPageObject();

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
	public void userGeneratesLeads() throws Throwable {

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
					// .sendKeys("Real Estate");
					.sendKeys(searchDetails.get("keyword"));
			Thread.sleep(2000);

			context.getWait().until(ExpectedConditions
					.presenceOfElementLocated(leadFinderSearchHistoryPageObject.keyword_suggestionList));
			context.getDriver().findElement(leadFinderSearchHistoryPageObject.keyword_suggestionList).click();
			Thread.sleep(2000);

			context.getDriver().findElement(leadFinderSearchHistoryPageObject.location_inputfield)
					// .sendKeys("Dubai - United Arab Emirates");
					.sendKeys(searchDetails.get("location"));
			Thread.sleep(2000);

			context.getWait().until(ExpectedConditions
					.presenceOfElementLocated(leadFinderSearchHistoryPageObject.location_suggestionList));
			context.getDriver().findElement(leadFinderSearchHistoryPageObject.location_suggestionList).click();
			Thread.sleep(2000);

			context.getWait()
					.until(ExpectedConditions.elementToBeClickable(leadFinderSearchHistoryPageObject.findLeads_button));
			context.getDriver().findElement(leadFinderSearchHistoryPageObject.findLeads_button).click();
			Thread.sleep(5000);

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

					else if (context.getDriver().findElement(leadFinderSearchHistoryPageObject.findLeads_button)
							.isDisplayed()) {

						Thread.sleep(20000);
						System.out.println("...hard reloading the page");
						context.getDriver().executeScript("location.reload(true);");
						Thread.sleep(10000);

						try {

							context.getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
									leadFinderSearchResultPageObject.leadGeneratorResult_tableRow));

							if (context.getDriver()
									.findElements(leadFinderSearchResultPageObject.leadGeneratorResult_tableRow)
									.size() >= 2) {

								// Extent Report
								context.getExtentTestScenario()
										.createNode(new GherkinKeyword("When"),
												"User generates a Leads for " + finalKeyword + "in "
														+ searchDetails.get("location"))
										.pass("PASSED: Redirected to RESULT PAGE");
							}

							else {

								// Extent Report
								context.getExtentTestScenario()
										.createNode(new GherkinKeyword("When"), "User generates a Leads for "
												+ finalKeyword + "in " + searchDetails.get("location"))
										.fail("FAILED: No Leads Found");
							}

						} catch (Exception e) {

							context.getWait().until(ExpectedConditions.visibilityOfElementLocated(
									leadFinderSearchResultPageObject.searchLeadStatus_span));
							if (context.getDriver().findElement(leadFinderSearchResultPageObject.searchLeadStatus_span)
									.getText().contains("EXPIRED")) {

								// Extent Report
								context.getExtentTestScenario()
										.createNode(new GherkinKeyword("When"),
												"User generates a Leads for " + finalKeyword + "in "
														+ searchDetails.get("location"))
										.fail("FAILED: Status is EXPIRED");
							}

							else if (context.getDriver()
									.findElement(leadFinderSearchResultPageObject.searchLeadStatus_span).getText()
									.contains("COMPLETED")) {

								// Extent Report
								context.getExtentTestScenario()
										.createNode(new GherkinKeyword("When"),
												"User generates a Leads for " + finalKeyword + "in "
														+ searchDetails.get("location"))
										.pass("PASSED: Status is COMPLETED however not redirected to RESULT PAGE");

								context.getDriver().findElement(leadFinderSearchResultPageObject.searchLeadResult_link)
										.click();

							}
						}

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

	@When("User save all leads to new list")
	public void userSaveAllLeadsToNewList() {

		String newListName = new SimpleDateFormat("yyMMdd_HHmmss").format(new Date()).toString();

		try {

			try {
				if (context.getDriver().findElements(leadFinderSearchResultPageObject.leadGeneratorResult_tableRow)
						.size() <= 1) {

					// Extent Report
					context.getExtentTestScenario().createNode(new GherkinKeyword("When"), "User generates a Leads")
							.fail("FAILED: No Leads Found");
				}

				else if (context.getDriver().findElements(leadFinderSearchResultPageObject.leadGeneratorResult_tableRow)
						.size() >= 2) {

					Integer allLeadsCount = context.getDriver()
							.findElements(leadFinderSearchResultPageObject.leadGeneratorResult_tableRow).size();

					// click SaveAll button
					context.getWait().until(ExpectedConditions
							.visibilityOfElementLocated(leadFinderSearchResultPageObject.saveAll_button));
					context.getDriver().findElement(leadFinderSearchResultPageObject.saveAll_button).click();
					Thread.sleep(5000);

					// click SaveAll dropdown
					context.getWait().until(ExpectedConditions
							.visibilityOfElementLocated(leadFinderSearchResultPageObject.saveAll_dropdown));
					context.getDriver().findElement(leadFinderSearchResultPageObject.saveAll_dropdown).click();
					Thread.sleep(5000);

					// click Add New List Option
					context.getWait().until(ExpectedConditions
							.visibilityOfElementLocated(leadFinderSearchResultPageObject.addNewList_option));
					context.getDriver().findElement(leadFinderSearchResultPageObject.addNewList_option).click();
					Thread.sleep(5000);

					// Input New List Name
					context.getWait().until(ExpectedConditions
							.visibilityOfElementLocated(leadFinderSearchResultPageObject.addNewListName_textfield));
					context.getDriver().findElement(leadFinderSearchResultPageObject.addNewListName_textfield)
							.sendKeys(newListName);
					Thread.sleep(5000);

					// click Add New List button
					context.getWait().until(ExpectedConditions
							.visibilityOfElementLocated(leadFinderSearchResultPageObject.addNewList_button));
					context.getDriver().findElement(leadFinderSearchResultPageObject.addNewList_button).click();
					Thread.sleep(5000);

					// click List tab
					context.getWait().until(
							ExpectedConditions.visibilityOfElementLocated(leadFinderSearchResultPageObject.list_link));
					context.getDriver().findElement(leadFinderSearchResultPageObject.list_link).click();
					Thread.sleep(5000);

					context.getWait().until(ExpectedConditions.visibilityOfElementLocated(
							leadFinderSearchResultPageObject.listName_link(newListName, allLeadsCount.toString())));

					if (context.getDriver().findElement(
							leadFinderSearchResultPageObject.listName_link(newListName, allLeadsCount.toString()))
							.isDisplayed()) {

						// Extent Report
						context.getExtentTestScenario()
								.createNode(new GherkinKeyword("When"), "User save all leads to new list")
								.pass("PASSED: Leads are save");
					}

					else {
						// Extent Report
						context.getExtentTestScenario()
								.createNode(new GherkinKeyword("When"), "User save all leads to new list")
								.fail("FAILED: Leads are NOT save");
					}

				}

			} catch (Exception e) {
				if (context.getDriver().findElement(leadFinderSearchResultPageObject.searchLeadStatus_span).getText()
						.contains("EXPIRED")) {

					// Extent Report
					context.getExtentTestScenario()
							.createNode(new GherkinKeyword("When"), "User save all leads to new list")
							.fail("FAILED: Status is EXPIRED");
				}

				else {
					// Extent Report
					context.getExtentTestScenario()
							.createNode(new GherkinKeyword("When"), "User save all leads to new list").fail("FAILED");
				}
			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User save all leads to new list")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
