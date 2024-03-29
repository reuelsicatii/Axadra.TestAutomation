package webApp.COMP;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.Status;

import helper.webAppContext;
import helper.webAppHelper;
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
	private webAppContext context;

	public trendPage(webAppContext context) {
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
			trendTableRowDropDownElementFinder().selectByVisibleText("100");

			// wait for table to load - no anchor
			Thread.sleep(10000);

			Integer keywordCountRow = 0;
			for (int i = 2; i < context.getDriver()
					.findElements(By.xpath("(//div[@id='rankings-table_wrapper']//ul[1])[1]/li")).size(); i++) {

				// Click on next active button
				// ====================================
				if (i > 2) {

					System.out.println("Click :" + i + "--"
							+ context.getDriver()
									.findElement(
											By.xpath("(//div[@id='rankings-table_wrapper']//ul[1])[1]/li[" + i + "]/a"))
									.getText());

					context.getDriver()
							.findElement(By.xpath("(//div[@id='rankings-table_wrapper']//ul[1])[1]/li[" + i + "]/a"))
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
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User compares KeywordCount over Trend" + string)
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@SuppressWarnings("unchecked")
	@When("User compares Count Targeted against {string} vs {string} vs {string}")
	public void userComparesCountTargeteddAgainstVsVs(String keywordPage, String trendPage, String summaryPage) {

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

				keywordTrackedCountRow = keywordTrackedCountRow + context.getDriver()
						.findElements(By.xpath(
								"//table[@id='keywords-table']/tbody/tr/td[3]//i[contains(@class, 'fa fa-bookmark ')]"))
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
						"//table[@id='rankings-summary-table']/tbody/tr/td[2]/div/div[contains(text(), 'Targeted')]"))
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
						.findElements(By.xpath(
								"//table[@id='rankings-table']/tbody/tr/td[3]/div[contains(text(), 'Targeted')]"))
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
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User compares Count Tracked against Keyword Page vs Summary Page vs Trend Page")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
