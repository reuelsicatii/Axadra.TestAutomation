package webApp.SEOR;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.Status;

import helper.webAppContext;
import helper.webAppHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import testAuto.Service.ExtentReportService;

public class loginPage extends webAppHelper {

	// SEOR - Page Elements
	// ==========================================
	By username_textfield = By.xpath("//input[@name='account_email']");
	By password_textfield = By.xpath("//input[@name='account_password']");
	By login_button = By.xpath("//button[text()='Sign In']");
	By welcome_message = By.xpath("//h2[contains(text(), 'Welcome to your dashboard!')]");

	// COMP - Page Elements
	// ==========================================
	By compassUsername_textfield = By.xpath("//input[@id='identity']");
	By compassPassword_textfield = By.xpath("//input[@id='password']");
	By compassLogin_button = By.xpath("//input[@name='submit']");
	By compassWelcome_message = By.xpath("//h4[contains(text(), 'Welcome back')]");

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
	
	// Declare Variables
	// ==========================================
	ArrayList<String> details = new ArrayList<String>();
	

	// Page Step Definition
	// =================================================

	@Given("User enter the username as {string}")
	public void userEnterTheUsernameAs(String username) throws ClassNotFoundException {

		try {
			// Step Definition
			context.getDriver().findElement(username_textfield).sendKeys(username);

			// Extent Report				
			details.clear();
			extentReportService.insertPassedStep(context, "User enter the username as " + username, details);				

			context.getExtentTestScenario().log(Status.PASS, "PASSED");
			extentReportService.attachedScreenshotToReport(context, "https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

		} catch (Exception e) {

			try {
				
				// Extent Report				
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " +  e.getMessage());
				extentReportService.insertFailedStep(context, "User enter the username as " + username, details);				

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context, "https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");
			
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Given("User enter the password as {string}")
	public void userEnterThePasswordAs(String password) throws IOException {

		try {
			// Step Definition
			context.getDriver().findElement(password_textfield).sendKeys(password);

			// Extent Report				
			details.clear();
			extentReportService.insertPassedStep(context, "User enter the password as " + password, details);				

			context.getExtentTestScenario().log(Status.PASS, "PASSED");
			extentReportService.attachedScreenshotToReport(context, "https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

		} catch (Exception e) {

			try {
				
				// Extent Report				
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " +  e.getMessage());
				extentReportService.insertFailedStep(context, "User enter the password as " + password, details);				

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context, "https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");
			
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@When("User click on the login button")
	public void userClickOnTheLoginButton() {

		try {
			// Step Definition
			context.getWait().until(ExpectedConditions.presenceOfElementLocated(login_button));
			context.getDriver().findElement(login_button).click();

			// Extent Report				
			details.clear();
			extentReportService.insertPassedStep(context, "User click on the login button", details);				

			context.getExtentTestScenario().log(Status.PASS, "PASSED");
			extentReportService.attachedScreenshotToReport(context, "https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

		} catch (Exception e) {

			try {
				
				// Extent Report				
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " +  e.getMessage());
				extentReportService.insertFailedStep(context, "User click on the login button", details);				

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context, "https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");
			
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
				extentReportService.insertPassedStep(context, "User is successfully login", details);				

				context.getExtentTestScenario().log(Status.PASS, "PASSED");
				extentReportService.attachedScreenshotToReport(context, "https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");
				
			} else {
				
				// Extent Report				
				details.clear();
				extentReportService.insertFailedStep(context, "User is successfully login", details);				

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context, "https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			}

		} catch (Exception e) {

			try {
				
				// Extent Report				
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " +  e.getMessage());
				extentReportService.insertFailedStep(context, "User is successfully login", details);				

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context, "https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");
			
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Given("User enter the Compass > username as {string}")
	public void userEnterTheCompassUsernameAs(String username) throws ClassNotFoundException {

		try {
			// Step Definition
			context.getDriver().findElement(compassUsername_textfield).sendKeys(username);

			// Extent Report				
			details.clear();
			extentReportService.insertPassedStep(context, "User enter the Compass > username as " + username, details);				

			context.getExtentTestScenario().log(Status.PASS, "PASSED");
			extentReportService.attachedScreenshotToReport(context, "https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

		} catch (Exception e) {

			try {
				
				// Extent Report				
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " +  e.getMessage());
				extentReportService.insertFailedStep(context, "User enter the Compass > username as " + username, details);				

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context, "https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");
			
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Given("User enter the Compass > password as {string}")
	public void userEnterTheCompassPasswordAs(String password) throws IOException {

		try {
			// Step Definition
			context.getDriver().findElement(compassPassword_textfield).sendKeys(password);

			// Extent Report				
			details.clear();
			extentReportService.insertPassedStep(context, "User enter the Compass > password as " + password, details);				

			context.getExtentTestScenario().log(Status.PASS, "PASSED");
			extentReportService.attachedScreenshotToReport(context, "https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

		} catch (Exception e) {

			try {
				
				// Extent Report				
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " +  e.getMessage());
				extentReportService.insertFailedStep(context, "User enter the Compass > password as " + password, details);				

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context, "https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");
			
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@When("User click on the Compass > login button")
	public void userClickOnTheCompassLoginButton() {

		try {
			// Step Definition
			context.getWait().until(ExpectedConditions.presenceOfElementLocated(compassLogin_button));
			context.getDriver().findElement(compassLogin_button).click();

			// Extent Report				
			details.clear();
			extentReportService.insertPassedStep(context, "User click on the Compass > login button", details);				

			context.getExtentTestScenario().log(Status.PASS, "PASSED");
			extentReportService.attachedScreenshotToReport(context, "https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

		} catch (Exception e) {

			try {
				
				// Extent Report				
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " +  e.getMessage());
				extentReportService.insertFailedStep(context, "User click on the Compass > login button", details);				

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context, "https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");
			
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User is successfully login on Compass")
	public void userIsSuccessfullyLoginOnCompass() throws InterruptedException, IOException {

		try {
			// Step Definition
			context.getWait().until(ExpectedConditions.presenceOfElementLocated(compassWelcome_message));

			if (context.getDriver().findElement(compassWelcome_message).getText() != null) {

				// Extent Report				
				details.clear();
				extentReportService.insertPassedStep(context, "User is successfully login", details);				

				context.getExtentTestScenario().log(Status.PASS, "PASSED");
				extentReportService.attachedScreenshotToReport(context, "https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");
				
			} else {

				// Extent Report				
				details.clear();
				extentReportService.insertFailedStep(context, "User is successfully login", details);				

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context, "https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			}

		} catch (Exception e) {

			try {
				
				// Extent Report				
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " +  e.getMessage());
				extentReportService.insertFailedStep(context, "User is successfully login", details);				

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context, "https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");
			
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
