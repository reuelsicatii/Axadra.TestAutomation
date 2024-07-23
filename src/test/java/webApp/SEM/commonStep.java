package webApp.SEM;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.By;

import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.Status;

import helper.webAppContext;
import helper.webAppHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import testAuto.Service.CommonService;
import testAuto.Service.ExtentReportService;

public class commonStep extends webAppHelper {

	// Page Elements
	// ==========================================
	By password_textfield = By.cssSelector("input[formcontrolname='password']");
	By login_button = By.xpath("(//span[text()='Login'])[last()]/..");

	// Declare Driver Instance
	// ==========================================
	private webAppContext context;

	public commonStep(webAppContext context) {
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

	@Given("User setup a {string}")
	public void userSetupABrowser(String browserName) throws MalformedURLException, ClassNotFoundException {

		try {

			context.setDriver(initializeBrowser(browserName));
			context.setWait(initializeBrowserWait(context.getDriver(), 120));
			context.setFluentWait(initializeFluentWait(context.getDriver()));
			context.getDriver().manage().window().maximize();

			// Extent Report
			context.getExtentTestScenario().createNode(new GherkinKeyword("Given"), "User setup a " + browserName)
					.pass("PASSED");

		} catch (Exception e) {

			try {
				// Extent Report
				context.getExtentTestScenario().createNode(new GherkinKeyword("Given"), "User setup a " + browserName)
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Given("User navigate to {string} using {string}")
	public void userNavigatesToUsing(String url, String browserName)
			throws MalformedURLException, ClassNotFoundException {

		try {

			context.setDriver(initializeBrowser(browserName));
			context.setWait(initializeBrowserWait(context.getDriver(), 120));
			context.setFluentWait(initializeFluentWait(context.getDriver()));
			context.getDriver().manage().window().maximize();
			context.getDriver().get(url);

			// no anchor over table
			Thread.sleep(5000);

			// Extent Report
			details.clear();
			details.add("Page URL: " + context.getDriver().getCurrentUrl());
			extentReportService.insertPassedStep(context, "User navigates to " + url + " using " + browserName,
					details);

			context.getExtentTestScenario().log(Status.PASS, "PASSED");
			extentReportService.attachedScreenshotToReport(context,
					"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				extentReportService.insertFailedStep(context, "User navigates to " + url + " using " + browserName,
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

	@When("User navigate to {string}")
	public void userNavigatesTo(String url) {

		try {

			context.getDriver().get(url);

			// Extent Report
			context.getExtentTestScenario().createNode(new GherkinKeyword("Given"), "User navigates to " + url)
					.pass("PASSED");

			// Extent Report
			details.clear();
			details.add("Page URL: " + context.getDriver().getCurrentUrl());
			extentReportService.insertPassedStep(context, "User navigates to " + url, details);

			context.getExtentTestScenario().log(Status.PASS, "PASSED");
			extentReportService.attachedScreenshotToReport(context,
					"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				extentReportService.insertFailedStep(context, "User navigates to " + url, details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@When("User pause for {int} seconds")
	public void userPauseForSeconds(Integer pauseSeconds) {

		try {

			Thread.sleep(pauseSeconds * 1000);

			// Extent Report
			context.getExtentTestScenario()
					.createNode(new GherkinKeyword("Given"), "User pause for " + pauseSeconds + " seconds")
					.pass("PASSED");

			// Extent Report
			details.clear();
			// details.add("Page URL: " + context.getDriver().getCurrentUrl());
			extentReportService.insertPassedStep(context, "User pause for " + pauseSeconds + " seconds", details);

			context.getExtentTestScenario().log(Status.PASS, "PASSED");
			extentReportService.attachedScreenshotToReport(context,
					"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				// details.add("Page URL: " + context.getDriver().getCurrentUrl());
				extentReportService.insertFailedStep(context, "User pause for " + pauseSeconds + " seconds", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@When("User switch to new tab")
	public void userSwitchToNewTab() {

		try {

			ArrayList<String> newTb = new ArrayList<String>(context.getDriver().getWindowHandles());
			context.getDriver().switchTo().window(newTb.get(1));

			// Extent Report
			context.getExtentTestScenario().createNode(new GherkinKeyword("When"), "User switch to new tab")
					.pass("PASSED");

		} catch (Exception e) {

			try {
				// Extent Report
				context.getExtentTestScenario().createNode(new GherkinKeyword("When"), "User switch to new tab")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@When("User switch back to previous tab")
	public void userSwitchBackToPreviousTab() throws ClassNotFoundException {

		try {

			ArrayList<String> newTb = new ArrayList<String>(context.getDriver().getWindowHandles());
			context.getDriver().switchTo().window(newTb.get(0));

			// Extent Report
			context.getExtentTestScenario().createNode(new GherkinKeyword("When"), "User switch back to previous tab")
					.pass("PASSED");

		} catch (Exception e) {

			try {
				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User switch back to previous tab")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
