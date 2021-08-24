package com.femmeActuelle.drivers;

import com.femmeActuelle.config.Properties;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory extends AbsWebDriverImpl{

    private final static String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
    private final static String GECKO_DRIVER_PROPERTY = "webdriver.gecko.driver";

    private final WebDriver driver;

    public DriverFactory(String browser){
        this.driver = this.createWebDriver(browser);
    }

    public WebDriver getDriver(){
        return driver;
    }

    public Capabilities getCapabilities(){
        return ((RemoteWebDriver) driver).getCapabilities();
    }

    public void closeDriver(){
        driver.close();
    }

    private WebDriver createWebDriver(String browser){
        String prop,path;

        switch (browser) {
            case "firefox":
                prop = GECKO_DRIVER_PROPERTY;
                break;
            default:
                prop = CHROME_DRIVER_PROPERTY;
        }
        path = Properties.Config.getWebDriverPath();
        System.setProperty(prop, path);

        return browser.equals("firefox")?
                new FirefoxDriver(getFirefoxOptions()):
                new ChromeDriver(getChromeOptions());
    }
}
