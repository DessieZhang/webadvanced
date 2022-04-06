/*
 * Copyright © 2021 EPAM Systems, Inc. All Rights Reserved. All information contained herein is, and remains the
 * property of EPAM Systems, Inc. and/or its suppliers and is protected by international intellectual
 * property law. Dissemination of this information or reproduction of this material is strictly forbidden,
 * unless prior written permission is obtained from EPAM Systems, Inc
 */

package com.epam.bdd.utaf.web.pages.github;

import com.epam.bdd.utaf.core.base.WebBasePage;
import com.epam.bdd.utaf.core.utils.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginPage extends GithubBasePage {
    private final Logger logger = LoggerFactory.getLogger(LoginPage.class);

    public LoginPage() {
        super("Login Page");
    }


    @FindBy(how = How.LINK_TEXT, using = "Sign in")
    private WebElement signInLnk;

    @FindBy(how = How.ID, using = "login_field")
    private WebElement usernameFld;

    @FindBy(how = How.ID, using = "password")
    private WebElement passwordFld;

    @FindBy(how = How.NAME, using = "commit")
    private WebElement signInBtn;

    @FindBy(how = How.XPATH, using = "//summary[@aria-label='View profile and more']")
    private WebElement userProfileImg;

    public void clickSignIn() {
        clickElement(signInLnk);
        assertThat(signInBtn.isDisplayed()).isTrue();
    }

    public void setAccount(String username, String password) {
        if (isElementVisible(passwordFld)) {
            setText(usernameFld, username);
            setText(passwordFld, password);
        } else
            Utils.logFail("Password field is not visible.");
    }

    public void verifySignedIn() {
        assertThat(userProfileImg.isDisplayed()).as("The user profile image displayed").isTrue();
    }

//    public void clickChineseLink(WebDriver driver) {
//        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
//        WebDriverWait wait = new WebDriverWait(30);
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/footer/div/div[2]/ul/div[1]/div/div[2]/span[1]/a/span")));
//        clickElement(linkChinese);
//        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
//        logger.info("I select the language for women page as Chinese");
//    }
//
//    public void clickEnglishLink(WebDriver driver) {
//        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
//        WebDriverWait wait = new WebDriverWait(30);
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/footer/div/div[2]/ul/div[1]/div/div[2]/span[3]")));
//        clickElement(linkEnglish);
//        logger.info("I select the language as english");
//    }
//
//    public void checkAfterSwitchLanguage(WebDriver String language) {
//        Assert.assertTrue(checkText.isDisplayed());
//        Assert.assertEquals(checkText.getText(), language);
//        logger.info("* After switch language, check the header of the text successfully");
//    }
//
//    public void navigateToWomen(WebDriver driver) {
//        clickElement(womenLink);
//        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
//        logger.info("I navigate to the women page");
//    }
//
//    public void clickMyAccount(WebDriver driver) {
////        waitUntilElementClickable(driver,btnMyAccount);
////        btnMyAccount.click();
//        clickElement(btnMyAccount);
//    }
//
//    public void hoverMyAccountandClickRegister(WebDriver driver) {
//        moveToElementAndClickElement(btnMyAccount, btnRegister);
//    }
//
//    public void hoverWomenMenuandClickNewArrivals(WebDriver driver) {
//        moveToElementAndClickElement(btnWomen, lnkWomenNewArrival);
//    }
//
//    public void hoverMyAccount(WebDriver driver) {
//        moveToElement(btnMyAccount);
//    }
//
//    public void moveToLogo(WebDriver driver) {
//        moveToElement(barLogo);
//    }
//
//    public boolean isProductinUserGuestMenu(WebDriver String product_id) {
//        String productInGuestMenuXpath = "//div[@class='user-guest-menu']//a[contains(@href,'" + product_id + "')]";
//        int size = driver.findElements(By.xpath(productInGuestMenuXpath)).size();
//        if (size > 0) {
//            return true;
//        }
//        return false;
//    }
//
//    public void hoverBagMenuandCliekNewArrivals(WebDriver driver) throws InterruptedException {
//        moveToElementAndClickElement(bagMenu, linkLoaBagArrival);
//    }
//
//    public void chooseScarfUnderWoman(WebDriver driver) {
//        moveToElementAndClickElement(btnWomen, sacrfBagArrival);
//    }

}