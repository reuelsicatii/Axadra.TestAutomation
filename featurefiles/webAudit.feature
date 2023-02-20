Feature: SEOR > Agency Tools > WebAudit

  Background: #<Describe Here a PreCondition for ff scenarios>

  @WebAuditReportSummary
  Scenario Outline: WebAuditReportSummary
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "reuel@axadra.com"
    And User enter the password as "asdasdasd"
    And User click on the login button
    Then User is successfully login
    When User navigates to "<webAuditUrl>"
    And User clicks the most recent WebAuditReport
    Then User sees a new tab is open redering the WebAuditReport
    And User sees the summary of the section grades
    And User sees the website score
    And User sees the count verdict of the section
    

    Examples: 
      | browser | loginUrl                              | webAuditUrl                                |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/audits |
