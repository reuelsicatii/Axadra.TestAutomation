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

public class accountDetailsPage extends webAppHelper {

	// SEM - Page Elements
	// ==========================================
	By hubMin_tab = By.xpath("//a[text()='Hubmin']");
	By resellerOff_radiobutton = By.xpath(
			"//form[@action='act_Account_Update.cfm']//input[@type='Radio' and @name='Reseller_IND' and @value='0']");
	By resellerOn_radiobutton = By.xpath(
			"//form[@action='act_Account_Update.cfm']//input[@type='Radio' and @name='Reseller_IND' and @value='1']");
	By commissionEligibleOff_radiobutton = By.xpath(
			"//form[@action='act_Account_Update.cfm']//input[@type='Radio' and @name='Commission_IND' and @value='0']");
	By commissionEligibleOn_radiobutton = By.xpath(
			"//form[@action='act_Account_Update.cfm']//input[@type='Radio' and @name='Commission_IND' and @value='1']");
	By update_button = By.xpath("//form[@action='act_Account_Update.cfm']//a[@id='submit_update_account']");

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

	public accountDetailsPage(webAppContext context) {
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
	@When("User click the hubMin tab")
	public void userClickTheHubMinTab() {
		try {

			// Step Definition
			context.getDriver().findElement(hubMin_tab).click();

			// Extent Report
			details.clear();
			details.add("Page URL: " + context.getDriver().getCurrentUrl());
			extentReportService.insertPassedStep(context, "User click the hubMin tab", details);

			context.getExtentTestScenario().log(Status.PASS, "PASSED");
			extentReportService.attachedScreenshotToReport(context,
					"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEM/accountDetailsPage/userClickTheHubMinTab.png?raw=true");

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context, "User click the hubMin tab", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEM/accountDetailsPage/userClickTheHubMinTab.png?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@When("User select from reseller radiobutton the {string}")
	public void userSelectFromResellerRadiobuttonThe(String resellerRadiobuttonToggle) {
		try {

			// Step Definition
			if (resellerRadiobuttonToggle.equals("on")) {
				context.getDriver().findElement(resellerOn_radiobutton).click();
			} else if (resellerRadiobuttonToggle.equals("off")) {
				context.getDriver().findElement(resellerOff_radiobutton).click();
			}

			Thread.sleep(2000);
			context.getDriver().switchTo().alert().accept();

			// Extent Report
			details.clear();
			details.add("Page URL: " + context.getDriver().getCurrentUrl());
			extentReportService.insertPassedStep(context,
					"User select from reseller radiobutton the " + resellerRadiobuttonToggle, details);

			context.getExtentTestScenario().log(Status.PASS, "PASSED");
			extentReportService.attachedScreenshotToReport(context,
					"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEM/accountDetailsPage/userSelectFromResellerRadiobuttonThe.png?raw=true");

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context,
						"User select from reseller radiobutton the " + resellerRadiobuttonToggle, details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEM/accountDetailsPage/userSelectFromResellerRadiobuttonThe.png?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@When("User select from commissionEligible radiobutton the {string}")
	public void userSelectFromCommissionEligibleRadiobuttonThe(String commissionEligibleRadiobuttonToggle) {
		try {

			// Step Definition
			if (commissionEligibleRadiobuttonToggle.equals("on")) {
				context.getDriver().findElement(commissionEligibleOn_radiobutton).click();
			} else if (commissionEligibleRadiobuttonToggle.equals("off")) {
				context.getDriver().findElement(commissionEligibleOn_radiobutton).click();
			}

			// Extent Report
			details.clear();
			details.add("Page URL: " + context.getDriver().getCurrentUrl());
			extentReportService.insertPassedStep(context,
					"User select from reseller radiobutton the " + commissionEligibleRadiobuttonToggle, details);

			context.getExtentTestScenario().log(Status.PASS, "PASSED");
			extentReportService.attachedScreenshotToReport(context,
					"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEM/accountDetailsPage/userSelectFromCommissionEligibleRadiobuttonThe.png?raw=true");

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context,
						"User select from reseller radiobutton the " + commissionEligibleRadiobuttonToggle, details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEM/accountDetailsPage/userSelectFromCommissionEligibleRadiobuttonThe.png?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@When("User click the update button")
	public void userClickTheUpdateButton() {
		try {

			// Step Definition
			context.getDriver().findElement(update_button).click();
			Thread.sleep(5000);

			// Extent Report
			details.clear();
			details.add("Page URL: " + context.getDriver().getCurrentUrl());
			extentReportService.insertPassedStep(context, "User click the update button", details);

			context.getExtentTestScenario().log(Status.PASS, "PASSED");
			context.getDriver().executeScript("window.scrollTo(0, " + 600 + ")");
			extentReportService.attachedScreenshotToReport(context,
					"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEM/accountDetailsPage/userClickTheUpdateButton.png?raw=true");

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context, "User click the update button", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				context.getDriver().executeScript("window.scrollTo(0, " + 600 + ")");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEM/accountDetailsPage/userClickTheUpdateButton.png?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User see reseller is successfully created")
	public void userSeeResellerIsSuccessfullyCreated() {

		try {

			// Step Definition
			//context.getDriver().executeScript("location.reload(true);");
			//Thread.sleep(5000);

			if (context.getDriver()
					.findElement(By.xpath("//div[@id='message_area']//p[text()='Account info updated.']"))
					.isDisplayed()) {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				extentReportService.insertPassedStep(context, "User see reseller is successfully created", details);

				context.getExtentTestScenario().log(Status.PASS, "PASSED");
				context.getDriver().executeScript("window.scrollTo(0, " + 0 + ")");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEM/accountDetailsPage/userSeeResellerIsSuccessfullyCreated.png?raw=true");

			}

			else {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				extentReportService.insertFailedStep(context, "User see reseller is successfully created", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				context.getDriver().executeScript("window.scrollTo(0, " + 0 + ")");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEM/accountDetailsPage/userSeeResellerIsSuccessfullyCreated.png?raw=true");

			}

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context, "User see reseller is successfully created", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				// context.getDriver().executeScript("window.scrollTo(0, " + 10 + ")");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEM/accountDetailsPage/userSeeResellerIsSuccessfullyCreated.png?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
