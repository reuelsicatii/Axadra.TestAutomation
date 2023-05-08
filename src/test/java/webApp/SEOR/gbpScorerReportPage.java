package webApp.SEOR;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.aventstack.extentreports.GherkinKeyword;

import helper.webAppContextDriver;
import helper.webAppHelper;
import io.cucumber.java.en.Then;

public class gbpScorerReportPage extends webAppHelper {

	// Declare Driver Instance
	// ==========================================
	private webAppContextDriver context;

	public gbpScorerReportPage(webAppContextDriver context) {
		super();
		this.context = context;
	}

	// Page Elements - Recent Review Section
	// ==========================================
	By recentReviewSection_RecentReviewCount = By.xpath("//td[text()='Total # of Reviews']/following-sibling::td");
	By recentReviewSection_RecentReviewActual = By
			.xpath("//div//h5[contains(text(),'Recent Reviews')]/ancestor::div[3]//div[@class='media-body']");
	By recentReviewSection_RecentReviewAuthorCount = By
			.xpath("//div//h5[contains(text(),'Recent Reviews')]/ancestor::div[3]//div[@class='media-body']/h5");
	By recentReviewSection_RecentReviewRatingCount = By.xpath(
			"//div//h5[contains(text(),'Recent Reviews')]/ancestor::div[3]//div[@class='media-body']/div/span[1]");
	By recentReviewSection_RecentReviewStarCount = By.xpath(
			"//div//h5[contains(text(),'Recent Reviews')]/ancestor::div[3]//div[@class='media-body']/div/span[2]");
	By recentReviewSection_RecentReviewCommentCount = By
			.xpath("//div//h5[contains(text(),'Recent Reviews')]/ancestor::div[3]//div[@class='media-body']/p");
	By recentReviewSection_RecentReviewDateCount = By
			.xpath("//div//h5[contains(text(),'Recent Reviews')]/ancestor::div[3]//div[@class='media-body']/div[2]");

	// Page Elements - Recent Review Section
	// ==========================================
	By similarListingSection_NameCount = By
			.xpath("//div//h5[contains(text(),'Similar Listings')]/ancestor::div[3]//h5[@class='text-truncate']");
	By similarListingSection_RatingCount = By.xpath(
			"//div//h5[contains(text(),'Similar Listings')]/ancestor::div[3]//h5[@class='text-truncate']/following-sibling::div/span[1]");
	By similarListingSection_StarCount = By.xpath(
			"//div//h5[contains(text(),'Similar Listings')]/ancestor::div[3]//h5[@class='text-truncate']/following-sibling::div/span[2]");
	By similarListingSection_VoteCount = By.xpath(
			"//div//h5[contains(text(),'Similar Listings')]/ancestor::div[3]//h5[@class='text-truncate']/following-sibling::div/ul");

	// Section - Element Finder
	// ===================================
	private By SectionElementFinder(String SectionName) {

		return By.xpath("//div//h5[contains(text(),'" + SectionName + "')]/ancestor::div[3]");

	}

	// Sub Section - Element Finder
	// ===================================

	private By subSectionElementFinder(String SubSectionName, String SubSectionNameType) {

		By element = null;
		if (SubSectionNameType.matches("verdict")) {

			element = By.xpath("//p[contains(text(), '" + SubSectionName + "')]//ancestor::div[2]//span");

		} else if (SubSectionNameType.matches("verbiage")) {
			element = By.xpath("//p[contains(text(), '" + SubSectionName
					+ "')]//ancestor::div[2]//div[contains(@class, 'breakdown')]/p");

		} else {
			// Do Nothing
		}

		return element;

	}

	private By subSectionElementParentH3Finder(String SubSectionName) {

		return By.xpath("//p[contains(text(), '" + SubSectionName + "')]//parent::div/h3");

	}

	private By subSectionSocialElementFinder(String SubSectionName, String SubSectionNameType) {

		By element = null;
		if (SubSectionNameType.matches("verdict")) {

			element = By.xpath("//div[@id='" + SubSectionName + "']/div[2]/div[1]");

		} else if (SubSectionNameType.matches("verbiage")) {
			element = By.xpath("//div[@id='" + SubSectionName + "']/div[2]/div[2]");

		} else if (SubSectionNameType.matches("verbiagerow1")) {
			element = By.xpath("(//div[@id='" + SubSectionName + "']/div[2]/div[2]//h3)[1]");

		} else if (SubSectionNameType.matches("verbiagerow2")) {
			element = By.xpath("(//div[@id='" + SubSectionName + "']/div[2]/div[2]//h3)[2]");

		} else if (SubSectionNameType.matches("verbiagerow3")) {
			element = By.xpath("(//div[@id='" + SubSectionName + "']/div[2]/div[2]//h3)[3]");

		} else if (SubSectionNameType.matches("verbiagerow4")) {
			element = By.xpath("(//div[@id='" + SubSectionName + "']/div[2]/div[2]//h3)[4]");

		}

		return element;

	}

