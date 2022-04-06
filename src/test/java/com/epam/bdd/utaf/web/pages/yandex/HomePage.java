package com.epam.bdd.utaf.web.pages.yandex;

import com.epam.bdd.utaf.core.base.WebBasePage;
import com.epam.bdd.utaf.web.cucumber.TestContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class HomePage extends WebBasePage {
    private final Logger logger = LoggerFactory.getLogger(HomePage.class);

    public HomePage() {
        super("Home Page");
    }

    @FindBy(how = How.CSS, using = "a.with-shadow:nth-child(1)")
    private WebElement btnCreateAccount;

    @FindBy(how = How.CSS, using = "a.with-shadow:nth-child(2)")
    private WebElement btnLogIn;

    @FindBy(how = How.XPATH, using = "//h1[contains(text(),'Registration')]")
    private WebElement txtRegistration;

    @FindBy(how = How.CSS, using = "span.WelcomePage-tagline")
    private WebElement txtLoginTitle;

    public void clickCreateAccount() {
        clickElement(btnCreateAccount);
        assertThat(txtRegistration.isDisplayed()).isTrue();
    }

    public void clickLogIn() {
        clickElement(btnLogIn);
    }
}
