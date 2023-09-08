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

public class crmContactPage extends webAppHelper {

	CrmService crmService = new CrmService();

	// Page Elements
	// ==========================================
	By rowContactDetails_tableLink = By.xpath("(//table[@id='crm-contacts-table']//tbody/tr[1]//a)[1]");
	By delete_sectionButton = By.xpath("//div[@id='contact-details-container']//button[text()='Delete']");
	By deletContact_modalButton = By.xpath("(//button[text()='Delete'])[2]");

	// Declare Driver Instance
	// ==========================================
	private webAppContextDriver context;

	public crmContactPage(webAppContextDriver context) {
		super();
		this.context = context;
	}

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
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
