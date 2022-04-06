package com.epam.bdd.utaf.web.pages.yandex;

import com.epam.bdd.utaf.core.base.WebBasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class NewEmailPage extends WebBasePage {
    private final Logger logger = LoggerFactory.getLogger(NewEmailPage.class);
    public NewEmailPage() {
        super("Mail List Page");
    }

    @FindBy(how = How.XPATH, using = "//div[contains(@class,'tst-field-to')]/div/div/div/div")
    public WebElement txtTo;

    @FindBy(how = How.NAME, using = "subject")
    public WebElement txtSubject;

    @FindBy(how = How.XPATH, using = "//div[contains(@class,'cke_wysiwyg_div')]")
    public WebElement txtMessageBody;

    @FindBy(how = How.XPATH, using = "//div[@class='ComposePopup-Head']//div[contains(@class,'button_close')]/button")
    public WebElement btnClose;

    @FindBy(how = How.XPATH, using = "//button[@aria-disabled='false']")
    public WebElement btnSend;

    @FindBy(how = How.LINK_TEXT, using = "Back to \"Inbox\"")
    public WebElement lnkBackToInbox;

    @FindBy(how = How.XPATH, using = "//span[text()='Back to email']/../..")
    public WebElement btnBackToEmail;

    public void enterEmailContents(String recipient, String subject, String message) {
        txtTo.sendKeys(recipient);
        txtSubject.sendKeys(subject);
        txtMessageBody.sendKeys(message);
    }

    public void draftEmail() {
        btnClose.click();
    }

    public void verifyDraftContent(String subject, String recipient,String message){
        Assert.assertEquals(getElementAttribute(txtSubject,"value"),subject);
        Assert.assertTrue(getElementText(txtTo).contains(recipient));
        Assert.assertEquals(getElementText(txtMessageBody),message);
    }

    public void sendEmail() {
        btnSend.click();
    }
}
