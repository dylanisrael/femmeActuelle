@MinorAbnormalities
@severity=minor
Feature: Annomalies mineures

  @bug_1388
  Scenario: Connexion error
    Given Go to femmeactuelle.fr
    When Scroll down to footer level
    And Click on Attendance certified by the OJD
    Then The display of the page content should correspond to the Attendance certified by the OjD link.

  @bug_1435
  Scenario Outline: No smart number search
    Given Go to femmeactuelle.fr
    When Click on subscribe to the magazine on the navigation bar
    And CLick on see all numbers
    And Do an research <with s>
    And research <without s>
    Then The number of economy research articles with s had to be equal to the one without s.
    Examples:
    |with s        |without s|
    |   economies  |   economie  |
#
  @bug_1438
  Scenario Outline: Button size varying
    Given Log in with your <login> and <password>
    When Click on edit your email
    And Fill in the field reserved for email on the window that opens
    Then The size of the validate button should remain unchanged despite the entry.

    Examples: Credentials
      |login                    |password|
      | malikadouma19@gmail.com |Malika2016*|










