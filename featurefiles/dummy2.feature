Feature: Dummy

  Background: #Precondition

  @TestCode
  Scenario Outline: WebAuditReportSummary
    Given Test HardAssert01 "<bool01>" "<bool02>" "<bool03>"
    When Test HardAssert02 "<bool01>" "<bool02>" "<bool03>"

    Examples: 
      | bool01 | bool02 | bool03 |
      | true   | true   | true   |
      | true   | false  | true   |
      | true   | true   | false  |

  @TestCode
  Scenario Outline: WebAuditReportSummary
    Given Test SoftAsset01 "<bool01>" "<bool02>" "<bool03>"
    When Test SoftAsset02 "<bool01>" "<bool02>" "<bool03>"
    When Test SoftAsset03

    Examples: 
      | bool01 | bool02 | bool03 |
      | false  | true   | true   |
      | true   | false  | true   |
      | true   | true   | false  |
