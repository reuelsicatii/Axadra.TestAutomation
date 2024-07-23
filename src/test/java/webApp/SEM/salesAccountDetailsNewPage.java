package webApp.SEM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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

public class salesAccountDetailsNewPage extends webAppHelper {

	// SEM - Page Elements
	// ==========================================
	By accountName_textfield = By
			.xpath("//form[@action='act_Sales_Account_Details_Insert.cfm']//input[@name='Account_Name']");
	By firstName_textfield = By
			.xpath("//form[@action='act_Sales_Account_Details_Insert.cfm']//input[@name='First_Name']");
	By lastName_textfield = By
			.xpath("//form[@action='act_Sales_Account_Details_Insert.cfm']//input[@name='Last_Name']");
	By buildLead_button = By.xpath("//form[@action='act_Sales_Account_Details_Insert.cfm']//a[@id='submit_myform']");
	By welcome_message = By.xpath("//form[@id='myform2']//span[text()='Reuel Sicat']");
	By goThereNow_link = By.xpath("//div[@id='message_area']//p[contains(text(), 'New Lead Account was build.')]/a");

	// Search Engine - Element Finder
	// ===================================
	private Select brandIDElementFinder() {

		Select element = new Select(context.getDriver().findElement(By.xpath("//select[@name='brand_id']")));
		return element;

	}

	// Declare Driver Instance
	// ==========================================
	private webAppContext context;

