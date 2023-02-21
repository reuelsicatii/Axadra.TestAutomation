package webApp.AppName;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import helper.webAppContextDriver;
import helper.webAppHelper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class webAuditPage extends webAppHelper {

	// Page Elements
	// ==========================================
	//By recent_WebAuditReport = By.xpath("//table[@id='webaudit-table']//tbody//i[first()]");
	By recent_WebAuditReport = By.xpath("//table[@id='webaudit-table']//tbody//i[@class='fa fa-external-link-alt'][1]");

	
	

	// Declare Driver Instance
	// ==========================================
	private webAppContextDriver context;

	public webAuditPage(webAppContextDriver context) {
		super();
		this.context = context;
	}

	// Page Step Definition
	// =================================================	
	@When("User clicks the most recent WebAuditReport")
	public void userClicksTheMostRecentWebAuditReport() {
		context.getWait().until(ExpectedConditions.presenceOfElementLocated(recent_WebAuditReport));
		context.getDriver().findElement(recent_WebAuditReport).click();	    
	}
	
	@Then("User sees a new tab is open redering the WebAuditReport")
	public void userSeesANewTabIsOpenRederingTheWebAuditReport() {
		ArrayList<String> newTb = new ArrayList<String>(context.getDriver().getWindowHandles());
		context.getDriver().switchTo().window(newTb.get(1));	    
	}


}
