package webApp.SEOR;

import java.net.MalformedURLException;
import java.util.ArrayList;

import org.openqa.selenium.By;

import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.Status;

import helper.webAppContextDriver;
import helper.webAppHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class commonStep extends webAppHelper {

	// Page Elements
	// ==========================================
	By password_textfield = By.cssSelector("input[formcontrolname='password']");
	By login_button = By.xpath("(//span[text()='Login'])[last()]/..");

	// Declare Driver Instance
	// ==========================================
	private webAppContextDriver context;

	public commonStep(webAppContextDriver context) {
		super();
		this.context = context;
	}

	// Page Step Definition
	// =================================================

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

			/*
			 * REMOVE THIS STEP since we handle pops-up over pop-up blocker extension in PROD
			 * 
			 * try {
			 * 
			 * // Test Server Browser -- NextRoll Inc Privacy context.getDriver()
			 * .findElement( By.
			 * xpath("//div[@id='adroll_consent_banner_container']//div[text()='Decline All']"
			 * )) .click();
			 * 
			 * } catch (Exception e) { e.printStackTrace(); }
			 */

			// Extent Report
			context.getExtentTestScenario()
					.createNode(new GherkinKeyword("Given"), "User navigates to " + url + " using" + browserName)
					.pass("PASSED");

		} catch (Exception e) {

			try {
				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("Given"), "User navigates to " + url + " using" + browserName)
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
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
			context.getExtentTestScenario().createNode(new GherkinKeyword("Given"), "User navigates to " + url)
					.pass("PASSED");

		} catch (Exception e) {

			try {
				// Extent Report
				context.getExtentTestScenario().createNode(new GherkinKeyword("Given"), "User navigates to " + url)
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
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
