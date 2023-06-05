package ru.iteco.fmhandroid.ui.tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.MethodsClass;
import ru.iteco.fmhandroid.ui.pages.AuthPage;
import ru.iteco.fmhandroid.ui.pages.NewsPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class NewsPageTests {

    MethodsClass methods = new MethodsClass();
    NewsPage newsPage = new NewsPage();
    AuthPage authPage = new AuthPage();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

//    @Before
//    public void login() {
//        authPage.fillAuthFormFields("login2", "password2", "Dashboard");
//    }
//    @After
//    public void logOut() {
//        authPage.logOut();
//    }


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

}
