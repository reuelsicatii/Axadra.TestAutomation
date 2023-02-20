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
	public void userEnterTheUsernameAs(String username) {
		//System.out.println("BeforeScenario - Thread ID" + Thread.currentThread().getId());
		context.getDriver().findElement(username_textfield).sendKeys(username);
	}

	@Given("User enter the password as {string}")
	public void userEnterThePasswordAs(String password) throws IOException {
		//System.out.println("BeforeScenario - Thread ID" + Thread.currentThread().getId());
		context.getDriver().findElement(password_textfield).sendKeys(password);
	}

	@When("User click on the login button")
	public void userClickOnTheLoginButton() {
		//System.out.println("BeforeScenario - Thread ID" + Thread.currentThread().getId());
		context.getWait().until(ExpectedConditions.presenceOfElementLocated(login_button));
		context.getDriver().findElement(login_button).click();
	}

	@Then("User is successfully login")
	public void userIsSuccessfullyLogin() throws InterruptedException, IOException {
		//System.out.println("BeforeScenario - Thread ID" + Thread.currentThread().getId());
		context.getWait().until(ExpectedConditions.presenceOfElementLocated(welcome_message));
		assertEquals(context.getDriver().findElement(welcome_message).isDisplayed(), true);
	}


}
