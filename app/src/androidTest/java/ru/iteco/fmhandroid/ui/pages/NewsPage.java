package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.PerformException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.util.HumanReadables;
import androidx.test.espresso.util.TreeIterables;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.concurrent.TimeoutException;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.MethodsClass;

public class NewsPage {
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
    static MethodsClass methods = new MethodsClass();
    static String today = methods.getToday();

    public static void getNewsPagesObjects(){
        onView(isRoot()).perform(waitId(R.id.sort_news_material_button, 5000));
        onView(allOf(withId(R.id.sort_news_material_button))).check(matches(isDisplayed()));
        onView(isRoot()).perform(waitId(R.id.filter_news_material_button, 5000));
        onView(allOf(withId(R.id.filter_news_material_button))).check(matches(isDisplayed()));
        onView(isRoot()).perform(waitId(R.id.edit_news_material_button, 5000));
        onView(allOf(withId(R.id.edit_news_material_button))).check(matches(isDisplayed()));
        onView(isRoot()).perform(waitId(R.id.news_item_material_card_view, 5000));
        onView(allOf(withParent(allOf(withId(R.id.news_item_material_card_view),
                        withParent(withId(R.id.news_list_recycler_view)))),
                isDisplayed())).check(matches(isDisplayed()));
        onView(isRoot()).perform(waitId(R.id.news_list_recycler_view, 5000));
        onView(allOf(withId(R.id.news_list_recycler_view))).perform(actionOnItemAtPosition(1, click()));
        onView(isRoot()).perform(waitId(R.id.news_item_description_text_view, 5000));
        onView(allOf(withId(R.id.news_item_description_text_view))).check(matches(isDisplayed()));
    }

    public static void sort(){
        onView(isRoot()).perform(waitId(R.id.sort_news_material_button, 5000));
        onView(allOf(withId(R.id.sort_news_material_button), withContentDescription("Sort news list button")))
                .check(matches(isDisplayed()))
                .perform(click())
                .perform(scrollToPosition(0));
        onView(allOf(withId(R.id.news_item_date_text_view))).check(matches(not(withText(today))));
    }

    public static void filterButtonClick(){
        onView(allOf(withText("News"),
                withParent(withParent(withId(R.id.container_list_news_include))),
                isDisplayed())).check(matches(withText("News")));
    }

