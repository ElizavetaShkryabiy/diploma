package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
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

import java.util.concurrent.TimeoutException;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class MainPage {
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

    public static void getMainPagesObjects(){
        Allure.step("проверить элементы");
        onView(isRoot()).perform(waitId(R.id.main_menu_image_button, 5000));
        onView(allOf(withId(R.id.news_list_recycler_view))).check(matches(isDisplayed()))
                .perform(actionOnItemAtPosition(0, click()));
        onView(allOf(withId(R.id.all_news_text_view))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.news_item_description_text_view))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.container_list_news_include_on_fragment_main))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.expand_material_button))).check(matches(isDisplayed()));

        onView(allOf(withId(R.id.container_list_claim_include_on_fragment_main))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.expand_material_button))).check(matches(isDisplayed()));

        onView(allOf(withId(R.id.all_claims_text_view))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.claim_list_card))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.claim_bottom_divider_image_view))).check(matches(isDisplayed()));
    }

    public static void allNewsRedirect(){
        Allure.step("редирект на новости");
        onView(allOf(withId(R.id.all_news_text_view))).check(matches(isDisplayed())).perform(click());
        onView(allOf(withText("News"),
                withParent(withParent(withId(R.id.container_list_news_include))),
                isDisplayed())).check(matches(withText("News")));
    }

    public static void allClaimsRedirect(){
        Allure.step("редирект на жалобы");
        onView(allOf(withId(R.id.all_claims_text_view))).check(matches(isDisplayed())).perform(click());
        onView(allOf(withText("Claims"),
                withParent(withParent(withId(R.id.container_list_claim_include))),
                isDisplayed())).check(matches(withText("Claims")));
    }
}
