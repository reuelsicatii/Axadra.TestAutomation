package webApp.SEOR;

import java.util.HashMap;

import org.openqa.selenium.support.ui.ExpectedConditions;

import com.aventstack.extentreports.GherkinKeyword;

import helper.webAppContextDriver;
import helper.webAppHelper;
import io.cucumber.java.en.Then;
import webApp.SEOR.PageObject.GbpScorerReportPageObject;

public class gbpScorerReportPage extends webAppHelper {

	// Declare Driver Instance
	// ==========================================
	private webAppContextDriver context;

	public gbpScorerReportPage(webAppContextDriver context) {
		super();
		this.context = context;
	}

	// Page Object
	// ==========================================
	GbpScorerReportPageObject gbpScorerReportPageObject = new GbpScorerReportPageObject();

	// Page Step Definition
	// =================================================
	@Then("User scroll to Similar Listing Section")
	public void userScrollToSimilarListingSection() {

		try {

			// document zooming
			// context.getDriver().executeScript("document.body.style.zoom = '0.75'");

			context.getWait().until(ExpectedConditions
					.presenceOfElementLocated(gbpScorerReportPageObject.sectionElementFinder("Similar Listings")));

			// scroll to pixel
			// context.getDriver().executeScript("window.scrollBy(0,2000)");

			// scroll to element
			context.getDriver().executeScript("arguments[0].scrollIntoView(false);", context.getDriver()
					.findElement(gbpScorerReportPageObject.sectionElementFinder("Similar Listings")));

			// Extent Report
			context.getExtentTestScenario()
					.createNode(new GherkinKeyword("When"), "User scroll to Similar Listing Section").pass("PASSED");

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User scroll to Similar Listing Section")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Then("User sees the Similar Listing Section is correct")
	public void userSeesTheSimilarListingSectionIsCorrect() {

		try {

			context.getWait().until(ExpectedConditions
					.presenceOfElementLocated(gbpScorerReportPageObject.sectionElementFinder("Similar Listings")));

			HashMap<String, Integer> similarListing = new HashMap<>();

			similarListing.put("Name",
					context.getDriver().findElements(gbpScorerReportPageObject.similarListingSection_NameCount).size());
			similarListing.put("Rating", context.getDriver()
					.findElements(gbpScorerReportPageObject.similarListingSection_RatingCount).size());

			// if there is NO rating then there are NONE star and bote hence below.
			similarListing.put("Star", context.getDriver()
					.findElements(gbpScorerReportPageObject.similarListingSection_RatingCount).size());
			similarListing.put("Vote", context.getDriver()
					.findElements(gbpScorerReportPageObject.similarListingSection_RatingCount).size());

			if (similarListing.get("Name") == 3) {

				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User sees the Similar Listing Section is correct")
						.pass("PASSED " + "<br>" + "All 3 Similar Listings are Rendered");

				for (String i : similarListing.keySet()) {

					if (similarListing.get(i) < 3) {
						context.getExtentTestScenario().createNode((3 - similarListing.get(i)) + " of 4 " + i
								+ " is missing from Similar Listing. See screenshot below.").warning("");
					}
				}

			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User sees the Similar Listing Section is correct")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

	@Then("User scroll to Recent Review Section")
	public void userScrollToRecentReviewSection() {

		try {

			context.getWait().until(ExpectedConditions
					.presenceOfElementLocated(gbpScorerReportPageObject.recentReviewSection_RecentReviewCount));

			if (Integer.parseInt(context.getDriver()
					.findElement(gbpScorerReportPageObject.recentReviewSection_RecentReviewCount).getText()) == 0) {

				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User scroll to Recent Review Section")
						.pass("PASSED " + "<br>" + "0 Recent Reviews for this Business Name");

			} else {
				// document zooming
				// context.getDriver().executeScript("document.body.style.zoom = '0.75'");

				context.getWait().until(ExpectedConditions
						.presenceOfElementLocated(gbpScorerReportPageObject.sectionElementFinder("Recent Reviews")));

				// scroll to pixel
				// context.getDriver().executeScript("window.scrollBy(0,2000)");

				// scroll to element
				context.getDriver().executeScript("arguments[0].scrollIntoView(false);", context.getDriver()
						.findElement(gbpScorerReportPageObject.sectionElementFinder("Recent Reviews")));

				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User scroll to Recent Review Section").pass("PASSED");
			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User scroll to Recent Review Section")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Then("User sees the Recent Review Section is correct")
	public void userSeesTheRecentReviewSectionIsCorrect() {

		try {

			context.getWait().until(ExpectedConditions
					.presenceOfElementLocated(gbpScorerReportPageObject.recentReviewSection_RecentReviewCount));

			if (Integer.parseInt(context.getDriver()
					.findElement(gbpScorerReportPageObject.recentReviewSection_RecentReviewCount).getText()) == 0) {

				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User sees the Recent Review Section is correct")
						.pass("PASSED " + "<br>" + "0 Recent Reviews for this Business Name");

			}

			else {

				context.getWait().until(ExpectedConditions
						.presenceOfElementLocated(gbpScorerReportPageObject.sectionElementFinder("Recent Reviews")));

				HashMap<String, Integer> recentReview = new HashMap<>();
				recentReview.put("RecentReviewCount", Integer.parseInt(context.getDriver()
						.findElement(gbpScorerReportPageObject.recentReviewSection_RecentReviewCount).getText()));
				recentReview.put("RecentReviewActual", context.getDriver()
						.findElements(gbpScorerReportPageObject.recentReviewSection_RecentReviewActual).size());
				recentReview.put("RecentReviewAuthorCount", context.getDriver()
						.findElements(gbpScorerReportPageObject.recentReviewSection_RecentReviewAuthorCount).size());
				recentReview.put("RecentReviewRatingCount", context.getDriver()
						.findElements(gbpScorerReportPageObject.recentReviewSection_RecentReviewRatingCount).size());
				recentReview.put("RecentReviewStarCount", context.getDriver()
						.findElements(gbpScorerReportPageObject.recentReviewSection_RecentReviewStarCount).size());
				recentReview.put("RecentReviewCommentCount", context.getDriver()
						.findElements(gbpScorerReportPageObject.recentReviewSection_RecentReviewCommentCount).size());
				recentReview.put("RecentReviewDateCount", context.getDriver()
						.findElements(gbpScorerReportPageObject.recentReviewSection_RecentReviewDateCount).size());

				if (recentReview.get("RecentReviewCount") >= 4 && recentReview.get("RecentReviewActual") == 4) {

					// Extent Report
					context.getExtentTestScenario()
							.createNode(new GherkinKeyword("When"), "User sees the Recent Review Section is correct")
							.pass("PASSED " + "<br>" + "All 4 Recent Reviews are Rendered");

					for (String i : recentReview.keySet()) {

						if (recentReview.get(i) < 4) {
							context.getExtentTestScenario()
									.createNode((4 - recentReview.get(i)) + " of 4 "
											+ i.replace("RecentReview", "").replace("Count", "")
											+ " is missing from Recent Reviews. See screenshot below.")
									.warning("");
						}
					}

				}

				else if (recentReview.get("RecentReviewCount") >= 4 && recentReview.get("RecentReviewActual") < 4) {

					// Extent Report
					context.getExtentTestScenario()
							.createNode(new GherkinKeyword("When"), "User sees the Recent Review Section is correct")
							.pass("PASSED " + "<br>" + "Not all 4 Recent Reviews are Rendered");

				}

			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User sees the Recent Review Section is correct")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

}
