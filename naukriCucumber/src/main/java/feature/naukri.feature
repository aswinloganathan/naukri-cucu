Feature: Upload CV in naukri

  Scenario: Upload a CV and get Error msg
    Given Launch the Browser
    And Set the Timeouts
    And Load the URL Naukri.com
    And Get the company names from new windows and close it
    When click on the upload CV button and upload some random image
    Then Get the error message printed