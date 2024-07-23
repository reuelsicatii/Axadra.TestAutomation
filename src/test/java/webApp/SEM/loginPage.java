package webApp.SEM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.aventstack.extentreports.Status;

import helper.webAppContext;
import helper.webAppHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import testAuto.Service.CommonService;
import testAuto.Service.ExtentReportService;

public class loginPage extends webAppHelper {

	// SEM - Page Elements
	// ==========================================
	By username_textfield = By.xpath("//input[@name='email']");
	By password_textfield = By.xpath("//input[@name='password']");
	By login_button = By.xpath("//span[text()='Log in']");
	By welcome_message = By.xpath("//div[@id='coValues']");

	// Declare Driver Instance
	// ==========================================
	private webAppContext context;

	public loginPage(webAppContext context) {
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

	@Given("User populate the username with {string}")
	public void userEnterTheUsernameAs(String username) throws ClassNotFoundException {

		try {
			// Step Definition
			context.getDriver().findElement(username_textfield).sendKeys(username);

			// Extent Report
			details.clear();
			details.add("Page URL: " + context.getDriver().getCurrentUrl());
			extentReportService.insertPassedStep(context, "User populate the username with " + username, details);

			context.getExtentTestScenario().log(Status.PASS, "PASSED");
			extentReportService.attachedScreenshotToReport(context,
					"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEM/login/userEnterTheUsernameAs.png?raw=true");

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context, "User populate the username with " + username, details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEM/login/userEnterTheUsernameAs.png?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Given("User populate the password with {string}")
	public void userEnterThePasswordAs(String password) throws IOException {

		try {
			// Step Definition
			context.getDriver().findElement(password_textfield).sendKeys(password);

			// Extent Report
			details.clear();
			details.add("Page URL: " + context.getDriver().getCurrentUrl());
			extentReportService.insertPassedStep(context, "User populate the password with " + password, details);

			context.getExtentTestScenario().log(Status.PASS, "PASSED");
			extentReportService.attachedScreenshotToReport(context,
					"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEM/login/userEnterThePasswordAs.png?raw=true");

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context, "User populate the password with " + password, details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEM/login/userEnterThePasswordAs.png?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@When("User click the login button")
	public void userClickOnTheLoginButton() throws Throwable {

		try {

			// Random Sleep
			//Thread.sleep(
			//		commonService.generateNumber(commonService.generateNumber(1, commonService.generateNumber(10, 30)),
			//				commonService.generateNumber(31, commonService.generateNumber(40, 60))) * 2500);

			// Extent Report
			details.clear();
			details.add("Page URL: " + context.getDriver().getCurrentUrl());
			details.add("TimeStamp: " + new SimpleDateFormat("yyyy/MM/dd HH:mm:ssSSS").format(new Date()));
			extentReportService.insertPassedStep(context, "User click the login button", details);

			context.getExtentTestScenario().log(Status.PASS, "PASSED");
			extentReportService.attachedScreenshotToReport(context,
					"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEM/login/userClickOnTheLoginButton.png?raw=true");

			// Step Definition
			context.getWait().until(ExpectedConditions.presenceOfElementLocated(login_button));
			context.getDriver().findElement(login_button).click();

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context, "User click the login button", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEM/login/userClickOnTheLoginButton.png?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User is successfully login")
	public void userIsSuccessfullyLogin() throws InterruptedException, IOException {

		try {
			// Step Definition
			context.getWait().until(ExpectedConditions.presenceOfElementLocated(welcome_message));

			if (context.getDriver().findElement(welcome_message).getText() != null) {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				extentReportService.insertPassedStep(context, "User is successfully login", details);

				context.getExtentTestScenario().log(Status.PASS, "PASSED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEM/login/userIsSuccessfullyLogin.png?raw=true");

			} else {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				extentReportService.insertFailedStep(context, "User is successfully login", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEM/login/userIsSuccessfullyLogin.png?raw=true");

			}

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context, "User is successfully login", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEM/login/userIsSuccessfullyLogin.png?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
