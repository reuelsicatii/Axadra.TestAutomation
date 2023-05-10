package webApp.SEOR;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.support.ui.ExpectedConditions;

import com.aventstack.extentreports.GherkinKeyword;
import com.jayway.jsonpath.JsonPath;

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
	@Then("User scroll to {string} Section")
	public void userScrollToSection(String SectioName) {
		try {

			// document zooming
			// context.getDriver().executeScript("document.body.style.zoom = '0.75'");

			context.getWait().until(ExpectedConditions
					.presenceOfElementLocated(gbpScorerReportPageObject.sectionElementFinder(SectioName)));

			// scroll to pixel
			// context.getDriver().executeScript("window.scrollBy(0,2000)");

			// scroll to element
			context.getDriver().executeScript("arguments[0].scrollIntoView(false);",
					context.getDriver().findElement(gbpScorerReportPageObject.sectionElementFinder(SectioName)));

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
	

	@Then("User sees the Google Post Score > Post Found SubSection is correct")
	public void userSeesTheGooglePostScorePostFoundSubSectionIsCorrect() throws IOException {

		try {
			// Step Definition
			String expected_verbiage = JsonPath
					.read(getGbpScorerReportVerbiages(),
							"$.['Google Post Score']['Post Found']['"
									+ context.getDriver()
											.findElement(gbpScorerReportPageObject.subSectionElementFinder(
													"Google Post Score", "posts found", "verdict"))
											.getText()
									+ "']");

			if (context.getDriver().findElement(gbpScorerReportPageObject.subSectionElementFinder("Google Post Score", "posts found", "verbiage")).getText().contains(expected_verbiage)) {

				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Google Post Score > Post Found SubSection is correct")
						.pass("PASSED");
			}

			else {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Google Post Score > Post Found SubSection is correct")
						.fail("FAILED" 
								+ "<br>" 
								+ "Expected - verbiage: " 
								+ expected_verbiage 
								+ "<br>"
								+ "Actual - verbiage: "
								+ context.getDriver().findElement(gbpScorerReportPageObject
										.subSectionElementFinder("Google Post Score", "posts found", "verbiage"))
										.getText());
			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Google Post Score > Post Found SubSection is correct")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}
	
	@Then("User sees the Google Post Score > Minimum Post SubSection is correct")
	public void userSeesTheGooglePostScoreMinimumPostSubSectionIsCorrect() throws IOException {

		try {
			// Step Definition
			String expected_verbiage = JsonPath
					.read(getGbpScorerReportVerbiages(),
							"$.['Google Post Score']['Minimum Post']['"
									+ context.getDriver()
											.findElement(gbpScorerReportPageObject.subSectionElementFinder(
													"Google Post Score", "Minimum 4 post", "verdict"))
											.getText()
									+ "']");

			if (context.getDriver().findElement(gbpScorerReportPageObject.subSectionElementFinder("Google Post Score", "Minimum 4 post", "verbiage")).getText().contains(expected_verbiage)) {

				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Google Post Score > Minimum Post SubSection is correct")
						.pass("PASSED");
			}

			else {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Google Post Score > Minimum Post SubSection is correct")
						.fail("FAILED" 
								+ "<br>" 
								+ "Expected - verbiage: " 
								+ expected_verbiage 
								+ "<br>"
								+ "Actual - verbiage: "
								+ context.getDriver().findElement(gbpScorerReportPageObject
										.subSectionElementFinder("Google Post Score", "Minimum 4 post", "verbiage"))
										.getText());
			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Google Post Score > Minimum Post SubSection is correct")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User sees the Google Post Score > Images in Post SubSection is correct")
	public void userSeesTheGooglePostScoreImagesInPostSubSectionIsCorrect() throws IOException {

		try {
			// Step Definition
			String expected_verbiage = JsonPath
					.read(getGbpScorerReportVerbiages(),
							"$.['Google Post Score']['Images in Post']['"
									+ context.getDriver()
											.findElement(gbpScorerReportPageObject.subSectionElementFinder(
													"Google Post Score", "Images of the business", "verdict"))
											.getText()
									+ "']");

			if (context.getDriver().findElement(gbpScorerReportPageObject.subSectionElementFinder("Google Post Score", "Images of the business", "verbiage")).getText().contains(expected_verbiage)) {

				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Google Post Score > Images in Post SubSection is correct")
						.pass("PASSED");
			}

			else {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Google Post Score > Images in Post SubSection is correct")
						.fail("FAILED" 
								+ "<br>" 
								+ "Expected - verbiage: " 
								+ expected_verbiage 
								+ "<br>"
								+ "Actual - verbiage: "
								+ context.getDriver().findElement(gbpScorerReportPageObject
										.subSectionElementFinder("Google Post Score", "Images of the business", "verbiage"))
										.getText());
			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Google Post Score > Images in Post SubSection is correct")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}	

	@Then("User sees the Google Review Score > Google Review SubSection is correct")
	public void userSeesTheGoogleReviewScoreGoogleReviewSubSectionIsCorrect() throws IOException {

		try {
			// Step Definition
			String expected_verbiage = JsonPath
					.read(getGbpScorerReportVerbiages(),
							"$.['Google Review Score']['Review Score']['"
									+ context.getDriver()
											.findElement(gbpScorerReportPageObject.subSectionElementFinder(
													"Google Review Score", "Google Reviews", "verdict"))
											.getText()
									+ "']");

			if (context.getDriver().findElement(gbpScorerReportPageObject.subSectionElementFinder("Google Review Score",
					"Google Reviews", "verbiage")).getText().contains(expected_verbiage)) {

				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Google Review Score > Google Review SubSection is correct")
						.pass("PASSED");
			}

			else {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Google Review Score > Google Review SubSection is correct")
						.fail("FAILED" 
								+ "<br>" 
								+ "Expected - verbiage: " 
								+ expected_verbiage 
								+ "<br>"
								+ "Actual - verbiage: "
								+ context.getDriver().findElement(gbpScorerReportPageObject
										.subSectionElementFinder("Google Review Score", "Google Reviews", "verbiage"))
										.getText());
			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Google Review Score > Google Review SubSection is correct")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}
	
	@Then("User sees the Google Review Score > Ave Review SubSection is correct")
	public void userSeesTheGoogleReviewScoreAveReviewSubSectionIsCorrect() throws IOException {

		try {
			// Step Definition
			String expected_verbiage = JsonPath
					.read(getGbpScorerReportVerbiages(),
							"$.['Google Review Score']['Ave Review']['"
									+ context.getDriver()
											.findElement(gbpScorerReportPageObject.subSectionElementFinder(
													"Google Review Score", "Ave reviews", "verdict"))
											.getText()
									+ "']");

			if (context.getDriver().findElement(gbpScorerReportPageObject.subSectionElementFinder("Google Review Score",
					"Ave reviews", "verbiage")).getText().contains(expected_verbiage)) {

				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Google Review Score > Ave Review SubSection is correct")
						.pass("PASSED");
			}

			else {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Google Review Score > Ave Review SubSection is correct")
						.fail("FAILED" 
								+ "<br>" 
								+ "Expected - verbiage: " 
								+ expected_verbiage 
								+ "<br>"
								+ "Actual - verbiage: "
								+ context.getDriver().findElement(gbpScorerReportPageObject
										.subSectionElementFinder("Google Review Score", "Ave reviews", "verbiage"))
										.getText());
			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Google Review Score > Ave Review SubSection is correct")
						.fail("FAILED: " + e.getMessage());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Then("User sees the Google Review Score > Owner Response SubSection is correct")
	public void userSeesTheGoogleReviewScoreOwnerResponseSubSectionIsCorrect() throws IOException {

		try {
			// Step Definition
			String expected_verbiage = JsonPath
					.read(getGbpScorerReportVerbiages(),
							"$.['Google Review Score']['Owner Response']['"
									+ context.getDriver()
											.findElement(gbpScorerReportPageObject.subSectionElementFinder(
													"Google Review Score", "Owner response", "verdict"))
											.getText()
									+ "']");

			if (context.getDriver().findElement(gbpScorerReportPageObject.subSectionElementFinder("Google Review Score",
					"Owner response", "verbiage")).getText().contains(expected_verbiage)) {

				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Google Review Score > Owner Response SubSection is correct")
						.pass("PASSED");
			}

			else {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Google Review Score > Owner Response SubSection is correct")
						.fail("FAILED" 
								+ "<br>" 
								+ "Expected - verbiage: " 
								+ expected_verbiage 
								+ "<br>"
								+ "Actual - verbiage: "
								+ context.getDriver().findElement(gbpScorerReportPageObject
										.subSectionElementFinder("Google Review Score", "Owner response", "verbiage"))
										.getText());
			}

		} catch (Exception e) {

			// Extent Report
			try {
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"),
								"User sees the Google Review Score > Owner Response SubSection is correct")
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