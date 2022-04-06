package com.epam.bdd.utaf.web.steps.yandex;

import com.epam.bdd.utaf.core.GlobalVar;
import com.epam.bdd.utaf.web.cucumber.TestContext;
import com.epam.bdd.utaf.web.models.Email;
import com.epam.bdd.utaf.web.models.TabType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.epam.bdd.utaf.web.managers.PageObjectManager.*;

public class NewEmailSteps{
    private static Logger logger = LoggerFactory.getLogger(NewEmailSteps.class);
    TestContext testContext;
    public NewEmailSteps(TestContext context){
        testContext = context;
    }

    @When("I new a email with recipient,subject, body fields filled")
    public void newAEmail() {
        logger.info("I new a email with recipient,subject, body fields filled");
        Email email = new Email();
        testContext.scenarioContext.setContext("Email",email);
        newEmailPage().enterEmailContents(email.getRecipient(),email.getSubject(),email.getBodyMessage());
    }

    @And("I close the new email")
    public void closeTheNewEmail(){
        logger.info("I close the new email");
        newEmailPage().draftEmail();
    }

    @And("I should see the mail in \"Drafts\" folder")
    public void verifyDraftsMailDisplayed()  {
        logger.info("I should see the mail in Drafts folder");
        Email email = (Email)testContext.getScenarioContext().getContext("Email");
        mailListPage().verifyEmailDisplayed(email.getSubject());
    }

    @Then("I open this draft mail")
    public void openDraftMail() throws InterruptedException{
        Email email = (Email)testContext.getScenarioContext().getContext("Email");
        logger.info("I open this draft mail " + email.getSubject());
        mailListPage().openMail(email.getSubject());
        Thread.sleep(Integer.valueOf(GlobalVar.GLOBAL_VARIABLES.get("sleep")));
    }

    @And("I see the draft content is correct")
    public void verifyDraftContent(){
        Email email = (Email)testContext.getScenarioContext().getContext("Email");
        logger.info("I see the draft content is Subject:" +email.getSubject()  + " Recipient: "+email.getRecipient()+" Body Message: "+email.getBodyMessage() );
        newEmailPage().verifyDraftContent(email.getSubject(),email.getRecipient(),email.getBodyMessage());
    }

    @And("I send the email")
    public void sendEmail(){
        logger.info("Send email");
        newEmailPage().sendEmail();
    }

    @Then("I see the mail disappeared from ‘Drafts’ folder")
    public void verifyMailIsDisappearedFromDrafts() throws InterruptedException {
        Email email = (Email)testContext.getScenarioContext().getContext("Email");
        logger.info("I see the mail: " + email.getSubject() + " disappeared from 'Drafts' folder");
        mailListPage().openTab(TabType.Drafts);
        Thread.sleep(Integer.valueOf(GlobalVar.GLOBAL_VARIABLES.get("sleep")));
        mailListPage().verifyEmailDisappeared(email.getSubject());
    }

    @And("I see the mail is in ‘Sent’ folder")
    public void verifyMailIsInSentFolder() throws InterruptedException {
        Email email = (Email)testContext.getScenarioContext().getContext("Email");
        logger.info("I see the mail: " + email.getSubject() + " is in 'Sent' folder");
        mailListPage().openTab(TabType.Sent);
        Thread.sleep(Integer.valueOf(GlobalVar.GLOBAL_VARIABLES.get("sleep")));
        mailListPage().verifyEmailDisplayed(email.getSubject());
    }
}
