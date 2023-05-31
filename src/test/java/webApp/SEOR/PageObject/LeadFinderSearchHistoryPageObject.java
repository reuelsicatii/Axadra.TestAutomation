package webApp.SEOR.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import helper.webAppContextDriver;

public class LeadFinderSearchHistoryPageObject {
	
	// Lead Finder - Search
	// ==========================================
	public By leadGeneratorHistory_tableRow = By.xpath("//table[@id='lead-generator-history-table']//tbody/tr");
	public By keyword_inputfield = By.xpath("//div[@class='tab-content']//input[@id='keyword']");
	public By keyword_suggestionList = By.xpath("//div[@class='tab-content']//div[@id='keyword-suggestions-wrapper']//div[1]");
	public By location_inputfield = By.xpath("//div[@class='tab-content']//input[@id='location']");
	public By location_suggestionList = By.xpath("//div[@class='tab-content']//div[@id='location-suggestions-wrapper']//div[1]");
	public By findLeads_button = By.xpath("//input[@id='generate-lead']");
	public By processing_button = By.xpath("//button[contains(text(), 'Processing')]");

	

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
