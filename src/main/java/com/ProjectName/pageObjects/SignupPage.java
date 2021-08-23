package com.ProjectName.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SignupPage extends Page{

    @FindBy(xpath = "//a[contains(@href, \"/signup\")]")
    private WebElement continueWithEmail;

    @FindBy(name="checkEmail")
    private WebElement emailField;

    @FindBy(xpath="//button[contains(text(), \"Suivant\")]")
    private WebElement nextButton;

    public void ClickOnContinueWithEmail(){clickOn(continueWithEmail);
    System.out.println("continue with email");}

    public void fillEmail(String email){
        wait.until(ExpectedConditions.visibilityOf(emailField));
        emailField.sendKeys(email);
    }

    public void clickOnNextButton(){clickOn(nextButton);}

}
