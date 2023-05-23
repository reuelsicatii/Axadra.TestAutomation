package webApp.SEOR;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.aventstack.extentreports.GherkinKeyword;
import com.jayway.jsonpath.JsonPath;

import helper.webAppContextDriver;
import helper.webAppHelper;
import io.cucumber.java.en.Then;
import webApp.SEOR.PageObject.GbpScorerReportPageObject;

public class gbpScorerReportPage extends webAppHelper {

	// Declare Driver Instance
	// ==========================================
	private webAppContextDriver context;

	public gbpScorerReportPage(webAppContextDriver context) {
		super();
		this.context = context;
	}

	// Page Object
	// ==========================================
	GbpScorerReportPageObject gbpScorerReportPageObject = new GbpScorerReportPageObject();

	// Page Step Definition
	// =================================================
	@Then("User scroll to Summary Chart Section")
	public void userScrollToSummaryChartSection() {

		try {

			// document zooming
			// context.getDriver().executeScript("document.body.style.zoom = '0.75'");

			context.getWait()
					.until(ExpectedConditions.presenceOfElementLocated(gbpScorerReportPageObject.summaryChartSection));

			// scroll to pixel
			// context.getDriver().executeScript("window.scrollBy(0,2000)");

			// scroll to element
			context.getDriver().executeScript("arguments[0].scrollIntoView(false);",
					context.getDriver().findElement(gbpScorerReportPageObject.summaryChartSection));

			// Extent Report
			context.getExtentTestScenario()
					.createNode(new GherkinKeyword("When"), "User scroll to Summary Chart Section").pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User scroll to Summary Chart Section")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Then("User sees the Summary Chart > Profile Score SubSection is correct")
	public void userSeesTheSummaryChartProfileScoreSubSectionIsCorrect() throws IOException {

		try {
			// Step Definition
			List<WebElement> verdicts = context.getDriver()
					.findElements(gbpScorerReportPageObject.subSectionElementFinder("Business Profile Score", "All", "verdicts"));

			Integer profileScore = 0;
			for (int i = 0; i < verdicts.size(); i++) {

				if (verdicts.get(i).getText().equals("Looking Good")) {
					profileScore = profileScore + 10;
				} else {
					profileScore = profileScore + 0;
				}
				
				//System.out.println(profileScore);
			}
			
			if (context.getDriver().findElement(gbpScorerReportPageObject.summaryChartSection_profileScore).getText()
					.contains(profileScore.toString())
					&& context.getDriver()
							.findElement(gbpScorerReportPageObject.subSectionElementFinder("Business Profile Score", "All", "rating")).getText()
							.contains(profileScore.toString())) {

				// Extent Report
				context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
						"User sees the Summary Chart > Profile Score SubSection is correct").pass("PASSED"
								+ "<br>" + "Expected - Computation: "
								+ profileScore.toString() + "%" + "<br>"
								+ "Actual - Computation - Summary Chart : "
								+ context.getDriver().findElement(
										gbpScorerReportPageObject.summaryChartSection_profileScore).getText()
								+ "<br>" + "Actual - Computation - Google Profile Score : "
								+ context.getDriver().findElement(
										gbpScorerReportPageObject.subSectionElementFinder("Business Profile Score", "All", "rating"))
										.getText());
			}

			else {
				context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
						"User sees the Summary Chart > Profile Score SubSection is correct").fail(
								"FAILED" + "<br>" + "Expected - Computation: "
										+ profileScore.toString() + "%" + "<br>"
										+ "Actual - Computation - Summary Chart : "
										+ context.getDriver().findElement(
												gbpScorerReportPageObject.summaryChartSection_profileScore).getText()
										+ "<br>" + "Actual - Computation - Google Profile Score : "
										+ context.getDriver().findElement(
												gbpScorerReportPageObject.subSectionElementFinder("Business Profile Score", "All", "rating"))
												.getText()

				);
			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Summary Chart > Profile Score SubSection is correct")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User sees the Summary Chart > Post Score SubSection is correct")
	public void userSeesTheSummaryChartPostScoreSubSectionIsCorrect() throws IOException {

		try {
			// Step Definition
			List<WebElement> verdicts = context.getDriver()
					.findElements(gbpScorerReportPageObject.subSectionElementFinder("Google Post Score", "All", "verdicts"));

			Integer postScore = 0;
			for (int i = 0; i < verdicts.size(); i++) {

				if (i == 0 && verdicts.get(i).getText().equals("Looking Good")) {
					postScore = postScore + 50;
				} 
				
				else if (i == 0 && verdicts.get(i).getText().equals("For Improvements")) {
					postScore = postScore + 25;
				} 
				
				else if (i == 1 && verdicts.get(i).getText().equals("Looking Good")) {
					postScore = postScore + 30;
				} 
				
				else if (i == 1 && verdicts.get(i).getText().equals("For Improvements")) {
					postScore = postScore + 15;
				} 
				
				else if (i == 2 && verdicts.get(i).getText().equals("Looking Good")) {
					postScore = postScore + 20;
				}				
				
				else {
					postScore = postScore + 0;
				}
				
				//System.out.println(profileScore);
			}
			
			if (context.getDriver().findElement(gbpScorerReportPageObject.summaryChartSection_postScore).getText()
					.contains(postScore.toString())
					&& context.getDriver()
							.findElement(gbpScorerReportPageObject.subSectionElementFinder("Google Post Score", "All", "rating")).getText()
							.contains(postScore.toString())) {

				// Extent Report
				context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
						"User sees the Summary Chart > Post Score SubSection is correct").pass("PASSED"
								+ "<br>" + "Expected - Computation: "
								+ postScore.toString() + "%" + "<br>"
								+ "Actual - Computation - Summary Chart : "
								+ context.getDriver().findElement(
										gbpScorerReportPageObject.summaryChartSection_postScore).getText()
								+ "<br>" + "Actual - Computation - Google Post Score : "
								+ context.getDriver().findElement(
										gbpScorerReportPageObject.subSectionElementFinder("Google Post Score", "All", "rating"))
										.getText());
			}

			else {
				context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
						"User sees the Summary Chart > Post Score SubSection is correct").fail(
								"FAILED" 
								+ "<br>" + "Expected - Computation: "
								+ postScore.toString() + "%" + "<br>"
								+ "Actual - Computation - Summary Chart : "
								+ context.getDriver().findElement(
										gbpScorerReportPageObject.summaryChartSection_postScore).getText()
								+ "<br>" + "Actual - Computation - Google Post Score : "
								+ context.getDriver().findElement(
										gbpScorerReportPageObject.subSectionElementFinder("Google Post Score", "All", "rating"))
										.getText());
			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Summary Chart > Post Score SubSection is correct")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User sees the Summary Chart > Review Score SubSection is correct")
	public void userSeesTheSummaryChartReviewScoreSubSectionIsCorrect() throws IOException {

		try {
			// Step Definition
			List<WebElement> verdicts = context.getDriver()
					.findElements(gbpScorerReportPageObject.subSectionElementFinder("Google Review Score", "All", "verdicts"));

			Integer reviewScore = 0;
			for (int i = 0; i < verdicts.size(); i++) {

				if (i == 0 && verdicts.get(i).getText().equals("Looking Good")) {
					reviewScore = reviewScore + 50;
				} 
				
				else if (i == 0 && verdicts.get(i).getText().equals("For Improvements")) {
					reviewScore = reviewScore + 25;
				} 
				
				else if (i == 1 && verdicts.get(i).getText().equals("Looking Good")) {
					reviewScore = reviewScore + 30;
				} 
				
				else if (i == 1 && verdicts.get(i).getText().equals("For Improvements")) {
					reviewScore = reviewScore + 15;
				} 
				
				else if (i == 2 && verdicts.get(i).getText().equals("Looking Good")) {
					reviewScore = reviewScore + 20;
				}		
				
				else if (i == 2 && verdicts.get(i).getText().equals("For Improvements")) {
					reviewScore = reviewScore + 10;
				}	
				
				else {
					reviewScore = reviewScore + 0;
				}
				
				//System.out.println(profileScore);
			}
			
			if (context.getDriver().findElement(gbpScorerReportPageObject.summaryChartSection_reviewScore).getText()
					.contains(reviewScore.toString())
					&& context.getDriver()
							.findElement(gbpScorerReportPageObject.subSectionElementFinder("Google Review Score", "All", "rating")).getText()
							.contains(reviewScore.toString())) {

				// Extent Report
				context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
						"User sees the Summary Chart > Review Score SubSection is correct").pass("PASSED"
								+ "<br>" + "Expected - Computation: "
								+ reviewScore.toString() + "%" + "<br>"
								+ "Actual - Computation - Summary Chart : "
								+ context.getDriver().findElement(
										gbpScorerReportPageObject.summaryChartSection_reviewScore).getText()
								+ "<br>" + "Actual - Computation - Google Post Score : "
								+ context.getDriver().findElement(
										gbpScorerReportPageObject.subSectionElementFinder("Google Review Score", "All", "rating"))
										.getText());
			}

			else {
				context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
						"User sees the Summary Chart > Review Score SubSection is correct").fail(
								"FAILED" 
								+ "<br>" + "Expected - Computation: "
								+ reviewScore.toString() + "%" + "<br>"
								+ "Actual - Computation - Summary Chart : "
								+ context.getDriver().findElement(
										gbpScorerReportPageObject.summaryChartSection_reviewScore).getText()
								+ "<br>" + "Actual - Computation - Google Post Score : "
								+ context.getDriver().findElement(
										gbpScorerReportPageObject.subSectionElementFinder("Google Review Score", "All", "rating"))
										.getText());
			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Summary Chart > Review Score SubSection is correct")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}
	
	@Then("User scroll to {string} Section")
	public void userScrollToSection(String SectioName) {
		try {

			// document zooming
			// context.getDriver().executeScript("document.body.style.zoom = '0.75'");

			context.getWait().until(ExpectedConditions
					.presenceOfElementLocated(gbpScorerReportPageObject.sectionElementFinder(SectioName)));

			// scroll to pixel
			// context.getDriver().executeScript("window.scrollBy(0,2000)");

			// scroll to element
			context.getDriver().executeScript("arguments[0].scrollIntoView(false);",
					context.getDriver().findElement(gbpScorerReportPageObject.sectionElementFinder(SectioName)));

			// Extent Report
			context.getExtentTestScenario()
					.createNode(new GherkinKeyword("When"), "User scroll to Similar Listing Section").pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User scroll to Similar Listing Section")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Then("User sees the Business Profile Score > Phone Number Found SubSection is correct")
	public void userSeesTheBusinessProfilePhoneNumberFoundSubSectionIsCorrect() throws IOException {

		try {
			// Step Definition
			String expected_verbiage = JsonPath.read(getGbpScorerReportVerbiages(),
					"$.['Business Profile Score']['Phone Number Found']['"
							+ context.getDriver()
									.findElement(gbpScorerReportPageObject.subSectionElementFinder(
											"Business Profile Score", "Phone Number Found", "verdict"))
									.getText()
							+ "']");

			if (context.getDriver().findElement(gbpScorerReportPageObject
					.subSectionElementFinder("Business Profile Score", "Phone Number Found", "verbiage")).getText()
					.contains(expected_verbiage)) {

				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Business Profile Score > Phone Number Found SubSection is correct  ")
						.pass("PASSED");
			}

			else {
				context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
						"User sees the Business Profile Score > Phone Number Found SubSection is correct  ").fail(
								"FAILED" + "<br>" + "Expected - verbiage: " + expected_verbiage + "<br>"
										+ "Actual - verbiage: "
										+ context.getDriver()
												.findElement(gbpScorerReportPageObject.subSectionElementFinder(
														"Business Profile Score", "Phone Number Found", "verbiage"))
												.getText());
			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Business Profile Score > Phone Number Found SubSection is correct  ")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User sees the Business Profile Score > Hours in Operation SubSection is correct")
	public void userSeesTheBusinessProfileScoreHoursInOperationSubSectionIsCorrect() throws IOException {

		try {
			// Step Definition
			String expected_verbiage = JsonPath.read(getGbpScorerReportVerbiages(),
					"$.['Business Profile Score']['Hours in Operation']['"
							+ context.getDriver()
									.findElement(gbpScorerReportPageObject.subSectionElementFinder(
											"Business Profile Score", "Hours in Operation", "verdict"))
									.getText()
							+ "']");

			if (context.getDriver().findElement(gbpScorerReportPageObject
					.subSectionElementFinder("Business Profile Score", "Hours in Operation", "verbiage")).getText()
					.contains(expected_verbiage)) {

				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Business Profile Score > Hours in Operation SubSection is correct  ")
						.pass("PASSED");
			}

			else {
				context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
						"User sees the Business Profile Score > Hours in Operation SubSection is correct  ").fail(
								"FAILED" + "<br>" + "Expected - verbiage: " + expected_verbiage + "<br>"
										+ "Actual - verbiage: "
										+ context.getDriver()
												.findElement(gbpScorerReportPageObject.subSectionElementFinder(
														"Business Profile Score", "Hours in Operation", "verbiage"))
												.getText());
			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Business Profile Score > Hours in Operation SubSection is correct  ")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User sees the Business Profile Score > Business Description SubSection is correct")
	public void userSeesTheBusinessProfileScoreBusinessDescriptionSubSectionIsCorrect() throws IOException {

		try {
			// Step Definition
			String expected_verbiage = JsonPath.read(getGbpScorerReportVerbiages(),
					"$.['Business Profile Score']['Business Description']['"
							+ context.getDriver()
									.findElement(gbpScorerReportPageObject.subSectionElementFinder(
											"Business Profile Score", "Business Description", "verdict"))
									.getText()
							+ "']");

			if (context.getDriver().findElement(gbpScorerReportPageObject
					.subSectionElementFinder("Business Profile Score", "Business Description", "verbiage")).getText()
					.contains(expected_verbiage)) {

				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Business Profile Score > Business Description SubSection is correct  ")
						.pass("PASSED");
			}

			else {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Business Profile Score > Business Description SubSection is correct  ")
						.fail("FAILED" + "<br>" + "Expected - verbiage: " + expected_verbiage + "<br>"
								+ "Actual - verbiage: "
								+ context.getDriver()
										.findElement(gbpScorerReportPageObject.subSectionElementFinder(
												"Business Profile Score", "Business Description", "verbiage"))
										.getText());
			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Business Profile Score > Business Description SubSection is correct  ")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User sees the Business Profile Score > Business Category SubSection is correct")
	public void userSeesTheBusinessProfileScoreBusinessCategorySubSectionIsCorrect() throws IOException {

		try {
			// Step Definition
			String expected_verbiage = JsonPath.read(getGbpScorerReportVerbiages(),
					"$.['Business Profile Score']['Business Category']['"
							+ context.getDriver()
									.findElement(gbpScorerReportPageObject.subSectionElementFinder(
											"Business Profile Score", "Business Category", "verdict"))
									.getText()
							+ "']");

			if (context.getDriver().findElement(gbpScorerReportPageObject
					.subSectionElementFinder("Business Profile Score", "Business Category", "verbiage")).getText()
					.contains(expected_verbiage)) {

				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Business Profile Score > Business Category SubSection is correct  ")
						.pass("PASSED");
			}

			else {
				context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
						"User sees the Business Profile Score > Business Category SubSection is correct  ").fail(
								"FAILED" + "<br>" + "Expected - verbiage: " + expected_verbiage + "<br>"
										+ "Actual - verbiage: "
										+ context.getDriver()
												.findElement(gbpScorerReportPageObject.subSectionElementFinder(
														"Business Profile Score", "Business Category", "verbiage"))
												.getText());
			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Business Profile Score > Business Category SubSection is correct  ")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User sees the Business Profile Score > Services Offered SubSection is correct")
	public void userSeesTheBusinessProfileScoreServicesOfferedSubSectionIsCorrect() throws IOException {

		try {
			// Step Definition
			String expected_verbiage = JsonPath
					.read(getGbpScorerReportVerbiages(),
							"$.['Business Profile Score']['Services Offered']['"
									+ context.getDriver()
											.findElement(gbpScorerReportPageObject.subSectionElementFinder(
													"Business Profile Score", "Services Offered", "verdict"))
											.getText()
									+ "']");

			if (context.getDriver().findElement(gbpScorerReportPageObject
					.subSectionElementFinder("Business Profile Score", "Services Offered", "verbiage")).getText()
					.contains(expected_verbiage)) {

				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Business Profile Score > Services Offered SubSection is correct  ")
						.pass("PASSED");
			}

			else {
				context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
						"User sees the Business Profile Score > Services Offered SubSection is correct  ").fail(
								"FAILED" + "<br>" + "Expected - verbiage: " + expected_verbiage + "<br>"
										+ "Actual - verbiage: "
										+ context.getDriver()
												.findElement(gbpScorerReportPageObject.subSectionElementFinder(
														"Business Profile Score", "Services Offered", "verbiage"))
												.getText());
			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Business Profile Score > Services Offered SubSection is correct  ")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User sees the Business Profile Score > Payment Option SubSection is correct")
	public void userSeesTheBusinessProfileScorePaymentOptionSubSectionIsCorrect() throws IOException {

		try {
			// Step Definition
			String expected_verbiage = JsonPath
					.read(getGbpScorerReportVerbiages(),
							"$.['Business Profile Score']['Payment Option']['"
									+ context.getDriver()
											.findElement(gbpScorerReportPageObject.subSectionElementFinder(
													"Business Profile Score", "Payment Option", "verdict"))
											.getText()
									+ "']");

			if (context.getDriver().findElement(gbpScorerReportPageObject
					.subSectionElementFinder("Business Profile Score", "Payment Option", "verbiage")).getText()
					.contains(expected_verbiage)) {

				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Business Profile Score > Payment Option SubSection is correct ")
						.pass("PASSED");
			}

			else {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Business Profile Score > Payment Option SubSection is correct ")
						.fail("FAILED" + "<br>" + "Expected - verbiage: " + expected_verbiage + "<br>"
								+ "Actual - verbiage: "
								+ context.getDriver().findElement(gbpScorerReportPageObject.subSectionElementFinder(
										"Business Profile Score", "Payment Option", "verbiage")).getText());
			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Business Profile Score > Payment Option SubSection is correct ")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User sees the Business Profile Score > URLDomain SubSection is correct")
	public void userSeesTheBusinessProfileScoreURLDomainSubSectionIsCorrect() throws IOException {

		try {
			// Step Definition
			String expected_verbiage = JsonPath
					.read(getGbpScorerReportVerbiages(),
							"$.['Business Profile Score']['URLDomain']['" + context.getDriver()
									.findElement(gbpScorerReportPageObject
											.subSectionElementFinder("Business Profile Score", "URL", "verdict"))
									.getText() + "']");

			if (context.getDriver().findElement(
					gbpScorerReportPageObject.subSectionElementFinder("Business Profile Score", "URL", "verbiage"))
					.getText().contains(expected_verbiage)) {

				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Business Profile Score > URL/Domain SubSection is correct ")
						.pass("PASSED");
			}

			else {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Business Profile Score > URL/Domain SubSection is correct ")
						.fail("FAILED" + "<br>" + "Expected - verbiage: " + expected_verbiage + "<br>"
								+ "Actual - verbiage: "
								+ context.getDriver()
										.findElement(gbpScorerReportPageObject
												.subSectionElementFinder("Business Profile Score", "URL", "verbiage"))
										.getText());
			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Business Profile Score > URL/Domain SubSection is correct ")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User sees the Business Profile Score > Google Business Profile Verified SubSection is correct")
	public void userSeesTheBusinessProfileScoreGoogleBusinessProfileVerifiedSubSectionIsCorrect() throws IOException {

		try {
			// Step Definition
			String expected_verbiage = JsonPath
					.read(getGbpScorerReportVerbiages(),
							"$.['Business Profile Score']['Google Business Profile Verified']['" + context.getDriver()
									.findElement(gbpScorerReportPageObject.subSectionElementFinder(
											"Business Profile Score", "Google Business Profile Verified", "verdict"))
									.getText() + "']");

			if (context.getDriver().findElement(gbpScorerReportPageObject
					.subSectionElementFinder("Business Profile Score", "Google Business Profile Verified", "verbiage"))
					.getText().contains(expected_verbiage)) {

				// Extent Report
				context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
						"User sees the Business Profile Score > Google Business Profile Verified SubSection is correct")
						.pass("PASSED");
			}

			else {
				context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
						"User sees the Business Profile Score > Google Business Profile Verified SubSection is correct")
						.fail("FAILED" + "<br>" + "Expected - verbiage: " + expected_verbiage + "<br>"
								+ "Actual - verbiage: "
								+ context.getDriver()
										.findElement(gbpScorerReportPageObject.subSectionElementFinder(
												"Business Profile Score", "Google Business Profile Verified",
												"verbiage"))
										.getText());
			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
						"User sees the Business Profile Score > Google Business Profile Verified SubSection is correct")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User sees the Business Profile Score > Business Logo Found SubSection is correct")
	public void userSeesTheBusinessProfileScoreBusinessLogoFoundSubSectionIsCorrect() throws IOException {

		try {
			// Step Definition
			String expected_verbiage = JsonPath.read(getGbpScorerReportVerbiages(),
					"$.['Business Profile Score']['Business Logo Found']['"
							+ context.getDriver()
									.findElement(gbpScorerReportPageObject.subSectionElementFinder(
											"Business Profile Score", "Business Logo Found", "verdict"))
									.getText()
							+ "']");

			if (context.getDriver().findElement(gbpScorerReportPageObject
					.subSectionElementFinder("Business Profile Score", "Business Logo Found", "verbiage")).getText()
					.contains(expected_verbiage)) {

				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Business Profile Score > Business Logo Found SubSection is correct")
						.pass("PASSED");
			}

			else {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Business Profile Score > Business Logo Found SubSection is correct")
						.fail("FAILED" + "<br>" + "Expected - verbiage: " + expected_verbiage + "<br>"
								+ "Actual - verbiage: "
								+ context.getDriver()
										.findElement(gbpScorerReportPageObject.subSectionElementFinder(
												"Business Profile Score", "Business Logo Found", "verbiage"))
										.getText());
			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Business Profile Score > Business Logo Found SubSection is correct")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User sees the Business Profile Score > Minimum 3 business photos SubSection is correct")
	public void userSeesTheBusinessProfileScoreMinimumBusinessPhotosSubSectionIsCorrect() throws IOException {

		try {
			// Step Definition
			String expected_verbiage = JsonPath.read(getGbpScorerReportVerbiages(),
					"$.['Business Profile Score']['Minimum 3 business photos']['"
							+ context.getDriver()
									.findElement(gbpScorerReportPageObject.subSectionElementFinder(
											"Business Profile Score", "Minimum 3 business photos", "verdict"))
									.getText()
							+ "']");

			if (context.getDriver().findElement(gbpScorerReportPageObject
					.subSectionElementFinder("Business Profile Score", "Minimum 3 business photos", "verbiage"))
					.getText().contains(expected_verbiage)) {

				// Extent Report
				context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
						"User sees the Business Profile Score > Minimum 3 business photos SubSection is correct")
						.pass("PASSED");
			}

			else {
				context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
						"User sees the Business Profile Score > Minimum 3 business photos SubSection is correct")
						.fail("FAILED" + "<br>" + "Expected - verbiage: " + expected_verbiage + "<br>"
								+ "Actual - verbiage: "
								+ context.getDriver()
										.findElement(gbpScorerReportPageObject.subSectionElementFinder(
												"Business Profile Score", "Minimum 3 business photos", "verbiage"))
										.getText());
			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
						"User sees the Business Profile Score > Minimum 3 business photos SubSection is correct")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User sees the Google Post Score > Post Found SubSection is correct")
	public void userSeesTheGooglePostScorePostFoundSubSectionIsCorrect() throws IOException {

		try {
			// Step Definition
			String expected_verbiage = JsonPath
					.read(getGbpScorerReportVerbiages(),
							"$.['Google Post Score']['Post Found']['" + context
									.getDriver().findElement(gbpScorerReportPageObject
											.subSectionElementFinder("Google Post Score", "posts found", "verdict"))
									.getText() + "']");

			if (context.getDriver().findElement(
					gbpScorerReportPageObject.subSectionElementFinder("Google Post Score", "posts found", "verbiage"))
					.getText().contains(expected_verbiage)) {

				// Extent Report
				context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
						"User sees the Google Post Score > Post Found SubSection is correct").pass("PASSED");
			}

			else {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Google Post Score > Post Found SubSection is correct")
						.fail("FAILED" + "<br>" + "Expected - verbiage: " + expected_verbiage + "<br>"
								+ "Actual - verbiage: "
								+ context.getDriver().findElement(gbpScorerReportPageObject
										.subSectionElementFinder("Google Post Score", "posts found", "verbiage"))
										.getText());
			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Google Post Score > Post Found SubSection is correct")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User sees the Google Post Score > Minimum Post SubSection is correct")
	public void userSeesTheGooglePostScoreMinimumPostSubSectionIsCorrect() throws IOException {

		try {
			// Step Definition
			String expected_verbiage = JsonPath
					.read(getGbpScorerReportVerbiages(),
							"$.['Google Post Score']['Minimum Post']['"
									+ context.getDriver()
											.findElement(gbpScorerReportPageObject.subSectionElementFinder(
													"Google Post Score", "Minimum 4 post", "verdict"))
											.getText()
									+ "']");

			if (context.getDriver().findElement(gbpScorerReportPageObject.subSectionElementFinder("Google Post Score",
					"Minimum 4 post", "verbiage")).getText().contains(expected_verbiage)) {

				// Extent Report
				context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
						"User sees the Google Post Score > Minimum Post SubSection is correct").pass("PASSED");
			}

			else {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Google Post Score > Minimum Post SubSection is correct")
						.fail("FAILED" + "<br>" + "Expected - verbiage: " + expected_verbiage + "<br>"
								+ "Actual - verbiage: "
								+ context.getDriver().findElement(gbpScorerReportPageObject
										.subSectionElementFinder("Google Post Score", "Minimum 4 post", "verbiage"))
										.getText());
			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Google Post Score > Minimum Post SubSection is correct")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User sees the Google Post Score > Images in Post SubSection is correct")
	public void userSeesTheGooglePostScoreImagesInPostSubSectionIsCorrect() throws IOException {

		try {
			// Step Definition
			String expected_verbiage = JsonPath
					.read(getGbpScorerReportVerbiages(),
							"$.['Google Post Score']['Images in Post']['"
									+ context.getDriver()
											.findElement(gbpScorerReportPageObject.subSectionElementFinder(
													"Google Post Score", "Images of the business", "verdict"))
											.getText()
									+ "']");

			if (context.getDriver().findElement(gbpScorerReportPageObject.subSectionElementFinder("Google Post Score",
					"Images of the business", "verbiage")).getText().contains(expected_verbiage)) {

				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Google Post Score > Images in Post SubSection is correct")
						.pass("PASSED");
			}

			else {
				context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
						"User sees the Google Post Score > Images in Post SubSection is correct").fail(
								"FAILED" + "<br>" + "Expected - verbiage: " + expected_verbiage + "<br>"
										+ "Actual - verbiage: "
										+ context.getDriver()
												.findElement(gbpScorerReportPageObject.subSectionElementFinder(
														"Google Post Score", "Images of the business", "verbiage"))
												.getText());
			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Google Post Score > Images in Post SubSection is correct")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User sees the Google Review Score > Google Review SubSection is correct")
	public void userSeesTheGoogleReviewScoreGoogleReviewSubSectionIsCorrect() throws IOException {

		try {
			// Step Definition
			String expected_verbiage = JsonPath
					.read(getGbpScorerReportVerbiages(),
							"$.['Google Review Score']['Review Score']['"
									+ context.getDriver()
											.findElement(gbpScorerReportPageObject.subSectionElementFinder(
													"Google Review Score", "Google Reviews", "verdict"))
											.getText()
									+ "']");

			if (context.getDriver().findElement(gbpScorerReportPageObject.subSectionElementFinder("Google Review Score",
					"Google Reviews", "verbiage")).getText().contains(expected_verbiage)) {

				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Google Review Score > Google Review SubSection is correct")
						.pass("PASSED");
			}

			else {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Google Review Score > Google Review SubSection is correct")
						.fail("FAILED" + "<br>" + "Expected - verbiage: " + expected_verbiage + "<br>"
								+ "Actual - verbiage: "
								+ context.getDriver().findElement(gbpScorerReportPageObject
										.subSectionElementFinder("Google Review Score", "Google Reviews", "verbiage"))
										.getText());
			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Google Review Score > Google Review SubSection is correct")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User sees the Google Review Score > Ave Review SubSection is correct")
	public void userSeesTheGoogleReviewScoreAveReviewSubSectionIsCorrect() throws IOException {

		try {
			// Step Definition
			String expected_verbiage = JsonPath
					.read(getGbpScorerReportVerbiages(),
							"$.['Google Review Score']['Ave Review']['" + context.getDriver()
									.findElement(gbpScorerReportPageObject
											.subSectionElementFinder("Google Review Score", "Ave reviews", "verdict"))
									.getText() + "']");

			if (context.getDriver().findElement(
					gbpScorerReportPageObject.subSectionElementFinder("Google Review Score", "Ave reviews", "verbiage"))
					.getText().contains(expected_verbiage)) {

				// Extent Report
				context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
						"User sees the Google Review Score > Ave Review SubSection is correct").pass("PASSED");
			}

			else {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Google Review Score > Ave Review SubSection is correct")
						.fail("FAILED" + "<br>" + "Expected - verbiage: " + expected_verbiage + "<br>"
								+ "Actual - verbiage: "
								+ context.getDriver().findElement(gbpScorerReportPageObject
										.subSectionElementFinder("Google Review Score", "Ave reviews", "verbiage"))
										.getText());
			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Google Review Score > Ave Review SubSection is correct")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User sees the Google Review Score > Owner Response SubSection is correct")
	public void userSeesTheGoogleReviewScoreOwnerResponseSubSectionIsCorrect() throws IOException {

		try {
			// Step Definition
			String expected_verbiage = JsonPath
					.read(getGbpScorerReportVerbiages(),
							"$.['Google Review Score']['Owner Response']['"
									+ context.getDriver()
											.findElement(gbpScorerReportPageObject.subSectionElementFinder(
													"Google Review Score", "Owner response", "verdict"))
											.getText()
									+ "']");

			if (context.getDriver().findElement(gbpScorerReportPageObject.subSectionElementFinder("Google Review Score",
					"Owner response", "verbiage")).getText().contains(expected_verbiage)) {

				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Google Review Score > Owner Response SubSection is correct")
						.pass("PASSED");
			}

			else {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Google Review Score > Owner Response SubSection is correct")
						.fail("FAILED" + "<br>" + "Expected - verbiage: " + expected_verbiage + "<br>"
								+ "Actual - verbiage: "
								+ context.getDriver().findElement(gbpScorerReportPageObject
										.subSectionElementFinder("Google Review Score", "Owner response", "verbiage"))
										.getText());
			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Google Review Score > Owner Response SubSection is correct")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User sees the Similar Listing Section is correct")
	public void userSeesTheSimilarListingSectionIsCorrect() {

		try {

			context.getWait().until(ExpectedConditions
					.presenceOfElementLocated(gbpScorerReportPageObject.sectionElementFinder("Similar Listings")));

			HashMap<String, Integer> similarListing = new HashMap<>();

			similarListing.put("Name",
					context.getDriver().findElements(gbpScorerReportPageObject.similarListingSection_NameCount).size());
			similarListing.put("Rating", context.getDriver()
					.findElements(gbpScorerReportPageObject.similarListingSection_RatingCount).size());

			// if there is NO rating then there are NONE star and bote hence below.
			similarListing.put("Star", context.getDriver()
					.findElements(gbpScorerReportPageObject.similarListingSection_RatingCount).size());
			similarListing.put("Vote", context.getDriver()
					.findElements(gbpScorerReportPageObject.similarListingSection_RatingCount).size());

			if (similarListing.get("Name") == 3) {

				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User sees the Similar Listing Section is correct")
						.pass("PASSED " + "<br>" + "All 3 Similar Listings are Rendered");

				for (String i : similarListing.keySet()) {

					if (similarListing.get(i) < 3) {
						context.getExtentTestScenario().createNode((3 - similarListing.get(i)) + " of 4 " + i
								+ " is missing from Similar Listing. See screenshot below.").warning("");
					}
				}

			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User sees the Similar Listing Section is correct")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

	@Then("User scroll to Recent Review Section")
	public void userScrollToRecentReviewSection() {

		try {

			context.getWait().until(ExpectedConditions
					.presenceOfElementLocated(gbpScorerReportPageObject.recentReviewSection_RecentReviewCount));

			if (Integer.parseInt(context.getDriver()
					.findElement(gbpScorerReportPageObject.recentReviewSection_RecentReviewCount).getText()) == 0) {

				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User scroll to Recent Review Section")
						.pass("PASSED " + "<br>" + "0 Recent Reviews for this Business Name");

			} else {
				// document zooming
				// context.getDriver().executeScript("document.body.style.zoom = '0.75'");

				context.getWait().until(ExpectedConditions
						.presenceOfElementLocated(gbpScorerReportPageObject.sectionElementFinder("Recent Reviews")));

				// scroll to pixel
				// context.getDriver().executeScript("window.scrollBy(0,2000)");

				// scroll to element
				context.getDriver().executeScript("arguments[0].scrollIntoView(false);", context.getDriver()
						.findElement(gbpScorerReportPageObject.sectionElementFinder("Recent Reviews")));

				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User scroll to Recent Review Section").pass("PASSED");
			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User scroll to Recent Review Section")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Then("User sees the Recent Review Section is correct")
	public void userSeesTheRecentReviewSectionIsCorrect() {

		try {

			context.getWait().until(ExpectedConditions
					.presenceOfElementLocated(gbpScorerReportPageObject.recentReviewSection_RecentReviewCount));

			if (Integer.parseInt(context.getDriver()
					.findElement(gbpScorerReportPageObject.recentReviewSection_RecentReviewCount).getText()) == 0) {

				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User sees the Recent Review Section is correct")
						.pass("PASSED " + "<br>" + "0 Recent Reviews for this Business Name");

			}

			else {

				context.getWait().until(ExpectedConditions
						.presenceOfElementLocated(gbpScorerReportPageObject.sectionElementFinder("Recent Reviews")));

				HashMap<String, Integer> recentReview = new HashMap<>();
				recentReview.put("RecentReviewCount", Integer.parseInt(context.getDriver()
						.findElement(gbpScorerReportPageObject.recentReviewSection_RecentReviewCount).getText()));
				recentReview.put("RecentReviewActual", context.getDriver()
						.findElements(gbpScorerReportPageObject.recentReviewSection_RecentReviewActual).size());
				recentReview.put("RecentReviewAuthorCount", context.getDriver()
						.findElements(gbpScorerReportPageObject.recentReviewSection_RecentReviewAuthorCount).size());
				recentReview.put("RecentReviewRatingCount", context.getDriver()
						.findElements(gbpScorerReportPageObject.recentReviewSection_RecentReviewRatingCount).size());
				recentReview.put("RecentReviewStarCount", context.getDriver()
						.findElements(gbpScorerReportPageObject.recentReviewSection_RecentReviewStarCount).size());
				recentReview.put("RecentReviewCommentCount", context.getDriver()
						.findElements(gbpScorerReportPageObject.recentReviewSection_RecentReviewCommentCount).size());
				recentReview.put("RecentReviewDateCount", context.getDriver()
						.findElements(gbpScorerReportPageObject.recentReviewSection_RecentReviewDateCount).size());

				if (recentReview.get("RecentReviewCount") >= 4 && recentReview.get("RecentReviewActual") == 4) {

					// Extent Report
					context.getExtentTestScenario()
							.createNode(new GherkinKeyword("When"), "User sees the Recent Review Section is correct")
							.pass("PASSED " + "<br>" + "All 4 Recent Reviews are Rendered");

					for (String i : recentReview.keySet()) {

						if (recentReview.get(i) < 4) {
							context.getExtentTestScenario()
									.createNode((4 - recentReview.get(i)) + " of 4 "
											+ i.replace("RecentReview", "").replace("Count", "")
											+ " is missing from Recent Reviews. See screenshot below.")
									.warning("");
						}
					}

				}

				else if (recentReview.get("RecentReviewCount") >= 4 && recentReview.get("RecentReviewActual") < 4) {

					// Extent Report
					context.getExtentTestScenario()
							.createNode(new GherkinKeyword("When"), "User sees the Recent Review Section is correct")
							.pass("PASSED " + "<br>" + "Not all 4 Recent Reviews are Rendered");

				}

			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User sees the Recent Review Section is correct")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

}
