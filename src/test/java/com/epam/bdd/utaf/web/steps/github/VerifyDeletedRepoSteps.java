/*
 * Copyright © 2021 EPAM Systems, Inc. All Rights Reserved. All information contained herein is, and remains the
 * property of EPAM Systems, Inc. and/or its suppliers and is protected by international intellectual
 * property law. Dissemination of this information or reproduction of this material is strictly forbidden,
 * unless prior written permission is obtained from EPAM Systems, Inc
 */

package com.epam.bdd.utaf.web.steps.github;

import com.epam.bdd.utaf.core.GlobalVar;
import com.epam.bdd.utaf.web.pages.github.RepositoriesPage;
import com.epam.bdd.utaf.web.pages.github.TopBarPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;


public class VerifyDeletedRepoSteps {
    private static Logger logger = LoggerFactory.getLogger(VerifyDeletedRepoSteps.class);
    private TopBarPage topBarPage;
    private RepositoriesPage repositoriesPage;

    public VerifyDeletedRepoSteps() {
        topBarPage = new TopBarPage();
        repositoriesPage = new RepositoriesPage();
    }

    @Given("I click Your repositories button")
    public void clickRepositories() throws InterruptedException {
        logger.info("Click Your repositories");
        topBarPage.clickRepoListBtn();
        Thread.sleep(Integer.valueOf(GlobalVar.GLOBAL_VARIABLES.get("sleep")));
    }

    @When("I search repository name")
    public void search(List<Map<String, String>> datatable) throws InterruptedException {
        logger.info("Set search text");
        String repoName = datatable.get(0).get("name");
        if (repoName.contains("${")) {
            String var = repoName.substring(repoName.indexOf("{") + 1, repoName.indexOf("}"));
            repoName = repoName.replace("${" + var + "}", GlobalVar.GLOBAL_VARIABLES.get(var));
        }
        repositoriesPage.inputRepoFilter(repoName);
        Thread.sleep(Integer.valueOf(GlobalVar.GLOBAL_VARIABLES.get("sleep")));
    }

    @Then("I see 0 result in search summary")
    public void verifyNoResult() {
        logger.info("Verify no result found");
        repositoriesPage.verifyDeletedRepo();
    }

}
