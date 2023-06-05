package ru.iteco.fmhandroid.ui.data;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.view.View;

import androidx.test.espresso.PerformException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.util.HumanReadables;
import androidx.test.espresso.util.TreeIterables;

import org.hamcrest.Matcher;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeoutException;

import ru.iteco.fmhandroid.R;

public class MethodsClass {
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
    static int number = 0;

    public static String getToday() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date date = new Date();
        String today = formatter.format(date).toString();
        return (today);
    }

    public static int getTestNumber() {
        number = number + 1;
        return number;
    }

    public static void chooseMenuItem(String item) {
        onView(isRoot()).perform(waitId(R.id.main_menu_image_button, 5000));
        onView(allOf(withId(R.id.main_menu_image_button)))
                .check(matches(isDisplayed()))
                .perform(click());

        onView(allOf(withId(android.R.id.title), withText(item)))
                .check(matches(isDisplayed()))
                .check(matches(withText(item)))
                .perform(click());
        onView(allOf(withText(item),
                withParent(withParent(withId(R.id.container_list_news_include)))))
                .check(matches(isDisplayed()))
                .check(matches(withText(item)));

    }

}
