package ru.iteco.fmhandroid.ui;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
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
import static org.hamcrest.core.IsNot.not;

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
public class News2Tests {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Test
    public void news2Tests() {
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
                allOf(withId(R.id.all_news_text_view), withText("All news"),
                        childAtPosition(
                                allOf(withId(R.id.container_list_news_include_on_fragment_main),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        materialTextView.perform(click());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.edit_news_material_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container_list_news_include),
                                        0),
                                3),
                        isDisplayed()));
        materialButton2.perform(click());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.sort_news_material_button),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                1),
                        isDisplayed()));
        materialButton3.perform(click());

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.filter_news_material_button),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                2),
                        isDisplayed()));
        materialButton4.perform(click());

        ViewInteraction checkableImageButton = onView(
                allOf(withId(com.google.android.material.R.id.text_input_end_icon), withContentDescription("Show dropdown menu"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                0),
                        isDisplayed()));
        checkableImageButton.perform(click());

        DataInteraction materialTextView2 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(0);
        materialTextView2.perform(click());

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.filter_button), withText("Filter"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                6),
                        isDisplayed()));
        materialButton5.perform(click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.news_list_recycler_view),
                        childAtPosition(
                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                0)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction textView = onView(
                allOf(withId(R.id.news_item_title_text_view), withText("����������"),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()));
        textView.check(matches(withText("obyavlenye")));

        ViewInteraction materialButton6 = onView(
                allOf(withId(R.id.filter_news_material_button),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                2),
                        isDisplayed()));
        materialButton6.perform(click());

        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.news_item_publish_date_start_text_input_edit_text),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.news_item_publish_date_start_text_input_layout),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText3.perform(click());

        ViewInteraction materialButton7 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton7.perform(scrollTo(), click());

        ViewInteraction textInputEditText4 = onView(
                allOf(withId(R.id.news_item_publish_date_end_text_input_edit_text),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.news_item_publish_date_end_text_input_layout),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText4.perform(click());

        ViewInteraction materialButton8 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton8.perform(scrollTo(), click());

        ViewInteraction materialButton9 = onView(
                allOf(withId(R.id.filter_button), withText("Filter"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                6),
                        isDisplayed()));
        materialButton9.perform(click());

        ViewInteraction materialButton10 = onView(
                allOf(withId(R.id.filter_news_material_button),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                2),
                        isDisplayed()));
        materialButton10.perform(click());

        ViewInteraction textInputEditText5 = onView(
                allOf(withId(R.id.news_item_publish_date_start_text_input_edit_text),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.news_item_publish_date_start_text_input_layout),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText5.perform(click());

        ViewInteraction materialButton11 = onView(
                allOf(withId(android.R.id.button1)));
        materialButton11.perform(scrollTo(), click());

        ViewInteraction textInputEditText6 = onView(
                allOf(withId(R.id.news_item_publish_date_end_text_input_edit_text)));
        textInputEditText6.perform(click());

        ViewInteraction materialButton12 = onView(
                allOf(withId(android.R.id.button1)));
        materialButton12.perform(scrollTo(), click());

        ViewInteraction editText = onView(
                allOf(withId(R.id.news_item_publish_date_end_text_input_edit_text), withText("21.05.2023")));
        editText.check(matches(withText("21.05.2023")));

        ViewInteraction materialButton13 = onView(
                allOf(withId(R.id.filter_button)));
        materialButton13.perform(click());

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.control_panel_empty_news_list_text_view), withText("There is nothing here yet�")));
        textView2.check(doesNotExist());

        ViewInteraction materialButton14 = onView(
                allOf(withId(R.id.filter_news_material_button)));
        materialButton14.perform(click());

        ViewInteraction textInputEditText7 = onView(
                allOf(withId(R.id.news_item_publish_date_start_text_input_edit_text)));
        textInputEditText7.perform(click());

        ViewInteraction materialButton15 = onView(
                allOf(withId(android.R.id.button1)));
        materialButton15.perform(scrollTo(), click());

        ViewInteraction textInputEditText8 = onView(
                allOf(withId(R.id.news_item_publish_date_end_text_input_edit_text)));
        textInputEditText8.perform(click());

        ViewInteraction materialButton16 = onView(
                allOf(withId(android.R.id.button1)));
        materialButton16.perform(scrollTo(), click());

        ViewInteraction materialCheckBox = onView(
                allOf(withId(R.id.filter_news_inactive_material_check_box), withText("Not active")));
        materialCheckBox.perform(click());

        ViewInteraction materialButton17 = onView(
                allOf(withId(R.id.filter_button)));
        materialButton17.perform(click());

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.news_list_recycler_view),
                        childAtPosition(
                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                0)));
        recyclerView2.perform(actionOnItemAtPosition(1, longClick()));

        ViewInteraction materialButton18 = onView(
                allOf(withId(R.id.filter_news_material_button),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                2),
                        isDisplayed()));
        materialButton18.perform(click());

        ViewInteraction textInputEditText9 = onView(
                allOf(withId(R.id.news_item_publish_date_start_text_input_edit_text)));
        textInputEditText9.perform(click());

        ViewInteraction materialButton19 = onView(
                allOf(withId(android.R.id.button1)));
        materialButton19.perform(scrollTo(), click());

        ViewInteraction textInputEditText10 = onView(
                allOf(withId(R.id.news_item_publish_date_end_text_input_edit_text)));
        textInputEditText10.perform(click());

        ViewInteraction materialButton20 = onView(
                allOf(withId(android.R.id.button1)));
        materialButton20.perform(scrollTo(), click());

        ViewInteraction materialCheckBox2 = onView(
                allOf(withId(R.id.filter_news_active_material_check_box), withText("Active")));
        materialCheckBox2.perform(click());

        ViewInteraction materialButton21 = onView(
                allOf(withId(R.id.filter_button)));
        materialButton21.perform(click());

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.news_item_published_text_view), withText("NOT ACTIVE")));
        textView3.check(matches(withText("NOT ACTIVE")));

        ViewInteraction materialButton22 = onView(
                allOf(withId(R.id.filter_news_material_button)));
        materialButton22.perform(click());

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.news_item_publish_date_start_text_input_edit_text), withText("DD.MM.YYYY"),
                        withParent(withParent(withId(R.id.news_item_publish_date_start_text_input_layout))),
                        isDisplayed()));
        editText2.check(matches(not(withText("DD.MM.YYYY"))));

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.news_item_publish_date_end_text_input_edit_text), withText("DD.MM.YYYY"),
                        withParent(withParent(withId(R.id.news_item_publish_date_end_text_input_layout))),
                        isDisplayed()));
        editText3.check(matches(not(withText("DD.MM.YYYY"))));

        ViewInteraction materialButton23 = onView(
                allOf(withId(R.id.cancel_button)));
        materialButton23.perform(click());

        ViewInteraction textView4 = onView(
                allOf(withText("Control panel"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        textView4.check(matches(withText("Control panel")));

        ViewInteraction materialButton24 = onView(
                allOf(withId(R.id.add_news_image_view), withContentDescription("Add news button"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                3),
                        isDisplayed()));
        materialButton24.perform(click());

        ViewInteraction materialAutoCompleteTextView = onView(
                allOf(withId(R.id.news_item_category_text_auto_complete_text_view)));
        materialAutoCompleteTextView.perform(click());

        DataInteraction materialTextView3 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(0);
        materialTextView3.perform(click());

        ViewInteraction textInputEditText11 = onView(
                allOf(withId(R.id.news_item_title_text_input_edit_text),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.news_item_title_text_input_layout),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText11.perform(replaceText("alert case number1"));

        ViewInteraction textInputEditText12 = onView(
                allOf(withId(R.id.news_item_title_text_input_edit_text), withText("alert case number1"),
                        isDisplayed()));
        textInputEditText12.perform(closeSoftKeyboard());

        ViewInteraction textInputEditText13 = onView(
                allOf(withId(R.id.news_item_publish_date_text_input_edit_text),
                        isDisplayed()));
        textInputEditText13.perform(click());

        ViewInteraction materialButton25 = onView(
                allOf(withId(android.R.id.button1)));
        materialButton25.perform(scrollTo(), click());

        ViewInteraction textInputEditText14 = onView(
                allOf(withId(R.id.news_item_publish_time_text_input_edit_text)));
        textInputEditText14.perform(click());

        ViewInteraction materialButton26 = onView(
                allOf(withId(android.R.id.button1)));
        materialButton26.perform(scrollTo(), click());

        ViewInteraction textInputEditText15 = onView(
                allOf(withId(R.id.news_item_description_text_input_edit_text)));
        textInputEditText15.perform(replaceText("description for nes case number 1"), closeSoftKeyboard());

        ViewInteraction materialButton27 = onView(
                allOf(withId(R.id.save_button), withContentDescription("Save")));
        materialButton27.perform(scrollTo(), click());

        ViewInteraction materialButton29 = onView(
                allOf(withId(R.id.filter_news_material_button)));
        materialButton29.perform(click());

        ViewInteraction materialAutoCompleteTextView2 = onView(
                allOf(withId(R.id.news_item_category_text_auto_complete_text_view)));
        materialAutoCompleteTextView2.perform(click());

        DataInteraction materialTextView4 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(0);
        materialTextView4.perform(click());

        ViewInteraction textInputEditText16 = onView(
                allOf(withId(R.id.news_item_publish_date_start_text_input_edit_text)));
        textInputEditText16.perform(click());

        ViewInteraction materialButton30 = onView(
                allOf(withId(android.R.id.button1)));
        materialButton30.perform(scrollTo(), click());

        ViewInteraction materialCheckBox3 = onView(
                allOf(withId(R.id.filter_news_inactive_material_check_box), withText("Not active")));
        materialCheckBox3.perform(click());

        ViewInteraction materialButton31 = onView(
                allOf(withId(R.id.filter_button)));
        materialButton31.perform(click());

        ViewInteraction materialButton32 = onView(
                allOf(withId(android.R.id.button1)));
        materialButton32.perform(scrollTo(), click());

        ViewInteraction textInputEditText17 = onView(
                allOf(withId(R.id.news_item_publish_date_end_text_input_edit_text)));
        textInputEditText17.perform(click());

        ViewInteraction materialButton33 = onView(
                allOf(withId(android.R.id.button1)));
        materialButton33.perform(scrollTo(), click());

        ViewInteraction materialButton34 = onView(
                allOf(withId(R.id.filter_button)));
        materialButton34.perform(click());

        ViewInteraction recyclerView3 = onView(
                allOf(withId(R.id.news_list_recycler_view),
                        childAtPosition(
                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                0)));
        recyclerView3.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.news_item_title_text_view)));
        textView5.check(matches(withText("alert case number1")));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.news_item_description_text_view)));
        textView7.check(matches(withText("description for nes case number 1")));

        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.delete_news_item_image_view), withContentDescription("News delete button")));
        appCompatImageView.perform(click());

        ViewInteraction materialButton35 = onView(
                allOf(withId(android.R.id.button2)));
        materialButton35.perform(scrollTo(), click());

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.news_item_title_text_view), withText("alert case number1")));
        textView8.check(matches(withText("alert case number1")));

        ViewInteraction appCompatImageView2 = onView(
                allOf(withId(R.id.delete_news_item_image_view), withContentDescription("News delete button")));
        appCompatImageView2.perform(click());

        ViewInteraction materialButton36 = onView(
                allOf(withId(android.R.id.button1)));
        materialButton36.perform(scrollTo(), click());

        ViewInteraction materialButton37 = onView(
                allOf(withId(R.id.add_news_image_view), withContentDescription("Add news button")));
        materialButton37.perform(click());

        ViewInteraction textInputEditText18 = onView(
                allOf(withId(R.id.news_item_publish_date_text_input_edit_text)));
        textInputEditText18.perform(click());

        ViewInteraction materialButton38 = onView(
                allOf(withId(android.R.id.button1)));
        materialButton38.perform(scrollTo(), click());

        ViewInteraction textInputEditText19 = onView(
                allOf(withId(R.id.news_item_publish_time_text_input_edit_text)));
        textInputEditText19.perform(click());

        ViewInteraction materialButton39 = onView(
                allOf(withId(android.R.id.button1)));
        materialButton39.perform(scrollTo(), click());

        ViewInteraction materialButton40 = onView(
                allOf(withId(R.id.save_button)));
        materialButton40.perform(scrollTo(), click());

        ViewInteraction materialAutoCompleteTextView3 = onView(
                allOf(withId(R.id.news_item_category_text_auto_complete_text_view)));
        materialAutoCompleteTextView3.perform(click());

        DataInteraction materialTextView5 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(0);
        materialTextView5.perform(click());

        ViewInteraction textInputEditText20 = onView(
                allOf(withId(R.id.news_item_description_text_input_edit_text)));
        textInputEditText20.perform(replaceText("klkl"), closeSoftKeyboard());

        ViewInteraction materialButton41 = onView(
                allOf(withId(R.id.save_button), withContentDescription("Save")));
        materialButton41.perform(scrollTo(), click());

        ViewInteraction recyclerView4 = onView(
                allOf(withId(R.id.news_list_recycler_view)));
        recyclerView4.perform(actionOnItemAtPosition(1, longClick()));

        ViewInteraction materialButton42 = onView(
                allOf(withId(R.id.sort_news_material_button)));
        materialButton42.perform(click());

        ViewInteraction materialButton43 = onView(
                allOf(withId(R.id.sort_news_material_button)));
        materialButton43.perform(click());

        ViewInteraction materialButton44 = onView(
                allOf(withId(R.id.add_news_image_view), withContentDescription("Add news button")));
        materialButton44.perform(click());

        ViewInteraction materialButton45 = onView(
                allOf(withId(R.id.save_button), withContentDescription("Save")));
        materialButton45.perform(scrollTo(), click());

        ViewInteraction imageButton = onView(
                allOf(withId(com.google.android.material.R.id.text_input_start_icon),
                        isDisplayed()));
        imageButton.check(matches(isDisplayed()));

        ViewInteraction imageButton2 = onView(
                allOf(withId(com.google.android.material.R.id.text_input_end_icon),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        imageButton2.check(matches(isDisplayed()));

        ViewInteraction imageButton3 = onView(
                allOf(withId(com.google.android.material.R.id.text_input_end_icon),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        imageButton3.check(matches(isDisplayed()));

        ViewInteraction imageButton4 = onView(
                allOf(withId(com.google.android.material.R.id.text_input_end_icon),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        imageButton4.check(matches(isDisplayed()));

        ViewInteraction imageButton5 = onView(
                allOf(withId(com.google.android.material.R.id.text_input_end_icon),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        imageButton5.check(matches(isDisplayed()));

        ViewInteraction materialButton46 = onView(
                allOf(withId(R.id.cancel_button), withContentDescription("Cancel")));
        materialButton46.perform(scrollTo(), click());

        ViewInteraction materialButton47 = onView(
                allOf(withId(android.R.id.button2)));
        materialButton47.perform(scrollTo(), click());

        ViewInteraction textView10 = onView(
                allOf(withId(R.id.custom_app_bar_title_text_view), withText("Creating")));
        textView10.check(matches(withText("Creating")));

        ViewInteraction materialButton48 = onView(
                allOf(withId(R.id.cancel_button), withContentDescription("Cancel")));
        materialButton48.perform(scrollTo(), click());

        ViewInteraction materialButton49 = onView(
                allOf(withId(android.R.id.button1)));
        materialButton49.perform(scrollTo(), click());

        ViewInteraction textView11 = onView(
                allOf(withText("Control panel")));
        textView11.check(matches(withText("Control panel")));

        ViewInteraction appCompatImageView3 = onView(
                allOf(withId(R.id.edit_news_item_image_view), withContentDescription("News editing button")));
        appCompatImageView3.perform(click());

        ViewInteraction textInputEditText21 = onView(
                allOf(withId(R.id.news_item_title_text_input_edit_text),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.news_item_title_text_input_layout),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText21.perform(replaceText("editing text title"));

        ViewInteraction textInputEditText22 = onView(
                allOf(withId(R.id.news_item_title_text_input_edit_text), withText("editing text title"),
                        isDisplayed()));
        textInputEditText22.perform(closeSoftKeyboard());

        ViewInteraction materialButton50 = onView(
                allOf(withId(R.id.cancel_button), withContentDescription("Cancel")));
        materialButton50.perform(scrollTo(), click());

        ViewInteraction materialButton51 = onView(
                allOf(withId(android.R.id.button1)));
        materialButton51.perform(scrollTo(), click());

        ViewInteraction materialButton52 = onView(
                allOf(withId(R.id.add_news_image_view), withContentDescription("Add news button")));
        materialButton52.perform(click());

        ViewInteraction materialAutoCompleteTextView4 = onView(
                allOf(withId(R.id.news_item_category_text_auto_complete_text_view)));
        materialAutoCompleteTextView4.perform(click());

        DataInteraction materialTextView6 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(1);
        materialTextView6.perform(click());

        ViewInteraction textInputEditText23 = onView(
                allOf(withId(R.id.news_item_title_text_input_edit_text)));
        textInputEditText23.perform(replaceText("birthday title news"));

        ViewInteraction textInputEditText25 = onView(
                allOf(withId(R.id.news_item_title_text_input_edit_text), withText("birthday title news"),
                        isDisplayed()));
        textInputEditText25.perform(closeSoftKeyboard());

        ViewInteraction textInputEditText26 = onView(
                allOf(withId(R.id.news_item_publish_date_text_input_edit_text)));
        textInputEditText26.perform(click());

        ViewInteraction materialButton53 = onView(
                allOf(withId(android.R.id.button1)));
        materialButton53.perform(scrollTo(), click());

        ViewInteraction textInputEditText27 = onView(
                allOf(withId(R.id.news_item_publish_time_text_input_edit_text)));
        textInputEditText27.perform(click());

        ViewInteraction materialButton54 = onView(
                allOf(withId(android.R.id.button1)));
        materialButton54.perform(scrollTo(), click());

        ViewInteraction textInputEditText28 = onView(
                allOf(withId(R.id.news_item_description_text_input_edit_text)));
        textInputEditText28.perform(replaceText("description"), closeSoftKeyboard());

        ViewInteraction materialButton55 = onView(
                allOf(withId(R.id.save_button), withContentDescription("Save")));
        materialButton55.perform(scrollTo(), click());

        ViewInteraction recyclerView5 = onView(
                allOf(withId(R.id.news_list_recycler_view)));
        recyclerView5.perform(actionOnItemAtPosition(0, longClick()));


        ViewInteraction materialButton58 = onView(
                allOf(withId(R.id.filter_news_material_button)));
        materialButton58.perform(click());

        ViewInteraction textInputEditText29 = onView(
                allOf(withId(R.id.news_item_publish_date_start_text_input_edit_text)));
        textInputEditText29.perform(click());

        ViewInteraction materialButton59 = onView(
                allOf(withId(android.R.id.button1)));
        materialButton59.perform(scrollTo(), click());

        ViewInteraction textInputEditText30 = onView(
                allOf(withId(R.id.news_item_publish_date_end_text_input_edit_text)));
        textInputEditText30.perform(click());

        ViewInteraction materialButton60 = onView(
                allOf(withId(android.R.id.button1)));
        materialButton60.perform(scrollTo(), click());

        ViewInteraction materialButton61 = onView(
                allOf(withId(R.id.filter_button)));
        materialButton61.perform(click());

        ViewInteraction appCompatImageView4 = onView(
                allOf(withId(R.id.edit_news_item_image_view), withContentDescription("News editing button")));
        appCompatImageView4.perform(click());

        ViewInteraction textInputEditText31 = onView(
                allOf(withId(R.id.news_item_title_text_input_edit_text), withText("birthday title news")));
        textInputEditText31.perform(replaceText("birthday title news edit"));

        ViewInteraction textInputEditText32 = onView(
                allOf(withId(R.id.news_item_title_text_input_edit_text), withText("birthday title news edit"),
                        isDisplayed()));
        textInputEditText32.perform(closeSoftKeyboard());

        ViewInteraction materialButton62 = onView(
                allOf(withId(R.id.save_button)));
        materialButton62.perform(scrollTo(), click());

        ViewInteraction textView12 = onView(
                allOf(withId(R.id.news_item_title_text_view), withText("birthday title news edit")));
        textView12.check(matches(withText("birthday title news edit")));

        ViewInteraction appCompatImageView5 = onView(
                allOf(withId(R.id.edit_news_item_image_view), withContentDescription("News editing button")));
        appCompatImageView5.perform(click());

        ViewInteraction switchMaterial = onView(
                allOf(withId(R.id.switcher), withText("Active")));
        switchMaterial.perform(scrollTo(), click());

        ViewInteraction materialButton63 = onView(
                allOf(withId(R.id.save_button), withContentDescription("Save")));
        materialButton63.perform(scrollTo(), click());

        ViewInteraction textView13 = onView(
                allOf(withId(R.id.news_item_published_text_view), withText("NOT ACTIVE")));
        textView13.check(matches(withText("NOT ACTIVE")));
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
