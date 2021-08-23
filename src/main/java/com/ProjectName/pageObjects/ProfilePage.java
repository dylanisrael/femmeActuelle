package com.ProjectName.pageObjects;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePage extends Page{

    @FindBy(xpath ="//span[contains(text(),'Modifier votre email')]")
    private WebElement editYourEmail;
    
    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(xpath ="//button[contains(text(),'Valider')]")
    private WebElement validateButton;

    @FindBy(xpath="//span[contains(text(),\"Ce format d'email est invalide. Ex: monnom@email.c\")]")
    private WebElement invalidEmailMessage;

    private int initialheight ;

    public void clickOnEditEmail(){clickOn(editYourEmail);};

    public void fillEmail(){
        Rectangle size = validateButton.getRect();
        initialheight = size.getHeight();
        emailField.sendKeys("a");}

    public boolean theSizeOfValidateButtonRemainTheSame(){

        Rectangle size = validateButton.getRect();
        int currentHeight = size.getHeight();

        return currentHeight == initialheight;

    }



}
