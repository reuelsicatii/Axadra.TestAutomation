package webApp.AppName;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.jayway.jsonpath.JsonPath;

import helper.webAppContextDriver;
import helper.webAppHelper;
import io.cucumber.java.en.Then;
import net.minidev.json.JSONArray;

public class webAuditReportPage extends webAppHelper {

	// Declare Driver Instance
	// ==========================================
	private webAppContextDriver context;

	public webAuditReportPage(webAppContextDriver context) {
		super();
		this.context = context;
	}

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

				// Validate verboage from JSON against WebAudit Report -- Looking Good or
				// Critical
				switch (context.getDriver().findElement(subSectionElementFinder("Google Analytics", "verdict"))
						.getText()) {
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
					;
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
			context.getWait()
					.until(ExpectedConditions.presenceOfElementLocated(subSectionElementFinder("SSL", "verdict")));

			context.getDriver().executeScript("arguments[0].scrollIntoView(true);",
					context.getDriver().findElement(subSectionElementFinder("SSL", "verdict")));

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
			context.getWait()
					.until(ExpectedConditions.presenceOfElementLocated(subSectionElementFinder("Malware", "verdict")));

			context.getDriver().executeScript("arguments[0].scrollIntoView(true);",
					context.getDriver().findElement(subSectionElementFinder("Malware", "verdict")));

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
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User sees the Security Section > HTTPS SubSection is correct")
	public void userSeesTheSecuritySectionHTTPSSubSectionIsCorrect() throws IOException {

		try {
			// Step Definition
			context.getWait()
					.until(ExpectedConditions.presenceOfElementLocated(subSectionElementFinder("HTTPS", "verdict")));

			context.getDriver().executeScript("arguments[0].scrollIntoView(true);",
					context.getDriver().findElement(subSectionElementFinder("HTTPS", "verdict")));

			try {
				String expected_verbiage = JsonPath.read(getWebAuditReportVerbiages(),
						"$.['Security']['HTTPS']['"
								+ context.getDriver().findElement(subSectionElementFinder("HTTPS", "verdict")).getText()
								+ "']");

				// validate verbiage against actual
				context.getSoftAssert()
						.assertEquals(context.getDriver().findElement(subSectionElementFinder("HTTPS", "verbiage"))
								.getText().contains(expected_verbiage), true);

			} catch (Exception e) {
				JSONArray expected_verbiage = JsonPath.read(getWebAuditReportVerbiages(),
						"$.['Security']['HTTPS']['"
								+ context.getDriver().findElement(subSectionElementFinder("HTTPS", "verdict")).getText()
								+ "']");

				// validate verbiage against actual
				for (int i = 0; i < expected_verbiage.size(); i++) {

					if (expected_verbiage.get(i).toString().equals(
							context.getDriver().findElement(subSectionElementFinder("HTTPS", "verbiage")).getText()))
						;
					{
						context.getSoftAssert().assertEquals(
								context.getDriver().findElement(subSectionElementFinder("HTTPS", "verbiage")).getText()
										.contains(expected_verbiage.get(i).toString()),
								true);

						break;
					}
				}
			}

			// Extent Report
			context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
					"User sees the Security Section > HTTPS SubSection is correct").pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Security Section > HTTPS SubSection is correct")
						.fail("FAILED: " + e.getMessage());
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
			context.getWait().until(
					ExpectedConditions.presenceOfElementLocated(subSectionElementFinder("blacklisted", "verdict")));

			context.getDriver().executeScript("arguments[0].scrollIntoView(true);",
					context.getDriver().findElement(subSectionElementFinder("blacklisted", "verdict")));

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
					context.getDriver().findElement(subSectionElementFinder("Sitemap", "verdict")));

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
			context.getWait()
					.until(ExpectedConditions.presenceOfElementLocated(subSectionElementFinder("Robots", "verdict")));

			context.getDriver().executeScript("arguments[0].scrollIntoView(true);",
					context.getDriver().findElement(subSectionElementFinder("Robots", "verdict")));

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
			context.getWait().until(
					ExpectedConditions.presenceOfElementLocated(subSectionElementFinder("redirection", "verdict")));

			context.getDriver().executeScript("arguments[0].scrollIntoView(true);",
					context.getDriver().findElement(subSectionElementFinder("redirection", "verdict")));

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
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

}
