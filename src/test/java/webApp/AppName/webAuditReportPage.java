package webApp.AppName;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.ArrayList;

import org.openqa.selenium.By;

import helper.webAppContextDriver;
import helper.webAppHelper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class webAuditReportPage extends webAppHelper {

	// Page Elements
	// ==========================================
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

	// Declare Driver Instance
	// ==========================================
	private webAppContextDriver context;

	public webAuditReportPage(webAppContextDriver context) {
		super();
		this.context = context;
	}

	// Page Step Definition
	// =================================================

	@Then("User sees the summary of the section grades")
	public void userSeesTheSummaryOfTheSectionGrades() {
		assertEquals(context.getDriver().findElement(summarySection_usability).isDisplayed(), true);
		assertEquals(context.getDriver().findElement(summarySection_usability_score).getText().isEmpty(), false);

	}

	@Then("User sees the website score")
	public void userSeesTheWebsiteScore() {
		assertEquals(context.getDriver().findElement(summarySection_webSiteScore_alpha).isDisplayed(), true);
		assertEquals(context.getDriver().findElement(summarySection_webSiteScore_alpha).getText().isEmpty(), false);
		assertEquals(context.getDriver().findElement(summarySection_webSiteScore_percentage).isDisplayed(), true);
		assertEquals(context.getDriver().findElement(summarySection_webSiteScore_percentage).getText().isEmpty(), false);

	}

	@Then("User sees the count verdict of the section")
	public void userSeesTheCountVerdictOfTheSection() {

		// Critical
		// ============================
		assertEquals(context.getDriver().findElement(summarySection_verdict_critical).isDisplayed(), true);
		assertEquals(context.getDriver().findElement(summarySection_verdict_criticalScore).isDisplayed(), true);
		assertEquals(context.getDriver().findElement(summarySection_verdict_criticalScore).getText().isEmpty(), false);

		// For Improvement
		// ============================

		// Looking Good
		// ============================

	}

}
