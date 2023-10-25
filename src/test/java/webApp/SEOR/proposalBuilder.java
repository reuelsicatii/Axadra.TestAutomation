package webApp.SEOR;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.Status;
import com.jayway.jsonpath.JsonPath;

import helper.webAppContext;
import helper.webAppHelper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import testAuto.Service.CommonService;
import testAuto.Service.CrmService;
import testAuto.Service.ProposalBuilderService;

public class proposalBuilder extends webAppHelper {

	CrmService crmService = new CrmService();
	CommonService commonService = new CommonService();
	ProposalBuilderService proposalBuilderService = new ProposalBuilderService();
	HashMap<String, String> contactDetails = new HashMap<String, String>();

	// Page Elements - Proposal Builder Page
	// ==========================================
	By createProposal_button = By.xpath("//div[@id='proposal-main-container']//a[text()='Create a Proposal']");
	By elipsesOption_button = By.xpath("//table[@id=\"proposals-table\"]//tr[1]/td[6]//span/i");
	By editProposal_button = By.xpath(
			"//table[@id=\"proposals-table\"]//tr[1]/td[6]//div[contains(@id, 'record-button')]//i[@class='fa fa-pencil']");

	// Page Elements - Create Proposal Builder Page
	// ==========================================
	By proposalTitle_textfield = By.xpath("//input[@id='proposal-title']");
	By selectAContact_textfield = By.xpath("//div[@id='proposal-main-container']//a[text()='Create a Proposal']");
	By writeAContact_textfield = By.xpath("//div[@id='select2-drop']//input");
	By addNewContact_link = By.xpath("(//a[text()='Add new contact'])[1]");
	By firstContact_list = By.xpath("//div[@id='select2-drop']//ul/li[1]");
	By firstNameProposal_textfield = By.xpath("//input[@id='proposal-first-name']");
	By lastNameProposal_textfield = By.xpath("//input[@id='proposal-last-name']");
	By emailAddressProposal_textfield = By.xpath("//input[@id='proposal-email']");
	By companyProposal_textfield = By.xpath("//input[@id='proposal-company']");
	By next_button = By.xpath("//button[@id='proposal-submit']");

	// Page Elements - Proposal Builder Editor Page
	// ====================================================
	By addASection_link = By.xpath("//a[@id='add-section']");
	By addProduct_button = By.xpath("//button[@id='addProductButton']");
	By savePropsal_button = By.xpath("//button[@id='saveProposal']");

	// Product Category - Element Finder
	// ===================================
	private By productCategoryFinder(String productCategory) {

		return By.xpath("//div[@id='productPanelAccordion']//h5[text()='" + productCategory + "']");

	}

	// Product Name - Element Finder
	// ===================================
	private By productNameFinder(String productName) throws Throwable {

		return By.xpath("//div[@id='productPanelAccordion']//input[@value='"
				+ proposalBuilderService.RetrieveProductIdFrom("PROD_CENTRAL", productName)
				+ "']/following-sibling::span");

	}

	// Declare Driver Instance
	// ==========================================
	private webAppContext context;

	public proposalBuilder(webAppContext context) {
		super();
		this.context = context;
	}

	// Page Step Definition
	// =================================================
	@When("User click on the createAProposal button")
	public void userClickOnTheCreateAProposalButton() {
		try {

			context.getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(createProposal_button));
			context.getDriver().findElement(createProposal_button).click();

			context.getExtentTestScenario()
					.createNode(new GherkinKeyword("When"), "User click on the createAProposal button").pass("PASSED:");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User click on the createAProposal button")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@When("User click on the editAProposal button")
	public void userClickOnTheEditAProposalButton() {
		try {

			context.getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elipsesOption_button));
			context.getDriver().findElement(elipsesOption_button).click();
			Thread.sleep(1000);
			context.getDriver().findElement(elipsesOption_button).click();

			Thread.sleep(3000);

			context.getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(editProposal_button));
			context.getDriver().findElement(editProposal_button).click();

