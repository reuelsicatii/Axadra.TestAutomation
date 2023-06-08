package webApp.SEOR.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import helper.webAppContextDriver;

public class LeadFinderSearchResultPageObject {
	
	// Lead Finder - Result
	// ==========================================
	public By saveAll_button = By.xpath("//button[@id='saveAllLeads']");
	public By saveAll_dropdown = By.xpath("//div[@id='s2id_lead-group-selector']");
	public By addNewList_option = By.xpath("//div[@id='select2-drop']//div[text()='Add new list']");
	public By addNewListName_textfield = By.xpath("//input[@id='lead-group-list']");
	public By addNewList_button = By.xpath("//button[contains(@class, 'save-all-lead')]");
	public By list_link = By.xpath("//a[text()='List']");
	public By leadGeneratorResult_tableRow = By.xpath("//table[@id='lead-generator-table']//tbody/tr");
	public By searchLeadStatus_span = By.xpath("(//span[contains(@id, 'status')])[1]");
	public By searchLeadResult_link = By.xpath("( (//h5[contains(@id, 'leadgenHistoryKeywordLocation')])[1]");	
	public By leadGeneratorGroupLeads_tableRow = By.xpath("//table[@id='group-leads-table']//tbody/tr");
	public By tableRow_dropdown = By.xpath("//select[@name='group-leads-table_length']");
	public By tableRowValue100_dropdown = By.xpath("(//select[@name='group-leads-table_length']/option)[4]");
	public By tablepagination_links = By.xpath("//div[@id='group-leads-table_paginate']//span/a");

	
	public By listName_link(String ListName, String ListCount) {
		
		return By.xpath("//div[@id='list-pane']//a[text()='"+ ListName +" ("+ ListCount +")']");
		
	}

}