	public salesAccountDetailsNewPage(webAppContext context) {
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

	@When("User populate the accountName textfield with {string}")
	public void userPopulateTheAccountNameTextfieldWith(String dynamicAccountName) throws Throwable {
		try {

			String finalDynamicAccountName;

			// Step Definition
			if (dynamicAccountName.equals("dynamicAccountName")) {

				finalDynamicAccountName = "AcctName" + commonService.generateRandomString(10);

			} else {

				finalDynamicAccountName = dynamicAccountName;

			}

			context.getDriver().findElement(accountName_textfield).sendKeys(finalDynamicAccountName);
			context.getTextExecutionDetails().put("Account Name", finalDynamicAccountName);

			// Extent Report
			details.clear();
			details.add("Page URL: " + context.getDriver().getCurrentUrl());
			extentReportService.insertPassedStep(context,
					"User populate the accountName textfield with " + dynamicAccountName, details);

			context.getExtentTestScenario().log(Status.PASS, "PASSED");
			extentReportService.attachedScreenshotToReport(context,
					"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEM/salesAccountDetailsNewPage/userPopulateTheAccountNameTextFieldWith.png?raw=true");

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context,
						"User populate the accountName textfield with " + dynamicAccountName, details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEM/salesAccountDetailsNewPage/userPopulateTheAccountNameTextFieldWith.png?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@When("User populate the firstName textfield with {string}")
	public void userPopulateTheFirstNameTextfieldWith(String dynamicFirstName) throws Throwable {
		try {

			// Step Definition
			if (dynamicFirstName.equals("dynamicFirstName")) {
				context.getDriver().findElement(firstName_textfield)
						.sendKeys("FName" + commonService.generateRandomString(10));
			} else {

				context.getDriver().findElement(firstName_textfield).sendKeys(dynamicFirstName);

			}

			// Extent Report
			details.clear();
			details.add("Page URL: " + context.getDriver().getCurrentUrl());
			extentReportService.insertPassedStep(context,
					"User populate the firstName textfield with " + dynamicFirstName, details);

			context.getExtentTestScenario().log(Status.PASS, "PASSED");
			extentReportService.attachedScreenshotToReport(context,
					"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEM/salesAccountDetailsNewPage/userPopulateTheFirstNameTextFieldWith.png?raw=true");

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context,
						"User populate the firstName textfield with " + dynamicFirstName, details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEM/salesAccountDetailsNewPage/userPopulateTheFirstNameTextFieldWith.png?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@When("User populate the lastName textfield with {string}")
	public void userPopulateTheLastNameTextfieldWith(String dynamicLastName) throws Throwable {
		try {

			// Step Definition
			if (dynamicLastName.equals("dynamicLastName")) {
				context.getDriver().findElement(lastName_textfield)
						.sendKeys("LName" + commonService.generateRandomString(10));
			} else {

				context.getDriver().findElement(lastName_textfield).sendKeys(dynamicLastName);

			}

			// Extent Report
			details.clear();
			details.add("Page URL: " + context.getDriver().getCurrentUrl());
			extentReportService.insertPassedStep(context,
					"User populate the lastName textfield with " + dynamicLastName, details);

			context.getExtentTestScenario().log(Status.PASS, "PASSED");
			extentReportService.attachedScreenshotToReport(context,
					"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEM/salesAccountDetailsNewPage/userPopulateTheLasttNameTextFieldWith.png?raw=true");

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context,
						"User populate the lastName textfield with " + dynamicLastName, details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEM/salesAccountDetailsNewPage/userPopulateTheLasttNameTextFieldWith.png?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@When("User select from brand dropdown the {string}")
	public void userSelectFromBrandDropdownThe(String brandID) {

		try {

			// Step Definition
			brandIDElementFinder().selectByVisibleText(brandID);

			// Extent Report
			details.clear();
			details.add("Page URL: " + context.getDriver().getCurrentUrl());
			extentReportService.insertPassedStep(context, "User select from brand dropdown the  " + brandID, details);

			context.getExtentTestScenario().log(Status.PASS, "PASSED");
			extentReportService.attachedScreenshotToReport(context,
					"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEM/salesAccountDetailsNewPage/userSelectFromBrandDropdownThe.png?raw=true");

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context, "User select from brand dropdown the  " + brandID,
						details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEM/salesAccountDetailsNewPage/userSelectFromBrandDropdownThe.png?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@When("User click the buildLead button")
	public void userClickTheBuildLeadButton() {

		try {

			// Step Definition
			context.getDriver().findElement(buildLead_button).click();

			// Extent Report
			details.clear();
			details.add("Page URL: " + context.getDriver().getCurrentUrl());
			extentReportService.insertPassedStep(context, "User click the buildLead button", details);

			context.getExtentTestScenario().log(Status.PASS, "PASSED");
			extentReportService.attachedScreenshotToReport(context,
					"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEM/salesAccountDetailsNewPage/userClickTheBuildLeadButton.png?raw=true");

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context, "User click the buildLead button", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEM/salesAccountDetailsNewPage/userClickTheBuildLeadButton.png?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Then("User see lead is successfully created")
	public void userSeeLeadIsSuccessfullyCreated() {
		try {

			// Step Definition
			Thread.sleep(10000);

			// Extent Report
			details.clear();
			details.add("Page URL: " + context.getDriver().getCurrentUrl());
			extentReportService.insertPassedStep(context, "User see lead is successfully created", details);

			context.getExtentTestScenario().log(Status.PASS, "PASSED");
			extentReportService.attachedScreenshotToReport(context,
					"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEM/salesAccountDetailsNewPage/userSeeLeadIsSuccessfullyCreated.png?raw=true");

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context, "User see lead is successfully created", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEM/salesAccountDetailsNewPage/userSeeLeadIsSuccessfullyCreated.png?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@When("User click the goThereNow link")
	public void userClickTheGoThereNowLink() {

		try {

			// Step Definition
			context.getDriver().findElement(goThereNow_link).click();

			// Extent Report
			details.clear();
			details.add("Page URL: " + context.getDriver().getCurrentUrl());
			extentReportService.insertPassedStep(context, "User click the goThereNow link", details);

			context.getExtentTestScenario().log(Status.PASS, "PASSED");
			extentReportService.attachedScreenshotToReport(context,
					"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEM/salesAccountDetailsNewPage/userClickTheGoThereNowLink.png?raw=true");

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context, "User see lead is successfully create", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEM/salesAccountDetailsNewPage/userClickTheGoThereNowLink.png?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@When("User navigate to accountDetailsAccountID")
	public void userNavigateToAccountDetailsAccountID() {
		try {

			// Step Definition
			String[] result = context.getDriver().getCurrentUrl().split("=");
			context.getTextExecutionDetails().put("Environment_URL", result[0]);
			context.getTextExecutionDetails().put("Account_ID", result[2]);
			context.getDriver().get(context.getTextExecutionDetails().get("Environment_URL")
					+ "=Account_Details&Account_ID=" + context.getTextExecutionDetails().get("Account_ID"));

			Thread.sleep(2000);

			// Extent Report
			details.clear();
			details.add("Page URL: " + context.getDriver().getCurrentUrl());
			extentReportService.insertPassedStep(context, "User navigate to accountDetailsAccountID", details);

			context.getExtentTestScenario().log(Status.PASS, "PASSED");
			extentReportService.attachedScreenshotToReport(context,
					"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEM/salesAccountDetailsNewPage/userNavigateToAccountDetailsAccountID.png?raw=true");

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context, "User navigate to accountDetailsAccountID", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEM/salesAccountDetailsNewPage/userNavigateToAccountDetailsAccountID.png?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
