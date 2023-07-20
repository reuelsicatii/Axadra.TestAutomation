package webApp.COMP;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.groovy.parser.antlr4.GroovyParser.IfElseStatementContext;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.GherkinKeyword;

import helper.webAppContextDriver;
import helper.webAppHelper;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class keywordPage extends webAppHelper {

	// Page Elements
	// ==========================================
	By add_button = By.xpath("//button[text()='Add']");
	By keywords_textarea = By.xpath("//textarea[@id='keywords']");
	By tagging_dropdown = By.xpath("//div[@id='s2id_cmbKeywordDefaultTagging']//span[2]");

	By geoTarget_dropdown = By.xpath("//div[@id='s2id_geosearch']/a");
	By geoTarget_textField = By.xpath("//div[@id='select2-drop']//input");
	By geoTarget_list = By.xpath("//div[@id='select2-drop']//li[1]/div");
	By save_button = By.xpath("//form[@id='form-add-keywords']//button[text()='Save']");
	By addKeyword_modalMessage = By.xpath("//div[@id='dialog-modal']//p");
	By close_modalButton = By.xpath("//div[@id='dialog-modal']//div[@class='modal-header']/button");
	By keywordColumn_table = By.xpath("(//table)[1]//tbody/tr/td[6]/span");
	By duplicateKeyword_modalMessage = By
			.xpath("//div[@class='modal-body']/div[@class='bootbox-body' and text()='Duplicate keyword']");

	By keywordCountEntries_span = By.xpath("//div[@id='keywords-table_wrapper']//span[3]");

	// Search Engine - Element Finder
	// ===================================
	private Select searchEngineElementFinder() {

		Select element = new Select(context.getDriver().findElement(By.xpath("//select[@id='search-engine']")));
		return element;

	}

	// Keyword Tagging - Element Finder
	// ===================================
	private Select keywordTaggingElementFinder() {

		Select element = new Select(
				context.getDriver().findElement(By.xpath("//select[@id='cmbKeywordDefaultTagging']")));

		return element;

	}

	// Geo Target Type - Element Finder
	// ===================================
	private Select geoTargetTypeElementFinder() {

		Select element = new Select(context.getDriver().findElement(By.xpath("//select[@id='geoSearchType']")));

		return element;

	}

	// Keyword Table Row Dropdown - Element Finder
	// ===================================
	private Select keywordTableRowDropDownElementFinder() {

		Select element = new Select(
				context.getDriver().findElement(By.xpath("//select[@name='keywords-table_length']")));

		return element;

	}

	// Declare Driver Instance
	// ==========================================
	private webAppContextDriver context;

	public keywordPage(webAppContextDriver context) {
		super();
		this.context = context;
	}

	// Page Step Definition
	// =================================================

	@Given("User add a single {string}")
	public void userAddASingle(String keyword) throws ClassNotFoundException {

		try {

			// Click Add Button
			Thread.sleep(5000);
			context.getWait().until(ExpectedConditions.visibilityOf(context.getDriver().findElement(add_button)));
			context.getDriver().findElement(add_button).click();

			// Populate Keywords TextArea
			context.getWait()
					.until(ExpectedConditions.visibilityOf(context.getDriver().findElement(keywords_textarea)));
			context.getDriver().findElement(keywords_textarea).click();
			context.getDriver().findElement(keywords_textarea).sendKeys(keyword);

			// Select from DropDown - Search Engine,Tagging and GeoType
			searchEngineElementFinder().selectByVisibleText("www.google.com");
			keywordTaggingElementFinder().selectByVisibleText("Tracked");
			geoTargetTypeElementFinder().selectByVisibleText("State");

			// Populate and Select - GeoLocation
			context.getDriver().findElement(geoTarget_dropdown).click();
			context.getWait()
					.until(ExpectedConditions.visibilityOf(context.getDriver().findElement(geoTarget_textField)));
			context.getDriver().findElement(geoTarget_textField).sendKeys("Washington");

			Thread.sleep(3000);

			context.getWait().until(ExpectedConditions.visibilityOf(context.getDriver().findElement(geoTarget_list)));
			context.getDriver().findElement(geoTarget_list).click();

			Thread.sleep(3000);

			// Click Save Button
			Thread.sleep(3000);
			context.getWait()
					.until(ExpectedConditions.elementToBeClickable(context.getDriver().findElement(save_button)));
			context.getDriver().findElement(save_button).click();

			// Extent Report
			context.getExtentTestScenario().createNode(new GherkinKeyword("When"), "User add a single " + keyword)
					.pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			context.getExtentTestScenario().createNode(new GherkinKeyword("When"), "User add a single " + keyword)
					.fail("FAILED: " + e.getMessage());
		}
	}

	@Given("User add a multiple {string}")
	public void userAddAMultiple(String string) throws ClassNotFoundException {

		List<String> keywords = Arrays.asList(string.split(","));

		try {

			// Click Add Button
			Thread.sleep(5000);
			context.getWait().until(ExpectedConditions.visibilityOf(context.getDriver().findElement(add_button)));
			context.getDriver().findElement(add_button).click();

			// Populate Keywords TextArea
			context.getWait()
					.until(ExpectedConditions.visibilityOf(context.getDriver().findElement(keywords_textarea)));
			context.getDriver().findElement(keywords_textarea).click();

			for (int i = 0; i < keywords.size(); i++) {

				context.getDriver().findElement(keywords_textarea).sendKeys(keywords.get(i));
				context.getDriver().findElement(keywords_textarea).sendKeys(Keys.RETURN);
				Thread.sleep(2000);

			}

			// Select from DropDown - Search Engine,Tagging and GeoType
			searchEngineElementFinder().selectByVisibleText("www.google.com");
			keywordTaggingElementFinder().selectByVisibleText("Tracked");
			geoTargetTypeElementFinder().selectByVisibleText("State");

			// Populate and Select - GeoLocation
			context.getDriver().findElement(geoTarget_dropdown).click();
			context.getWait()
					.until(ExpectedConditions.visibilityOf(context.getDriver().findElement(geoTarget_textField)));
			context.getDriver().findElement(geoTarget_textField).sendKeys("Washington");

			Thread.sleep(3000);

			context.getWait().until(ExpectedConditions.visibilityOf(context.getDriver().findElement(geoTarget_list)));
			context.getDriver().findElement(geoTarget_list).click();

			Thread.sleep(3000);

			// Click Save Button
			Thread.sleep(3000);
			context.getWait()
					.until(ExpectedConditions.elementToBeClickable(context.getDriver().findElement(save_button)));
			context.getDriver().findElement(save_button).click();

			// Extent Report
			context.getExtentTestScenario().createNode(new GherkinKeyword("When"), "User add a multiple " + string)
					.pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			context.getExtentTestScenario().createNode(new GherkinKeyword("When"), "User add a multiple " + string)
					.fail("FAILED: " + e.getMessage());
		}

	}

	@Given("User see Add Keyword modal")
	public void userSeeAddKeywordModal() throws IOException {

		try {
			// Keyword Successfully added Modal
			context.getWait()
					.until(ExpectedConditions.visibilityOf(context.getDriver().findElement(addKeyword_modalMessage)));

			if (context.getDriver().findElement(addKeyword_modalMessage).getText()
					.contains("Keywords were added to the campaign.")) {

				// Extent Report
				context.getExtentTestScenario().createNode(new GherkinKeyword("When"), "User see Add Keyword modal")
						.pass("PASSED");
			}

			else {

				// Extent Report
				context.getExtentTestScenario().createNode(new GherkinKeyword("When"), "User see Add Keyword modal")
						.fail("FAILED");

			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario().createNode(new GherkinKeyword("When"), "User see Add Keyword modal")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Given("User close Add Keyword modal")
	public void userCloseAddKeywordModal() throws IOException {

		try {
			// Step Definition
			context.getDriver().findElement(close_modalButton).click();

			// Extent Report
			context.getExtentTestScenario().createNode(new GherkinKeyword("When"), "User close Add Keyword modal")
					.pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario().createNode(new GherkinKeyword("When"), "User close Add Keyword modal")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Given("User see Duplicate Keyword modal")
	public void userSeeDuplicateKeywordModal() throws IOException {

		try {
			// Duplicate Keyword Modal
			context.getWait().until(
					ExpectedConditions.visibilityOf(context.getDriver().findElement(duplicateKeyword_modalMessage)));

			if (context.getDriver().findElement(duplicateKeyword_modalMessage).getText()
					.contains("Duplicate keyword")) {

				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User see Duplicate Keyword modal").pass("PASSED");
			}

			else {

				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User see Duplicate Keyword modal").fail("FAILED");

			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User close success message modal")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@When("User remove a single {string} from Trash button")
	public void userRemoveASingleFromTrashButton(String keyword) throws IOException {

		try {

			// Hard Reload Page
			Thread.sleep(5000);
			context.getDriver().executeScript("location.reload(true);");
			Thread.sleep(5000);

			// Search for Keyword Index and click delete button
			for (int i = 0; i < context.getDriver().findElements(keywordColumn_table).size(); i++) {

				if (context.getDriver().findElements(keywordColumn_table).get(i).getText().contains(keyword)) {
					context.getDriver().findElement(By.xpath("(//table)[1]//tbody/tr[" + (i + 1) + "]//button[1]"))
							.click();
					context.getWait().until(ExpectedConditions
							.visibilityOf(context.getDriver().findElement(By.xpath("(//button[text()='Delete'])[2]"))));
					context.getDriver().findElement(By.xpath("(//button[text()='Delete'])[2]")).click();
				}

			}

			// Hard Reload Page
			Thread.sleep(5000);
			context.getDriver().executeScript("location.reload(true);");
			Thread.sleep(5000);

			// Validate keyword no longer exist
			boolean keywordExist = true;
			while (keywordExist) {

				for (int i = 0; i < context.getDriver().findElements(keywordColumn_table).size(); i++) {

					if (context.getDriver().findElements(keywordColumn_table).get(i).getText().contains(keyword)) {

						// Extent Report
						context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
								"User remove a single '" + keyword + "' from Trash button").fail("FAILED");
						keywordExist = false;
						break;

					}

				}

				if (keywordExist) {
					// Extent Report
					context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
							"User remove a single '" + keyword + "' from Trash button").pass("PASSED");

				}

				// Exit While Loop
				break;

			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User remove a single '" + keyword + "' from Trash button")
						.fail("FAILED" + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@When("User remove a single {string} from Remove button")
	public void userRemoveASingleFromRemoveButton(String keyword) throws IOException {

		try {

			// Hard Reload Page
			Thread.sleep(5000);
			context.getDriver().executeScript("location.reload(true);");
			Thread.sleep(5000);

			// Search for Keyword Index and click delete button
			for (int i = 0; i < context.getDriver().findElements(keywordColumn_table).size(); i++) {

				if (context.getDriver().findElements(keywordColumn_table).get(i).getText().contains(keyword)) {
					context.getDriver().findElement(By.xpath("(//table)[1]//tbody/tr[" + (i + 1) + "]//input[1]"))
							.click();
					Thread.sleep(2000);
					context.getDriver().findElement(By.xpath("//button[text()='Remove']")).click();
					context.getWait().until(ExpectedConditions
							.visibilityOf(context.getDriver().findElement(By.xpath("(//button[text()='Delete'])[2]"))));
					context.getDriver().findElement(By.xpath("(//button[text()='Delete'])[2]")).click();

				}

			}

			// Hard Reload Page
			Thread.sleep(5000);
			context.getDriver().executeScript("location.reload(true);");
			Thread.sleep(5000);

			// Validate keyword no longer exist
			boolean keywordExist = true;
			while (keywordExist) {

				for (int i = 0; i < context.getDriver().findElements(keywordColumn_table).size(); i++) {

					if (context.getDriver().findElements(keywordColumn_table).get(i).getText().contains(keyword)) {

						// Extent Report
						context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
								"User remove a single '" + keyword + "' from Remove button").fail("FAILED");
						keywordExist = false;
						break;

					}

				}

				if (keywordExist) {
					// Extent Report
					context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
							"User remove a single '" + keyword + "' from Remove button").pass("PASSED");

				}

				// Exit While Loop
				break;

			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User remove a single '" + keyword + "' from Remove button")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@When("User remove a multiple {string} from Trash button")
	public void userRemoveAMultipleFromTrashButton(String string) throws IOException {

		try {

			List<String> keywords = Arrays.asList(string.split(","));

			// Hard Reload Page
			Thread.sleep(5000);
			context.getDriver().executeScript("location.reload(true);");
			Thread.sleep(5000);

			// Search for Keyword Index and tick checkbox
			for (int j = 0; j < keywords.size(); j++) {

				for (int i = 0; i < context.getDriver().findElements(keywordColumn_table).size(); i++) {

					if (context.getDriver().findElements(keywordColumn_table).get(i).getText()
							.contains(keywords.get(j))) {
						context.getDriver().findElement(By.xpath("(//table)[1]//tbody/tr[" + (i + 1) + "]//button[1]"))
								.click();
						Thread.sleep(2000);
						context.getWait().until(ExpectedConditions.visibilityOf(
								context.getDriver().findElement(By.xpath("(//button[text()='Delete'])[2]"))));
						context.getDriver().findElement(By.xpath("(//button[text()='Delete'])[2]")).click();
						context.getDriver().executeScript("location.reload(true);");
						Thread.sleep(5000);

					}
				}

			}

			// Hard Reload Page
			Thread.sleep(5000);
			context.getDriver().executeScript("location.reload(true);");
			Thread.sleep(5000);

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
											"User remove a multiple '" + keywords.get(j) + "' from Remove button")
									.fail("FAILED");
							keywordExist = false;
							break;

						}

					}

					if (keywordExist) {
						// Extent Report
						context.getExtentTestScenario()
								.createNode(new GherkinKeyword("When"),
										"User remove a multiple '" + keywords.get(j) + "' from Remove button")
								.pass("PASSED");

					}

				}

				// Exit While Loop
				break;

			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User remove a multiple '" + string + "' from Remove button")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@When("User remove a multiple {string} from Remove button")
	public void userRemoveAMultipleFromRemoveButton(String string) throws IOException {

		try {

			List<String> keywords = Arrays.asList(string.split(","));

			// Hard Reload Page
			Thread.sleep(5000);
			context.getDriver().executeScript("location.reload(true);");
			Thread.sleep(5000);

			// Search for Keyword Index and tick checkbox
			for (int j = 0; j < keywords.size(); j++) {

				for (int i = 0; i < context.getDriver().findElements(keywordColumn_table).size(); i++) {

					if (context.getDriver().findElements(keywordColumn_table).get(i).getText()
							.contains(keywords.get(j))) {
						context.getDriver().findElement(By.xpath("(//table)[1]//tbody/tr[" + (i + 1) + "]//input[1]"))
								.click();
						Thread.sleep(2000);

					}
				}

			}

			// Search for selected checkbox and delete keywords
			for (int i = 0; i < context.getDriver().findElements(keywordColumn_table).size(); i++) {

				if (context.getDriver().findElement(By.xpath("(//table)[1]//tbody/tr[" + (i + 1) + "]//input[1]"))
						.isSelected()) {

					Thread.sleep(3000);
					context.getDriver().findElement(By.xpath("//button[text()='Remove']")).click();
					context.getWait().until(ExpectedConditions
							.visibilityOf(context.getDriver().findElement(By.xpath("(//button[text()='Delete'])[2]"))));
					context.getDriver().findElement(By.xpath("(//button[text()='Delete'])[2]")).click();

					break;

				}
			}

			// Hard Reload Page
			Thread.sleep(5000);
			context.getDriver().executeScript("location.reload(true);");
			Thread.sleep(5000);

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
											"User remove a multiple '" + keywords.get(j) + "' from Remove button")
									.fail("FAILED");
							keywordExist = false;
							break;

						}

					}

					if (keywordExist) {
						// Extent Report
						context.getExtentTestScenario()
								.createNode(new GherkinKeyword("When"),
										"User remove a multiple '" + keywords.get(j) + "' from Remove button")
								.pass("PASSED");

					}

				}

				// Exit While Loop
				break;

			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User remove a multiple '" + string + "' from Remove button")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Then("User compares KeywordCount over Keyword {string}")
	public void userComparesKeywordCountOverKeyword(String string) {

		try {

			context.getFluentWait()
					.until(ExpectedConditions.visibilityOf(context.getDriver().findElement(keywordCountEntries_span)));
			keywordTableRowDropDownElementFinder().selectByVisibleText("100");

			// wait for table to load - no anchor
			Thread.sleep(10000);

			Integer keywordCountRow = 0;
			for (int i = 2; i < context.getDriver().findElements(By.xpath("//div[@id='keywords-table_wrapper']//li"))
					.size(); i++) {

				// Click on next active button
				// ====================================
				if (i > 2) {

					System.out.println("Click :" + i + "--" + context.getDriver()
							.findElement(By.xpath("//div[@id='keywords-table_wrapper']//li[" + i + "]/a")).getText());

					context.getDriver().findElement(By.xpath("//div[@id='keywords-table_wrapper']//li[" + i + "]/a"))
							.click();

					// wait for table to load - no anchor
					Thread.sleep(10000);

				}

				context.getFluentWait().until(ExpectedConditions.visibilityOf(
						context.getDriver().findElement(By.xpath("//table[@id='keywords-table']/tbody/tr[last()]"))));

				keywordCountRow = keywordCountRow
						+ context.getDriver().findElements(By.xpath("//table[@id='keywords-table']/tbody/tr")).size();

				System.out.println("Keyword Count Row:" + i + "--" + keywordCountRow);

			}

			if (keywordCountRow.toString().contains(context.getDriver().findElement(keywordCountEntries_span).getText())) {

				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User compares KeywordCount over Keyword " + string)
						.pass("PASSED: " + "<br>" + "Keyword Count Entries: "
								+ context.getDriver().findElement(keywordCountEntries_span).getText() + "<br>"
								+ "Keyword Count Row: " + keywordCountRow.toString());

			} else {

				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User compares KeywordCount over Keyword " + string)
						.fail("FAILED: " + "<br>" + "Keyword Count Entries: "
								+ context.getDriver().findElement(keywordCountEntries_span).getText() + "<br>"
								+ "Keyword Count Row: " + keywordCountRow.toString());
			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User compares KeywordCount over Keyword" + string)
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

}
