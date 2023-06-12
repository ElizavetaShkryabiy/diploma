package ru.iteco.fmhandroid.ui.tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.rules.ScreenshotRule;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.pages.AuthPage;

@RunWith(AllureAndroidJUnit4.class)
public class AuthTest {
    AuthPage authPage = new AuthPage();
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    @Rule
    private String screenshotName;
    private ScreenshotRule.Mode mode;
    ScreenshotRule logcatRule = new ScreenshotRule(mode = ScreenshotRule.Mode.END, screenshotName = "ss_end");

    @Test
    public void emptyLoginErrorTest() {
        authPage.fillAuthFormFields("", "password2", "nada");
    }

    @Test
    public void spaceLoginErrorTest() {
        authPage.fillAuthFormFields("  ", "password2", "nada");
    }

    @Test
    public void nonExistingLoginErrorTest() {
        authPage.fillAuthFormFields("sfjksdsusfjsd", "password2", "nada");
    }

    @Test
    public void wrongPasswordErrorTest() {
        authPage.fillAuthFormFields("login2", "pass", "nada");
    }

    @Test
    public void emptyPasswordErrorTest() {
        authPage.fillAuthFormFields("login2", "", "nada");
    }

    @Test
    public void logInAndOutOKTest() {
        authPage.fillAuthFormFields("login2", "password2", "Dashboard");
        authPage.logOut();
    }
}
