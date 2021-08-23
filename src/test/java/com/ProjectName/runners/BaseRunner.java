package com.ProjectName.runners;

import com.ProjectName.config.Properties;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.*;

public class BaseRunner extends AbstractTestNGCucumberTests {


    @Parameters({"browser", "device"})
    @BeforeClass
    public static void beforeTest(@Optional String browser, @Optional String device){
        String browserA;

        browserA = java.util.Optional
                .ofNullable(browser)
                .orElse(Properties.Config.getBrowser());

        Properties.DriverManager.setDriver(browserA);
    }

    @AfterTest
    public static void tearDown(){
        Properties.DriverManager.getDriver().quit();
    }

}
