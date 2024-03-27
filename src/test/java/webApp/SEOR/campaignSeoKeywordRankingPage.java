package webApp.SEOR;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.Status;

import helper.webAppContext;
import helper.webAppHelper;
import io.cucumber.java.en.When;
import testAuto.Service.ExtentReportService;

public class campaignSeoKeywordRankingPage extends webAppHelper {

	// Page Elements
	// ==========================================
	By keywordColumn_table = By
			.xpath("//table[@id='keyword-ranking-post-table']/tbody/tr/td[3]//div[@class='keyword-term']");
	By deleteButton_table = By.xpath("//button[text()='Delete']");
	By deleteButton_modal = By.xpath("//div[@class='modal-footer']/button[text()='Delete']");
	By addKeyword_button = By.xpath("//button[text()='Add Keywords']");
	By keywordInput_textArea = By.xpath("//textarea[@id='keyword-input']");
	By nextKeywordTrack_button = By.xpath(
			"//form[@id='campaign-keyword-wizard']//div[@id='add-keyword-form-container']//button[text()='Next ']");
	By nextKeywordLocation_button = By.xpath(
			"//form[@id='campaign-keyword-wizard']//div[@id='add-keyword-location-form-container']//button[text()='Next ']");
	By geoTarget_dropdown = By.xpath("//div[@id='s2id_geosearch']/a");
	By geoTarget_textField = By.xpath("(//div[@class='select2-search'])[3]//input");
	By geoTarget_list = By.xpath("//ul[@class='select2-results']/li[1]");
	By submitButton_modal = By.xpath("//div[@id='add-keyword-tags-form-container']//button");

	// Keyword Table Row Dropdown - Element Finder
	// ===================================
	private Select keywordTableRowDropDownElementFinder() {

		Select element = new Select(
				context.getDriver().findElement(By.xpath("//select[@name='keyword-ranking-post-table_length']")));

		return element;

	}

	private Select targetTypeDropDownModalElementFinder() {

		Select element = new Select(context.getDriver().findElement(By.xpath("//select[@id='target-type']")));

		return element;

	}

	// Declare Driver Instance
	// ==========================================
	private webAppContext context;

	public campaignSeoKeywordRankingPage(webAppContext context) {
		super();
		this.context = context;
	}

	// Declare Services
	// ==========================================
	ExtentReportService extentReportService = new ExtentReportService();

	// Declare Variables
	// ==========================================
	ArrayList<String> details = new ArrayList<String>();

