/*
 * Copyright © 2021 EPAM Systems, Inc. All Rights Reserved. All information contained herein is, and remains the
 * property of EPAM Systems, Inc. and/or its suppliers and is protected by international intellectual
 * property law. Dissemination of this information or reproduction of this material is strictly forbidden,
 * unless prior written permission is obtained from EPAM Systems, Inc
 */

package com.epam.bdd.utaf.web.steps;

import com.epam.bdd.utaf.core.driver.DriverSingleton;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class WebHooks {
    private static Logger logger = LoggerFactory.getLogger(WebHooks.class);
    private  WebDriver driver;

    @Before
    public void beforeScenario(Scenario scenario) {
        logger.info("* Start running scenario: {}", scenario.getName());
    }


    @After
    public void afterScenario(Scenario scenario) {
        logger.info("* End scenario: {}", scenario.getName());

        if (scenario.isFailed()) {
            //Take screenshot to report
            final byte[] screenshot = ((TakesScreenshot) DriverSingleton.INSTANCE.getDriver()).getScreenshotAs(OutputType.BYTES);

            scenario.embed(screenshot, "image/png", "");
            //Take screenshots to project folder
            takeScreenshot(scenario);
        }
        DriverSingleton.INSTANCE.getDriver().manage().deleteAllCookies();
    }

    private void takeScreenshot(Scenario scenario) {
        String screenshotName = scenario.getName().replaceAll(" ", "_");
        String methodName = screenshotName + "_" + getCurrentTimeAsString();

        TakesScreenshot driver = (TakesScreenshot) DriverSingleton.INSTANCE.getDriver();
        File screenFile = driver.getScreenshotAs(OutputType.FILE);
        File file = new File(".//target/screenshots/" + methodName + ".png");
        try {
            FileUtils.copyFile(screenFile, file);
            logger.info("Screenshot is saved successfully: " + file.getAbsolutePath());
        } catch (IOException e) {
            logger.error("Failed to save screenshot: " + e.getLocalizedMessage());
        }
    }

    private String getCurrentTimeAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd_HH-mm-ss");
        return ZonedDateTime.now().format(formatter);
    }

}
