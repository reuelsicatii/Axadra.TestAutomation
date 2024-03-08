package webApp.SEOR;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.Status;

import helper.webAppContext;
import helper.webAppHelper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import testAuto.Service.ExtentReportService;
import testAuto.Service.WebAuditService;

public class webAuditPage extends webAppHelper {

	WebAuditService webAuditService = new WebAuditService();

	// Page Elements
	// ==========================================
	// By recent_WebAuditReport =
	// By.xpath("//table[@id='webaudit-table']//tbody//i[first()]");
	By recent_WebAuditReport = By
			.xpath("(//table[@id='webaudit-table']//tbody//i[@class='fa fa-external-link-alt'])[1]");
	By urlWebAudit_inputfield = By.xpath("//input[@id='audit-url']");
	By urlWebAudit_button = By.xpath("//button[contains(text(),'Run Audit')]");

	// Declare Driver Instance
	// ==========================================
	private webAppContext context;

	public webAuditPage(webAppContext context) {
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
	@When("User generates a WebAuditReport")
	public void userGeneratesAWebAuditReport() throws Throwable {

		// Step Definition
		String url = webAuditService.RetrieveURLFrom("PROD_CENTRAL");
		System.out.println("URL from SERVICE:" + url);

		try {
			// Step Definition
			context.getDriver().findElement(urlWebAudit_inputfield).sendKeys(url);
			Thread.sleep(2000);
			context.getDriver().findElement(urlWebAudit_button).click();
			
			
			// Extent Report
			details.clear();
			details.add("Page URL: " + context.getDriver().getCurrentUrl());
			details.add("Additional Details: " + "Populate WebAudit textfield with " + url + "and click RUN AUDIT button");
			extentReportService.insertPassedStep(context,
					"User generates a WebAuditReport", details);

			context.getExtentTestScenario().log(Status.PASS, "PASSED");
			extentReportService.attachedScreenshotToReport(context,
					"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEOR/webAudit/userGeneratesAWebAuditReport1.png?raw=true");
			

			// Check WebAudit Report is generated
			// ==================================================

			int x = 0;
			while (true) {

				try {

					if (x >= 420) {

						// Extent Report
						details.clear();
						details.add("Page URL: " + context.getDriver().getCurrentUrl());
						details.add("Additional Details: " + "Not able to generate WebAudit Report for " + url + "WebAudit Report generation, waiting for " + x + "sec");

						extentReportService.insertFailedStep(context, "User generates a WebAuditReport", details);

						context.getExtentTestScenario().log(Status.FAIL, "FAILED");
						extentReportService.attachedScreenshotToReport(context,
								"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEOR/webAudit/userGeneratesAWebAuditReport2.png?raw=true");

						// exit the loop
						System.out.println("Exiting whileloop");
						break;

					}

					else if (context.getDriver()
							.findElement(By.xpath(
									"(//table[@id='webaudit-table']//tbody//a[contains(text(), '" + url + "')])[1]"))
							.isDisplayed()) {

						
						// Extent Report
						details.clear();
						details.add("Page URL: " + context.getDriver().getCurrentUrl());
						details.add("Additional Details: " + "Able to generate WebAudit Report for " + url + "WebAudit Report generation, waiting for " + x + "sec");
						extentReportService.insertPassedStep(context,
								"User generates a WebAuditReport", details);

						context.getExtentTestScenario().log(Status.PASS, "PASSED");
						extentReportService.attachedScreenshotToReport(context,
								"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEOR/webAudit/userGeneratesAWebAuditReport2.png?raw=true");

						// exit the loop
						System.out.println("Exiting whileloop");
						break;

					}

				} catch (Exception e) {

					Thread.sleep(10000);
					x = x + 10;
					System.out.println("WebAudit Report generation, waiting for " + x + "sec");
				}

			}

		} catch (Exception e) {

			// Extent Report
			try {
					
				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Additional Details: " + "Not able to generate WebAudit Report for " + url);
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context, "User generates a WebAuditReport", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEOR/webAudit/userGeneratesAWebAuditReport2.png?raw=true");
				
				
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@When("User clicks the most recent WebAuditReport")
	public void userClicksTheMostRecentWebAuditReport() {

		try {
			// Step Definition
			context.getWait().until(ExpectedConditions.presenceOfElementLocated(recent_WebAuditReport));
			context.getDriver().executeScript("arguments[0].scrollIntoView(true);",
					context.getDriver().findElement(urlWebAudit_button));
			context.getDriver().findElement(recent_WebAuditReport).click();

			
			// Extent Report
			details.clear();
			details.add("Page URL: " + context.getDriver().getCurrentUrl());
			extentReportService.insertPassedStep(context, "User clicks the most recent WebAuditReport", details);

			context.getExtentTestScenario().log(Status.PASS, "PASSED");
			extentReportService.attachedScreenshotToReport(context, "https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

		} catch (Exception e) {

			// Extent Report
			try {
				
				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context, "User clicks the most recent WebAuditReport", details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context, "https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");
				
				
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Then("User sees a new tab is open rendering the WebAuditReport")
	public void userSeesANewTabIsOpenRederingTheWebAuditReport() {

		try {
			// Step Definition
			ArrayList<String> newTb = new ArrayList<String>(context.getDriver().getWindowHandles());
			context.getDriver().switchTo().window(newTb.get(1));

			// Extent Report
			details.clear();
			details.add("Page URL: " + context.getDriver().getCurrentUrl());
			extentReportService.insertPassedStep(context, "User sees a new tab is open redering the WebAuditReport", details);

			context.getExtentTestScenario().log(Status.PASS, "PASSED");
			extentReportService.attachedScreenshotToReport(context, "https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

		} catch (Exception e) {

			// Extent Report
			try {
				
				
				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				extentReportService.insertFailedStep(context, "User sees a new tab is open redering the WebAuditReport", details);
				details.add("Error Message: " + e.getMessage());

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context, "https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");
				
				
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

}
