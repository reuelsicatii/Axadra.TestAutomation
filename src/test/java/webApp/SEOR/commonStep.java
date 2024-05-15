package webApp.SEOR;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.By;

import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.Status;

import helper.webAppContext;
import helper.webAppHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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

	// Declare Variables
	// ==========================================
	ArrayList<String> details = new ArrayList<String>();
	// HashMap<String, Long> loadTime = new HashMap<String, Long>();

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

			// Extent Report
			details.clear();
			extentReportService.insertPassedStep(context, "User setup a " + browserName, details);

			context.getExtentTestScenario().log(Status.PASS, "PASSED");
			extentReportService.attachedScreenshotToReport(context,
					"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context, "User setup a " + browserName, details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Given("User navigates to {string} using {string}")
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

			try {

				// Test Server Browser -- NextRoll Inc Privacy
				context.getDriver()
						.findElement(
								By.xpath("//div[@id='adroll_consent_banner_container']//div[text()='Decline All']"))
						.click();

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				extentReportService.insertPassedStep(context, "User navigates to " + url + " using " + browserName,
						details);

				context.getExtentTestScenario().log(Status.PASS, "PASSED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			} catch (Exception e) {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context, "User navigates to " + url + " using " + browserName,
						details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");
			}

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
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

	@When("User navigates to {string}")
	public void userNavigatesTo(String url) {

		try {

			context.getDriver().get(url);

			// Extent Report
			details.clear();
			extentReportService.insertPassedStep(context, "User navigates to " + url, details);

			context.getExtentTestScenario().log(Status.PASS, "PASSED");
			extentReportService.attachedScreenshotToReport(context,
					"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
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

	@When("User switch to new tab")
	public void userSwitchToNewTab() {

		try {

			ArrayList<String> newTb = new ArrayList<String>(context.getDriver().getWindowHandles());
			context.getDriver().switchTo().window(newTb.get(1));

			// Extent Report
			details.clear();
			details.add("Page URL: " + context.getDriver().getCurrentUrl());
			extentReportService.insertPassedStep(context, "User switch to new tab", details);

			context.getExtentTestScenario().log(Status.PASS, "PASSED");
			extentReportService.attachedScreenshotToReport(context,
					"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context, "User switch to new tab", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

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
			details.clear();
			details.add("Page URL: " + context.getDriver().getCurrentUrl());
			extentReportService.insertPassedStep(context, "User switch back to previous tab", details);

			context.getExtentTestScenario().log(Status.PASS, "PASSED");
			extentReportService.attachedScreenshotToReport(context,
					"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context, "User switch back to previous tab", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@When("User captures startTime")
	public void userCapturesStartime() {

		// Captures startTime
		context.getLoadTime().put("startTime", System.currentTimeMillis());

	}

	@Then("User measures page load to be within {int}")
	public void userMeasuresPageLoadToBeWithin(Integer expectedPageLoad) {
		try {

			// Captures endTime
			context.getLoadTime().put("stopPageLoad", System.currentTimeMillis());
			long actualPageLoad = Math.subtractExact(context.getLoadTime().get("stopPageLoad"), context.getLoadTime().get("startTime"));

			if (actualPageLoad <= expectedPageLoad) {
				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Expected Page Load: " + expectedPageLoad);
				details.add("Actual Page Load: " + actualPageLoad);
				extentReportService.insertPassedStep(context, "User measures page load to be within" + expectedPageLoad,
						details);

				context.getExtentTestScenario().log(Status.PASS, "PASSED");
				extentReportService.attachedPageLoadToReport(context, expectedPageLoad, actualPageLoad);
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

			} else {
				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Expected Page Load: " + expectedPageLoad);
				details.add("Actual Page Load: " + actualPageLoad);
				extentReportService.insertFailedStep(context, "User measures page load to be within" + expectedPageLoad,
						details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedPageLoadToReport(context, expectedPageLoad, actualPageLoad);
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");
			}

		} catch (Exception e) {

			// Extent Report
			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				extentReportService.insertFailedStep(context, "User measures page load to be within" + expectedPageLoad,
						details);
				details.add("Error Message: " + e.getMessage());

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
