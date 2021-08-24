package com.femmeActuelle.pageObjects;

import com.femmeActuelle.config.Configuration;
import com.femmeActuelle.config.Properties;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.ByteArrayInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.function.Function;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class Page {

    @FindBy( css = "div.message-component.message-row.mainrow")
    private WebElement popInCookieWrap;

    @FindBy( css = "button.message-component.message-button.no-children.focusable.accepter.sp_choice_type_11.last-focusable-el")
    private WebElement popInCookieButton;

    protected WebDriver driver;
    protected JavascriptExecutor js;
    protected Actions action;

    /**** waiter\*/
    protected WebDriverWait wait;
    protected WebDriverWait shortWait;
    protected WebDriverWait middleWait;
    protected WebDriverWait longWait;

    protected Configuration config = Properties.Config;

    //    Page constructor
    Page(){

        // Init
        driver = Properties.DriverManager.getDriver();
        PageFactory.initElements(driver, this);

        js = (JavascriptExecutor) driver;
        action = new Actions(driver);

        //Waiter
        wait        = new WebDriverWait(driver, Duration.ofSeconds(4));
        shortWait   = new WebDriverWait(driver, Duration.ofSeconds(10));
        middleWait  = new WebDriverWait(driver, Duration.ofSeconds(20));
        longWait    = new WebDriverWait(driver, Duration.ofSeconds(30));

    }

    // waiters functions
    protected <V>boolean waitUntil(Function<? super WebDriver, V> isTrue){
        try{
            wait.until(isTrue);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    protected <V>boolean shortUntil(Function<? super WebDriver, V> isTrue){
        try{
            shortWait.until(isTrue);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /***
     *
     * @param isTrue
     * @param <V>
     * @return
     */

    /***
     *
     * @param isTrue
     * @param <V>
     * @return
     */

    protected <V>boolean longUntil(Function<? super WebDriver, V> isTrue){
        try{
            longWait.until(isTrue);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**** Waiting for a page to loaded*/
    protected void waitForLoadingPage( ){
        if(!longUntil(driver->js.executeScript("return document.readyState").equals("complete") || js.executeScript("return document.readyState").equals("interactive") )){
            throw new RuntimeException(driver.getCurrentUrl()+" not loaded");
        }
    }

    /***
     *get a page
     * @param url
     */
    protected void get(String url){
        driver.get(url);
        waitForLoadingPage();
    }

    //  Click on an element
    protected void clickOn(WebElement element){

        if( !shortUntil(visibilityOf(element)) ){
            // Logger
            throw new RuntimeException("Element not visible after click");
        }

        if( !shortUntil(elementToBeClickable(element))){
            // Logger
            throw new RuntimeException("Element not clickable");
        }
        element.click();
    }

    // vertical scrolling on the page
    protected void scroll(int height){
        js.executeScript("window.scrollBy(0,"+height+")", "");
    }

    //scroll to an element
    protected void scrollTo(WebElement element){js.executeScript("arguments[0].scrollIntoView(true);", element);}

    // get http request response code
    public Boolean checkUrlResponseCode(String url){

        try {
            HttpURLConnection c= (HttpURLConnection)new URL(url).openConnection();
            c.setRequestMethod("HEAD");
            c.connect();
            int r = c.getResponseCode();
            System.out.println("Http response code: " + r);
            if(r != 200) return true;
            return false;
        }catch (Exception e){
            return false;
        }

    }
    //get a page url
    protected String getPageUrl(){
        return driver.getCurrentUrl();
    }

    //    Accept cookies
    protected void cookieManager( ){
        if(waitUntil(visibilityOf(popInCookieWrap))) clickOn(popInCookieButton);
    }

    @Attachment(value = "screenshot", type = "image/png")
    public void saveScreenShotPNG(){
        Allure.addAttachment("screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    //    Verify color
    public String ColorVerify(WebElement target, String cssValue)
    {
        /* This method used to verify color code*/
        String colorCode= target.getCssValue(cssValue);
        String hexacolor = Color.fromString(colorCode).asHex();
        return hexacolor;
    }

}
