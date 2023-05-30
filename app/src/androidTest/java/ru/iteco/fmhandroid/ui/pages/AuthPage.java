package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import ru.iteco.fmhandroid.R;

public class AuthPage {

    public static void fillAuthFormFields(String login, String password) {
        onView(allOf(withId(R.id.login_text_input_layout))).perform(replaceText(login));
        onView(allOf(withId(R.id.password_text_input_layout))).perform(click())
                .perform(typeText(password), closeSoftKeyboard());
        onView(allOf(withId(R.id.enter_button))).perform(click());

        onView(allOf(withId(R.id.trademark_image_view))).check(matches(isDisplayed()));
    }

    public static void logOut() {
        onView(allOf(withId(R.id.authorization_image_button))).perform(click());
        onView(allOf(withId(android.R.id.title), withText("Log out"))).perform(click());
        onView(allOf(withText("Authorization"))).check(matches(withText("Authorization")));
    }
}
