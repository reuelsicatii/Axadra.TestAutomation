package webApp.SEOR;

import java.util.LinkedHashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.GherkinKeyword;

import helper.webAppContextDriver;
import helper.webAppHelper;
import io.cucumber.java.en.When;
import testAuto.Service.CrmService;

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

	By companyColumn_tableDropdown = By.xpath("(//div[@class='table-row'])[1]/div[2]/div[1]");
	By valueCompanyColumn_tableDropdown = By
			.xpath("(//div[@class='table-row'])[1]/div[2]/div[1]/div[2]/div[text()='Company']");
	By phoneColumn_tableDropdown = By.xpath("(//div[@class='table-row'])[1]/div[5]/div[1]");
	By valuePhoneColumn_tableDropdown = By
			.xpath("(//div[@class='table-row'])[1]/div[5]/div[1]/div[2]/div[text()='Phone']");
	By import_button = By.xpath("//button[@id='import-contact-btn']");
	By proceed1_button = By.xpath("//button[text()='Proceed']");
	By proceed2_button = By.xpath("//button[text()='PROCEED']");

	By row_tableCheckboxn = By.xpath("(//table[@id='crm-contacts-table']//tbody/tr[1]//span)[1]");
	By rowHeader_tableCheckboxn = By.xpath("(//table[@id='crm-contacts-table']//thead/tr//span)[1]");
	By delete_tableButton = By.xpath("//div[@id='selection-details']//button[text()='Delete']");
	By deletContact_modalButton = By.xpath("//button[text() ='Yes, delete contacts']");

	// Contact TableRow DropwDown - Element Finder
	// ===================================
	private Select contactTableRowDropDownElementFinder() {

		Select element = new Select(
				context.getDriver().findElement(By.xpath("//select[@name='crm-contacts-table_length']")));
		return element;

	}

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

	@When("User saves a contact from file with {string}")
	public void userSavesAContactFromFileWith(String companyName) throws Throwable {

		try {

			// no anchor over table
			Thread.sleep(10000);

			for (int i = 1; i < context.getDriver().findElements(By.xpath("//table[@id='crm-contacts-table']/tbody/tr"))
					.size() + 1; i++) {

				if (context.getDriver()
						.findElement(By.xpath("//table[@id='crm-contacts-table']/tbody/tr[" + i + "]/td[2]")).getText()
						.contains(companyName)) {

					context.getDriver().executeScript("arguments[0].scrollIntoView(true);", context.getDriver()
							.findElement(By.xpath("//table[@id='crm-contacts-table']/tbody/tr[" + i + "]/td[2]")));

					// Extent Report
					context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
							"User saves a contact from file with " + companyName).pass("PASSED");

					break;

				}

			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User saves a contact from file with " + companyName)
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@When("User saves a contact with {string} {string} {string}")
	public void userSavesAContact(String companyName, String emailAddress, String websiteURL) throws Throwable {

		try {

			// no anchor over table
			Thread.sleep(5000);

			for (int i = 1; i < context.getDriver().findElements(By.xpath("//table[@id='crm-contacts-table']/tbody/tr"))
					.size() + 1; i++) {

				if (context.getDriver()
						.findElement(By.xpath("//table[@id='crm-contacts-table']/tbody/tr[" + i + "]/td[2]")).getText()
						.contains(companyName)
						&& context.getDriver()
								.findElement(By.xpath("//table[@id='crm-contacts-table']/tbody/tr[" + i + "]/td[4]"))
								.getText().contains(emailAddress)
						&& context.getDriver()
								.findElement(By.xpath("//table[@id='crm-contacts-table']/tbody/tr[" + i + "]/td[5]"))
								.getText().contains(websiteURL)) {

					context.getDriver().executeScript("arguments[0].scrollIntoView(true);", context.getDriver()
							.findElement(By.xpath("//table[@id='crm-contacts-table']/tbody/tr[" + i + "]/td[2]")));

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

	@When("User uploads a file to the uploadFile textArea with {string}")
	public void userUploadsAFileToTheUploadFileTextArea(String FileName) throws Throwable {

		try {

			switch (FileName) {
			case "crm_exportfile_csv.csv":
				crmService.updateCSVFileRecord("original_crm_exportfile_csv.csv", "crm_exportfile_csv.csv");
				break;
			case "crm_exportfile_txt.txt":
				crmService.updateTXTFileRecord("original_crm_exportfile_txt.txt", "crm_exportfile_txt.txt");
				break;
			case "crm_exportfile_xls.xls":
				crmService.updateXLSFileRecord("crm_exportfile_xls.xls");
				break;
			case "crm_exportfile_xlsx.xlsx":
				crmService.updateXLSXFileRecord("crm_exportfile_xlsx.xlsx");
				break;
			default:
				System.out.println("Invalid FileName.");
			}

			context.getWait().until(ExpectedConditions.presenceOfElementLocated(uploadFile_textArea));
			context.getDriver().findElement(uploadFile_textArea)
					.sendKeys(System.getProperty("user.dir") + "\\data\\webApp.SEOR.crm\\" + FileName);

			// no anchor over table
			Thread.sleep(10000);

			// choose Company over Company Column
			context.getWait().until(ExpectedConditions.presenceOfElementLocated(companyColumn_tableDropdown));
			context.getDriver().findElement(companyColumn_tableDropdown).click();

			context.getWait().until(ExpectedConditions.presenceOfElementLocated(valueCompanyColumn_tableDropdown));
			context.getDriver().findElement(valueCompanyColumn_tableDropdown).click();

			// no anchor over table
			Thread.sleep(5000);

			// choose Phone over Phone Column
			context.getWait().until(ExpectedConditions.presenceOfElementLocated(phoneColumn_tableDropdown));
			context.getDriver().findElement(phoneColumn_tableDropdown).click();

			context.getWait().until(ExpectedConditions.presenceOfElementLocated(valuePhoneColumn_tableDropdown));
			context.getDriver().findElement(valuePhoneColumn_tableDropdown).click();

			// no anchor over table
			Thread.sleep(5000);

			// click Import button
			context.getWait().until(ExpectedConditions.visibilityOf(context.getDriver().findElement(import_button)));
			context.getDriver().findElement(import_button).click();

			// no anchor over table
			Thread.sleep(10000);

			// check if element is displayed
			if (FileName.contains("crm_exportfile_csv") || FileName.contains("crm_exportfile_txt")
					|| FileName.contains("crm_exportfile_xls") || FileName.contains("crm_exportfile_xlsx")) {
				context.getWait()
						.until(ExpectedConditions.visibilityOf(context.getDriver().findElement(proceed1_button)));
				context.getDriver().findElement(proceed1_button).click();
			}

			// Extent Report
			context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
					"User uploads a file to the uploadFile textArea with " + FileName).pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User uploads a file to the uploadFile textArea with " + FileName)
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@When("User clicks on the proceedSuccess button")
	public void userClicksOnTheProceedSuccessButton() throws Throwable {

		try {

			// no anchor over table
			Thread.sleep(15000);

			context.getWait().until(ExpectedConditions.visibilityOf(context.getDriver().findElement(proceed2_button)));
			context.getDriver().findElement(proceed2_button).click();

			// Extent Report
			context.getExtentTestScenario()
					.createNode(new GherkinKeyword("When"), "User clicks on the proceedSuccess button").pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User clicks on the proceedSuccess button")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@When("User delete single contact")
	public void userDeleteSingleContact() throws Throwable {

		try {

			// no anchor over table
			Thread.sleep(5000);

			// get Company Name
			String companyName = context.getDriver()
					.findElement(By.xpath("//table[@id='crm-contacts-table']//tbody/tr[1]/td[2]/div")).getText();

			String emailAddress = context.getDriver()
					.findElement(By.xpath("//table[@id='crm-contacts-table']//tbody/tr[1]/td[4]/div")).getText();

			context.getWait().until(ExpectedConditions.presenceOfElementLocated(row_tableCheckboxn));
			context.getDriver().findElement(row_tableCheckboxn).click();
			// context.getDriver().executeScript("arguments[0].click();",
			// context.getDriver().findElement(row_tableCheckboxn));

			context.getWait()
					.until(ExpectedConditions.visibilityOf(context.getDriver().findElement(delete_tableButton)));
			context.getDriver().findElement(delete_tableButton).click();

			context.getWait()
					.until(ExpectedConditions.visibilityOf(context.getDriver().findElement(deletContact_modalButton)));
			context.getDriver().findElement(deletContact_modalButton).click();

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
									.getText().contains(emailAddress)) {

						context.getDriver().executeScript("arguments[0].scrollIntoView(true);", context.getDriver()
								.findElement(By.xpath("//table[@id='crm-contacts-table']/tbody/tr[" + i + "]/td[2]")));

						// Extent Report
						context.getExtentTestScenario()
								.createNode(new GherkinKeyword("When"), "User delete single contact" + "<br>"
										+ "Company Name: " + companyName + "<br>" + "Email Address: " + emailAddress)
								.fail("FAILED");

						break;

					}

				}

				// Extent Report
				context.getExtentTestScenario().createNode(new GherkinKeyword("When"), "User delete single contact"
						+ "<br>" + "Company Name: " + companyName + "<br>" + "Email Address: " + emailAddress)
						.pass("PASSED");
				break;

			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario().createNode(new GherkinKeyword("When"), "User delete single contact")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@When("User delete {string} contact")
	public void userDeleteSingleContact(String tableRowValue) throws Throwable {

		try {

			// Select from DropDown - Table Row
			contactTableRowDropDownElementFinder().selectByVisibleText(tableRowValue);

			// no anchor over table
			Thread.sleep(5000);

			// Loop through Table row - Get Keyword and its position
			LinkedHashMap<String, String> contactCompanyEmailBefore = new LinkedHashMap<String, String>();
			for (int b = 1; b < context.getDriver().findElements(By.xpath("//table[@id='crm-contacts-table']/tbody/tr"))
					.size() + 1; b++) {

				contactCompanyEmailBefore.put(
						context.getDriver()
								.findElement(
										By.xpath("//table[@id='crm-contacts-table']//tbody/tr[" + b + "]/td[2]/div"))
								.getText(),
						context.getDriver()
								.findElement(
										By.xpath("//table[@id='crm-contacts-table']//tbody/tr[" + b + "]/td[4]/div"))
								.getText());

			}

			// Iterating HashMap through for loop
			for (Map.Entry<String, String> set : contactCompanyEmailBefore.entrySet()) {

				// Printing all elements of a Map
				System.out.println("contactCompanyEmailBefore " + set.getKey() + " = " + set.getValue());
			}

			context.getDriver().executeScript("arguments[0].scrollIntoView(true);",
					context.getDriver().findElement(addContact_button));
			context.getWait().until(ExpectedConditions.presenceOfElementLocated(rowHeader_tableCheckboxn));
			context.getDriver().findElement(rowHeader_tableCheckboxn).click();

			context.getWait()
					.until(ExpectedConditions.visibilityOf(context.getDriver().findElement(delete_tableButton)));
			context.getDriver().findElement(delete_tableButton).click();

			context.getWait()
					.until(ExpectedConditions.visibilityOf(context.getDriver().findElement(deletContact_modalButton)));
			context.getDriver().findElement(deletContact_modalButton).click();

			Thread.sleep(5000);
			System.out.println("...hard reloading the page");
			context.getDriver().executeScript("location.reload(true);");
			Thread.sleep(5000);

			// Select from DropDown - Table Row
			contactTableRowDropDownElementFinder().selectByVisibleText(tableRowValue);

			// no anchor over table
			Thread.sleep(5000);

			// Loop through Table row - Get Keyword and its position
			LinkedHashMap<String, String> contactCompanyEmailAfter = new LinkedHashMap<String, String>();
			for (int a = 1; a < context.getDriver().findElements(By.xpath("//table[@id='crm-contacts-table']/tbody/tr"))
					.size() + 1; a++) {

				contactCompanyEmailAfter.put(
						context.getDriver()
								.findElement(
										By.xpath("//table[@id='crm-contacts-table']//tbody/tr[" + a + "]/td[2]/div"))
								.getText(),
						context.getDriver()
								.findElement(
										By.xpath("//table[@id='crm-contacts-table']//tbody/tr[" + a + "]/td[4]/div"))
								.getText());

			}

			// Iterating HashMap through for loop
			for (Map.Entry<String, String> set : contactCompanyEmailAfter.entrySet()) {

				// Printing all elements of a Map
				System.out.println("contactCompanyEmailBefore " + set.getKey() + " = " + set.getValue());
			}

			// Validating through delete contacts
			boolean multiContactsDeleted = true;
			for (Map.Entry<String, String> contactCompanyEmailBeforeset : contactCompanyEmailBefore.entrySet()) {
				for (Map.Entry<String, String> contactCompanyEmailAfterset : contactCompanyEmailAfter.entrySet()) {

					if (contactCompanyEmailAfterset.getKey().equals(contactCompanyEmailBeforeset.getKey())
							&& contactCompanyEmailAfterset.getValue().equals(contactCompanyEmailBeforeset.getValue())) {

						// Extent Report
						context.getExtentTestScenario()
								.createNode(new GherkinKeyword("When"),
										"User delete " + tableRowValue + " contacts" 
												+ "<br>" 
												+ "Company Name: " + contactCompanyEmailAfterset.getKey() 
												+ "<br>" 
												+ "Email Address: " + contactCompanyEmailAfterset.getValue())
								.fail("FAILED");

						multiContactsDeleted = false;
						break;

					}
				}
			}

			if (multiContactsDeleted) {
				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User delete " + tableRowValue + " contacts")
						.pass("PASSED");
			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User delete " + tableRowValue + " contacts")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
