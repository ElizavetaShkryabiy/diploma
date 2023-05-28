package ru.iteco.fmhandroid.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import ru.iteco.fmhandroid.R;

@RunWith(AllureAndroidJUnit4.class)
public class AuthTest {
    MethodsClass methods = new MethodsClass();
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Test
    public void EmptyLoginErrorTest() {
        methods.fillAuthFormFields("","password2");
        onView(allOf(withId(R.id.trademark_image_view))).check(matches(not(isDisplayed())));
    }

    @Test
    public void SpaceLoginErrorTest() {
        methods.fillAuthFormFields("  ","password2");
        onView(allOf(withId(R.id.trademark_image_view))).check(matches(not(isDisplayed())));
    }

    @Test
    public void NonExistingLoginErrorTest() {
        methods.fillAuthFormFields("sfjksdsusfjsd","password2");
        onView(allOf(withId(R.id.trademark_image_view))).check(matches(not(isDisplayed())));
    }

    @Test
    public void WrongPasswordErrorTest() {
        methods.fillAuthFormFields("login2","pass");
        onView(allOf(withId(R.id.trademark_image_view))).check(matches(not(isDisplayed())));
    }

    @Test
    public void EmptyPasswordErrorTest() {
        methods.fillAuthFormFields("login2","");
        onView(allOf(withId(R.id.trademark_image_view))).check(matches(not(isDisplayed())));
    }

    @Test
    public void LogInOKTest() {
        methods.fillAuthFormFields("login2","password2");
        onView(allOf(withId(R.id.trademark_image_view))).check(matches(isDisplayed()));
    }
    @Test
    public void LogOutOKTest() {
        methods.fillAuthFormFields("login2","password2");
        onView(allOf(withId(R.id.trademark_image_view))).check(matches(isDisplayed()));
        ViewInteraction appCompatImageButton3 = onView(
                allOf(withId(R.id.authorization_image_button), withContentDescription("Authorization")));
        appCompatImageButton3.perform(click());

        ViewInteraction materialTextView5 = onView(
                allOf(withId(android.R.id.title), withText("Log out")));
        materialTextView5.perform(click());

        onView(allOf(withText("Authorization"))).check(matches(withText("Authorization")));

    }
}