	// Page Step Definition
	// =================================================
	@When("User remove a single {string} from Delete button")
	public void userRemoveASingleFromDeleteButton(String keyword) throws InterruptedException, IOException {

		try {

			// Hard Reload Page
			context.getDriver().executeScript("location.reload(true);");
			Thread.sleep(5000);
			context.getDriver().executeScript("document.body.style.zoom = '0.85'");
			Thread.sleep(5000);

			// select 100 from table dropdown
			keywordTableRowDropDownElementFinder().selectByVisibleText("100");

			// no anchor over table
			Thread.sleep(10000);

			// Search for Keyword Index and click delete button
			for (int i = 0; i < context.getDriver().findElements(keywordColumn_table).size(); i++) {

				System.out.println(context.getDriver().findElements(keywordColumn_table).get(i).getText());

				context.getDriver().executeScript("arguments[0].scrollIntoView(true);", context.getDriver()
						.findElement(By.xpath("//table[@id='keyword-ranking-post-table']/tbody/tr[" + (i + 1) + "]")));

				if (context.getDriver().findElements(keywordColumn_table).get(i).getText().contains(keyword)) {

					if (i > 2) {

						context.getDriver().executeScript("arguments[0].scrollIntoView(true);",
								context.getDriver().findElement(By
										.xpath("//table[@id='keyword-ranking-post-table']/tbody/tr[" + (i - 2) + "]")));

					}

					Thread.sleep(2000);

					context.getDriver().executeScript("arguments[0].click();", context.getDriver().findElement(By
							.xpath("//table[@id='keyword-ranking-post-table']/tbody/tr[" + (i + 1) + "]/td[1]//span")));

					Thread.sleep(5000);

					context.getDriver().executeScript("arguments[0].scrollIntoView(true);",
							context.getDriver().findElement(By.xpath("//section[@id='main-container']")));

					Thread.sleep(2000);

					context.getDriver().executeScript("arguments[0].click();",
							context.getDriver().findElement(deleteButton_table));

					Thread.sleep(2000);

					// Extent Report
					details.clear();
					details.add("Page URL: " + context.getDriver().getCurrentUrl());
					details.add("User confimrs deletion of keywords");
					extentReportService.insertPassedStep(context,
							"User remove a single " + keyword + " from Delete button", details);

					context.getExtentTestScenario().log(Status.PASS, "PASSED");
					extentReportService.attachedScreenshotToReport(context,
							"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEOR/campaignSeoKeywordRanking/userRemoveASingleFromDeleteButtonModalConfirmation.png?raw=true");

					context.getDriver().executeScript("arguments[0].click();",
							context.getDriver().findElement(deleteButton_modal));

					// no anchor over table
					Thread.sleep(2000);

				}

			}

			// Hard Reload Page
			Thread.sleep(5000);
			context.getDriver().executeScript("location.reload(true);");
			Thread.sleep(5000);
			context.getDriver().executeScript("document.body.style.zoom = '0.85'");

			// select 100 from table dropdown
			keywordTableRowDropDownElementFinder().selectByVisibleText("100");

			// no anchor over table
			Thread.sleep(10000);

			// Validate keyword no longer exist
			boolean keywordExist = true;
			while (keywordExist) {

				for (int i = 0; i < context.getDriver().findElements(keywordColumn_table).size(); i++) {

					if (context.getDriver().findElements(keywordColumn_table).get(i).getText().contains(keyword)) {

						// Extent Report
						details.clear();
						details.add("Page URL: " + context.getDriver().getCurrentUrl());
						details.add("User confimrs deletion of keywords is unsuccessful");
						extentReportService.insertFailedStep(context,
								"User remove a single " + keyword + " from Delete button", details);

						context.getExtentTestScenario().log(Status.FAIL, "FAILED");
						extentReportService.attachedScreenshotToReport(context,
								"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEOR/campaignSeoKeywordRanking/userRemoveASingleFromDeleteButtonSuccess.png?raw=true");

						keywordExist = false;
						break;

					}

				}

				if (keywordExist) {

					// Extent Report
					details.clear();
					details.add("Page URL: " + context.getDriver().getCurrentUrl());
					details.add("User confirms deletion of keywords is successful");
					extentReportService.insertPassedStep(context,
							"User remove a single " + keyword + " from Delete button", details);

					context.getExtentTestScenario().log(Status.PASS, "PASSED");
					extentReportService.attachedScreenshotToReport(context,
							"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEOR/campaignSeoKeywordRanking/userRemoveASingleFromDeleteButtonSuccess.png?raw=true");

				}

				// Exit While Loop
				break;

			}

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context, "User remove a single " + keyword + " from Delete button",
						details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEOR/campaignSeoKeywordRanking/userRemoveASingleFromDeleteButtonSuccess.png?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@When("User remove a multiple {string} from Delete button")
	public void userRemoveAMultipleFromDeleteButton(String string) throws InterruptedException, IOException {

		try {

			// Hard Reload Page
			context.getDriver().executeScript("location.reload(true);");
			Thread.sleep(5000);
			context.getDriver().executeScript("document.body.style.zoom = '0.85'");
			Thread.sleep(5000);

			// select 100 from table dropdown
			keywordTableRowDropDownElementFinder().selectByVisibleText("100");

			// no anchor over table
			Thread.sleep(10000);

			// Search for Keyword Index and click delete button
			List<String> keywords = Arrays.asList(string.split(","));
			for (int j = 0; j < keywords.size(); j++) {
				for (int i = 0; i < context.getDriver().findElements(keywordColumn_table).size(); i++) {

					System.out.println(keywords.get(j) + " vs "
							+ context.getDriver().findElements(keywordColumn_table).get(i).getText());

					context.getDriver().executeScript("arguments[0].scrollIntoView(true);",
							context.getDriver().findElement(
									By.xpath("//table[@id='keyword-ranking-post-table']/tbody/tr[" + (i + 1) + "]")));

					if (context.getDriver().findElements(keywordColumn_table).get(i).getText()
							.contains(keywords.get(j))) {

						if (i > 2) {

							context.getDriver().executeScript("arguments[0].scrollIntoView(true);",
									context.getDriver().findElement(By.xpath(
											"//table[@id='keyword-ranking-post-table']/tbody/tr[" + (i - 2) + "]")));

						}

						Thread.sleep(2000);

						context.getDriver().executeScript("arguments[0].click();",
								context.getDriver()
										.findElement(By.xpath("//table[@id='keyword-ranking-post-table']/tbody/tr["
												+ (i + 1) + "]/td[1]//span")));

						Thread.sleep(5000);

						context.getDriver().executeScript("arguments[0].scrollIntoView(true);",
								context.getDriver().findElement(By.xpath("//section[@id='main-container']")));

						Thread.sleep(5000);

						context.getDriver().executeScript("arguments[0].click();",
								context.getDriver().findElement(deleteButton_table));

						Thread.sleep(5000);

						context.getDriver().executeScript("arguments[0].click();",
								context.getDriver().findElement(deleteButton_modal));

						// Hard Reload Page
						Thread.sleep(5000);
						context.getDriver().executeScript("location.reload(true);");
						Thread.sleep(5000);
						context.getDriver().executeScript("document.body.style.zoom = '0.85'");

						// select 100 from table dropdown
						keywordTableRowDropDownElementFinder().selectByVisibleText("100");

						// no anchor over table
						Thread.sleep(10000);

					}

				}
			}

			// Hard Reload Page
			Thread.sleep(5000);
			context.getDriver().executeScript("location.reload(true);");
			Thread.sleep(5000);
			context.getDriver().executeScript("document.body.style.zoom = '0.85'");

			// select 100 from table dropdown
			keywordTableRowDropDownElementFinder().selectByVisibleText("100");

			// no anchor over table
			Thread.sleep(10000);

			// Validate keyword no longer exist
			boolean keywordExist = true;
			while (keywordExist) {

				for (int j = 0; j < keywords.size(); j++) {

					for (int i = 0; i < context.getDriver().findElements(keywordColumn_table).size(); i++) {

						if (context.getDriver().findElements(keywordColumn_table).get(i).getText()
								.contains(keywords.get(j))) {

							// Extent Report
							context.getExtentTestScenario()
									.createNode(new GherkinKeyword("When"),
											"User remove a \"" + keywords.get(j) + "\' from Delete button")
									.info("INFO: ");
							keywordExist = false;
							break;

						}

					}

					if (keywordExist) {
						// Extent Report
						context.getExtentTestScenario()
								.createNode(new GherkinKeyword("When"),
										"User remove a \"" + keywords.get(j) + "\' from Delete button")
								.pass("PASSED: ");

					}
				}

				// Exit While Loop
				break;

			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("Then"), "User remove a \'" + string + "\' from Delete button")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@When("User add a single {string}")
	public void userAddASingle(String keyword) throws InterruptedException, IOException {

		try {

			// Hard Reload Page
			context.getDriver().executeScript("location.reload(true);");
			Thread.sleep(5000);
			context.getDriver().executeScript("document.body.style.zoom = '0.65'");
			Thread.sleep(5000);

			// Click Add Keyword Button
			context.getDriver().executeScript("arguments[0].click();",
					context.getDriver().findElement(addKeyword_button));

			Thread.sleep(3000);

			// Populate Keywords TextArea
			context.getDriver().executeScript("arguments[0].click();",
					context.getDriver().findElement(keywordInput_textArea));
			Thread.sleep(3000);
			context.getDriver().findElement(keywordInput_textArea).sendKeys(keyword);

			// Click Next button
			Thread.sleep(5000);
			context.getDriver().executeScript("arguments[0].click();",
					context.getDriver().findElement(nextKeywordTrack_button));

			// Select from DropDown - Search Engine,Tagging and GeoType
			// Thread.sleep(3000);
			// targetTypeDropDownModalElementFinder().selectByVisibleText("City");

			// Populate and Select - GeoLocation
			// Thread.sleep(10000);
			// context.getDriver().executeScript("$('#geosearch').select2('open');");
			// Thread.sleep(5000);
			// context.getDriver().findElement(geoTarget_textField).sendKeys("Washington");
			// Thread.sleep(5000);
			// context.getDriver().executeScript("arguments[0].click();",
			// context.getDriver().findElement(geoTarget_list));

			// Select from DropDown - Search Engine,Tagging and GeoType - Country
			// context.getDriver().executeScript("$('#target-type').val('country').trigger('change');");
			// Populate and Select - GeoLocation - United States
			// context.getDriver().executeScript("$('#geosearch').val('2840').trigger('change');");

			// Select from DropDown - Search Engine,Tagging and GeoType - City
			context.getDriver().executeScript("$('#target-type').val('city').trigger('change');");

			// Populate and Select - GeoLocation - Aberdeen, Washington, United States
			context.getDriver().executeScript("$('#geosearch').val('1027494').trigger('change');");

			// Click Next button
			Thread.sleep(5000);
			context.getDriver().executeScript("arguments[0].click();",
					context.getDriver().findElement(nextKeywordLocation_button));

			// Extent Report
			details.clear();
			details.add("Page URL: " + context.getDriver().getCurrentUrl());
			details.add("User confimrs addition of keywords");
			extentReportService.insertPassedStep(context, "User add a single " + keyword, details);

			context.getExtentTestScenario().log(Status.PASS, "PASSED");
			extentReportService.attachedScreenshotToReport(context,
					"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEOR/campaignSeoKeywordRanking/userAddASingleModalConfirmation.png?raw=true");

			// Click Submit button
			Thread.sleep(5000);
			context.getDriver().executeScript("arguments[0].click();",
					context.getDriver().findElement(submitButton_modal));

			// Hard Reload Page
			Thread.sleep(5000);
			context.getDriver().executeScript("location.reload(true);");
			Thread.sleep(5000);
			context.getDriver().executeScript("document.body.style.zoom = '0.85'");

			// select 100 from table dropdown
			keywordTableRowDropDownElementFinder().selectByVisibleText("100");

			// no anchor over table
			Thread.sleep(10000);

			// Validate keyword exist
			boolean keywordExist = true;
			while (keywordExist) {

				for (int i = 0; i < context.getDriver().findElements(keywordColumn_table).size(); i++) {

					if (context.getDriver().findElements(keywordColumn_table).get(i).getText().contains(keyword)) {

						if (i > 2) {

							context.getDriver().executeScript("arguments[0].scrollIntoView(true);",
									context.getDriver().findElement(By.xpath(
											"//table[@id='keyword-ranking-post-table']/tbody/tr[" + (i - 2) + "]")));

						}

						else {

							context.getDriver().executeScript("arguments[0].scrollIntoView(true);",
									context.getDriver().findElement(
											By.xpath("//table[@id='keyword-ranking-post-table']/tbody/tr[" + i + "]")));
						}

						// Extent Report
						details.clear();
						details.add("Page URL: " + context.getDriver().getCurrentUrl());
						details.add("User successfully adds a keywords");
						extentReportService.insertPassedStep(context, "User add a single keyword: " + keyword, details);

						context.getExtentTestScenario().log(Status.PASS, "PASSED");
						extentReportService.attachedScreenshotToReport(context,
								"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEOR/campaignSeoKeywordRanking/userAddASingleSuccess.png?raw=true");

					}

				}

				if (keywordExist) {

					// Extent Report
					details.clear();
					details.add("Page URL: " + context.getDriver().getCurrentUrl());
					details.add("User successfully adds a keywords");
					extentReportService.insertFailedStep(context, "User add a single keyword: " + keyword, details);

					context.getExtentTestScenario().log(Status.FAIL, "FAILED");
					extentReportService.attachedScreenshotToReport(context,
							"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEOR/campaignSeoKeywordRanking/userAddASingleSuccess.png?raw=true");

				}

				// Exit While Loop
				break;

			}

		} catch (Exception e) {

			try {

				// Extent Report
				details.clear();
				details.add("Page URL: " + context.getDriver().getCurrentUrl());
				details.add("Error Message: " + e.getMessage());
				extentReportService.insertFailedStep(context, "User add a single keyword: " + keyword, details);

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context,
						"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/SEOR/campaignSeoKeywordRanking/userAddASingleSuccess.png?raw=true");

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@When("User add a multiple {string}")
	public void userAddAMultiple(String string) throws InterruptedException, IOException {

		try {

			// Hard Reload Page
			context.getDriver().executeScript("location.reload(true);");
			Thread.sleep(5000);
			context.getDriver().executeScript("document.body.style.zoom = '0.65'");
			Thread.sleep(5000);

			// Click Add Keyword Button
			context.getDriver().executeScript("arguments[0].click();",
					context.getDriver().findElement(addKeyword_button));

			Thread.sleep(3000);

			// Populate Keywords TextArea
			context.getDriver().executeScript("arguments[0].click();",
					context.getDriver().findElement(keywordInput_textArea));
			Thread.sleep(3000);
			List<String> keywords = Arrays.asList(string.split(","));

			for (int j = 0; j < keywords.size(); j++) {
				context.getDriver().findElement(keywordInput_textArea).sendKeys(keywords.get(j));
				context.getDriver().findElement(keywordInput_textArea).sendKeys(Keys.RETURN);
				Thread.sleep(1000);
			}

			// Click Next button
			Thread.sleep(3000);
			context.getDriver().executeScript("arguments[0].click();",
					context.getDriver().findElement(nextKeywordTrack_button));

			// Select from DropDown - Search Engine,Tagging and GeoType
			// Thread.sleep(3000);
			// targetTypeDropDownModalElementFinder().selectByVisibleText("City");

			// Populate and Select - GeoLocation
			// Thread.sleep(10000);
			// context.getDriver().executeScript("$('#geosearch').select2('open');");
			// Thread.sleep(5000);
			// context.getDriver().findElement(geoTarget_textField).sendKeys("Washington");
			// Thread.sleep(5000);
			// context.getDriver().executeScript("arguments[0].click();",
			// context.getDriver().findElement(geoTarget_list));

			// Select from DropDown - Search Engine,Tagging and GeoType - Country
			// context.getDriver().executeScript("$('#target-type').val('country').trigger('change');");
			// Populate and Select - GeoLocation - United States
			// context.getDriver().executeScript("$('#geosearch').val('2840').trigger('change');");

			// Select from DropDown - Search Engine,Tagging and GeoType - City
			context.getDriver().executeScript("$('#target-type').val('city').trigger('change');");

			// Populate and Select - GeoLocation - Aberdeen, Washington, United States
			context.getDriver().executeScript("$('#geosearch').val('1027494').trigger('change');");

			// Click Next button
			Thread.sleep(5000);
			context.getDriver().executeScript("arguments[0].click();",
					context.getDriver().findElement(nextKeywordLocation_button));

			// Click Submit button
			Thread.sleep(5000);
			context.getDriver().executeScript("arguments[0].click();",
					context.getDriver().findElement(submitButton_modal));

			// Hard Reload Page
			Thread.sleep(5000);
			context.getDriver().executeScript("location.reload(true);");
			Thread.sleep(5000);
			context.getDriver().executeScript("document.body.style.zoom = '0.85'");

			// select 100 from table dropdown
			keywordTableRowDropDownElementFinder().selectByVisibleText("100");

			// no anchor over table
			Thread.sleep(10000);

			// Validate keyword exist
			boolean keywordExist = true;
			while (keywordExist) {

				for (int j = 0; j < keywords.size(); j++) {

					for (int i = 0; i < context.getDriver().findElements(keywordColumn_table).size(); i++) {

						if (context.getDriver().findElements(keywordColumn_table).get(i).getText()
								.contains(keywords.get(j))) {

							// Extent Report
							context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
									"User add a \"" + keywords.get(j) + "\" keyword").pass("PASSED: ");
							keywordExist = false;
							break;

						}

					}

					if (keywordExist) {
						// Extent Report
						context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
								"User add a \"" + keywords.get(j) + "\" keyword").fail("FAILED: ");
						context.getExtentTestScenario().log(Status.FAIL, "Failed");

					}
				}

				// Exit While Loop
				break;

			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("Then"), "User add a \"" + string + "\" keyword")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
