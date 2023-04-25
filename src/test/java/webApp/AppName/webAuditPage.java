package webApp.AppName;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.aventstack.extentreports.GherkinKeyword;

import helper.webAppContextDriver;
import helper.webAppHelper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import testAuto.Service.WebAuditURLService;

public class webAuditPage extends webAppHelper {

	static WebAuditURLService webAuditURLService = new WebAuditURLService();

	// Page Elements
	// ==========================================
	// By recent_WebAuditReport =
	// By.xpath("//table[@id='webaudit-table']//tbody//i[first()]");
	By recent_WebAuditReport = By.xpath("//table[@id='webaudit-table']//tbody//i[@class='fa fa-external-link-alt'][1]");
	By urlWebAudit_inputfield = By.xpath("//input[@id='audit-url']");
	By urlWebAudit_button = By.xpath("//button[contains(text(),'Run Audit')]");

	// Declare Driver Instance
	// ==========================================
	private webAppContextDriver context;

	public webAuditPage(webAppContextDriver context) {
		super();
		this.context = context;
	}

	// Page Step Definition
	// =================================================
	@When("User generates a WebAuditReport")
	public void userGeneratesAWebAuditReport() throws Throwable {

		try {
			// Step Definition
			String url = webAuditURLService.RetrieveFromPRD();
			System.out.println("URL from SERVICE:" + url);
			context.getDriver().findElement(urlWebAudit_inputfield).sendKeys(url);
			Thread.sleep(2000);
			context.getDriver().findElement(urlWebAudit_button).click();

			// Check WebAudit Report is generated
			// ==================================================
			boolean whileloop = true;
			Integer x = 0;
			
			while (whileloop) {

				try {

					if (context.getDriver()
							.findElement(By
									.xpath("(//table[@id='webaudit-table']//tbody//a[contains(text(), '" + url + "')])[1]"))
							.isDisplayed() || x.equals(420)) {
						// exit the loop
						System.out.println("Exiting whileloop");
						whileloop = false;
					}

				} catch (Exception e) {
					Thread.sleep(10000);
					System.out.println(e.getMessage());
					x = x + 10;
					System.out.println("WebAudit Report generation, waiting for " + x + "sec");

				}

			}

			// Extent Report
			context.getExtentTestScenario().createNode(new GherkinKeyword("When"), "User generates a WebAuditReport")
					.pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User generates a WebAuditReport")
						.fail("FAILED: " + e.getMessage());
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
			context.getExtentTestScenario()
					.createNode(new GherkinKeyword("When"), "User clicks the most recent WebAuditReport")
					.pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User clicks the most recent WebAuditReport")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Then("User sees a new tab is open redering the WebAuditReport")
	public void userSeesANewTabIsOpenRederingTheWebAuditReport() {

		try {
			// Step Definition
			ArrayList<String> newTb = new ArrayList<String>(context.getDriver().getWindowHandles());
			context.getDriver().switchTo().window(newTb.get(1));

			// Extent Report
			context.getExtentTestScenario()
					.createNode(new GherkinKeyword("When"), "User sees a new tab is open redering the WebAuditReport")
					.pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees a new tab is open redering the WebAuditReport")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
