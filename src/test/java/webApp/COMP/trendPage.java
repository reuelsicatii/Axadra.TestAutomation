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

public class trendPage extends webAppHelper {

	// Page Elements
	// ==========================================

	By keywordCountEntries_span = By.xpath("(//div[@id='rankings-table_wrapper']//span[3])[1]");

	// Keyword Table Row Dropdown - Element Finder
	// ===================================
	private Select keywordTableRowDropDownElementFinder() {

		Select element = new Select(
				context.getDriver().findElement(By.xpath("(//select[@name='rankings-table_length'])[1]")));

		return element;

	}

	// Declare Driver Instance
	// ==========================================
	private webAppContextDriver context;

	public trendPage(webAppContextDriver context) {
		super();
		this.context = context;
	}

	// Page Step Definition
	// =================================================

	@SuppressWarnings("unchecked")
	@Then("User compares KeywordCount over Trend {string}")
	public void userComparesKeywordCountOverTrend(String string) {

		try {

			// wait for table to load - no anchor
			Thread.sleep(10000);

			context.getFluentWait()
					.until(ExpectedConditions.visibilityOf(context.getDriver().findElement(keywordCountEntries_span)));
			keywordTableRowDropDownElementFinder().selectByVisibleText("100");

			// wait for table to load - no anchor
			Thread.sleep(10000);

			Integer keywordCountRow = 0;
			for (int i = 2; i < context.getDriver().findElements(By.xpath("(//div[@id='rankings-table_wrapper']//ul[1])[1]/li"))
					.size(); i++) {

				// Click on next active button
				// ====================================
				if (i > 2) {

					System.out.println("Click :" + i + "--" + context.getDriver()
							.findElement(By.xpath("(//div[@id='rankings-table_wrapper']//ul[1])[1]/li[" + i + "]/a")).getText());

					context.getDriver().findElement(By.xpath("(//div[@id='rankings-table_wrapper']//ul[1])[1]/li[" + i + "]/a"))
							.click();

					// wait for table to load - no anchor
					Thread.sleep(10000);

				}

				context.getFluentWait().until(ExpectedConditions.visibilityOf(
						context.getDriver().findElement(By.xpath("//table[@id='rankings-table']/tbody/tr[last()]"))));

				keywordCountRow = keywordCountRow
						+ context.getDriver().findElements(By.xpath("//table[@id='rankings-table']/tbody/tr")).size();

				System.out.println("Keyword Count Row:" + i + "--" + keywordCountRow);

			}

			if (keywordCountRow.toString()
					.contains(context.getDriver().findElement(keywordCountEntries_span).getText())) {

				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User compares KeywordCount over Trend " + string)
						.pass("PASSED: " + "<br>" + "Keyword Count Entries: "
								+ context.getDriver().findElement(keywordCountEntries_span).getText() + "<br>"
								+ "Keyword Count Row: " + keywordCountRow.toString());

			} else {

				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User compares KeywordCount over Trend " + string)
						.fail("FAILED: " + "<br>" + "Keyword Count Entries: "
								+ context.getDriver().findElement(keywordCountEntries_span).getText() + "<br>"
								+ "Keyword Count Row: " + keywordCountRow.toString());
			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User compares KeywordCount over Trend" + string)
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

}
