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
			context.getSoftAssert()
					.assertEquals(context.getDriver().findElement(summarySection_usability).isDisplayed(), true);
			context.getSoftAssert().assertEquals(
					context.getDriver().findElement(summarySection_usability_score).getText().isEmpty(), false);

			// Extent Report
			context.getExtentTestScenario().createNode(new GherkinKeyword("Then"),
					"User sees the Summary Section > Grades SubSection is correct").pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Summary Section > Grades SubSection is correct")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
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
			context.getSoftAssert().assertEquals(
					context.getDriver().findElement(summarySection_webSiteScore_alpha).isDisplayed(), true);
			context.getSoftAssert().assertEquals(
					context.getDriver().findElement(summarySection_webSiteScore_alpha).getText().isEmpty(), false);
			context.getSoftAssert().assertEquals(
					context.getDriver().findElement(summarySection_webSiteScore_percentage).isDisplayed(), true);
			context.getSoftAssert().assertEquals(
					context.getDriver().findElement(summarySection_webSiteScore_percentage).getText().isEmpty(), false);

			// Extent Report
			context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
					"User sees the Summary Section > WebSite Score SubSection is correct").pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Summary Section > WebSite Score SubSection is correct")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
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
			// Critical
			// ============================
			context.getSoftAssert()
					.assertEquals(context.getDriver().findElement(summarySection_verdict_critical).isDisplayed(), true);
			context.getSoftAssert().assertEquals(
					context.getDriver().findElement(summarySection_verdict_criticalScore).isDisplayed(), true);
			context.getSoftAssert().assertEquals(
					context.getDriver().findElement(summarySection_verdict_criticalScore).getText().isEmpty(), false);

			// For Improvement
			// ============================
			context.getSoftAssert().assertEquals(
					context.getDriver().findElement(summarySection_verdict_forImprovement).isDisplayed(), true);
			context.getSoftAssert().assertEquals(
					context.getDriver().findElement(summarySection_verdict_forImprovementScore).isDisplayed(), true);
			context.getSoftAssert().assertEquals(
					context.getDriver().findElement(summarySection_verdict_forImprovementScore).getText().isEmpty(),
					false);

			// Looking Good
			// ============================
			context.getSoftAssert().assertEquals(
					context.getDriver().findElement(summarySection_verdict_lookingGood).isDisplayed(), true);
			context.getSoftAssert().assertEquals(
					context.getDriver().findElement(summarySection_verdict_lookingGoodScore).isDisplayed(), true);
			context.getSoftAssert().assertEquals(
					context.getDriver().findElement(summarySection_verdict_lookingGoodScore).getText().isEmpty(),
					false);

			// Extent Report
			context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
					"User sees the Summary Section > Verdict Table SubSection is correct").pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Summary Section > Verdict Table SubSection is correct")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
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

			context.getDriver().executeScript("arguments[0].scrollIntoView(true);",
					context.getDriver().findElement(subSectionElementFinder("Mobile Friendliness", "verdict")));

			String expected_verbiage = JsonPath
					.read(getWebAuditReportVerbiages(),
							"$.['Usability']['Mobile Friendliness']['" + context.getDriver()
									.findElement(subSectionElementFinder("Mobile Friendliness", "verdict")).getText()
									+ "']");

			context.getSoftAssert()
					.assertEquals(context.getDriver()
							.findElement(subSectionElementFinder("Mobile Friendliness", "verbiage")).getText(),
							expected_verbiage);

			// Extent Report
			context.getExtentTestScenario()
					.createNode(new GherkinKeyword("When"),
							"User sees the Usability Section > Mobile Friendliness SubSection is correct")
					.pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Usability Section > Mobile Friendliness SubSection is correct")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
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

				context.getDriver().executeScript("arguments[0].scrollIntoView(true);",
						context.getDriver().findElement(subSectionElementFinder("Google Analytics", "verdict")));

				// Get value from JSON based on rating -- Looking Good or Critical
				expected_verbiage = JsonPath.read(getWebAuditReportVerbiages(),
						"$.['Usability']['Google Analytics']['"
								+ context.getDriver()
										.findElement(subSectionElementFinder("Google Analytics", "verdict")).getText()
								+ "']");

				// Validate verbiage from JSON against WebAudit Report -- Looking Good or
				// Critical
				switch (context.getDriver().findElement(subSectionElementFinder("Google Analytics", "verdict"))
						.getText())

				{
				case "Looking Good":
					context.getSoftAssert()
							.assertEquals(context.getDriver()
									.findElement(subSectionElementFinder("Google Analytics", "verbiage")).getText()
									.contains(expected_verbiage), true);
					break;

				case "Critical":
					context.getSoftAssert()
							.assertEquals(context.getDriver()
									.findElement(subSectionElementFinder("Google Analytics", "verbiage")).getText()
									.contains(expected_verbiage), true);

					break;

				}
			} catch (Exception e) {

				// Check if Rating is available in report
				context.getWait().until(ExpectedConditions
						.presenceOfElementLocated(subSectionElementFinder("data could not be retrieved ", "verdict")));

				// Get value from JSON based on rating -- Looking Good or Critical
				expected_verbiage = JsonPath.read(getWebAuditReportVerbiages(),
						"$.['Usability']['Google Analytics']['N/A']");

				context.getSoftAssert()
						.assertEquals(context.getDriver()
								.findElement(subSectionElementFinder("data could not be retrieved", "verbiage"))
								.getText().contains(expected_verbiage), true);

			}

			// Extent Report
			context.getExtentTestScenario()
					.createNode(new GherkinKeyword("When"),
							"User sees the Usability Section > Google Analytics Found SubSection is correct")
					.pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Usability Section > Google Analytics Found SubSection is correct")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
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

			context.getDriver().executeScript("arguments[0].scrollIntoView(true);",
					context.getDriver().findElement(subSectionElementFinder("website speed", "verdict")));

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
					context.getSoftAssert()
							.assertEquals(context.getDriver()
									.findElement(subSectionElementFinder("website speed", "verbiage")).getText(),
									expected_verbiage.get(i).toString());
					break;
				}
			}

			// Extent Report
			context.getExtentTestScenario()
					.createNode(new GherkinKeyword("When"),
							"User sees the Usability Section > Page Speed Insight SubSection is correct")
					.pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Usability Section > Page Speed Insight SubSection is correct")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
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
			context.getExtentTestScenario().createNode(new GherkinKeyword("When"), "User scroll to Security Section")
					.pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User scroll to Security Section")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
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

			context.getSoftAssert().assertEquals(context.getDriver()
					.findElement(subSectionElementFinder("SSL", "verbiage")).getText().contains(expected_verbiage),
					true);

			// Extent Report
			context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
					"User sees the Security Section > SSL SubSection is correct").pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Security Section > SSL SubSection is correct")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
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

			context.getSoftAssert().assertEquals(context.getDriver()
					.findElement(subSectionElementFinder("Malware", "verbiage")).getText().contains(expected_verbiage),
					true);

			// Extent Report
			context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
					"User sees the Security Section > Malware SubSection is correct").pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Security Section > Malware SubSection is correct")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
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

					context.getSoftAssert()
							.assertEquals(context.getDriver()
									.findElement(subSectionElementFinder(subSectionAnchor, "verbiage")).getText()
									.contains(expected_verbiage), true);

					// Extent Report
					context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
							"User sees the Security Section > HTTPS SubSection is correct").pass("PASSED");
				}

				else {

					// Extent Report
					context.getExtentTestScenario()
							.createNode(new GherkinKeyword("When"),
									"User sees the Security Section > HTTPS SubSection is correct")
							.fail("FAILED: " + "<br>" + "Actual - verbiage: " + context.getDriver()
									.findElement(subSectionElementFinder(subSectionAnchor, "verbiage")).getText()
									+ "<br>" + "Expected - verbiage: " + expected_verbiage);
					context.getExtentTestScenario().log(Status.FAIL, "Failed");

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
						context.getSoftAssert()
								.assertEquals(context.getDriver()
										.findElement(subSectionElementFinder(subSectionAnchor, "verbiage")).getText()
										.contains(expected_verbiage.get(i).toString()), true);

						// Extent Report
						context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
								"User sees the Security Section > HTTPS SubSection is correct").pass("PASSED");

						fail = false;

						// Exit While-Loop
						break;
					}

				}

				if (fail) {
					// Extent Report
					context.getExtentTestScenario()
							.createNode(new GherkinKeyword("When"),
									"User sees the Security Section > HTTPS SubSection is correct")
							.fail("FAILED: " + "<br>" + "Actual - verbiage: " + context.getDriver()
									.findElement(subSectionElementFinder(subSectionAnchor, "verbiage")).getText()
									+ "<br>" + "Expected - verbiage: " + expected_verbiage);
					context.getExtentTestScenario().log(Status.FAIL, "Failed");

				}

			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Security Section > HTTPS SubSection is correct")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
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

			context.getSoftAssert()
					.assertEquals(context.getDriver().findElement(subSectionElementFinder("blacklisted", "verbiage"))
							.getText().contains(expected_verbiage), true);

			// Extent Report
			context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
					"User sees the Security Section > Blacklisted SubSection is correct").pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Security Section > Blacklisted SubSection is correct")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
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
			context.getExtentTestScenario()
					.createNode(new GherkinKeyword("When"), "User scroll to Organic Traffic Section").pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User scroll to Organic Traffic Section")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
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

			context.getSoftAssert().assertEquals(
					context.getDriver().findElement(subSectionElementFinder("Sitemap", "verbiage")).getText()
							.contains(expected_verbiage),
					true, "User sees the Organic Traffic > Technical SEO > Sitemap SubSection is correct");

			// Extent Report
			context.getExtentTestScenario()
					.createNode(new GherkinKeyword("When"),
							"User sees the Organic Traffic > Technical SEO > Sitemap SubSection is correct")
					.pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Organic Traffic > Technical SEO > Sitemap SubSection is correct")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
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

			context.getSoftAssert().assertEquals(
					context.getDriver().findElement(subSectionElementFinder("Robots", "verbiage")).getText()
							.contains(expected_verbiage),
					true, "User sees the Organic Traffic > Technical SEO > Robot SubSection is correct ---");

			// Extent Report
			context.getExtentTestScenario()
					.createNode(new GherkinKeyword("When"),
							"User sees the Organic Traffic > Technical SEO > Robot SubSection is correct")
					.pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Organic Traffic > Technical SEO > Robot SubSection is correct")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
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

			context.getSoftAssert().assertEquals(
					context.getDriver().findElement(subSectionElementFinder("redirection", "verbiage")).getText()
							.contains(expected_verbiage),
					true, "User sees the Organic Traffic > Technical SEO > Redirection SubSection is correct ---");

			// Extent Report
			context.getExtentTestScenario()
					.createNode(new GherkinKeyword("When"),
							"User sees the Organic Traffic > Technical SEO > Redirection SubSection is correct")
					.pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Organic Traffic > Technical SEO > Redirection SubSection is correct")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
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
					.until(ExpectedConditions.presenceOfElementLocated(subSectionElementFinder("itle", "verdict")));

			try {
				String expected_verbiage = JsonPath.read(getWebAuditReportVerbiages(),
						"$.['Organic Traffic']['On-Page SEO']['Title']['"
								+ context.getDriver().findElement(subSectionElementFinder("itle", "verdict")).getText()
								+ "']");

				// validate verbiage against actual
				context.getSoftAssert().assertEquals(context.getDriver()
						.findElement(subSectionElementFinder("itle", "verbiage")).getText().contains(expected_verbiage),
						true);

			} catch (Exception e) {
				JSONArray expected_verbiage = JsonPath.read(getWebAuditReportVerbiages(),
						"$.['Organic Traffic']['On-Page SEO']['Title']['"
								+ context.getDriver().findElement(subSectionElementFinder("itle", "verdict")).getText()
								+ "']");

				// validate verbiage against actual
				for (int i = 0; i < expected_verbiage.size(); i++) {

					if (expected_verbiage.get(i).toString().equals(
							context.getDriver().findElement(subSectionElementFinder("itle", "verbiage")).getText()))
						;
					{
						context.getSoftAssert().assertEquals(
								context.getDriver().findElement(subSectionElementFinder("itle", "verbiage")).getText()
										.contains(expected_verbiage.get(i).toString()),
								true);

						break;
					}
				}
			}

			// Extent Report
			context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
					"User sees the Organic Traffic > OnPage SEO > Title SubSection is correct").pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Organic Traffic > OnPage SEO > Title SubSection is correct")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
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
				context.getSoftAssert().assertEquals(
						context.getDriver().findElement(subSectionElementFinder("description", "verbiage")).getText()
								.contains(expected_verbiage),
						true);

			} catch (Exception e) {
				JSONArray expected_verbiage = JsonPath
						.read(getWebAuditReportVerbiages(),
								"$.['Organic Traffic']['On-Page SEO']['Description']['" + context.getDriver()
										.findElement(subSectionElementFinder("description", "verdict")).getText()
										+ "']");

				// validate verbiage against actual
				for (int i = 0; i < expected_verbiage.size(); i++) {

					if (expected_verbiage.get(i).toString().equals(context.getDriver()
							.findElement(subSectionElementFinder("description", "verbiage")).getText()))
						;
					{
						context.getSoftAssert()
								.assertEquals(context.getDriver()
										.findElement(subSectionElementFinder("description", "verbiage")).getText()
										.contains(expected_verbiage.get(i).toString()), true);

						break;
					}
				}
			}

			// Extent Report
			context.getExtentTestScenario()
					.createNode(new GherkinKeyword("When"),
							"User sees the Organic Traffic > OnPage SEO > Description SubSection is correct")
					.pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Organic Traffic > OnPage SEO > Description SubSection is correct")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
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
				context.getSoftAssert()
						.assertEquals(context.getDriver().findElement(subSectionElementFinder("headings", "verbiage"))
								.getText().contains(expected_verbiage), true);

			} catch (Exception e) {
				JSONArray expected_verbiage = JsonPath
						.read(getWebAuditReportVerbiages(),
								"$.['Organic Traffic']['On-Page SEO']['Headings']['"
										+ context.getDriver()
												.findElement(subSectionElementFinder("headings", "verdict")).getText()
										+ "']");

				// validate verbiage against actual
				for (int i = 0; i < expected_verbiage.size(); i++) {

					if (expected_verbiage.get(i).toString().equals(
							context.getDriver().findElement(subSectionElementFinder("headings", "verbiage")).getText()))

					{
						context.getSoftAssert().assertEquals(
								context.getDriver().findElement(subSectionElementFinder("headings", "verbiage"))
										.getText().contains(expected_verbiage.get(i).toString()),
								true);

						break;
					}
				}
			}

			// Extent Report
			context.getExtentTestScenario()
					.createNode(new GherkinKeyword("When"),
							"User sees the Organic Traffic > OnPage SEO > Heading SubSection is correct")
					.pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Organic Traffic > OnPage SEO > Heading SubSection is correct")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
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
				context.getSoftAssert()
						.assertEquals(context.getDriver().findElement(subSectionElementFinder("Images", "verbiage"))
								.getText().contains(expected_verbiage), true);

			} catch (Exception e) {
				JSONArray expected_verbiage = JsonPath.read(getWebAuditReportVerbiages(),
						"$.['Organic Traffic']['On-Page SEO']['Images']['" + context.getDriver()
								.findElement(subSectionElementFinder("Images", "verdict")).getText() + "']");

				// validate verbiage against actual
				for (int i = 0; i < expected_verbiage.size(); i++) {

					if (expected_verbiage.get(i).toString().equals(
							context.getDriver().findElement(subSectionElementFinder("Images", "verbiage")).getText()))

					{
						context.getSoftAssert().assertEquals(
								context.getDriver().findElement(subSectionElementFinder("Images", "verbiage")).getText()
										.contains(expected_verbiage.get(i).toString()),
								true);

						break;
					}
				}
			}

			// Extent Report
			context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
					"User sees the Organic Traffic > OnPage SEO > Images SubSection is correct").pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Organic Traffic > OnPage SEO > Images SubSection is correct")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
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

			context.getWait().until(
					ExpectedConditions.presenceOfElementLocated(subSectionElementFinder("Backlinks", "verdict")));

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
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Organic Traffic > OnPage SEO > Backlinks SubSection is correct")
						.pass("PASSED");
			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Organic Traffic > OnPage SEO > Backlinks SubSection is correct")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
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
					context.getSoftAssert()
							.assertEquals(context.getDriver()
									.findElement(subSectionElementFinder("MOZ Page Authority", "verbiage")).getText()
									.contains(expected_verbiage.get(i).toString()), true);
					if (Pattern.matches("[0-9]+", context.getDriver()
							.findElement(subSectionElementParentH3Finder("MOZ Domain Authority")).getText())) {
						// Extent Report
						context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
								"User sees the Organic Traffic > OnPage SEO > MOZ Domain Authority SubSection is correct")
								.pass("PASSED");
						break;
					} else {
						context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
								"User sees the Organic Traffic > OnPage SEO > MOZ Domain Authority SubSection is correct")
								.fail("FAILED: MOZ Domain Authority count is NULL and/or MOZ Domain Authority verbiage is incorrect");
						context.getExtentTestScenario().log(Status.FAIL, "Failed");
					}

				}
			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
						"User sees the Organic Traffic > OnPage SEO > MOZ Domain Authority SubSection is correct")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
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
					context.getSoftAssert()
							.assertEquals(context.getDriver()
									.findElement(subSectionElementFinder("MOZ Page Authority", "verbiage")).getText()
									.contains(expected_verbiage.get(i).toString()), true);
					if (Pattern.matches("[0-9]+", context.getDriver()
							.findElement(subSectionElementParentH3Finder("MOZ Page Authority")).getText())) {
						// Extent Report
						context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
								"User sees the Organic Traffic > OnPage SEO > MOZ Page Authority SubSection is correct")
								.pass("PASSED");
						break;
					} else {
						context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
								"User sees the Organic Traffic > OnPage SEO > MOZ Page Authority SubSection is correct")
								.fail("FAILED: MOZ Page Authority count is NULL and/or MOZ Page Authority verbiage is incorrect");
						context.getExtentTestScenario().log(Status.FAIL, "Failed");
					}

				}
			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Organic Traffic > OnPage SEO > MOZ Page Authority SubSection is correct")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
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
					context.getSoftAssert()
							.assertEquals(context.getDriver()
									.findElement(subSectionElementFinder("Majestic Citation Flow", "verbiage"))
									.getText().contains(expected_verbiage.get(i).toString()), true);
					if (Pattern.matches("[0-9]+", context.getDriver()
							.findElement(subSectionElementParentH3Finder("Majestic Citation Flow")).getText())) {
						// Extent Report
						context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
								"User sees the Organic Traffic > OnPage SEO > Majestic Citation Flow SubSection is correct")
								.pass("PASSED");
						break;
					} else {
						context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
								"User sees the Organic Traffic > OnPage SEO > Majestic Citation Flow SubSection is correct")
								.fail("FAILED: Majestic Citation Flow count is NULL and/or Majestic Citation Flow verbiage is incorrect");
						context.getExtentTestScenario().log(Status.FAIL, "Failed");
					}

				}
			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
						"User sees the Organic Traffic > OnPage SEO > Majestic Citation Flow SubSection is correct")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
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

					context.getSoftAssert()
							.assertEquals(context.getDriver()
									.findElement(subSectionElementFinder("Majestic Trust Flow", "verbiage")).getText()
									.contains(expected_verbiage.get(i).toString()), true);

					if (Pattern.matches("[0-9]+", context.getDriver()
							.findElement(subSectionElementParentH3Finder("Majestic Trust Flow")).getText())) {
						// Extent Report
						context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
								"User sees the Organic Traffic > OnPage SEO > Majestic Trust Flow SubSection is correct")
								.pass("PASSED");
						break;

					} else {
						context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
								"User sees the Organic Traffic > OnPage SEO > Majestic Trust Flow SubSection is correct")
								.fail("FAILED: Majestic Trust Flow count is NULL and/or Majestic Trust Flow verbiage is incorrect");
						context.getExtentTestScenario().log(Status.FAIL, "Failed");
					}

				}
			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
						"User sees the Organic Traffic > OnPage SEO > Majestic Trust Flow SubSection is correct")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
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
			context.getExtentTestScenario()
					.createNode(new GherkinKeyword("When"), "User scroll to Paid Traffic Section").pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User scroll to Paid Traffic Section")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
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
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Paid Traffic > Estimated Traffic SubSection is correct")
						.pass("PASSED" + "<br>" + "Actual - verbiage: "
								+ context.getDriver().findElement(By.xpath("//p[contains(text(), 'Estimated')]"))
										.getText()
								+ "<br>" + "Expected - verbiage: " + expected_verbiage + "<br>" + "Actual - count: "
								+ context.getDriver().findElement(subSectionElementParentH3Finder("Estimated"))
										.getText());

			} else {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Paid Traffic > Estimated Traffic SubSection is correct")
						.fail("FAILED: Paid Traffic > Estimated Traffic count is NULL and/or Paid Traffic > Estimated Traffic verbiage is incorrect"
								+ "<br>" + "Actual - verbiage: "
								+ context.getDriver().findElement(By.xpath("//p[contains(text(), 'Estimated')]"))
										.getText()
								+ "<br>" + "Expected - verbiage: " + expected_verbiage + "<br>" + "Actual - count: "
								+ context.getDriver().findElement(subSectionElementParentH3Finder("Estimated"))
										.getText());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Paid Traffic > Estimated Traffic SubSection is correct")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
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

				context.getSoftAssert()
						.assertEquals(context.getDriver().findElement(By.xpath("//p[contains(text(), 'Keywords')]"))
								.getText().contains(expected_verbiage.toString()), true);

				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Paid Traffic > Keyword Detected SubSection is correct")
						.pass("PASSED" + "<br>" + "Actual - verbiage: "
								+ context.getDriver().findElement(By.xpath("//p[contains(text(), 'Keywords')]"))
										.getText()
								+ "<br>" + "Expected - verbiage: " + expected_verbiage + "<br>" + "Actual - count: "
								+ context.getDriver().findElement(subSectionElementParentH3Finder("Keywords"))
										.getText());
			}

			else {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Paid Traffic > Keyword Detected SubSection is correct")
						.fail("FAILED: Keyword Detected count is NULL and/or Keyword Detected verbiage is incorrect"
								+ "<br>" + "Actual - verbiage: "
								+ context.getDriver().findElement(By.xpath("//p[contains(text(), 'Keywords')]"))
										.getText()
								+ "<br>" + "Expected - verbiage: " + expected_verbiage + "<br>" + "Actual - count: "
								+ context.getDriver().findElement(subSectionElementParentH3Finder("Keywords"))
										.getText());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Paid Traffic > Keyword Detected SubSection is correct")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
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
				String expected_verbiage_String = JsonPath
						.read(getWebAuditReportVerbiages(),
								"$.['Paid Traffic']['Average Position']['"
										+ context.getDriver()
												.findElement(subSectionElementFinder("Position", "verdict")).getText()
										+ "']");

				if (expected_verbiage_String.toString().equals(
						context.getDriver().findElement(subSectionElementFinder("Position", "verbiage")).getText())
						&& Pattern.matches("[0-9]+", context.getDriver()
								.findElement(subSectionElementParentH3Finder("Position")).getText())) {

					context.getSoftAssert().assertEquals(
							context.getDriver().findElement(subSectionElementFinder("Position", "verbiage")).getText()
									.contains(expected_verbiage_String.toString()),
							true);

					// Extent Report
					System.out.println("======================two===============================");
					context.getExtentTestScenario()
							.createNode(new GherkinKeyword("When"),
									"User sees the Paid Traffic > Average Position SubSection is correct")
							.pass("PASSED" + "<br>" + "Actual - verbiage: "
									+ context.getDriver().findElement(subSectionElementFinder("Position", "verbiage"))
											.getText()
									+ "<br>" + "Expected - verbiage: " + expected_verbiage_String + "<br>"
									+ "Actual - count: " + context.getDriver()
											.findElement(subSectionElementParentH3Finder("Position")).getText());

				}

				else {

					// Extent Report
					context.getExtentTestScenario()
							.createNode(new GherkinKeyword("When"),
									"User sees the Paid Traffic > Average Position SubSection is correct")
							.fail("FAILED: " + "<br>" + "Actual - verbiage: "
									+ context.getDriver().findElement(subSectionElementFinder("Position", "verbiage"))
											.getText()
									+ "<br>" + "Expected - verbiage: " + expected_verbiage_String + "<br>"
									+ "Actual - count: " + context.getDriver()
											.findElement(subSectionElementParentH3Finder("Position")).getText());
					context.getExtentTestScenario().log(Status.FAIL, "Failed");

				}

			} catch (Exception e) {
				System.out.println("======================four===============================");
				JSONArray expected_verbiage_JSON = JsonPath
						.read(getWebAuditReportVerbiages(),
								"$.['Paid Traffic']['Average Position']['"
										+ context.getDriver()
												.findElement(subSectionElementFinder("Position", "verdict")).getText()
										+ "']");

				// validate verbiage against actual
				boolean fail = true;
				for (int i = 0; i < expected_verbiage_JSON.size(); i++) {

					if (expected_verbiage_JSON.get(i).toString().equals(
							context.getDriver().findElement(subSectionElementFinder("Position", "verbiage")).getText())
							&& Pattern.matches("[0-9]+", context.getDriver()
									.findElement(subSectionElementParentH3Finder("Position")).getText())) {

						context.getSoftAssert().assertEquals(
								context.getDriver().findElement(subSectionElementFinder("Position", "verbiage"))
										.getText().contains(expected_verbiage_JSON.get(i).toString()),
								true);

						// Extent Report
						System.out.println("======================five===============================");
						context.getExtentTestScenario()
								.createNode(new GherkinKeyword("When"),
										"User sees the Paid Traffic > Average Position SubSection is correct")
								.pass("PASSED" + "<br>" + "Actual - verbiage: "
										+ context.getDriver()
												.findElement(subSectionElementFinder("Position", "verbiage")).getText()
										+ "<br>" + "Expected - verbiage: " + expected_verbiage_JSON.get(i).toString()
										+ "<br>" + "Actual - count: " + context.getDriver()
												.findElement(subSectionElementParentH3Finder("Position")).getText());

						fail = false;

						// Exit While-Loop
						break;
					}

				}

				if (fail) {
					// Extent Report
					context.getExtentTestScenario()
							.createNode(new GherkinKeyword("When"),
									"User sees the Paid Traffic > Average Position SubSection is correct")
							.fail("FAILED: " + "<br>" + "Actual - verbiage: "
									+ context.getDriver().findElement(subSectionElementFinder("Position", "verbiage"))
											.getText()
									+ "<br>" + "Expected - verbiage: " + expected_verbiage_JSON + "<br>"
									+ "Actual - count: " + context.getDriver()
											.findElement(subSectionElementParentH3Finder("Position")).getText());
					context.getExtentTestScenario().log(Status.FAIL, "Failed");
				}

			}
		}

		catch (Exception e) {

			try {
				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Paid Traffic > Average Position SubSection is correct")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
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
				String expected_verbiage_String = JsonPath.read(getWebAuditReportVerbiages(),
						"$.['Paid Traffic']['SEM vs SEO Ratio']['"
								+ context.getDriver().findElement(subSectionElementFinder("Ratio", "verdict")).getText()
								+ "']");

				if (expected_verbiage_String.toString()
						.equals(context.getDriver().findElement(subSectionElementFinder("Ratio", "verbiage")).getText())
						&& Pattern.matches("^\\d*.?\\d*%$",
								context.getDriver().findElement(subSectionElementParentH3Finder("Ratio")).getText())) {

					context.getSoftAssert()
							.assertEquals(context.getDriver().findElement(subSectionElementFinder("Ratio", "verbiage"))
									.getText().contains(expected_verbiage_String.toString()), true);

					System.out.println("======================two===============================");
					// Extent Report
					context.getExtentTestScenario()
							.createNode(new GherkinKeyword("When"),
									"User sees the Paid Traffic > SEMvsSEO Ratio SubSection is correct")
							.pass("PASSED" + "<br>" + "Actual - verbiage: "
									+ context.getDriver().findElement(subSectionElementFinder("Ratio", "verbiage"))
											.getText()
									+ "<br>" + "Expected - verbiage: " + expected_verbiage_String + "<br>"
									+ "Actual - count: " + context.getDriver()
											.findElement(subSectionElementParentH3Finder("Ratio")).getText());

				}

				else {

					// Extent Report
					context.getExtentTestScenario()
							.createNode(new GherkinKeyword("When"),
									"User sees the Paid Traffic > SEMvsSEO Ratio SubSection is correct")
							.fail("FAILED: " + "<br>" + "Actual - verbiage: "
									+ context.getDriver().findElement(subSectionElementFinder("Ratio", "verbiage"))
											.getText()
									+ "<br>" + "Expected - verbiage: " + expected_verbiage_String + "<br>"
									+ "Actual - count: " + context.getDriver()
											.findElement(subSectionElementParentH3Finder("Ratio")).getText());
					context.getExtentTestScenario().log(Status.FAIL, "Failed");

				}

			} catch (Exception e) {
				System.out.println("======================four===============================");
				JSONArray expected_verbiage_JSON = JsonPath.read(getWebAuditReportVerbiages(),
						"$.['Paid Traffic']['SEM vs SEO Ratio']['"
								+ context.getDriver().findElement(subSectionElementFinder("Ratio", "verdict")).getText()
								+ "']");

				// validate verbiage against actual
				boolean fail = true;
				for (int i = 0; i < expected_verbiage_JSON.size(); i++) {

					if (expected_verbiage_JSON.get(i).toString().equals(
							context.getDriver().findElement(subSectionElementFinder("Ratio", "verbiage")).getText())
							&& Pattern.matches("^\\d+.\\d+%$", context.getDriver()
									.findElement(subSectionElementParentH3Finder("Ratio")).getText())) {

						context.getSoftAssert().assertEquals(
								context.getDriver().findElement(subSectionElementFinder("Ratio", "verbiage")).getText()
										.contains(expected_verbiage_JSON.get(i).toString()),
								true);

						System.out.println("======================five===============================");
						// Extent Report
						context.getExtentTestScenario()
								.createNode(new GherkinKeyword("When"),
										"User sees the Paid Traffic > SEMvsSEO Ratio SubSection is correct")
								.pass("PASSED" + "<br>" + "Actual - verbiage: "
										+ context.getDriver().findElement(subSectionElementFinder("Ratio", "verbiage"))
												.getText()
										+ "<br>" + "Expected - verbiage: " + expected_verbiage_JSON.get(i).toString()
										+ "<br>" + "Actual - count: " + context.getDriver()
												.findElement(subSectionElementParentH3Finder("Ratio")).getText());

						fail = false;

						// Exit While-Loop
						break;
					}
				}

				if (fail) {
					// Extent Report
					context.getExtentTestScenario()
							.createNode(new GherkinKeyword("When"),
									"User sees the Paid Traffic > SEMvsSEO Ratio SubSection is correct")
							.fail("FAILED: "
									+ "<br>" + "Actual - verbiage: " + context.getDriver()
											.findElement(subSectionElementFinder("Ratio", "verbiage")).getText()
									+ "<br>" + "Expected - verbiage: " + expected_verbiage_JSON + "<br>"
									+ "Actual - count: " + context.getDriver()
											.findElement(subSectionElementParentH3Finder("Ratio")).getText()
									+ "<br>" + "Exception Message: " + e.getMessage());
					context.getExtentTestScenario().log(Status.FAIL, "Failed");

				}

			}

		}

		catch (Exception e) {

			try {

				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Paid Traffic > SEMvsSEO Ratio SubSection is correct")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");

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
			context.getExtentTestScenario()
					.createNode(new GherkinKeyword("When"), "User scroll to Social Activity Section").pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User scroll to Social Activity Section")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
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

				context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
						"User sees the Social Activity > Facebook SubSection is correct").pass("PASSED");

			}

			else if (Pattern.matches("^[a-z]+:\\/\\/[a-z]+.[a-z]{3}\\/[a-zA-Z]+\\/?$",
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

				context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
						"User sees the Social Activity > Facebook SubSection is correct").pass("PASSED");

			}

			else {
				context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
						"User sees the Social Activity > Facebook SubSection is correct").fail("FAILED");
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			}

		}

		catch (

		Exception e) {

			try {

				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Social Activity > Facebook SubSection is correct")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");

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

						context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
								"User validates the Social Activity > Facebook SubSection > Data is pulled between "
										+ fromDate + " and " + toDate)
								.fail("FAILED: " + "<br>" + "WebSite Audited: " + websiteUrl + "<br>" + "Report Link: "
										+ reportUrl + "<br>" + "Report Date: " + date_posted);

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

						context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
								"User validates the Social Activity > Facebook SubSection > Data is pulled between "
										+ fromDate + " and " + toDate)
								.warning("WARNING: " + "<br>" + "WebSite Audited: " + websiteUrl + "<br>"
										+ "Report Link: " + reportUrl + "<br>" + "Report Date: " + date_posted);

						context.getExtentTestScenario().log(Status.FAIL, "Failed");

					}

					else {

						context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
								"User validates the Social Activity > Facebook SubSection > Data is pulled between "
										+ fromDate + " and " + toDate)
								.pass("PASSED: " + "<br>" + "WebSite Audited: " + websiteUrl + "<br>" + "Report Link: "
										+ reportUrl + "<br>" + "Report Date: " + date_posted);

					}

				} catch (Exception e) {

					System.err.println("Inner Catch" + reportUrl);

					if (context.getDriver().findElement(subSectionSocialElementFinder("facebook", "verbiageHeader"))
							.getText().equals("No Facebook detected")) {

						context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
								"User validates the Social Activity > Facebook SubSection > Data is pulled between "
										+ fromDate + " and " + toDate)
								.warning("WARNING: " + "<br>" + "WebSite Audited: " + websiteUrl + "<br>"
										+ "Report Link: " + reportUrl + "<br>" + "Report Date: " + date_posted);

						context.getExtentTestScenario().log(Status.WARNING, "Failed");

					} else {

						context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
								"User validates the Social Activity > Facebook SubSection > Data is pulled between "
										+ fromDate + " and " + toDate)
								.fail("FAILED: " + "<br>" + "WebSite Audited: " + websiteUrl + "<br>" + "Report Link: "
										+ reportUrl + "<br>" + "Report Date: " + date_posted);

						context.getExtentTestScenario().log(Status.FAIL, "Failed");
					}

				}

				commonService.attachedScreenshotToReport(websiteUrl, context);
			}

		} catch (Exception e) {

			System.err.println("Outer Catch");

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User validates the Social Activity > Facebook SubSection > Data is pulled between "
										+ fromDate + " and " + toDate)
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
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

			if (context.getDriver().findElement(By.xpath("//div[@id='twitter']/div[1]/div[2]")).getText()
					.contains("No Twitter detected")
					&& context.getDriver().findElement(subSectionSocialElementFinder("twitter", "verdict")).getText()
							.equals("Critical")
					&& context.getDriver().findElement(subSectionSocialElementFinder("twitter", "verbiage")).getText()
							.equals("A twitter account is a great way to converse with your customers and leads in real time.")) {

				context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
						"User sees the Social Activity > Twitter SubSection is correct").pass("PASSED");

			}

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

				context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
						"User sees the Social Activity > Twitter SubSection is correct").pass("PASSED");

			}

			else {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Social Activity > Twitter SubSection is correct")
						.fail("FAILED - TRY: "
								+ "<br" + "Actual-URL: " + context.getDriver()
										.findElement(By.xpath("//div[@id='twitter']/div[1]/div[2]")).getText()
								+ "<br" + "Actual-Verdict: " + context.getDriver()
										.findElement(subSectionSocialElementFinder("twitter", "verdict")).getText()
								+ "<br" + "Actual-Follower: "
								+ context.getDriver()
										.findElement(subSectionSocialElementFinder("twitter", "verbiagerow1")).getText()
								+ "<br" + "Actual-Number of Tweets: "
								+ context.getDriver()
										.findElement(subSectionSocialElementFinder("twitter", "verbiagerow2")).getText()
								+ "<br" + "Actual-Average EngageMent: "
								+ context.getDriver()
										.findElement(subSectionSocialElementFinder("twitter", "verbiagerow3")).getText()
								+ "<br" + "Actual-Number of Tweets: "
								+ context.getDriver()
										.findElement(subSectionSocialElementFinder("twitter", "verbiagerow4"))
										.getText());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			}

		}

		catch (Exception e) {

			try {

				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Social Activity > Twitter SubSection is correct")
						.fail("FAILED - CATCH: "
								+ "<br" + "Actual-URL: " + context.getDriver()
										.findElement(By.xpath("//div[@id='twitter']/div[1]/div[2]")).getText()
								+ "<br" + "Actual-Verdict: " + context.getDriver()
										.findElement(subSectionSocialElementFinder("twitter", "verdict")).getText()
								+ "<br" + "Actual-Follower: "
								+ context.getDriver()
										.findElement(subSectionSocialElementFinder("twitter", "verbiagerow1")).getText()
								+ "<br" + "Actual-Number of Tweets: "
								+ context.getDriver()
										.findElement(subSectionSocialElementFinder("twitter", "verbiagerow2")).getText()
								+ "<br" + "Actual-Average EngageMent: "
								+ context.getDriver()
										.findElement(subSectionSocialElementFinder("twitter", "verbiagerow3")).getText()
								+ "<br" + "Actual-Number of Tweets: "
								+ context.getDriver()
										.findElement(subSectionSocialElementFinder("twitter", "verbiagerow4")).getText()
								+ "<br" + "Error Message: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");

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
						details.add("Report Link: "+ reportUrl);
						details.add("Report Date: " + date_posted);
						extentReportService.insertFailedStep(context, "User validates the Social Activity > Twitter SubSection > Data is pulled between "
										+ fromDate + " and " + toDate, details);

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
						details.add("Report Link: "+ reportUrl);
						details.add("Report Date: " + date_posted);
						extentReportService.insertWarningStep(context, "User validates the Social Activity > Twitter SubSection > Data is pulled between "
										+ fromDate + " and " + toDate, details);

						context.getExtentTestScenario().log(Status.WARNING, "Warning");

					}

					else {

						
						details.clear();
						details.add("WebSite Audited: " + websiteUrl);
						details.add("Report Link: "+ reportUrl);
						details.add("Report Date: " + date_posted);
						extentReportService.insertPassedStep(context, "User validates the Social Activity > Twitter SubSection > Data is pulled between "
										+ fromDate + " and " + toDate, details);
						
						context.getExtentTestScenario().log(Status.PASS, "Pass");

					}

				} catch (Exception e) {

					System.err.println("Inner Catch" + reportUrl);

					if (context.getDriver().findElement(subSectionSocialElementFinder("twitter", "verbiageHeader"))
							.getText().equals("No Twitter detected")) {						
						
						details.clear();
						details.add("WebSite Audited: " + websiteUrl);
						details.add("Report Link: "+ reportUrl);
						details.add("Report Date: " + date_posted);
						extentReportService.insertWarningStep(context, "User validates the Social Activity > Twitter SubSection > Data is pulled between "
										+ fromDate + " and " + toDate, details);

						context.getExtentTestScenario().log(Status.WARNING, "Failed");

					} else {					
						
						details.clear();
						details.add("WebSite Audited: " + websiteUrl);
						details.add("Report Link: "+ reportUrl);
						details.add("Report Date: " + date_posted);
						extentReportService.insertFailedStep(context, "User validates the Social Activity > Twitter SubSection > Data is pulled between "
										+ fromDate + " and " + toDate, details);

						context.getExtentTestScenario().log(Status.FAIL, "Failed");
					}

				}

				commonService.attachedScreenshotToReport(websiteUrl, context);
			}

		} catch (Exception e) {

			System.err.println("Outer Catch");

			// Extent Report
			try {
							
				
				details.clear();
				details.add( e.getMessage());
				extentReportService.insertFailedStep(context, "User validates the Social Activity > Twitter SubSection > Data is pulled between "
								+ fromDate + " and " + toDate, details);
				
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
				
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

}
