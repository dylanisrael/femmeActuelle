Feature:  Recommandations

  @Recommandation_1375
  Scenario: Improve search filters

    Given navigate to femmeactuelle.fr website on the navigation bar
    When you click on the magnifying glass located in the navigation bar
    And enter in the search bar "Ovaire"
    Then we observed 17 results for your search and all of them are in the videos category, Although not all videos.

  @Recommandation_1376
  Scenario: Superfluous space
    Given Go to femmeactuelle.fr
    When you scroll down
    Then There is a huge gap between the newsletter container and the footer.

  @Recommandation_1413
  Scenario: Change text color
    Given Go to femmeactuelle.fr
    When you scroll to the footer up to the keyword level
    And click on "cut words"
    Then Note that the text color used for container texts is unreadable.