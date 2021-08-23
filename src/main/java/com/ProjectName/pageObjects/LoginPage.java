package com.ProjectName.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Page{
    @FindBy(id = "logWithPassword")
    private WebElement loginWithPassword;

    @FindBy(name="enterPassword")
    private WebElement passwordField;
    
    @FindBy(xpath="//button[contains(text(), \"Me connecter\")]")
    private WebElement loginButton;

    public void clickOnLoginWithPassword(){clickOn(loginWithPassword);}

    public void fillPassword(String password){passwordField.sendKeys(password);}

    public void clickOnLoginButton(){clickOn(loginButton);}
    
}
