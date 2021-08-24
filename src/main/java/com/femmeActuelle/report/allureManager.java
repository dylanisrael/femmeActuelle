package com.femmeActuelle.report;

import com.femmeActuelle.config.Properties;
import com.femmeActuelle.driverManager.WebDriverManager;
import com.github.automatedowl.tools.AllureEnvironmentWriter;
import com.google.common.collect.ImmutableMap;
import io.qameta.allure.Attachment;

public class allureManager {
    public static WebDriverManager driverManager = WebDriverManager.getInstance();

    public static void setAllureEnvironmentInformation() {
        AllureEnvironmentWriter.allureEnvironmentWriter(
                ImmutableMap.<String, String>builder().
                        put("Test URL", Properties.Config.getEnvironment()).
                        put("Browser", String.valueOf(Properties.Config.getBrowser())).
                        put("Headless mode", String.valueOf(Properties.Config.getHeadless())).
                        put("Browser version",Properties.DriverManager.getCapabilities().getVersion()).
                        put("Platform name", String.valueOf(Properties.DriverManager.getCapabilities().getPlatform())).
                        build(),System.getProperty("user.dir")+"/allure-results/");
    }


}
