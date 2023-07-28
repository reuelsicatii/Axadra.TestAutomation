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

public class summaryPage extends webAppHelper {

	// Page Elements
	// ==========================================

	By keywordCountEntries_span = By.xpath("(//div[@id='rankings-summary-table_info']//span[3])[1]");

	// Keyword Table Row Dropdown - Element Finder
	// ===================================
	private Select keywordTableRowDropDownElementFinder() {

		Select element = new Select(
				context.getDriver().findElement(By.xpath("(//select[@name='keywords-table_length'])[1]")));

		return element;

	}

	private Select trendTableRowDropDownElementFinder() {

		Select element = new Select(
				context.getDriver().findElement(By.xpath("(//select[@name='rankings-table_length'])[1]")));

		return element;

	}

	private Select summaryTableRowDropDownElementFinder() {

		Select element = new Select(
				context.getDriver().findElement(By.xpath("(//select[@name='rankings-summary-table_length'])[1]")));

		return element;

	}

	// Declare Driver Instance
	// ==========================================
	private webAppContextDriver context;

	public summaryPage(webAppContextDriver context) {
		super();
		this.context = context;
	}

	// Page Step Definition
	// =================================================

	@SuppressWarnings("unchecked")
	@Then("User compares KeywordCount over Summary {string}")
	public void userComparesKeywordCountOverSummary(String string) {

		try {

			// wait for table to load - no anchor
			Thread.sleep(10000);

			context.getFluentWait()
					.until(ExpectedConditions.visibilityOf(context.getDriver().findElement(keywordCountEntries_span)));
			summaryTableRowDropDownElementFinder().selectByVisibleText("100");

			// wait for table to load - no anchor
			Thread.sleep(10000);

			Integer keywordCountRow = 0;
			for (int i = 2; i < context.getDriver()
					.findElements(By.xpath("(//div[@id='rankings-summary-table_wrapper']//ul[1])[1]/li")).size(); i++) {

				// Click on next active button
				// ====================================
				if (i > 2) {

					System.out.println("Click :" + i + "--" + context.getDriver()
							.findElement(
									By.xpath("(//div[@id='rankings-summary-table_wrapper']//ul[1])[1]/li[" + i + "]/a"))
							.getText());

					context.getDriver()
							.findElement(
									By.xpath("(//div[@id='rankings-summary-table_wrapper']//ul[1])[1]/li[" + i + "]/a"))
							.click();

					// wait for table to load - no anchor
					Thread.sleep(10000);

				}

				context.getFluentWait().until(ExpectedConditions.visibilityOf(context.getDriver()
						.findElement(By.xpath("//table[@id='rankings-summary-table']/tbody/tr[last()]"))));

				keywordCountRow = keywordCountRow + context.getDriver()
						.findElements(By.xpath("//table[@id='rankings-summary-table']/tbody/tr")).size();

				System.out.println("Keyword Count Row:" + i + "--" + keywordCountRow);

			}

			if (keywordCountRow.toString()
					.contains(context.getDriver().findElement(keywordCountEntries_span).getText())) {

				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User compares KeywordCount over Summary " + string)
						.pass("PASSED: " + "<br>" + "Keyword Count Entries: "
								+ context.getDriver().findElement(keywordCountEntries_span).getText() + "<br>"
								+ "Keyword Count Row: " + keywordCountRow.toString());

			} else {

				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User compares KeywordCount over Summary " + string)
						.fail("FAILED: " + "<br>" + "Keyword Count Entries: "
								+ context.getDriver().findElement(keywordCountEntries_span).getText() + "<br>"
								+ "Keyword Count Row: " + keywordCountRow.toString());
			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User compares KeywordCount over Summary" + string)
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@SuppressWarnings("unchecked")
	@When("User compares Count Entries against {string} vs {string} vs {string}")
	public void userComparesKeywordCountAgainstVsVs(String KeywordPage, String TrendPage, String SummaryPage) {

		try {

			// Keyword Page
			context.getDriver().get(KeywordPage);
			Thread.sleep(10000);
			context.getFluentWait().until(ExpectedConditions.visibilityOf(
					context.getDriver().findElement(By.xpath("//div[@id='keywords-table_wrapper']//span[3]"))));
			String keywordCountEntries = context.getDriver()
					.findElement(By.xpath("//div[@id='keywords-table_wrapper']//span[3]")).getText();

			// Trend Page
			context.getDriver().get(TrendPage);
			Thread.sleep(10000);
			context.getFluentWait().until(ExpectedConditions.visibilityOf(
					context.getDriver().findElement(By.xpath("(//div[@id='rankings-table_wrapper']//span[3])[1]"))));
			String trendCountEntries = context.getDriver()
					.findElement(By.xpath("(//div[@id='rankings-table_wrapper']//span[3])[1]")).getText();

			// Summary Page
			context.getDriver().get(SummaryPage);
			Thread.sleep(10000);
			context.getFluentWait().until(ExpectedConditions.visibilityOf(context.getDriver()
					.findElement(By.xpath("(//div[@id='rankings-summary-table_info']//span[3])[1]"))));
			String summaryCountEntries = context.getDriver()
					.findElement(By.xpath("(//div[@id='rankings-summary-table_info']//span[3])[1]")).getText();

			if (Integer.valueOf(keywordCountEntries).equals(Integer.valueOf(trendCountEntries))
					&& Integer.valueOf(keywordCountEntries).equals(Integer.valueOf(summaryCountEntries))
					&& Integer.valueOf(trendCountEntries).equals(Integer.valueOf(keywordCountEntries))
					&& Integer.valueOf(trendCountEntries).equals(Integer.valueOf(summaryCountEntries))
					&& Integer.valueOf(summaryCountEntries).equals(Integer.valueOf(keywordCountEntries))
					&& Integer.valueOf(summaryCountEntries).equals(Integer.valueOf(trendCountEntries))) {

				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User compares Count Entries against Keyword vs Trend vs Summary")
						.pass("PASSED: " + "<br>" + "Keyword Count Entries: " + keywordCountEntries + "<br>"
								+ "Trend Count Entries: " + trendCountEntries + "<br>" + "Summary Count Entries: "
								+ summaryCountEntries);

			} else {

				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User compares Count Entries against Keyword vs Trend vs Summary")
						.fail("FAILED: " + "<br>" + "Keyword Count Entries: " + keywordCountEntries + "<br>"
								+ "Trend Count Entries: " + trendCountEntries + "<br>" + "Summary Count Entries: "
								+ summaryCountEntries);
			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User compares Count Entries against Keyword vs Trend vs Summary")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unchecked")
	@When("User compares Count Tracked against {string} vs {string} vs {string}")
	public void userComparesCountTrackedAgainstVsVs(String keywordPage, String trendPage, String summaryPage) {

		try {

			// Keyword Page
			// =============================================================================================================
			context.getDriver().get(keywordPage);

			// wait for table to load - no anchor
			Thread.sleep(10000);

			// select 100 over dropdown
			keywordTableRowDropDownElementFinder().selectByVisibleText("100");

			// wait for table to load - no anchor
			Thread.sleep(10000);

			Integer keywordTrackedCountRow = 0;
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

				keywordTrackedCountRow = keywordTrackedCountRow + context.getDriver().findElements(By.xpath(
						"//table[@id='keywords-table']/tbody/tr/td[3]//i[contains(@class, 'fa fa-bookmark-o ')]"))
						.size();

				System.out.println("Keyword Tracked Count Row:" + i + "--" + keywordTrackedCountRow);
			}

			// Summary Page
			// =============================================================================================================
			context.getDriver().get(summaryPage);

			// wait for table to load - no anchor
			Thread.sleep(10000);

			// select 100 over dropdown
			summaryTableRowDropDownElementFinder().selectByVisibleText("100");

			// wait for table to load - no anchor
			Thread.sleep(10000);

			Integer summaryTrackedCountRow = 0;
			for (int j = 2; j < context.getDriver()
					.findElements(By.xpath("(//div[@id='rankings-summary-table_wrapper']//ul[1])[1]/li")).size(); j++) {

				// Click on next active button
				// ====================================
				if (j > 2) {

					System.out.println("Click :" + j + "--" + context.getDriver()
							.findElement(
									By.xpath("(//div[@id='rankings-summary-table_wrapper']//ul[1])[1]/li[" + j + "]/a"))
							.getText());

					context.getDriver()
							.findElement(
									By.xpath("(//div[@id='rankings-summary-table_wrapper']//ul[1])[1]/li[" + j + "]/a"))
							.click();

					// wait for table to load - no anchor
					Thread.sleep(10000);

				}

				context.getFluentWait().until(ExpectedConditions.visibilityOf(context.getDriver()
						.findElement(By.xpath("//table[@id='rankings-summary-table']/tbody/tr[last()]"))));

				summaryTrackedCountRow = summaryTrackedCountRow + context.getDriver().findElements(By.xpath(
						"//table[@id='rankings-summary-table']/tbody/tr/td[2]/div/div[contains(text(), 'Tracked')]"))
						.size();

				System.out.println("Summary Tracked Count Row:" + j + "--" + summaryTrackedCountRow);

			}

			// Trend Page
			// =============================================================================================================
			context.getDriver().get(trendPage);

			// wait for table to load - no anchor
			Thread.sleep(10000);

			// select 100 over dropdown
			trendTableRowDropDownElementFinder().selectByVisibleText("100");

			// wait for table to load - no anchor
			Thread.sleep(10000);

			Integer trendTrackedCountRow = 0;
			for (int k = 2; k < context.getDriver()
					.findElements(By.xpath("(//div[@id='rankings-table_wrapper']//ul[1])[1]/li")).size(); k++) {

				// Click on next active button
				// ====================================
				if (k > 2) {

					System.out.println("Click :" + k + "--"
							+ context.getDriver()
									.findElement(
											By.xpath("(//div[@id='rankings-table_wrapper']//ul[1])[1]/li[" + k + "]/a"))
									.getText());

					context.getDriver()
							.findElement(By.xpath("(//div[@id='rankings-table_wrapper']//ul[1])[1]/li[" + k + "]/a"))
							.click();

					// wait for table to load - no anchor
					Thread.sleep(10000);

				}

				context.getFluentWait().until(ExpectedConditions.visibilityOf(
						context.getDriver().findElement(By.xpath("//table[@id='rankings-table']/tbody/tr[last()]"))));

				trendTrackedCountRow = trendTrackedCountRow + context.getDriver()
						.findElements(By
								.xpath("//table[@id='rankings-table']/tbody/tr/td[3]/div[contains(text(), 'Tracked')]"))
						.size();

				System.out.println("Trend Tracked Count Row:" + k + "--" + trendTrackedCountRow);

			}

			if (Integer.valueOf(keywordTrackedCountRow).equals(Integer.valueOf(summaryTrackedCountRow))
					&& Integer.valueOf(keywordTrackedCountRow).equals(Integer.valueOf(trendTrackedCountRow))
					&& Integer.valueOf(summaryTrackedCountRow).equals(Integer.valueOf(keywordTrackedCountRow))
					&& Integer.valueOf(summaryTrackedCountRow).equals(Integer.valueOf(trendTrackedCountRow))
					&& Integer.valueOf(trendTrackedCountRow).equals(Integer.valueOf(keywordTrackedCountRow))
					&& Integer.valueOf(trendTrackedCountRow).equals(Integer.valueOf(summaryTrackedCountRow))) {

				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User compares Count Tracked against Keyword Page vs Summary Page vs Trend Page")
						.pass("PASSED: " + "<br>" + "Keyword Tracked Count Row: " + keywordTrackedCountRow.toString()
								+ "<br>" + "Trend Tracked Count Row: " + trendTrackedCountRow.toString() + "<br>"
								+ "Summary Tracked Count Row: " + summaryTrackedCountRow.toString());

			} else {

				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User compares Count Tracked against Keyword Page vs Summary Page vs Trend Page")
						.fail("FAILED: " + "<br>" + "Keyword Tracked Count Row: " + keywordTrackedCountRow.toString()
								+ "<br>" + "Trend Tracked Count Row: " + trendTrackedCountRow.toString() + "<br>"
								+ "Summary Tracked Count Row: " + summaryTrackedCountRow.toString());
			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User compares Count Tracked against Keyword Page vs Summary Page vs Trend Page")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
