package com.epam.bdd.utaf.web.managers;

import com.epam.bdd.utaf.web.pages.yandex.HomePage;
import com.epam.bdd.utaf.web.pages.yandex.LoginPage;
import com.epam.bdd.utaf.web.pages.yandex.MailListPage;
import com.epam.bdd.utaf.web.pages.yandex.NewEmailPage;

public class PageObjectManager {
    private static HomePage homePage;
    private static LoginPage loginPage;
    private static NewEmailPage newEmailPage;
    private static MailListPage mailListPage;

    public static HomePage homePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }

    public static LoginPage loginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public static NewEmailPage newEmailPage() {
        if (newEmailPage == null) {
            newEmailPage = new NewEmailPage();
        }
        return newEmailPage;
    }

    public static MailListPage mailListPage() {
        if (mailListPage == null) {
            mailListPage = new MailListPage();
        }
        return mailListPage;
    }
}
