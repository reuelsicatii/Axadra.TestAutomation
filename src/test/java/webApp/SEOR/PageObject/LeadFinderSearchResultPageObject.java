package webApp.SEOR.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import helper.webAppContextDriver;

public class LeadFinderSearchResultPageObject {
	
	// Page Elements - Summary Chart Section
	// ==========================================
	public By summarySection_BusinessName = By.xpath("//div[@class='summary-section row']//td[text()='Business Name']/following-sibling::td");
	public By summarySection_BusinessAddress = By.xpath("//div[@class='summary-section row']//td[text()='Business Address']/following-sibling::td");
	public By summarySection_BusinessCategory = By.xpath("//div[@class='summary-section row']//td[text()='Business Category']/following-sibling::td");
	public By summarySection_Score = By.xpath("//div[@class='summary-section row']//h2");
	public By summarySection_Map = By.xpath("//div[@class='summary-section row']//iframe");

	// Page Elements - Summary Chart Section
	// ==========================================
	public By summaryChartSection = By.xpath("//div[contains(@class, 'summary-section-chart')]");
	public By summaryChartSection_profileScore = By.xpath("//h5[text()='Profile Score']/following-sibling::div[1]//h2");
	public By summaryChartSection_postScore = By.xpath("//h5[text()='Post Score']/following-sibling::div[1]//h2");
	public By summaryChartSection_reviewScore = By.xpath("//h5[text()='Review Score']/following-sibling::div[1]//h2");	

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
	public By subSectionElementFinder(String SectionName, String SubSectionName, String SubSectionNameType) {

		By elementBy = null;
		if (SubSectionNameType.matches("verdict")) {

			elementBy = By
					.xpath("//div//h5[contains(text(),'" + SectionName + "')]/ancestor::div[3]//div[contains(text(), '"
							+ SubSectionName + "')]/following-sibling::div[1]/div");

		} else if (SubSectionNameType.matches("verbiage")) {
			elementBy = By
					.xpath("//div//h5[contains(text(),'" + SectionName + "')]/ancestor::div[3]//div[contains(text(), '"
							+ SubSectionName + "')]/following-sibling::div[2]");

		}
		else if (SubSectionNameType.matches("verdicts")) {
			elementBy = By
					.xpath("//div//h5[contains(text(),'"+ SectionName + "')]/ancestor::div[3]/div[2]/div[2]/div/div[2]");

		}
		else if (SubSectionNameType.matches("rating")) {
			elementBy = By
					.xpath("//div//h5[contains(text(),'" + SectionName + "')]/ancestor::div[3]/div[2]//h2");

		}

		return elementBy;

	}

	public By subSectionElementParentH3Finder(String SubSectionName) {

		return By.xpath("//p[contains(text(), '" + SubSectionName + "')]//parent::div/h3");

	}

}
