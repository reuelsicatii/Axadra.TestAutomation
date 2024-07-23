@SEM_CreateReseller
Feature: SEM_CreateReseller

  Background: 

  #Given ...
  #And ...
  #When...
  #And ...
  #Then ...
  #And ...
  
  
  @CAS-TC-171 @CreateReseller @SEM_CreateReseller
  Scenario Outline: Create a Reseller through Sales SubMenu
    Given User navigate to "<loginUrl>" using "<browser>"
    And User populate the username with "reuel@seoreseller.com"
    When User populate the password with "Sem1fy@3"
    When User click the login button
    Then User is successfully login
    #  ======= create lead ================
    When User navigate to "https://uat.semify.com/?p=Campaign_Details&Camp_ID=41"
    And User pause for 5 seconds
    And User navigate to "https://uat.semify.com/?p=Sales_Account_Details_New"
    And User pause for 5 seconds
    And User populate the accountName textfield with "dynamicAccountName"
    And User populate the firstName textfield with "dynamicFirstName"
    And User populate the lastName textfield with "dynamicLastName"
    And User select from brand dropdown the "<brand>"
    And User click the buildLead button
    Then User see lead is successfully created
    #  ======= convert lead to reseller ================
    When User click the goThereNow link
    And User navigate to accountDetailsAccountID
    And User click the hubMin tab
    And User select from reseller radiobutton the "<reseller>"
    And User select from commissionEligible radiobutton the "<commissionEligible>"
    And User click the update button
    Then User see reseller is successfully created
    When User navigate to "https://uat.semify.com/?p=admin"
    Then User see reseller is searchable over Admin > Campaign Scorecard

    Examples: 
      | browser | loginUrl               | brand            | stage      | reseller | commissionEligible |
      | chrome  | https://uat.semify.com | SEOReseller      | Closed/Won | on       | on                 |
      | chrome  | https://uat.semify.com | Semify           | Closed/Won | on       | on                 |
      | chrome  | https://uat.semify.com | Agency Elevation | Closed/Won | on       | on                 |
