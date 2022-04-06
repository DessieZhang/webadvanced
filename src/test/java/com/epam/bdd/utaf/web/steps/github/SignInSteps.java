/*
 * Copyright © 2021 EPAM Systems, Inc. All Rights Reserved. All information contained herein is, and remains the
 * property of EPAM Systems, Inc. and/or its suppliers and is protected by international intellectual
 * property law. Dissemination of this information or reproduction of this material is strictly forbidden,
 * unless prior written permission is obtained from EPAM Systems, Inc
 */

package com.epam.bdd.utaf.web.steps.github;

import com.epam.bdd.utaf.core.GlobalVar;
import com.epam.bdd.utaf.core.driver.DriverSingleton;
import com.epam.bdd.utaf.core.utils.EncryUtil;
import com.epam.bdd.utaf.core.utils.PropertiesReader;
import com.epam.bdd.utaf.web.pages.github.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;


public class SignInSteps {
    private static Logger logger = LoggerFactory.getLogger(SignInSteps.class);
    private LoginPage loginPage;

    public SignInSteps() {
        loginPage = new LoginPage();
    }

    @Given("I navigate to GitHub website")
    public void openSignPage() throws InterruptedException {
        logger.info("Navigate to github website");
        loginPage.openWebURL(PropertiesReader.getInstance().getProperty("url"));
        Thread.sleep(Integer.valueOf(GlobalVar.GLOBAL_VARIABLES.get("sleep")));
    }

    @When("I click Sign in button")
    public void clickSignIn() throws InterruptedException {
        logger.info("Click signin icon");
        loginPage.clickSignIn();
        Thread.sleep(Integer.valueOf(GlobalVar.GLOBAL_VARIABLES.get("sleep")));
    }

    @And("I input account and click sign in button")
    public void setAccount(List<Map<String, String>> account) throws InterruptedException {
        logger.info("Input email and password");
        loginPage.setAccount(account.get(0).get("email"), EncryUtil.decodePassword(account.get(0).get("password")));
        Thread.sleep(Integer.valueOf(GlobalVar.GLOBAL_VARIABLES.get("sleep")));
    }

    @Then("I see the organization home page")
    public void verifySignin() {
        logger.info("Verify signed in page");
        loginPage.verifySignedIn();
    }
}
