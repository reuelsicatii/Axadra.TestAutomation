package webApp.SEM;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.Status;

import helper.webAppContext;
import helper.webAppHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import testAuto.Service.CommonService;

public class semAWSPage extends webAppHelper {

	// Service
	// ===============================================
	CommonService commonService = new CommonService();

	// Data Prop
	// ===============================================
	ArrayList<String> awsLinks = new ArrayList<String>();

	// Page Elements
	// ==========================================
	By accountID_textfield = By.xpath("//input[@id='account']");
	By username_textfield = By.xpath("//input[@id='username']");
	By password_textfield = By.xpath("//input[@id='password']");
	By signIn_button = By.xpath("//div[@id='input_signin_button']");

	// Declare Driver Instance
	// ==========================================
	private webAppContext context;

	public semAWSPage(webAppContext context) {
		super();
		this.context = context;
	}

	@Given("I copy the the href between {int} and {int} links")
	public void iCopyTheTheHrefBetweenAndLinks(Integer from, Integer to) {

		// context.getDriver().findElements(By.xpath("//a")).size()+1

		for (int i = from; i < to + 1; i++) {

			awsLinks.add(context.getDriver().findElement(By.xpath("//a[" + i + "]")).getAttribute("href"));
		}

		System.out.println(awsLinks);

	}

	@When("User enter the AWS accountID as {string}")
	public void userEnterTheAWSAccountIDAs(String accountID) {
		context.getDriver().findElement(accountID_textfield).clear();
		context.getDriver().findElement(accountID_textfield).sendKeys(accountID);
	}

	@When("User enter the AWS username as {string}")
	public void userEnterTheAWSUsernameAs(String username) {
		context.getDriver().findElement(username_textfield).sendKeys(username);
	}

	@When("User enter the AWS password as {string}")
	public void userEnterTheAWSPasswordAs(String password) {
		context.getDriver().findElement(password_textfield).sendKeys(password);
	}

	@When("User click the AWS SignIn button")
	public void userClickTheAWSSignInButton() throws InterruptedException {
		context.getDriver().findElement(signIn_button).click();

		Thread.sleep(5000);
	}

	@When("User delete the AWS Buckets")
	public void userDeleteTheAWSBucket() throws ClassNotFoundException, IOException {

		for (int i = 0; i < awsLinks.size(); i++) {

			try {

				context.getDriver().get(awsLinks.get(i));
				Thread.sleep(3000);
				// context.getDriver().executeScript("document.body.style.zoom = '0.65'");

				// sleep
				Thread.sleep(5000);

				context.getWait().until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//button[@data-testid='delete-objects-button']")));

				// Sort Files Descending
				context.getDriver().findElement(By.xpath("(//div[@data-focus-id='sorting-control-LastModified'])[1]"))
						.click();
				Thread.sleep(3000);
				context.getDriver().findElement(By.xpath("(//div[@data-focus-id='sorting-control-LastModified'])[1]"))
						.click();

				// sleep
				Thread.sleep(5000);
				commonService.attachedScreenshotToReport("Sorting Files", context);

				// Select Files for Deletion
				boolean DeleteFiles = false;
				for (int j = 1; j < context.getDriver()
						.findElements(By.xpath("//table[contains(@class, 'awsui_table')]//tr")).size() - 20; j++) {

					if (context.getDriver()
							.findElement(By.xpath("//table[contains(@class, 'awsui_table')]//tr[" + j + "]/td[4]"))
							.getText().contains("October 16, 2023")
							|| context.getDriver()
									.findElement(
											By.xpath("//table[contains(@class, 'awsui_table')]//tr[" + j + "]/td[4]"))
									.getText().contains("October 17, 2023")
							|| context.getDriver()
									.findElement(
											By.xpath("//table[contains(@class, 'awsui_table')]//tr[" + j + "]/td[4]"))
									.getText().contains("October 18, 2023")
							|| context.getDriver()
									.findElement(
											By.xpath("//table[contains(@class, 'awsui_table')]//tr[" + j + "]/td[4]"))
									.getText().contains("October 19, 2023")
							|| context.getDriver()
									.findElement(
											By.xpath("//table[contains(@class, 'awsui_table')]//tr[" + j + "]/td[4]"))
									.getText().contains("October 24, 2023")
							|| context.getDriver()
									.findElement(
											By.xpath("//table[contains(@class, 'awsui_table')]//tr[" + j + "]/td[4]"))
									.getText().contains("October 25, 2023")) {

						
						if (j > 2) {

							// scroll to view
							context.getDriver().executeScript("arguments[0].scrollIntoView(true);",
									context.getDriver()
											.findElement(By.xpath("//button[@data-testid='delete-objects-button']")));

						}

						// sleep
						Thread.sleep(2000);

						// click element
						context.getDriver()
								.findElement(By.xpath(
										"//table[contains(@class, 'awsui_table')]//tr[" + j + "]/td[1]//span/input"))
								.click();

						// sleep
						Thread.sleep(2000);

						DeleteFiles = true;

					}

				}

				// Delete Files
				if (DeleteFiles) {

					// sleep
					Thread.sleep(5000);
					commonService.attachedScreenshotToReport("Delete Files", context);

					// click delete_button
					context.getDriver().findElement(By.xpath("//button[@data-testid='delete-objects-button']")).click();

					// populate permanently_delete
					context.getWait().until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath("(//input[@id='awsui-input-0'])[2]")));
					context.getDriver().findElement(By.xpath("(//input[@id='awsui-input-0'])[2]"))
							.sendKeys("permanently delete");

					// sleep
					Thread.sleep(5000);
					commonService.attachedScreenshotToReport("Permanent Delete Files", context);

					// click deleteObject_button
					context.getDriver().findElement(By.xpath("(//button[contains(@class, 'awsui-button')])[2]"))
							.click();

					context.getExtentTestScenario()
							.createNode(new GherkinKeyword("When"), "User delete the AWS Buckets")
							.pass("PASSED:" + "<br>" + "Link: " + awsLinks.get(i));
					Thread.sleep(5000);
					commonService.attachedScreenshotToReport("Successful Deletion of Files", context);

				}

				else {

					context.getExtentTestScenario()
							.createNode(new GherkinKeyword("When"), "User delete the AWS Buckets")
							.pass("PASSED:" + "<br>" + "Link: " + awsLinks.get(i));
					Thread.sleep(5000);
					commonService.attachedScreenshotToReport("Nothing to Delete", context);

				}

			}

			catch (Exception e) {

				// Extent Report
				try {
					context.getExtentTestScenario()
							.createNode(new GherkinKeyword("When"), "User delete the AWS Buckets")
							.fail("FAILED: " + "<br>" + "Link: " + awsLinks.get(i) + "<br>" + e.getMessage());
					context.getExtentTestScenario().log(Status.FAIL, "Failed");
					commonService.attachedScreenshotToReport("Failed to Delete Files", context);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		}

	}
}
