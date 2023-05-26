package ru.iteco.fmhandroid.ui;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
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

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.R;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ClaimsPageTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Test
    public void claimsPageTest() {
        ViewInteraction textInputEditText = onView(
                allOf(childAtPosition(
                                childAtPosition(
                                        withId(R.id.login_text_input_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText.perform(replaceText("login2"), closeSoftKeyboard());

        ViewInteraction textInputEditText2 = onView(
                allOf(childAtPosition(
                                childAtPosition(
                                        withId(R.id.password_text_input_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText2.perform(replaceText("password2"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.enter_button), withText("Sign in"), withContentDescription("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        1),
                                2),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction materialTextView = onView(
                allOf(withId(R.id.all_claims_text_view), withText("all claims"),
                        childAtPosition(
                                allOf(withId(R.id.container_list_claim_include_on_fragment_main),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                1),
                        isDisplayed()));
        materialTextView.perform(click());

        ViewInteraction textView = onView(
                allOf(withText("Claims"),
                        withParent(withParent(withId(R.id.container_list_claim_include))),
                        isDisplayed()));
        textView.check(matches(withText("Claims")));

        ViewInteraction button = onView(
                allOf(withId(R.id.filters_material_button), withContentDescription("Filter claim list menu button"),
                        withParent(withParent(withId(R.id.container_list_claim_include))),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction button2 = onView(
                allOf(withId(R.id.add_new_claim_material_button), withContentDescription("Add new claim button"),
                        withParent(withParent(withId(R.id.container_list_claim_include))),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));

        ViewInteraction viewGroup = onView(
                allOf(withParent(allOf(withId(R.id.claim_list_card),
                                withParent(withId(R.id.claim_list_recycler_view)))),
                        isDisplayed()));
        viewGroup.check(matches(isDisplayed()));

        ViewInteraction cardView = onView(
                allOf(withId(R.id.claim_list_card),
                        withParent(allOf(withId(R.id.claim_list_recycler_view),
                                withParent(withId(R.id.all_claims_cards_block_constraint_layout)))),
                        isDisplayed()));
        cardView.check(matches(isDisplayed()));

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.claim_list_recycler_view),
                        withParent(allOf(withId(R.id.all_claims_cards_block_constraint_layout),
                                withParent(withId(R.id.container_list_claim_include)))),
                        isDisplayed()));
        recyclerView.check(matches(isDisplayed()));

        ViewInteraction viewGroup2 = onView(
                allOf(withId(R.id.all_claims_cards_block_constraint_layout),
                        withParent(allOf(withId(R.id.container_list_claim_include),
                                withParent(withId(R.id.claim_list_swipe_refresh)))),
                        isDisplayed()));
        viewGroup2.check(matches(isDisplayed()));

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.claim_list_recycler_view),
                        childAtPosition(
                                withId(R.id.all_claims_cards_block_constraint_layout),
                                4)));
        recyclerView2.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction viewGroup3 = onView(
                allOf(withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
                        isDisplayed()));
        viewGroup3.check(matches(isDisplayed()));

        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.close_image_button), withContentDescription("button close"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                23),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.filters_material_button), withContentDescription("Filter claim list menu button"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container_list_claim_include),
                                        0),
                                1),
                        isDisplayed()));
        materialButton2.perform(click());

        ViewInteraction linearLayoutCompat = onView(
                allOf(withParent(withParent(withId(com.google.android.material.R.id.custom))),
                        isDisplayed()));
        linearLayoutCompat.check(matches(isDisplayed()));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.claim_filter_dialog_title), withText("Filtering"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
                        isDisplayed()));
        textView2.check(matches(withText("Filtering")));

        ViewInteraction checkBox = onView(
                allOf(withId(R.id.item_filter_open), withText("Open"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
                        isDisplayed()));
        checkBox.check(matches(isDisplayed()));

        ViewInteraction checkBox2 = onView(
                allOf(withId(R.id.item_filter_in_progress), withText("In progress"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
                        isDisplayed()));
        checkBox2.check(matches(isDisplayed()));

        ViewInteraction checkBox3 = onView(
                allOf(withId(R.id.item_filter_executed), withText("Executed"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
                        isDisplayed()));
        checkBox3.check(matches(isDisplayed()));

        ViewInteraction checkBox4 = onView(
                allOf(withId(R.id.item_filter_cancelled), withText("Cancelled"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
                        isDisplayed()));
        checkBox4.check(matches(isDisplayed()));

        ViewInteraction button3 = onView(
                allOf(withId(R.id.claim_list_filter_ok_material_button), withText("OK"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
                        isDisplayed()));
        button3.check(matches(isDisplayed()));

        ViewInteraction button4 = onView(
                allOf(withId(R.id.claim_filter_cancel_material_button), withText("CANCEL"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
                        isDisplayed()));
        button4.check(matches(isDisplayed()));

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.claim_filter_cancel_material_button), withText("Cancel"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        materialButton3.perform(scrollTo(), click());

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.filters_material_button), withContentDescription("Filter claim list menu button"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container_list_claim_include),
                                        0),
                                1),
                        isDisplayed()));
        materialButton4.perform(click());

        ViewInteraction materialCheckBox = onView(
                allOf(withId(R.id.item_filter_in_progress), withText("In progress"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        materialCheckBox.perform(scrollTo(), click());

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.claim_list_filter_ok_material_button), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                5)));
        materialButton5.perform(scrollTo(), click());

        ViewInteraction recyclerView3 = onView(
                allOf(withId(R.id.claim_list_recycler_view),
                        childAtPosition(
                                withId(R.id.all_claims_cards_block_constraint_layout),
                                4)));
        recyclerView3.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.status_label_text_view), withText("Open"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.cardview.widget.CardView.class))),
                        isDisplayed()));
        textView3.check(matches(withText("Open")));

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withId(R.id.close_image_button), withContentDescription("button close"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                23),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction materialButton6 = onView(
                allOf(withId(R.id.filters_material_button), withContentDescription("Filter claim list menu button"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container_list_claim_include),
                                        0),
                                1),
                        isDisplayed()));
        materialButton6.perform(click());

        ViewInteraction materialCheckBox2 = onView(
                allOf(withId(R.id.item_filter_in_progress), withText("In progress"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        materialCheckBox2.perform(scrollTo(), click());

        ViewInteraction materialCheckBox3 = onView(
                allOf(withId(R.id.item_filter_open), withText("Open"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        materialCheckBox3.perform(scrollTo(), click());

        ViewInteraction materialButton7 = onView(
                allOf(withId(R.id.claim_list_filter_ok_material_button), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                5)));
        materialButton7.perform(scrollTo(), click());

        ViewInteraction recyclerView4 = onView(
                allOf(withId(R.id.claim_list_recycler_view),
                        childAtPosition(
                                withId(R.id.all_claims_cards_block_constraint_layout),
                                4)));
        recyclerView4.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.status_label_text_view), withText("In progress"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.cardview.widget.CardView.class))),
                        isDisplayed()));
        textView4.check(matches(withText("In progress")));

        ViewInteraction appCompatImageButton3 = onView(
                allOf(withId(R.id.close_image_button), withContentDescription("button close"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                23),
                        isDisplayed()));
        appCompatImageButton3.perform(click());

        ViewInteraction materialButton8 = onView(
                allOf(withId(R.id.filters_material_button), withContentDescription("Filter claim list menu button"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container_list_claim_include),
                                        0),
                                1),
                        isDisplayed()));
        materialButton8.perform(click());

        ViewInteraction materialCheckBox4 = onView(
                allOf(withId(R.id.item_filter_executed), withText("Executed"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialCheckBox4.perform(scrollTo(), click());

        ViewInteraction materialCheckBox5 = onView(
                allOf(withId(R.id.item_filter_in_progress), withText("In progress"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        materialCheckBox5.perform(scrollTo(), click());

        ViewInteraction materialButton9 = onView(
                allOf(withId(R.id.claim_list_filter_ok_material_button), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                5)));
        materialButton9.perform(scrollTo(), click());

        ViewInteraction recyclerView5 = onView(
                allOf(withId(R.id.claim_list_recycler_view),
                        childAtPosition(
                                withId(R.id.all_claims_cards_block_constraint_layout),
                                4)));
        recyclerView5.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.status_label_text_view), withText("Executed"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.cardview.widget.CardView.class))),
                        isDisplayed()));
        textView5.check(matches(withText("Executed")));

        ViewInteraction appCompatImageButton4 = onView(
                allOf(withId(R.id.close_image_button), withContentDescription("button close"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                23),
                        isDisplayed()));
        appCompatImageButton4.perform(click());

        ViewInteraction materialButton10 = onView(
                allOf(withId(R.id.filters_material_button), withContentDescription("Filter claim list menu button"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container_list_claim_include),
                                        0),
                                1),
                        isDisplayed()));
        materialButton10.perform(click());

        ViewInteraction materialCheckBox6 = onView(
                allOf(withId(R.id.item_filter_cancelled), withText("Cancelled"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                4)));
        materialCheckBox6.perform(scrollTo(), click());

        ViewInteraction materialCheckBox7 = onView(
                allOf(withId(R.id.item_filter_executed), withText("Executed"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialCheckBox7.perform(scrollTo(), click());

        ViewInteraction materialButton11 = onView(
                allOf(withId(R.id.claim_list_filter_ok_material_button), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                5)));
        materialButton11.perform(scrollTo(), click());

        ViewInteraction recyclerView6 = onView(
                allOf(withId(R.id.claim_list_recycler_view),
                        childAtPosition(
                                withId(R.id.all_claims_cards_block_constraint_layout),
                                4)));
        recyclerView6.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.status_label_text_view), withText("Canceled"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.cardview.widget.CardView.class))),
                        isDisplayed()));
        textView6.check(matches(withText("Canceled")));

        ViewInteraction appCompatImageButton5 = onView(
                allOf(withId(R.id.close_image_button), withContentDescription("button close"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                23),
                        isDisplayed()));
        appCompatImageButton5.perform(click());

        ViewInteraction materialButton12 = onView(
                allOf(withId(R.id.filters_material_button), withContentDescription("Filter claim list menu button"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container_list_claim_include),
                                        0),
                                1),
                        isDisplayed()));
        materialButton12.perform(click());

        ViewInteraction materialCheckBox8 = onView(
                allOf(withId(R.id.item_filter_open), withText("Open"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        materialCheckBox8.perform(scrollTo(), click());

        ViewInteraction materialButton13 = onView(
                allOf(withId(R.id.claim_filter_cancel_material_button), withText("Cancel"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        materialButton13.perform(scrollTo(), click());

        ViewInteraction recyclerView7 = onView(
                allOf(withId(R.id.claim_list_recycler_view),
                        childAtPosition(
                                withId(R.id.all_claims_cards_block_constraint_layout),
                                4)));
        recyclerView7.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.status_label_text_view), withText("Canceled"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.cardview.widget.CardView.class))),
                        isDisplayed()));
        textView7.check(matches(withText("Canceled")));

        ViewInteraction appCompatImageButton6 = onView(
                allOf(withId(R.id.close_image_button), withContentDescription("button close"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                23),
                        isDisplayed()));
        appCompatImageButton6.perform(click());

        ViewInteraction materialButton14 = onView(
                allOf(withId(R.id.add_new_claim_material_button), withContentDescription("Add new claim button"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container_list_claim_include),
                                        0),
                                2),
                        isDisplayed()));
        materialButton14.perform(click());

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.custom_app_bar_title_text_view), withText("Creating"),
                        withParent(allOf(withId(R.id.container_custom_app_bar_include_on_fragment_create_edit_claim),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class)))),
                        isDisplayed()));
        textView8.check(matches(withText("Creating")));

        ViewInteraction editText = onView(
                allOf(withId(R.id.title_edit_text), withText("Title"),
                        withParent(withParent(withId(R.id.title_text_input_layout))),
                        isDisplayed()));
        editText.check(matches(isDisplayed()));

        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.executor_drop_menu_text_input_layout),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.cardview.widget.CardView.class))),
                        isDisplayed()));
        linearLayout.check(matches(isDisplayed()));

        ViewInteraction linearLayout2 = onView(
                allOf(withId(R.id.date_in_plan_text_input_layout),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.cardview.widget.CardView.class))),
                        isDisplayed()));
        linearLayout2.check(matches(isDisplayed()));

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.time_in_plan_text_input_edit_text), withText("Time"),
                        withParent(withParent(withId(R.id.time_in_plan_text_input_layout))),
                        isDisplayed()));
        editText2.check(matches(withText("Time")));

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.time_in_plan_text_input_edit_text), withText("Time"),
                        withParent(withParent(withId(R.id.time_in_plan_text_input_layout))),
                        isDisplayed()));
        editText3.check(matches(isDisplayed()));

        ViewInteraction linearLayout3 = onView(
                allOf(withId(R.id.description_text_input_layout),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.cardview.widget.CardView.class))),
                        isDisplayed()));
        linearLayout3.check(matches(isDisplayed()));

        ViewInteraction button5 = onView(
                allOf(withId(R.id.save_button), withText("SAVE"), withContentDescription("Save"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.cardview.widget.CardView.class))),
                        isDisplayed()));
        button5.check(matches(isDisplayed()));

        ViewInteraction button6 = onView(
                allOf(withId(R.id.cancel_button), withText("CANCEL"), withContentDescription("Cancel"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.cardview.widget.CardView.class))),
                        isDisplayed()));
        button6.check(matches(isDisplayed()));

        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.title_edit_text),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.title_text_input_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText3.perform(replaceText("no puedo mas"), closeSoftKeyboard());

        ViewInteraction materialAutoCompleteTextView = onView(
                allOf(withId(R.id.executor_drop_menu_auto_complete_text_view),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.executor_drop_menu_text_input_layout),
                                        0),
                                0),
                        isDisplayed()));
        materialAutoCompleteTextView.perform(click());

        DataInteraction materialTextView2 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(0);
        materialTextView2.perform(click());

        ViewInteraction textInputEditText4 = onView(
                allOf(withId(R.id.date_in_plan_text_input_edit_text),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.date_in_plan_text_input_layout),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText4.perform(click());

        ViewInteraction materialButton15 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton15.perform(scrollTo(), click());

        ViewInteraction textInputEditText5 = onView(
                allOf(withId(R.id.time_in_plan_text_input_edit_text),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.time_in_plan_text_input_layout),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText5.perform(click());

        ViewInteraction materialButton16 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton16.perform(scrollTo(), click());

        ViewInteraction textInputEditText6 = onView(
                allOf(withId(R.id.description_edit_text),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.description_text_input_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText6.perform(replaceText("la cancion de enrique"), closeSoftKeyboard());

        ViewInteraction materialButton17 = onView(
                allOf(withId(R.id.save_button), withText("Save"), withContentDescription("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                6)));
        materialButton17.perform(scrollTo(), click());

        ViewInteraction materialButton18 = onView(
                allOf(withId(R.id.filters_material_button), withContentDescription("Filter claim list menu button"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container_list_claim_include),
                                        0),
                                1),
                        isDisplayed()));
        materialButton18.perform(click());

        ViewInteraction materialCheckBox9 = onView(
                allOf(withId(R.id.item_filter_cancelled), withText("Cancelled"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                4)));
        materialCheckBox9.perform(scrollTo(), click());

        ViewInteraction materialCheckBox10 = onView(
                allOf(withId(R.id.item_filter_in_progress), withText("In progress"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        materialCheckBox10.perform(scrollTo(), click());

        ViewInteraction materialButton19 = onView(
                allOf(withId(R.id.claim_list_filter_ok_material_button), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                5)));
        materialButton19.perform(scrollTo(), click());

        ViewInteraction materialButton20 = onView(
                allOf(withId(R.id.filters_material_button), withContentDescription("Filter claim list menu button"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container_list_claim_include),
                                        0),
                                1),
                        isDisplayed()));
        materialButton20.perform(click());

        ViewInteraction materialCheckBox11 = onView(
                allOf(withId(R.id.item_filter_open), withText("Open"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        materialCheckBox11.perform(scrollTo(), click());

        ViewInteraction materialButton21 = onView(
                allOf(withId(R.id.claim_list_filter_ok_material_button), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                5)));
        materialButton21.perform(scrollTo(), click());

        ViewInteraction cardView2 = onView(
                allOf(withId(R.id.claim_list_card),
                        withParent(allOf(withId(R.id.claim_list_recycler_view),
                                withParent(withId(R.id.all_claims_cards_block_constraint_layout)))),
                        isDisplayed()));
        cardView2.check(matches(isDisplayed()));

        ViewInteraction textView9 = onView(
                allOf(withId(R.id.description_material_text_view), withText("no puedo mas"),
                        withParent(withParent(withId(R.id.claim_list_card))),
                        isDisplayed()));
        textView9.check(matches(withText("no puedo mas")));

        ViewInteraction textView10 = onView(
                allOf(withId(R.id.description_material_text_view), withText("no puedo mas"),
                        withParent(withParent(withId(R.id.claim_list_card))),
                        isDisplayed()));
        textView10.check(matches(withText("no puedo mas")));

        ViewInteraction recyclerView8 = onView(
                allOf(withId(R.id.claim_list_recycler_view),
                        childAtPosition(
                                withId(R.id.all_claims_cards_block_constraint_layout),
                                4)));
        recyclerView8.perform(actionOnItemAtPosition(2213, click()));

        ViewInteraction textView11 = onView(
                allOf(withId(R.id.title_text_view), withText("no puedo mas"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.cardview.widget.CardView.class))),
                        isDisplayed()));
        textView11.check(matches(withText("no puedo mas")));

        ViewInteraction textView12 = onView(
                allOf(withId(R.id.executor_name_text_view), withText("Ivanov Ivan Ivanovich"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.cardview.widget.CardView.class))),
                        isDisplayed()));
        textView12.check(matches(withText("Ivanov Ivan Ivanovich")));

        ViewInteraction textView13 = onView(
                allOf(withId(R.id.plane_date_text_view), withText("26.05.2023"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.cardview.widget.CardView.class))),
                        isDisplayed()));
        textView13.check(matches(withText("26.05.2023")));

        ViewInteraction textView14 = onView(
                allOf(withId(R.id.plan_time_text_view), withText("16:53"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.cardview.widget.CardView.class))),
                        isDisplayed()));
        textView14.check(matches(withText("16:53")));

        ViewInteraction imageView = onView(
                allOf(withId(R.id.status_icon_image_view),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.cardview.widget.CardView.class))),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        ViewInteraction textView15 = onView(
                allOf(withId(R.id.status_label_text_view), withText("In progress"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.cardview.widget.CardView.class))),
                        isDisplayed()));
        textView15.check(matches(withText("In progress")));

        ViewInteraction textView16 = onView(
                allOf(withId(R.id.description_text_view), withText("la cancion de enrique"),
                        withParent(allOf(withId(R.id.description_material_card_view),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        textView16.check(matches(withText("la cancion de enrique")));

        ViewInteraction textView17 = onView(
                allOf(withText("Add comment"),
                        withParent(withParent(withId(R.id.comments_material_card_view))),
                        isDisplayed()));
        textView17.check(matches(withText("Add comment")));

        ViewInteraction imageButton = onView(
                allOf(withId(R.id.status_processing_image_button), withContentDescription("button change status"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.cardview.widget.CardView.class))),
                        isDisplayed()));
        imageButton.check(matches(isDisplayed()));

        ViewInteraction imageButton2 = onView(
                allOf(withId(R.id.close_image_button), withContentDescription("button close"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.cardview.widget.CardView.class))),
                        isDisplayed()));
        imageButton2.check(matches(isDisplayed()));

        ViewInteraction appCompatImageButton7 = onView(
                allOf(withId(R.id.add_comment_image_button), withContentDescription("button add comment"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.comments_material_card_view),
                                        0),
                                2),
                        isDisplayed()));
        appCompatImageButton7.perform(click());

        ViewInteraction textInputEditText7 = onView(
                allOf(childAtPosition(
                                childAtPosition(
                                        withId(R.id.comment_text_input_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText7.perform(replaceText("comment 1"), closeSoftKeyboard());

        ViewInteraction materialButton22 = onView(
                allOf(withId(R.id.save_button), withText("Save"), withContentDescription("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                1)));
        materialButton22.perform(scrollTo(), click());

        ViewInteraction appCompatImageButton8 = onView(
                allOf(withId(R.id.add_comment_image_button), withContentDescription("button add comment"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.comments_material_card_view),
                                        0),
                                2),
                        isDisplayed()));
        appCompatImageButton8.perform(click());

        ViewInteraction textInputEditText8 = onView(
                allOf(childAtPosition(
                                childAtPosition(
                                        withId(R.id.comment_text_input_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText8.perform(replaceText("comment2"), closeSoftKeyboard());

        ViewInteraction materialButton23 = onView(
                allOf(withId(R.id.cancel_button), withText("Cancel"), withContentDescription("Cancel"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                2)));
        materialButton23.perform(scrollTo(), click());

        ViewInteraction appCompatImageButton9 = onView(
                allOf(withId(R.id.edit_comment_image_button), withContentDescription("button edit comment"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.claim_comments_list_recycler_view),
                                        0),
                                1),
                        isDisplayed()));
        appCompatImageButton9.perform(click());

        ViewInteraction textInputEditText9 = onView(
                allOf(withText("comment 1"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.comment_text_input_layout),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText9.perform(replaceText("comment 1.2"));

        ViewInteraction textInputEditText10 = onView(
                allOf(withText("comment 1.2"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.comment_text_input_layout),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText10.perform(closeSoftKeyboard());

        ViewInteraction materialButton24 = onView(
                allOf(withId(R.id.save_button), withText("Save"), withContentDescription("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                1)));
        materialButton24.perform(scrollTo(), click());

        ViewInteraction appCompatImageButton10 = onView(
                allOf(withId(R.id.edit_comment_image_button), withContentDescription("button edit comment"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.claim_comments_list_recycler_view),
                                        0),
                                1),
                        isDisplayed()));
        appCompatImageButton10.perform(click());

        ViewInteraction textInputEditText11 = onView(
                allOf(withText("comment 1.2"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.comment_text_input_layout),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText11.perform(replaceText("comment 1.2.3"));

        ViewInteraction textInputEditText12 = onView(
                allOf(withText("comment 1.2.3"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.comment_text_input_layout),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText12.perform(closeSoftKeyboard());

        ViewInteraction materialButton25 = onView(
                allOf(withId(R.id.cancel_button), withText("Cancel"), withContentDescription("Cancel"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                2)));
        materialButton25.perform(scrollTo(), click());

        ViewInteraction textView18 = onView(
                allOf(withId(R.id.comment_description_text_view), withText("comment 1.2"),
                        withParent(withParent(withId(R.id.claim_comments_list_recycler_view))),
                        isDisplayed()));
        textView18.check(matches(withText("comment 1.2")));

        ViewInteraction appCompatImageButton11 = onView(
                allOf(withId(R.id.status_processing_image_button), withContentDescription("button change status"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                24),
                        isDisplayed()));
        appCompatImageButton11.perform(click());

        ViewInteraction materialTextView3 = onView(
                allOf(withId(android.R.id.title), withText("To execute"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        materialTextView3.perform(click());

        ViewInteraction textInputEditText13 = onView(
                allOf(withId(R.id.editText),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.textfield.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText13.perform(replaceText("executetd"), closeSoftKeyboard());

        ViewInteraction materialButton26 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(com.google.android.material.R.id.buttonPanel),
                                        0),
                                3)));
        materialButton26.perform(scrollTo(), click());

        ViewInteraction textView19 = onView(
                allOf(withId(R.id.status_label_text_view), withText("Executed"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.cardview.widget.CardView.class))),
                        isDisplayed()));
        textView19.check(matches(withText("Executed")));

        ViewInteraction textView20 = onView(
                allOf(withId(R.id.comment_description_text_view), withText("executetd"),
                        withParent(withParent(withId(R.id.claim_comments_list_recycler_view))),
                        isDisplayed()));
        textView20.check(matches(withText("executetd")));

        ViewInteraction imageButton3 = onView(
                allOf(withId(R.id.close_image_button), withContentDescription("button close"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.cardview.widget.CardView.class))),
                        isDisplayed()));
        imageButton3.check(matches(isDisplayed()));

        ViewInteraction appCompatImageButton12 = onView(
                allOf(withId(R.id.status_processing_image_button), withContentDescription("button change status"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                24),
                        isDisplayed()));
        appCompatImageButton12.perform(click());

        ViewInteraction appCompatImageButton13 = onView(
                allOf(withId(R.id.close_image_button), withContentDescription("button close"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                23),
                        isDisplayed()));
        appCompatImageButton13.perform(click());

        ViewInteraction materialButton27 = onView(
                allOf(withId(R.id.filters_material_button), withContentDescription("Filter claim list menu button"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container_list_claim_include),
                                        0),
                                1),
                        isDisplayed()));
        materialButton27.perform(click());

        ViewInteraction materialButton28 = onView(
                allOf(withId(R.id.claim_list_filter_ok_material_button), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                5)));
        materialButton28.perform(scrollTo(), click());

        ViewInteraction materialButton29 = onView(
                allOf(withId(R.id.add_new_claim_material_button), withContentDescription("Add new claim button"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container_list_claim_include),
                                        0),
                                2),
                        isDisplayed()));
        materialButton29.perform(click());

        ViewInteraction textInputEditText14 = onView(
                allOf(withId(R.id.title_edit_text),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.title_text_input_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText14.perform(replaceText("claim title number 2"), closeSoftKeyboard());

        ViewInteraction textInputEditText15 = onView(
                allOf(withId(R.id.date_in_plan_text_input_edit_text),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.date_in_plan_text_input_layout),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText15.perform(click());

        ViewInteraction materialButton30 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton30.perform(scrollTo(), click());

        ViewInteraction textInputEditText16 = onView(
                allOf(withId(R.id.time_in_plan_text_input_edit_text),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.time_in_plan_text_input_layout),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText16.perform(click());

        ViewInteraction materialButton31 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton31.perform(scrollTo(), click());

        ViewInteraction textInputEditText17 = onView(
                allOf(withId(R.id.description_edit_text),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.description_text_input_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText17.perform(replaceText("descriptionw"), closeSoftKeyboard());

        ViewInteraction materialButton32 = onView(
                allOf(withId(R.id.save_button), withText("Save"), withContentDescription("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                6)));
        materialButton32.perform(scrollTo(), click());

        ViewInteraction materialTextView4 = onView(
                allOf(withId(R.id.description_material_text_view), withText("J. R. R. Tolkien 622"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.claim_list_card),
                                        0),
                                2),
                        isDisplayed()));
        materialTextView4.perform(longClick());

        ViewInteraction recyclerView9 = onView(
                allOf(withId(R.id.claim_list_recycler_view),
                        childAtPosition(
                                withId(R.id.all_claims_cards_block_constraint_layout),
                                4)));
        recyclerView9.perform(actionOnItemAtPosition(1, click()));

        ViewInteraction appCompatImageButton14 = onView(
                allOf(withId(R.id.close_image_button), withContentDescription("button close"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                23),
                        isDisplayed()));
        appCompatImageButton14.perform(click());

        ViewInteraction materialButton33 = onView(
                allOf(withId(R.id.filters_material_button), withContentDescription("Filter claim list menu button"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container_list_claim_include),
                                        0),
                                1),
                        isDisplayed()));
        materialButton33.perform(click());

        ViewInteraction materialCheckBox12 = onView(
                allOf(withId(R.id.item_filter_in_progress), withText("In progress"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        materialCheckBox12.perform(scrollTo(), click());

        ViewInteraction materialButton34 = onView(
                allOf(withId(R.id.claim_list_filter_ok_material_button), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                5)));
        materialButton34.perform(scrollTo(), click());

        ViewInteraction materialTextView5 = onView(
                allOf(withId(R.id.description_material_text_view), withText("������"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.claim_list_card),
                                        0),
                                2),
                        isDisplayed()));
        materialTextView5.perform(longClick());

        ViewInteraction materialTextView6 = onView(
                allOf(withId(R.id.description_material_text_view), withText("Tatiana hgk0jn6lb3it0or9onm"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.claim_list_card),
                                        0),
                                2),
                        isDisplayed()));
        materialTextView6.perform(longClick());

        ViewInteraction textView21 = onView(
                allOf(withId(R.id.description_material_text_view), withText("claim title number 2"),
                        withParent(withParent(withId(R.id.claim_list_card))),
                        isDisplayed()));
        textView21.check(matches(withText("claim title number 2")));

        ViewInteraction recyclerView10 = onView(
                allOf(withId(R.id.claim_list_recycler_view),
                        childAtPosition(
                                withId(R.id.all_claims_cards_block_constraint_layout),
                                4)));
        recyclerView10.perform(actionOnItemAtPosition(1708, click()));

        ViewInteraction textView22 = onView(
                allOf(withId(R.id.title_text_view), withText("claim title number 2"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.cardview.widget.CardView.class))),
                        isDisplayed()));
        textView22.check(matches(withText("claim title number 2")));

        ViewInteraction textView23 = onView(
                allOf(withId(R.id.executor_name_text_view), withText("NOT ASSIGNED"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.cardview.widget.CardView.class))),
                        isDisplayed()));
        textView23.check(matches(withText("NOT ASSIGNED")));

        ViewInteraction textView24 = onView(
                allOf(withId(R.id.plane_date_text_view), withText("27.05.2023"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.cardview.widget.CardView.class))),
                        isDisplayed()));
        textView24.check(matches(withText("27.05.2023")));

        ViewInteraction textView25 = onView(
                allOf(withId(R.id.plan_time_text_view), withText("17:27"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.cardview.widget.CardView.class))),
                        isDisplayed()));
        textView25.check(matches(withText("17:27")));

        ViewInteraction textView26 = onView(
                allOf(withId(R.id.status_label_text_view), withText("Open"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.cardview.widget.CardView.class))),
                        isDisplayed()));
        textView26.check(matches(withText("Open")));

        ViewInteraction textView27 = onView(
                allOf(withId(R.id.description_text_view), withText("descriptionw"),
                        withParent(allOf(withId(R.id.description_material_card_view),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        textView27.check(matches(withText("descriptionw")));

        ViewInteraction textView28 = onView(
                allOf(withId(R.id.create_data_text_view), withText("26.05.2023"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.cardview.widget.CardView.class))),
                        isDisplayed()));
        textView28.check(matches(withText("26.05.2023")));

        ViewInteraction imageButton4 = onView(
                allOf(withId(R.id.status_processing_image_button), withContentDescription("button change status"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.cardview.widget.CardView.class))),
                        isDisplayed()));
        imageButton4.check(matches(isDisplayed()));

        ViewInteraction imageButton5 = onView(
                allOf(withId(R.id.edit_processing_image_button), withContentDescription("button settings"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.cardview.widget.CardView.class))),
                        isDisplayed()));
        imageButton5.check(matches(isDisplayed()));

        ViewInteraction appCompatImageButton15 = onView(
                allOf(withId(R.id.edit_processing_image_button), withContentDescription("button settings"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                25),
                        isDisplayed()));
        appCompatImageButton15.perform(click());

        ViewInteraction textInputEditText18 = onView(
                allOf(withId(R.id.description_edit_text), withText("descriptionw"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.description_text_input_layout),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText18.perform(replaceText("description edite3"));

        ViewInteraction textInputEditText19 = onView(
                allOf(withId(R.id.description_edit_text), withText("description edite3"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.description_text_input_layout),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText19.perform(closeSoftKeyboard());

        ViewInteraction textInputEditText20 = onView(
                allOf(withId(R.id.title_edit_text), withText("claim title number 2"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.title_text_input_layout),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText20.perform(replaceText("claim title number 2d edited"));

        ViewInteraction textInputEditText21 = onView(
                allOf(withId(R.id.title_edit_text), withText("claim title number 2d edited"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.title_text_input_layout),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText21.perform(closeSoftKeyboard());

        ViewInteraction materialButton35 = onView(
                allOf(withId(R.id.save_button), withText("Save"), withContentDescription("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                6)));
        materialButton35.perform(scrollTo(), click());

        ViewInteraction textView29 = onView(
                allOf(withId(R.id.title_text_view), withText("claim title number 2d edited"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.cardview.widget.CardView.class))),
                        isDisplayed()));
        textView29.check(matches(withText("claim title number 2d edited")));

        ViewInteraction textView30 = onView(
                allOf(withId(R.id.description_text_view), withText("description edite3"),
                        withParent(allOf(withId(R.id.description_material_card_view),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        textView30.check(matches(withText("description edite3")));

        ViewInteraction appCompatImageButton16 = onView(
                allOf(withId(R.id.status_processing_image_button), withContentDescription("button change status"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                24),
                        isDisplayed()));
        appCompatImageButton16.perform(click());

        ViewInteraction materialTextView7 = onView(
                allOf(withId(android.R.id.title), withText("take to work"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        materialTextView7.perform(click());

        ViewInteraction textView31 = onView(
                allOf(withId(R.id.executor_name_text_view), withText("Ivanov Ivan Ivanovich"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.cardview.widget.CardView.class))),
                        isDisplayed()));
        textView31.check(matches(withText("Ivanov Ivan Ivanovich")));

        ViewInteraction textView32 = onView(
                allOf(withId(R.id.status_label_text_view), withText("In progress"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.cardview.widget.CardView.class))),
                        isDisplayed()));
        textView32.check(matches(withText("In progress")));

        ViewInteraction appCompatImageButton17 = onView(
                allOf(withId(R.id.status_processing_image_button), withContentDescription("button change status"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                24),
                        isDisplayed()));
        appCompatImageButton17.perform(click());

        ViewInteraction materialTextView8 = onView(
                allOf(withId(android.R.id.title), withText("Throw off"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        materialTextView8.perform(click());

        ViewInteraction textInputEditText22 = onView(
                allOf(withId(R.id.editText),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.textfield.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText22.perform(replaceText("throwen off"), closeSoftKeyboard());

        ViewInteraction materialButton36 = onView(
                allOf(withId(android.R.id.button2), withText("Cancel"),
                        childAtPosition(
                                childAtPosition(
                                        withId(com.google.android.material.R.id.buttonPanel),
                                        0),
                                2)));
        materialButton36.perform(scrollTo(), click());

        ViewInteraction textView33 = onView(
                allOf(withId(R.id.status_label_text_view), withText("In progress"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.cardview.widget.CardView.class))),
                        isDisplayed()));
        textView33.check(matches(withText("In progress")));

        ViewInteraction appCompatImageButton18 = onView(
                allOf(withId(R.id.status_processing_image_button), withContentDescription("button change status"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                24),
                        isDisplayed()));
        appCompatImageButton18.perform(click());

        ViewInteraction materialTextView9 = onView(
                allOf(withId(android.R.id.title), withText("Throw off"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        materialTextView9.perform(click());

        ViewInteraction textInputEditText23 = onView(
                allOf(withId(R.id.editText),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.textfield.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText23.perform(replaceText("throwen off"), closeSoftKeyboard());

        ViewInteraction materialButton37 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(com.google.android.material.R.id.buttonPanel),
                                        0),
                                3)));
        materialButton37.perform(scrollTo(), click());

        ViewInteraction textView34 = onView(
                allOf(withId(R.id.status_label_text_view), withText("Open"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.cardview.widget.CardView.class))),
                        isDisplayed()));
        textView34.check(matches(withText("Open")));

        ViewInteraction textView35 = onView(
                allOf(withId(R.id.comment_description_text_view), withText("throwen off"),
                        withParent(withParent(withId(R.id.claim_comments_list_recycler_view))),
                        isDisplayed()));
        textView35.check(matches(withText("throwen off")));

        ViewInteraction appCompatImageButton19 = onView(
                allOf(withId(R.id.status_processing_image_button), withContentDescription("button change status"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                24),
                        isDisplayed()));
        appCompatImageButton19.perform(click());

        ViewInteraction materialTextView10 = onView(
                allOf(withId(android.R.id.title), withText("Cancel"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        materialTextView10.perform(click());

        ViewInteraction textView36 = onView(
                allOf(withId(R.id.status_label_text_view), withText("Canceled"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.cardview.widget.CardView.class))),
                        isDisplayed()));
        textView36.check(matches(withText("Canceled")));

        ViewInteraction appCompatImageButton20 = onView(
                allOf(withId(R.id.add_comment_image_button), withContentDescription("button add comment"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.comments_material_card_view),
                                        0),
                                2),
                        isDisplayed()));
        appCompatImageButton20.perform(click());

        ViewInteraction materialButton38 = onView(
                allOf(withId(R.id.save_button), withText("Save"), withContentDescription("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                1)));
        materialButton38.perform(scrollTo(), click());

        ViewInteraction materialButton39 = onView(
                allOf(withId(R.id.cancel_button), withText("Cancel"), withContentDescription("Cancel"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                2)));
        materialButton39.perform(scrollTo(), click());

        ViewInteraction appCompatImageButton21 = onView(
                allOf(withId(R.id.close_image_button), withContentDescription("button close"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                23),
                        isDisplayed()));
        appCompatImageButton21.perform(click());

        ViewInteraction recyclerView11 = onView(
                allOf(withId(R.id.claim_list_recycler_view),
                        childAtPosition(
                                withId(R.id.all_claims_cards_block_constraint_layout),
                                4)));
        recyclerView11.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction appCompatImageButton22 = onView(
                allOf(withId(R.id.edit_processing_image_button), withContentDescription("button settings"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                25),
                        isDisplayed()));
        appCompatImageButton22.perform(click());

        ViewInteraction textInputEditText24 = onView(
                allOf(withId(R.id.title_edit_text), withText("J. R. R. Tolkien 622"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.title_text_input_layout),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText24.perform(replaceText(""));

        ViewInteraction textInputEditText25 = onView(
                allOf(withId(R.id.title_edit_text),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.title_text_input_layout),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText25.perform(closeSoftKeyboard());

        ViewInteraction materialButton40 = onView(
                allOf(withId(R.id.save_button), withText("Save"), withContentDescription("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                6)));
        materialButton40.perform(scrollTo(), click());

        ViewInteraction textView37 = onView(
                allOf(withId(android.R.id.message), withText("Fill empty fields"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
                        isDisplayed()));
        textView37.check(matches(withText("Fill empty fields")));

        ViewInteraction button7 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
                        isDisplayed()));
        button7.check(matches(isDisplayed()));

        ViewInteraction materialButton41 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton41.perform(scrollTo(), click());

        ViewInteraction materialButton42 = onView(
                allOf(withId(R.id.cancel_button), withText("Cancel"), withContentDescription("Cancel"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                7)));
        materialButton42.perform(scrollTo(), click());

        ViewInteraction materialButton43 = onView(
                allOf(withId(android.R.id.button2), withText("Cancel"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        materialButton43.perform(scrollTo(), click());

        ViewInteraction materialButton44 = onView(
                allOf(withId(R.id.cancel_button), withText("Cancel"), withContentDescription("Cancel"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                7)));
        materialButton44.perform(scrollTo(), click());

        ViewInteraction materialButton45 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton45.perform(scrollTo(), click());

        ViewInteraction appCompatImageButton23 = onView(
                allOf(withId(R.id.edit_processing_image_button), withContentDescription("button settings"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                25),
                        isDisplayed()));
        appCompatImageButton23.perform(click());

        ViewInteraction materialButton46 = onView(
                allOf(withId(R.id.cancel_button), withText("Cancel"), withContentDescription("Cancel"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                7)));
        materialButton46.perform(scrollTo(), click());

        ViewInteraction materialButton47 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton47.perform(scrollTo(), click());

        ViewInteraction appCompatImageButton24 = onView(
                allOf(withId(R.id.close_image_button), withContentDescription("button close"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                23),
                        isDisplayed()));
        appCompatImageButton24.perform(click());

        ViewInteraction materialButton48 = onView(
                allOf(withId(R.id.add_new_claim_material_button), withContentDescription("Add new claim button"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container_list_claim_include),
                                        0),
                                2),
                        isDisplayed()));
        materialButton48.perform(click());

        ViewInteraction materialButton49 = onView(
                allOf(withId(R.id.save_button), withText("Save"), withContentDescription("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                6)));
        materialButton49.perform(scrollTo(), click());

        ViewInteraction textView38 = onView(
                allOf(withId(android.R.id.message), withText("Fill empty fields"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
                        isDisplayed()));
        textView38.check(matches(withText("Fill empty fields")));

        ViewInteraction materialButton50 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton50.perform(scrollTo(), click());

        ViewInteraction textInputEditText26 = onView(
                allOf(withId(R.id.title_edit_text),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.title_text_input_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText26.perform(replaceText("vs k.,bv ;bdjnys[b cnfhftv jlhbf [r k.,bv k.,bv k."), closeSoftKeyboard());

        ViewInteraction textView39 = onView(
                allOf(withId(com.google.android.material.R.id.textinput_counter), withText("50/50"), withContentDescription("Characters entered 50 of 50"),
                        withParent(withParent(withId(R.id.title_text_input_layout))),
                        isDisplayed()));
        textView39.check(matches(withText("50/50")));
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
