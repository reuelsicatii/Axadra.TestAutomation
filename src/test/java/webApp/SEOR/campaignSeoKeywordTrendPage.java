package webApp.SEOR;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.Status;

import helper.webAppContextDriver;
import helper.webAppHelper;
import io.cucumber.java.en.When;

public class campaignSeoKeywordTrendPage extends webAppHelper {

	// Page Elements
	// ==========================================
	By keywordColumn_table = By
			.xpath("//table[@id='keyword-ranking-post-table']/tbody/tr/td[3]//div[@class='keyword-term']");

	// Keyword Ranking Table Row Dropdown - Element Finder
	// ===================================
	private Select keywordRankingTableRowDropDownElementFinder() {

		Select element = new Select(
				context.getDriver().findElement(By.xpath("//select[@name='keyword-ranking-post-table_length']")));

		return element;

	}

	// Month Trend Table Row Dropdown - Element Finder
	// ===================================
	private Select monthTrendTableRowDropDownElementFinder() {

		Select element = new Select(
				context.getDriver().findElement(By.xpath("//select[@name='months-trend-table_length']")));

		return element;

	}

	// Declare Driver Instance
	// ==========================================
	private webAppContextDriver context;

	public campaignSeoKeywordTrendPage(webAppContextDriver context) {
		super();
		this.context = context;
	}

