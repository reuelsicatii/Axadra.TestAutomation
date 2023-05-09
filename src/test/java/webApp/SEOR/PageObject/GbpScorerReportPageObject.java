package webApp.SEOR.PageObject;

import org.openqa.selenium.By;

public class GbpScorerReportPageObject {

	// Page Elements - Recent Review Section
	// ==========================================
	public By recentReviewSection_RecentReviewCount = By
			.xpath("//td[text()='Total # of Reviews']/following-sibling::td");
	public By recentReviewSection_RecentReviewActual = By
			.xpath("//div//h5[contains(text(),'Recent Reviews')]/ancestor::div[3]//div[@class='media-body']");
	public By recentReviewSection_RecentReviewAuthorCount = By
			.xpath("//div//h5[contains(text(),'Recent Reviews')]/ancestor::div[3]//div[@class='media-body']/h5");
	public By recentReviewSection_RecentReviewRatingCount = By.xpath(
			"//div//h5[contains(text(),'Recent Reviews')]/ancestor::div[3]//div[@class='media-body']/div/span[1]");
	public By recentReviewSection_RecentReviewStarCount = By.xpath(
			"//div//h5[contains(text(),'Recent Reviews')]/ancestor::div[3]//div[@class='media-body']/div/span[2]");
	public By recentReviewSection_RecentReviewCommentCount = By
			.xpath("//div//h5[contains(text(),'Recent Reviews')]/ancestor::div[3]//div[@class='media-body']/p");
	public By recentReviewSection_RecentReviewDateCount = By
			.xpath("//div//h5[contains(text(),'Recent Reviews')]/ancestor::div[3]//div[@class='media-body']/div[2]");

	// Page Elements - Recent Review Section
	// ==========================================
	public By similarListingSection_NameCount = By
			.xpath("//div//h5[contains(text(),'Similar Listings')]/ancestor::div[3]//h5[@class='text-truncate']");
	public By similarListingSection_RatingCount = By.xpath(
			"//div//h5[contains(text(),'Similar Listings')]/ancestor::div[3]//h5[@class='text-truncate']/following-sibling::div/span[1]");
	public By similarListingSection_StarCount = By.xpath(
			"//div//h5[contains(text(),'Similar Listings')]/ancestor::div[3]//h5[@class='text-truncate']/following-sibling::div/span[2]");
	public By similarListingSection_VoteCount = By.xpath(
			"//div//h5[contains(text(),'Similar Listings')]/ancestor::div[3]//h5[@class='text-truncate']/following-sibling::div/ul");

	// Section - Element Finder
	// ===================================
	public By sectionElementFinder(String SectionName) {

		return By.xpath("//div//h5[contains(text(),'" + SectionName + "')]/ancestor::div[3]");

	}

	// Sub Section - Element Finder
	// ===================================

	public By subSectionElementFinder(String SubSectionName, String SubSectionNameType) {

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

	public By subSectionElementParentH3Finder(String SubSectionName) {

		return By.xpath("//p[contains(text(), '" + SubSectionName + "')]//parent::div/h3");

	}

	public By subSectionSocialElementFinder(String SubSectionName, String SubSectionNameType) {

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

	public By subSectionSocialElementParentH3Finder(String SubSectionName, String colNumber) {

		return By.xpath("(//div[@id='" + SubSectionName + "']//div[@class='row']/div)[" + colNumber + "]");

	}

}
