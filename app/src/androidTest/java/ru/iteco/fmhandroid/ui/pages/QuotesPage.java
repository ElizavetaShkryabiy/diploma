package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

import android.view.View;

import androidx.test.espresso.PerformException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.util.HumanReadables;
import androidx.test.espresso.util.TreeIterables;

import org.hamcrest.Matcher;

import java.util.concurrent.TimeoutException;

import ru.iteco.fmhandroid.R;

public class QuotesPage {
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

    public static void goToQuotesPage(){
        onView(isRoot()).perform(waitId(R.id.trademark_image_view, 5000));
        onView(allOf(withId(R.id.trademark_image_view))).check(matches(isDisplayed()));
        onView(isRoot()).perform(waitId(R.id.our_mission_image_button, 5000));
        onView(allOf(withId(R.id.our_mission_image_button))).check(matches(isDisplayed()));
        onView(isRoot()).perform(waitId(R.id.authorization_image_button, 5000));
        onView(allOf(withId(R.id.authorization_image_button))).check(matches(isDisplayed()));

        onView(isRoot()).perform(waitId(R.id.our_mission_image_button, 5000));
        onView(allOf(withId(R.id.our_mission_image_button))).perform(click());

        onView(isRoot()).perform(waitId(R.id.our_mission_item_list_recycler_view, 5000));
        onView(allOf(withId(R.id.our_mission_item_list_recycler_view))).perform(actionOnItemAtPosition(0, click()));

        onView(isRoot()).perform(waitId(R.id.our_mission_title_text_view, 5000));
        onView(allOf(withId(R.id.our_mission_title_text_view))).check(matches(isDisplayed()));
        onView(isRoot()).perform(waitId(R.id.our_mission_item_title_text_view, 5000));
        onView(allOf(withId(R.id.our_mission_item_title_text_view))).check(matches(isDisplayed()));
        onView(isRoot()).perform(waitId(R.id.our_mission_item_description_text_view, 5000));
        onView(allOf(withId(R.id.our_mission_item_description_text_view))).check(matches(isDisplayed()));
    }
}
