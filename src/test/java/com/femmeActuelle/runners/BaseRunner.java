package com.femmeActuelle.runners;

import com.femmeActuelle.config.Properties;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;

public class BaseRunner extends AbstractTestNGCucumberTests {

    private static final Logger Log =  LogManager.getLogger( BaseRunner.class);


    @Parameters({"browser", "device"})
    @BeforeClass
    public static void beforeTest(@Optional String browser, @Optional String device){
        String browserA;
        browserA = java.util.Optional
                .ofNullable(browser)
                .orElse(Properties.Config.getBrowser());
        Log.info("Tests are starting");
        Properties.DriverManager.setDriver(browserA);

    }

    @AfterTest
    public static void tearDown(){
        Log.info("Tests are ending");
        Properties.DriverManager.getDriver().quit();
    }

}
