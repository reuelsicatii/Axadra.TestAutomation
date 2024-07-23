package webApp.SEM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.text.SimpleDateFormat;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.Status;

import helper.webAppContext;
import helper.webAppHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import testAuto.Service.CommonService;
import testAuto.Service.ExtentReportService;

public class adminPage extends webAppHelper {

	// SEM - Page Elements
	// ==========================================
	By hubMin_tab = By.xpath("//a[text()='Hubmin']");
	By resellerOff_radiobutton = By.xpath(
			"//form[@action='act_Account_Update.cfm']//input[@type='Radio' and @name='Reseller_IND' and @value='0']");

	/*
	 * // Search Engine - Element Finder // ===================================
	 * private Select brandIDElementFinder() {
	 * 
	 * Select element = new
	 * Select(context.getDriver().findElement(By.xpath("//select[@name='brand_id']")
	 * )); return element;
	 * 
	 * }
	 */

	// Declare Driver Instance
	// ==========================================
	private webAppContext context;

	public adminPage(webAppContext context) {
		super();
		this.context = context;
	}

	// Declare Services
	// ==========================================
	ExtentReportService extentReportService = new ExtentReportService();
	CommonService commonService = new CommonService();

	// Declare Variables
	// ==========================================
	ArrayList<String> details = new ArrayList<String>();

	// Page Step Definition
	// =================================================
	@Then("User see reseller is searchable over Admin > Campaign Scorecard")
	public void userSeeResellerCanBeSearched() {

		try {

			// Step Definition
			context.getDriver().findElement(By.xpath("//input[@name='Reseller_Account_ID_2']"))
					.sendKeys(context.getTextExecutionDetails().get("Account Name"));
			Thread.sleep(2000);

			if (context.getDriver()
					.findElement(By.xpath("//div[@id='as-results-Reseller_Account_ID_2']//li//em[contains(text(),'"
							+ context.getTextExecutionDetails().get("Account Name") + "')][1]"))
					.isDisplayed()) {

				context.getDriver()
						.findElement(By.xpath("//div[@id='as-results-Reseller_Account_ID_2']//li//em[contains(text(),'"
								+ context.getTextExecutionDetails().get("Account Name") + "')][1]"))
						.click();

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				extentReportService.insertPassedStep(context,
						"User see reseller is searchable over Admin > Campaign Scorecard", details);

				context.getExtentTestScenario().log(Status.PASS, "PASSED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEM/adminPage/userSeeResellerIsSearchableOverAdminCampaignScorecard.png?raw=true");

			} else {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				extentReportService.insertFailedStep(context,
						"User see reseller is searchable over Admin > Campaign Scorecard", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEM/adminPage/userSeeResellerIsSearchableOverAdminCampaignScorecard.png?raw=true");

			}

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context,
						"User see reseller is searchable over Admin > Campaign Scorecard", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEM/accountDetailsPage/userClickTheUpdateButton.png?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

}
