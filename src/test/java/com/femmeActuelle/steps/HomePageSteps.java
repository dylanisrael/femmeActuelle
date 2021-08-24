package com.femmeActuelle.steps;

import com.femmeActuelle.pageObjects.HomePage;
import com.femmeActuelle.pageObjects.LoginPage;
import com.femmeActuelle.pageObjects.SignupPage;
import io.cucumber.java8.En;
import org.testng.Assert;

public class HomePageSteps implements En {

    public HomePageSteps(HomePage homepage, SignupPage signuppage, LoginPage loginpage){

        Given("^Go to femmeactuelle\\.fr$", homepage::goToHomePage);

        When("^Scroll down to footer level$", homepage::scrollToFooterLevel);

        And("^Click on Attendance certified by the OJD$", homepage::clickOnAttendanceCertified);

        Then("^The display of the page content should correspond to the Attendance certified by the OjD link\\.$", () -> {
            homepage.saveScreenShotPNG();
            Assert.assertTrue(homepage.anErrorPageIsDisplayed()," ❌ Le lien nous redirige vers une page d'erreur");
        });

        When("^Click on subscribe to the magazine on the navigation bar$", homepage::clickOnSubscribeToMagazine);

        Given("^Log in with your ([^\"]*) and ([^\"]*)$", (String email, String password) -> {

            homepage.goToHomePage();
            homepage.clickOnLogin();
            signuppage.ClickOnContinueWithEmail();
            signuppage.fillEmail(email);
            signuppage.clickOnNextButton();
            loginpage.clickOnLoginWithPassword();
            loginpage.fillPassword(password);
            loginpage.clickOnLoginButton();

        });

        Given("^navigate to femmeactuelle\\.fr website on the navigation bar$", homepage::goToHomePage);

        When("^you click on the magnifying glass located in the navigation bar$", homepage::clickOnSearchButton);

        And("^enter in the search bar \"([^\"]*)\"$", homepage::fillSearchInput);

        Then("^we observed (\\d+) results for your search and all of them are in the videos category, Although not all videos\\.$", (Integer arg0) -> {
            homepage.saveScreenShotPNG();
            Assert.assertTrue(homepage.resultsAreAllVideos());
        });

        When("^you scroll down$", homepage::scrollToFooterLevel);

        Then("^There is a huge gap between the newsletter container and the footer\\.$", () -> {
            Assert.assertFalse(homepage.thereIsAHugeGap());
        });

        When("^you scroll to the footer up to the keyword level$", () -> {
        });

        And("^click on \"([^\"]*)\"$", (String arg0) -> {
            homepage.clickOnCutWords();
        });
    }
}
