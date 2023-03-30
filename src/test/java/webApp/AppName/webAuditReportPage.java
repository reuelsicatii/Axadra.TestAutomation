package webApp.AppName;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.google.gson.JsonArray;
import com.jayway.jsonpath.JsonPath;

import helper.webAppContextDriver;
import helper.webAppHelper;
import io.cucumber.java.en.Then;
import net.minidev.json.JSONArray;

public class webAuditReportPage extends webAppHelper {

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

	// Declare Driver Instance
	// ==========================================
	private webAppContextDriver context;

	public webAuditReportPage(webAppContextDriver context) {
		super();
		this.context = context;
	}

	// Page Step Definition
	// =================================================

	@Then("User sees the Summary Section > Grades SubSection is correct")
	public void userSeesTheSummarySectionGradesSubSectionIsCorrect() {
		assertEquals(context.getDriver().findElement(summarySection_usability).isDisplayed(), true);
		assertEquals(context.getDriver().findElement(summarySection_usability_score).getText().isEmpty(), false);

	}

	@Then("User sees the Summary Section > WebSite Score SubSection is correct")
	public void userSeesTheSummarySectionWebSiteScoreSubSectionIsCorrect() {
		assertEquals(context.getDriver().findElement(summarySection_webSiteScore_alpha).isDisplayed(), true);
		assertEquals(context.getDriver().findElement(summarySection_webSiteScore_alpha).getText().isEmpty(), false);
		assertEquals(context.getDriver().findElement(summarySection_webSiteScore_percentage).isDisplayed(), true);
		assertEquals(context.getDriver().findElement(summarySection_webSiteScore_percentage).getText().isEmpty(),
				false);

	}

	@Then("User sees the Summary Section > Verdict Table SubSection is correct")
	public void userSeesTheSummarySectionVerdictTableSubSectionIsCorrect() {

		// Critical
		// ============================
		assertEquals(context.getDriver().findElement(summarySection_verdict_critical).isDisplayed(), true);
		assertEquals(context.getDriver().findElement(summarySection_verdict_criticalScore).isDisplayed(), true);
		assertEquals(context.getDriver().findElement(summarySection_verdict_criticalScore).getText().isEmpty(), false);

		// For Improvement
		// ============================
		assertEquals(context.getDriver().findElement(summarySection_verdict_forImprovement).isDisplayed(), true);
		assertEquals(context.getDriver().findElement(summarySection_verdict_forImprovementScore).isDisplayed(), true);
		assertEquals(context.getDriver().findElement(summarySection_verdict_forImprovementScore).getText().isEmpty(),
				false);

		// Looking Good
		// ============================
		assertEquals(context.getDriver().findElement(summarySection_verdict_lookingGood).isDisplayed(), true);
		assertEquals(context.getDriver().findElement(summarySection_verdict_lookingGoodScore).isDisplayed(), true);
		assertEquals(context.getDriver().findElement(summarySection_verdict_lookingGoodScore).getText().isEmpty(),
				false);

	}

	@Then("User sees the Usability Section > Mobile Friendliness SubSection is correct")
	public void userSeesTheUsabilitySectionMobileFriendlinessSubSectionIsCorrect() throws IOException {

		context.getWait().until(
				ExpectedConditions.presenceOfElementLocated(subSectionElementFinder("Mobile Friendliness", "verdict")));

		String expected_verbiage = JsonPath
				.read(getWebAuditReportVerbiages(),
						"$.['Usability']['Mobile Friendliness']['" + context.getDriver()
								.findElement(subSectionElementFinder("Mobile Friendliness", "verdict")).getText()
								+ "']");

		assertEquals(
				context.getDriver().findElement(subSectionElementFinder("Mobile Friendliness", "verbiage")).getText(),
				expected_verbiage);

	}

	@Then("User sees the Usability Section > Google Analytics Found SubSection is correct")
	public void userSeesTheUsabilitySectionGoogleAnalyticsFoundSubSectionIsCorrect() throws IOException {

		String expected_verbiage;

		try {

			// Check if Rating is available in report
			context.getWait().until(ExpectedConditions
					.presenceOfElementLocated(subSectionElementFinder("Google Analytics", "verdict")));

			// Get value from JSON based on rating -- Looking Good or Critical
			expected_verbiage = JsonPath.read(getWebAuditReportVerbiages(), "$.['Usability']['Google Analytics']['"
					+ context.getDriver().findElement(subSectionElementFinder("Google Analytics", "verdict")).getText()
					+ "']");

			// Validate verboage from JSON against WebAudit Report -- Looking Good or
			// Critical
			switch (context.getDriver().findElement(subSectionElementFinder("Google Analytics", "verdict")).getText()) {
			case "Looking Good":
				assertEquals(context.getDriver().findElement(subSectionElementFinder("Google Analytics", "verbiage"))
						.getText().contains(expected_verbiage), true);
				break;

			case "Critical":
				assertEquals(context.getDriver().findElement(subSectionElementFinder("Google Analytics", "verbiage"))
						.getText().contains(expected_verbiage), true);
				break;

			}
		} catch (Exception e) {

			// Check if Rating is available in report
			context.getWait().until(ExpectedConditions
					.presenceOfElementLocated(subSectionElementFinder("data could not be retrieved ", "verdict")));

			// Get value from JSON based on rating -- Looking Good or Critical
			expected_verbiage = JsonPath.read(getWebAuditReportVerbiages(),
					"$.['Usability']['Google Analytics']['N/A']");

			assertEquals(
					context.getDriver().findElement(subSectionElementFinder("data could not be retrieved", "verbiage"))
							.getText().contains(expected_verbiage),
					true);
		}

	}

