@LumenCampaigns
Feature: Lumen > API > Campaigns

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
  
  
  @postCampaignsToContacts200
  Scenario Outline: postcampaignsToContacts - 200 - /campaigns-to-contacts
    Given I set a request
    And I set the baseURL to "<BaseURL>"
    And I set the basePath to "<BasePath>"
    And I add formBody Key as "email" and Value as "dynamicEmail"
    And I add formBody Key as "client_id" and Value as "7140"
    And I add formBody Key as "is_test" and Value as "1"
    And I add formBody Key as "added_by" and Value as "7140"
    And I add the formBody to the request
    And I build a "<method>" request
    Then I validate response status against "<expectedStatus>"
    And I validate response body against raw "<expectedBody>"
    And I validate response body against schema "<expectedSchemaBody>"

    Examples: 
      | BaseURL                     | BasePath                   | method | expectedStatus | expectedBody                                                                 | expectedSchemaBody                                                                 |
      | http://dev.api.lumen.io.aws | /api/campaigns-to-contacts | POST   |            200 | \\data\\webApi.LUM.Campaigns\\postcampaignsToContacts_Resp200_TS01_TC01.json | \\data\\webApi.LUM.Campaigns\\postcampaignsToContacts_RespSchema200_TS01_TC01.json |

  @postCampaignsToContacts200
  Scenario Outline: postcampaignsToContacts - 200 - /campaigns-to-contacts
    Given I set a request
    And I set the baseURL to "<BaseURL>"
    And I set the basePath to "<BasePath>"
    And I add formBody Key as "email" and Value as "dynamicEmail"
    And I add formBody Key as "client_id" and Value as "7140"
    And I add formBody Key as "is_test" and Value as "1"
    And I add formBody Key as "added_by" and Value as "7140"
    And I add the formBody to the request
    And I build a "<method>" request
    Then I validate response status against "<expectedStatus>"
    And I validate response body against raw "<expectedBody>"
    And I validate response body against schema "<expectedSchemaBody>"

    Examples: 
      | BaseURL                     | BasePath                   | method | expectedStatus | expectedBody                                                                 | expectedSchemaBody                                                                 |
      | http://dev.api.lumen.io.aws | /api/campaigns-to-contacts | POST   |            200 | \\data\\webApi.LUM.Campaigns\\postcampaignsToContacts_Resp200_TS01_TC01.json | \\data\\webApi.LUM.Campaigns\\postcampaignsToContacts_RespSchema200_TS01_TC01.json |

  @getCampaigns200
  Scenario Outline: getCampaigns - 200 - /campaigns
    Given I set a request
    And I set the baseURL to "<BaseURL>"
    And I set the basePath to "<BasePath>"
    And I add parameter Key as "campaignId" and Value as "1"
    And I add parameter Key as "site_Id" and Value as "1"
    And I add parameter Key as "page" and Value as "1"
    And I add parameter Key as "pagination_clause[pagination_limit]" and Value as "10"
    And I build a "<method>" request
    Then I validate response status against "<expectedStatus>"
    And I validate response body against raw "<expectedBody>"
    And I validate response body against schema "<expectedSchemaBody>"

    Examples: 
      | BaseURL                     | BasePath       | method | expectedStatus | expectedBody                                                      | expectedSchemaBody                                                      |
      | http://dev.api.lumen.io.aws | /api/campaigns | GET    |            200 | \\data\\webApi.LUM.Campaigns\\getCampaigns_Resp200_TS01_TC01.json | \\data\\webApi.LUM.Campaigns\\getCampaigns_RespSchema200_TS01_TC01.json |

  @getCampaigns400
  Scenario Outline: getCampaigns - 400 - /campaigns
    Given I set a request
    And I set the baseURL to "<BaseURL>"
    And I set the basePath to "<BasePath>"
    And I add parameter Key as "campaignId " and Value as "1"
    And I add parameter Key as "site_Id" and Value as "1"
    And I add parameter Key as "page" and Value as "1"
    And I add parameter Key as "pagination_clause[pagination_limit]" and Value as "10"
    And I build a "<method>" request
    Then I validate response status against "<expectedStatus>"
    And I validate response body against raw "<expectedBody>"
    And I validate response body against schema "<expectedSchemaBody>"

    Examples: 
      | BaseURL                     | BasePath       | method | expectedStatus | expectedBody                                                      | expectedSchemaBody                                                      |
      | http://dev.api.lumen.io.aws | /api/campaigns | GET    |            400 | \\data\\webApi.LUM.Campaigns\\getCampaigns_Resp400_TS01_TC01.json | \\data\\webApi.LUM.Campaigns\\getCampaigns_RespSchema400_TS01_TC01.json |
