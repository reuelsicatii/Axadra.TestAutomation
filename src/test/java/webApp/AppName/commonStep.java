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
	public void userNavigatesToUsing(String url, String browserName) throws MalformedURLException {
		// System.out.println("BeforeScenario - Thread ID" +
		// Thread.currentThread().getId());
		context.setDriver(initializeBrowser(browserName));
		context.setWait(initializeBrowserWait(context.getDriver()));
		context.getDriver().get(url);
		context.getDriver().manage().window().maximize();
	}

	@When("User navigates to {string}")
	public void userNavigatesTo(String url) {
		context.getDriver().get(url);
	}

}
