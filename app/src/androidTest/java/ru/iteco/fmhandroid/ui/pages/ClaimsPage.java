package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
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

public class ClaimsPage {
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

    public static void getClaimsPagesObjects(){
        onView(isRoot()).perform(waitId(R.id.main_menu_image_button, 5000));
        onView(allOf(withId(R.id.add_new_claim_material_button))).check(matches(isDisplayed()));
        onView(allOf(withParent(allOf(withId(R.id.claim_list_card))))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.claim_list_card))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.claim_list_recycler_view))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.all_claims_cards_block_constraint_layout))).check(matches(isDisplayed()));
    }

    public static void checkClaimStatus(String status) {
        onView(allOf(withId(R.id.claim_list_filter_ok_material_button), withText("OK"))).perform(scrollTo(), click());

        onView(allOf(withId(R.id.claim_list_recycler_view),
                childAtPosition(
                        withId(R.id.all_claims_cards_block_constraint_layout),
                        4))).perform(actionOnItemAtPosition(0, click()));

        onView(allOf(withId(R.id.status_label_text_view))).check(matches(withText(status)));
    }

    public static void chooseFilterCriteriaInClaims(String option) {
        onView(allOf(withId(R.id.filters_material_button), withContentDescription("Filter claim list menu button"))).check(matches(isDisplayed())).perform(click());
        onView(allOf(withParent(withParent(withId(com.google.android.material.R.id.custom))))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.claim_filter_dialog_title))).check(matches(withText("Filtering")));
        ViewInteraction checkBox = onView(
                allOf(withId(R.id.item_filter_open), withText("Open")));
        checkBox.check(matches(isDisplayed()));

        ViewInteraction checkBox2 = onView(
                allOf(withId(R.id.item_filter_in_progress), withText("In progress")));
        checkBox2.check(matches(isDisplayed()));

        ViewInteraction checkBox3 = onView(
                allOf(withId(R.id.item_filter_executed), withText("Executed")));
        checkBox3.check(matches(isDisplayed()));

        ViewInteraction checkBox4 = onView(
                allOf(withId(R.id.item_filter_cancelled), withText("Cancelled")));
        checkBox4.check(matches(isDisplayed()));

        ViewInteraction saveButton = onView(allOf(withId(R.id.claim_list_filter_ok_material_button), withText("OK"))).check(matches(isDisplayed()));

        onView(allOf(withId(R.id.claim_filter_cancel_material_button), withText("CANCEL"))).check(matches(isDisplayed()));

        if (option == "Open") {
            checkBox2.perform(click());
        } else if (option == "In progress") {
            checkBox.perform(click());
        } else if (option == "Executed") {
            checkBox2.perform(click());
            checkBox.perform(click());
            checkBox3.perform(click());
        } else if (option == "Cancelled") {
            checkBox2.perform(click());
            checkBox.perform(click());
            checkBox4.perform(click());
        }
        saveButton.perform(click());
        checkClaimStatus(option);

    }


    static ViewInteraction title = onView(allOf(withId(R.id.title_edit_text), withText("Title")));
    static ViewInteraction executor = onView(allOf(withId(R.id.executor_drop_menu_auto_complete_text_view)));
    static ViewInteraction date = onView(allOf(withId(R.id.date_in_plan_text_input_edit_text)));
    static ViewInteraction time = onView(allOf(withId(R.id.time_in_plan_text_input_edit_text)));
    static ViewInteraction description = onView(allOf(withId(R.id.description_edit_text)));
    static ViewInteraction saveB = onView(allOf(withId(R.id.save_button)));
    static ViewInteraction cancelB = onView(allOf(withId(R.id.cancel_button)));

    public static void checkFieldsOnCreateClaimPagePresent() {
        onView(allOf(withId(R.id.custom_app_bar_sub_title_text_view), withText("Claims"))).check(matches(withText("Claims")));
        onView(allOf(withId(R.id.executor_drop_menu_text_input_layout))).check(matches(isDisplayed()));
        title.check(matches(isDisplayed()));
        executor.check(matches(isDisplayed()));
        date.check(matches(isDisplayed()));
        time.check(matches(isDisplayed()));
        description.check(matches(isDisplayed()));
        saveB.check(matches(isDisplayed()));
        cancelB.check(matches(isDisplayed()));
    }

    public static void checkClaimData(int testNumber, boolean fillExecutor, String status) {
        onView(allOf(withId(R.id.title_text_view)))
                .check(matches(isDisplayed()))
                .check(matches(withText("Title for test number " + testNumber)));
        String executor;
        if (fillExecutor == true) {
            executor = "Ivanov Ivan Ivanovich";
        } else {
            executor = "NOT ASSIGNED";
        }
        onView(allOf(withId(R.id.executor_name_text_view)))
                .check(matches(isDisplayed()))
                .check(matches(withText(executor)));
        onView(allOf(withId(R.id.plane_date_text_view)))
                .check(matches(isDisplayed()))
                .check(matches(withText(today)));
        onView(allOf(withId(R.id.status_label_text_view)))
                .check(matches(isDisplayed()))
                .check(matches(withText(status)));
        onView(allOf(withId(R.id.description_text_view)))
                .check(matches(isDisplayed()))
                .check(matches(withText("Description for test number " + testNumber)));
        onView(allOf(withId(R.id.comments_material_card_view)))
                .check(matches(isDisplayed()));
        onView(allOf(withId(R.id.add_comment_image_button)))
                .check(matches(isDisplayed()));
        onView(allOf(withId(R.id.close_image_button)))
                .check(matches(isDisplayed()));
        onView(allOf(withId(R.id.status_processing_image_button)))
                .check(matches(isDisplayed()));
        onView(allOf(withId(R.id.edit_processing_image_button)))
                .check(matches(isDisplayed()));
    }

    public static void checkClaimDataOnClaimsPage(int testNumber) {
        onView(
                allOf(withId(R.id.description_material_text_view), withText("Title for test number " + testNumber)))
                .check(matches(isDisplayed()))
                .check(matches(withText("Title for test number " + testNumber)));
        onView(allOf(withId(R.id.plan_date_material_text_view), withText(today)))
                .check(matches(isDisplayed()))
                .check(matches(withText(today)));
    }


    public static void createNewClaim(Integer testNumber, boolean person) {
//        is displayed
        onView(allOf(withId(R.id.add_new_claim_material_button))).perform(click());
        onView(allOf(withId(R.id.custom_app_bar_title_text_view), withText("Creating"))).check(matches(withText("Creating")));
        checkFieldsOnCreateClaimPagePresent();


//        interactions
        title.perform(replaceText("Title for test number " + testNumber), closeSoftKeyboard());
        description.perform(replaceText("Description for test number " + testNumber), closeSoftKeyboard());

        executor = onView(allOf(withId(R.id.executor_drop_menu_auto_complete_text_view))).perform(click());
        onData(anything()).inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(0).perform(click());
        date.perform(click());
        ViewInteraction okB = onView(allOf(withId(android.R.id.button1)));
        okB.perform(scrollTo(), click());
        time.perform(click());
        okB.perform(scrollTo(), click());
        saveB.perform(scrollTo(), click());

        checkClaimDataOnClaimsPage(testNumber);

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.claim_list_recycler_view)));
        recyclerView.perform(actionOnItemAtPosition(537, click()));

        String status;
        if (person == true) {
            status = "In progress";
        } else {
            status = "Open";
        }

        checkClaimData(testNumber, person, status);
    }

    public static void addComment(String text) {
        onView(allOf(withId(R.id.add_comment_image_button)))
                .check(matches(isDisplayed()))
                .perform(click());

        ViewInteraction editText = onView(
                allOf(withText("Comment"),
                        withParent(withParent(withId(R.id.comment_text_input_layout)))));
        editText.check(matches(withText("Comment")));

        ViewInteraction saveButton = onView(allOf(withId(R.id.save_button)))
                .check(matches(isDisplayed()));

        onView(allOf(withId(R.id.cancel_button))).check(matches(isDisplayed()));
        editText.perform(replaceText(text), closeSoftKeyboard());
        saveButton.perform(scrollTo(), click());
        onView(allOf(withId(R.id.comment_description_text_view))).check(matches(withText(text)));
        onView(allOf(withId(R.id.edit_comment_image_button))).check(matches(isDisplayed()));
    }

    public static void changeStatus(String currentStatus, String newStatus, String toNewStatus, String secStatus) {
        onView(allOf(withId(R.id.status_label_text_view)))
                .check(matches(isDisplayed()))
                .check(matches(withText(currentStatus)));
        onView(allOf(withId(R.id.status_processing_image_button))).perform(click());

        ViewInteraction newStatusSet = onView(allOf(withId(android.R.id.title), withText(newStatus))).check(matches(isDisplayed()));
        onView(allOf(withId(android.R.id.title), withText(secStatus))).check(matches(isDisplayed()));

        newStatusSet.perform(click());
        onView(allOf(withId(R.id.status_label_text_view)))
                .check(matches(isDisplayed()))
                .check(matches(withText(newStatus)));
        onView(allOf(withId(R.id.status_processing_image_button))).perform(click());
    }

    public static void editClaim(int testNumber, int newTestNumber, boolean person, boolean cancelled) {
        checkClaimData(testNumber, person, "Open");
        onView(allOf(withId(R.id.edit_processing_image_button)))
                .check(matches(isDisplayed()));
        checkFieldsOnCreateClaimPagePresent();
        title.perform(replaceText("Title for test number " + testNumber), closeSoftKeyboard());
        description.perform(replaceText("Description for test number " + testNumber), closeSoftKeyboard());
        if (cancelled == true) {
            cancelB.check(matches(isDisplayed()))
                    .perform(click());
            onView(allOf(withId(android.R.id.button1))).perform(scrollTo(), click());
        } else {
            saveB.check(matches(isDisplayed()))
                    .perform(click());
        }
        checkClaimData(newTestNumber, person, "Open");
    }

    public void getErrorOnEmptyFields() {
        onView(allOf(withId(R.id.add_new_claim_material_button)))
                .check(matches(isDisplayed()))
                .perform(click());
        onView(allOf(withId(R.id.save_button)))
                .check(matches(isDisplayed()))
                .perform(click());
        onView(allOf(withId(android.R.id.message))).check(matches(withText("Fill empty fields")));
        onView(allOf(withId(android.R.id.button1))).perform(scrollTo(), click());
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
