package com.femmeActuelle.steps;

import com.femmeActuelle.pageObjects.HomePage;
import com.femmeActuelle.pageObjects.ProfilePage;
import io.cucumber.java8.En;
import org.testng.Assert;

public class ProfilePageSteps implements En {

    public ProfilePageSteps(ProfilePage profilePage, HomePage homePage) {
        When("^Click on edit your email$", () -> {
            homePage.clickOnMyProfile();
            profilePage.clickOnEditEmail();
            profilePage.saveScreenShotPNG();
        });
        And("^Fill in the field reserved for email on the window that opens$", () -> {
            profilePage.fillEmail();
        });
        Then("^The size of the validate button should remain unchanged despite the entry\\.$", () -> {
            profilePage.saveScreenShotPNG();
            Assert.assertTrue(profilePage.theSizeOfValidateButtonRemainTheSame(),"❌ la taille du boutton change lors de la modification de l'email");
        });
    }
}
