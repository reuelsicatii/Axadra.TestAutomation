package webApp.COMP;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.GherkinKeyword;

import helper.webAppContextDriver;
import helper.webAppHelper;
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

	private Select srsSummaryTableRowDropDownElementFinder() {

		Select element = new Select(
				context.getDriver().findElement(By.xpath("//select[@name='keyword-ranking-post-table_length']")));

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

	@SuppressWarnings("unchecked")
	@When("User compares Keyword Position against {string} vs {string} vs {string}")
	public void userComparesKeywordPositionAgainstVsVs(String summaryPage, String trendPage, String rankingPage) {

		try {

			LinkedHashMap<String, String> summaryPageKeywordPosition = new LinkedHashMap<String, String>();
			LinkedHashMap<String, String> trendPageKeywordPosition = new LinkedHashMap<String, String>();
			LinkedHashMap<String, String> rankingPageKeywordPosition = new LinkedHashMap<String, String>();

			// Summary Page
			// =============================================================================================================
			context.getDriver().get(summaryPage);

			// wait for table to load - no anchor Thread.sleep(10000);

			// select 100 over dropdown
			summaryTableRowDropDownElementFinder().selectByVisibleText("100");

			// wait for table to load - no anchor Thread.sleep(10000);

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

					// wait for table to load - no anchor Thread.sleep(10000);

				}

				// Get Keyword and its position
				for (int a = 1; a < context.getDriver()
						.findElements(By.xpath("//table[@id='rankings-summary-table']/tbody/tr")).size(); a++) {

					context.getFluentWait().until(ExpectedConditions.visibilityOf(context.getDriver()
							.findElement(By.xpath("//table[@id='rankings-summary-table']/tbody/tr[last()]"))));

					String keyword = context.getDriver()
							.findElement(By.xpath("//table[@id='rankings-summary-table']/tbody/tr[" + a
									+ "]/td[2]/div[@class='kw-name']"))
							.getText().replace("Tracked", "").replace("Targeted", "");

					String position = context.getDriver()
							.findElement(
									By.xpath("//table[@id='rankings-summary-table']/tbody/tr[" + a + "]/td[4]/div"))
							.getText();

					summaryPageKeywordPosition.put(keyword.trim(), position);

				}

				// Iterating HashMap through for loop
				for (Map.Entry<String, String> set : summaryPageKeywordPosition.entrySet()) {

					// Printing all elements of a Map
					System.out.println("summaryPageKeywordPosition " + set.getKey() + " = " + set.getValue());
				}

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

				// Get Keyword and its position

				for (int a = 1; a < context.getDriver().findElements(By.xpath("//table[@id='rankings-table']/tbody/tr"))
						.size(); a++) {

					context.getFluentWait().until(ExpectedConditions.visibilityOf(context.getDriver()
							.findElement(By.xpath("//table[@id='rankings-table']/tbody/tr[last()]"))));

					trendPageKeywordPosition.put(
							context.getDriver()
									.findElement(
											By.xpath("//table[@id='rankings-table']/tbody/tr[" + a + "]/td[3]/span"))
									.getText().trim(),
							context.getDriver()
									.findElement(By
											.xpath("//table[@id='rankings-table']/tbody/tr[" + a + "]/td[12]//strong"))
									.getText());

				}

				// Iterating HashMap through for loop
				for (Map.Entry<String, String> set : trendPageKeywordPosition.entrySet()) {

					// Printing all elements of a Map
					System.out.println("trendPageKeywordPosition " + set.getKey() + " = " + set.getValue());

				}

			}

			// Ranking Page
			// =============================================================================================================
			context.getDriver().findElement(By.xpath("//button[contains(text(), ' Log In as Client')]")).click();

			context.getFluentWait().until(ExpectedConditions
					.visibilityOf(context.getDriver().findElement(By.xpath("//input[@id='account_password']"))));
			context.getDriver().findElement(By.xpath("//input[@id='account_password']")).sendKeys("asdasdasd");

			context.getFluentWait().until(ExpectedConditions.elementToBeClickable(context.getDriver().findElement(By
					.xpath("//form[@id='login_as_client_authenticator_modal']//button[contains(text(), 'Submit')]"))));
			context.getDriver()
					.findElement(By.xpath(
							"//form[@id='login_as_client_authenticator_modal']//button[contains(text(), 'Submit')]"))
					.click();

			// wait for table to load - no anchor
			Thread.sleep(5000);

			// Swich to new tab
			ArrayList<String> newTb = new ArrayList<String>(context.getDriver().getWindowHandles());
			context.getDriver().switchTo().window(newTb.get(1));

			// wait for table to load - no anchor
			Thread.sleep(5000);

			context.getDriver().get(rankingPage);

			// wait for table to load - no anchor
			Thread.sleep(10000);

			// select 100 over dropdown
			srsSummaryTableRowDropDownElementFinder().selectByVisibleText("100");

			// wait for table to load - no anchor
			Thread.sleep(20000);

			for (int l = 1; l < context.getDriver()
					.findElements(By.xpath("//div[@id='keyword-ranking-post-table_paginate']//span/a")).size()
					+ 1; l++) {

				// Click on next active button
				// ====================================
				if (l > 1) {

					context.getDriver().executeScript("arguments[0].scrollIntoView(false);", context.getDriver()
							.findElement(By.xpath("//div[@id='keyword-ranking-post-table_paginate']//span/a")));

					System.out.println("Click :" + l + "--" + context.getDriver()
							.findElement(
									By.xpath("//div[@id='keyword-ranking-post-table_paginate']//span/a[" + l + "]"))
							.getText());

					context.getDriver()
							.findElement(
									By.xpath("//div[@id='keyword-ranking-post-table_paginate']//span/a[" + l + "]"))
							.click();

					// wait for table to load - no anchor
					Thread.sleep(20000);

				}

				// Get Keyword and its position
				for (int b = 1; b < context.getDriver()
						.findElements(By.xpath("//table[@id='keyword-ranking-post-table']/tbody/tr")).size(); b++) {

					context.getFluentWait().until(ExpectedConditions.visibilityOf(context.getDriver()
							.findElement(By.xpath("//table[@id='keyword-ranking-post-table']/tbody/tr[last()]"))));

					rankingPageKeywordPosition.put(
							context.getDriver()
									.findElement(By.xpath("//table[@id='keyword-ranking-post-table']/tbody/tr[" + b
											+ "]/td[3]//span"))
									.getText().trim(),
							context.getDriver().findElement(By
									.xpath("//table[@id='keyword-ranking-post-table']/tbody/tr[" + b + "]/td[4]//span"))
									.getText());

				}

				// Iterating HashMap through for loop
				for (Map.Entry<String, String> set : rankingPageKeywordPosition.entrySet()) {

					// Printing all elements of a Map
					System.out.println("rankingPageKeywordPosition " + set.getKey() + " = " + set.getValue());

				}

			}

			// compare keyword and possition
			context.getExtentTestScenario()
					.createNode("User compares Keyword and Position against CPS > Summary Page vs CPS > Trend Page vs SRS > Ranking Page" 
							+ "<br>" + "Campaign URL: " + summaryPage.replace("/ranking_summary", "")
							+ "<br>" + "Summary Page Keyword Count: " + summaryPageKeywordPosition.size() 
							+ "<br>" + "Trend Page Keyword Count: " + trendPageKeywordPosition.size() 
							+ "<br>" + "Ranking Page Keyword Count: " + rankingPageKeywordPosition.size()

					);
			int summ = 1;
			for (Map.Entry<String, String> summaryPageKeywordPositionset : summaryPageKeywordPosition.entrySet()) {

				// Iterating HashMap through for loop
				int trd = 1;
				for (Map.Entry<String, String> trendPageKeywordPositionset : trendPageKeywordPosition.entrySet()) {

					boolean exitLoop = false;

					int ran = 1;
					for (Map.Entry<String, String> rankingPageKeywordPositionset : rankingPageKeywordPosition
							.entrySet()) {

						if (summaryPageKeywordPositionset.getKey().equals(trendPageKeywordPositionset.getKey())
								&& summaryPageKeywordPositionset.getKey().equals(rankingPageKeywordPositionset.getKey())
								&& summaryPageKeywordPositionset.getValue()
										.equals(trendPageKeywordPositionset.getValue())
								&& summaryPageKeywordPositionset.getValue()
										.equals(rankingPageKeywordPositionset.getValue())) {

							context.getExtentTestScenario()
									// .createNode("User compares Keyword and Position against Summary Page row " +
									// summ + " vs Trend Page row " + trd)
									.createNode("Index: " + summ + " -- " + trd + " -- " + ran)
									.pass("PASSED: " + "<br>" + "Summary Page: "
											+ summaryPageKeywordPositionset.getKey() + " -- "
											+ summaryPageKeywordPositionset.getValue() + "<br>" + "Trend Page: "
											+ trendPageKeywordPositionset.getKey() + " -- "
											+ trendPageKeywordPositionset.getValue() + "<br>" + "Ranking Page: "
											+ rankingPageKeywordPositionset.getKey() + " -- "
											+ rankingPageKeywordPositionset.getValue());

						}

						else if (summaryPageKeywordPositionset.getKey().equals(trendPageKeywordPositionset.getKey())
								&& summaryPageKeywordPositionset.getKey().equals(rankingPageKeywordPositionset.getKey())) {

							context.getExtentTestScenario()
									// .createNode("User compares Keyword and Position against Summary Page row " +
									// summ + " vs Trend Page row" + trd)
									.createNode("Index: " + summ + " -- " + trd + " -- " + ran)
									.warning("WARNING: " + "<br>" + "Summary Page: "
											+ summaryPageKeywordPositionset.getKey() + " -- "
											+ summaryPageKeywordPositionset.getValue() + "<br>" + "Trend Page: "
											+ trendPageKeywordPositionset.getKey() + " -- "
											+ trendPageKeywordPositionset.getValue() + "<br>" + "Summary Page: "
											+ rankingPageKeywordPositionset.getKey() + " -- "
											+ rankingPageKeywordPositionset.getValue());

							exitLoop = true;

							// break 3rd loop
							break;

						}

						ran++;

					}

					// break 2nd loop
					if (exitLoop) {
						break;
					}

					trd++;
				}

				summ++;
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
