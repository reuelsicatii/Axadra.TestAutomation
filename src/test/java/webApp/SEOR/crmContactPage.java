package webApp.SEOR;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.Status;

import helper.webAppContext;
import helper.webAppHelper;
import io.cucumber.java.en.When;
import testAuto.Service.CrmService;

public class crmContactPage extends webAppHelper {

	CrmService crmService = new CrmService();

	// Page Elements
	// ==========================================
	By addContact_button = By.xpath("//div[@id='contacts-container']//button[text()='Add a contact']");

	By rowContactDetails_tableLink = By.xpath("(//table[@id='crm-contacts-table']//tbody/tr[1]//a)[1]");
	By delete_sectionButton = By.xpath("//div[@id='contact-details-container']//button[text()='Delete']");
	By deletContact_modalButton = By.xpath("(//button[text()='Delete'])[2]");

	By edit_sectionButton = By.xpath("//div[@id='contact-details-container']//img[contains(@src, 'edit')]");

	By website_sectionTextfield = By.xpath("//form[@id='business-contact-form']//input[@name='website']");
	By emailAddress_sectionTextfield = By.xpath("//form[@id='business-contact-form']//input[@name='email']");
	By company_sectionTextfield = By.xpath("//form[@id='business-contact-form']//input[@name='company']");
	By phone_sectionTextfield = By.xpath("//form[@id='business-contact-form']//input[@name='phone_1']");

	By street_sectionTextfield = By.xpath("//form[@id='business-contact-form']//input[@name='street']");
	By city_sectionTextfield = By.xpath("//form[@id='business-contact-form']//input[@name='city']");
	By state_sectionTextfield = By.xpath("//form[@id='business-contact-form']//input[@name='state']");
	By zip_sectionTextfield = By.xpath("//form[@id='business-contact-form']//input[@name='zip']");

	By facebook_sectionTextfield = By.xpath("//form[@id='business-contact-form']//input[@name='facebook']");
	By instagram_sectionTextfield = By.xpath("//form[@id='business-contact-form']//input[@name='instagram']");
	By twitter_sectionTextfield = By.xpath("//form[@id='business-contact-form']//input[@name='twitter']");
	By linkedin_sectionTextfield = By.xpath("//form[@id='business-contact-form']//input[@name='linkedin']");

	By saveChange_sectionButton = By.xpath("//form[@id='business-contact-form']//button[text()='Save Changes']");

	By website_sectionlabel = By
			.xpath("//div[@id='contact-details-container']//div[contains(@class, 'contact-website-container')]");
	By email_sectionlabel = By
			.xpath("//div[@id='contact-details-container']//div[contains(@class, 'contact-email-container')]");
	By phone_sectionlabel = By
			.xpath("//div[@id='contact-details-container']//div[contains(@class, 'contact-phone-container')]");
	By address_sectionlabel = By
			.xpath("//div[@id='contact-details-container']//div[contains(@class, 'contact-location-container')]");

	By company_sectionlabel = By.xpath("//div[@id='contact-details-container']//h4[contains(@class, 'business-name')]");

	By addNewContact_sectionbutton = By.xpath("//button[contains(text(), 'Add New Contact')]");
	By editContact_sectionbutton = By.xpath("(//div[@class='business-contact-list-container']//button)[1]");
	By deleteContact_sectionbutton = By.xpath("(//div[@class='business-contact-list-container']//button)[2]");
	By firstName_Modaltextfield = By.xpath("//input[@id='clFirstname']");
	By lastName_Modaltextfield = By.xpath("//input[@id='clLastname']");
	By email_Modaltextfield = By.xpath("//input[@id='clEmail']");
	By phone_Modaltextfield = By.xpath("//input[@id='clPhone']");
	By save_Modalbutton = By.xpath("//div[@class='modal-dialog']//button[contains(text(), 'Save')]");

	// Declare Driver Instance
	// ==========================================
	private webAppContext context;

	public crmContactPage(webAppContext context) {
		super();
		this.context = context;
	}

	// Data Elements
	// ==========================================
	String updatedWebsiteURL = "";
	String updatedEmailAddress = "";
	String updatedCompany = "";
	String updatedPhone = "";
	String[] updatedAddress = new String[] {};

