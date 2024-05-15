@SEOR_Page_And_Lazy_Load
Feature: SEOR > Agency Tools > WebAudit

  Background: 

  #Given User navigates to "https://account.seoreseller.com/login" using "chrome"
  #And User enter the username as "reuel+01@axadra.com"
  #And User enter the password as "asdasdasd"
  #And User click on the login button
  #Then User is successfully login
  #When User navigates to "https://account.seoreseller.com/pro/audits"
  #And User generates a WebAuditReport
  
  @PageAndLazyWebAuditReport
  Scenario Outline: Measure Page and Lazy Load of WebAudit Report
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "reuel+01@axadra.com"
    And User enter the password as "asdasdasd"
    And User click on the login button
    Then User is successfully login
    When User captures startTime
    And User navigates to "https://account.seoreseller.com/pro/audits"
    Then User measures page load to be within <PageLoad>

    Examples: 
      | browser | loginUrl                              | PageLoad | LazyLoad |
      | chrome  | https://account.seoreseller.com/login |     3000 |     4000 |

  @PageAndLazyGBPReport
  Scenario Outline: Measure Page and Lazy Load of GBP Report
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "reuel+01@axadra.com"
    And User enter the password as "asdasdasd"
    And User click on the login button
    Then User is successfully login
    When User captures startTime
    And User navigates to "https://account.seoreseller.com/pro/gbp-report"
    Then User measures page load to be within <PageLoad>

    Examples: 
      | browser | loginUrl                              | PageLoad | LazyLoad |
      | chrome  | https://account.seoreseller.com/login |     3000 |     4000 |
