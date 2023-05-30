package ru.iteco.fmhandroid.ui.tests;


import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.MethodsClass;
import ru.iteco.fmhandroid.ui.pages.AboutPage;
import ru.iteco.fmhandroid.ui.pages.AuthPage;
import ru.iteco.fmhandroid.ui.pages.ClaimsPage;
import ru.iteco.fmhandroid.ui.pages.MainPage;
import ru.iteco.fmhandroid.ui.pages.NewsPage;
import ru.iteco.fmhandroid.ui.pages.QuotesPage;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AppTests {
    MethodsClass methods = new MethodsClass();
    MainPage mainPage = new MainPage();
    NewsPage newsPage = new NewsPage();
    ClaimsPage claimsPage = new ClaimsPage();
    AboutPage aboutPage = new AboutPage();
    QuotesPage quotesPage = new QuotesPage();
    AuthPage authPage = new AuthPage();

    static String statusOpen = "Open";
    static String statusInProcess = "In progress";
    static String statusCancelled = "Canceled";
    static String statusExecuted = "Executed";

    static String statusToOpen = "Throw off";
    static String statusToInProcess = "take to work";
    static String statusToCancelled = "Cancel";
    static String statusTo = "To execute";

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void login() {
        authPage.fillAuthFormFields("login2", "password2");
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
    public void everythingDisplayedOnNewsPage() {
        methods.chooseMenuItem("News");
        newsPage.getNewsPagesObjects();
    }

    @Test
    public void checkSortWorkOnNewsPageTest() {
        methods.chooseMenuItem("News");
        newsPage.sort();
    }

    @Test
    public void filterWorksNewsPageTest() {
        methods.chooseMenuItem("News");
        newsPage.filterButtonClick();
        newsPage.filterNews("День рождения");
    }

    @Test
    public void controlPanelOpensWithAllElements() {
        methods.chooseMenuItem("News");
        newsPage.controlPanelElementsDisplayed();
    }

    @Test
    public void createNewsItem() {
        methods.chooseMenuItem("News");
        newsPage.controlPanelElementsDisplayed();
        newsPage.createNewNewsItem(methods.getTestNumber());

    }

    @Test
    public void editNewsItem() {
        methods.chooseMenuItem("News");
        newsPage.controlPanelElementsDisplayed();
        int testN = methods.getTestNumber();
        newsPage.createNewNewsItem(testN);
        int testN2 = methods.getTestNumber();
        newsPage.editNewsItems(testN2, true, true, true, false);
        int testN3 = methods.getTestNumber();
        newsPage.editNewsItems(testN3, true, false, true, true);
    }

    @Test
    public void claimsPageItemsDisplayedTest() {
        methods.chooseMenuItem("Claims");
        claimsPage.getClaimsPagesObjects();
    }

    @Test
    public void checkCreateNewClaim() {
        methods.chooseMenuItem("Claims");
        claimsPage.createNewClaim(methods.getTestNumber(), false);
    }

    @Test
    public void checkEditNewClaim() {
        methods.chooseMenuItem("Claims");
        int firstTest = methods.getTestNumber();
        claimsPage.createNewClaim(firstTest, false);
        int secTest = methods.getTestNumber();
        claimsPage.editClaim(firstTest, secTest, false, false);
    }

    @Test
    public void changeClaimStatusFO2P() {
        methods.chooseMenuItem("Claims");
        int firstTest = methods.getTestNumber();
        claimsPage.createNewClaim(firstTest, false);
        claimsPage.changeStatus(statusOpen, statusInProcess, statusToInProcess, statusCancelled);
    }

    @Test
    public void changeClaimStatusFO2C() {
        methods.chooseMenuItem("Claims");
        int firstTest = methods.getTestNumber();
        claimsPage.createNewClaim(firstTest, false);
        claimsPage.changeStatus(statusOpen, statusCancelled, statusToCancelled, statusToInProcess);
    }

    @Test
    public void changeClaimStatusFP2O() {
        methods.chooseMenuItem("Claims");
        int firstTest = methods.getTestNumber();
        claimsPage.createNewClaim(firstTest, true);
        claimsPage.changeStatus(statusInProcess, statusOpen, statusToOpen, statusTo);
    }

    @Test
    public void changeClaimStatusFP2E() {
        methods.chooseMenuItem("Claims");
        int firstTest = methods.getTestNumber();
        claimsPage.createNewClaim(firstTest, true);
        claimsPage.changeStatus(statusInProcess, statusExecuted, statusTo, statusToOpen);
    }

    @Test
    public void addComment() {
        methods.chooseMenuItem("Claims");
        int firstTest = methods.getTestNumber();
        claimsPage.createNewClaim(firstTest, true);
        claimsPage.addComment("comment text");
    }

    @Test
    public void checkErrorOnEmptyFieldsWhileCreatingClaim() {
        methods.chooseMenuItem("Claims");
        claimsPage.getErrorOnEmptyFields();
    }

    @Test
    public void checkExcessiveFillOfTitleFieldWhileCreatingClaim() {
        methods.chooseMenuItem("Claims");
        claimsPage.createNewClaim(00000000000000000000000000123, true);
    }

    @Test
    public void checkFilterWorksInClaims() {
        methods.chooseMenuItem("Claims");
        claimsPage.chooseFilterCriteriaInClaims(statusOpen);
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