	// Page Step Definition
	// =================================================
	@When("User delete single contact over contact details")
	public void userDeleteSingleContactOverContactDetails() throws Throwable {

		try {

			// no anchor
			Thread.sleep(5000);

			// get Company Name
			String companyName = context.getDriver()
					.findElement(By.xpath("//table[@id='crm-contacts-table']//tbody/tr[1]/td[2]/div")).getText();

			String emailAddress = context.getDriver()
					.findElement(By.xpath("//table[@id='crm-contacts-table']//tbody/tr[1]/td[4]/div")).getText();

			context.getWait().until(ExpectedConditions.presenceOfElementLocated(rowContactDetails_tableLink));
			context.getDriver().findElement(rowContactDetails_tableLink).click();

			// no anchor
			Thread.sleep(5000);

			context.getWait()
					.until(ExpectedConditions.visibilityOf(context.getDriver().findElement(delete_sectionButton)));
			context.getDriver().findElement(delete_sectionButton).click();

			context.getWait()
					.until(ExpectedConditions.visibilityOf(context.getDriver().findElement(deletContact_modalButton)));
			context.getDriver().findElement(deletContact_modalButton).click();

			// no anchor
			Thread.sleep(5000);
			String deletedContactURL = context.getDriver().getCurrentUrl();

			// navigate back to CRM Page
			context.getDriver().navigate().back();

			Thread.sleep(5000);
			System.out.println("...hard reloading the page");
			context.getDriver().executeScript("location.reload(true);");
			Thread.sleep(5000);

			while (true) {
				for (int i = 1; i < context.getDriver()
						.findElements(By.xpath("//table[@id='crm-contacts-table']/tbody/tr")).size() + 1; i++) {

					if (context.getDriver()
							.findElement(By.xpath("//table[@id='crm-contacts-table']/tbody/tr[" + i + "]/td[2]"))
							.getText().contains(companyName)
							&& context.getDriver()
									.findElement(
											By.xpath("//table[@id='crm-contacts-table']/tbody/tr[" + i + "]/td[4]"))
									.getText().contains(emailAddress)
							&& !deletedContactURL.contains("account.seoreseller.com/pro/crm/contacts/")) {

						context.getDriver().executeScript("arguments[0].scrollIntoView(true);", context.getDriver()
								.findElement(By.xpath("//table[@id='crm-contacts-table']/tbody/tr[" + i + "]/td[2]")));

						// Extent Report
						context.getExtentTestScenario()
								.createNode(new GherkinKeyword("When"),
										"User delete single contact over contact details" + "<br>" + "Company Name: "
												+ companyName + "<br>" + "Email Address: " + emailAddress + "<br>"
												+ "Contact Details URL: " + deletedContactURL)
								.fail("FAILED");
						context.getExtentTestScenario().log(Status.FAIL, "Failed");

						break;

					}

				}

				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User delete single contact over contact details"
								+ "<br>" + "Company Name: " + companyName + "<br>" + "Email Address: " + emailAddress)
						.pass("PASSED");
				break;

			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User delete single contact over contact details")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@When("User clicks on the contactDetails link")
	public void userClicksOnContactDetailsLink() throws Throwable {

		try {

			// no anchor
			Thread.sleep(5000);
			context.getDriver().executeScript("arguments[0].scrollIntoView(true);",
					context.getDriver().findElement(addContact_button));
			context.getWait().until(ExpectedConditions.presenceOfElementLocated(rowContactDetails_tableLink));
			context.getDriver().findElement(rowContactDetails_tableLink).click();

			// Extent Report
			context.getExtentTestScenario()
					.createNode(new GherkinKeyword("When"), "User clicks on the contact details link").pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User clicks on the contact details link")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@When("User clicks on the editContact button")
	public void userClicksOnTheEditContactButton() throws Throwable {

		try {

			// no anchor
			Thread.sleep(5000);

			context.getWait().until(ExpectedConditions.visibilityOfElementLocated(edit_sectionButton));
			context.getDriver().findElement(edit_sectionButton).click();

			// no anchor
			Thread.sleep(2000);

			// Extent Report
			context.getExtentTestScenario()
					.createNode(new GherkinKeyword("When"), "User clicks on the edit contact button").pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User clicks on the edit contact button")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@When("User updates websiteURL textfield with {string}")
	public void userUpdatesWebsiteURLTextfield(String websiteURL) throws Throwable {

		try {

			// no anchor
			Thread.sleep(2000);

			updatedWebsiteURL = "http://upd." + crmService.generateRandomString(8) + websiteURL;
			context.getWait().until(ExpectedConditions.presenceOfElementLocated(website_sectionTextfield));
			context.getDriver().findElement(website_sectionTextfield).clear();
			context.getDriver().findElement(website_sectionTextfield).sendKeys(updatedWebsiteURL);

			// Extent Report
			context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
					"User updates websiteURL textfield with " + updatedWebsiteURL).pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User updates websiteURL textfield with " + updatedWebsiteURL)
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@When("User updates emailAddress textfield with {string}")
	public void userUpdatesEmailAddressTextfield(String emailAddress) throws Throwable {

		try {

			// no anchor
			Thread.sleep(2000);

			updatedEmailAddress = "upd_" + crmService.generateRandomString(4) + "_" + emailAddress;
			context.getWait().until(ExpectedConditions.presenceOfElementLocated(emailAddress_sectionTextfield));
			context.getDriver().findElement(emailAddress_sectionTextfield).clear();
			context.getDriver().findElement(emailAddress_sectionTextfield).sendKeys(updatedEmailAddress);

			// Extent Report
			context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
					"User updates emailAddress textfield with " + updatedEmailAddress).pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User updates emailAddress textfield with " + updatedEmailAddress)
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@When("User updates company textfield with {string}")
	public void userUpdatesCompanyTextfield(String company) throws Throwable {

		try {

			// no anchor
			Thread.sleep(2000);

			updatedCompany = "upd_" + crmService.generateRandomString(8) + "" + company;
			context.getWait().until(ExpectedConditions.presenceOfElementLocated(company_sectionTextfield));
			context.getDriver().findElement(company_sectionTextfield).clear();
			context.getDriver().findElement(company_sectionTextfield).sendKeys(updatedCompany);

			// Extent Report
			context.getExtentTestScenario()
					.createNode(new GherkinKeyword("When"), "User updates company textfield with " + updatedCompany)
					.pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User updates company textfield with " + updatedCompany)
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@When("User populates phone textfield with {string}")
	public void userPopulatesPhoneTextfield(String phone) throws Throwable {

		try {

			// no anchor
			Thread.sleep(2000);

			updatedPhone = crmService.generateRandomPhoneNumber();
			context.getWait().until(ExpectedConditions.presenceOfElementLocated(phone_sectionTextfield));
			context.getDriver().findElement(phone_sectionTextfield).clear();
			context.getDriver().findElement(phone_sectionTextfield).sendKeys(updatedPhone);

			// Extent Report
			context.getExtentTestScenario()
					.createNode(new GherkinKeyword("When"), "User populates phone textfield with " + updatedPhone)
					.pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User populates phone textfield with " + updatedPhone)
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@When("User populates address textfield with {string}")
	public void userPopulatesAddressTextfield(String address) throws Throwable {

		try {

			// no anchor
			Thread.sleep(2000);

			updatedAddress = address.split(",");
			context.getWait().until(ExpectedConditions.presenceOfElementLocated(street_sectionTextfield));
			context.getDriver().findElement(street_sectionTextfield).clear();
			context.getDriver().findElement(street_sectionTextfield).sendKeys(updatedAddress[0]);

			context.getWait().until(ExpectedConditions.presenceOfElementLocated(city_sectionTextfield));
			context.getDriver().findElement(city_sectionTextfield).clear();
			context.getDriver().findElement(city_sectionTextfield).sendKeys(updatedAddress[1]);

			context.getWait().until(ExpectedConditions.presenceOfElementLocated(state_sectionTextfield));
			context.getDriver().findElement(state_sectionTextfield).clear();
			context.getDriver().findElement(state_sectionTextfield).sendKeys(updatedAddress[2]);

			context.getWait().until(ExpectedConditions.presenceOfElementLocated(zip_sectionTextfield));
			context.getDriver().findElement(zip_sectionTextfield).clear();
			context.getDriver().findElement(zip_sectionTextfield).sendKeys(updatedAddress[3]);

			// Extent Report
			context.getExtentTestScenario()
					.createNode(new GherkinKeyword("When"), "User populates phone textfield with " + updatedPhone)
					.pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User populates phone textfield with " + updatedPhone)
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@When("User populates facebook textfield with {string}")
	public void userPopulatesFacebookTextfield(String facebook) throws Throwable {

		try {

			// no anchor
			Thread.sleep(2000);

			context.getWait().until(ExpectedConditions.presenceOfElementLocated(facebook_sectionTextfield));
			context.getDriver().findElement(facebook_sectionTextfield).clear();
			context.getDriver().findElement(facebook_sectionTextfield).sendKeys(facebook);

			// Extent Report
			context.getExtentTestScenario()
					.createNode(new GherkinKeyword("When"), "User populates facebook textfield with " + facebook)
					.pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User populates facebook textfield with " + facebook)
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@When("User populates instagram textfield with {string}")
	public void userPopulatesInstagramTextfield(String instagram) throws Throwable {

		try {

			// no anchor
			Thread.sleep(2000);

			context.getWait().until(ExpectedConditions.presenceOfElementLocated(instagram_sectionTextfield));
			context.getDriver().findElement(instagram_sectionTextfield).clear();
			context.getDriver().findElement(instagram_sectionTextfield).sendKeys(instagram);

			// Extent Report
			context.getExtentTestScenario()
					.createNode(new GherkinKeyword("When"), "User populates instagram textfield with " + instagram)
					.pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User populates instagram textfield with " + instagram)
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@When("User populates twitter textfield with {string}")
	public void userPopulatesTwitterTextfield(String twitter) throws Throwable {

		try {

			// no anchor
			Thread.sleep(2000);

			context.getWait().until(ExpectedConditions.presenceOfElementLocated(twitter_sectionTextfield));
			context.getDriver().findElement(twitter_sectionTextfield).clear();
			context.getDriver().findElement(twitter_sectionTextfield).sendKeys(twitter);

			// Extent Report
			context.getExtentTestScenario()
					.createNode(new GherkinKeyword("When"), "User populates twitter textfield with " + twitter)
					.pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User populates twitter textfield with " + twitter)
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@When("User populates linkedin textfield with {string}")
	public void userPopulatesLinkedin_sectionTextfieldTextfield(String linkedin) throws Throwable {

		try {

			// no anchor
			Thread.sleep(2000);

			context.getWait().until(ExpectedConditions.presenceOfElementLocated(linkedin_sectionTextfield));
			context.getDriver().findElement(linkedin_sectionTextfield).clear();
			context.getDriver().findElement(linkedin_sectionTextfield).sendKeys(linkedin);

			// Extent Report
			context.getExtentTestScenario()
					.createNode(new GherkinKeyword("When"), "User populates linkedin textfield with " + linkedin)
					.pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User populates linkedin textfield with " + linkedin)
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@When("User clicks on the saveChanges button")
	public void userClicksOnTheSaveChangesButton() throws Throwable {

		try {

			// no anchor
			Thread.sleep(2000);

			context.getDriver().executeScript("arguments[0].scrollIntoView(true);",
					context.getDriver().findElement(saveChange_sectionButton));
			context.getWait().until(ExpectedConditions.visibilityOfElementLocated(saveChange_sectionButton));
			context.getDriver().findElement(saveChange_sectionButton).click();

			// Extent Report
			context.getExtentTestScenario()
					.createNode(new GherkinKeyword("When"), "User clicks on the saveChanges button").pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User clicks on the saveChanges button")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@When("User confirms {string} is updated successfully")
	public void userConfirmsIsUpdatedSuccessfully(String label) throws Throwable {

		try {

			// no anchor
			Thread.sleep(2000);

			// no anchor
			Thread.sleep(5000);
			context.getDriver().executeScript("arguments[0].scrollIntoView(true);",
					context.getDriver().findElement(delete_sectionButton));

			if (label.contains("url")
					&& context.getDriver().findElement(website_sectionlabel).getText().contains(updatedWebsiteURL)) {

				System.out.println(label);
				System.out.println(context.getDriver().findElement(website_sectionlabel).getText());
				System.out.println(updatedWebsiteURL);

				// Extent Report
				context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
						"User updates websiteURL textfield with " + updatedWebsiteURL + " is updated successfully")
						.pass("PASSED");

			} else if (label.contains("email")
					&& context.getDriver().findElement(email_sectionlabel).getText().contains(updatedEmailAddress)) {

				System.out.println(label);
				System.out.println(context.getDriver().findElement(email_sectionlabel).getText());
				System.out.println(updatedEmailAddress);

				// Extent Report
				context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
						"User updates emailAddress textfield with " + updatedEmailAddress + " is updated successfully")
						.pass("PASSED");

			}

			else if (label.contains("company")
					&& context.getDriver().findElement(company_sectionlabel).getText().contains(updatedCompany)) {

				System.out.println(label);
				System.out.println(context.getDriver().findElement(company_sectionlabel).getText());
				System.out.println(updatedCompany);

				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User updates company textfield with " + updatedCompany + " is updated successfully")
						.pass("PASSED");

			}

			else if (label.contains("808")
					&& context.getDriver().findElement(phone_sectionlabel).getText().contains(updatedPhone)) {

				System.out.println(label);
				System.out.println(context.getDriver().findElement(phone_sectionlabel).getText());
				System.out.println(updatedPhone);

				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User populates phone textfield with " + updatedPhone + " is updated successfully")
						.pass("PASSED");

			}

			else if (label.contains("3700 S Western Ave")
					&& context.getDriver().findElement(address_sectionlabel).getText().contains(updatedAddress[0])) {

				System.out.println(label);
				System.out.println(context.getDriver().findElement(address_sectionlabel).getText());

				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User populates address textfield with " + label + " is updated successfully")
						.pass("PASSED");

			}

			else if (label.contains("facebook") && context.getDriver().findElement(By.xpath(
					"(//div[@id='contact-details-container']//div[contains(@class, 'contact-social-networks')]/a)[1]"))
					.getAttribute("href").contains(label)) {

				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User populates facebook textfield with " + label + " is updated successfully")
						.pass("PASSED");
			}

			else if (label.contains("instagram") && context.getDriver().findElement(By.xpath(
					"(//div[@id='contact-details-container']//div[contains(@class, 'contact-social-networks')]/a)[2]"))
					.getAttribute("href").contains(label)) {

				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User populates instagram textfield with " + label + " is updated successfully")
						.pass("PASSED");
			}

			else if (label.contains("twitter") && context.getDriver().findElement(By.xpath(
					"(//div[@id='contact-details-container']//div[contains(@class, 'contact-social-networks')]/a)[3]"))
					.getAttribute("href").contains(label)) {

				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User populates twitter textfield with " + label + " is updated successfully")
						.pass("PASSED");
			}

			else if (label.contains("linkedin") && context.getDriver().findElement(By.xpath(
					"(//div[@id='contact-details-container']//div[contains(@class, 'contact-social-networks')]/a)[4]"))
					.getAttribute("href").contains(label)) {

				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User populates linkedin textfield with " + label + " is updated successfully")
						.pass("PASSED");
			}

			else {

				// Extent Report
				context.getExtentTestScenario().createNode(new GherkinKeyword("When"), "User updates NOT successfully")
						.fail("FAILED");
				context.getExtentTestScenario().log(Status.FAIL, "Failed");

			}

		} catch (

		Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User updates textfield with " + label + " is NOT updated successfully")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@When("User adds a contact over CRMContactDetailsBusinessInfoYourContact")
	public void userAddsAContactOverCRMContactDetailsBusinessInfoYourContact() throws Throwable {

		try {

			// no anchor
			Thread.sleep(2000);

			context.getDriver().executeScript("arguments[0].scrollIntoView(true);",
					context.getDriver().findElement(addNewContact_sectionbutton));
			context.getWait().until(ExpectedConditions.visibilityOfElementLocated(addNewContact_sectionbutton));
			context.getDriver().findElement(addNewContact_sectionbutton).click();

			// no anchor
			Thread.sleep(3000);

			String phone = crmService.generateRandomPhoneNumber();

			context.getDriver().findElement(firstName_Modaltextfield).sendKeys(crmService.generateRandomString(8));
			context.getDriver().findElement(lastName_Modaltextfield).sendKeys(crmService.generateRandomString(8));
			context.getDriver().findElement(email_Modaltextfield)
					.sendKeys(crmService.generateRandomString(8) + "@gmail.com");
			context.getDriver().findElement(phone_Modaltextfield).sendKeys(phone);
			context.getDriver().findElement(save_Modalbutton).click();

			// no anchor
			Thread.sleep(5000);

			boolean verdict = true;
			while (true) {

				for (int i = 1; i < context.getDriver()
						.findElements(By.xpath("//div[contains(@class, 'business-contact-list-container')]//tr")).size()
						+ 1; i++) {

					if (context.getDriver()
							.findElement(By.xpath(
									"//div[contains(@class, 'business-contact-list-container')]//tr[" + i + "]/td[4]"))
							.getText().contains(phone)) {
						// Extent Report
						context.getExtentTestScenario()
								.createNode(new GherkinKeyword("When"),
										"User adds a contact over CRMContactDetailsBusinessInfoYourContact")
								.pass("PASSED");

						verdict = false;
						break;

					}
				}

				// Extent Report
				if (verdict) {
					context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
							"User adds a contact over CRMContactDetailsBusinessInfoYourContact").fail("FAILED");
					context.getExtentTestScenario().log(Status.FAIL, "Failed");
				}
				break;

			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User adds a contact over CRMContactDetailsBusinessInfoYourContact")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
