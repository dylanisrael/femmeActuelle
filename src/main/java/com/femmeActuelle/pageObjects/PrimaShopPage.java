package com.femmeActuelle.pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

import java.util.ArrayList;
import java.util.List;

public class PrimaShopPage extends Page {
    private static final Logger Log = LogManager.getLogger(PrimaShopPage.class);

    @FindBy(css = "a.title-link")
    private WebElement seeAllIssues;

    @FindBy(css= "div.message-container.messagewindow")
    private WebElement cookieContainer;

    @FindBy(id = "searchnum")
    private WebElement searchIssuesField;

    @FindBy(id = "sp_message_container_533495")
    private WebElement divCookie;

    @FindBy(css="button.accepter")
    private WebElement acceptCookieButton;

    @FindBy(xpath = "//*[@id=\"content\"]/div/section[5]/div[1]/h3/div")
    private WebElement para;

    @FindBy(css = "button.search-btn")
    private WebElement searchButton;

    @FindBy(xpath = "//*[@id=\"js-content-post-replace\"]/div/a")
    private List<WebElement> articles;

    private ArrayList<String> resultsEconomie = new ArrayList<>();
    private ArrayList<String> resultsEconomies= new ArrayList<>() ;



    public void clickOnSeeAllIssues() throws InterruptedException {
        try{
            longWait.until(visibilityOf(divCookie));
            driver.switchTo().frame(1);
            clickOn(acceptCookieButton);
        }catch (Exception ignored){

        }
        driver.switchTo().defaultContent();
        action.moveToElement(seeAllIssues).click().build().perform();
        waitForLoadingPage();
    }

    public void searchIssuesWithS(String nameWithS ){

        scrollTo(searchIssuesField);
        searchIssuesField.sendKeys(nameWithS);
        clickOn(searchButton);
        Log.info("Search of {}",nameWithS);

            try {
                for (int i=0 ; i< articles.size() ; i++) {
                    if (articles.get(i).isDisplayed()) {
                        resultsEconomie.add(articles.get(i).getAttribute("title"));
                    }
                }
            }catch (Exception e){
                for (int i=0 ; i< articles.size() ; i++) {
                    if (articles.get(i).isDisplayed()) {
                        resultsEconomie.add(articles.get(i).getAttribute("title"));
                    }
                }
            }
        longWait.until(presenceOfAllElementsLocatedBy(By.cssSelector("div.cover > img.lazy")));
    }

    public  void searchIssuesWithoutS(String nameWithoutS){
        searchIssuesField.clear();
        searchIssuesField.sendKeys(nameWithoutS);
        clickOn(searchButton);

        Log.info("Search of {}",nameWithoutS);
        try {
            for (int i=0 ; i< articles.size() ; i++) {
                if (articles.get(i).isDisplayed() ){
                    resultsEconomies.add(articles.get(i).getAttribute("title"));
                }
            }
        }
        catch (Exception e) {
            for (int i=0 ; i< articles.size() ; i++) {
                if (articles.get(i).isDisplayed() ){
                    resultsEconomies.add(articles.get(i).getAttribute("title"));
                }
            }
        }
        longWait.until(presenceOfAllElementsLocatedBy(By.cssSelector("div.cover > img.lazy")));
    }

    public boolean theResultsAreSame() {
        Log.info("Checking if results are same");
        return resultsEconomies.size() == resultsEconomie.size();
    }
}
