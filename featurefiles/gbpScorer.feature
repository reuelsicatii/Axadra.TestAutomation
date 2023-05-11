@SEOR_AgencyTools_GbpScorer
Feature: SEOR > Agency Tools > WebAudit

  Background: 

  #Given User navigates to "https://account.seoreseller.com/login" using "chrome"
  #And User enter the username as "reuel+01@axadra.com"
  #And User enter the password as "asdasdasd"
  #And User click on the login button
  #Then User is successfully login
  #When User navigates to "https://account.seoreseller.com/pro/audits"
  #And User generates a WebAuditReport
  @WebAuditReportSummary
  Scenario Outline: Generate WebAudit Report and validate Summary section
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "reuel+01@axadra.com"
    And User enter the password as "asdasdasd"
    And User click on the login button
    Then User is successfully login
    When User navigates to "<webAuditUrl>"
    And User clicks the most recent WebAuditReport
    Then User sees a new tab is open rendering the WebAuditReport
    And User sees the Summary Section > Grades SubSection is correct
    And User sees the Summary Section > WebSite Score SubSection is correct
    And User sees the Summary Section > Verdict Table SubSection is correct

    Examples: 
      | browser | loginUrl                              | webAuditUrl                                |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/audits |

@GbpScorerReportGooglePostScore
  Scenario Outline: Generate GBPScorer Report and validate Google Post Score
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "reuel+01@axadra.com"
    And User enter the password as "asdasdasd"
    And User click on the login button
    Then User is successfully login
    When User navigates to "<webAuditUrl>"
    And User clicks the most recent GBPScorer Report
    Then User sees a new tab is open rendering the GBPScorer Report
    And User scroll to "Google Post Score" Section
    And User sees the Google Post Score > Post Found SubSection is correct
    And User sees the Google Post Score > Minimum Post SubSection is correct
    And User sees the Google Post Score > Images in Post SubSection is correct 

    Examples: 
      | browser | loginUrl                              | webAuditUrl                                    |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/gbp-report |


  @GbpScorerReportGoogleReviewScore
  Scenario Outline: Generate GBPScorer Report and validate Google Review Score
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "reuel+01@axadra.com"
    And User enter the password as "asdasdasd"
    And User click on the login button
    Then User is successfully login
    When User navigates to "<webAuditUrl>"
    And User clicks the most recent GBPScorer Report
    Then User sees a new tab is open rendering the GBPScorer Report
    And User scroll to "Google Review Score" Section
    And User sees the Google Review Score > Google Review SubSection is correct
    And User sees the Google Review Score > Ave Review SubSection is correct
    And User sees the Google Review Score > Owner Response SubSection is correct

    Examples: 
      | browser | loginUrl                              | webAuditUrl                                    |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/gbp-report |

  @GbpScorerReportSimilarListing
  Scenario Outline: Generate GBPScorer Report and validate Similar Listing
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "reuel+01@axadra.com"
    And User enter the password as "asdasdasd"
    And User click on the login button
    Then User is successfully login
    When User navigates to "<webAuditUrl>"
    And User clicks the most recent GBPScorer Report
    Then User sees a new tab is open rendering the GBPScorer Report
    And User scroll to "Similar Listings" Section
    And User sees the Similar Listing Section is correct

    Examples: 
      | browser | loginUrl                              | webAuditUrl                                    |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/gbp-report |

  @GbpScorerReportRecentReview
  Scenario Outline: Generate GBPScorer Report and validate Recent Review
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "reuel+01@axadra.com"
    And User enter the password as "asdasdasd"
    And User click on the login button
    Then User is successfully login
    When User navigates to "<webAuditUrl>"
    And User clicks the most recent GBPScorer Report
    Then User sees a new tab is open rendering the GBPScorer Report
    And User scroll to Recent Review Section
    And User sees the Recent Review Section is correct

    Examples: 
      | browser | loginUrl                              | webAuditUrl                                    |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/gbp-report |

  @GenerateGbpScorerReport
  Scenario Outline: Generate GBP Scorer Report
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "<username>"
    And User enter the password as "<password>"
    And User click on the login button
    Then User is successfully login
    When User navigates to "<gbpScorerUrl>"
    And User generates a GbpScorerReport

    Examples: 
      | browser | loginUrl                              | gbpScorerUrl                                   | username                 | password        |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/gbp-report | reuel+01@axadra.com      | asdasdasd       |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/gbp-report | clayton@truelogic.com.ph | aGXYwqhPeAV4j7J |
