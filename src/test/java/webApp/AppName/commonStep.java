package webApp.AppName;

import java.net.MalformedURLException;
import java.util.ArrayList;

import org.openqa.selenium.By;

import helper.webAppContextDriver;
import helper.webAppHelper;
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
		context.setWait(initializeBrowserWait(context.getDriver(), 120));
		context.getDriver().get(url);
		context.getDriver().manage().window().maximize();
	}

	@When("User navigates to {string}")
	public void userNavigatesTo(String url) {
		context.getDriver().get(url);
	}
	
	@When("User switch to new tab")
	public void userSwitchToNewTab() {
		ArrayList<String> newTb = new ArrayList<String>(context.getDriver().getWindowHandles());
		context.getDriver().switchTo().window(newTb.get(1));	
	}	
	
	@When("User switch back to previous tab")
	public void userSwitchBackToPreviousTab() {
		ArrayList<String> newTb = new ArrayList<String>(context.getDriver().getWindowHandles());
		context.getDriver().switchTo().window(newTb.get(0));	
	}


}
