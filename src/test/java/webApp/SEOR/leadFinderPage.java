package webApp.SEOR;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.aventstack.extentreports.GherkinKeyword;

import helper.webAppContextDriver;
import helper.webAppHelper;
import io.cucumber.java.en.Then;
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
					// .sendKeys("Home Office");
					.sendKeys(searchDetails.get("keyword"));
			Thread.sleep(2000);

			context.getWait().until(
					ExpectedConditions.elementToBeClickable(leadFinderSearchHistoryPageObject.keyword_suggestionList));
			context.getDriver().findElement(leadFinderSearchHistoryPageObject.keyword_suggestionList).click();
			Thread.sleep(2000);

			context.getDriver().findElement(leadFinderSearchHistoryPageObject.location_inputfield)
					// .sendKeys("Bangko, Merangin Regency, Jambi, Indonesia");
					.sendKeys(searchDetails.get("location"));
			Thread.sleep(2000);

			context.getWait().until(
					ExpectedConditions.elementToBeClickable(leadFinderSearchHistoryPageObject.location_suggestionList));
			context.getDriver().findElement(leadFinderSearchHistoryPageObject.location_suggestionList).click();
			Thread.sleep(2000);

			context.getWait()
					.until(ExpectedConditions.elementToBeClickable(leadFinderSearchHistoryPageObject.findLeads_button));
			context.getDriver().findElement(leadFinderSearchHistoryPageObject.findLeads_button).click();
			Thread.sleep(2000);

			// Waiting for Lead Finder to process request
			// ==================================================
			System.out.println("Waiting for Lead Finder to process request");

			int y = 0;
			while (true) {
				try {

					if (y >= 30) {

						// Extent Report
						context.getExtentTestScenario()
								.createNode(new GherkinKeyword("When"), "User request a process to generate Leads")
								.fail("FAILED: Processing lead generation for " + finalKeyword + "in "
										+ searchDetails.get("location") + "<br>"
										+ "Processing Lead Finder generation, waiting for " + y + " sec has exceeded");

						// exit the loop
						System.out.println("Exiting whileloop");
						break;

					}

					else if (context.getDriver().findElement(leadFinderSearchHistoryPageObject.processing_button)
							.isDisplayed()) {

						// Extent Report
						context.getExtentTestScenario()
								.createNode(new GherkinKeyword("When"), "User request a process to generate Leads")
								.pass("PASSED: Processing lead generation for " + finalKeyword + "in "
										+ searchDetails.get("location") + "<br>"
										+ "Processing Lead Finder generation, started in " + y + " sec");

						// exit the loop
						System.out.println("Exiting whileloop");
						break;

					}

				} catch (Exception e) {
					Thread.sleep(10000);
					y = y + 10;
					System.out.println("Processing Lead Finder generation, waiting for " + y + " sec");
				}

			}

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
										+ "Lead Finder generation, waiting for " + x + " sec");

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
					System.out.println("Lead Finder generation, waiting for " + x + " sec");

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

			System.out.println("TRY 01 -- User save all leads to new list");

			try {
				System.out.println("TRY 02 -- User save all leads to new list");

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

				System.out.println("CATCH 02 -- User save all leads to new list");

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

			System.out.println("CATCH 01 -- User save all leads to new list");

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

	@Then("User sees List Lead count is correct")
	public void userSeesListLeadCountIsCorrect() {

		try {

			System.out.println("click List link");
			// Click New List Name
			context.getWait()
					.until(ExpectedConditions.visibilityOfElementLocated(leadFinderSearchResultPageObject.list_link));
			context.getDriver().findElement(leadFinderSearchResultPageObject.list_link).click();
			Thread.sleep(2000);

			System.out.println("Wait for ListName link");
			context.getWait().until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(leadFinderSearchHistoryPageObject.listOfName_links));

			for (int i = 0; i < context.getDriver().findElements(leadFinderSearchHistoryPageObject.listOfName_links)
					.size(); i++) {

				// Capture ListName link
				context.getDriver().executeScript("arguments[0].scrollIntoView(true);",
						context.getDriver().findElement(leadFinderSearchResultPageObject.list_link));
				String listName = context.getDriver().findElements(leadFinderSearchHistoryPageObject.listOfName_links)
						.get(i).getText();

				System.out.println("click ListName link -- " + listName);
				// Click ListName link
				context.getWait().until(ExpectedConditions.elementToBeClickable(
						context.getDriver().findElements(leadFinderSearchHistoryPageObject.listOfName_links).get(i)));
				context.getDriver().findElements(leadFinderSearchHistoryPageObject.listOfName_links).get(i).click();
				Thread.sleep(2000);

				System.out.println("Click Table Row dropdown");
				// Click Table Row dropdown
				context.getWait().until(ExpectedConditions
						.visibilityOfElementLocated(leadFinderSearchResultPageObject.tableRow_dropdown));
				context.getDriver().executeScript("arguments[0].scrollIntoView(false);",
						context.getDriver().findElement(leadFinderSearchResultPageObject.tableRow_dropdown));
				context.getDriver().findElement(leadFinderSearchResultPageObject.tableRow_dropdown).click();
				Thread.sleep(2000);

				System.out.println("Select 100 from Table Row dropdown");
				// Select 100 from Table Row dropdown
				context.getWait().until(ExpectedConditions
						.visibilityOfElementLocated(leadFinderSearchResultPageObject.tableRowValue100_dropdown));
				context.getDriver().findElement(leadFinderSearchResultPageObject.tableRowValue100_dropdown).click();
				Thread.sleep(10000);

				System.out.println("ListName: " + listName);
				if (Pattern.matches("^.*\\b[0-9]\\b.$", listName)) {

					System.out.println("Wait for Lead List table");
					context.getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
							leadFinderSearchResultPageObject.leadGeneratorGroupLeads_tableRow));

					System.out.println("1 Digit Save to List");
					System.out.println(listName.substring(listName.length() - 2, listName.length() - 1) + " vs "
							+ Integer.toString(context.getDriver()
									.findElements(leadFinderSearchResultPageObject.leadGeneratorGroupLeads_tableRow)
									.size()));

					if (listName.substring(listName.length() - 2, listName.length() - 1)
							.contains(Integer.toString(context.getDriver()
									.findElements(leadFinderSearchResultPageObject.leadGeneratorGroupLeads_tableRow)
									.size()))) {

						System.out.println("ListName: " + listName + " PASSED");
						// Extent Report
						context.getExtentTestScenario()
								.createNode(new GherkinKeyword("When"), "User sees List Lead count is correct")
								.pass("PASSED: " + listName + " have the same count as the table");
					}

					else {

						System.out.println("ListName: " + listName + " FAILED");
						// Extent Report
						context.getExtentTestScenario()
								.createNode(new GherkinKeyword("When"), "User sees List Lead count is correct")
								.fail("FAILED: " + listName + " does not have the same count as the table." + "<br>"
										+ "Expected - ListName Count: "
										+ listName.substring(listName.length() - 2, listName.length() - 1) + "<br>"
										+ "Actual - TableRow Count: "
										+ context.getDriver().findElements(
												leadFinderSearchResultPageObject.leadGeneratorGroupLeads_tableRow)
												.size());
					}

				}

				else if (Pattern.matches("^.*\\b[0-9][0-9]\\b.$", listName)) {

					System.out.println("Wait for Lead List table");
					context.getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
							leadFinderSearchResultPageObject.leadGeneratorGroupLeads_tableRow));

					System.out.println("2 Digits Save to List");
					System.out.println(listName.substring(listName.length() - 3, listName.length() - 1) + " vs "
							+ Integer.toString(context.getDriver()
									.findElements(leadFinderSearchResultPageObject.leadGeneratorGroupLeads_tableRow)
									.size()));

					if (listName.substring(listName.length() - 3, listName.length() - 1)
							.contains(Integer.toString(context.getDriver()
									.findElements(leadFinderSearchResultPageObject.leadGeneratorGroupLeads_tableRow)
									.size()))) {

						System.out.println("ListName: " + listName + " PASSED");
						// Extent Report
						context.getExtentTestScenario()
								.createNode(new GherkinKeyword("When"), "User sees List Lead count is correct")
								.pass("PASSED: " + listName + " have the same count as the table");
					}

					else {

						System.out.println("ListName: " + listName + " FAILED");
						// Extent Report
						context.getExtentTestScenario()
								.createNode(new GherkinKeyword("When"), "User sees List Lead count is correct")
								.fail("FAILED: " + listName + " does not have the same count as the table. " + "<br>"
										+ "Expected - ListName Count: "
										+ listName.substring(listName.length() - 3, listName.length() - 1) + "<br>"
										+ "Actual - TableRow Count: "
										+ context.getDriver().findElements(
												leadFinderSearchResultPageObject.leadGeneratorGroupLeads_tableRow)
												.size());
					}

				}

				else if (Pattern.matches("^.*\\b[0-9][0-9][0-9]\\b.$", listName)) {

					System.out.println("3 Digits Save to List");

					System.out.println("Wait for Lead List table");
					context.getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
							leadFinderSearchResultPageObject.leadGeneratorGroupLeads_tableRow));

					Integer tableRowCount = 0;
					for (int j = 0; j < context.getDriver()
							.findElements(leadFinderSearchResultPageObject.tablepagination_links).size(); j++) {

						// Click Pagination
						System.out.println("Click Pagination " + context.getDriver()
								.findElements(leadFinderSearchResultPageObject.tablepagination_links).get(j).getText());
						context.getDriver().findElements(leadFinderSearchResultPageObject.tablepagination_links).get(j)
								.click();

						System.out.println("Wait for Lead List table after clicking pagination");
						Thread.sleep(10000);
						
						context.getDriver().executeScript("arguments[0].scrollIntoView(false);",
								context.getDriver().findElement(leadFinderSearchResultPageObject.tableRow_dropdown));

						System.out.println("Current Table Row Count: " + context.getDriver()
								.findElements(leadFinderSearchResultPageObject.leadGeneratorGroupLeads_tableRow)
								.size());

						tableRowCount = tableRowCount + context.getDriver()
								.findElements(leadFinderSearchResultPageObject.leadGeneratorGroupLeads_tableRow)
								.size();

						System.out.println("Total Table Row Count: " + tableRowCount);

					}

					System.out.println("---" + listName.substring(listName.length() - 4, listName.length() - 1)
							+ "--- vs ---" + Integer.toString(tableRowCount) + "---");

					if (listName.substring(listName.length() - 4, listName.length() - 1)
							.contains(Integer.toString(tableRowCount))) {

						System.out.println("ListName: " + listName + " PASSED");
						// Extent Report
						context.getExtentTestScenario()
								.createNode(new GherkinKeyword("When"), "User sees List Lead count is correct")
								.pass("PASSED: " + listName + " have the same count as the table");
					}

					else {

						System.out.println("ListName: " + listName + " FAILED");
						// Extent Report
						context.getExtentTestScenario()
								.createNode(new GherkinKeyword("When"), "User sees List Lead count is correct")
								.fail("FAILED: " + listName + " does not have the same count as the table. " + "<br>"
										+ "Expected - ListName Count: "
										+ listName.substring(listName.length() - 4, listName.length() - 1) + "<br>"
										+ "Actual - TableRow Count: "
										+ tableRowCount);
					}

				}

			}

		} catch (Exception e) {

			System.out.println("CATCH -- User sees List Lead count is correct");

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User sees List Lead count is correct")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
