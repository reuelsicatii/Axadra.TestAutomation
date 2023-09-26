@Reqres_Dummy_Users
Feature: COMP > Partner > Campaign > SEO

  Background: 

  #Given User navigates to "https://account.seoreseller.com/login" using "chrome"
  #And User enter the username as "reuel+01@axadra.com"
  #And User enter the password as "asdasdasd"
  #And User click on the login button
  #Then User is successfully login
  #When User navigates to "https://account.seoreseller.com/pro/audits"
  #And User generates a WebAuditReport
  @User
  Scenario Outline: /api/users - API Testing
    Given I set a request
    And I set the baseURL to "<BaseURL>"
    And I reset the baseURL
    And I set the baseURL to "<BaseURL>"
    And I set the basePath to "<BasePath>"
    And I reset the basePath
    And I set the basePath to "<BasePath>"
    And I add parameter Key as "page" and Value as "<page>"
    And I remove parameter Key as "page" and Value as "<page>"
    And I add parameter Key as "page" and Value as "<page>"
    And I build a "<method>" request
    Then I validate response status against "<expectedStatus>"
    And I validate response body against schema "<expectedSchemaBody>"
    And I validate response body against raw "<expectedBody>"

    Examples: 
      | BaseURL           | BasePath   | page | method | resquestBody | expectedStatus | expectedSchemaBody                                    | expectedBody                                    |
      | https://reqres.in | /api/users |    2 | GET    |              |            200 | \\data\\webApi.AppName\\UserRespSchema_TS01_TC01.json | \\data\\webApi.AppName\\UserResp_TS01_TC01.json |
     