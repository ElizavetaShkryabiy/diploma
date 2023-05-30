package ru.iteco.fmhandroid.ui.tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.pages.AuthPage;

@RunWith(AllureAndroidJUnit4.class)
public class AuthTest {
    AuthPage authPage = new AuthPage();
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Test
    public void EmptyLoginErrorTest() {
        authPage.fillAuthFormFields("","password2");
    }

    @Test
    public void SpaceLoginErrorTest() {
        authPage.fillAuthFormFields("  ","password2");
    }

    @Test
    public void NonExistingLoginErrorTest() {
        authPage.fillAuthFormFields("sfjksdsusfjsd","password2");
    }

    @Test
    public void WrongPasswordErrorTest() {
        authPage.fillAuthFormFields("login2","pass");
    }

    @Test
    public void EmptyPasswordErrorTest() {
        authPage.fillAuthFormFields("login2","");
    }

    @Test
    public void LogInOKTest() {
        authPage.fillAuthFormFields("login2","password2");
    }
    @Test
    public void LogOutOKTest() {
        authPage.fillAuthFormFields("login2","password2");
        authPage.logOut();
    }
}
