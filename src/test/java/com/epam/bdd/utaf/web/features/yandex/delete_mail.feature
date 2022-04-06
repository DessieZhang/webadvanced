Feature: Delete mail test
  Description: The purpose of this feature is to check the delete email function
  Background:
    Given I navigate to the yandex mail home page
    When I click Log in button to open login page
    And I login yandex mail as existing user
      | ID               | Password             |
      | dessie.zhang16     |QmFpZHUwMDEh          |

  @positive
  Scenario:  Delete one email in Drafts
    Given I open the new mail page
    When I new a email with recipient,subject, body fields filled
    And I close the new email
    And I open Drafts mail folder
    And I select the email
    And I click delete button
    Then I see the Drafts number is decreased
    And I log off Yandex



