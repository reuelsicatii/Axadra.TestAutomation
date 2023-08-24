@SEOR_AgencyTools_Crm
Feature: SEOR > Agency Tools > WebAudit

  Background: 

  #Given User navigates to "https://account.seoreseller.com/login" using "chrome"
  #And User enter the username as "reuel+01@axadra.com"
  #And User enter the password as "asdasdasd"
  #And User click on the login button
  #Then User is successfully login
  #When User navigates to "https://account.seoreseller.com/pro/audits"
  #And User generates a WebAuditReport
  @AddContactFromForm
  Scenario Outline: Add Contact using Form
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "<username>"
    And User enter the password as "<password>"
    And User click on the login button
    Then User is successfully login
    When User navigates to "<crmURL>"
    And User clicks on the addContact button
    And User populates websiteURL textfield with "<websiteURL>"
    And User populates companyName textfield with "<companyName>"
    And User populates emailAddress textfield with "<emailAddress>"
    And User clicks on the createContact button
    And User clicks on the okaySuccess button
    Then User saves a contact with "<websiteURL>" "<companyName>" "<emailAddress>"

    Examples: 
      | browser | loginUrl                              | crmURL                                   | username            | password  | websiteURL   | companyName  | emailAddress   |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/crm/ | reuel+01@axadra.com | asdasdasd | www.test.com | test company | test@gmail.com |
