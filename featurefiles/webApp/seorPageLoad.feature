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
    Then User measures lazy load to be within <LazyLoad> using "//table[@id='webaudit-table']//tbody/tr[1]"

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
    Then User measures lazy load to be within <LazyLoad> using "//table[@id='lar-table']//tbody/tr[1]"

    Examples: 
      | browser | loginUrl                              | PageLoad | LazyLoad |
      | chrome  | https://account.seoreseller.com/login |     3000 |     4000 |

  @PageAndLazyCampaignOverview
  Scenario Outline: Measure Page and Lazy Load of Campaign > Overview
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "clayton@truelogic.com.ph"
    And User enter the password as "aGXYwqhPeAV4j7J"
    And User click on the login button
    Then User is successfully login
    When User captures startTime
    And User navigates to "https://account.seoreseller.com/campaigns/140"
    Then User measures page load to be within <PageLoad>
    Then User measures lazy load to be within <LazyLoad> using "//h5[contains(text(), 'Desktop Speed')]"
    Then User measures lazy load to be within <LazyLoad> using "//h4[contains(text(), 'Robots.txt found')]"

    Examples: 
      | browser | loginUrl                              | PageLoad | LazyLoad |
      | chrome  | https://account.seoreseller.com/login |     3000 |     4000 |

  @PageAndLazyCampaignSEO
  Scenario Outline: Measure Page and Lazy Load of Campaign > SEO
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "clayton@truelogic.com.ph"
    And User enter the password as "aGXYwqhPeAV4j7J"
    And User click on the login button
    Then User is successfully login
    When User captures startTime
    And User navigates to "https://account.seoreseller.com/campaigns/140/seo"
    Then User measures page load to be within <PageLoad>
    Then User measures lazy load to be within <LazyLoad> using "//table[@id='keyword-ranking-post-table']//tbody/tr[1]"

    Examples: 
      | browser | loginUrl                              | PageLoad | LazyLoad |
      | chrome  | https://account.seoreseller.com/login |     3000 |     4000 |

  @PageAndLazyCampaignGBP
  Scenario Outline: Measure Page and Lazy Load of Campaign > GBP
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "clayton@truelogic.com.ph"
    And User enter the password as "aGXYwqhPeAV4j7J"
    And User click on the login button
    Then User is successfully login
    When User captures startTime
    And User navigates to "https://account.seoreseller.com/campaigns/140/location"
    Then User measures page load to be within <PageLoad>
    Then User measures lazy load to be within <LazyLoad> using "//span[@class='legend-title'][contains(text(), 'Direction')]"
    Then User measures lazy load to be within <LazyLoad> using "(//div[@class='rating-stars'])[1]"
    Then User measures lazy load to be within <LazyLoad> using "(//div[@class='rating-stars'])[2]"

    Examples: 
      | browser | loginUrl                              | PageLoad | LazyLoad |
      | chrome  | https://account.seoreseller.com/login |     3000 |     4000 |

  @PageAndLazyCampaignRepMan
  Scenario Outline: Measure Page and Lazy Load of Campaign > Reputation Management
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "clayton@truelogic.com.ph"
    And User enter the password as "aGXYwqhPeAV4j7J"
    And User click on the login button
    Then User is successfully login
    When User captures startTime
    And User navigates to "https://account.seoreseller.com/campaigns/140/reputation-management"
    Then User measures page load to be within <PageLoad>
    Then User measures lazy load to be within <LazyLoad> using "(//span[@class='rating-container'])[1]"
    Then User measures lazy load to be within <LazyLoad> using "//div[@id='feedback-request-container']//p[contains(text(), 'Sent Feedbacks')]"

    Examples: 
      | browser | loginUrl                              | PageLoad | LazyLoad |
      | chrome  | https://account.seoreseller.com/login |     3000 |     4000 |

  @PageAndLazyCampaignAnalytics
  Scenario Outline: Measure Page and Lazy Load of Campaign > Analytics Overview
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "clayton@truelogic.com.ph"
    And User enter the password as "aGXYwqhPeAV4j7J"
    And User click on the login button
    Then User is successfully login
    When User captures startTime
    And User navigates to "https://account.seoreseller.com/campaigns/140/analytics"
    Then User measures page load to be within <PageLoad>

    Examples: 
      | browser | loginUrl                              | PageLoad | LazyLoad |
      | chrome  | https://account.seoreseller.com/login |     3000 |     4000 |

  @PageAndLazyCampaignContacts
  Scenario Outline: Measure Page and Lazy Load of Campaign > Contacts
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "clayton@truelogic.com.ph"
    And User enter the password as "aGXYwqhPeAV4j7J"
    And User click on the login button
    Then User is successfully login
    When User captures startTime
    And User navigates to "https://account.seoreseller.com/campaigns/140/contacts"
    Then User measures page load to be within <PageLoad>
    Then User measures lazy load to be within <LazyLoad> using "//table[@id='leads-table']//tbody/tr[1]"

    Examples: 
      | browser | loginUrl                              | PageLoad | LazyLoad |
      | chrome  | https://account.seoreseller.com/login |     3000 |     4000 |

  @PageAndLazyCampaignCallTracking
  Scenario Outline: Measure Page and Lazy Load of Campaign > Call Tracking
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "clayton@truelogic.com.ph"
    And User enter the password as "aGXYwqhPeAV4j7J"
    And User click on the login button
    Then User is successfully login
    When User captures startTime
    And User navigates to "https://account.seoreseller.com/campaigns/140/call-tracking"
    Then User measures page load to be within <PageLoad>
    Then User measures lazy load to be within <LazyLoad> using "(//div[@id='call_tracking_graphs_widget']//h3)[2][contains(text(), 'Number')]"

    Examples: 
      | browser | loginUrl                              | PageLoad | LazyLoad |
      | chrome  | https://account.seoreseller.com/login |     3000 |     4000 |

  @PageAndLazyCampaignPPC
  Scenario Outline: Measure Page and Lazy Load of Campaign > PPC
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "clayton@truelogic.com.ph"
    And User enter the password as "aGXYwqhPeAV4j7J"
    And User click on the login button
    Then User is successfully login
    When User captures startTime
    And User navigates to "https://account.seoreseller.com/campaigns/140/ppc"
    Then User measures page load to be within <PageLoad>
    Then User measures lazy load to be within <LazyLoad> using "//table[@id='ppc-campaigns-table']//tbody/tr[1]"

    Examples: 
      | browser | loginUrl                              | PageLoad | LazyLoad |
      | chrome  | https://account.seoreseller.com/login |     3000 |     4000 |

  @PageAndLazyCampaignReport
  Scenario Outline: Measure Page and Lazy Load of Campaign > Report
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "clayton@truelogic.com.ph"
    And User enter the password as "aGXYwqhPeAV4j7J"
    And User click on the login button
    Then User is successfully login
    When User captures startTime
    And User navigates to "https://account.seoreseller.com/campaigns/140/reports"
    Then User measures page load to be within <PageLoad>
    Then User measures lazy load to be within <LazyLoad> using "//table[@id='report-builder-table']//tbody/tr[1]"

    Examples: 
      | browser | loginUrl                              | PageLoad | LazyLoad |
      | chrome  | https://account.seoreseller.com/login |     3000 |     4000 |

  @PageAndLazyCampaignProject
  Scenario Outline: Measure Page and Lazy Load of Campaign > Project
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "clayton@truelogic.com.ph"
    And User enter the password as "aGXYwqhPeAV4j7J"
    And User click on the login button
    Then User is successfully login
    When User captures startTime
    And User navigates to "https://account.seoreseller.com/campaigns/140/projects"
    Then User measures page load to be within <PageLoad>
    Then User measures lazy load to be within <LazyLoad> using "//table[@id='projects-table']//tbody/tr[1]"

    Examples: 
      | browser | loginUrl                              | PageLoad | LazyLoad |
      | chrome  | https://account.seoreseller.com/login |     3000 |     4000 |

  @PageAndLazyCampaignSettingsCompetitors
  Scenario Outline: Measure Page and Lazy Load of Campaign > Settings > Competitors
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "clayton@truelogic.com.ph"
    And User enter the password as "aGXYwqhPeAV4j7J"
    And User click on the login button
    Then User is successfully login
    When User captures startTime
    And User navigates to "https://account.seoreseller.com/campaigns/140/settings/competitors"
    Then User measures page load to be within <PageLoad>
    Then User measures lazy load to be within <LazyLoad> using "//table[@id='mycompetitors-table']//tbody/tr[1]"

    Examples: 
      | browser | loginUrl                              | PageLoad | LazyLoad |
      | chrome  | https://account.seoreseller.com/login |     3000 |     4000 |