	private By subSectionSocialElementParentH3Finder(String SubSectionName, String colNumber) {

		return By.xpath("(//div[@id='" + SubSectionName + "']//div[@class='row']/div)[" + colNumber + "]");

	}

	// Page Step Definition
	// =================================================
	@Then("User scroll to Similar Listing Section")
	public void userScrollToSimilarListingSection() {

		try {

			// document zooming
			// context.getDriver().executeScript("document.body.style.zoom = '0.75'");

			context.getWait()
					.until(ExpectedConditions.presenceOfElementLocated(SectionElementFinder("Similar Listings")));

			// scroll to pixel
			// context.getDriver().executeScript("window.scrollBy(0,2000)");

			// scroll to element
			context.getDriver().executeScript("arguments[0].scrollIntoView(false);",
					context.getDriver().findElement(SectionElementFinder("Similar Listings")));

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

			// Step Definition
			context.getWait()
					.until(ExpectedConditions.presenceOfElementLocated(SectionElementFinder("Similar Listings")));

			HashMap<String, Integer> similarListing = new HashMap<>();

			similarListing.put("Name", context.getDriver().findElements(similarListingSection_NameCount).size());
			similarListing.put("Rating", context.getDriver().findElements(similarListingSection_RatingCount).size());
			
			// if there is NO rating then there are NONE star and bote hence below.
			similarListing.put("Star", context.getDriver().findElements(similarListingSection_RatingCount).size());
			similarListing.put("Vote", context.getDriver().findElements(similarListingSection_RatingCount).size());

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

			// Step Definition
			context.getWait().until(ExpectedConditions.presenceOfElementLocated(recentReviewSection_RecentReviewCount));

			if (Integer
					.parseInt(context.getDriver().findElement(recentReviewSection_RecentReviewCount).getText()) == 0) {

				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User scroll to Recent Review Section")
						.pass("PASSED " + "<br>" + "0 Recent Reviews for this Business Name");

			} else {
				// document zooming
				// context.getDriver().executeScript("document.body.style.zoom = '0.75'");

				context.getWait()
						.until(ExpectedConditions.presenceOfElementLocated(SectionElementFinder("Recent Reviews")));

				// scroll to pixel
				// context.getDriver().executeScript("window.scrollBy(0,2000)");

				// scroll to element
				context.getDriver().executeScript("arguments[0].scrollIntoView(false);",
						context.getDriver().findElement(SectionElementFinder("Recent Reviews")));

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

			// Step Definition
			context.getWait().until(ExpectedConditions.presenceOfElementLocated(recentReviewSection_RecentReviewCount));

			if (Integer
					.parseInt(context.getDriver().findElement(recentReviewSection_RecentReviewCount).getText()) == 0) {

				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("When"), "User sees the Recent Review Section is correct")
						.pass("PASSED " + "<br>" + "0 Recent Reviews for this Business Name");

			}

			else {

				// Step Definition
				context.getWait()
						.until(ExpectedConditions.presenceOfElementLocated(SectionElementFinder("Recent Reviews")));

				HashMap<String, Integer> recentReview = new HashMap<>();
				recentReview.put("RecentReviewCount", Integer
						.parseInt(context.getDriver().findElement(recentReviewSection_RecentReviewCount).getText()));
				recentReview.put("RecentReviewActual",
						context.getDriver().findElements(recentReviewSection_RecentReviewActual).size());
				recentReview.put("RecentReviewAuthorCount",
						context.getDriver().findElements(recentReviewSection_RecentReviewAuthorCount).size());
				recentReview.put("RecentReviewRatingCount",
						context.getDriver().findElements(recentReviewSection_RecentReviewRatingCount).size());
				recentReview.put("RecentReviewStarCount",
						context.getDriver().findElements(recentReviewSection_RecentReviewStarCount).size());
				recentReview.put("RecentReviewCommentCount",
						context.getDriver().findElements(recentReviewSection_RecentReviewCommentCount).size());
				recentReview.put("RecentReviewDateCount",
						context.getDriver().findElements(recentReviewSection_RecentReviewDateCount).size());

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
