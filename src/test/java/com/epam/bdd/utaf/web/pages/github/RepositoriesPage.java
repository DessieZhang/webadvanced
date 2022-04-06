/*
 * Copyright © 2021 EPAM Systems, Inc. All Rights Reserved. All information contained herein is, and remains the
 * property of EPAM Systems, Inc. and/or its suppliers and is protected by international intellectual
 * property law. Dissemination of this information or reproduction of this material is strictly forbidden,
 * unless prior written permission is obtained from EPAM Systems, Inc
 */

package com.epam.bdd.utaf.web.pages.github;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class RepositoriesPage extends GithubBasePage {
    private final Logger logger = LoggerFactory.getLogger(RepositoriesPage.class);

    @FindBy(how = How.ID, using = "your-repos-filter")
    private WebElement repoFilter;

    @FindBy(how = How.ID, using = "user-repositories-list")
    private WebElement searchSummary;

    public RepositoriesPage() {
        super("Repositories Page");
    }

    public void inputRepoFilter(String repoName) throws InterruptedException {
        setText(repoFilter, repoName);
        Thread.sleep(1000);
    }

    public void verifyDeletedRepo() {
        assertThat(searchSummary.getText()).contains("0 results for repositories matching");
    }

}