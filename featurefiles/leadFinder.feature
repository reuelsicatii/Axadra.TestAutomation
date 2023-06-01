@SEOR_AgencyTools_LeadFinder
Feature: SEOR > Agency Tools > WebAudit

  Background: 

  #Given User navigates to "https://account.seoreseller.com/login" using "chrome"
  #And User enter the username as "reuel+01@axadra.com"
  #And User enter the password as "asdasdasd"
  #And User click on the login button
  #Then User is successfully login
  #When User navigates to "https://account.seoreseller.com/pro/audits"
  #And User generates a WebAuditReport
  
  @GenerateLead
  Scenario Outline: Generate Lead
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "<username>"
    And User enter the password as "<password>"
    And User click on the login button
    Then User is successfully login
    When User navigates to "<leadFinderUrl>"
    Then User generates Leads

    Examples: 
      | browser | loginUrl                              | leadFinderUrl                             | username                 | password        |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/leads | clayton@truelogic.com.ph | aGXYwqhPeAV4j7J |

  @SaveAllLeadstoNewList
  Scenario Outline: Generate Leads and Save All Leads to New List
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "<username>"
    And User enter the password as "<password>"
    And User click on the login button
    Then User is successfully login
    When User navigates to "<leadFinderUrl>"
    And User generates Leads
    Then User save all leads to new list

    Examples: 
      | browser | loginUrl                              | leadFinderUrl                             | username            | password  |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/leads | reuel+01@axadra.com | asdasdasd |

  @ListLeadCountLessThanOrEqualTo100
  Scenario Outline: Generate Leads and Save All Leads to New List
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "<username>"
    And User enter the password as "<password>"
    And User click on the login button
    Then User is successfully login
    When User navigates to "<leadFinderUrl>"
    Then User sees List Lead count with less than or equal to 100 is correct

    Examples: 
      | browser | loginUrl                              | leadFinderUrl                             | username            | password  |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/leads | reuel+01@axadra.com | asdasdasd |

  @ListLeadCountGreaterThan100
  Scenario Outline: Generate Leads and Save All Leads to New List
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "<username>"
    And User enter the password as "<password>"
    And User click on the login button
    Then User is successfully login
    When User navigates to "<leadFinderUrl>"
    Then User sees List Lead count with greater than 100 is correct

    Examples: 
      | browser | loginUrl                              | leadFinderUrl                             | username            | password  |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/leads | reuel+01@axadra.com | asdasdasd |
