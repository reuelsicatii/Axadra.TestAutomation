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
			keywordTableRowDropDownElementFinder().selectByVisibleText("100");

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
			context.getFluentWait().until(ExpectedConditions.visibilityOf(
					context.getDriver().findElement(By.xpath("(//div[@id='rankings-summary-table_info']//span[3])[1]"))));
			String summaryCountEntries = context.getDriver()
					.findElement(By.xpath("(//div[@id='rankings-summary-table_info']//span[3])[1]")).getText();

			if (Integer.valueOf(keywordCountEntries).equals(Integer.valueOf(trendCountEntries))
					&& Integer.valueOf(keywordCountEntries).equals(Integer.valueOf(summaryCountEntries))
					&& Integer.valueOf(trendCountEntries).equals(Integer.valueOf(keywordCountEntries))
					&& Integer.valueOf(trendCountEntries).equals(Integer.valueOf(summaryCountEntries))
					&& Integer.valueOf(summaryCountEntries).equals(Integer.valueOf(keywordCountEntries))
					&& Integer.valueOf(summaryCountEntries).equals(Integer.valueOf(trendCountEntries))) {

				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User compares Count Entries against Keyword vs Trend vs Summary")
						.pass(
							"PASSED: " 
							+ "<br>" + "Keyword Count Entries: " + keywordCountEntries
							+ "<br>" + "Trend Count Entries: " + trendCountEntries
							+ "<br>" + "Summary Count Entries: " + summaryCountEntries);

			} else {

				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User compares Count Entries against Keyword vs Trend vs Summary")
						.fail(
							"FAILED: " 
							+ "<br>" + "Keyword Count Entries: " + keywordCountEntries
							+ "<br>" + "Trend Count Entries: " + trendCountEntries
							+ "<br>" + "Summary Count Entries: " + summaryCountEntries);
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

}
