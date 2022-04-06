/*
 * Copyright © 2021 EPAM Systems, Inc. All Rights Reserved. All information contained herein is, and remains the
 * property of EPAM Systems, Inc. and/or its suppliers and is protected by international intellectual
 * property law. Dissemination of this information or reproduction of this material is strictly forbidden,
 * unless prior written permission is obtained from EPAM Systems, Inc
 */

package com.epam.bdd.utaf.web.steps.github;

import com.epam.bdd.utaf.core.GlobalVar;
import com.epam.bdd.utaf.core.driver.DriverSingleton;
import com.epam.bdd.utaf.web.pages.github.NewRepositoryPage;
import com.epam.bdd.utaf.web.pages.github.TopBarPage;
import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Locale;
import java.util.Map;


public class NewRepoSteps {
    private static Logger logger = LoggerFactory.getLogger(NewRepoSteps.class);
    private static Faker faker = new Faker(new Locale("en", "US"));
    private NewRepositoryPage newRepositoryPage;
    private TopBarPage topBarPage;

    public NewRepoSteps() {
        newRepositoryPage = new NewRepositoryPage();
        topBarPage = new TopBarPage();
    }

    @Given("I click New button")
    public void clickNewButton() throws InterruptedException {
        logger.info("Click new repository button");
        topBarPage.clickNewRepoBtn();
        Thread.sleep(Integer.valueOf(GlobalVar.GLOBAL_VARIABLES.get("sleep")));
    }

    @Given("I click Create button")
    public void clickCreateButton() throws InterruptedException {
        logger.info("Click create repository button to complete the creation");
//        Thread.sleep(1000);
        newRepositoryPage.clickCreateRepository();
        Thread.sleep(Integer.valueOf(GlobalVar.GLOBAL_VARIABLES.get("sleep")));
    }

    @When("I give repository name")
    public void setRepoName(List<Map<String, String>> datatable) throws InterruptedException {
        logger.info("Input repo name");
        String name = datatable.get(0).get("name");
        if (name.equals("_Random")) {
            name = faker.name().fullName().replace(" ", "-").replace("'", "-");
            GlobalVar.GLOBAL_VARIABLES.put(Repository.REPOSITORY_NAME, name);
        }
        newRepositoryPage.setRepoName(name);
        Thread.sleep(Integer.valueOf(GlobalVar.GLOBAL_VARIABLES.get("sleep")));
    }

    @And("I give repository description")
    public void setRepoDesc(List<Map<String, String>> datatable) throws InterruptedException {
        logger.info("Input repo description");
        newRepositoryPage.setRepoDesc(datatable.get(0).get("description"));
        Thread.sleep(Integer.valueOf(GlobalVar.GLOBAL_VARIABLES.get("sleep")));
    }

    @And("I select repository permission")
    public void setRepoPerm(List<Map<String, String>> datatable) throws InterruptedException {
        logger.info("Set repo permission");
        newRepositoryPage.setPermission(datatable.get(0).get("permission"));
        Thread.sleep(Integer.valueOf(GlobalVar.GLOBAL_VARIABLES.get("sleep")));
    }

    @Then("I see the new repository")
    public void verifyNewRepo() {
        logger.info("Verify new repo");
        newRepositoryPage.verifyNewRepo();
    }

}
