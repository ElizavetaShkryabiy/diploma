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
import ru.iteco.fmhandroid.ui.pages.AuthPage;
import ru.iteco.fmhandroid.ui.pages.ClaimsPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class ClaimsPageTests {

    MethodsClass methods = new MethodsClass();
    ClaimsPage claimsPage = new ClaimsPage();
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
    @Rule
    ScreenshotRule logcatRule = new ScreenshotRule(ScreenshotRule.Mode.END, screenshotName = "ss_end");

    @Before
    public void login() {
        authPage.fillAuthFormFields("login2", "password2", "Dashboard");
    }
    @After
    public void logOut() {
        authPage.logOut();
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

}
