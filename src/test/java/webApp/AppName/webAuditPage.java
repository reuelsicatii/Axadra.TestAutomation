package webApp.AppName;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
		String url = webAuditURLService.RetrieveFromPRD();
		System.out.println("URL from SERVICE:" + url);
		context.getDriver().findElement(urlWebAudit_inputfield).sendKeys(url);
		Thread.sleep(2000);
		context.getDriver().findElement(urlWebAudit_button).click();

		// Check WebAudit Report is generated
		// ==================================================
		boolean whileloop = true;
		while (whileloop) {

			try {

				if (context.getDriver()
						.findElement(
								By.xpath("//table[@id='webaudit-table']//tbody//a[contains(text(), '" + url + "')]"))
						.isDisplayed()) {
					// exit the loop
					System.out.println("Exiting whileloop");
					whileloop = false;
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("WebAudit Report generation, waiting for 10sec");
				Thread.sleep(10000);
			}

		}

	}

	@When("User clicks the most recent WebAuditReport")
	public void userClicksTheMostRecentWebAuditReport() {
		context.getWait().until(ExpectedConditions.presenceOfElementLocated(recent_WebAuditReport));
		context.getDriver().executeScript("arguments[0].scrollIntoView(true);",
				context.getDriver().findElement(urlWebAudit_button));
		context.getDriver().findElement(recent_WebAuditReport).click();
	}

	@Then("User sees a new tab is open redering the WebAuditReport")
	public void userSeesANewTabIsOpenRederingTheWebAuditReport() {
		ArrayList<String> newTb = new ArrayList<String>(context.getDriver().getWindowHandles());
		context.getDriver().switchTo().window(newTb.get(1));
	}

}
