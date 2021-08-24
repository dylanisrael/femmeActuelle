package com.femmeActuelle.pageObjects;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import java.util.List;

public class HomePage extends Page{

    Logger Log = LogManager.getLogger(HomePage.class);

    @FindBy(linkText = "Fréquentation certifiée par l'OJD")
    private WebElement attendanceCertifiedByOJDLink;

    @FindBy(className = "headerNav-magText")
    private WebElement subscribeToMagazineLink;

    @FindBy(linkText = "Mon profil")
    private WebElement myProfileLink;

    @FindBy(linkText = "Gérer mon profil")
    private WebElement manageMyProfileLink;

    @FindBy(xpath = "//div[contains(@class, \"pmc-authentification\")]")
    private WebElement login;

    @FindBy(css = "div.articleCardImage-playCircle")
    private List<WebElement> videoArticleList;

    @FindBy(linkText = "Vidéos")
    private WebElement videoTab;

    @FindBy(className = "articleCard-body")
    private List<WebElement> articleList;

    @FindBy(className ="headerNav-searchButton")
    private WebElement searchButton;
    
    @FindBy(className="headerSearch-searchInput")
    private WebElement searchInput;

    @FindBy(className = "articleList")
    private WebElement articleSection;

    @FindBy(css="div.home-bottomPartLeft > section")
    private List<WebElement> sectionLeftContent;

    @FindBy(className="home-bottomPartLeft")
    private WebElement sectionLeftContainer;

    @FindBy(css="div.home-bottomPartRight > section")
    private List<WebElement> sectionRightContent;

    @FindBy(className ="home-bottomPartLeft")
    private WebElement sectionRightContainer;

    @FindBy(linkText="Mots Coupes")
    private WebElement cutWord;



    public HomePage(){ }

    private final String HOME_PAGE_URL = config.getEnvironment();

    public  void goToHomePage(){
        get(HOME_PAGE_URL);
        cookieManager();
        Log.info("On home page");
    }

    public void clickOnAttendanceCertified(){
        longWait.until(elementToBeClickable(attendanceCertifiedByOJDLink));
        js.executeScript("arguments[0].click()",attendanceCertifiedByOJDLink);
    }

    public void scrollToFooterLevel() {
        scroll(2000);
    }

    public boolean anErrorPageIsDisplayed(){
        Log.info("Checking url response code");
        return checkUrlResponseCode(getPageUrl());
    }

    public void clickOnSubscribeToMagazine(){
        clickOn(subscribeToMagazineLink);
        waitForLoadingPage();
    }

    public void clickOnLogin(){clickOn(login);}

    public void clickOnMyProfile(){
        longWait.until(visibilityOf(myProfileLink));
        action.moveToElement(myProfileLink).perform();
        wait.until(visibilityOf(manageMyProfileLink));
        clickOn(manageMyProfileLink);
        Log.info("Click on My Profile");
    }

    public void clickOnSearchButton(){
        clickOn(searchButton);
    }

    public void fillSearchInput(String mot){
        wait.until(visibilityOf(searchInput));
        searchInput.sendKeys(mot, Keys.ENTER);
        Log.info("Search input filled with {} ",mot);
    }

    public void clickOnVideoTab(){
        wait.until(visibilityOf(articleSection));
        clickOn(videoTab);
    }

    public boolean resultsAreAllVideos(){
        Log.info("Checking if all results are Videos");
        return articleList.size() == videoArticleList.size();
    }

    public boolean thereIsAHugeGap(){

        int leftContainerHeight =sectionLeftContainer.getRect().getHeight() ,
                rightContainerHeight = sectionRightContainer.getRect().getHeight(),
                sectionRightElementsTotalSize = 0 ,
                sectionLeftElementsTotalSize = 0;

        for (int i=0 ;i<sectionLeftContent.size();i++ ){
            sectionLeftElementsTotalSize += sectionLeftContent.get(i).getRect().getHeight();
            sectionRightElementsTotalSize += sectionRightContent.get(i).getRect().getHeight();
        }

        Log.info("Checking superflous spaces");

        return sectionLeftElementsTotalSize < ((leftContainerHeight * 90) / 100) || sectionRightElementsTotalSize < (rightContainerHeight * 90) / 100;
    }

    public void clickOnCutWords(){
        wait.until(elementToBeClickable(cutWord));
        js.executeScript("arguments[0].scrollIntoView(true);", cutWord);
        js.executeScript("arguments[0].click();", cutWord);
    }

}
