package com.ProjectName.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class PrimaShopPage extends Page {

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
            longWait.until(ExpectedConditions.visibilityOf(divCookie));
            driver.switchTo().frame(1);
            clickOn(acceptCookieButton);
        }catch (Exception e){

        }
        driver.switchTo().defaultContent();
        action.moveToElement(seeAllIssues).click().build().perform();
        waitForLoadingPage();
    }

    public void searchIssues(String nameWithS, String nameWithoutS) throws InterruptedException {
        action.moveToElement(searchIssuesField).build().perform();
        searchIssuesField.sendKeys(nameWithS);
        clickOn(searchButton);

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
        searchIssuesField.clear();
        searchIssuesField.sendKeys(nameWithoutS);
        clickOn(searchButton);
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

    }

    public boolean theResultsAreSame() {
        System.out.println("les resultat de la recherche pour economie a "+resultsEconomie.size()+" et economies a "+resultsEconomies.size());
        if(resultsEconomies.size() == resultsEconomie.size()){
            return true;
        }
        else {
            return false;
        }
    }
}
