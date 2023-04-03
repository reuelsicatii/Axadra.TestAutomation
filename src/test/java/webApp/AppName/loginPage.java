package webApp.AppName;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.aventstack.extentreports.GherkinKeyword;

import helper.webAppContextDriver;
import helper.webAppHelper;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class loginPage extends webAppHelper {

	// Page Elements
	// ==========================================
	By username_textfield = By.xpath("//input[@name='account_email']");
	By password_textfield = By.xpath("//input[@name='account_password']");
	By login_button = By.xpath("//button[text()='Sign In']");
	By welcome_message = By.xpath("//h2[contains(text(), 'Welcome to your dashboard!')]");

	// Declare Driver Instance
	// ==========================================
	private webAppContextDriver context;

	public loginPage(webAppContextDriver context) {
		super();
		this.context = context;
	}

	// Page Step Definition
	// =================================================

	@Given("User enter the username as {string}")
	public void userEnterTheUsernameAs(String username) throws ClassNotFoundException {

		try {
			// Step Definition
			context.getDriver().findElement(username_textfield).sendKeys(username);

			// Extent Report
			context.getExtentTestScenario()
					.createNode(new GherkinKeyword("When"), "User enter the username as " + username).pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			context.getExtentTestScenario()
					.createNode(new GherkinKeyword("When"), "User enter the username as " + username)
					.fail("FAILED: " + e.getMessage());
		}
	}

	@Given("User enter the password as {string}")
	public void userEnterThePasswordAs(String password) throws IOException {

		try {
			// Step Definition
			context.getDriver().findElement(password_textfield).sendKeys(password);

			// Extent Report
			context.getExtentTestScenario()
					.createNode(new GherkinKeyword("When"), "User enter the password as " + password).pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User enter the password as " + password)
						.fail("FAILED: " + e.getMessage());
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
			context.getExtentTestScenario().createNode(new GherkinKeyword("When"), "User click on the login button")
					.pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario().createNode(new GherkinKeyword("When"), "User click on the login button")
						.fail("FAILED: " + e.getMessage());
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
			assertEquals(context.getDriver().findElement(welcome_message).isDisplayed(), true);

			// Extent Report
			context.getExtentTestScenario().createNode(new GherkinKeyword("Then"), "User is successfully login")
					.pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario().createNode(new GherkinKeyword("Then"), "User is successfully login")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
