package ru.iteco.fmhandroid.ui.tests;


import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.rules.ScreenshotRule;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.MethodsClass;
import ru.iteco.fmhandroid.ui.pages.AboutPage;
import ru.iteco.fmhandroid.ui.pages.AuthPage;
import ru.iteco.fmhandroid.ui.pages.ClaimsPage;
import ru.iteco.fmhandroid.ui.pages.MainPage;
import ru.iteco.fmhandroid.ui.pages.QuotesPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class MainQuotesAndAboutPagesTests {

    MethodsClass methods = new MethodsClass();
    MainPage mainPage = new MainPage();
    ClaimsPage claimsPage = new ClaimsPage();
    AboutPage aboutPage = new AboutPage();
    QuotesPage quotesPage = new QuotesPage();
    AuthPage authPage = new AuthPage();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    @Rule
    ScreenshotRule logcatRule = new ScreenshotRule(mode = ScreenshotRule.Mode.END, screenshotName = "ss_end");

    @Before
    public void login() {
        authPage.fillAuthFormFields("login2", "password2", "Dashboard");
    }
    @After
    public void logOut() {
        authPage.logOut();
    }


    @Test
    public void checkEverythingExistsInMainPage() {
        mainPage.getMainPagesObjects();
    }

    @Test
    public void checkRedirectFromMainToAllClaimsPage() {
        mainPage.allClaimsRedirect();
    }

    @Test
    public void checkRedirectFromMainToCreateClaimPage() {
        claimsPage.createNewClaim(1, false);
    }

    @Test
    public void checkRedirectFromMainToAllNewsPage() {
        mainPage.allNewsRedirect();
    }

    @Test
    public void aboutPageTest() {
        methods.chooseMenuItem("About");
        aboutPage.getAboutPagesObjects();
    }

    @Test
    public void quotesPageTest() {
        methods.chooseMenuItem("About");
        methods.chooseMenuItem("Main");
        quotesPage.goToQuotesPage();
    }

}
