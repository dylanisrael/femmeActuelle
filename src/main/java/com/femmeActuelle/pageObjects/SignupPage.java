package com.femmeActuelle.pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class SignupPage extends Page{

    private static final Logger Log =  LogManager.getLogger( SignupPage.class);

    @FindBy(xpath = "//a[contains(@href, \"/signup\")]")
    private WebElement continueWithEmail;

    @FindBy(name="checkEmail")
    private WebElement emailField;

    @FindBy(xpath="//button[contains(text(), \"Suivant\")]")
    private WebElement nextButton;

    public void ClickOnContinueWithEmail(){clickOn(continueWithEmail);}

    public void fillEmail(String email){
        wait.until(visibilityOf(emailField));
        emailField.sendKeys(email);
        Log.info("Filling email");
    }

    public void clickOnNextButton(){clickOn(nextButton);}

}
