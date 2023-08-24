package webApp.SEOR;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.aventstack.extentreports.GherkinKeyword;

import helper.webAppContextDriver;
import helper.webAppHelper;
import testAuto.Service.CrmService;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class crmPage extends webAppHelper {

	CrmService crmService = new CrmService();

	// Page Elements
	// ==========================================
	By addContact_button = By.xpath("//div[@id='contacts-container']//button[text()='Add a contact']");
	By websiteURL_textfield = By.xpath("(//div[@id='add-new-contact-modal']//input)[1]");
	By companyName_textfield = By.xpath("(//div[@id='add-new-contact-modal']//input)[2]");
	By emailAddress_textfield = By.xpath("(//div[@id='add-new-contact-modal']//input)[3]");
	By createContact_button = By.xpath("//div[@id='add-new-contact-modal']//button[text()='Create Contact']");
	By uploadFile_textArea = By.xpath("(//div[@id='add-new-contact-modal']//input)[4]");
	By okaySuccess_button = By.xpath("//div[@id='create-contact-alert-success']//button[text()='OKAY']");

	// Declare Driver Instance
	// ==========================================
	private webAppContextDriver context;

	public crmPage(webAppContextDriver context) {
		super();
		this.context = context;
	}

	// Page Step Definition
	// =================================================
	@When("User clicks on the addContact button")
	public void userClicksOnTheAddContactButton() throws Throwable {

		try {

			context.getWait().until(ExpectedConditions.visibilityOfElementLocated(addContact_button));
			context.getDriver().findElement(addContact_button).click();

			// Extent Report
			context.getExtentTestScenario()
					.createNode(new GherkinKeyword("When"), "User clicks on the addContact button").pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User clicks on the addContact button")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@When("User populates websiteURL textfield with {string}")
	public void userPopulatesWebsiteURLTextfield(String websiteURL) throws Throwable {

		try {
			context.getWait().until(ExpectedConditions.visibilityOfElementLocated(websiteURL_textfield));
			context.getDriver().findElement(websiteURL_textfield)
					.sendKeys(crmService.generateRandomString(8) + websiteURL);

			// Extent Report
			context.getExtentTestScenario()
					.createNode(new GherkinKeyword("When"), "User populates websiteURL textfield with " + websiteURL)
					.pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User populates websiteURL textfield with " + websiteURL)
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@When("User populates companyName textfield with {string}")
	public void userPopulatesCompanyNameTextfield(String companyName) throws Throwable {

		try {

			context.getWait().until(ExpectedConditions.visibilityOfElementLocated(companyName_textfield));
			context.getDriver().findElement(companyName_textfield)
					.sendKeys(crmService.generateRandomString(8) + companyName);

			// Extent Report
			context.getExtentTestScenario()
					.createNode(new GherkinKeyword("When"), "User populates companyName textfield with " + companyName)
					.pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User populates companyName textfield with " + companyName)
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@When("User populates emailAddress textfield with {string}")
	public void userPopulatesEmailAddressTextfield(String emailAddress) throws Throwable {

		try {

			context.getWait().until(ExpectedConditions.visibilityOfElementLocated(emailAddress_textfield));
			context.getDriver().findElement(emailAddress_textfield)
					.sendKeys(crmService.generateRandomString(8) + emailAddress);

			// Extent Report
			context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
					"User populates emailAddress textfield with " + emailAddress).pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User populates emailAddress textfield with " + emailAddress)
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@When("User clicks on the createContact button")
	public void userClicksOnTheCreateContactButton() throws Throwable {

		try {

			context.getWait().until(ExpectedConditions.presenceOfElementLocated(createContact_button));
			context.getDriver().findElement(createContact_button).click();

			// Extent Report
			context.getExtentTestScenario()
					.createNode(new GherkinKeyword("When"), "User clicks on the createContact button").pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User clicks on the createContact button")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@When("User clicks on the okaySuccess button")
	public void userClicksOnThekaySuccessButton() throws Throwable {

		try {

			context.getWait().until(ExpectedConditions.presenceOfElementLocated(okaySuccess_button));
			context.getDriver().findElement(okaySuccess_button).click();

			// Extent Report
			context.getExtentTestScenario()
					.createNode(new GherkinKeyword("When"), "User clicks on the okaySucess button").pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User clicks on the okaySucess button")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@When("User saves a contact with {string} {string} {string}")
	public void userSavesAContact(String websiteURL, String companyName, String emailAddress) throws Throwable {

		try {

			// no anchor over table
			Thread.sleep(5000);

			for (int i = 1; i < context.getDriver().findElements(By.xpath("//table[@id='crm-contacts-table']/tbody/tr"))
					.size() + 1; i++) {

				if (context.getDriver()
						.findElement(By.xpath("//table[@id='crm-contacts-table']/tbody/tr[" + i + "]/td[2]")).getText()
						.contains(websiteURL)
						&& context.getDriver()
								.findElement(By.xpath("//table[@id='crm-contacts-table']/tbody/tr[" + i + "]/td[4]"))
								.getText().contains(companyName)
						&& context.getDriver()
								.findElement(By.xpath("//table[@id='crm-contacts-table']/tbody/tr[" + i + "]/td[5]"))
								.getText().contains(emailAddress)) {

					// Extent Report
					context.getExtentTestScenario()
							.createNode(new GherkinKeyword("When"),
									"User saves a contact with " + websiteURL + " " + companyName + " " + emailAddress)
							.pass("PASSED");

				}

			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User saves a contact with " + websiteURL + " " + companyName + " " + emailAddress)
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
