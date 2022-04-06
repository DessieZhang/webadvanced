/*
 * Copyright © 2021 EPAM Systems, Inc. All Rights Reserved. All information contained herein is, and remains the
 * property of EPAM Systems, Inc. and/or its suppliers and is protected by international intellectual
 * property law. Dissemination of this information or reproduction of this material is strictly forbidden,
 * unless prior written permission is obtained from EPAM Systems, Inc
 */

package com.epam.bdd.utaf.web.pages.github;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class NewRepositoryPage extends GithubBasePage {
    private final Logger logger = LoggerFactory.getLogger(NewRepositoryPage.class);

    @FindBy(how = How.ID, using = "repository_name")
    private WebElement repoNameFld;

    @FindBy(how = How.ID, using = "repository_description")
    private WebElement repoDescFld;

    @FindBy(how = How.ID, using = "repository_visibility_public")
    private WebElement publicRadioBtn;

    @FindBy(how = How.ID, using = "repository_visibility_private")
    private WebElement privateRadioBtn;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Create repository')]")
    private WebElement createRepoBtn;

    @FindBy(how = How.XPATH, using = "//a[contains(@href,'repositories/new')][1]")
    private WebElement newRepoBtn;

    @FindBy(how = How.XPATH, using = "//summary[@aria-label='Create new…']")
    private WebElement createNewDropDown;

    private String repoName = null;

    public NewRepositoryPage() {
        super("New Repository Page");
    }

    public void setRepoDesc(String repositoryDesc) {
        setText(repoDescFld, repositoryDesc);
    }

    public void setRepoName(String repositoryName) {
        setText(repoNameFld, repositoryName);
        this.repoName = repositoryName;
    }

    public void setPermission(String permission) {
        switch (permission.toUpperCase()) {
            case "PUBLIC" -> clickElement(publicRadioBtn);
            case "PRIVATE" -> clickElement(privateRadioBtn);
        }
    }

    public void clickCreateRepository() {
        waitUntilElementClickable(createRepoBtn);
        clickElement(createRepoBtn);
    }

    public void clickNewRepoBtn() {
        clickElement(newRepoBtn);
    }

    public void verifyNewRepo() {
        WebElement repo = getElement(By.xpath("//a[contains(text(), '" + repoName + "')]"));
        waitUntilElementDisplayed(repo);
        assertThat(repo.isDisplayed()).isTrue();
    }

}