package webApp.SEOR;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.Status;
import com.jayway.jsonpath.JsonPath;

import helper.webAppContext;
import helper.webAppHelper;
import io.cucumber.java.en.Then;
import net.minidev.json.JSONArray;
import testAuto.Service.CommonService;
import testAuto.Service.ExtentReportService;
import testAuto.Service.HttpReqService;
import testAuto.Service.WebAuditService;

public class webAuditReportPage extends webAppHelper {

	// Declare Driver Instance
	// ==========================================
	private webAppContext context;

	public webAuditReportPage(webAppContext context) {
		super();
		this.context = context;
	}

	// Declare Services
	// ==========================================
	WebAuditService webAuditService = new WebAuditService();
	CommonService commonService = new CommonService();
	HttpReqService httpReqService = new HttpReqService();
	ExtentReportService extentReportService = new ExtentReportService();

	// Declare Variables
	// ==========================================
	ArrayList<String> details = new ArrayList<String>();

	// Page Elements
	// ==========================================
	// Summary Section
	// ===================================
	By summarySection_usability = By.xpath("//td[text()='Usability']");
	By summarySection_security = By.xpath("//td[text()='Security']");
	By summarySection_organicTraffic = By.xpath("//td[text()='Organic Traffic']");
	By summarySection_paidTraffic = By.xpath("//td[text()='Paid Traffic']");
	By summarySection_socialActivity = By.xpath("//td[text()='Social Activity']");
	By summarySection_usability_score = By.xpath("//td[text()='Usability']/following-sibling::td[last()]");
	By summarySection_security_score = By.xpath("//td[text()='Security']/following-sibling::td[last()]");
	By summarySection_organicTraffic_score = By.xpath("//td[text()='Organic Traffic']/following-sibling::td[last()]");
	By summarySection_paidTraffic_score = By.xpath("//td[text()='Paid Traffic']/following-sibling::td[last()]");
	By summarySection_socialActivity_score = By.xpath("//td[text()='Social Activity']/following-sibling::td[last()]");

	By summarySection_webSiteScore_alpha = By.xpath("//div[@class='overall-grade']/h3");
	By summarySection_webSiteScore_percentage = By.xpath("//div[@class='overall-grade']/p");

	By summarySection_verdict_critical = By.xpath("//table//th[text()='Critical']");
	By summarySection_verdict_criticalScore = By.xpath("//table//th[text()='Critical']/following-sibling::th");
	By summarySection_verdict_forImprovement = By.xpath("//table//th[text()='For Improvement']");
	By summarySection_verdict_forImprovementScore = By
			.xpath("//table//th[text()='For Improvement']/following-sibling::th");
	By summarySection_verdict_lookingGood = By.xpath("//table//th[text()='Looking Good']");
	By summarySection_verdict_lookingGoodScore = By.xpath("//table//th[text()='Looking Good']/following-sibling::th");

	// Sub Section - Element Finder
	// ===================================
	private By SectionElemenFinder(String SectionName) {

		// return By.xpath("(//div[contains(@id,'" + SectionName + "')]//h3)[1]");
		return By.xpath("//div[contains(@id,'" + SectionName + "')]");

	}

	private By subSectionElementFinder(String SubSectionName, String SubSectionNameType) {

		By element = null;
		if (SubSectionNameType.matches("verdict")) {

			element = By.xpath("//p[contains(text(), '" + SubSectionName + "')]//ancestor::div[2]//span");

		} else if (SubSectionNameType.matches("verbiage")) {
			element = By.xpath("//p[contains(text(), '" + SubSectionName
					+ "')]//ancestor::div[2]//div[contains(@class, 'breakdown')]/p");

		} else {
			// Do Nothing
		}

		return element;

	}

	private By subSectionElementParentH3Finder(String SubSectionName) {

		return By.xpath("//p[contains(text(), '" + SubSectionName + "')]//parent::div/h3");

	}

	private By subSectionSocialElementFinder(String SubSectionName, String SubSectionNameType) {

		By element = null;
		if (SubSectionNameType.matches("verdict")) {

			element = By.xpath("//div[@id='" + SubSectionName + "']/div[2]/div[1]");

		} else if (SubSectionNameType.matches("verbiageHeader")) {
			element = By.xpath("//div[@id='" + SubSectionName + "']/div[1]/div[2]");

		} else if (SubSectionNameType.matches("verbiage")) {
			element = By.xpath("//div[@id='" + SubSectionName + "']/div[2]/div[2]");

		} else if (SubSectionNameType.matches("verbiagerow1")) {
			element = By.xpath("(//div[@id='" + SubSectionName + "']/div[2]/div[2]//h3)[1]");

		} else if (SubSectionNameType.matches("verbiagerow2")) {
			element = By.xpath("(//div[@id='" + SubSectionName + "']/div[2]/div[2]//h3)[2]");

		} else if (SubSectionNameType.matches("verbiagerow3")) {
			element = By.xpath("(//div[@id='" + SubSectionName + "']/div[2]/div[2]//h3)[3]");

		} else if (SubSectionNameType.matches("verbiagerow4")) {
			element = By.xpath("(//div[@id='" + SubSectionName + "']/div[2]/div[2]//h3)[4]");

		}

		return element;

	}

	private By subSectionSocialElementParentH3Finder(String SubSectionName, String colNumber) {

		return By.xpath("(//div[@id='" + SubSectionName + "']//div[@class='row']/div)[" + colNumber + "]");

	}

	// Header Row
	// h3[text()='Usability']//ancestor::div[contains(@class, 'row')]//span

	// Mobile Friendliness SubSection
	// ===================================
	By usabilitySection_mobileFriendliness_verdict = By
			.xpath("//p[text()='Mobile Friendliness']//ancestor::div[2]//span");
	By usabilitySection_mobileFriendliness_verbiage = By
			.xpath("//p[text()='Mobile Friendliness']//ancestor::div[2]//div[contains(@class, 'breakdown')]/p");

	// Google Analytics Found SubSection
	// ===================================
	By usabilitySection_googleAnalyticsFound_verdict = By
			.xpath("//p[text()='Google Analytics Found']//ancestor::div[2]//span");
	By usabilitySection_googleAnalyticsFound_verbiage = By
			.xpath("//p[text()='Google Analytics Found']//ancestor::div[2]//div[contains(@class, 'breakdown')]/p");

	// Page Speed Insights SubSection
	// ===================================
	By usabilitySection_pageSpeedInsights_verdict = By
			.xpath("//p[text()='Page Speed Insights']//ancestor::div[2]//span");
	By usabilitySection_pageSpeedInsights_verbiage = By
			.xpath("//p[text()='Page Speed Insights']//ancestor::div[2]//div[contains(@class, 'breakdown')]/p");

	// Page Step Definition
	// =================================================