			context.getExtentTestScenario()
					.createNode(new GherkinKeyword("When"), "User click on the editAProposal button").pass("PASSED:");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User click on the editAProposal button")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@When("User add a title to the Proposal")
	public void userAddATitleToTheProposal() {
		try {

			context.getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(proposalTitle_textfield));
			context.getDriver().findElement(proposalTitle_textfield).clear();
			context.getDriver().findElement(proposalTitle_textfield)
					.sendKeys("Digital Marketing Proposal - Automation");

			context.getExtentTestScenario().createNode(new GherkinKeyword("When"), "User add a title to the Proposal")
					.pass("PASSED:");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User add a title to the Proposal")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@When("User add an existing contact to the Proposal")
	public void userAddAnExistingContactToTheProposal() throws Throwable {
		try {

			// WorkAround - add existing contact -
			// =====================================================
			// Open newTab
			Random generator = new Random();
			int randomNum = generator.nextInt(50);
			context.getDriver().executeScript(
					"window.open(\"https://account.seoreseller.com/proposals/getContacts?search=Company&page="
							+ randomNum + "\")");

			// switch to new tab
			ArrayList<String> newTb = new ArrayList<String>(context.getDriver().getWindowHandles());
			context.getDriver().switchTo().window(newTb.get(1));

			// context.getDriver().get("https://account.seoreseller.com/proposals/getContacts?search=company&page="
			// + randomNum);
			Thread.sleep(10000);
			String jsonResponse = context.getDriver().findElement(By.xpath("//pre")).getText();
			System.out.println("Response Body: " + jsonResponse);
			String contactID = JsonPath.read(jsonResponse, "$.items.[0].id").toString();
			System.out.println("Contact ID: " + contactID);
			commonService.attachedScreenshotToReport("Response JSON - ContactID", context);

			// close newTab
			context.getDriver().executeScript("window.close()");

			// switch back to previous tab
			context.getDriver().switchTo().window(newTb.get(0));

			// Add existing contact
			context.getDriver().executeScript("$('#proposal-contacts').val('" + contactID + "').trigger('change');");

			contactDetails.put("firstName", crmService.generateRandomString(6));
			contactDetails.put("lastName", crmService.generateRandomString(6));
			contactDetails.put("company", crmService.generateRandomString(6) + " company");

			// populate firstName, lastname and company
			context.getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(firstNameProposal_textfield));
			context.getDriver().findElement(firstNameProposal_textfield).clear();
			context.getDriver().findElement(firstNameProposal_textfield).sendKeys(contactDetails.get("firstName"));
			context.getDriver().findElement(lastNameProposal_textfield).clear();
			context.getDriver().findElement(lastNameProposal_textfield).sendKeys(contactDetails.get("lastName"));
			context.getDriver().findElement(companyProposal_textfield).clear();
			context.getDriver().findElement(companyProposal_textfield).sendKeys(contactDetails.get("company"));
			commonService.attachedScreenshotToReport("Adding Existing Contact", context);

			// click next button
			context.getWait().until(ExpectedConditions.elementToBeClickable(next_button));
			context.getDriver().findElement(next_button).click();

			// handling incase changes were made to firstName, lastName and company
			try {
				context.getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
						By.xpath("//div[@class='modal-footer']//button[text()='Save changes']")));
				commonService.attachedScreenshotToReport("Saving changes on Existing Contact", context);
				context.getDriver().findElement(By.xpath("//div[@class='modal-footer']//button[text()='Save changes']"))
						.click();

				Thread.sleep(10000);

			} catch (Exception e) {
				System.err.println(e.getMessage());
			}

			context.getExtentTestScenario()
					.createNode(new GherkinKeyword("When"), "User add an existing contact to the Proposal")
					.pass("PASSED:");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User add an existing contact to the Proposal")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@When("User add a new contact to the Proposal")
	public void userAddANewContactToTheProposal() throws Throwable {
		try {

			contactDetails.put("firstName", crmService.generateRandomString(6));
			contactDetails.put("lastName", crmService.generateRandomString(6));
			contactDetails.put("emailAdd", crmService.generateRandomString(6) + "@gmail.com");
			contactDetails.put("company", crmService.generateRandomString(6) + " company");

			// click "Add new contact" link
			context.getDriver().findElement(addNewContact_link).click();

			// populate firstName, lastname, emailAdd and company
			context.getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(firstNameProposal_textfield));
			context.getDriver().findElement(firstNameProposal_textfield).clear();
			context.getDriver().findElement(firstNameProposal_textfield).sendKeys(contactDetails.get("firstName"));
			context.getDriver().findElement(lastNameProposal_textfield).clear();
			context.getDriver().findElement(lastNameProposal_textfield).sendKeys(contactDetails.get("lastName"));
			commonService.attachedScreenshotToReport("Adding New Contact", context);
			context.getDriver().findElement(emailAddressProposal_textfield).clear();
			context.getDriver().findElement(emailAddressProposal_textfield).sendKeys(contactDetails.get("emailAdd"));
			context.getDriver().findElement(companyProposal_textfield).clear();
			context.getDriver().findElement(companyProposal_textfield).sendKeys(contactDetails.get("company"));
			commonService.attachedScreenshotToReport("Adding New Contact", context);

			// click next button
			context.getWait().until(ExpectedConditions.elementToBeClickable(next_button));
			context.getDriver().findElement(next_button).click();

			// handling incase changes were made to firstName, lastName and company
			try {
				context.getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
						By.xpath("//div[@class='modal-footer']//button[text()='Save changes']")));
				commonService.attachedScreenshotToReport("Saving changes on Existing Contact", context);
				context.getDriver().findElement(By.xpath("//div[@class='modal-footer']//button[text()='Save changes']"))
						.click();

