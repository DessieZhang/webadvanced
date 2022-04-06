/*
 * Copyright © 2021 EPAM Systems, Inc. All Rights Reserved. All information contained herein is, and remains the
 * property of EPAM Systems, Inc. and/or its suppliers and is protected by international intellectual
 * property law. Dissemination of this information or reproduction of this material is strictly forbidden,
 * unless prior written permission is obtained from EPAM Systems, Inc
 */

package com.epam.bdd.utaf.web.runners;

import com.epam.bdd.utaf.core.GlobalVar;
import com.epam.bdd.utaf.core.driver.DriverSingleton;
import com.epam.bdd.utaf.core.runners.UTAFTestRunner;
import com.epam.bdd.utaf.core.utils.PropertiesReader;
import io.cucumber.testng.CucumberOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.Map;

@CucumberOptions(
        plugin = {"pretty", "com.epam.reportportal.cucumber.StepReporter"},
        features = {"src/test/java/com/epam/bdd/utaf/web/features/yandex/"},
        glue = {"com/epam/bdd/utaf/web/steps"},
        tags = "",
        monochrome = true)
public class WebTestRunner extends UTAFTestRunner {

    private static final Logger logger = LoggerFactory.getLogger(WebTestRunner.class);

    @BeforeSuite(
            alwaysRun = true
    )
    @Override
    public void setUp() {
        GlobalVar.ENV = System.getProperty("env");
        GlobalVar.PLATFORM = System.getProperty("platform");
        super.setUp();
    }
}

