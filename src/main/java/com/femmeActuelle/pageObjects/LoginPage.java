package com.femmeActuelle.pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Page{

    private static final Logger Log = LogManager.getLogger(LoginPage.class);

    @FindBy(id = "logWithPassword")
    private WebElement loginWithPassword;

    @FindBy(name="enterPassword")
    private WebElement passwordField;
    
    @FindBy(xpath="//button[contains(text(), \"Me connecter\")]")
    private WebElement loginButton;

    public void clickOnLoginWithPassword(){clickOn(loginWithPassword);}

    public void fillPassword(String password){

        Log.info("Filling password");
        passwordField.sendKeys(password);}

    public void clickOnLoginButton(){
        clickOn(loginButton);
        Log.info("User logged succesfully");
    }
    
}
