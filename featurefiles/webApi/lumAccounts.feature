@LumenAccounts
Feature: Lumen > API > Accounts

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
  @postCredentialStatus200
  Scenario Outline: postCredentialStatus - 200 - /account-email-server/credentials-status
    Given I set a request
    And I set the baseURL to "<BaseURL>"
    And I set the basePath to "<BasePath>"
    And I add the JSON "<requestBody>" to the request
    And I build a "<method>" request
    Then I validate response status against "<expectedStatus>"
    And I validate response body against raw "<expectedBody>"
    And I validate response body against schema "<expectedSchemaBody>"

    Examples: 
      | BaseURL                     | BasePath                                     | method | expectedStatus | requestBody                                                             | expectedBody                                                             | expectedSchemaBody                                                             |
      | http://dev.api.lumen.io.aws | /api/account-email-server/credentials-status | POST   |            200 | \\data\\webApi.LUM.Accounts\\postCredentialStatus_Req200_TS01_TC01.json | \\data\\webApi.LUM.Accounts\\postCredentialStatus_Resp200_TS01_TC01.json | \\data\\webApi.LUM.Accounts\\postCredentialStatus_RespSchema200_TS01_TC01.json |

  @postCredentialStatus422
  Scenario Outline: postCredentialStatus - 422 - /account-email-server/credentials-status
    Given I set a request
    And I set the baseURL to "<BaseURL>"
    And I set the basePath to "<BasePath>"
    And I add the JSON "<requestBody>" to the request
    And I build a "<method>" request
    Then I validate response status against "<expectedStatus>"
    And I validate response body against raw "<expectedBody>"
    And I validate response body against schema "<expectedSchemaBody>"

    Examples: 
      | BaseURL                     | BasePath                                     | method | expectedStatus | requestBody                                                             | expectedBody                                                             | expectedSchemaBody                                                             |
      | http://dev.api.lumen.io.aws | /api/account-email-server/credentials-status | POST   |            422 | \\data\\webApi.LUM.Accounts\\postCredentialStatus_Req422_TS02_TC01.json | \\data\\webApi.LUM.Accounts\\postCredentialStatus_Resp422_TS02_TC01.json | \\data\\webApi.LUM.Accounts\\postCredentialStatus_RespSchema422_TS02_TC01.json |
