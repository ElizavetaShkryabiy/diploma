package netology.espresso.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
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
import ru.iteco.fmhandroid.ui.AppActivity;

@RunWith(AllureAndroidJUnit4.class)
public class AuthTest {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Test
    public void EmptyLoginErrorTest() {
        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.password_text_input_layout)));
        textInputEditText.perform(click());
        textInputEditText.perform(typeText("password2"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.enter_button)));
        materialButton.perform(click());
        onView(allOf(withId(R.id.trademark_image_view))).check(matches(not(isDisplayed())));
//        onView(withText(R.string.toast_empty_field)).check(matches(isDisplayed()));
    }

    @Test
    public void SpaceLoginErrorTest() {
        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.login_text_input_layout)));
        textInputEditText2.perform(replaceText(" "));
        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.password_text_input_layout)));
        textInputEditText.perform(click());
        textInputEditText.perform(typeText("password2"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.enter_button)));
        materialButton.perform(click());
        onView(allOf(withId(R.id.trademark_image_view))).check(matches(not(isDisplayed())));
//        onView(withText(R.string.toast_empty_field)).check(matches(isDisplayed()));
    }

    @Test
    public void NonExistingLoginErrorTest() {
        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.login_text_input_layout)));
        textInputEditText2.perform(replaceText("sfjksdsusfjsd"));
        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.password_text_input_layout)));
        textInputEditText.perform(click());
        textInputEditText.perform(typeText("password2"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.enter_button)));
        materialButton.perform(click());
        onView(allOf(withId(R.id.trademark_image_view))).check(matches(not(isDisplayed())));
//        onView(withText(R.string.toast_empty_field)).check(matches(isDisplayed()));
    }

    @Test
    public void WrongPasswordErrorTest() {
        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.login_text_input_layout)));
        textInputEditText2.perform(replaceText("login2"));
        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.password_text_input_layout)));
        textInputEditText.perform(click());
        textInputEditText.perform(typeText("pass"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.enter_button)));
        materialButton.perform(click());

//        onView(withText(R.string.toast_empty_field)).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.trademark_image_view))).check(matches(not(isDisplayed())));
    }

    @Test
    public void EmptyPasswordErrorTest() {
        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.login_text_input_layout)));
        textInputEditText2.perform(replaceText("login2"));
        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.password_text_input_layout)));
        textInputEditText.perform(click());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.enter_button)));
        materialButton.perform(click());

//        onView(withText(R.string.toast_empty_field)).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.trademark_image_view))).check(matches(not(isDisplayed())));
    }

    @Test
    public void LogInOKTest() {
        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.login_text_input_layout)));
        textInputEditText2.perform(replaceText("login2"));
        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.password_text_input_layout)));
        textInputEditText.perform(click());
        textInputEditText.perform(typeText("password2"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.enter_button)));
        materialButton.perform(click());

        onView(allOf(withId(R.id.trademark_image_view))).check(matches(isDisplayed()));
    }
    @Test
    public void LogOutOKTest() {
        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.login_text_input_layout)));
        textInputEditText2.perform(replaceText("login2"));
        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.password_text_input_layout)));
        textInputEditText.perform(click());
        textInputEditText.perform(typeText("password2"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.enter_button)));
        materialButton.perform(click());

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