	@Then("User sees the Usability Section > Page Speed Insight SubSection is correct")
	public void userSeesTheUsabilitySectionPageSpeedInsightSubSectionIsCorrect() throws IOException {

		context.getWait().until(
				ExpectedConditions.presenceOfElementLocated(subSectionElementFinder("website speed", "verdict")));

		JSONArray expected_verbiage = JsonPath.read(getWebAuditReportVerbiages(),
				"$.['Usability']['Page Speed Insights']['"
						+ context.getDriver().findElement(subSectionElementFinder("website speed", "verdict")).getText()
						+ "']");

		for (int i = 0; i < expected_verbiage.size(); i++) {

			if (expected_verbiage.get(i).toString().equals(
					context.getDriver().findElement(subSectionElementFinder("website speed", "verbiage")).getText()))
				;
			{
				assertEquals(
						context.getDriver().findElement(subSectionElementFinder("website speed", "verbiage")).getText(),
						expected_verbiage.get(i).toString());
				break;
			}
		}

	}

	@Then("User sees the Security Section > SSL SubSection is correct")
	public void userSeesTheSecuritySectionSSLSubSectionIsCorrect() throws IOException {

		context.getWait().until(ExpectedConditions.presenceOfElementLocated(subSectionElementFinder("SSL", "verdict")));

		String expected_verbiage = JsonPath.read(getWebAuditReportVerbiages(), "$.['Security']['SSL']['"
				+ context.getDriver().findElement(subSectionElementFinder("SSL", "verdict")).getText() + "']");

		assertEquals(context.getDriver().findElement(subSectionElementFinder("SSL", "verbiage")).getText()
				.contains(expected_verbiage), true);

	}

	@Then("User sees the Security Section > Malware SubSection is correct")
	public void userSeesTheSecuritySectionMalwareSubSectionIsCorrect() throws IOException {

		context.getWait()
				.until(ExpectedConditions.presenceOfElementLocated(subSectionElementFinder("Malware", "verdict")));

		String expected_verbiage = JsonPath.read(getWebAuditReportVerbiages(), "$.['Security']['Malware']['"
				+ context.getDriver().findElement(subSectionElementFinder("Malware", "verdict")).getText() + "']");

		assertEquals(context.getDriver().findElement(subSectionElementFinder("Malware", "verbiage")).getText()
				.contains(expected_verbiage), true);

	}

	@Then("User sees the Security Section > HTTPS SubSection is correct")
	public void userSeesTheSecuritySectionHTTPSSubSectionIsCorrect() throws IOException {

		context.getWait()
				.until(ExpectedConditions.presenceOfElementLocated(subSectionElementFinder("HTTPS", "verdict")));

		try {
			String expected_verbiage = JsonPath.read(getWebAuditReportVerbiages(), "$.['Security']['HTTPS']['"
					+ context.getDriver().findElement(subSectionElementFinder("HTTPS", "verdict")).getText() + "']");

			// validate verbiage against actual
			assertEquals(context.getDriver().findElement(subSectionElementFinder("HTTPS", "verbiage")).getText()
					.contains(expected_verbiage), true);

		} catch (Exception e) {
			JsonArray expected_verbiage = JsonPath.read(getWebAuditReportVerbiages(), "$.['Security']['HTTPS']['"
					+ context.getDriver().findElement(subSectionElementFinder("HTTPS", "verdict")).getText() + "']");

			// validate verbiage against actual
			for (int i = 0; i < expected_verbiage.size(); i++) {

				if (expected_verbiage.get(i).toString().equals(
						context.getDriver().findElement(subSectionElementFinder("HTTPS", "verbiage")).getText()))
					;
				{
					assertEquals(context.getDriver().findElement(subSectionElementFinder("HTTPS", "verbiage")).getText()
							.contains(expected_verbiage.get(i).toString()), true);
					break;
				}
			}
		}

	}

	@Then("User sees the Security Section > Blacklisted SubSection is correct")
	public void userSeesTheSecuritySectionBlacklistedSubSectionIsCorrect() throws IOException {

		context.getWait()
				.until(ExpectedConditions.presenceOfElementLocated(subSectionElementFinder("blacklisted", "verdict")));

		String expected_verbiage = JsonPath.read(getWebAuditReportVerbiages(), "$.['Security']['Blacklisted']['"
				+ context.getDriver().findElement(subSectionElementFinder("blacklisted", "verdict")).getText() + "']");

		assertEquals(context.getDriver().findElement(subSectionElementFinder("blacklisted", "verbiage")).getText()
				.contains(expected_verbiage), true);

	}

}