				Thread.sleep(10000);

			} catch (Exception e) {
				System.err.println(e.getMessage());
			}

			context.getExtentTestScenario()
					.createNode(new GherkinKeyword("When"), "User add a new contact to the Proposal").pass("PASSED:");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User add a new contact to the Proposal")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@When("User add a {string} section from {string} to the Proposal")
	public void userAddASectionFromToTheProposal(String productName, String productCategory) throws Throwable {
		try {

			contactDetails.put("productName", productName);
			contactDetails.put("productCategory", productCategory);

			// sleep - no anchor
			Thread.sleep(5000);

			context.getWait().until(ExpectedConditions.elementToBeClickable(addASection_link));
			context.getDriver().findElement(addASection_link).click();

			Thread.sleep(2000);
			context.getDriver().findElement(productCategoryFinder(productCategory)).click();

			Thread.sleep(2000);
			context.getDriver().findElement(productNameFinder(productName)).click();

			Thread.sleep(2000);
			context.getWait().until(ExpectedConditions.elementToBeClickable(addProduct_button));
			context.getDriver().findElement(addProduct_button).click();

			context.getExtentTestScenario()
					.createNode(new GherkinKeyword("When"),
							"User add a " + productName + " section from " + productCategory + " to the Proposal")
					.pass("PASSED:");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User add a " + productName + " section from " + productCategory + " to the Proposal")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Then("User see {string} section is added to the Proposal")
	public void userSeeSectionIsAddedToTheProposal(String productName) {
		try {

			context.getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
					By.xpath("(//div[@class='proposal-editor-body']//h3[text()='" + productName + "'])[1]")));
			if (context.getDriver()
					.findElement(
							By.xpath("(//div[@class='proposal-editor-body']//h3[text()='" + productName + "'])[1]"))
					.getText() != null) {

				context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
						"User see " + productName + " section is added to the Proposal").pass("PASSED:");

			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User see " + productName + " section is added to the Proposal")
						.pass("PASSED:").fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@When("User click on the saveProposal button")
	public void userClickOnTheSaveProposalButton() {
		try {

			context.getWait().until(ExpectedConditions.elementToBeClickable(savePropsal_button));
			context.getDriver().findElement(savePropsal_button).click();

			context.getExtentTestScenario()
					.createNode(new GherkinKeyword("When"), "User click on the saveProposal button").pass("PASSED:");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User click on the saveProposal button")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Then("User see the proposal is created")
	public void userSeeTheProposalIsCreated() {
		try {

			// sleep - no anchor
			Thread.sleep(5000);
			boolean failedStep = true;
			for (int i = 0; i < context.getDriver().findElements(By.xpath("//table[@id='proposals-table']//tr")).size()
					+ 1; i++) {

				if (context.getDriver()
						.findElement(By.xpath("//table[@id='proposals-table']//tr[" + (i + 1) + "]/td[2]")).getText()
						.contains(contactDetails.get("firstName"))) {

					context.getExtentTestScenario()
							.createNode(new GherkinKeyword("When"), "User see the proposal is created").pass("PASSED:"
									+ "<br>" + "Client Actual: " + context.getDriver()
											.findElement(By
													.xpath("//table[@id='proposals-table']//tr[" + (i + 1) + "]/td[2]"))
											.getText()
									+ "<br>" + "Client Expected: " + contactDetails.get("firstName") + " "
									+ contactDetails.get("lastName") + "<br>" + "Products Actual: "
									+ context.getDriver()
											.findElement(By
													.xpath("//table[@id='proposals-table']//tr[" + (i + 1) + "]/td[3]"))
											.getText()
									+ "<br>" + "Products Expected: " + contactDetails.get("productName"));

					failedStep = false;
					break;
				}
			}

			if (failedStep) {

				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User see the proposal is created")
						.fail("FAILED:" + "<br>" + "Client Expected: " + contactDetails.get("firstName") + " "
								+ contactDetails.get("lastName") + "<br>" + "Products Expected: "
								+ contactDetails.get("productName"));

			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User see the proposal is created")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	private int contactDetails(String string) {
		// TODO Auto-generated method stub
		return 0;
	}

}
