package com.ProjectName.steps;

import com.ProjectName.pageObjects.PrimaShopPage;
import io.cucumber.java8.En;
import org.testng.Assert;

public class PrimaShopPageSteps implements En {

    public PrimaShopPageSteps(PrimaShopPage primaShopPage) {

        And("^CLick on see all numbers$", ()->{

            primaShopPage.clickOnSeeAllIssues();
        });

        And("^Do an research ([^\"]*)", (String text) -> {
            primaShopPage.searchIssuesWithS(text);
            primaShopPage.saveScreenShotPNG();
        });

        And("^research ([^\"]*)", (String text) -> {
            primaShopPage.searchIssuesWithoutS(text);
            primaShopPage.saveScreenShotPNG();
        });
        Then("^The number of economy research articles with s had to be equal to the one without s\\.$", () -> {
            Assert.assertTrue(primaShopPage.theResultsAreSame(),"les resultats de recherche restent differents");
        });
    }
}
