@SEOR_AgencyTools_WebAudit
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

  @WebAuditReportUsability
  Scenario Outline: Generate WebAudit Report and validate Usability section
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "reuel+01@axadra.com"
    And User enter the password as "asdasdasd"
    And User click on the login button
    Then User is successfully login
    When User navigates to "<webAuditUrl>"
    And User clicks the most recent WebAuditReport
    Then User sees a new tab is open rendering the WebAuditReport
    And User sees the Usability Section > Mobile Friendliness SubSection is correct
    And User sees the Usability Section > Google Analytics Found SubSection is correct
    And User sees the Usability Section > Page Speed Insight SubSection is correct

    Examples: 
      | browser | loginUrl                              | webAuditUrl                                |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/audits |

  @WebAuditReportSecurity
  Scenario Outline: Generate WebAudit Report and validate Security section
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "reuel+01@axadra.com"
    And User enter the password as "asdasdasd"
    And User click on the login button
    Then User is successfully login
    When User navigates to "<webAuditUrl>"
    And User clicks the most recent WebAuditReport
    Then User sees a new tab is open rendering the WebAuditReport
    And User scroll to Security Section
    And User sees the Security Section > SSL SubSection is correct
    And User sees the Security Section > Malware SubSection is correct
    And User sees the Security Section > HTTPS SubSection is correct
    And User sees the Security Section > Blacklisted SubSection is correct

    Examples: 
      | browser | loginUrl                              | webAuditUrl                                |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/audits |

  @WebAuditReportOrganicTrafficTechnicalSEO
  Scenario Outline: Generate WebAudit Report and validate Organic Traffic > Technical SEO section
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "reuel+01@axadra.com"
    And User enter the password as "asdasdasd"
    And User click on the login button
    Then User is successfully login
    When User navigates to "<webAuditUrl>"
    And User clicks the most recent WebAuditReport
    Then User sees a new tab is open rendering the WebAuditReport
    And User scroll to Organic Traffic Section
    And User sees the Organic Traffic > Technical SEO > Sitemap SubSection is correct
    And User sees the Organic Traffic > Technical SEO > Robot SubSection is correct
    And User sees the Organic Traffic > Technical SEO > Redirection SubSection is correct

    Examples: 
      | browser | loginUrl                              | webAuditUrl                                |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/audits |

  @WebAuditReportOrganicTrafficOnPageSEO
  Scenario Outline: Generate WebAudit Report and validate Organic Traffic > OnPage SEO section
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "reuel+01@axadra.com"
    And User enter the password as "asdasdasd"
    And User click on the login button
    Then User is successfully login
    When User navigates to "<webAuditUrl>"
    And User clicks the most recent WebAuditReport
    Then User sees a new tab is open rendering the WebAuditReport
    And User scroll to Organic Traffic Section
    And User sees the Organic Traffic > OnPage SEO > Title SubSection is correct
    And User sees the Organic Traffic > OnPage SEO > Description SubSection is correct
    And User sees the Organic Traffic > OnPage SEO > Heading SubSection is correct
    And User sees the Organic Traffic > OnPage SEO > Images SubSection is correct

    Examples: 
      | browser | loginUrl                              | webAuditUrl                                |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/audits |

  @WebAuditReportOrganicTrafficOffPageSEO
  Scenario Outline: Generate WebAudit Report and validate Organic Traffic > OffPage SEO section
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "reuel+01@axadra.com"
    And User enter the password as "asdasdasd"
    And User click on the login button
    Then User is successfully login
    When User navigates to "<webAuditUrl>"
    And User clicks the most recent WebAuditReport
    Then User sees a new tab is open rendering the WebAuditReport
    And User scroll to Organic Traffic Section
    And User sees the Organic Traffic > OffPage SEO > Backlinks SubSection is correct
    And User sees the Organic Traffic > OffPage SEO > MOZ Domain Authority SubSection is correct
    And User sees the Organic Traffic > OffPage SEO > MOZ Page Authority SubSection is correct
    And User sees the Organic Traffic > OffPage SEO > Majestic Citation Flow SubSection is correct
    And User sees the Organic Traffic > OffPage SEO > Majestic Trust Flow SubSection is correct

    Examples: 
      | browser | loginUrl                              | webAuditUrl                                |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/audits |

  @WebAuditReportPaidTraffic
  Scenario Outline: Generate WebAudit Report and validate Paid Traffic
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "reuel+01@axadra.com"
    And User enter the password as "asdasdasd"
    And User click on the login button
    Then User is successfully login
    When User navigates to "<webAuditUrl>"
    And User clicks the most recent WebAuditReport
    Then User sees a new tab is open rendering the WebAuditReport
    And User scroll to Paid Traffic Section
    And User sees the Paid Traffic > Estimated Traffic SubSection is correct
    And User sees the Paid Traffic > Keyword Detected SubSection is correct
    And User sees the Paid Traffic > Average Position SubSection is correct
    And User sees the Paid Traffic > SEMvsSEO Ratio SubSection is correct

    Examples: 
      | browser | loginUrl                              | webAuditUrl                                |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/audits |

  @WebAuditReportSocialActivity
  Scenario Outline: Generate WebAudit Report and validate Social Activity
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "reuel+01@axadra.com"
    And User enter the password as "asdasdasd"
    And User click on the login button
    Then User is successfully login
    When User navigates to "<webAuditUrl>"
    And User clicks the most recent WebAuditReport
    Then User sees a new tab is open rendering the WebAuditReport
    And User scroll to Social Activity Section
    And User sees the Social Activity > Facebook SubSection is correct
    And User sees the Social Activity > Twitter SubSection is correct

    Examples: 
      | browser | loginUrl                              | webAuditUrl                                |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/audits |

  @WebAuditReportSocialActivityFacebookData
  Scenario Outline: Generate WebAudit Report and validate Social Activity > Facebook Data between "<fromDate>" and "<toDate>"
    Given User setup a "<browser>"
    And User validates the Social Activity > Facebook SubSection > Data is pulled between "<fromDate>" and "<toDate>"

    Examples: 
      | browser | fromDate            | toDate              |
      | chrome  | 2023-09-01 19:34:51 | NOW                 |
      | chrome  | 2023-06-01 00:00:00 | 2023-08-31 23:59:59 |
      | chrome  | 2023-03-01 00:00:00 | 2023-05-31 23:59:59 |
      | chrome  | 2023-01-01 00:00:00 | 2023-02-28 23:59:59 |

  @WebAuditReportSocialActivityTwitterData
  Scenario Outline: Generate WebAudit Report and validate Social Activity > Twitter Data between "<fromDate>" and "<toDate>"
    Given User setup a "<browser>"
    And User validates the Social Activity > Twitter SubSection > Data is pulled between "<fromDate>" and "<toDate>"

    Examples: 
      | browser | fromDate            | toDate              |
      | chrome  | 2023-09-01 19:34:51 | NOW                 |
      | chrome  | 2023-06-01 00:00:00 | 2023-08-31 23:59:59 |
      | chrome  | 2023-03-01 00:00:00 | 2023-05-31 23:59:59 |
      | chrome  | 2023-01-01 00:00:00 | 2023-02-28 23:59:59 |

  @GenerateWebAuditReport
  Scenario Outline: Generate WebAudit Report
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
      #| chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/audits | reuel+01@axadra.com      | asdasdasd       |
      #| chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/audits | clayton@truelogic.com.ph | aGXYwqhPeAV4j7J |
      #| chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/audits | reuel+01@axadra.com      | asdasdasd       |
      #| chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/audits | clayton@truelogic.com.ph | aGXYwqhPeAV4j7J |
      #| chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/audits | reuel+01@axadra.com      | asdasdasd       |
      #| chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/pro/audits | clayton@truelogic.com.ph | aGXYwqhPeAV4j7J |
