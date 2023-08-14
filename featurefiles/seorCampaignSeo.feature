@SEOR_Campaign_Seo_Keywords
Feature: SEOR > Partner > Campaign > SEO

  Background: 

  #Given User navigates to "https://account.seoreseller.com/login" using "chrome"
  #And User enter the username as "reuel+01@axadra.com"
  #And User enter the password as "asdasdasd"
  #And User click on the login button
  #Then User is successfully login
  #When User navigates to "https://account.seoreseller.com/pro/audits"
  #And User generates a WebAuditReport
  @Add_And_Remove_Single_Keyword_From_Delete_Button
  Scenario Outline: Add and Remove Single Keyword from Delete button
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "<username>"
    And User enter the password as "<password>"
    And User click on the login button
    Then User is successfully login
    When User navigates to "<campaignKeywordUrl>"
    And User remove a single "<keyword>" from Delete button
    And User add a single "<keyword>"
    And User remove a single "<keyword>" from Delete button

    Examples: 
      | browser | loginUrl                              | campaignKeywordUrl                                           | username            | password  | keyword                            |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/campaigns/28082/seo/rankings | reuel+01@axadra.com | asdasdasd | srsaa real estate agent green lake |

  @Add_And_Remove_Multiple_Keyword_From_Delete_Button
  Scenario Outline: Add and Remove Multiple Keyword from Trash button
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "<username>"
    And User enter the password as "<password>"
    And User click on the login button
    Then User is successfully login
    When User navigates to "<campaignKeywordUrl>"
    And User remove a multiple "<keyword>" from Delete button
    And User add a multiple "<keyword>"
    And User remove a multiple "<keyword>" from Delete button

    Examples: 
      | browser | loginUrl                              | campaignKeywordUrl                                           | username            | password  | keyword                                                     |
      | chrome  | https://account.seoreseller.com/login | https://account.seoreseller.com/campaigns/28082/seo/rankings | reuel+01@axadra.com | asdasdasd | ba real estate,bb real estate,bc real estate,bd real estate |

  @KeywordPosition_Against_Ranking_vs_Trend
  Scenario Outline: Keyword Position against Summary vs Trend vs Ranking
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the Compass > username as "reuel@axadra.com"
    And User enter the Compass > password as "asdasdasd"
    And User click on the Compass > login button
    Then User is successfully login on Compass
    And User navigates to "<campaignUrl>"
    And User compares Keyword Position against "<campaignRankingUrl>" vs "<campaignTrendUrl>"

    Examples: 
      | browser | loginUrl                           | campaignUrl                                                      | campaignRankingUrl                                           | campaignTrendUrl                                          |
      | chrome  | https://compass.trendup.asia/login | https://compass.trendup.asia/clients/bucket/1/5703/project/34246 | https://account.seoreseller.com/campaigns/34246/seo/rankings | https://account.seoreseller.com/campaigns/34246/seo/trend |
  
  
  #@Add_Mulitple_Duplicate_Keyword
  #Scenario Outline: Add Multiple Duplicate Keyword
  #Given User navigates to "<loginUrl>" using "<browser>"
  #And User enter the username as "<username>"
  #And User enter the password as "<password>"
  #And User click on the login button
  #Then User is successfully login
  #When User navigates to "<campaignKeywordUrl>"
  #And User add a multiple "<keyword>"
  #And User see Duplicate Keyword modal
  #
  #Examples:
  #| browser | loginUrl                           | campaignKeywordUrl                                                         | username         | password  | keyword                                        |
  #| chrome  | https://compass.trendup.asia/login | https://compass.trendup.asia/clients/bucket/1/35505/project/37031/keywords | reuel@axadra.com | asdasdasd | duplicate real estate, duplicate real estate 1 |
