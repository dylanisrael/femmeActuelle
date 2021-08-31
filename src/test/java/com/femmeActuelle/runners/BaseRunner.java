package com.femmeActuelle.runners;

import com.femmeActuelle.config.Properties;
import com.femmeActuelle.utils.AllureFilesManager;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.IOException;

public class BaseRunner extends AbstractTestNGCucumberTests {


    @Parameters({"browser", "device"})
    @BeforeClass
    public static void beforeTest(@Optional String browser, @Optional String device) throws ConfigurationException, IOException {
        String browserA;

        browserA = java.util.Optional
                .ofNullable(browser)
                .orElse(Properties.Config.getBrowser());

        Properties.DriverManager.setDriver(browserA);
        AllureFilesManager.createEnvironmentPropertiesFile();
        AllureFilesManager.createCategorieJsonFile();
        AllureFilesManager.createExecutorJsonFile();
    }

    @AfterClass
    public static void tearDown(){
        Properties.DriverManager.getDriver().quit();
    }

}
