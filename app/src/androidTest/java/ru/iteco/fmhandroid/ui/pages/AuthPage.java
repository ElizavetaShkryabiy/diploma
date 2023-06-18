package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.view.View;

import androidx.test.espresso.PerformException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.util.HumanReadables;
import androidx.test.espresso.util.TreeIterables;

import org.hamcrest.Matcher;

import java.util.concurrent.TimeoutException;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class AuthPage {
    public static ViewAction waitId(final int viewId, final long millis) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "wait for a specific view with id <" + viewId + "> during " + millis + " millis.";
            }

            @Override
            public void perform(final UiController uiController, final View view) {
                uiController.loopMainThreadUntilIdle();
                final long startTime = System.currentTimeMillis();
                final long endTime = startTime + millis;
                final Matcher<View> viewMatcher = withId(viewId);

                do {
                    for (View child : TreeIterables.breadthFirstViewTraversal(view)) {
                        // found view with required ID
                        if (viewMatcher.matches(child)) {
                            return;
                        }
                    }

                    uiController.loopMainThreadForAtLeast(50);
                }
                while (System.currentTimeMillis() < endTime);

                // timeout happens
                throw new PerformException.Builder()
                        .withActionDescription(this.getDescription())
                        .withViewDescription(HumanReadables.describe(view))
                        .withCause(new TimeoutException())
                        .build();
            }
        };
    }

    public static void fillAuthFormFields(String login, String password, String result) {
        Allure.step("заполнить поля");
        onView(isRoot()).perform(waitId(R.id.login_text_input_edit, 15000));
        onView(isRoot()).perform(waitId(R.id.password_text_input_edit, 15000));
        onView(isRoot()).perform(waitId(R.id.enter_button, 5000));
        onView(allOf(withId(R.id.login_text_input_edit))).perform(replaceText(login));
        onView(allOf(withId(R.id.password_text_input_edit))).perform(click())
                .perform(typeText(password), closeSoftKeyboard());
        onView(allOf(withId(R.id.enter_button))).perform(click());
        checkDisplayed(result);
    }

    public static void checkDisplayed(String view) {
        Allure.step("все видно");
        if (view == "Dashboard") {
            onView(isRoot()).perform(waitId(R.id.trademark_image_view, 15000));
            onView(allOf(withId(R.id.trademark_image_view))).check(matches(isDisplayed()));
        } else {
            onView(allOf(withText("Authorization"))).check(matches(withText("Authorization")));
        }
    }

    public static void logOut() {
        Allure.step("логаут");
        onView(isRoot()).perform(waitId(R.id.authorization_image_button, 5000));
        onView(allOf(withId(R.id.authorization_image_button))).perform(click());
        onView(isRoot()).perform(waitId(R.id.authorization_logout_menu_item, 1000));
        onData(allOf(withId(R.id.authorization_logout_menu_item)))
                .perform(longClick());
        onView(isRoot()).perform(waitId(R.id.auth_page_header, 3000));
        onView(allOf(withId(R.id.auth_page_header))).check(matches(isDisplayed()));
    }
}
