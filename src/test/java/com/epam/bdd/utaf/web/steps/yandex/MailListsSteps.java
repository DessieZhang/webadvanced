package com.epam.bdd.utaf.web.steps.yandex;

import com.epam.bdd.utaf.web.cucumber.TestContext;
import com.epam.bdd.utaf.web.models.Email;
import com.epam.bdd.utaf.web.models.TabType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.epam.bdd.utaf.web.managers.PageObjectManager.mailListPage;

public class MailListsSteps{
    private static Logger logger = LoggerFactory.getLogger(MailListsSteps.class);
    TestContext testContext;
    public MailListsSteps(TestContext context) {
        testContext = context;
    }

    @Given("I open the new mail page")
    public void openNewEmailPage() {
        logger.info("I open the new mail page");
        mailListPage().openNewEmailPage();
    }

    @And("I open Drafts mail folder")
    public void openDraftsMailList() throws InterruptedException {
        logger.info("I open Drafts mail folder");
        mailListPage().openTab(TabType.Drafts);
    }

    @And("I select the email")
    public void selectEmail() {
        logger.info("I select one email");
        mailListPage().selectOneEmail();
    }

    @And("I click delete button")
    public void deleteEmail(){
        logger.info("I click delete button");
        mailListPage().deleteEmail();
    }

    @Then("I see the Drafts number is decreased")
    public void verifyDraftsDecreased(){
        logger.info("I see the Drafts number is decreased");
        mailListPage().verifyEmailIsDeleted();
    }

    @And("I search mail by Subject")
    public void searchEmail(){
        logger.info("I search mail by Subject");
        Email email = (Email)testContext.getScenarioContext().getContext("Email");
        mailListPage().searchEmail(email.getSubject());
    }

    @Then("I see the mail is in search result list")
    public void verifySearchResult(){
        logger.info("I see the mail is in search result list");
        Email email = (Email)testContext.getScenarioContext().getContext("Email");
        mailListPage().verifySearchResult(email.getSubject());
    }
}