    public static void openNewsWindow() {
        onView(isRoot()).perform(waitId(R.id.filter_news_material_button, 5000));
        onView(allOf(withId(R.id.filter_news_material_button))).check(matches(isDisplayed())).perform(click());
        onView(isRoot()).perform(waitId(R.id.filter_news_title_text_view, 5000));
        onView(allOf(withId(R.id.filter_news_title_text_view))).check(matches(withText("Filter news")));
        onView(allOf(withId(R.id.news_item_category_text_auto_complete_text_view))).check(matches(isDisplayed()));

        onView(allOf(withId(R.id.filter_button))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.cancel_button))).check(matches(isDisplayed())).perform(click());
        onView(allOf(withText("News"),
                withParent(withParent(withId(R.id.container_list_news_include))),
                isDisplayed())).check(matches(withText("News")));
    }

    public static void controlPanelElementsDisplayed() {
        onView(isRoot()).perform(waitId(R.id.edit_news_material_button, 5000));
        onView(allOf(withId(R.id.edit_news_material_button))).perform(click());
        onView(allOf(withText("Control panel"))).check(matches(isDisplayed()));
        onView(isRoot()).perform(waitId(R.id.sort_news_material_button, 5000));
        onView(allOf(withId(R.id.sort_news_material_button))).check(matches(isDisplayed()));
        onView(isRoot()).perform(waitId(R.id.filter_news_material_button, 5000));
        onView(allOf(withId(R.id.filter_news_material_button))).check(matches(isDisplayed()));
        onView(isRoot()).perform(waitId(R.id.add_news_image_view, 5000));
        onView(allOf(withId(R.id.add_news_image_view))).check(matches(isDisplayed()));
        onView(isRoot()).perform(waitId(R.id.news_item_material_card_view, 5000));
        onView(allOf(withId(R.id.news_item_material_card_view))).check(matches(isDisplayed()));
        onView(isRoot()).perform(waitId(R.id.category_icon_image_view, 5000));
        onView(allOf(withId(R.id.category_icon_image_view))).check(matches(isDisplayed()));
        onView(isRoot()).perform(waitId(R.id.news_item_title_text_view, 5000));
        onView(allOf(withId(R.id.news_item_title_text_view))).check(matches(isDisplayed()));
        onView(isRoot()).perform(waitId(R.id.news_item_publication_text_view, 5000));
        onView(allOf(withId(R.id.news_item_publication_text_view))).check(matches(isDisplayed()));
        onView(isRoot()).perform(waitId(R.id.news_item_publication_date_text_view, 5000));
        onView(allOf(withId(R.id.news_item_publication_date_text_view))).check(matches(isDisplayed()));
        onView(isRoot()).perform(waitId(R.id.news_item_creation_text_view, 5000));
        onView(allOf(withId(R.id.news_item_creation_text_view))).check(matches(isDisplayed()));
        onView(isRoot()).perform(waitId(R.id.news_item_create_date_text_view, 5000));
        onView(allOf(withId(R.id.news_item_create_date_text_view))).check(matches(isDisplayed()));
        onView(isRoot()).perform(waitId(R.id.news_item_author_text_view, 5000));
        onView(allOf(withId(R.id.news_item_author_text_view))).check(matches(isDisplayed()));
        onView(isRoot()).perform(waitId(R.id.news_item_author_name_text_view, 5000));
        onView(allOf(withId(R.id.news_item_author_name_text_view))).check(matches(isDisplayed()));
        onView(isRoot()).perform(waitId(R.id.news_item_published_icon_image_view, 5000));
        onView(allOf(withId(R.id.news_item_published_icon_image_view))).check(matches(isDisplayed()));
        onView(isRoot()).perform(waitId(R.id.delete_news_item_image_view, 5000));
        onView(allOf(withId(R.id.delete_news_item_image_view))).check(matches(isDisplayed()));
        onView(isRoot()).perform(waitId(R.id.edit_news_item_image_view, 5000));
        onView(allOf(withId(R.id.edit_news_item_image_view))).check(matches(isDisplayed()));
        onView(isRoot()).perform(waitId(R.id.view_news_item_image_view, 5000));
        onView(allOf(withId(R.id.view_news_item_image_view))).check(matches(isDisplayed()));
        onView(isRoot()).perform(waitId(R.id.news_list_recycler_view, 5000));
        onView(allOf(withId(R.id.news_list_recycler_view))).perform(actionOnItemAtPosition(0, click()));
        onView(isRoot()).perform(waitId(R.id.news_item_description_text_view, 5000));
        onView(allOf(withId(R.id.news_item_description_text_view))).check(matches(isDisplayed()));
        onView(isRoot()).perform(waitId(R.id.delete_news_item_image_view, 5000));
        onView(allOf(withId(R.id.delete_news_item_image_view))).perform(click());
    }

    public static void filterNews(String category) {
        openNewsWindow();
        onView(isRoot()).perform(waitId(R.id.news_item_category_text_auto_complete_text_view, 5000));
        onView(allOf(withId(R.id.news_item_category_text_auto_complete_text_view))).perform(click());
        onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(5).perform(click());
        onView(isRoot()).perform(waitId(R.id.news_item_category_text_auto_complete_text_view, 5000));
        onView(allOf(withId(R.id.news_item_category_text_auto_complete_text_view), withText(category)))
                .check(matches(withText(category)));
        onView(isRoot()).perform(waitId(R.id.news_item_publish_date_start_text_input_edit_text, 5000));
        onView(allOf(withId(R.id.news_item_publish_date_start_text_input_edit_text))).perform(click());

        ViewInteraction okButton = onView(
                allOf(withId(android.R.id.button1))).perform(scrollTo(), click());
        onView(isRoot()).perform(waitId(R.id.news_item_publish_date_end_text_input_edit_text, 5000));
        onView(allOf(withId(R.id.news_item_publish_date_end_text_input_edit_text))).perform(click());

        okButton.perform(scrollTo(), click());
        onView(isRoot()).perform(waitId(R.id.filter_button, 5000));
        onView(allOf(withId(R.id.filter_button))).perform(click());

        onView(allOf(withId(R.id.filter_button))).check(matches(isDisplayed())).perform(click());
        onView(allOf(withId(R.id.news_list_recycler_view))).perform(actionOnItemAtPosition(3, click()));
        onView(allOf(withId(R.id.news_item_title_text_view))).check(matches(isDisplayed()));
    }

    public static void newsItemCheck(int testNumberT, String date, int testNumberD, boolean active) {
        String state;
        onView(isRoot()).perform(waitId(R.id.news_item_title_text_view, 5000));
        onView(allOf(withId(R.id.news_item_title_text_view)))
                .check(matches(isDisplayed()))
                .check(matches(withText("Birthday test n " + testNumberT)));

        onView(allOf(withId(R.id.news_item_publication_date_text_view)))
                .check(matches(isDisplayed()))
                .check(matches(withText(date)));
        if (active == true) {
            state = "ACTIVE";
        } else {
            state = "NOT ACTIVE";
        }

        onView(allOf(withId(R.id.news_item_published_text_view)))
                .check(matches(isDisplayed()))
                .check(matches(withText(state)));

        onView(allOf(withId(R.id.news_item_description_text_view)))
                .check(matches(isDisplayed()))
                .check(matches(withText("Description for birthday test next " + testNumberD)));
    }

    public static void createNewNewsItem(Integer testNumber) {
        onView(isRoot()).perform(waitId(R.id.add_news_image_view, 5000));
        onView(allOf(withId(R.id.add_news_image_view))).perform(click());
        onView(isRoot()).perform(waitId(R.id.custom_app_bar_title_text_view, 5000));
        onView(allOf(withId(R.id.custom_app_bar_title_text_view)))
                .check(matches(isDisplayed()))
                .check(matches(withText("Creating")));
        onView(allOf(withId(R.id.custom_app_bar_sub_title_text_view)))
                .check(matches(isDisplayed()))
                .check(matches(withText("News")));
        ViewInteraction category = onView(allOf(withId(R.id.news_item_category_text_auto_complete_text_view)))
                .check(matches(isDisplayed()))
                .check(matches(withText("Category")));
        ViewInteraction title = onView(allOf(withId(R.id.news_item_title_text_input_edit_text)))
                .check(matches(isDisplayed()))
                .check(matches(withText("Title")));
        ViewInteraction date = onView(allOf(withId(R.id.news_item_publish_date_text_input_edit_text)))
                .check(matches(isDisplayed()))
                .check(matches(withText("Publication date")));
        ViewInteraction time = onView(allOf(withId(R.id.news_item_publish_time_text_input_edit_text)))
                .check(matches(isDisplayed()))
                .check(matches(withText("Time")));
        ViewInteraction descriptionField = onView(allOf(withId(R.id.news_item_description_text_input_edit_text)))
                .check(matches(isDisplayed()))
                .check(matches(withText("Description")));
        ViewInteraction saveB = onView(allOf(withId(R.id.save_button)))
                .check(matches(isDisplayed()));
        onView(allOf(withId(R.id.switcher)))
                .check(matches(isDisplayed()));
        onView(allOf(withId(R.id.cancel_button)))
                .check(matches(isDisplayed()));

        category.perform(click());
        onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(1).perform(click());

        title.perform(replaceText("Birthday test n " + testNumber)).perform(closeSoftKeyboard());
        date.perform(click());

        ViewInteraction okB = onView(allOf(withId(android.R.id.button1)));
        okB.perform(scrollTo(), click());

        time.perform(click());
        okB.perform(scrollTo(), click());

        descriptionField.perform(replaceText("Description for birthday test n " + testNumber), closeSoftKeyboard());
        saveB.perform(scrollTo(), click());

        newsItemCheck(testNumber, today, testNumber, true);

    }

    public static void editTitle(int testNumber) {
        onView(isRoot()).perform(waitId(R.id.news_item_title_text_input_edit_text, 5000));
        onView(allOf(withId(R.id.news_item_title_text_input_edit_text)))
                .check(matches(isDisplayed()))
                .perform(replaceText("Birthday test n " + testNumber))
                .perform(closeSoftKeyboard());
    }

    public static void editDescription(int testNumber) {
        onView(isRoot()).perform(waitId(R.id.news_item_description_text_input_edit_text, 5000));
        onView(allOf(withId(R.id.news_item_description_text_input_edit_text)))
                .check(matches(isDisplayed()))
                .perform(replaceText("Description for birthday test next " + testNumber))
                .perform(closeSoftKeyboard());
    }

    public static void editState() {
        onView(isRoot()).perform(waitId(R.id.switcher, 5000));
        onView(allOf(withId(R.id.switcher), withText("Active")))
                .check(matches(isDisplayed()))
                .perform(scrollTo(), click());
    }

    public static void editNewsItems(int testNumber, boolean editTitle, boolean descriptionEdit, boolean active, boolean editNews) {
        onView(isRoot()).perform(waitId(R.id.news_list_recycler_view, 5000));
        onView(allOf(withId(R.id.news_list_recycler_view))).perform(actionOnItemAtPosition(0, click()));
        onView(isRoot()).perform(waitId(R.id.edit_news_item_image_view, 5000));
        onView(allOf(withId(R.id.edit_news_item_image_view)))
                .perform(click());
        if (editTitle == true) {
            editTitle(testNumber);
        }
        if (descriptionEdit == true) {
            editDescription(testNumber);
        }
        if (active == true) {
            editState();
        }
        if (editNews == true) {
            onView(allOf(withId(R.id.save_button))).perform(scrollTo(), click());
        } else {
            onView(allOf(withId(R.id.cancel_button))).perform(scrollTo(), click());
            onView(allOf(withId(android.R.id.button1), withText("OK"))).perform(scrollTo(), click());
        }
        newsItemCheck(testNumber, today, testNumber, editNews);
    }


    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
