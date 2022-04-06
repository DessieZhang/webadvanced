Feature: Search mail test
  Description: The purpose of this feature is to test the search mail function

  Background:
    Given I navigate to the yandex mail home page
    When I click Log in button to open login page
    And I login yandex mail as existing user
      | ID               | Password             |
      | dessie.zhang16     |QmFpZHUwMDEh          |

  @positive
  Scenario: Simple search for existing emails
    Given I open the new mail page
    When I new a email with recipient,subject, body fields filled
    And I close the new email
    And I open Drafts mail folder
    And I search mail by Subject
    Then I see the mail is in search result list
    And I log off Yandex
