package com.epam.bdd.utaf.web.steps.yandex;

import com.epam.bdd.utaf.core.GlobalVar;
import com.epam.bdd.utaf.core.utils.EncryUtil;
import com.epam.bdd.utaf.core.utils.PropertiesReader;
import com.epam.bdd.utaf.web.cucumber.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import static com.epam.bdd.utaf.web.managers.PageObjectManager.*;

public class LogInSteps {
    private static Logger logger = LoggerFactory.getLogger(LogInSteps.class);

    TestContext testContext;

    public LogInSteps(TestContext context) {
        testContext = context;
    }

    @Given("I navigate to the yandex mail home page")
    public void openHomePage() {
        logger.info("Navigate to yandex mail home page");
        loginPage().openWebURL(PropertiesReader.getInstance().getProperty("url"));
    }

    @When("I click Log in button to open login page")
    public void openLoginPage()throws InterruptedException {
        logger.info("Click Log in button");
        homePage().clickLogIn();
        Thread.sleep(Integer.valueOf(GlobalVar.GLOBAL_VARIABLES.get("sleep")));
    }

    @And("I login yandex mail as existing user")
    public void loginAsExistingUser(List<Map<String,String>> account) throws InterruptedException {
        logger.info("Log in as existing user");
        loginPage().setAccount(account.get(0).get("ID"), EncryUtil.decodePassword(account.get(0).get("Password")));
        loginPage().clickLogIn();
        Thread.sleep(Integer.valueOf(GlobalVar.GLOBAL_VARIABLES.get("sleep")));
        loginPage().verifyLogIn(account.get(0).get("ID"));
    }

    @And("I log off Yandex")
    public void logOff(){
        logger.info("I log off Yandex");
        loginPage().clickLogOff();
        loginPage().verifyLogOff();
    }
}