	@Then("User sees the Summary Section > Grades SubSection is correct")
	public void userSeesTheSummarySectionGradesSubSectionIsCorrect() {

		try {
			// Step Definition
			if (context.getDriver().findElement(summarySection_usability).isDisplayed()
					&& !context.getDriver().findElement(summarySection_usability_score).getText().isEmpty()
					&& context.getDriver().findElement(summarySection_security).isDisplayed()
					&& !context.getDriver().findElement(summarySection_security_score).getText().isEmpty()
					&& context.getDriver().findElement(summarySection_organicTraffic).isDisplayed()
					&& !context.getDriver().findElement(summarySection_organicTraffic_score).getText().isEmpty()
					&& context.getDriver().findElement(summarySection_paidTraffic).isDisplayed()
					&& !context.getDriver().findElement(summarySection_paidTraffic_score).getText().isEmpty()
					&& context.getDriver().findElement(summarySection_socialActivity).isDisplayed()
					&& !context.getDriver().findElement(summarySection_socialActivity_score).getText().isEmpty()) {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				extentReportService.insertPassedStep(context,
						"User sees the Summary Section > Grades SubSection is correct", details);

				context.getExtentTestScenario().log(Status.PASS, "PASSED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEOR/webAudit/userSeesTheSummarySectionGradesSubSectionIsCorrect.png?raw=true");
			}

			else {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				extentReportService.insertFailedStep(context,
						"User sees the Summary Section > Grades SubSection is correct", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEOR/webAudit/userSeesTheSummarySectionGradesSubSectionIsCorrect.png?raw=true");

			}

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context,
						"User sees the Summary Section > Grades SubSection is correct", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEOR/webAudit/userSeesTheSummarySectionGradesSubSectionIsCorrect.png?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User sees the Summary Section > WebSite Score SubSection is correct")
	public void userSeesTheSummarySectionWebSiteScoreSubSectionIsCorrect() {

		try {
			// Step Definition
			if (context.getDriver().findElement(summarySection_webSiteScore_alpha).isDisplayed()
					&& !context.getDriver().findElement(summarySection_webSiteScore_alpha).getText().isEmpty()
					&& context.getDriver().findElement(summarySection_webSiteScore_percentage).isDisplayed()
					&& !context.getDriver().findElement(summarySection_webSiteScore_percentage).getText().isEmpty()) {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				extentReportService.insertPassedStep(context,
						"User sees the Summary Section > WebSite Score SubSection is correct", details);

				context.getExtentTestScenario().log(Status.PASS, "PASSED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEOR/webAudit/userSeesTheSummarySectionWebSiteScoreSubSectionIsCorrect.png?raw=true");
			}

			else {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				extentReportService.insertFailedStep(context,
						"User sees the Summary Section > WebSite Score SubSection is correct", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEOR/webAudit/userSeesTheSummarySectionWebSiteScoreSubSectionIsCorrect.png?raw=true");

			}

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context,
						"User sees the Summary Section > WebSite Score SubSection is correct", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEOR/webAudit/userSeesTheSummarySectionWebSiteScoreSubSectionIsCorrect.png?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User sees the Summary Section > Verdict Table SubSection is correct")
	public void userSeesTheSummarySectionVerdictTableSubSectionIsCorrect() {

		try {

			// Step Definition
			// Critical, For Improvement, Looking Good Table Header
			// ===============================================================
			if (context.getDriver().findElement(summarySection_verdict_critical).isDisplayed()
					&& context.getDriver().findElement(summarySection_verdict_criticalScore).isDisplayed()
					&& !context.getDriver().findElement(summarySection_verdict_criticalScore).getText().isEmpty()

					&& context.getDriver().findElement(summarySection_verdict_forImprovement).isDisplayed()
					&& context.getDriver().findElement(summarySection_verdict_forImprovementScore).isDisplayed()
					&& !context.getDriver().findElement(summarySection_verdict_forImprovementScore).getText().isEmpty()

					&& context.getDriver().findElement(summarySection_verdict_lookingGood).isDisplayed()
					&& context.getDriver().findElement(summarySection_verdict_lookingGoodScore).isDisplayed()
					&& !context.getDriver().findElement(summarySection_verdict_lookingGoodScore).getText().isEmpty()) {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add(context.getDriver().findElement(summarySection_verdict_critical).getText() + " - "
						+ context.getDriver().findElement(summarySection_verdict_criticalScore).getText());
				details.add(context.getDriver().findElement(summarySection_verdict_forImprovement).getText() + " - "
						+ context.getDriver().findElement(summarySection_verdict_forImprovementScore).getText());
				details.add(context.getDriver().findElement(summarySection_verdict_lookingGood).getText() + " - "
						+ context.getDriver().findElement(summarySection_verdict_lookingGoodScore).getText());
				extentReportService.insertPassedStep(context,
						"User sees the Summary Section > Verdict Table SubSection is correct", details);

				context.getExtentTestScenario().log(Status.PASS, "PASSED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEOR/webAudit/userSeesTheSummarySectionVerdictTableSubSectionIsCorrect.png?raw=true");

			}

			else {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add(context.getDriver().findElement(summarySection_verdict_critical).getText() + " - "
						+ context.getDriver().findElement(summarySection_verdict_criticalScore).getText());
				details.add(context.getDriver().findElement(summarySection_verdict_forImprovement).getText() + " - "
						+ context.getDriver().findElement(summarySection_verdict_forImprovementScore).getText());
				details.add(context.getDriver().findElement(summarySection_verdict_lookingGood).getText() + " - "
						+ context.getDriver().findElement(summarySection_verdict_lookingGoodScore).getText());

				extentReportService.insertFailedStep(context,
						"User sees the Summary Section > Verdict Table SubSection is correct", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEOR/webAudit/userSeesTheSummarySectionVerdictTableSubSectionIsCorrect.png?raw=true");

			}

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add(context.getDriver().findElement(summarySection_verdict_critical).getText() + " - "
						+ context.getDriver().findElement(summarySection_verdict_criticalScore).getText());
				details.add(context.getDriver().findElement(summarySection_verdict_forImprovement).getText() + " - "
						+ context.getDriver().findElement(summarySection_verdict_forImprovementScore).getText());
				details.add(context.getDriver().findElement(summarySection_verdict_lookingGood).getText() + " - "
						+ context.getDriver().findElement(summarySection_verdict_lookingGoodScore).getText());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context,
						"User sees the Summary Section > Verdict Table SubSection is correct", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEOR/webAudit/userSeesTheSummarySectionVerdictTableSubSectionIsCorrect.png?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User sees the Usability Section > Mobile Friendliness SubSection is correct")
	public void userSeesTheUsabilitySectionMobileFriendlinessSubSectionIsCorrect() throws IOException {

		try {
			// Step Definition
			context.getWait().until(ExpectedConditions
					.presenceOfElementLocated(subSectionElementFinder("Mobile Friendliness", "verdict")));

			context.getDriver().executeScript("arguments[0].scrollIntoView(false);", context.getDriver()
					.findElement(By.xpath("//div[@id='usability-section']//div[@class='item-collapsible open']")));

			String expected_verbiage = JsonPath
					.read(getWebAuditReportVerbiages(),
							"$.['Usability']['Mobile Friendliness']['" + context.getDriver()
									.findElement(subSectionElementFinder("Mobile Friendliness", "verdict")).getText()
									+ "']");

			// Validate verbiage from JSON against WebAudit Report -- Looking Good or
			// Critical
			if (context.getDriver().findElement(subSectionElementFinder("Mobile Friendliness", "verdict")).getText()
					.equals("Looking Good")
					&& context.getDriver().findElement(subSectionElementFinder("Mobile Friendliness", "verbiage"))
							.getText().contains(expected_verbiage)) {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Expected Verbiage: " + expected_verbiage);
				details.add("Actual Verbiage: " + context.getDriver()
						.findElement(subSectionElementFinder("Mobile Friendliness", "verbiage")).getText());
				extentReportService.insertPassedStep(context,
						"User sees the Usability Section > Mobile Friendliness SubSection is correct", details);

				context.getExtentTestScenario().log(Status.PASS, "PASSED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEOR/webAudit/userSeesTheUsabilitySectionMobileFriendlinessSubSectionIsCorrect.png?raw=true");

			}

			else if (context.getDriver().findElement(subSectionElementFinder("Mobile Friendliness", "verdict"))
					.getText().equals("Critical")
					&& context.getDriver().findElement(subSectionElementFinder("Mobile Friendliness", "verbiage"))
							.getText().contains(expected_verbiage)) {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Expected Verbiage: " + expected_verbiage);
				details.add("Actual Verbiage: " + context.getDriver()
						.findElement(subSectionElementFinder("Mobile Friendliness", "verbiage")).getText());
				extentReportService.insertPassedStep(context,
						"User sees the Usability Section > Mobile Friendliness SubSection is correct", details);

				context.getExtentTestScenario().log(Status.PASS, "PASSED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEOR/webAudit/userSeesTheUsabilitySectionMobileFriendlinessSubSectionIsCorrect.png?raw=true");

			}

			else if (context.getDriver().findElement(subSectionElementFinder("Mobile Friendliness", "verdict"))
					.getText().equals("N/A")
					&& context.getDriver().findElement(subSectionElementFinder("Mobile Friendliness", "verbiage"))
							.getText().contains(expected_verbiage)) {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Expected Verbiage: " + expected_verbiage);
				details.add("Actual Verbiage: " + context.getDriver()
						.findElement(subSectionElementFinder("Mobile Friendliness", "verbiage")).getText());
				extentReportService.insertPassedStep(context,
						"User sees the Usability Section > Mobile Friendliness SubSection is correct", details);

				context.getExtentTestScenario().log(Status.PASS, "PASSED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEOR/webAudit/userSeesTheUsabilitySectionMobileFriendlinessSubSectionIsCorrect.png?raw=true");

			}

			else {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Expected Verbiage: " + expected_verbiage);
				details.add("Actual Verbiage: " + context.getDriver()
						.findElement(subSectionElementFinder("Mobile Friendliness", "verbiage")).getText());
				extentReportService.insertFailedStep(context,
						"User sees the Usability Section > Mobile Friendliness SubSection is correct", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEOR/webAudit/userSeesTheUsabilitySectionMobileFriendlinessSubSectionIsCorrect.png?raw=true");

			}

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context,
						"User sees the Usability Section > Mobile Friendliness SubSection is correct", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEOR/webAudit/userSeesTheUsabilitySectionMobileFriendlinessSubSectionIsCorrect.png?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User sees the Usability Section > Google Analytics Found SubSection is correct")
	public void userSeesTheUsabilitySectionGoogleAnalyticsFoundSubSectionIsCorrect() throws IOException {

		String expected_verbiage;

		try {

			try {

				// Step Definition
				// Check if Rating is available in report
				context.getWait().until(ExpectedConditions
						.presenceOfElementLocated(subSectionElementFinder("Google Analytics", "verdict")));

				context.getDriver().executeScript("arguments[0].scrollIntoView(false);",
						context.getDriver().findElement(By.xpath("//div[@id='usability-section']/div/div[3]")));

				// Get value from JSON based on rating -- Looking Good or Critical
				expected_verbiage = JsonPath.read(getWebAuditReportVerbiages(),
						"$.['Usability']['Google Analytics']['"
								+ context.getDriver()
										.findElement(subSectionElementFinder("Google Analytics", "verdict")).getText()
								+ "']");

				// Validate verbiage from JSON against WebAudit Report -- Looking Good or
				// Critical
				if (context.getDriver().findElement(subSectionElementFinder("Google Analytics", "verdict")).getText()
						.equals("Looking Good")
						&& context.getDriver().findElement(subSectionElementFinder("Google Analytics", "verbiage"))
								.getText().contains(expected_verbiage)) {

					// Extent Report
					details.clear();
					details.add("Page URL: " + context.getDriver().getCurrentUrl());
					details.add("Expected Verbiage: " + expected_verbiage);
					details.add("Actual Verbiage: " + context.getDriver()
							.findElement(subSectionElementFinder("Google Analytics", "verbiage")).getText());
					extentReportService.insertPassedStep(context,
							"User sees the Usability Section > Google Analytics SubSection is correct", details);

					context.getExtentTestScenario().log(Status.PASS, "PASSED");
					extentReportService.attachedScreenshotToReport(context,
							"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEOR/webAudit/userSeesTheUsabilitySectionGoogleAnalyticsFoundSubSectionIsCorrect.png?raw=true");

				}

				else if (context.getDriver().findElement(subSectionElementFinder("Google Analytics", "verdict"))
						.getText().equals("Critical")
						&& context.getDriver().findElement(subSectionElementFinder("Google Analytics", "verbiage"))
								.getText().contains(expected_verbiage)) {

					// Extent Report
					details.clear();
					details.add("Page URL: " + context.getDriver().getCurrentUrl());
					details.add("Expected Verbiage: " + expected_verbiage);
					details.add("Actual Verbiage: " + context.getDriver()
							.findElement(subSectionElementFinder("Google Analytics", "verbiage")).getText());
					extentReportService.insertPassedStep(context,
							"User sees the Usability Section > Google Analytics SubSection is correct", details);

					context.getExtentTestScenario().log(Status.PASS, "PASSED");
					extentReportService.attachedScreenshotToReport(context,
							"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEOR/webAudit/userSeesTheUsabilitySectionGoogleAnalyticsFoundSubSectionIsCorrect.png?raw=true");

				}

				else if (context.getDriver().findElement(subSectionElementFinder("Google Analytics", "verdict"))
						.getText().equals("N/A")
						&& context.getDriver().findElement(subSectionElementFinder("Google Analytics", "verbiage"))
								.getText().contains(expected_verbiage)) {

					// Extent Report
					details.clear();
					details.add("Page URL: " + context.getDriver().getCurrentUrl());
					details.add("Expected Verbiage: " + expected_verbiage);
					details.add("Actual Verbiage: " + context.getDriver()
							.findElement(subSectionElementFinder("Google Analytics", "verbiage")).getText());
					extentReportService.insertPassedStep(context,
							"User sees the Usability Section > Google Analytics SubSection is correct", details);

					context.getExtentTestScenario().log(Status.PASS, "PASSED");
					extentReportService.attachedScreenshotToReport(context,
							"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEOR/webAudit/userSeesTheUsabilitySectionGoogleAnalyticsFoundSubSectionIsCorrect.png?raw=true");

				}

				else {

					// Extent Report
					details.clear();
					details.add("Page URL: " + context.getDriver().getCurrentUrl());
					details.add("Expected Verbiage: " + expected_verbiage);
					details.add("Actual Verbiage: " + context.getDriver()
							.findElement(subSectionElementFinder("Google Analytics", "verbiage")).getText());
					extentReportService.insertFailedStep(context,
							"User sees the Usability Section > Google Analytics SubSection is correct", details);

					context.getExtentTestScenario().log(Status.FAIL, "FAILED");
					extentReportService.attachedScreenshotToReport(context,
							"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEOR/webAudit/userSeesTheUsabilitySectionGoogleAnalyticsFoundSubSectionIsCorrect.png?raw=true");

				}

			} catch (Exception e) {

				// Check if Rating is available in report
				context.getWait().until(ExpectedConditions
						.presenceOfElementLocated(subSectionElementFinder("data could not be retrieved ", "verdict")));

				// Get value from JSON based on rating -- Looking Good or Critical
				expected_verbiage = JsonPath.read(getWebAuditReportVerbiages(),
						"$.['Usability']['Google Analytics']['N/A']");

				if (context.getDriver().findElement(subSectionElementFinder("Google Analytics", "verdict")).getText()
						.equals("N/A")
						&& context.getDriver().findElement(subSectionElementFinder("Google Analytics", "verbiage"))
								.getText().contains(expected_verbiage)) {

					// Extent Report
					details.clear();
					details.add("Page URL: " + context.getDriver().getCurrentUrl());
					details.add("Expected Verbiage: " + expected_verbiage);
					details.add("Actual Verbiage: " + context.getDriver()
							.findElement(subSectionElementFinder("Google Analytics", "verbiage")).getText());
					extentReportService.insertPassedStep(context,
							"User sees the Usability Section > Google Analytics SubSection is correct", details);

					context.getExtentTestScenario().log(Status.PASS, "PASSED");
					extentReportService.attachedScreenshotToReport(context,
							"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEOR/webAudit/userSeesTheUsabilitySectionGoogleAnalyticsFoundSubSectionIsCorrect.png?raw=true");

				}

				else {

					// Extent Report
					details.clear();
					details.add("Page URL: " + context.getDriver().getCurrentUrl());
					details.add("Expected Verbiage: " + expected_verbiage);
					details.add("Actual Verbiage: " + context.getDriver()
							.findElement(subSectionElementFinder("Google Analytics", "verbiage")).getText());
					extentReportService.insertFailedStep(context,
							"User sees the Usability Section > Google Analytics SubSection is correct", details);

					context.getExtentTestScenario().log(Status.FAIL, "FAILED");
					extentReportService.attachedScreenshotToReport(context,
							"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEOR/webAudit/userSeesTheUsabilitySectionGoogleAnalyticsFoundSubSectionIsCorrect.png?raw=true");

				}

			}

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context,
						"User sees the Usability Section > Google Analytics Found SubSection is correct", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEOR/webAudit/userSeesTheUsabilitySectionGoogleAnalyticsFoundSubSectionIsCorrect.png?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User sees the Usability Section > Page Speed Insight SubSection is correct")
	public void userSeesTheUsabilitySectionPageSpeedInsightSubSectionIsCorrect() throws IOException {

		try {
			// Step Definition
			context.getWait().until(
					ExpectedConditions.presenceOfElementLocated(subSectionElementFinder("website speed", "verdict")));

			context.getDriver().executeScript("document.body.style.zoom = '0.75'");

			context.getDriver().executeScript("arguments[0].scrollIntoView(false);",
					context.getDriver().findElement(By.xpath("//div[@id='usability-section']/div/div[4]")));

			JSONArray expected_verbiage = JsonPath
					.read(getWebAuditReportVerbiages(),
							"$.['Usability']['Page Speed Insights']['"
									+ context.getDriver()
											.findElement(subSectionElementFinder("website speed", "verdict")).getText()
									+ "']");

			for (int i = 0; i < expected_verbiage.size(); i++) {

				if (expected_verbiage.get(i).toString().equals(context.getDriver()
						.findElement(subSectionElementFinder("website speed", "verbiage")).getText()))

				{

					if (context.getDriver().findElement(subSectionElementFinder("website speed", "verbiage")).getText()
							.equals(expected_verbiage.get(i))) {

						// Extent Report
						details.clear();
						details.add("Page URL: " + context.getDriver().getCurrentUrl());
						details.add("Expected Verbiage: " + expected_verbiage.get(i));
						details.add("Actual Verbiage: " + context.getDriver()
								.findElement(subSectionElementFinder("website speed", "verbiage")).getText());
						extentReportService.insertPassedStep(context,
								"User sees the Usability Section > Page Speed Insight SubSection is correct", details);

						context.getExtentTestScenario().log(Status.PASS, "PASSED");
						extentReportService.attachedScreenshotToReport(context,
								"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEOR/webAudit/userSeesTheUsabilitySectionPageSpeedInsightSubSectionIsCorrect.png?raw=true");

					}

					else {

						// Extent Report
						details.clear();
						details.add("Page URL: " + context.getDriver().getCurrentUrl());
						details.add("Expected Verbiage: " + expected_verbiage.get(i));
						details.add("Actual Verbiage: " + context.getDriver()
								.findElement(subSectionElementFinder("website speed", "verbiage")).getText());
						extentReportService.insertFailedStep(context,
								"User sees the Usability Section > Page Speed Insight SubSection is correct", details);

						context.getExtentTestScenario().log(Status.FAIL, "FAILED");
						extentReportService.attachedScreenshotToReport(context,
								"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEOR/webAudit/userSeesTheUsabilitySectionPageSpeedInsightSubSectionIsCorrect.png?raw=true");

					}

					break;
				}
			}

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context,
						"User sees the Usability Section > Page Speed Insight SubSection is correct", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEOR/webAudit/userSeesTheUsabilitySectionPageSpeedInsightSubSectionIsCorrect.png?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User scroll to Security Section")
	public void userScrollToSecuritySection() {

		try {
			// document zooming
			// context.getDriver().executeScript("document.body.style.zoom = '0.75'");

			context.getWait().until(ExpectedConditions.presenceOfElementLocated(SectionElemenFinder("security")));

			// scroll to pixel
			// context.getDriver().executeScript("window.scrollBy(0,2000)");

			// scroll to element
			context.getDriver().executeScript("arguments[0].scrollIntoView(false);",
					context.getDriver().findElement(SectionElemenFinder("security")));

			// Extent Report
			details.clear();
			details.add("Page URL: " + context.getDriver().getCurrentUrl());
			extentReportService.insertPassedStep(context, "User scroll to Security Section", details);

			context.getExtentTestScenario().log(Status.PASS, "PASSED");
			extentReportService.attachedScreenshotToReport(context,
					"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context, "User scroll to Security Section", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Then("User sees the Security Section > SSL SubSection is correct")
	public void userSeesTheSecuritySectionSSLSubSectionIsCorrect() throws IOException {

		try {
			// Step Definition
			String expected_verbiage = JsonPath.read(getWebAuditReportVerbiages(), "$.['Security']['SSL']['"
					+ context.getDriver().findElement(subSectionElementFinder("SSL", "verdict")).getText() + "']");

			if (context.getDriver().findElement(subSectionElementFinder("SSL", "verdict")).getText()
					.equals("Looking Good")
					&& context.getDriver().findElement(subSectionElementFinder("SSL", "verbiage")).getText()
							.contains(expected_verbiage)) {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Expected Verbiage: " + expected_verbiage);
				details.add("Actual Verbiage: "
						+ context.getDriver().findElement(subSectionElementFinder("SSL", "verbiage")).getText());
				extentReportService.insertPassedStep(context,
						"User sees the Security Section > SSL SubSection is correct", details);

				context.getExtentTestScenario().log(Status.PASS, "PASSED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			}

			else if (context.getDriver().findElement(subSectionElementFinder("SSL", "verdict")).getText()
					.equals("Critical")
					&& context.getDriver().findElement(subSectionElementFinder("SSL", "verbiage")).getText()
							.contains(expected_verbiage)) {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Expected Verbiage: " + expected_verbiage);
				details.add("Actual Verbiage: "
						+ context.getDriver().findElement(subSectionElementFinder("SSL", "verbiage")).getText());
				extentReportService.insertPassedStep(context,
						"User sees the Security Section > SSL SubSection is correct", details);

				context.getExtentTestScenario().log(Status.PASS, "PASSED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			}

			else if (context.getDriver().findElement(subSectionElementFinder("SSL", "verdict")).getText().equals("N/A")
					&& context.getDriver().findElement(subSectionElementFinder("SSL", "verbiage")).getText()
							.contains(expected_verbiage)) {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Expected Verbiage: " + expected_verbiage);
				details.add("Actual Verbiage: "
						+ context.getDriver().findElement(subSectionElementFinder("SSL", "verbiage")).getText());
				extentReportService.insertPassedStep(context,
						"User sees the Security Section > SSL SubSection is correct", details);

				context.getExtentTestScenario().log(Status.PASS, "PASSED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			}

			else {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Expected Verbiage: " + expected_verbiage);
				details.add("Actual Verbiage: "
						+ context.getDriver().findElement(subSectionElementFinder("SSL", "verbiage")).getText());
				extentReportService.insertFailedStep(context,
						"User sees the Security Section > SSL SubSection is correct", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			}

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context,
						"User sees the Security Section > SSL SubSection is correct", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User sees the Security Section > Malware SubSection is correct")
	public void userSeesTheSecuritySectionMalwareSubSectionIsCorrect() throws IOException {

		try {
			// Step Definition
			String expected_verbiage = JsonPath.read(getWebAuditReportVerbiages(), "$.['Security']['Malware']['"
					+ context.getDriver().findElement(subSectionElementFinder("Malware", "verdict")).getText() + "']");

			if (context.getDriver().findElement(subSectionElementFinder("Malware", "verdict")).getText()
					.equals("Looking Good")
					&& context.getDriver().findElement(subSectionElementFinder("Malware", "verbiage")).getText()
							.contains(expected_verbiage)) {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Expected Verbiage: " + expected_verbiage);
				details.add("Actual Verbiage: "
						+ context.getDriver().findElement(subSectionElementFinder("Malware", "verbiage")).getText());
				extentReportService.insertPassedStep(context,
						"User sees the Security Section > Malware SubSection is correct", details);

				context.getExtentTestScenario().log(Status.PASS, "PASSED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			}

			else if (context.getDriver().findElement(subSectionElementFinder("Malware", "verdict")).getText()
					.equals("Critical")
					&& context.getDriver().findElement(subSectionElementFinder("Malware", "verbiage")).getText()
							.contains(expected_verbiage)) {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Expected Verbiage: " + expected_verbiage);
				details.add("Actual Verbiage: "
						+ context.getDriver().findElement(subSectionElementFinder("Malware", "verbiage")).getText());
				extentReportService.insertPassedStep(context,
						"User sees the Security Section > Malware SubSection is correct", details);

				context.getExtentTestScenario().log(Status.PASS, "PASSED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			}

			else if (context.getDriver().findElement(subSectionElementFinder("Malware", "verdict")).getText()
					.equals("N/A")
					&& context.getDriver().findElement(subSectionElementFinder("Malware", "verbiage")).getText()
							.contains(expected_verbiage)) {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Expected Verbiage: " + expected_verbiage);
				details.add("Actual Verbiage: "
						+ context.getDriver().findElement(subSectionElementFinder("Malware", "verbiage")).getText());
				extentReportService.insertPassedStep(context,
						"User sees the Security Section > Malware SubSection is correct", details);

				context.getExtentTestScenario().log(Status.PASS, "PASSED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			}

			else {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Expected Verbiage: " + expected_verbiage);
				details.add("Actual Verbiage: "
						+ context.getDriver().findElement(subSectionElementFinder("Malware", "verbiage")).getText());
				extentReportService.insertFailedStep(context,
						"User sees the Security Section > Malware SubSection is correct", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			}

			// Extent Report
			details.clear();
			details.add("Page URL: " + context.getDriver().getCurrentUrl());
			extentReportService.insertPassedStep(context,
					"User sees the Security Section > Malware SubSection is correct", details);

			context.getExtentTestScenario().log(Status.PASS, "PASSED");
			extentReportService.attachedScreenshotToReport(context,
					"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

		} catch (Exception e) {

			try {
				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context,
						"User sees the Security Section > Malware SubSection is correct", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User sees the Security Section > HTTPS SubSection is correct")
	public void userSeesTheSecuritySectionHTTPSSubSectionIsCorrect() throws IOException {

		String subSectionAnchor;

		try {
			// Step Definition
			if (context.getDriver().findElement(By.xpath("//div[@id='security-section']/div/div[4]/div[2]/p")).getText()
					.contains("HTTPS")) {
				subSectionAnchor = "HTTPS";
			} else {
				subSectionAnchor = "HTTP";
			}

			// validate verbiage against actual
			try {
				String expected_verbiage = JsonPath
						.read(getWebAuditReportVerbiages(),
								"$.['Security']['HTTPS']['" + context.getDriver()
										.findElement(subSectionElementFinder(subSectionAnchor, "verdict")).getText()
										+ "']");

				if (context.getDriver().findElement(subSectionElementFinder(subSectionAnchor, "verbiage")).getText()
						.contains(expected_verbiage)) {

					// Extent Report
					context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
							"User sees the Security Section > HTTPS SubSection is correct").pass("PASSED");

					// Extent Report
					details.clear();
					details.add("Page URL: " + context.getDriver().getCurrentUrl());
					details.add("Actual - verbiage: " + context.getDriver()
							.findElement(subSectionElementFinder(subSectionAnchor, "verbiage")).getText());
					details.add("Expected - verbiage: " + expected_verbiage);
					extentReportService.insertPassedStep(context,
							"User sees the Security Section > HTTPS SubSection is correct", details);

					context.getExtentTestScenario().log(Status.PASS, "PASSED");
					extentReportService.attachedScreenshotToReport(context,
							"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");
				}

				else {

					// Extent Report
					details.clear();
					details.add("Page URL: " + context.getDriver().getCurrentUrl());
					details.add("Actual - verbiage: " + context.getDriver()
							.findElement(subSectionElementFinder(subSectionAnchor, "verbiage")).getText());
					details.add("Expected - verbiage: " + expected_verbiage);
					extentReportService.insertFailedStep(context,
							"User sees the Security Section > HTTPS SubSection is correct", details);

					context.getExtentTestScenario().log(Status.FAIL, "FAILED");
					extentReportService.attachedScreenshotToReport(context,
							"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

				}

			} catch (Exception e) {
				JSONArray expected_verbiage = JsonPath
						.read(getWebAuditReportVerbiages(),
								"$.['Security']['HTTPS']['" + context.getDriver()
										.findElement(subSectionElementFinder(subSectionAnchor, "verdict")).getText()
										+ "']");

				// validate verbiage against actual
				boolean fail = true;
				for (int i = 0; i < expected_verbiage.size(); i++) {

					if (expected_verbiage.get(i).toString().equals(context.getDriver()
							.findElement(subSectionElementFinder(subSectionAnchor, "verbiage")).getText()))

					{

						// Extent Report
						details.clear();
						details.add("Page URL: " + context.getDriver().getCurrentUrl());
						details.add("Actual - verbiage: " + context.getDriver()
								.findElement(subSectionElementFinder(subSectionAnchor, "verbiage")).getText());
						details.add("Expected - verbiage: " + expected_verbiage);
						extentReportService.insertPassedStep(context,
								"User sees the Security Section > HTTPS SubSection is correct", details);

						context.getExtentTestScenario().log(Status.PASS, "PASSED");
						extentReportService.attachedScreenshotToReport(context,
								"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

						fail = false;

						// Exit While-Loop
						break;
					}

				}

				if (fail) {
					// Extent Report
					details.clear();
					details.add("Page URL: " + context.getDriver().getCurrentUrl());
					details.add("Actual - verbiage: " + context.getDriver()
							.findElement(subSectionElementFinder(subSectionAnchor, "verbiage")).getText());
					details.add("Expected - verbiage: " + expected_verbiage);
					extentReportService.insertFailedStep(context,
							"User sees the Security Section > HTTPS SubSection is correct", details);

					context.getExtentTestScenario().log(Status.FAIL, "FAILED");
					extentReportService.attachedScreenshotToReport(context,
							"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

				}

			}

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context,
						"User sees the Security Section > HTTPS SubSection is correct", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User sees the Security Section > Blacklisted SubSection is correct")
	public void userSeesTheSecuritySectionBlacklistedSubSectionIsCorrect() throws IOException {

		try {
			// Step Definition
			String expected_verbiage = JsonPath.read(getWebAuditReportVerbiages(), "$.['Security']['Blacklisted']['"
					+ context.getDriver().findElement(subSectionElementFinder("blacklisted", "verdict")).getText()
					+ "']");

			if (context.getDriver().findElement(subSectionElementFinder("blacklisted", "verdict")).getText()
					.equals("Looking Good")
					&& context.getDriver().findElement(subSectionElementFinder("blacklisted", "verbiage")).getText()
							.contains(expected_verbiage)) {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Expected Verbiage: " + expected_verbiage);
				details.add("Actual Verbiage: " + context.getDriver()
						.findElement(subSectionElementFinder("blacklisted", "verbiage")).getText());
				extentReportService.insertPassedStep(context,
						"User sees the Security Section > Blacklisted SubSection is correct", details);

				context.getExtentTestScenario().log(Status.PASS, "PASSED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			}

			else if (context.getDriver().findElement(subSectionElementFinder("blacklisted", "verdict")).getText()
					.equals("Critical")
					&& context.getDriver().findElement(subSectionElementFinder("blacklisted", "verbiage")).getText()
							.contains(expected_verbiage)) {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Expected Verbiage: " + expected_verbiage);
				details.add("Actual Verbiage: " + context.getDriver()
						.findElement(subSectionElementFinder("blacklisted", "verbiage")).getText());
				extentReportService.insertPassedStep(context,
						"User sees the Security Section > Blacklisted SubSection is correct", details);

				context.getExtentTestScenario().log(Status.PASS, "PASSED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			}

			else if (context.getDriver().findElement(subSectionElementFinder("blacklisted", "verdict")).getText()
					.equals("N/A")
					&& context.getDriver().findElement(subSectionElementFinder("blacklisted", "verbiage")).getText()
							.contains(expected_verbiage)) {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Expected Verbiage: " + expected_verbiage);
				details.add("Actual Verbiage: " + context.getDriver()
						.findElement(subSectionElementFinder("blacklisted", "verbiage")).getText());
				extentReportService.insertPassedStep(context,
						"User sees the Security Section > Blacklisted SubSection is correct", details);

				context.getExtentTestScenario().log(Status.PASS, "PASSED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			}

			else {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Expected Verbiage: " + expected_verbiage);
				details.add("Actual Verbiage: " + context.getDriver()
						.findElement(subSectionElementFinder("blacklisted", "verbiage")).getText());
				extentReportService.insertFailedStep(context,
						"User sees the Security Section > Blacklisted SubSection is correct", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			}

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context,
						"User sees the Security Section > Blacklisted SubSection is correct", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User scroll to Organic Traffic Section")
	public void userScrollToOrganicTrafficSection() {

		try {
			// document zooming
			context.getDriver().executeScript("document.body.style.zoom = '0.45'");

			context.getWait().until(ExpectedConditions.presenceOfElementLocated(SectionElemenFinder("security")));

			// scroll to pixel
			// context.getDriver().executeScript("window.scrollBy(0,2000)");

			// scroll to element
			context.getDriver().executeScript("arguments[0].scrollIntoView(false);",
					context.getDriver().findElement(SectionElemenFinder("security")));

			// Extent Report
			details.clear();
			details.add("Page URL: " + context.getDriver().getCurrentUrl());
			extentReportService.insertPassedStep(context, "User scroll to Organic Traffic Section", details);

			context.getExtentTestScenario().log(Status.PASS, "PASSED");
			extentReportService.attachedScreenshotToReport(context,
					"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context, "User scroll to Organic Traffic Section", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Then("User sees the Organic Traffic > Technical SEO > Sitemap SubSection is correct")
	public void userSeesTheOrganicTrafficSectionTechnicalSEOSitemapSubSectionIsCorrect() throws IOException {

		try {
			// Step Definition
			context.getWait()
					.until(ExpectedConditions.presenceOfElementLocated(subSectionElementFinder("Sitemap", "verdict")));

			context.getDriver().executeScript("arguments[0].scrollIntoView(true);",
					context.getDriver().findElement(subSectionElementFinder("blacklisted", "verdict")));

			String expected_verbiage = JsonPath.read(getWebAuditReportVerbiages(),
					"$.['Organic Traffic']['Technical SEO']['Sitemap']['"
							+ context.getDriver().findElement(subSectionElementFinder("Sitemap", "verdict")).getText()
							+ "']");

			if (context.getDriver().findElement(subSectionElementFinder("Sitemap", "verdict")).getText()
					.equals("Looking Good")
					&& context.getDriver().findElement(subSectionElementFinder("Sitemap", "verbiage")).getText()
							.contains(expected_verbiage)) {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Expected Verbiage: " + expected_verbiage);
				details.add("Actual Verbiage: "
						+ context.getDriver().findElement(subSectionElementFinder("Sitemap", "verbiage")).getText());
				extentReportService.insertPassedStep(context,
						"User sees the Organic Traffic > Technical SEO > Sitemap SubSection is correct", details);

				context.getExtentTestScenario().log(Status.PASS, "PASSED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			}

			else if (context.getDriver().findElement(subSectionElementFinder("Sitemap", "verdict")).getText()
					.equals("Critical")
					&& context.getDriver().findElement(subSectionElementFinder("Sitemap", "verbiage")).getText()
							.contains(expected_verbiage)) {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Expected Verbiage: " + expected_verbiage);
				details.add("Actual Verbiage: "
						+ context.getDriver().findElement(subSectionElementFinder("Sitemap", "verbiage")).getText());
				extentReportService.insertPassedStep(context,
						"User sees the Organic Traffic > Technical SEO > Sitemap SubSection is correct", details);

				context.getExtentTestScenario().log(Status.PASS, "PASSED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			}

			else if (context.getDriver().findElement(subSectionElementFinder("Sitemap", "verdict")).getText()
					.equals("N/A")
					&& context.getDriver().findElement(subSectionElementFinder("Sitemap", "verbiage")).getText()
							.contains(expected_verbiage)) {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Expected Verbiage: " + expected_verbiage);
				details.add("Actual Verbiage: "
						+ context.getDriver().findElement(subSectionElementFinder("Sitemap", "verbiage")).getText());
				extentReportService.insertPassedStep(context,
						"User sees the Organic Traffic > Technical SEO > Sitemap SubSection is correct", details);

				context.getExtentTestScenario().log(Status.PASS, "PASSED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			}

			else {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Expected Verbiage: " + expected_verbiage);
				details.add("Actual Verbiage: "
						+ context.getDriver().findElement(subSectionElementFinder("Sitemap", "verbiage")).getText());
				extentReportService.insertFailedStep(context,
						"User sees the Organic Traffic > Technical SEO > Sitemap SubSection is correct", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			}

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context,
						"User sees the Organic Traffic > Technical SEO > Sitemap SubSection is correct", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User sees the Organic Traffic > Technical SEO > Robot SubSection is correct")
	public void userSeesTheOrganicTrafficSectionTechnicalSEORobotSubSectionIsCorrect() throws IOException {

		try {
			// Step Definition
			context.getDriver().executeScript("arguments[0].scrollIntoView(true);",
					context.getDriver().findElement(subSectionElementFinder("blacklisted", "verdict")));

			context.getWait()
					.until(ExpectedConditions.presenceOfElementLocated(subSectionElementFinder("Robots", "verdict")));

			String expected_verbiage = JsonPath.read(getWebAuditReportVerbiages(),
					"$.['Organic Traffic']['Technical SEO']['Robot']['"
							+ context.getDriver().findElement(subSectionElementFinder("Robots", "verdict")).getText()
							+ "']");

			if (context.getDriver().findElement(subSectionElementFinder("Robots", "verdict")).getText()
					.equals("Looking Good")
					&& context.getDriver().findElement(subSectionElementFinder("Robots", "verbiage")).getText()
							.contains(expected_verbiage)) {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Expected Verbiage: " + expected_verbiage);
				details.add("Actual Verbiage: "
						+ context.getDriver().findElement(subSectionElementFinder("Robots", "verbiage")).getText());
				extentReportService.insertPassedStep(context,
						"User sees the Organic Traffic > Technical SEO > Robot SubSection is correct", details);

				context.getExtentTestScenario().log(Status.PASS, "PASSED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			}

			else if (context.getDriver().findElement(subSectionElementFinder("Robots", "verdict")).getText()
					.equals("Critical")
					&& context.getDriver().findElement(subSectionElementFinder("Robots", "verbiage")).getText()
							.contains(expected_verbiage)) {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Expected Verbiage: " + expected_verbiage);
				details.add("Actual Verbiage: "
						+ context.getDriver().findElement(subSectionElementFinder("Robots", "verbiage")).getText());
				extentReportService.insertPassedStep(context,
						"User sees the Organic Traffic > Technical SEO > Robot SubSection is correct", details);

				context.getExtentTestScenario().log(Status.PASS, "PASSED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			}

			else if (context.getDriver().findElement(subSectionElementFinder("Robots", "verdict")).getText()
					.equals("N/A")
					&& context.getDriver().findElement(subSectionElementFinder("Robots", "verbiage")).getText()
							.contains(expected_verbiage)) {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Expected Verbiage: " + expected_verbiage);
				details.add("Actual Verbiage: "
						+ context.getDriver().findElement(subSectionElementFinder("Robots", "verbiage")).getText());
				extentReportService.insertPassedStep(context,
						"User sees the Organic Traffic > Technical SEO > Robot SubSection is correct", details);

				context.getExtentTestScenario().log(Status.PASS, "PASSED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			}

			else {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Expected Verbiage: " + expected_verbiage);
				details.add("Actual Verbiage: "
						+ context.getDriver().findElement(subSectionElementFinder("Robots", "verbiage")).getText());
				extentReportService.insertFailedStep(context,
						"User sees the Organic Traffic > Technical SEO > Robot SubSection is correct", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			}

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context,
						"User sees the Organic Traffic > Technical SEO > Robot SubSection is correct", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User sees the Organic Traffic > Technical SEO > Redirection SubSection is correct")
	public void userSeesTheOrganicTrafficSectionTechnicalSEORedirectionSubSectionIsCorrect() throws IOException {

		try {
			// Step Definition
			context.getDriver().executeScript("arguments[0].scrollIntoView(true);",
					context.getDriver().findElement(subSectionElementFinder("blacklisted", "verdict")));

			context.getWait().until(
					ExpectedConditions.presenceOfElementLocated(subSectionElementFinder("redirection", "verdict")));

			String expected_verbiage = JsonPath.read(getWebAuditReportVerbiages(),
					"$.['Organic Traffic']['Technical SEO']['Redirection']['" + context.getDriver()
							.findElement(subSectionElementFinder("redirection", "verdict")).getText() + "']");

			if (context.getDriver().findElement(subSectionElementFinder("redirection", "verdict")).getText()
					.equals("Looking Good")
					&& context.getDriver().findElement(subSectionElementFinder("redirection", "verbiage")).getText()
							.contains(expected_verbiage)) {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Expected Verbiage: " + expected_verbiage);
				details.add("Actual Verbiage: " + context.getDriver()
						.findElement(subSectionElementFinder("redirection", "verbiage")).getText());
				extentReportService.insertPassedStep(context,
						"User sees the Organic Traffic > Technical SEO > Redirection SubSection is correct", details);

				context.getExtentTestScenario().log(Status.PASS, "PASSED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			}

			else if (context.getDriver().findElement(subSectionElementFinder("redirection", "verdict")).getText()
					.equals("Critical")
					&& context.getDriver().findElement(subSectionElementFinder("redirection", "verbiage")).getText()
							.contains(expected_verbiage)) {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Expected Verbiage: " + expected_verbiage);
				details.add("Actual Verbiage: " + context.getDriver()
						.findElement(subSectionElementFinder("redirection", "verbiage")).getText());
				extentReportService.insertPassedStep(context,
						"User sees the Organic Traffic > Technical SEO > Redirection SubSection is correct", details);

				context.getExtentTestScenario().log(Status.PASS, "PASSED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			}

			else if (context.getDriver().findElement(subSectionElementFinder("redirection", "verdict")).getText()
					.equals("N/A")
					&& context.getDriver().findElement(subSectionElementFinder("redirection", "verbiage")).getText()
							.contains(expected_verbiage)) {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Expected Verbiage: " + expected_verbiage);
				details.add("Actual Verbiage: " + context.getDriver()
						.findElement(subSectionElementFinder("redirection", "verbiage")).getText());
				extentReportService.insertPassedStep(context,
						"User sees the Organic Traffic > Technical SEO > Redirection SubSection is correct", details);

				context.getExtentTestScenario().log(Status.PASS, "PASSED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			}

			else {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Expected Verbiage: " + expected_verbiage);
				details.add("Actual Verbiage: " + context.getDriver()
						.findElement(subSectionElementFinder("redirection", "verbiage")).getText());
				extentReportService.insertFailedStep(context,
						"User sees the Organic Traffic > Technical SEO > Redirection SubSection is correct", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			}

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context,
						"User sees the Organic Traffic > Technical SEO > Redirection SubSection is correct", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User sees the Organic Traffic > OnPage SEO > Title SubSection is correct")
	public void userSeesTheOrganicTrafficSectionOnPageSEOTitleSubSectionIsCorrect() throws IOException {

		try {
			// Step Definition
			context.getDriver().executeScript("arguments[0].scrollIntoView(true);",
					context.getDriver().findElement(subSectionElementFinder("redirection", "verdict")));

			context.getWait()
					.until(ExpectedConditions.presenceOfElementLocated(subSectionElementFinder("title", "verdict")));

			try {

				String expected_verbiage = JsonPath.read(getWebAuditReportVerbiages(),
						"$.['Organic Traffic']['On-Page SEO']['Title']['"
								+ context.getDriver().findElement(subSectionElementFinder("title", "verdict")).getText()
								+ "']");

				// validate verbiage against actual
				if (context.getDriver().findElement(subSectionElementFinder("title", "verdict")).getText()
						.equals("For Improvement")
						&& context.getDriver().findElement(subSectionElementFinder("title", "verbiage")).getText()
								.contains(expected_verbiage)) {

					// Extent Report
					details.clear();
					details.add("Page URL: " + context.getDriver().getCurrentUrl());
					details.add("Expected Verbiage: " + expected_verbiage);
					details.add("Actual Verbiage: "
							+ context.getDriver().findElement(subSectionElementFinder("title", "verbiage")).getText());
					extentReportService.insertPassedStep(context,
							"User sees the Organic Traffic > OnPage SEO > Title SubSection is correct", details);

					context.getExtentTestScenario().log(Status.PASS, "PASSED");
					extentReportService.attachedScreenshotToReport(context,
							"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

				}

				else if (context.getDriver().findElement(subSectionElementFinder("title", "verdict")).getText()
						.equals("N/A")
						&& context.getDriver().findElement(subSectionElementFinder("title", "verbiage")).getText()
								.contains(expected_verbiage)) {

					// Extent Report
					details.clear();
					details.add("Page URL: " + context.getDriver().getCurrentUrl());
					details.add("Expected Verbiage: " + expected_verbiage);
					details.add("Actual Verbiage: "
							+ context.getDriver().findElement(subSectionElementFinder("title", "verbiage")).getText());
					extentReportService.insertPassedStep(context,
							"User sees the Organic Traffic > OnPage SEO > Title SubSection is correct", details);

					context.getExtentTestScenario().log(Status.PASS, "PASSED");
					extentReportService.attachedScreenshotToReport(context,
							"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

				}

				else {

					// Extent Report
					details.clear();
					details.add("Page URL: " + context.getDriver().getCurrentUrl());
					details.add("Expected Verbiage: " + expected_verbiage);
					details.add("Actual Verbiage: "
							+ context.getDriver().findElement(subSectionElementFinder("title", "verbiage")).getText());
					extentReportService.insertFailedStep(context,
							"User sees the Organic Traffic > OnPage SEO > Title SubSection is correct", details);

					context.getExtentTestScenario().log(Status.FAIL, "FAILED");
					extentReportService.attachedScreenshotToReport(context,
							"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

				}

			} catch (Exception e) {

				JSONArray expected_verbiageArray = JsonPath.read(getWebAuditReportVerbiages(),
						"$.['Organic Traffic']['On-Page SEO']['Title']['"
								+ context.getDriver().findElement(subSectionElementFinder("title", "verdict")).getText()
								+ "']");

				String expected_verbiage = "";

				if (context.getDriver().findElement(subSectionElementFinder("title", "verdict")).getText()
						.equals("Critical")) {

					// validate verbiage against actual
					for (int i = 0; i < expected_verbiageArray.size(); i++) {

						expected_verbiage = expected_verbiageArray.get(i).toString();

						if (context.getDriver().findElement(subSectionElementFinder("title", "verdict")).getText()
								.equals("For Improvement")
								&& context.getDriver().findElement(subSectionElementFinder("title", "verbiage"))
										.getText().equals(expected_verbiage)) {

							// Extent Report
							details.clear();
							details.add("Page URL: " + context.getDriver().getCurrentUrl());
							details.add("Expected Verbiage: " + expected_verbiage);
							details.add("Actual Verbiage: " + context.getDriver()
									.findElement(subSectionElementFinder("title", "verbiage")).getText());
							extentReportService.insertPassedStep(context,
									"User sees the Organic Traffic > OnPage SEO > Title SubSection is correct",
									details);

							context.getExtentTestScenario().log(Status.PASS, "PASSED");
							extentReportService.attachedScreenshotToReport(context,
									"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

							break;

						}
					}
				}

				else {

					// Extent Report
					details.clear();
					details.add("Page URL: " + context.getDriver().getCurrentUrl());
					details.add("Expected Verbiage: " + expected_verbiage);
					details.add("Actual Verbiage: "
							+ context.getDriver().findElement(subSectionElementFinder("title", "verbiage")).getText());
					extentReportService.insertFailedStep(context,
							"User sees the Organic Traffic > OnPage SEO > Title SubSection is correct", details);

					context.getExtentTestScenario().log(Status.FAIL, "FAILED");
					extentReportService.attachedScreenshotToReport(context,
							"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

				}

			}

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context,
						"User sees the Organic Traffic > OnPage SEO > Title SubSection is correct", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User sees the Organic Traffic > OnPage SEO > Description SubSection is correct")
	public void userSeesTheOrganicTrafficSectionOnPageSEODescriptionSubSectionIsCorrect() throws IOException {

		try {
			// Step Definition
			context.getDriver().executeScript("arguments[0].scrollIntoView(true);",
					context.getDriver().findElement(subSectionElementFinder("redirection", "verdict")));

			context.getWait().until(
					ExpectedConditions.presenceOfElementLocated(subSectionElementFinder("description", "verdict")));

			try {

				String expected_verbiage = JsonPath
						.read(getWebAuditReportVerbiages(),
								"$.['Organic Traffic']['On-Page SEO']['Description']['" + context.getDriver()
										.findElement(subSectionElementFinder("description", "verdict")).getText()
										+ "']");

				// validate verbiage against actual
				if (context.getDriver().findElement(subSectionElementFinder("description", "verdict")).getText()
						.equals("For Improvement")
						&& context.getDriver().findElement(subSectionElementFinder("description", "verbiage")).getText()
								.contains(expected_verbiage)) {

					// Extent Report
					details.clear();
					details.add("Page URL: " + context.getDriver().getCurrentUrl());
					details.add("Expected Verbiage: " + expected_verbiage);
					details.add("Actual Verbiage: " + context.getDriver()
							.findElement(subSectionElementFinder("description", "verbiage")).getText());
					extentReportService.insertPassedStep(context,
							"User sees the Organic Traffic > OnPage SEO > Description SubSection is correct", details);

					context.getExtentTestScenario().log(Status.PASS, "PASSED");
					extentReportService.attachedScreenshotToReport(context,
							"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

				}

				else if (context.getDriver().findElement(subSectionElementFinder("description", "verdict")).getText()
						.equals("N/A")
						&& context.getDriver().findElement(subSectionElementFinder("description", "verbiage")).getText()
								.contains(expected_verbiage)) {

					// Extent Report
					details.clear();
					details.add("Page URL: " + context.getDriver().getCurrentUrl());
					details.add("Expected Verbiage: " + expected_verbiage);
					details.add("Actual Verbiage: " + context.getDriver()
							.findElement(subSectionElementFinder("description", "verbiage")).getText());
					extentReportService.insertPassedStep(context,
							"User sees the Organic Traffic > OnPage SEO > Description SubSection is correct", details);

					context.getExtentTestScenario().log(Status.PASS, "PASSED");
					extentReportService.attachedScreenshotToReport(context,
							"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

				}

				else {

					// Extent Report
					details.clear();
					details.add("Page URL: " + context.getDriver().getCurrentUrl());
					details.add("Expected Verbiage: " + expected_verbiage);
					details.add("Actual Verbiage: " + context.getDriver()
							.findElement(subSectionElementFinder("description", "verbiage")).getText());
					extentReportService.insertFailedStep(context,
							"User sees the Organic Traffic > OnPage SEO > Description SubSection is correct", details);

					context.getExtentTestScenario().log(Status.FAIL, "FAILED");
					extentReportService.attachedScreenshotToReport(context,
							"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

				}

			} catch (Exception e) {

				JSONArray expected_verbiageArray = JsonPath
						.read(getWebAuditReportVerbiages(),
								"$.['Organic Traffic']['On-Page SEO']['Description']['" + context.getDriver()
										.findElement(subSectionElementFinder("description", "verdict")).getText()
										+ "']");

				String expected_verbiage = "";

				if (context.getDriver().findElement(subSectionElementFinder("description", "verdict")).getText()
						.equals("Critical")) {

					// validate verbiage against actual
					for (int i = 0; i < expected_verbiageArray.size(); i++) {

						expected_verbiage = expected_verbiageArray.get(i).toString();

						if (context.getDriver().findElement(subSectionElementFinder("description", "verdict")).getText()
								.equals("For Improvement")
								&& context.getDriver().findElement(subSectionElementFinder("description", "verbiage"))
										.getText().equals(expected_verbiage)) {

							// Extent Report
							details.clear();
							details.add("Page URL: " + context.getDriver().getCurrentUrl());
							details.add("Expected Verbiage: " + expected_verbiage);
							details.add("Actual Verbiage: " + context.getDriver()
									.findElement(subSectionElementFinder("description", "verbiage")).getText());
							extentReportService.insertPassedStep(context,
									"User sees the Organic Traffic > OnPage SEO > Description SubSection is correct",
									details);

							context.getExtentTestScenario().log(Status.PASS, "PASSED");
							extentReportService.attachedScreenshotToReport(context,
									"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

							break;

						}
					}
				}

				else {

					// Extent Report
					details.clear();
					details.add("Page URL: " + context.getDriver().getCurrentUrl());
					details.add("Expected Verbiage: " + expected_verbiage);
					details.add("Actual Verbiage: " + context.getDriver()
							.findElement(subSectionElementFinder("description", "verbiage")).getText());
					extentReportService.insertFailedStep(context,
							"User sees the Organic Traffic > OnPage SEO > Description SubSection is correct", details);

					context.getExtentTestScenario().log(Status.FAIL, "FAILED");
					extentReportService.attachedScreenshotToReport(context,
							"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

				}

			}

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context,
						"User sees the Organic Traffic > OnPage SEO > Description SubSection is correct", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User sees the Organic Traffic > OnPage SEO > Heading SubSection is correct")
	public void userSeesTheOrganicTrafficSectionOnPageSEOHeadingSubSectionIsCorrect() throws IOException {

		try {

			// Step Definition
			context.getDriver().executeScript("arguments[0].scrollIntoView(true);",
					context.getDriver().findElement(subSectionElementFinder("redirection", "verdict")));

			context.getWait()
					.until(ExpectedConditions.presenceOfElementLocated(subSectionElementFinder("headings", "verdict")));

			try {

				String expected_verbiage = JsonPath
						.read(getWebAuditReportVerbiages(),
								"$.['Organic Traffic']['On-Page SEO']['Headings']['"
										+ context.getDriver()
												.findElement(subSectionElementFinder("headings", "verdict")).getText()
										+ "']");

				// validate verbiage against actual
				if (context.getDriver().findElement(subSectionElementFinder("headings", "verdict")).getText()
						.equals("Critical")
						&& context.getDriver().findElement(subSectionElementFinder("headings", "verbiage")).getText()
								.contains(expected_verbiage)) {

					// Extent Report
					details.clear();
					details.add("Page URL: " + context.getDriver().getCurrentUrl());
					details.add("Expected Verbiage: " + expected_verbiage);
					details.add("Actual Verbiage: " + context.getDriver()
							.findElement(subSectionElementFinder("headings", "verbiage")).getText());
					extentReportService.insertPassedStep(context,
							"User sees the Organic Traffic > OnPage SEO > Heading SubSection is correct", details);

					context.getExtentTestScenario().log(Status.PASS, "PASSED");
					extentReportService.attachedScreenshotToReport(context,
							"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

				}

				else if (context.getDriver().findElement(subSectionElementFinder("headings", "verdict")).getText()
						.equals("N/A")
						&& context.getDriver().findElement(subSectionElementFinder("headings", "verbiage")).getText()
								.contains(expected_verbiage)) {

					// Extent Report
					details.clear();
					details.add("Page URL: " + context.getDriver().getCurrentUrl());
					details.add("Expected Verbiage: " + expected_verbiage);
					details.add("Actual Verbiage: " + context.getDriver()
							.findElement(subSectionElementFinder("headings", "verbiage")).getText());
					extentReportService.insertPassedStep(context,
							"User sees the Organic Traffic > OnPage SEO > Heading SubSection is correct", details);

					context.getExtentTestScenario().log(Status.PASS, "PASSED");
					extentReportService.attachedScreenshotToReport(context,
							"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

				}

				else {

					// Extent Report
					details.clear();
					details.add("Page URL: " + context.getDriver().getCurrentUrl());
					details.add("Expected Verbiage: " + expected_verbiage);
					details.add("Actual Verbiage: " + context.getDriver()
							.findElement(subSectionElementFinder("headings", "verbiage")).getText());
					extentReportService.insertFailedStep(context,
							"User sees the Organic Traffic > OnPage SEO > Heading SubSection is correct", details);

					context.getExtentTestScenario().log(Status.FAIL, "FAILED");
					extentReportService.attachedScreenshotToReport(context,
							"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

				}

			} catch (Exception e) {

				JSONArray expected_verbiageArray = JsonPath
						.read(getWebAuditReportVerbiages(),
								"$.['Organic Traffic']['On-Page SEO']['Headings']['"
										+ context.getDriver()
												.findElement(subSectionElementFinder("headings", "verdict")).getText()
										+ "']");

				String expected_verbiage = "";

				if (context.getDriver().findElement(subSectionElementFinder("headings", "verdict")).getText()
						.equals("For Improvement")) {

					// validate verbiage against actual
					for (int i = 0; i < expected_verbiageArray.size(); i++) {

						expected_verbiage = expected_verbiageArray.get(i).toString();

						if (context.getDriver().findElement(subSectionElementFinder("headings", "verdict")).getText()
								.equals("For Improvement")
								&& context.getDriver().findElement(subSectionElementFinder("headings", "verbiage"))
										.getText().equals(expected_verbiage)) {

							// Extent Report
							details.clear();
							details.add("Page URL: " + context.getDriver().getCurrentUrl());
							details.add("Expected Verbiage: " + expected_verbiage);
							details.add("Actual Verbiage: " + context.getDriver()
									.findElement(subSectionElementFinder("headings", "verbiage")).getText());
							extentReportService.insertPassedStep(context,
									"User sees the Organic Traffic > OnPage SEO > Heading SubSection is correct",
									details);

							context.getExtentTestScenario().log(Status.PASS, "PASSED");
							extentReportService.attachedScreenshotToReport(context,
									"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

							break;

						}
					}
				}

				else {

					// Extent Report
					details.clear();
					details.add("Page URL: " + context.getDriver().getCurrentUrl());
					details.add("Expected Verbiage: " + expected_verbiage);
					details.add("Actual Verbiage: " + context.getDriver()
							.findElement(subSectionElementFinder("headings", "verbiage")).getText());
					extentReportService.insertFailedStep(context,
							"User sees the Organic Traffic > OnPage SEO > Heading SubSection is correct", details);

					context.getExtentTestScenario().log(Status.FAIL, "FAILED");
					extentReportService.attachedScreenshotToReport(context,
							"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

				}

			}

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context,
						"User sees the Organic Traffic > OnPage SEO > Heading SubSection is correct", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User sees the Organic Traffic > OnPage SEO > Images SubSection is correct")
	public void userSeesTheOrganicTrafficSectionOnPageSEOImagesSubSectionIsCorrect() throws IOException {

		try {
			// Step Definition
			context.getDriver().executeScript("arguments[0].scrollIntoView(true);",
					context.getDriver().findElement(subSectionElementFinder("description", "verdict")));

			context.getWait()
					.until(ExpectedConditions.presenceOfElementLocated(subSectionElementFinder("Images", "verdict")));

			try {

				String expected_verbiage = JsonPath.read(getWebAuditReportVerbiages(),
						"$.['Organic Traffic']['On-Page SEO']['Images']['" + context.getDriver()
								.findElement(subSectionElementFinder("Images", "verdict")).getText() + "']");

				// validate verbiage against actual
				if (context.getDriver().findElement(subSectionElementFinder("Images", "verdict")).getText()
						.equals("N/A")
						&& context.getDriver().findElement(subSectionElementFinder("Images", "verbiage")).getText()
								.contains(expected_verbiage)) {

					// Extent Report
					details.clear();
					details.add("Page URL: " + context.getDriver().getCurrentUrl());
					details.add("Expected Verbiage: " + expected_verbiage);
					details.add("Actual Verbiage: "
							+ context.getDriver().findElement(subSectionElementFinder("Images", "verbiage")).getText());
					extentReportService.insertPassedStep(context,
							"User sees the Organic Traffic > OnPage SEO > Images SubSection is correct", details);

					context.getExtentTestScenario().log(Status.PASS, "PASSED");
					extentReportService.attachedScreenshotToReport(context,
							"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

				}

				else {

					// Extent Report
					details.clear();
					details.add("Page URL: " + context.getDriver().getCurrentUrl());
					details.add("Expected Verbiage: " + expected_verbiage);
					details.add("Actual Verbiage: "
							+ context.getDriver().findElement(subSectionElementFinder("Images", "verbiage")).getText());
					extentReportService.insertFailedStep(context,
							"User sees the Organic Traffic > OnPage SEO > Images SubSection is correct", details);

					context.getExtentTestScenario().log(Status.FAIL, "FAILED");
					extentReportService.attachedScreenshotToReport(context,
							"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

				}

			} catch (Exception e) {

				JSONArray expected_verbiageArray = JsonPath.read(getWebAuditReportVerbiages(),
						"$.['Organic Traffic']['On-Page SEO']['Images']['" + context.getDriver()
								.findElement(subSectionElementFinder("Images", "verdict")).getText() + "']");

				String expected_verbiage = "";

				if (context.getDriver().findElement(subSectionElementFinder("Images", "verdict")).getText()
						.equals("Critical")) {

					// validate verbiage against actual
					for (int i = 0; i < expected_verbiageArray.size(); i++) {

						expected_verbiage = expected_verbiageArray.get(i).toString();

						if (context.getDriver().findElement(subSectionElementFinder("Images", "verdict")).getText()
								.equals("For Improvement")
								&& context.getDriver().findElement(subSectionElementFinder("Images", "verbiage"))
										.getText().equals(expected_verbiage)) {

							// Extent Report
							details.clear();
							details.add("Page URL: " + context.getDriver().getCurrentUrl());
							details.add("Expected Verbiage: " + expected_verbiage);
							details.add("Actual Verbiage: " + context.getDriver()
									.findElement(subSectionElementFinder("Images", "verbiage")).getText());
							extentReportService.insertPassedStep(context,
									"User sees the Organic Traffic > OnPage SEO > Images SubSection is correct",
									details);

							context.getExtentTestScenario().log(Status.PASS, "PASSED");
							extentReportService.attachedScreenshotToReport(context,
									"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

							break;

						}
					}
				}

				else if (context.getDriver().findElement(subSectionElementFinder("Images", "verdict")).getText()
						.equals("For Improvement")) {

					// validate verbiage against actual
					for (int i = 0; i < expected_verbiageArray.size(); i++) {

						expected_verbiage = expected_verbiageArray.get(i).toString();

						if (context.getDriver().findElement(subSectionElementFinder("Images", "verdict")).getText()
								.equals("For Improvement")
								&& context.getDriver().findElement(subSectionElementFinder("Images", "verbiage"))
										.getText().equals(expected_verbiage)) {

							// Extent Report
							details.clear();
							details.add("Page URL: " + context.getDriver().getCurrentUrl());
							details.add("Expected Verbiage: " + expected_verbiage);
							details.add("Actual Verbiage: " + context.getDriver()
									.findElement(subSectionElementFinder("Images", "verbiage")).getText());
							extentReportService.insertPassedStep(context,
									"User sees the Organic Traffic > OnPage SEO > Images SubSection is correct",
									details);

							context.getExtentTestScenario().log(Status.PASS, "PASSED");
							extentReportService.attachedScreenshotToReport(context,
									"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

							break;

						}
					}
				}

				else {

					// Extent Report
					details.clear();
					details.add("Page URL: " + context.getDriver().getCurrentUrl());
					details.add("Expected Verbiage: " + expected_verbiage);
					details.add("Actual Verbiage: "
							+ context.getDriver().findElement(subSectionElementFinder("Images", "verbiage")).getText());
					extentReportService.insertFailedStep(context,
							"User sees the Organic Traffic > OnPage SEO > Images SubSection is correct", details);

					context.getExtentTestScenario().log(Status.FAIL, "FAILED");
					extentReportService.attachedScreenshotToReport(context,
							"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

				}

			}

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context,
						"User sees the Organic Traffic > OnPage SEO > Images SubSection is correct", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User sees the Organic Traffic > OffPage SEO > Backlinks SubSection is correct")
	public void userSeesTheOrganicTrafficSectionOffPageSEOBacklinksSubSectionIsCorrect() throws IOException {

		try {
			// Step Definition
			context.getDriver().executeScript("arguments[0].scrollIntoView(true);",
					context.getDriver().findElement(subSectionElementFinder("Images", "verdict")));

			context.getWait()
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(text(), 'Backlinks')]")));

			String expected_verbiage = JsonPath.read(getWebAuditReportVerbiages(),
					"$.['Organic Traffic']['Off-Page SEO']['Backlinks']['"
							+ context.getDriver().findElement(subSectionElementFinder("headings", "verdict")).getText()
							+ "']");

			// Assertion
			if (context.getDriver().findElement(By.xpath("//p[contains(text(), 'Backlinks')]")).getText()
					.contains(expected_verbiage)
					&& Pattern.matches("[0-9]+",
							context.getDriver().findElement(subSectionElementParentH3Finder("Backlinks")).getText())) {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Expected Verbiage: " + expected_verbiage);
				details.add("Actual Verbiage: "
						+ context.getDriver().findElement(By.xpath("//p[contains(text(), 'Backlinks')]")).getText());
				extentReportService.insertPassedStep(context,
						"User sees the Organic Traffic > OnPage SEO > Backlinks SubSection is correct", details);

				context.getExtentTestScenario().log(Status.PASS, "PASSED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");
			}

			else {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Expected Verbiage: " + expected_verbiage);
				details.add("Actual Verbiage: "
						+ context.getDriver().findElement(By.xpath("//p[contains(text(), 'Backlinks')]")).getText());
				extentReportService.insertFailedStep(context,
						"User sees the Organic Traffic > OnPage SEO > Backlinks SubSection is correct", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			}

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context,
						"User sees the Organic Traffic > OnPage SEO > Backlinks SubSection is correct", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User sees the Organic Traffic > OffPage SEO > MOZ Domain Authority SubSection is correct")
	public void userSeesTheOrganicTrafficSectionOffPageSEOMOZDomainAuthoritySubSectionIsCorrect() throws IOException {

		try {
			// Step Definition
			context.getDriver().executeScript("arguments[0].scrollIntoView(true);",
					context.getDriver().findElement(subSectionElementFinder("Images", "verdict")));

			context.getWait().until(ExpectedConditions
					.presenceOfElementLocated(subSectionElementFinder("MOZ Domain Authority", "verbiage")));

			JSONArray expected_verbiage = JsonPath.read(getWebAuditReportVerbiages(),
					"$.['Organic Traffic']['Off-Page SEO']['MOZ Domain Authority']");

			// validate verbiage against actual
			for (int i = 0; i < expected_verbiage.size(); i++) {

				if (expected_verbiage.get(i).toString().equals(context.getDriver()
						.findElement(subSectionElementFinder("MOZ Domain Authority", "verbiage")).getText())) {

					if (Pattern.matches("[0-9]+", context.getDriver()
							.findElement(subSectionElementParentH3Finder("MOZ Domain Authority")).getText())) {

						// Extent Report
						details.clear();
						details.add("Page URL: " + context.getDriver().getCurrentUrl());
						details.add("Expected Verbiage: " + expected_verbiage);
						details.add("Actual Verbiage: " + context.getDriver()
								.findElement(subSectionElementFinder("MOZ Domain Authority", "verbiage")).getText());
						extentReportService.insertPassedStep(context,
								"User sees the Organic Traffic > OnPage SEO > MOZ Domain Authority SubSection is correct",
								details);

						context.getExtentTestScenario().log(Status.PASS, "PASSED");
						extentReportService.attachedScreenshotToReport(context,
								"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

						break;

					} else {

						// Extent Report
						details.clear();
						details.add("Page URL: " + context.getDriver().getCurrentUrl());
						details.add("Expected Verbiage: " + expected_verbiage);
						details.add("Actual Verbiage: " + context.getDriver()
								.findElement(subSectionElementFinder("MOZ Domain Authority", "verbiage")).getText());
						details.add("Additional Details: "
								+ "MOZ Domain Authority count is NULL and/or MOZ Page Authority verbiage is incorrect");
						extentReportService.insertFailedStep(context,
								"User sees the Organic Traffic > OnPage SEO > MOZ Domain Authority SubSection is correct",
								details);

						context.getExtentTestScenario().log(Status.FAIL, "FAILED");
						extentReportService.attachedScreenshotToReport(context,
								"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

					}

				}
			}

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context,
						"User sees the Organic Traffic > OnPage SEO > MOZ Domain Authority SubSection is correct",
						details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User sees the Organic Traffic > OffPage SEO > MOZ Page Authority SubSection is correct")
	public void userSeesTheOrganicTrafficSectionOffPageSEOMOZPageAuthoritySubSectionIsCorrect() throws IOException {

		try {
			// Step Definition
			context.getDriver().executeScript("arguments[0].scrollIntoView(true);",
					context.getDriver().findElement(subSectionElementFinder("MOZ Page Authority", "verbiage")));

			context.getWait().until(ExpectedConditions
					.presenceOfElementLocated(subSectionElementFinder("MOZ Page Authority", "verbiage")));

			JSONArray expected_verbiage = JsonPath.read(getWebAuditReportVerbiages(),
					"$.['Organic Traffic']['Off-Page SEO']['MOZ Page Authority']");

			// validate verbiage against actual
			for (int i = 0; i < expected_verbiage.size(); i++) {

				if (expected_verbiage.get(i).toString().equals(context.getDriver()
						.findElement(subSectionElementFinder("MOZ Page Authority", "verbiage")).getText())) {

					if (Pattern.matches("[0-9]+", context.getDriver()
							.findElement(subSectionElementParentH3Finder("MOZ Page Authority")).getText())) {

						// Extent Report
						details.clear();
						details.add("Page URL: " + context.getDriver().getCurrentUrl());
						details.add("Expected Verbiage: " + expected_verbiage);
						details.add("Actual Verbiage: " + context.getDriver()
								.findElement(subSectionElementFinder("MOZ Page Authority", "verbiage")).getText());
						extentReportService.insertPassedStep(context,
								"User sees the Organic Traffic > OffPage SEO > MOZ Page Authority SubSection is correct",
								details);

						context.getExtentTestScenario().log(Status.PASS, "PASSED");
						extentReportService.attachedScreenshotToReport(context,
								"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

						break;
					} else {

						// Extent Report
						details.clear();
						details.add("Page URL: " + context.getDriver().getCurrentUrl());
						details.add("Expected Verbiage: " + expected_verbiage);
						details.add("Actual Verbiage: " + context.getDriver()
								.findElement(subSectionElementFinder("MOZ Page Authority", "verbiage")).getText());
						details.add("Additional Details: "
								+ "MOZ Page Authority count is NULL and/or MOZ Page Authority verbiage is incorrect");
						extentReportService.insertFailedStep(context,
								"User sees the Organic Traffic > OffPage SEO > MOZ Page Authority SubSection is correct",
								details);

						context.getExtentTestScenario().log(Status.FAIL, "FAILED");
						extentReportService.attachedScreenshotToReport(context,
								"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

					}

				}
			}

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context,
						"User sees the Organic Traffic > OffPage SEO > MOZ Page Authority SubSection is correct",
						details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User sees the Organic Traffic > OffPage SEO > Majestic Citation Flow SubSection is correct")
	public void userSeesTheOrganicTrafficSectionOffPageSEOMajesticCitationFlowSubSectionIsCorrect() throws IOException {

		try {
			// Step Definition
			context.getDriver().executeScript("arguments[0].scrollIntoView(true);",
					context.getDriver().findElement(subSectionElementFinder("Majestic Citation Flow", "verbiage")));

			context.getWait().until(ExpectedConditions
					.presenceOfElementLocated(subSectionElementFinder("Majestic Citation Flow", "verbiage")));

			JSONArray expected_verbiage = JsonPath.read(getWebAuditReportVerbiages(),
					"$.['Organic Traffic']['Off-Page SEO']['Majestic Citation Flow']");

			// validate verbiage against actual
			for (int i = 0; i < expected_verbiage.size(); i++) {

				if (expected_verbiage.get(i).toString().equals(context.getDriver()
						.findElement(subSectionElementFinder("Majestic Citation Flow", "verbiage")).getText())) {

					if (Pattern.matches("[0-9]+", context.getDriver()
							.findElement(subSectionElementParentH3Finder("Majestic Citation Flow")).getText())) {

						// Extent Report
						details.clear();
						details.add("Page URL: " + context.getDriver().getCurrentUrl());
						details.add("Expected Verbiage: " + expected_verbiage);
						details.add("Actual Verbiage: " + context.getDriver()
								.findElement(subSectionElementFinder("Majestic Citation Flow", "verbiage")).getText());
						extentReportService.insertPassedStep(context,
								"User sees the Organic Traffic > OffPage SEO > Majestic Citation Flow SubSection is correct",
								details);

						context.getExtentTestScenario().log(Status.PASS, "PASSED");
						extentReportService.attachedScreenshotToReport(context,
								"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

						break;
					} else {

						// Extent Report
						details.clear();
						details.add("Page URL: " + context.getDriver().getCurrentUrl());
						details.add("Expected Verbiage: " + expected_verbiage);
						details.add("Actual Verbiage: " + context.getDriver()
								.findElement(subSectionElementFinder("Majestic Citation Flow", "verbiage")).getText());
						details.add("Additional Details: "
								+ "Majestic Citation Flow count is NULL and/or MOZ Page Authority verbiage is incorrect");
						extentReportService.insertFailedStep(context,
								"User sees the Organic Traffic > OffPage SEO > Majestic Citation Flow SubSection is correct",
								details);

						context.getExtentTestScenario().log(Status.FAIL, "FAILED");
						extentReportService.attachedScreenshotToReport(context,
								"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");
					}

				}
			}

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context,
						"User sees the Organic Traffic > OffPage SEO > Majestic Citation Flow SubSection is correct",
						details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User sees the Organic Traffic > OffPage SEO > Majestic Trust Flow SubSection is correct")
	public void userSeesTheOrganicTrafficSectionOffPageSEOMajesticTrustFlowSubSectionIsCorrect() throws IOException {

		try {
			// Step Definition
			context.getDriver().executeScript("arguments[0].scrollIntoView(true);",
					context.getDriver().findElement(subSectionElementFinder("Majestic Citation Flow", "verbiage")));

			context.getWait().until(ExpectedConditions
					.presenceOfElementLocated(subSectionElementFinder("Majestic Trust Flow", "verbiage")));

			JSONArray expected_verbiage = JsonPath.read(getWebAuditReportVerbiages(),
					"$.['Organic Traffic']['Off-Page SEO']['Majestic Trust Flow']");

			// validate verbiage against actual
			for (int i = 0; i < expected_verbiage.size(); i++) {

				if (expected_verbiage.get(i).toString().equals(context.getDriver()
						.findElement(subSectionElementFinder("Majestic Trust Flow", "verbiage")).getText())) {

					if (Pattern.matches("[0-9]+", context.getDriver()
							.findElement(subSectionElementParentH3Finder("Majestic Trust Flow")).getText())) {

						// Extent Report
						details.clear();
						details.add("Page URL: " + context.getDriver().getCurrentUrl());
						details.add("Expected Verbiage: " + expected_verbiage);
						details.add("Actual Verbiage: " + context.getDriver()
								.findElement(subSectionElementFinder("Majestic Trust Flow", "verbiage")).getText());
						extentReportService.insertPassedStep(context,
								"User sees the Organic Traffic > OffPage SEO > Majestic Trust Flow SubSection is correct",
								details);

						context.getExtentTestScenario().log(Status.PASS, "PASSED");
						extentReportService.attachedScreenshotToReport(context,
								"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

						break;

					} else {

						// Extent Report
						details.clear();
						details.add("Page URL: " + context.getDriver().getCurrentUrl());
						details.add("Expected Verbiage: " + expected_verbiage);
						details.add("Actual Verbiage: " + context.getDriver()
								.findElement(subSectionElementFinder("Majestic Trust Flow", "verbiage")).getText());
						details.add("Additional Details: "
								+ "Majestic Trust Flow count is NULL and/or MOZ Page Authority verbiage is incorrect");
						extentReportService.insertFailedStep(context,
								"User sees the Organic Traffic > OffPage SEO > Majestic Trust Flow SubSection is correct",
								details);

						context.getExtentTestScenario().log(Status.FAIL, "FAILED");
						extentReportService.attachedScreenshotToReport(context,
								"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

					}

				}
			}

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context,
						"User sees the Organic Traffic > OffPage SEO > Majestic Trust Flow SubSection is correct",
						details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User scroll to Paid Traffic Section")
	public void userScrollToPaidTrafficSection() {

		try {
			// document zooming
			context.getDriver().executeScript("document.body.style.zoom = '0.75'");

			context.getWait().until(ExpectedConditions.presenceOfElementLocated(SectionElemenFinder("paid")));

			// scroll to pixel
			// context.getDriver().executeScript("window.scrollBy(0,2000)");

			// scroll to element
			context.getDriver().executeScript("arguments[0].scrollIntoView(false);",
					context.getDriver().findElement(SectionElemenFinder("paid")));

			// Thread.sleep(1000);

			// Extent Report
			details.clear();
			details.add("Page URL: " + context.getDriver().getCurrentUrl());
			extentReportService.insertPassedStep(context, "User scroll to Paid Traffic Section", details);

			context.getExtentTestScenario().log(Status.PASS, "PASSED");
			extentReportService.attachedScreenshotToReport(context,
					"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context, "User scroll to Paid Traffic Section", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Then("User sees the Paid Traffic > Estimated Traffic SubSection is correct")
	public void userSeesThePaidTrafficEstimatedTrafficSubSectionIsCorrect() throws IOException {

		try {
			// Step Definition
			String expected_verbiage = JsonPath.read(getWebAuditReportVerbiages(),
					"$.['Paid Traffic']['Estimated traffic']['N/A']");

			// validate verbiage against actual
			if (expected_verbiage.toString()
					.equals(context.getDriver().findElement(By.xpath("//p[contains(text(), 'Estimated')]")).getText())
					&& Pattern.matches("^\\d*.?\\d*K?$",
							context.getDriver().findElement(subSectionElementParentH3Finder("Estimated")).getText())) {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Expected Verbiage: " + expected_verbiage);
				details.add("Actual Verbiage: "
						+ context.getDriver().findElement(By.xpath("//p[contains(text(), 'Estimated')]")).getText());
				details.add("Actual Count: "
						+ context.getDriver().findElement(subSectionElementParentH3Finder("Estimated")).getText());
				extentReportService.insertPassedStep(context,
						"User sees the Paid Traffic > Estimated Traffic SubSection is correct", details);

				context.getExtentTestScenario().log(Status.PASS, "PASSED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			} else {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Expected Verbiage: " + expected_verbiage);
				details.add("Actual Verbiage: "
						+ context.getDriver().findElement(By.xpath("//p[contains(text(), 'Estimated')]")).getText());
				details.add("Actual Count: "
						+ context.getDriver().findElement(subSectionElementParentH3Finder("Estimated")).getText());
				details.add("Additional Details: "
						+ "Estimated Traffic count is NULL and/or MOZ Page Authority verbiage is incorrect");
				extentReportService.insertFailedStep(context,
						"User sees the Paid Traffic > Estimated Traffic SubSection is correct", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");
			}

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context,
						"User sees the Paid Traffic > Estimated Traffic SubSection is correct", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User sees the Paid Traffic > Keyword Detected SubSection is correct")
	public void userSeesThePaidTrafficKeywordDetectedSubSectionIsCorrect() throws IOException {

		try {
			// Step Definition
			String expected_verbiage = JsonPath.read(getWebAuditReportVerbiages(),
					"$.['Paid Traffic']['Keywords']['N/A']");

			// validate verbiage against actual

			if (expected_verbiage.toString()
					.equals(context.getDriver().findElement(By.xpath("//p[contains(text(), 'Keywords')]")).getText())
					&& Pattern.matches("[0-9]+",
							context.getDriver().findElement(subSectionElementParentH3Finder("Keywords")).getText())) {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Expected Verbiage: " + expected_verbiage);
				details.add("Actual Verbiage: "
						+ context.getDriver().findElement(By.xpath("//p[contains(text(), 'Estimated')]")).getText());
				details.add("Actual Count: "
						+ context.getDriver().findElement(subSectionElementParentH3Finder("Keywords")).getText());
				extentReportService.insertPassedStep(context,
						"User sees the Paid Traffic > Keyword Detected SubSection is correct", details);

				context.getExtentTestScenario().log(Status.PASS, "PASSED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");
			}

			else {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Expected Verbiage: " + expected_verbiage);
				details.add("Actual Verbiage: "
						+ context.getDriver().findElement(By.xpath("//p[contains(text(), 'Estimated')]")).getText());
				details.add("Actual Count: "
						+ context.getDriver().findElement(subSectionElementParentH3Finder("Keywords")).getText());
				details.add("Additional Details: "
						+ "Keyword Detected count is NULL and/or MOZ Page Authority verbiage is incorrect");
				extentReportService.insertFailedStep(context,
						"User sees the Paid Traffic > Keyword Detected SubSection is correct", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			}

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context,
						"User sees the Paid Traffic > Keyword Detected SubSection is correct", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User sees the Paid Traffic > Average Position SubSection is correct")
	public void userSeesThePaidTrafficAveragePositionSubSectionIsCorrect() throws IOException {

		// Step Definition
		try {

			try {

				System.out.println("======================one===============================");
				String expected_verbiage = JsonPath
						.read(getWebAuditReportVerbiages(),
								"$.['Paid Traffic']['Average Position']['"
										+ context.getDriver()
												.findElement(subSectionElementFinder("Position", "verdict")).getText()
										+ "']");

				if (expected_verbiage.equals(
						context.getDriver().findElement(subSectionElementFinder("Position", "verbiage")).getText())
						&& Pattern.matches("[0-9]+", context.getDriver()
								.findElement(subSectionElementParentH3Finder("Position")).getText())) {

					// Extent Report
					details.clear();
					details.add("Page URL: " + context.getDriver().getCurrentUrl());
					details.add("Expected Verbiage: " + expected_verbiage);
					details.add("Actual Verbiage: " + context.getDriver()
							.findElement(subSectionElementFinder("Position", "verbiage")).getText());
					details.add("Actual Count: "
							+ context.getDriver().findElement(subSectionElementParentH3Finder("Position")).getText());
					extentReportService.insertPassedStep(context,
							"User sees the Paid Traffic > Average Position SubSection is correct", details);

					context.getExtentTestScenario().log(Status.PASS, "PASSED");
					extentReportService.attachedScreenshotToReport(context,
							"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

				}

				else {

					// Extent Report
					details.clear();
					details.add("Page URL: " + context.getDriver().getCurrentUrl());
					details.add("Expected Verbiage: " + expected_verbiage);
					details.add("Actual Verbiage: " + context.getDriver()
							.findElement(subSectionElementFinder("Position", "verbiage")).getText());
					details.add("Actual Count: "
							+ context.getDriver().findElement(subSectionElementParentH3Finder("Position")).getText());
					extentReportService.insertFailedStep(context,
							"User sees the Paid Traffic > Average Position SubSection is correct", details);

					context.getExtentTestScenario().log(Status.FAIL, "FAILED");
					extentReportService.attachedScreenshotToReport(context,
							"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

				}

			} catch (Exception e) {
				System.out.println("======================four===============================");
				ArrayList<String> expected_verbiage = JsonPath
						.read(getWebAuditReportVerbiages(),
								"$.['Paid Traffic']['Average Position']['"
										+ context.getDriver()
												.findElement(subSectionElementFinder("Position", "verdict")).getText()
										+ "']");

				// validate verbiage against actual
				boolean fail = true;
				for (int i = 0; i < expected_verbiage.size(); i++) {

					if (expected_verbiage.get(i).equals(
							context.getDriver().findElement(subSectionElementFinder("Position", "verbiage")).getText())
							&& Pattern.matches("[0-9]+", context.getDriver()
									.findElement(subSectionElementParentH3Finder("Position")).getText())) {

						// Extent Report
						details.clear();
						details.add("Page URL: " + context.getDriver().getCurrentUrl());
						details.add("Expected Verbiage: " + expected_verbiage.get(i));
						details.add("Actual Verbiage: " + context.getDriver()
								.findElement(subSectionElementFinder("Position", "verbiage")).getText());
						details.add("Actual Count: " + context.getDriver()
								.findElement(subSectionElementParentH3Finder("Position")).getText());
						extentReportService.insertPassedStep(context,
								"User sees the Paid Traffic > Average Position SubSection is correct", details);

						context.getExtentTestScenario().log(Status.PASS, "PASSED");
						extentReportService.attachedScreenshotToReport(context,
								"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

						fail = false;

						// Exit While-Loop
						break;
					}

				}

				if (fail) {

					// Extent Report
					details.clear();
					details.add("Page URL: " + context.getDriver().getCurrentUrl());
					details.add("Expected Verbiage: " + expected_verbiage);
					details.add("Actual Verbiage: " + context.getDriver()
							.findElement(subSectionElementFinder("Position", "verbiage")).getText());
					details.add("Actual Count: "
							+ context.getDriver().findElement(subSectionElementParentH3Finder("Position")).getText());
					extentReportService.insertFailedStep(context,
							"User sees the Paid Traffic > Average Position SubSection is correct", details);

					context.getExtentTestScenario().log(Status.FAIL, "FAILED");
					extentReportService.attachedScreenshotToReport(context,
							"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

				}

			}
		}

		catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context,
						"User sees the Paid Traffic > Average Position SubSection is correct", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User sees the Paid Traffic > SEMvsSEO Ratio SubSection is correct")
	public void userSeesThePaidTrafficSEMvsSEORatioSubSectionIsCorrect() throws IOException {

		// Step Definition
		try {

			try {

				System.out.println("======================one===============================");
				String expected_verbiage = JsonPath.read(getWebAuditReportVerbiages(),
						"$.['Paid Traffic']['SEM vs SEO Ratio']['"
								+ context.getDriver().findElement(subSectionElementFinder("Ratio", "verdict")).getText()
								+ "']");

				if (expected_verbiage
						.equals(context.getDriver().findElement(subSectionElementFinder("Ratio", "verbiage")).getText())
						&& Pattern.matches("^\\d*.?\\d*%$",
								context.getDriver().findElement(subSectionElementParentH3Finder("Ratio")).getText())) {

					// Extent Report
					details.clear();
					details.add("Page URL: " + context.getDriver().getCurrentUrl());
					details.add("Expected Verbiage: " + expected_verbiage);
					details.add("Actual Verbiage: "
							+ context.getDriver().findElement(subSectionElementFinder("Ratio", "verbiage")).getText());
					details.add("Actual Count: "
							+ context.getDriver().findElement(subSectionElementParentH3Finder("Ratio")).getText());
					extentReportService.insertPassedStep(context,
							"User sees the Paid Traffic > Average Position SubSection is correct", details);

					context.getExtentTestScenario().log(Status.PASS, "PASSED");
					extentReportService.attachedScreenshotToReport(context,
							"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

				}

				else {

					// Extent Report
					details.clear();
					details.add("Page URL: " + context.getDriver().getCurrentUrl());
					details.add("Expected Verbiage: " + expected_verbiage);
					details.add("Actual Verbiage: "
							+ context.getDriver().findElement(subSectionElementFinder("Ratio", "verbiage")).getText());
					details.add("Actual Count: "
							+ context.getDriver().findElement(subSectionElementParentH3Finder("Ratio")).getText());
					extentReportService.insertFailedStep(context,
							"User sees the Paid Traffic > Average Position SubSection is correct", details);

					context.getExtentTestScenario().log(Status.FAIL, "FAILED");
					extentReportService.attachedScreenshotToReport(context,
							"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

				}

			} catch (Exception e) {
				System.out.println("======================four===============================");
				ArrayList<String> expected_verbiage = JsonPath.read(getWebAuditReportVerbiages(),
						"$.['Paid Traffic']['SEM vs SEO Ratio']['"
								+ context.getDriver().findElement(subSectionElementFinder("Ratio", "verdict")).getText()
								+ "']");

				// validate verbiage against actual
				boolean fail = true;
				for (int i = 0; i < expected_verbiage.size(); i++) {

					if (expected_verbiage.get(i).equals(
							context.getDriver().findElement(subSectionElementFinder("Ratio", "verbiage")).getText())
							&& Pattern.matches("^\\d+.\\d+%$", context.getDriver()
									.findElement(subSectionElementParentH3Finder("Ratio")).getText())) {

						// Extent Report
						details.clear();
						details.add("Page URL: " + context.getDriver().getCurrentUrl());
						details.add("Expected Verbiage: " + expected_verbiage);
						details.add("Actual Verbiage: " + context.getDriver()
								.findElement(subSectionElementFinder("Ratio", "verbiage")).getText());
						details.add("Actual Count: "
								+ context.getDriver().findElement(subSectionElementParentH3Finder("Ratio")).getText());
						extentReportService.insertPassedStep(context,
								"User sees the Paid Traffic > Average Position SubSection is correct", details);

						context.getExtentTestScenario().log(Status.PASS, "PASSED");
						extentReportService.attachedScreenshotToReport(context,
								"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

						fail = false;

						// Exit While-Loop
						break;
					}
				}

				if (fail) {

					// Extent Report
					details.clear();
					details.add("Page URL: " + context.getDriver().getCurrentUrl());
					details.add("Expected Verbiage: " + expected_verbiage);
					details.add("Actual Verbiage: "
							+ context.getDriver().findElement(subSectionElementFinder("Ratio", "verbiage")).getText());
					details.add("Actual Count: "
							+ context.getDriver().findElement(subSectionElementParentH3Finder("Ratio")).getText());
					extentReportService.insertFailedStep(context,
							"User sees the Paid Traffic > Average Position SubSection is correct", details);

					context.getExtentTestScenario().log(Status.FAIL, "FAILED");
					extentReportService.attachedScreenshotToReport(context,
							"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

				}

			}

		}

		catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context,
						"User sees the Paid Traffic > Average Position SubSection is correct", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User scroll to Social Activity Section")
	public void userScrollToSocialActivitySection() {

		try {
			// document zooming
			// context.getDriver().executeScript("document.body.style.zoom = '0.75'");

			context.getWait().until(ExpectedConditions.presenceOfElementLocated(SectionElemenFinder("social")));

			// scroll to pixel
			// context.getDriver().executeScript("window.scrollBy(0,2000)");

			// scroll to element
			context.getDriver().executeScript("arguments[0].scrollIntoView(false);",
					context.getDriver().findElement(SectionElemenFinder("social")));

			// Extent Report
			details.clear();
			details.add("Page URL: " + context.getDriver().getCurrentUrl());
			extentReportService.insertPassedStep(context, "User scroll to Social Activity Section", details);

			context.getExtentTestScenario().log(Status.PASS, "PASSED");
			extentReportService.attachedScreenshotToReport(context,
					"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context, "User scroll to Social Activity Section", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Then("User sees the Social Activity > Facebook SubSection is correct")
	public void userSeesTheSocialActivityFacebookSubSectionIsCorrect() throws IOException {

		// Step Definition

		try {

			if (context.getDriver().findElement(By.xpath("//div[@id='facebook']/div[1]/div[2]")).getText()
					.contains("No Facebook detected")
					&& context.getDriver().findElement(subSectionSocialElementFinder("facebook", "verdict")).getText()
							.equals("Critical")
					&& context.getDriver().findElement(subSectionSocialElementFinder("facebook", "verbiage")).getText()
							.equals("A Facebook page is a great way to reach and keep in contact with your customers.")) {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Actual-URL: "
						+ context.getDriver().findElement(By.xpath("//div[@id='twitter']/div[1]/div[1]")).getText());
				details.add("Actual-Verdict: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("facebook", "verdict")).getText());
				details.add("Actual-Verbiage: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("facebook", "verbiage")).getText());
				extentReportService.insertPassedStep(context,
						"User sees the Social Activity > Facebook SubSection is correct", details);

				context.getExtentTestScenario().log(Status.PASS, "PASSED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			}

			else if (Pattern.matches("^[a-z]+:\\/\\/[a-z]{3}.[a-z]+.[a-z]{3}\\/[a-zA-Z]+\\/?$",
					context.getDriver().findElement(By.xpath("//div[@id='facebook']/div[1]/div[2]")).getText())
					&& context.getDriver().findElement(subSectionSocialElementFinder("facebook", "verdict")).getText()
							.equals("Looking Good")
					&& Pattern.matches("^\\d*.?\\d*[A-Z]?$",
							context.getDriver().findElement(subSectionSocialElementFinder("facebook", "verbiagerow1"))
									.getText())
					&& Pattern.matches("^\\d*.?\\d*[A-Z]?$",
							context.getDriver().findElement(subSectionSocialElementFinder("facebook", "verbiagerow2"))
									.getText())
					&& Pattern.matches("^\\d*.?\\d*[A-Z]?$",
							context.getDriver().findElement(subSectionSocialElementFinder("facebook", "verbiagerow3"))
									.getText())
					&& Pattern.matches("^\\d*.?\\d*[A-Z]?$", context.getDriver()
							.findElement(subSectionSocialElementFinder("facebook", "verbiagerow4")).getText())) {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Actual-URL: "
						+ context.getDriver().findElement(By.xpath("//div[@id='facebook']/div[1]/div[2]")).getText());
				details.add("Actual-URLStatusCode: " + httpReqService.checkURLStatus200(
						context.getDriver().findElement(By.xpath("//div[@id='facebook']/div[1]/div[2]")).getText()));
				details.add("Actual-Verdict: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("facebook", "verdict")).getText());
				details.add("Actual-Likes: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("facebook", "verbiagerow1")).getText());
				details.add("Actual-Number of Post: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("facebook", "verbiagerow2")).getText());
				details.add("Actual-Average EngageMent: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("facebook", "verbiagerow3")).getText());
				details.add("Actual-Number of Post: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("facebook", "verbiagerow4")).getText());
				extentReportService.insertPassedStep(context,
						"User sees the Social Activity > Facebook SubSection is correct", details);

				context.getExtentTestScenario().log(Status.PASS, "PASSED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			}

			else if (Pattern.matches("^[a-z]+:\\/\\/[a-z]{3}.[a-z]+.[a-z]{3}\\/[a-zA-Z]+\\/?$",
					context.getDriver().findElement(By.xpath("//div[@id='facebook']/div[1]/div[2]")).getText())
					&& context.getDriver().findElement(subSectionSocialElementFinder("facebook", "verdict")).getText()
							.equals("For Improvement")
					&& Pattern.matches("^\\d*.?\\d*[A-Z]?$",
							context.getDriver().findElement(subSectionSocialElementFinder("facebook", "verbiagerow1"))
									.getText())
					&& Pattern.matches("^\\d*.?\\d*[A-Z]?$",
							context.getDriver().findElement(subSectionSocialElementFinder("facebook", "verbiagerow2"))
									.getText())
					&& Pattern.matches("^\\d*.?\\d*[A-Z]?$",
							context.getDriver().findElement(subSectionSocialElementFinder("facebook", "verbiagerow3"))
									.getText())
					&& Pattern.matches("^\\d*.?\\d*[A-Z]?$", context.getDriver()
							.findElement(subSectionSocialElementFinder("facebook", "verbiagerow4")).getText())) {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Actual-URL: "
						+ context.getDriver().findElement(By.xpath("//div[@id='facebook']/div[1]/div[2]")).getText());
				details.add("Actual-URLStatusCode: " + httpReqService.checkURLStatus200(
						context.getDriver().findElement(By.xpath("//div[@id='facebook']/div[1]/div[2]")).getText()));
				details.add("Actual-Verdict: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("facebook", "verdict")).getText());
				details.add("Actual-Likes: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("facebook", "verbiagerow1")).getText());
				details.add("Actual-Number of Post: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("facebook", "verbiagerow2")).getText());
				details.add("Actual-Average EngageMent: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("facebook", "verbiagerow3")).getText());
				details.add("Actual-Number of Post: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("facebook", "verbiagerow4")).getText());
				extentReportService.insertPassedStep(context,
						"User sees the Social Activity > Facebook SubSection is correct", details);

				context.getExtentTestScenario().log(Status.PASS, "PASSED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			}

			else if (Pattern.matches("^[a-z]+:\\/\\/[a-z]{3}.[a-z]+.[a-z]{3}\\/[a-zA-Z]+\\/?$",
					context.getDriver().findElement(By.xpath("//div[@id='facebook']/div[1]/div[2]")).getText())
					&& context.getDriver().findElement(subSectionSocialElementFinder("facebook", "verdict")).getText()
							.equals("Critical")
					&& Pattern.matches("^\\d*.?\\d*[A-Z]?$",
							context.getDriver().findElement(subSectionSocialElementFinder("facebook", "verbiagerow1"))
									.getText())
					&& Pattern.matches("^\\d*.?\\d*[A-Z]?$",
							context.getDriver().findElement(subSectionSocialElementFinder("facebook", "verbiagerow2"))
									.getText())
					&& Pattern.matches("^\\d*.?\\d*[A-Z]?$",
							context.getDriver().findElement(subSectionSocialElementFinder("facebook", "verbiagerow3"))
									.getText())
					&& Pattern.matches("^\\d*.?\\d*[A-Z]?$", context.getDriver()
							.findElement(subSectionSocialElementFinder("facebook", "verbiagerow4")).getText())) {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Actual-URL: "
						+ context.getDriver().findElement(By.xpath("//div[@id='facebook']/div[1]/div[2]")).getText());
				details.add("Actual-URLStatusCode: " + httpReqService.checkURLStatus200(
						context.getDriver().findElement(By.xpath("//div[@id='facebook']/div[1]/div[2]")).getText()));
				details.add("Actual-Verdict: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("facebook", "verdict")).getText());
				details.add("Actual-Likes: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("facebook", "verbiagerow1")).getText());
				details.add("Actual-Number of Post: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("facebook", "verbiagerow2")).getText());
				details.add("Actual-Average EngageMent: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("facebook", "verbiagerow3")).getText());
				details.add("Actual-Number of Post: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("facebook", "verbiagerow4")).getText());
				extentReportService.insertPassedStep(context,
						"User sees the Social Activity > Facebook SubSection is correct", details);

				context.getExtentTestScenario().log(Status.PASS, "PASSED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			}

			else if (!Pattern.matches("^[a-z]+:\\/\\/[a-z]{3}.[a-z]+.[a-z]{3}\\/[a-zA-Z]+\\/?$",
					context.getDriver().findElement(By.xpath("//div[@id='facebook']/div[1]/div[2]")).getText())
					&& context.getDriver().findElement(subSectionSocialElementFinder("facebook", "verdict")).getText()
							.equals("Looking Good")
					&& Pattern.matches("^\\d*.?\\d*[A-Z]?$",
							context.getDriver().findElement(subSectionSocialElementFinder("facebook", "verbiagerow1"))
									.getText())
					&& Pattern.matches("^\\d*.?\\d*[A-Z]?$",
							context.getDriver().findElement(subSectionSocialElementFinder("facebook", "verbiagerow2"))
									.getText())
					&& Pattern.matches("^\\d*.?\\d*[A-Z]?$",
							context.getDriver().findElement(subSectionSocialElementFinder("facebook", "verbiagerow3"))
									.getText())
					&& Pattern.matches("^\\d*.?\\d*[A-Z]?$", context.getDriver()
							.findElement(subSectionSocialElementFinder("facebook", "verbiagerow4")).getText())) {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Actual-URL: "
						+ context.getDriver().findElement(By.xpath("//div[@id='facebook']/div[1]/div[2]")).getText());
				details.add("Actual-URLStatusCode: " + httpReqService.checkURLStatus200(
						context.getDriver().findElement(By.xpath("//div[@id='facebook']/div[1]/div[2]")).getText()));
				details.add("Actual-Verdict: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("facebook", "verdict")).getText());
				details.add("Actual-Likes: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("facebook", "verbiagerow1")).getText());
				details.add("Actual-Number of Post: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("facebook", "verbiagerow2")).getText());
				details.add("Actual-Average EngageMent: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("facebook", "verbiagerow3")).getText());
				details.add("Actual-Number of Post: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("facebook", "verbiagerow4")).getText());
				extentReportService.insertFailedStep(context,
						"User sees the Social Activity > Facebook SubSection is correct", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			}

			else if (!Pattern.matches("^[a-z]+:\\/\\/[a-z]{3}.[a-z]+.[a-z]{3}\\/[a-zA-Z]+\\/?$",
					context.getDriver().findElement(By.xpath("//div[@id='facebook']/div[1]/div[2]")).getText())
					&& context.getDriver().findElement(subSectionSocialElementFinder("facebook", "verdict")).getText()
							.equals("For Improvement")
					&& Pattern.matches("^\\d*.?\\d*[A-Z]?$",
							context.getDriver().findElement(subSectionSocialElementFinder("facebook", "verbiagerow1"))
									.getText())
					&& Pattern.matches("^\\d*.?\\d*[A-Z]?$",
							context.getDriver().findElement(subSectionSocialElementFinder("facebook", "verbiagerow2"))
									.getText())
					&& Pattern.matches("^\\d*.?\\d*[A-Z]?$",
							context.getDriver().findElement(subSectionSocialElementFinder("facebook", "verbiagerow3"))
									.getText())
					&& Pattern.matches("^\\d*.?\\d*[A-Z]?$", context.getDriver()
							.findElement(subSectionSocialElementFinder("facebook", "verbiagerow4")).getText())) {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Actual-URL: "
						+ context.getDriver().findElement(By.xpath("//div[@id='facebook']/div[1]/div[2]")).getText());
				details.add("Actual-URLStatusCode: " + httpReqService.checkURLStatus200(
						context.getDriver().findElement(By.xpath("//div[@id='facebook']/div[1]/div[2]")).getText()));
				details.add("Actual-Verdict: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("facebook", "verdict")).getText());
				details.add("Actual-Likes: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("facebook", "verbiagerow1")).getText());
				details.add("Actual-Number of Post: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("facebook", "verbiagerow2")).getText());
				details.add("Actual-Average EngageMent: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("facebook", "verbiagerow3")).getText());
				details.add("Actual-Number of Post: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("facebook", "verbiagerow4")).getText());
				extentReportService.insertFailedStep(context,
						"User sees the Social Activity > Facebook SubSection is correct", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			}

			else if (!Pattern.matches("^[a-z]+:\\/\\/[a-z]{3}.[a-z]+.[a-z]{3}\\/[a-zA-Z]+\\/?$",
					context.getDriver().findElement(By.xpath("//div[@id='facebook']/div[1]/div[2]")).getText())
					&& context.getDriver().findElement(subSectionSocialElementFinder("facebook", "verdict")).getText()
							.equals("Critical")
					&& Pattern.matches("^\\d*.?\\d*[A-Z]?$",
							context.getDriver().findElement(subSectionSocialElementFinder("facebook", "verbiagerow1"))
									.getText())
					&& Pattern.matches("^\\d*.?\\d*[A-Z]?$",
							context.getDriver().findElement(subSectionSocialElementFinder("facebook", "verbiagerow2"))
									.getText())
					&& Pattern.matches("^\\d*.?\\d*[A-Z]?$",
							context.getDriver().findElement(subSectionSocialElementFinder("facebook", "verbiagerow3"))
									.getText())
					&& Pattern.matches("^\\d*.?\\d*[A-Z]?$", context.getDriver()
							.findElement(subSectionSocialElementFinder("facebook", "verbiagerow4")).getText())) {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Actual-URL: "
						+ context.getDriver().findElement(By.xpath("//div[@id='facebook']/div[1]/div[2]")).getText());
				details.add("Actual-URLStatusCode: " + httpReqService.checkURLStatus200(
						context.getDriver().findElement(By.xpath("//div[@id='facebook']/div[1]/div[2]")).getText()));
				details.add("Actual-Verdict: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("facebook", "verdict")).getText());
				details.add("Actual-Likes: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("facebook", "verbiagerow1")).getText());
				details.add("Actual-Number of Post: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("facebook", "verbiagerow2")).getText());
				details.add("Actual-Average EngageMent: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("facebook", "verbiagerow3")).getText());
				details.add("Actual-Number of Post: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("facebook", "verbiagerow4")).getText());
				extentReportService.insertFailedStep(context,
						"User sees the Social Activity > Facebook SubSection is correct", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			}

			else {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Actual-URL: "
						+ context.getDriver().findElement(By.xpath("//div[@id='facebook']/div[1]/div[2]")).getText());
				details.add("Actual-URLStatusCode: " + httpReqService.checkURLStatus200(
						context.getDriver().findElement(By.xpath("//div[@id='facebook']/div[1]/div[2]")).getText()));
				details.add("Actual-Verdict: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("facebook", "verdict")).getText());
				details.add("Actual-Likes: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("facebook", "verbiagerow1")).getText());
				details.add("Actual-Number of Post: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("facebook", "verbiagerow2")).getText());
				details.add("Actual-Average EngageMent: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("facebook", "verbiagerow3")).getText());
				details.add("Actual-Number of Post: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("facebook", "verbiagerow4")).getText());

				extentReportService.insertFailedStep(context,
						"User sees the Social Activity > Facebook SubSection is correct", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			}

		}

		catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context,
						"User sees the Social Activity > Facebook SubSection is correct", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User validates the Social Activity > Facebook SubSection > Data is pulled between {string} and {string}")
	public void UservalidatestheSocialActivityFacebookSubSectionDataispulledbetween(String fromDate, String toDate)
			throws Throwable {

		try {
			// Step Definition
			List<String[]> reportList = webAuditService.RetrieveGeneratedReportsBetweenAndFrom(fromDate, toDate,
					"PROD_CENTRAL");
			String[][] reportListArray = reportList.toArray(new String[0][]);
			String reportUrl, date_posted, websiteUrl = null;

			for (int i = 0; i < reportListArray.length; i++) {
				reportUrl = reportListArray[i][0];
				date_posted = reportListArray[i][1];
				websiteUrl = reportListArray[i][2];

				context.getDriver().get(reportUrl);
				Thread.sleep(10000);

				// scroll to element
				context.getDriver().executeScript("arguments[0].scrollIntoView(false);",
						context.getDriver().findElement(SectionElemenFinder("social")));
				Thread.sleep(5000);

				try {

					if (Pattern.matches("^[a-z]+:\\/\\/[a-z]+.[a-z]{3}\\/[a-zA-Z]+\\/?$",
							context.getDriver().findElement(subSectionSocialElementFinder("facebook", "verbiageHeader"))
									.getText())
							&& (context.getDriver()
									.findElement(subSectionSocialElementFinder("facebook", "verbiagerow1")).getText()
									.equals("0")
									|| context.getDriver()
											.findElement(subSectionSocialElementFinder("facebook", "verbiagerow2"))
											.getText().equals("0"))) {

						details.clear();
						details.add("WebSite Audited: " + websiteUrl);
						details.add("Report Link: " + reportUrl);
						details.add("Report Date: " + date_posted);
						extentReportService.insertFailedStep(context,
								"User validates the Social Activity > Facebook SubSection > Data is pulled between "
										+ fromDate + " and " + toDate,
								details);

						context.getExtentTestScenario().log(Status.FAIL, "Failed");

					}

					else if (!Pattern.matches("^[a-z]+:\\/\\/[a-z]+.[a-z]{3}\\/[a-zA-Z]+\\/?$",
							context.getDriver().findElement(subSectionSocialElementFinder("facebook", "verbiageHeader"))
									.getText())
							&& (context.getDriver()
									.findElement(subSectionSocialElementFinder("facebook", "verbiagerow1")).getText()
									.equals("0")
									|| context.getDriver()
											.findElement(subSectionSocialElementFinder("facebook", "verbiagerow2"))
											.getText().equals("0"))) {

						details.clear();
						details.add("WebSite Audited: " + websiteUrl);
						details.add("Report Link: " + reportUrl);
						details.add("Report Date: " + date_posted);
						extentReportService.insertFailedStep(context,
								"User validates the Social Activity > Facebook SubSection > Data is pulled between "
										+ fromDate + " and " + toDate,
								details);

						context.getExtentTestScenario().log(Status.FAIL, "Failed");

					}

					else {

						details.clear();
						details.add("WebSite Audited: " + websiteUrl);
						details.add("Report Link: " + reportUrl);
						details.add("Report Date: " + date_posted);
						extentReportService.insertPassedStep(context,
								"User validates the Social Activity > Facebook SubSection > Data is pulled between "
										+ fromDate + " and " + toDate,
								details);

						context.getExtentTestScenario().log(Status.PASS, "Passed");

					}

				} catch (Exception e) {

					System.err.println("Inner Catch" + reportUrl);

					if (context.getDriver().findElement(subSectionSocialElementFinder("facebook", "verbiageHeader"))
							.getText().equals("No Facebook detected")) {

						details.clear();
						details.add("WebSite Audited: " + websiteUrl);
						details.add("Report Link: " + reportUrl);
						details.add("Report Date: " + date_posted);
						extentReportService.insertWarningStep(context,
								"User validates the Social Activity > Facebook SubSection > Data is pulled between "
										+ fromDate + " and " + toDate,
								details);

						context.getExtentTestScenario().log(Status.WARNING, "Failed");

					} else {

						details.clear();
						details.add("WebSite Audited: " + websiteUrl);
						details.add("Report Link: " + reportUrl);
						details.add("Report Date: " + date_posted);
						extentReportService.insertFailedStep(context,
								"User validates the Social Activity > Facebook SubSection > Data is pulled between "
										+ fromDate + " and " + toDate,
								details);

						context.getExtentTestScenario().log(Status.FAIL, "Failed");
					}

				}

				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEOR/webAudit/UservalidatestheSocialActivityFacebookSubSectionDataispulledbetween.png?raw=true");
			}

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context,
						"User validates the Social Activity > Facebook SubSection > Data is pulled between " + fromDate
								+ " and " + toDate,
						details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEOR/webAudit/UservalidatestheSocialActivityFacebookSubSectionDataispulledbetween.png?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User sees the Social Activity > Twitter SubSection is correct")
	public void userSeesTheSocialActivityTwitterSubSectionIsCorrect() {

		// Step Definition
		try {

			// Validate that "No Twitter detected"
			// Validate that verdict and verbiage is correct
			if (context.getDriver().findElement(By.xpath("//div[@id='twitter']/div[1]/div[2]")).getText()
					.contains("No Twitter detected")
					&& context.getDriver().findElement(subSectionSocialElementFinder("twitter", "verdict")).getText()
							.equals("Critical")
					&& context.getDriver().findElement(subSectionSocialElementFinder("twitter", "verbiage")).getText()
							.equals("A twitter account is a great way to converse with your customers and leads in real time.")) {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Actual-URL: "
						+ context.getDriver().findElement(By.xpath("//div[@id='twitter']/div[1]/div[2]")).getText());
				details.add("Actual-Verdict: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("twitter", "verdict")).getText());
				details.add("Actual-Follower: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("twitter", "verbiage")).getText());
				extentReportService.insertPassedStep(context,
						"User sees the Social Activity > Twitter SubSection is correct", details);

				context.getExtentTestScenario().log(Status.PASS, "PASSED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			}

			// For Verdict = Looking Good
			// Validate the URL Pattern follows https://twitter.com/AlphaCharacter
			// Validate that twitter data is either a number (ex. 3000) or a number with
			// prefix (ex. 3K)
			else if (Pattern.matches("^[a-z]+:\\/\\/[a-z]+.[a-z]{3}\\/[a-zA-Z]+\\/?$",
					context.getDriver().findElement(By.xpath("//div[@id='twitter']/div[1]/div[2]")).getText())
					&& context.getDriver().findElement(subSectionSocialElementFinder("twitter", "verdict")).getText()
							.equals("Looking Good")
					&& Pattern.matches("^\\d*.?\\d*[A-Z]?$",
							context.getDriver().findElement(subSectionSocialElementFinder("twitter", "verbiagerow1"))
									.getText())
					&& Pattern.matches("^\\d*.?\\d*[A-Z]?$",
							context.getDriver().findElement(subSectionSocialElementFinder("twitter", "verbiagerow2"))
									.getText())
					&& Pattern.matches("^\\d*.?\\d*[A-Z]?$",
							context.getDriver().findElement(subSectionSocialElementFinder("twitter", "verbiagerow3"))
									.getText())
					&& Pattern.matches("^\\d*.?\\d*[A-Z]?$", context.getDriver()
							.findElement(subSectionSocialElementFinder("twitter", "verbiagerow4")).getText())) {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Actual-URL: "
						+ context.getDriver().findElement(By.xpath("//div[@id='twitter']/div[1]/div[2]")).getText());
				details.add("Actual-Verdict: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("twitter", "verdict")).getText());
				details.add("Actual-Follower: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("twitter", "verbiagerow1")).getText());
				details.add("Actual-Number of Tweets: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("twitter", "verbiagerow2")).getText());
				details.add("Actual-Average EngageMent: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("twitter", "verbiagerow3")).getText());
				details.add("Actual-Number of Tweets: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("twitter", "verbiagerow4")).getText());
				extentReportService.insertPassedStep(context,
						"User sees the Social Activity > Twitter SubSection is correct", details);

				context.getExtentTestScenario().log(Status.PASS, "PASSED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			}

			// For Verdict = For Improvement
			// Validate the URL Pattern follows https://twitter.com/AlphaCharacter
			// Validate that twitter data is either a number (ex. 3000) or a number with
			// prefix (ex. 3K)
			else if (Pattern.matches("^[a-z]+:\\/\\/[a-z]+.[a-z]{3}\\/[a-zA-Z]+\\/?$",
					context.getDriver().findElement(By.xpath("//div[@id='twitter']/div[1]/div[2]")).getText())
					&& context.getDriver().findElement(subSectionSocialElementFinder("twitter", "verdict")).getText()
							.equals("For Improvement")
					&& Pattern.matches("^\\d*.?\\d*[A-Z]?$",
							context.getDriver().findElement(subSectionSocialElementFinder("twitter", "verbiagerow1"))
									.getText())
					&& Pattern.matches("^\\d*.?\\d*[A-Z]?$",
							context.getDriver().findElement(subSectionSocialElementFinder("twitter", "verbiagerow2"))
									.getText())
					&& Pattern.matches("^\\d*.?\\d*[A-Z]?$",
							context.getDriver().findElement(subSectionSocialElementFinder("twitter", "verbiagerow3"))
									.getText())
					&& Pattern.matches("^\\d*.?\\d*[A-Z]?$", context.getDriver()
							.findElement(subSectionSocialElementFinder("twitter", "verbiagerow4")).getText())) {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Actual-URL: "
						+ context.getDriver().findElement(By.xpath("//div[@id='twitter']/div[1]/div[2]")).getText());
				details.add("Actual-Verdict: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("twitter", "verdict")).getText());
				details.add("Actual-Follower: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("twitter", "verbiagerow1")).getText());
				details.add("Actual-Number of Tweets: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("twitter", "verbiagerow2")).getText());
				details.add("Actual-Average EngageMent: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("twitter", "verbiagerow3")).getText());
				details.add("Actual-Number of Tweets: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("twitter", "verbiagerow4")).getText());
				extentReportService.insertPassedStep(context,
						"User sees the Social Activity > Twitter SubSection is correct", details);

				context.getExtentTestScenario().log(Status.PASS, "PASSED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			}

			// For Verdict = Critical
			// Validate the URL Pattern follows https://twitter.com/AlphaCharacter
			// Validate that twitter data is either a number (ex. 3000) or a number with
			// prefix (ex. 3K)
			else if (Pattern.matches("^[a-z]+:\\/\\/[a-z]+.[a-z]{3}\\/[a-zA-Z]+\\/?$",
					context.getDriver().findElement(By.xpath("//div[@id='twitter']/div[1]/div[2]")).getText())
					&& context.getDriver().findElement(subSectionSocialElementFinder("twitter", "verdict")).getText()
							.equals("Critical")
					&& Pattern.matches("^\\d*.?\\d*[A-Z]?$",
							context.getDriver().findElement(subSectionSocialElementFinder("twitter", "verbiagerow1"))
									.getText())
					&& Pattern.matches("^\\d*.?\\d*[A-Z]?$",
							context.getDriver().findElement(subSectionSocialElementFinder("twitter", "verbiagerow2"))
									.getText())
					&& Pattern.matches("^\\d*.?\\d*[A-Z]?$",
							context.getDriver().findElement(subSectionSocialElementFinder("twitter", "verbiagerow3"))
									.getText())
					&& Pattern.matches("^\\d*.?\\d*[A-Z]?$", context.getDriver()
							.findElement(subSectionSocialElementFinder("twitter", "verbiagerow4")).getText())) {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Actual-URL: "
						+ context.getDriver().findElement(By.xpath("//div[@id='twitter']/div[1]/div[2]")).getText());
				details.add("Actual-Verdict: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("twitter", "verdict")).getText());
				details.add("Actual-Follower: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("twitter", "verbiagerow1")).getText());
				details.add("Actual-Number of Tweets: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("twitter", "verbiagerow2")).getText());
				details.add("Actual-Average EngageMent: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("twitter", "verbiagerow3")).getText());
				details.add("Actual-Number of Tweets: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("twitter", "verbiagerow4")).getText());
				extentReportService.insertPassedStep(context,
						"User sees the Social Activity > Twitter SubSection is correct", details);

				context.getExtentTestScenario().log(Status.PASS, "PASSED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			}

			// For Verdict = Looking Good
			// Validate the URL Pattern do not follows https://twitter.com/AlphaCharacter
			// Validate that twitter data is either a number (ex. 3000) or a number with
			// prefix (ex. 3K)
			else if (!Pattern.matches("^[a-z]+:\\/\\/[a-z]+.[a-z]{3}\\/[a-zA-Z]+\\/?$",
					context.getDriver().findElement(By.xpath("//div[@id='twitter']/div[1]/div[2]")).getText())
					&& context.getDriver().findElement(subSectionSocialElementFinder("twitter", "verdict")).getText()
							.equals("Looking Good")
					&& Pattern.matches("^\\d*.?\\d*[A-Z]?$",
							context.getDriver().findElement(subSectionSocialElementFinder("twitter", "verbiagerow1"))
									.getText())
					&& Pattern.matches("^\\d*.?\\d*[A-Z]?$",
							context.getDriver().findElement(subSectionSocialElementFinder("twitter", "verbiagerow2"))
									.getText())
					&& Pattern.matches("^\\d*.?\\d*[A-Z]?$",
							context.getDriver().findElement(subSectionSocialElementFinder("twitter", "verbiagerow3"))
									.getText())
					&& Pattern.matches("^\\d*.?\\d*[A-Z]?$", context.getDriver()
							.findElement(subSectionSocialElementFinder("twitter", "verbiagerow4")).getText())) {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Actual-URL: "
						+ context.getDriver().findElement(By.xpath("//div[@id='twitter']/div[1]/div[2]")).getText());
				details.add("Actual-Verdict: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("twitter", "verdict")).getText());
				details.add("Actual-Follower: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("twitter", "verbiagerow1")).getText());
				details.add("Actual-Number of Tweets: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("twitter", "verbiagerow2")).getText());
				details.add("Actual-Average EngageMent: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("twitter", "verbiagerow3")).getText());
				details.add("Actual-Number of Tweets: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("twitter", "verbiagerow4")).getText());
				extentReportService.insertFailedStep(context,
						"User sees the Social Activity > Twitter SubSection is correct", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			}

			// For Verdict = For Improvement
			// Validate the URL Pattern do not follows https://twitter.com/AlphaCharacter
			// Validate that twitter data is either a number (ex. 3000) or a number with
			// prefix (ex. 3K)
			else if (!Pattern.matches("^[a-z]+:\\/\\/[a-z]+.[a-z]{3}\\/[a-zA-Z]+\\/?$",
					context.getDriver().findElement(By.xpath("//div[@id='twitter']/div[1]/div[2]")).getText())
					&& context.getDriver().findElement(subSectionSocialElementFinder("twitter", "verdict")).getText()
							.equals("For Improvement")
					&& Pattern.matches("^\\d*.?\\d*[A-Z]?$",
							context.getDriver().findElement(subSectionSocialElementFinder("twitter", "verbiagerow1"))
									.getText())
					&& Pattern.matches("^\\d*.?\\d*[A-Z]?$",
							context.getDriver().findElement(subSectionSocialElementFinder("twitter", "verbiagerow2"))
									.getText())
					&& Pattern.matches("^\\d*.?\\d*[A-Z]?$",
							context.getDriver().findElement(subSectionSocialElementFinder("twitter", "verbiagerow3"))
									.getText())
					&& Pattern.matches("^\\d*.?\\d*[A-Z]?$", context.getDriver()
							.findElement(subSectionSocialElementFinder("twitter", "verbiagerow4")).getText())) {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Actual-URL: "
						+ context.getDriver().findElement(By.xpath("//div[@id='twitter']/div[1]/div[2]")).getText());
				details.add("Actual-Verdict: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("twitter", "verdict")).getText());
				details.add("Actual-Follower: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("twitter", "verbiagerow1")).getText());
				details.add("Actual-Number of Tweets: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("twitter", "verbiagerow2")).getText());
				details.add("Actual-Average EngageMent: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("twitter", "verbiagerow3")).getText());
				details.add("Actual-Number of Tweets: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("twitter", "verbiagerow4")).getText());
				extentReportService.insertFailedStep(context,
						"User sees the Social Activity > Twitter SubSection is correct", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			}

			// For Verdict = For Improvement
			// Validate the URL Pattern do not follows https://twitter.com/AlphaCharacter
			// Validate that twitter data is either a number (ex. 3000) or a number with
			// prefix (ex. 3K)
			else if (!Pattern.matches("^[a-z]+:\\/\\/[a-z]+.[a-z]{3}\\/[a-zA-Z]+\\/?$",
					context.getDriver().findElement(By.xpath("//div[@id='twitter']/div[1]/div[2]")).getText())
					&& context.getDriver().findElement(subSectionSocialElementFinder("twitter", "verdict")).getText()
							.equals("Critical")
					&& Pattern.matches("^\\d*.?\\d*[A-Z]?$",
							context.getDriver().findElement(subSectionSocialElementFinder("twitter", "verbiagerow1"))
									.getText())
					&& Pattern.matches("^\\d*.?\\d*[A-Z]?$",
							context.getDriver().findElement(subSectionSocialElementFinder("twitter", "verbiagerow2"))
									.getText())
					&& Pattern.matches("^\\d*.?\\d*[A-Z]?$",
							context.getDriver().findElement(subSectionSocialElementFinder("twitter", "verbiagerow3"))
									.getText())
					&& Pattern.matches("^\\d*.?\\d*[A-Z]?$", context.getDriver()
							.findElement(subSectionSocialElementFinder("twitter", "verbiagerow4")).getText())) {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Actual-URL: "
						+ context.getDriver().findElement(By.xpath("//div[@id='twitter']/div[1]/div[2]")).getText());
				details.add("Actual-Verdict: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("twitter", "verdict")).getText());
				details.add("Actual-Follower: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("twitter", "verbiagerow1")).getText());
				details.add("Actual-Number of Tweets: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("twitter", "verbiagerow2")).getText());
				details.add("Actual-Average EngageMent: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("twitter", "verbiagerow3")).getText());
				details.add("Actual-Number of Tweets: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("twitter", "verbiagerow4")).getText());
				extentReportService.insertFailedStep(context,
						"User sees the Social Activity > Twitter SubSection is correct", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			}

			else {
				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Actual-URL: "
						+ context.getDriver().findElement(By.xpath("//div[@id='twitter']/div[1]/div[2]")).getText());
				details.add("Actual-Verdict: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("twitter", "verdict")).getText());
				details.add("Actual-Follower: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("twitter", "verbiagerow1")).getText());
				details.add("Actual-Number of Tweets: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("twitter", "verbiagerow2")).getText());
				details.add("Actual-Average EngageMent: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("twitter", "verbiagerow3")).getText());
				details.add("Actual-Number of Tweets: " + context.getDriver()
						.findElement(subSectionSocialElementFinder("twitter", "verbiagerow4")).getText());
				extentReportService.insertFailedStep(context,
						"User sees the Social Activity > Twitter SubSection is correct", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");
			}

		}

		catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context,
						"User sees the Paid Traffic > Keyword Detected SubSection is correct", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Then("User validates the Social Activity > Twitter SubSection > Data is pulled between {string} and {string}")
	public void UservalidatestheSocialActivityTwitterSubSectionDataispulledbetween(String fromDate, String toDate)
			throws Throwable {

		try {
			// Step Definition
			List<String[]> reportList = webAuditService.RetrieveGeneratedReportsBetweenAndFrom(fromDate, toDate,
					"PROD_CENTRAL");
			String[][] reportListArray = reportList.toArray(new String[0][]);
			String reportUrl, date_posted, websiteUrl = null;

			for (int i = 0; i < reportListArray.length; i++) {
				reportUrl = reportListArray[i][0];
				date_posted = reportListArray[i][1];
				websiteUrl = reportListArray[i][2];

				context.getDriver().get(reportUrl);
				Thread.sleep(10000);

				// scroll to element
				context.getDriver().executeScript("arguments[0].scrollIntoView(false);",
						context.getDriver().findElement(SectionElemenFinder("social")));
				Thread.sleep(5000);

				try {

					if (Pattern.matches("^[a-z]+:\\/\\/[a-z]+.[a-z]{3}\\/[a-zA-Z]+\\/?$",
							context.getDriver().findElement(subSectionSocialElementFinder("twitter", "verbiageHeader"))
									.getText())
							&& (context.getDriver()
									.findElement(subSectionSocialElementFinder("twitter", "verbiagerow1")).getText()
									.equals("0")
									|| context.getDriver()
											.findElement(subSectionSocialElementFinder("twitter", "verbiagerow2"))
											.getText().equals("0"))) {

						details.clear();
						details.add("WebSite Audited: " + websiteUrl);
						details.add("Report Link: " + reportUrl);
						details.add("Report Date: " + date_posted);
						extentReportService.insertFailedStep(context,
								"User validates the Social Activity > Twitter SubSection > Data is pulled between "
										+ fromDate + " and " + toDate,
								details);

						context.getExtentTestScenario().log(Status.FAIL, "Failed");

					}

					else if (!Pattern.matches("^[a-z]+:\\/\\/[a-z]+.[a-z]{3}\\/[a-zA-Z]+\\/?$",
							context.getDriver().findElement(subSectionSocialElementFinder("twitter", "verbiageHeader"))
									.getText())
							&& (context.getDriver()
									.findElement(subSectionSocialElementFinder("twitter", "verbiagerow1")).getText()
									.equals("0")
									|| context.getDriver()
											.findElement(subSectionSocialElementFinder("twitter", "verbiagerow2"))
											.getText().equals("0"))) {

						details.clear();
						details.add("WebSite Audited: " + websiteUrl);
						details.add("Report Link: " + reportUrl);
						details.add("Report Date: " + date_posted);
						extentReportService.insertWarningStep(context,
								"User validates the Social Activity > Twitter SubSection > Data is pulled between "
										+ fromDate + " and " + toDate,
								details);

						context.getExtentTestScenario().log(Status.WARNING, "Warning");

					}

					else {

						details.clear();
						details.add("WebSite Audited: " + websiteUrl);
						details.add("Report Link: " + reportUrl);
						details.add("Report Date: " + date_posted);
						extentReportService.insertPassedStep(context,
								"User validates the Social Activity > Twitter SubSection > Data is pulled between "
										+ fromDate + " and " + toDate,
								details);

						context.getExtentTestScenario().log(Status.PASS, "Pass");

					}

				} catch (Exception e) {

					System.err.println("Inner Catch" + reportUrl);

					if (context.getDriver().findElement(subSectionSocialElementFinder("twitter", "verbiageHeader"))
							.getText().equals("No Twitter detected")) {

						details.clear();
						details.add("WebSite Audited: " + websiteUrl);
						details.add("Report Link: " + reportUrl);
						details.add("Report Date: " + date_posted);
						extentReportService.insertWarningStep(context,
								"User validates the Social Activity > Twitter SubSection > Data is pulled between "
										+ fromDate + " and " + toDate,
								details);

						context.getExtentTestScenario().log(Status.WARNING, "Warning");

					} else {

						details.clear();
						details.add("WebSite Audited: " + websiteUrl);
						details.add("Report Link: " + reportUrl);
						details.add("Report Date: " + date_posted);
						extentReportService.insertFailedStep(context,
								"User validates the Social Activity > Twitter SubSection > Data is pulled between "
										+ fromDate + " and " + toDate,
								details);

						context.getExtentTestScenario().log(Status.FAIL, "Failed");
					}

				}

				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEOR/webAudit/UservalidatestheSocialActivityTwitterSubSectionDataispulledbetween.png?raw=true");
			}

		} catch (Exception e) {

			System.err.println("Outer Catch");

			// Extent Report
			try {

				details.clear();
				details.add(e.getMessage());
				extentReportService.insertFailedStep(context,
						"User validates the Social Activity > Twitter SubSection > Data is pulled between " + fromDate
								+ " and " + toDate,
						details);

				context.getExtentTestScenario().log(Status.FAIL, "Failed");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEOR/webAudit/UservalidatestheSocialActivityTwitterSubSectionDataispulledbetween.png?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

}
