Feature: SEOR > Agency Tools > WebAudit

  Background: 
    #Given User navigates to "https://account.seoreseller.com/login" using "chrome"
    #And User enter the username as "reuel+01@axadra.com"
    #And User enter the password as "asdasdasd"
    #And User click on the login button
    #Then User is successfully login
    #When User navigates to "https://account.seoreseller.com/pro/audits"
    #And User generates a WebAuditReport

  @GenerateWebAuditReport 
  Scenario Outline: Generate WebAuditReport
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "<username>"
    And User enter the password as "<password>"
    And User click on the login button
    Then User is successfully login
    When User navigates to "<webAuditUrl>"
    And User generates a WebAuditReport

    Examples: 
      | browser | loginUrl                              | webAuditUrl                                | username                 | password        |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/audits | reuel+01@axadra.com      | asdasdasd       |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/audits | clayton@truelogic.com.ph | aGXYwqhPeAV4j7J |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/audits | reuel+01@axadra.com      | asdasdasd       |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/audits | clayton@truelogic.com.ph | aGXYwqhPeAV4j7J |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/audits | reuel+01@axadra.com      | asdasdasd       |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/audits | clayton@truelogic.com.ph | aGXYwqhPeAV4j7J |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/audits | reuel+01@axadra.com      | asdasdasd       |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/audits | clayton@truelogic.com.ph | aGXYwqhPeAV4j7J |

  @WebAuditReportSummary
  Scenario Outline: WebAuditReportSummary
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "reuel+01@axadra.com"
    And User enter the password as "asdasdasd"
    And User click on the login button
    Then User is successfully login
    When User navigates to "<webAuditUrl>"
    And User clicks the most recent WebAuditReport
    Then User sees a new tab is open redering the WebAuditReport
    And User sees the Summary Section > Grades SubSection is correct
    And User sees the Summary Section > WebSite Score SubSection is correct
    And User sees the Summary Section > Verdict Table SubSection is correct

    Examples: 
      | browser | loginUrl                              | webAuditUrl                                |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/audits |

  @WebAuditReportUsability 
  Scenario Outline: WebAuditReportSummary
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "reuel+01@axadra.com"
    And User enter the password as "asdasdasd"
    And User click on the login button
    Then User is successfully login
    When User navigates to "<webAuditUrl>"
    And User clicks the most recent WebAuditReport
    Then User sees a new tab is open redering the WebAuditReport
    And User sees the Usability Section > Mobile Friendliness SubSection is correct
    And User sees the Usability Section > Google Analytics Found SubSection is correct
    And User sees the Usability Section > Page Speed Insight SubSection is correct

    Examples: 
      | browser | loginUrl                              | webAuditUrl                                |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/audits |

  @WebAuditReportSecurity @TestCode
  Scenario Outline: WebAuditReportSummary
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "reuel+01@axadra.com"
    And User enter the password as "asdasdasd"
    And User click on the login button
    Then User is successfully login
    When User navigates to "<webAuditUrl>"
    And User clicks the most recent WebAuditReport
    Then User sees a new tab is open redering the WebAuditReport
    And User sees the Security Section > SSL SubSection is correct
    And User sees the Security Section > Malware SubSection is correct
    And User sees the Security Section > HTTPS SubSection is correct
    And User sees the Security Section > Blacklisted SubSection is correct

    Examples: 
      | browser | loginUrl                              | webAuditUrl                                |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/audits |
