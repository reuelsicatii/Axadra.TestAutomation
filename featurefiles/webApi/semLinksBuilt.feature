@LinksBuilt
Feature: API > LinksBuilt

  Background: 

  #Given I set a request
  #And I set the baseURL to "<BaseURL>"
  #And I reset the baseURL
  #And I set the baseURL to "<BaseURL>"
  #And I set the basePath to "<BasePath>"
  #And I reset the basePath
  #And I set the basePath to "<BasePath>"
  #And I add parameter Key as "page" and Value as "<page>"
  #And I remove parameter Key as "page" and Value as "<page>"
  #And I add parameter Key as "page" and Value as "<page>"
  #And I build a "<method>" request
  #Then I validate response status against "<expectedStatus>"
  #And I validate response body against schema "<expectedSchemaBody>"
  #And I validate response body against raw "<expectedBody>"
 
  @getAccount200
  Scenario Outline: getAccount - 200 - /api/v1/semify/account/details?accountId=
    Given I set a request
    And I set the baseURL to "<BaseURL>"
    And I set the basePath to "<BasePath>"
    And I add parameter Key as "accountId" and Value as "<accountId>"
    And I build a "<method>" request
    Then I validate response status against "<expectedStatus>"
    And I validate response body against raw "<expectedBody>"
    And I validate response body against schema "<expectedSchemaBody>"

    Examples: 
      | BaseURL                      | BasePath                       | accountId | method | expectedStatus | expectedSchemaBody                                                 | expectedBody                                                 |
      | http://dev.sync.accesshub.co | /api/v1/semify/account/details |      7140 | GET    |            200 | \\data\\webApi.SEM.linksBuilt\\getAccountRespSchema_TS01_TC01.json | \\data\\webApi.SEM.linksBuilt\\getAccountResp_TS01_TC01.json |

  @getAccount422
  Scenario Outline: getAccount - 422 - /api/v1/semify/account/details?accountId=
    Given I set a request
    And I set the baseURL to "<BaseURL>"
    And I set the basePath to "<BasePath>"
    And I add parameter Key as "accountId" and Value as "<accountId>"
    And I build a "<method>" request
    Then I validate response status against "<expectedStatus>"
    And I validate response body against raw "<expectedBody>"
    And I validate response body against schema "<expectedSchemaBody>"

    Examples: 
      | BaseURL                      | BasePath                       | accountId | method | expectedStatus | expectedSchemaBody                                                 | expectedBody                                                 |
      | http://dev.sync.accesshub.co | /api/v1/semify/account/details |           | GET    |            422 | \\data\\webApi.SEM.linksBuilt\\getAccountRespSchema_TS02_TC01.json | \\data\\webApi.SEM.linksBuilt\\getAccountResp_TS02_TC01.json |
