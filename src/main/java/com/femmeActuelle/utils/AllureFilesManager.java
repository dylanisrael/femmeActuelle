package com.femmeActuelle.utils;

import com.femmeActuelle.config.Properties;
import org.apache.commons.configuration.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class AllureFilesManager {

    public static Logger logger = LoggerFactory.getLogger(AllureFilesManager.class);

    public static void createEnvironmentPropertiesFile() throws IOException, ConfigurationException {
        FileOutputStream fileOutputStream
                = new FileOutputStream(
                "allure-results/environment.properties");

        java.util.Properties properties = new java.util.Properties();
        properties.setProperty("Test URL ☢️", Properties.Config.getEnvironment());
      properties.setProperty("Headless mode 🌀", String.valueOf(Properties.Config.getHeadless()));
        properties.setProperty("Local browser 🌍", String.valueOf(Properties.Config.getBrowser()));
        properties.store(
                fileOutputStream,
                "environement allure configuration file");

        fileOutputStream.close();
    }

    public static void createCategorieJsonFile() {

        JSONObject ignoredTests = new JSONObject();
        ignoredTests.put("name", "Ignored tests");

        JSONArray matchedStatusesIT = new JSONArray();
        matchedStatusesIT.add("skipped");

        ignoredTests.put("matchedStatuses", matchedStatusesIT);


        JSONObject infrastructureProblems = new JSONObject();
        infrastructureProblems.put("name", "Infrastructure problems");

        JSONArray matchedStatusesIP = new JSONArray();
        matchedStatusesIP.add("broken");
        matchedStatusesIP.add("failed");

        infrastructureProblems.put("matchedStatuses", matchedStatusesIP);
        infrastructureProblems.put("messageRegex", ".*bye-bye.*");

        JSONObject outdatedTests = new JSONObject();
        outdatedTests.put("name", "Outdated tests");
            JSONArray matchedStatusesOT = new JSONArray();
            matchedStatusesOT.add("broken");
        outdatedTests.put("matchedStatuses", matchedStatusesOT);
        outdatedTests.put("traceRegex", ".*FileNotFoundException.*");

        JSONObject koTests = new JSONObject();
        koTests.put("name", "KO tests");
            JSONArray matchedStatusesKO = new JSONArray();
            matchedStatusesKO.add("failed");
        koTests.put("matchedStatuses", matchedStatusesKO);

        JSONObject errorDuringTests = new JSONObject();
        errorDuringTests.put("name", "Error during test execution");
            JSONArray matchedStatusesEDE = new JSONArray();
            matchedStatusesEDE.add("broken");
        errorDuringTests.put("matchedStatuses", matchedStatusesEDE);

        JSONArray categories = new JSONArray();
        categories.add(ignoredTests);
        categories.add(infrastructureProblems);
        categories.add(outdatedTests);
        categories.add(koTests);
        categories.add(errorDuringTests);


        //Write JSON file
        try (FileWriter file = new FileWriter("allure-results/categories.json")) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(categories.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createExecutorJsonFile() {
        JSONObject executor = new JSONObject();
        executor.put("name", "Maven");
        executor.put("type", "Maven");
        executor.put( "reportName", "femmeActuelleTestsReport");

        //Write JSON file
        try (FileWriter file = new FileWriter("allure-results/executor.json")) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(executor.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
