package ru.iteco.fmhandroid.ui;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
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

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.text.SimpleDateFormat;
import java.util.Date;

import ru.iteco.fmhandroid.R;

public class MethodsClass {
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

    //    news page methods
    public static void openNewsWindow() {
        onView(allOf(withId(R.id.filter_news_material_button))).check(matches(isDisplayed())).perform(click());
        onView(allOf(withId(R.id.filter_news_title_text_view))).check(matches(withText("Filter news")));
        onView(allOf(withId(R.id.news_item_category_text_auto_complete_text_view))).check(matches(isDisplayed()));

        onView(allOf(withId(R.id.filter_button))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.cancel_button))).check(matches(isDisplayed())).perform(click());
        onView(allOf(withText("News"),
                withParent(withParent(withId(R.id.container_list_news_include))),
                isDisplayed())).check(matches(withText("News")));
    }

    public static void controlPanelElementsDisplayed() {
        onView(allOf(withText("Control panel"))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.sort_news_material_button))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.filter_news_material_button))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.add_news_image_view))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.news_item_material_card_view))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.category_icon_image_view))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.news_item_title_text_view))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.news_item_publication_text_view))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.news_item_publication_date_text_view))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.news_item_creation_text_view))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.news_item_create_date_text_view))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.news_item_author_text_view))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.news_item_author_name_text_view))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.news_item_published_icon_image_view))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.delete_news_item_image_view))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.edit_news_item_image_view))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.view_news_item_image_view))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.news_list_recycler_view))).perform(actionOnItemAtPosition(0, click()));
        onView(allOf(withId(R.id.news_item_description_text_view))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.delete_news_item_image_view))).perform(click());
    }

    public static void filterNews(String category) {
        openNewsWindow();

        onView(allOf(withId(R.id.news_item_category_text_auto_complete_text_view))).perform(click());
        onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(5).perform(click());

        onView(allOf(withId(R.id.news_item_category_text_auto_complete_text_view), withText(category)))
                .check(matches(withText(category)));

        onView(allOf(withId(R.id.news_item_publish_date_start_text_input_edit_text))).perform(click());

        ViewInteraction okButton = onView(
                allOf(withId(android.R.id.button1))).perform(scrollTo(), click());

        onView(allOf(withId(R.id.news_item_publish_date_end_text_input_edit_text))).perform(click());

        okButton.perform(scrollTo(), click());

        onView(allOf(withId(R.id.filter_button))).perform(click());

        onView(allOf(withId(R.id.filter_button))).check(matches(isDisplayed())).perform(click());
        onView(allOf(withId(R.id.news_list_recycler_view))).perform(actionOnItemAtPosition(3, click()));
        onView(allOf(withId(R.id.news_item_title_text_view))).check(matches(isDisplayed()));
    }


    public static void fillAuthFormFields(String login, String password) {
        onView(allOf(withId(R.id.login_text_input_layout))).perform(replaceText(login));
        onView(allOf(withId(R.id.password_text_input_layout))).perform(click())
                .perform(typeText(password), closeSoftKeyboard());
        onView(allOf(withId(R.id.enter_button))).perform(click());
    }

    public static void newsItemCheck(int testNumberT, String date, int testNumberD, boolean flagS) {
        String state;
        onView(allOf(withId(R.id.news_item_title_text_view)))
                .check(matches(isDisplayed()))
                .check(matches(withText("Birthday test n " + testNumberT)));

        onView(allOf(withId(R.id.news_item_publication_date_text_view)))
                .check(matches(isDisplayed()))
                .check(matches(withText(date)));
        if (flagS == true) {
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
        onView(allOf(withId(R.id.add_news_image_view))).perform(click());
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

        newsItemCheck(testNumber, getToday(), testNumber, true);

    }

    public static void editTitle(int testNumber) {
        onView(allOf(withId(R.id.news_item_title_text_input_edit_text)))
                .check(matches(isDisplayed()))
                .perform(replaceText("Birthday test n " + testNumber))
                .perform(closeSoftKeyboard());
    }

    public static void editDescription(int testNumber) {
        onView(allOf(withId(R.id.news_item_description_text_input_edit_text)))
                .check(matches(isDisplayed()))
                .perform(replaceText("Description for birthday test next " + testNumber))
                .perform(closeSoftKeyboard());
    }

    public static void editState() {
        onView(allOf(withId(R.id.switcher), withText("Active")))
                .check(matches(isDisplayed()))
                .perform(scrollTo(), click());
    }

    public static void editNewsItems(int testNumber, boolean flagT, boolean flagD, boolean flagS, boolean flagI) {
        onView(allOf(withId(R.id.news_list_recycler_view))).perform(actionOnItemAtPosition(0, click()));
        onView(allOf(withId(R.id.edit_news_item_image_view)))
                .perform(click());
        if (flagT == true) {
            editTitle(testNumber);
        }
        if (flagD == true) {
            editDescription(testNumber);
        }
        if (flagS == true) {
            editState();
        }
        if (flagI == true) {
            onView(allOf(withId(R.id.save_button))).perform(scrollTo(), click());
        } else {
            onView(allOf(withId(R.id.cancel_button))).perform(scrollTo(), click());
            onView(allOf(withId(android.R.id.button1), withText("OK"))).perform(scrollTo(), click());
        }
        newsItemCheck(testNumber, getToday(), testNumber, flagI);
    }


//    claims page methods

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

    public static void checkClaimData(int testNumber, boolean flagExecutor, String status) {
        onView(allOf(withId(R.id.title_text_view)))
                .check(matches(isDisplayed()))
                .check(matches(withText("Title for test number " + testNumber)));
        String executor;
        if (flagExecutor == true) {
            executor = "Ivanov Ivan Ivanovich";
        } else {
            executor = "NOT ASSIGNED";
        }
        onView(allOf(withId(R.id.executor_name_text_view)))
                .check(matches(isDisplayed()))
                .check(matches(withText(executor)));
        onView(allOf(withId(R.id.plane_date_text_view)))
                .check(matches(isDisplayed()))
                .check(matches(withText(getToday())));
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
        onView(allOf(withId(R.id.plan_date_material_text_view), withText(getToday())))
                .check(matches(isDisplayed()))
                .check(matches(withText(getToday())));
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

    public static void editClaim(int testNumber, int newTestNumber, boolean person, boolean flagCancelled) {
        checkClaimData(testNumber, person, "Open");
        onView(allOf(withId(R.id.edit_processing_image_button)))
                .check(matches(isDisplayed()));
        checkFieldsOnCreateClaimPagePresent();
        title.perform(replaceText("Title for test number " + testNumber), closeSoftKeyboard());
        description.perform(replaceText("Description for test number " + testNumber), closeSoftKeyboard());
        if (flagCancelled == true) {
            cancelB.check(matches(isDisplayed()))
                    .perform(click());
            onView(allOf(withId(android.R.id.button1))).perform(scrollTo(), click());
        } else {
            saveB.check(matches(isDisplayed()))
                    .perform(click());
        }
        checkClaimData(newTestNumber, person, "Open");
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
