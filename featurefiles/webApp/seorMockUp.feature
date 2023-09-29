@SEOR_AgencyTools_MockUp
Feature: SEOR > Agency Tools > WebAudit

  Background: 

  #Given User navigates to "https://account.seoreseller.com/login" using "chrome"
  #And User enter the username as "reuel+01@axadra.com"
  #And User enter the password as "asdasdasd"
  #And User click on the login button
  #Then User is successfully login
  #When User navigates to "https://account.seoreseller.com/pro/audits"
  #And User generates a WebAuditReport
  @LoadAllMockUpSites
  Scenario Outline: Load All Sites Over All Category
    Given User navigates to "<loginUrl>" using "<browser>"
    And User enter the username as "<username>"
    And User enter the password as "<password>"
    And User click on the login button
    Then User is successfully login
    When User navigates to "<mokcUpUrl>"
    Then User loads "<category>" category sites

    Examples: 
      | browser | loginUrl                              | username                 | password        | mokcUpUrl                                   | category                           |
      | chrome  | https://account.seoreseller.com/login | clayton@truelogic.com.ph | aGXYwqhPeAV4j7J | https://account.seoreseller.com/pro/mockups | All Mockups                        |
      | chrome  | https://account.seoreseller.com/login | clayton@truelogic.com.ph | aGXYwqhPeAV4j7J | https://account.seoreseller.com/pro/mockups | Art, Design & Media                |
      | chrome  | https://account.seoreseller.com/login | clayton@truelogic.com.ph | aGXYwqhPeAV4j7J | https://account.seoreseller.com/pro/mockups | Automotive & Local Transportation  |
      | chrome  | https://account.seoreseller.com/login | clayton@truelogic.com.ph | aGXYwqhPeAV4j7J | https://account.seoreseller.com/pro/mockups | Business Services & Finance        |
      | chrome  | https://account.seoreseller.com/login | clayton@truelogic.com.ph | aGXYwqhPeAV4j7J | https://account.seoreseller.com/pro/mockups | Construction & Home Services       |
      | chrome  | https://account.seoreseller.com/login | clayton@truelogic.com.ph | aGXYwqhPeAV4j7J | https://account.seoreseller.com/pro/mockups | Fashion & Lifestyle                |
      | chrome  | https://account.seoreseller.com/login | clayton@truelogic.com.ph | aGXYwqhPeAV4j7J | https://account.seoreseller.com/pro/mockups | Government, Non Profit & Community |
      | chrome  | https://account.seoreseller.com/login | clayton@truelogic.com.ph | aGXYwqhPeAV4j7J | https://account.seoreseller.com/pro/mockups | Medicine, Health & Wellness        |
      | chrome  | https://account.seoreseller.com/login | clayton@truelogic.com.ph | aGXYwqhPeAV4j7J | https://account.seoreseller.com/pro/mockups | Outdoors, Plants & Pets            |
      | chrome  | https://account.seoreseller.com/login | clayton@truelogic.com.ph | aGXYwqhPeAV4j7J | https://account.seoreseller.com/pro/mockups | Real Estate                        |
      | chrome  | https://account.seoreseller.com/login | clayton@truelogic.com.ph | aGXYwqhPeAV4j7J | https://account.seoreseller.com/pro/mockups | Science, Digital & Technology      |
      | chrome  | https://account.seoreseller.com/login | clayton@truelogic.com.ph | aGXYwqhPeAV4j7J | https://account.seoreseller.com/pro/mockups | Travel & Hospitality               |
