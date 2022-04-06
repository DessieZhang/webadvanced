package com.epam.bdd.utaf.web.pages.yandex;

import com.epam.bdd.utaf.core.base.WebBasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginPage extends WebBasePage {
    private final Logger logger = LoggerFactory.getLogger(LoginPage.class);
    public LoginPage() {
        super("Login Page");
    }

    @FindBy(how = How.CSS, using = "span.WelcomePage-tagline")
    private WebElement txtLoginTitle;

    @FindBy(how = How.ID, using = "passp-field-login")
    private WebElement inputID;

    @FindBy(how = How.ID, using = "passp-field-passwd")
    private WebElement inputPassword;

    @FindBy(how = How.ID, using = "passp:sign-in")
    private WebElement btnLogIn;

    @FindBy(how = How.CSS, using = "span.user-account__name")
    private WebElement textAccountName;

    @FindBy(how = How.XPATH, using = "//a/div/img[@class=\"user-pic__image\"]")
    private WebElement iconProfile;

    @FindBy(how = How.XPATH, using = "//span[text()=\"Log out\"]")
    private WebElement txtLogOff;

    public void setAccount(String ID, String Password){
        setText(inputID, ID);
        clickElement(btnLogIn);
        clickElement(inputPassword);
        setText(inputPassword, Password);
    }

    public void clickLogIn() {
        clickElement(btnLogIn);
        assertThat(txtLoginTitle.isDisplayed()).isTrue();
    }

    public void verifyLogIn(String accountID){
        waitUntilElementDisplayed(textAccountName);
        Assert.assertEquals(getElementText(textAccountName),accountID);
    }

    public void clickLogOff(){
        clickElement(iconProfile);
        waitUntilElementClickable(txtLogOff);
        clickElement(txtLogOff);
    }

    public void verifyLogOff(){
        Assert.assertTrue(btnLogIn.isDisplayed());
    }
}
