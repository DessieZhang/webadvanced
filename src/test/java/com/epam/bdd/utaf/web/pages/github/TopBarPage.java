/*
 * Copyright © 2021 EPAM Systems, Inc. All Rights Reserved. All information contained herein is, and remains the
 * property of EPAM Systems, Inc. and/or its suppliers and is protected by international intellectual
 * property law. Dissemination of this information or reproduction of this material is strictly forbidden,
 * unless prior written permission is obtained from EPAM Systems, Inc
 */

package com.epam.bdd.utaf.web.pages.github;

import com.epam.bdd.utaf.core.base.WebBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TopBarPage extends GithubBasePage {
    private final Logger logger = LoggerFactory.getLogger(TopBarPage.class);

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'New repository')]")
    private WebElement newRepoBtn;

    @FindBy(how = How.LINK_TEXT, using = "Your repositories")
    private WebElement repoListBtn;

    @FindBy(how = How.XPATH, using = "//summary[@aria-label='Create new…']")
    private WebElement createNewDropDown;

    @FindBy(how = How.XPATH, using = "//summary[@aria-label='View profile and more']")
    private WebElement viewProfileDropDown;


    public TopBarPage() {
        super("Top Bar Page");
    }

    public void clickNewRepoBtn() {
        clickElement(createNewDropDown);
        clickElement(newRepoBtn);
    }

    public void clickRepoListBtn() {
        clickElement(viewProfileDropDown);
        clickElement(repoListBtn);
    }

}