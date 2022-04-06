package com.epam.bdd.utaf.web.pages.yandex;

import com.epam.bdd.utaf.core.GlobalVar;
import com.epam.bdd.utaf.core.base.WebBasePage;
import com.epam.bdd.utaf.web.models.TabType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MailListPage extends WebBasePage {
    private final Logger logger = LoggerFactory.getLogger(MailListPage.class);
    private int draftsBeforeDelete;
    private int draftsAfterDelete;

    public MailListPage() {
        super("Mail List Page");
    }

    @FindBy(how = How.CLASS_NAME, using = "user-account__name")
    private WebElement txtAccountName;

    @FindBy(how = How.CSS, using = "span.mail-ComposeButton-Text")
    private WebElement btnCompose;

    @FindBy(how = How.CSS, using = "span.mail-ComposeButton-Refresh")
    private WebElement iconRefresh;

    @FindBy(how = How.XPATH, using ="//span[contains(text(),'Inbox')]")
    private WebElement tabInbox;

    @FindBy(how = How.XPATH, using ="//span[contains(text(),'Drafts')]")
    private WebElement tabDrafts;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'New message')]")
    private WebElement txtNewMessage;

    @FindBy(how = How.XPATH, using = "//div[contains(@class,\"mail-MessageSnippet-Wrap\")][1]/div[1]//span[contains(@class,\"mail-MessageSnippet-Item_subject\")]/span[@title]")
    private WebElement txtSubjectOfFirstMail;

    @FindBy(how = How.XPATH, using = "//span[contains(@title,'Check for new messages')]")
    public WebElement btnRefresh;

    @FindBy(how = How.XPATH, using = "//div[contains(text(),'Messages by month:')]")
    public WebElement txtTableFooter;

    @FindAll(@FindBy(how = How.XPATH, using = "//label[@data-nb='checkbox']"))
    private List<WebElement> checkboxList;

    @FindBy(how = How.XPATH, using = "//div[contains(@title,'Delete')]")
    private WebElement btnDelete;

    @FindBy(how = How.XPATH, using = "//a[contains(@title,'Drafts')]/div/span")
    private WebElement txtDraftsNumber;

    @FindBy(how = How.XPATH, using = "//input[@class='textinput__control']")
    public WebElement txtSearch;

    @FindBy(how=How.CSS,using = "button.search-input__form-button")
    public WebElement btnSearch;

    @FindBy(how = How.CSS, using = "span.mail-MessagesSearchInfo-Title")
    public WebElement txtSearchResult;

    public void openNewEmailPage(){
        clickElement(btnCompose);
        assertThat(txtNewMessage.isDisplayed()).isTrue();
    }

    public void openTab(TabType tabType) throws InterruptedException {
        String tabXpath = "//span[@class='mail-NestedList-Item-Name'][text()='"+tabType+"']";
        clickElement(getElement(By.xpath(tabXpath)));
        clickElement(btnRefresh);
        Thread.sleep(Integer.valueOf(GlobalVar.GLOBAL_VARIABLES.get("sleep"))*5);
    }

    public void openMail(String subject){
        String draftXpath = "//span[@title='"+ subject +"']/ancestor::div[@class=\"mail-MessageSnippet-Wrapper\"]";
        WebElement firstEmail = getElement(By.xpath(draftXpath));
        waitUntilElementClickable(firstEmail);
        clickElement(firstEmail);
    }

    public boolean isMailPresent(String subject){
        waitUntilElementDisplayed(txtSubjectOfFirstMail);
        if(getElementText(txtSubjectOfFirstMail).equals(subject))
            return true;
        else
            return false;
    }

    public void verifyEmailDisplayed(String subject){
        assertThat(isMailPresent(subject)).isTrue();
        }

    public void verifyEmailDisappeared(String subject){
        Assert.assertFalse(isMailPresent(subject));
    }

    public void selectOneEmail(){
       if (checkboxList.size() == 0){
           logger.info("There are no mails in Inbox!");
       }else {
           clickElement(checkboxList.get(0));
       }
    }

    public void deleteEmail()
    {
        waitUntilElementClickable(btnDelete);
        draftsBeforeDelete = Integer.valueOf(getElementText(txtDraftsNumber));
        clickElement(btnDelete);
        moveToElementAndClickElement(btnDelete,btnRefresh);
        draftsAfterDelete = Integer.valueOf(getElementText(txtDraftsNumber));

    }

    public void verifyEmailIsDeleted(){
        Assert.assertNotEquals(draftsAfterDelete,draftsBeforeDelete);
    }

    public void searchEmail(String subject){
        txtSearch.clear();
        txtSearch.sendKeys(subject);
        clickElement(btnSearch);
        waitUntilElementDisplayed(txtSearchResult);
    }

    public void verifySearchResult(String subject){
        String resultXpath = "//span[@title='"+ subject +"']";
        WebElement resultEmail = getElement(By.xpath(resultXpath));
        Assert.assertTrue(resultEmail.isDisplayed());
    }


}
