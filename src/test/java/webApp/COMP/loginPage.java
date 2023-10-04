package webApp.COMP;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.Status;

import helper.webAppContext;
import helper.webAppHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class loginPage extends webAppHelper {

	// Page Elements
	// ==========================================
	By username_textfield = By.xpath("//input[@id='identity']");
	By password_textfield = By.xpath("//input[@id='password']");
	By login_button = By.xpath("//input[@name='submit']");
	By welcome_message = By.xpath("//h4[contains(text(), 'Welcome back')]");

	// Declare Driver Instance
	// ==========================================
	private webAppContext context;

	public loginPage(webAppContext context) {
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
			context.getExtentTestScenario().log(Status.FAIL, "Failed");
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
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
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
			context.getWait().until(ExpectedConditions.visibilityOfElementLocated(login_button));
			context.getDriver().findElement(login_button).click();

			// Extent Report
			context.getExtentTestScenario().createNode(new GherkinKeyword("When"), "User click on the login button")
					.pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario().createNode(new GherkinKeyword("When"), "User click on the login button")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
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
			context.getWait().until(ExpectedConditions.visibilityOfElementLocated(welcome_message));

			if (context.getDriver().findElement(welcome_message).getText() != null) {

				// Extent Report
				context.getExtentTestScenario().createNode(new GherkinKeyword("Then"), "User is successfully login")
						.pass("PASSED");
			} else {
				// Extent Report
				context.getExtentTestScenario().createNode(new GherkinKeyword("Then"), "User is successfully login")
						.fail("FAILED");
			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario().createNode(new GherkinKeyword("Then"), "User is successfully login")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