	// Page Step Definition
	// =================================================
	@SuppressWarnings("unchecked")
	@When("User compares Keyword Position against {string} vs {string}")
	public void userComparesKeywordPositionAgainstVs(String rankingPage, String trendPage) {

		try {

			LinkedHashMap<String, String> rankingPageKeywordPosition = new LinkedHashMap<String, String>();
			LinkedHashMap<String, String> trendPageKeywordPosition = new LinkedHashMap<String, String>();

			// Navigate to COMP -SEOR
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

			// Summary Page
			// =============================================================================================================
			context.getDriver().get(rankingPage);

			// wait for table to load - no anchor Thread.sleep(10000);

			// select 100 over dropdown
			keywordRankingTableRowDropDownElementFinder().selectByVisibleText("100");

			// wait for table to load - no anchor
			Thread.sleep(10000);

			// Loop through Pagination
			for (int j = 1; j < context.getDriver()
					.findElements(By.xpath("//div[@id='keyword-ranking-post-table_paginate']/span/a")).size()
					+ 1; j++) {

				// Click on next active button
				// ====================================
				if (j > 1) {

					System.out.println("Click :" + j + "--" + context.getDriver()
							.findElement(By.xpath("//div[@id='keyword-ranking-post-table_paginate']/span/a[" + j + "]"))
							.getText());

					context.getDriver()
							.findElement(By.xpath("//div[@id='keyword-ranking-post-table_paginate']/span/a[" + j + "]"))
							.click();

					// wait for table to load - no anchor
					Thread.sleep(10000);

				}

				// // Loop through Table row - Get Keyword and its position
				for (int a = 1; a < context.getDriver()
						.findElements(By.xpath("//table[@id='keyword-ranking-post-table']/tbody/tr")).size() + 1; a++) {

					context.getFluentWait().until(ExpectedConditions.visibilityOf(context.getDriver()
							.findElement(By.xpath("//table[@id='keyword-ranking-post-table']/tbody/tr[last()]"))));

					rankingPageKeywordPosition.put(
							context.getDriver()
									.findElement(By.xpath("//table[@id='keyword-ranking-post-table']/tbody/tr[" + a
											+ "]/td[3]//div[@class='keyword-term']"))
									.getText(),
							context.getDriver()
									.findElement(By.xpath("//table[@id='keyword-ranking-post-table']/tbody/tr[" + a
											+ "]/td[4]//span[@class='position-column']"))
									.getText());

				}

				// Iterating HashMap through for loop
				for (Map.Entry<String, String> set : rankingPageKeywordPosition.entrySet()) {

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
			monthTrendTableRowDropDownElementFinder().selectByVisibleText("100");

			// wait for table to load - no anchor
			Thread.sleep(10000);

			// Loop through Pagination
			while (true) {

				// Get Keyword and its position
				for (int b = 1; b < context.getDriver()
						.findElements(By.xpath("//table[@id='months-trend-table']/tbody/tr")).size() + 1; b++) {

					context.getFluentWait().until(ExpectedConditions.visibilityOf(context.getDriver()
							.findElement(By.xpath("//table[@id='months-trend-table']/tbody/tr[last()]"))));

					String[] keyword = context.getDriver()
							.findElement(
									By.xpath("//table[@id='months-trend-table']/tbody/tr[" + b + "]/td[1]/span[1]"))
							.getText().split("https");

					trendPageKeywordPosition.put(keyword[0].trim(),
							context.getDriver()
									.findElement(
											By.xpath("//table[@id='months-trend-table']/tbody/tr[" + b + "]/td[10]"))
									.getText());

				}

				// Iterating HashMap through for loop
				for (Map.Entry<String, String> set : trendPageKeywordPosition.entrySet()) {

					// Printing all elements of a Map
					System.out.println("trendPageKeywordPosition " + set.getKey() + " = " + set.getValue());

				}

				// Exit While Loop
				if (!context.getDriver()
						.findElement(By.xpath("//div[@id='months-trend-table-custom-pagination']//button[2]"))
						.isEnabled()) {
					break;
				}

				// Click on next active button
				// ====================================
				context.getDriver()
						.findElement(By.xpath("//div[@id='months-trend-table-custom-pagination']//button[2]")).click();
				Thread.sleep(10000);

			}

			// compare keyword and position
			context.getExtentTestScenario().createNode(
					"User compares Keyword and Position against CPS > Summary Page vs CPS > Trend Page vs SRS > Ranking Page"
							+ "<br>" + "Campaign URL: " + rankingPage.replace("/seo/rankings", "") + "<br>"
							+ "Trend Page Keyword Count: " + trendPageKeywordPosition.size() + "<br>"
							+ "Ranking Page Keyword Count: " + rankingPageKeywordPosition.size()

			);

			// Iterating HashMap through for loop
			int trd = 1;
			for (Map.Entry<String, String> trendPageKeywordPositionset : trendPageKeywordPosition.entrySet()) {

				int ran = 1;
				for (Map.Entry<String, String> rankingPageKeywordPositionset : rankingPageKeywordPosition.entrySet()) {

					if (rankingPageKeywordPositionset.getKey().equals(trendPageKeywordPositionset.getKey())
							&& rankingPageKeywordPositionset.getValue()
									.equals(trendPageKeywordPositionset.getValue())) {

						context.getExtentTestScenario()
								// .createNode("User compares Keyword and Position against Summary Page row " +
								// summ + " vs Trend Page row " + trd)
								.createNode("Index: " + trd + " -- " + ran)
								.pass("PASSED: " + "<br>" + "Trend Page: " + trendPageKeywordPositionset.getKey()
										+ " -- " + trendPageKeywordPositionset.getValue() + "<br>" + "Ranking Page: "
										+ rankingPageKeywordPositionset.getKey() + " -- "
										+ rankingPageKeywordPositionset.getValue());

					}

					else if (rankingPageKeywordPositionset.getKey().equals(trendPageKeywordPositionset.getKey())) {

						context.getExtentTestScenario()
								// .createNode("User compares Keyword and Position against Summary Page row " +
								// summ + " vs Trend Page row" + trd)
								.createNode("Index: " + trd + " -- " + ran)
								.warning("WARNING: " + "<br>" + "Trend Page: " + trendPageKeywordPositionset.getKey()
										+ " -- " + trendPageKeywordPositionset.getValue() + "<br>" + "Ranking Page: "
										+ rankingPageKeywordPositionset.getKey() + " -- "
										+ rankingPageKeywordPositionset.getValue());

						// break inner loop
						break;

					}

					ran++;
				}

				trd++;
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
