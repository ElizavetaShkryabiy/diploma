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
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
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
public class NewsPageTests {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Test
    public void newsPageTests() {
        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.login_text_input_layout),
                        isDisplayed()));
        textInputEditText.perform(replaceText("login2"), closeSoftKeyboard());

        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.password_text_input_layout),
                        isDisplayed()));
        textInputEditText2.perform(replaceText("password2"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.enter_button), withContentDescription("Save"),isDisplayed()));
        materialButton.perform(click());

        ViewInteraction materialTextView = onView(
                allOf(withId(R.id.all_news_text_view),
                        isDisplayed()));
        materialTextView.perform(click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.news_list_recycler_view),
                        childAtPosition(
                                withId(R.id.all_news_cards_block_constraint_layout),
                                0)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction textView = onView(
                allOf(withId(R.id.news_item_description_text_view)));
        textView.check(matches(isDisplayed()));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.news_item_title_text_view)));
        textView2.check(matches(isDisplayed()));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.news_item_date_text_view), withText("20.05.2023"),
                        isDisplayed()));

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.sort_news_material_button), withContentDescription("Sort news list button"),
                        isDisplayed()));
        materialButton2.perform(click()).perform(scrollToPosition(0));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.news_item_date_text_view)));
        textView4.check(matches(not(withText("20.05.2023"))));

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.filter_news_material_button)));
        materialButton3.perform(click());

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.filter_news_title_text_view),
                        isDisplayed()));
        textView5.check(matches(withText("Filter news")));

        ViewInteraction editText = onView(
                allOf(withId(R.id.news_item_category_text_auto_complete_text_view)));
        editText.check(matches(isDisplayed()));

        ViewInteraction button = onView(
                allOf(withId(R.id.filter_button)));
        button.check(matches(isDisplayed()));

        ViewInteraction button2 = onView(
                allOf(withId(R.id.cancel_button)));
        button2.check(matches(isDisplayed()));

        ViewInteraction materialAutoCompleteTextView = onView(
                allOf(withId(R.id.news_item_category_text_auto_complete_text_view)));
        materialAutoCompleteTextView.perform(click());

        DataInteraction materialTextView2 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(5);
        materialTextView2.perform(click());

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.news_item_category_text_auto_complete_text_view), withText("День Рождения")));
        editText2.check(matches(withText("День Рождения")));

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.filter_button)));
        materialButton4.perform(click());

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.news_list_recycler_view)));
        recyclerView2.perform(actionOnItemAtPosition(3, click()));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.news_item_title_text_view), withText("������"),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()));
        textView6.check(matches(withText("������")));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.news_item_title_text_view), withText("������"),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()));
        textView7.check(matches(withText("������")));

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.filter_news_material_button)));
        materialButton5.perform(click());

        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.news_item_publish_date_start_text_input_edit_text)));
        textInputEditText3.perform(click());

        ViewInteraction materialButton6 = onView(
                allOf(withId(android.R.id.button1), withText("OK")));
        materialButton6.perform(scrollTo(), click());

        ViewInteraction textInputEditText4 = onView(
                allOf(withId(R.id.news_item_publish_date_end_text_input_edit_text)));
        textInputEditText4.perform(click());

        ViewInteraction materialButton7 = onView(
                allOf(withId(android.R.id.button2), withText("Cancel")));
        materialButton7.perform(scrollTo(), click());

        ViewInteraction textInputEditText5 = onView(
                allOf(withId(R.id.news_item_publish_date_start_text_input_edit_text), withText("18.05.2023")));
        textInputEditText5.perform(click());

        ViewInteraction appCompatImageButton = onView(
                allOf(withClassName(is("androidx.appcompat.widget.AppCompatImageButton")), withContentDescription("Previous month")));
        appCompatImageButton.perform(scrollTo(), click());

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withClassName(is("androidx.appcompat.widget.AppCompatImageButton")), withContentDescription("Previous month")));
        appCompatImageButton2.perform(scrollTo(), click());

        ViewInteraction materialButton8 = onView(
                allOf(withId(android.R.id.button1), withText("OK")));
        materialButton8.perform(scrollTo(), click());

        ViewInteraction textInputEditText6 = onView(
                allOf(withId(R.id.news_item_publish_date_end_text_input_edit_text)));
        textInputEditText6.perform(click());

        ViewInteraction materialButton9 = onView(
                allOf(withId(android.R.id.button1), withText("OK")));
        materialButton9.perform(scrollTo(), click());

        ViewInteraction materialButton10 = onView(
                allOf(withId(R.id.filter_button)));
        materialButton10.perform(click());

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.empty_news_list_text_view), withText("There is nothing here yet�"),
                        isDisplayed()));
        textView8.check(matches(withText("There is nothing here yet�")));

        ViewInteraction button3 = onView(
                allOf(withId(R.id.news_retry_material_button),
                        isDisplayed()));
        button3.check(matches(isDisplayed()));

        ViewInteraction materialButton11 = onView(
                allOf(withId(R.id.news_retry_material_button)));
        materialButton11.perform(click());

        ViewInteraction materialButton12 = onView(
                allOf(withId(R.id.filter_news_material_button)));
        materialButton12.perform(click());

        ViewInteraction materialAutoCompleteTextView2 = onView(
                allOf(withId(R.id.news_item_category_text_auto_complete_text_view)));
        materialAutoCompleteTextView2.perform(click());

        ViewInteraction materialAutoCompleteTextView3 = onView(
                allOf(withId(R.id.news_item_category_text_auto_complete_text_view)));
        materialAutoCompleteTextView3.perform(click());

        ViewInteraction materialButton13 = onView(
                allOf(withId(R.id.cancel_button)));
        materialButton13.perform(click());

        ViewInteraction appCompatImageButton3 = onView(
                allOf(withId(R.id.main_menu_image_button), withContentDescription("Main menu")));
        appCompatImageButton3.perform(click());

        ViewInteraction materialTextView3 = onView(
                allOf(withId(android.R.id.title), withText("Claims")));
        materialTextView3.perform(click());

        ViewInteraction appCompatImageButton4 = onView(
                allOf(withId(R.id.main_menu_image_button), withContentDescription("Main menu")));
        appCompatImageButton4.perform(click());

        ViewInteraction materialTextView4 = onView(
                allOf(withId(android.R.id.title), withText("News"),
                        isDisplayed()));
        materialTextView4.perform(click());

        ViewInteraction materialButton14 = onView(
                allOf(withId(R.id.filter_news_material_button)));
        materialButton14.perform(click());

        ViewInteraction textInputEditText7 = onView(
                allOf(withId(R.id.news_item_publish_date_start_text_input_edit_text)));
        textInputEditText7.perform(click());

        ViewInteraction appCompatImageButton5 = onView(
                allOf(withClassName(is("androidx.appcompat.widget.AppCompatImageButton")), withContentDescription("Previous month")));
        appCompatImageButton5.perform(scrollTo(), click());

        ViewInteraction appCompatImageButton6 = onView(
                allOf(withClassName(is("androidx.appcompat.widget.AppCompatImageButton")), withContentDescription("Previous month")));
        appCompatImageButton6.perform(scrollTo(), click());

        ViewInteraction materialButton15 = onView(
                allOf(withId(android.R.id.button1), withText("OK")));
        materialButton15.perform(scrollTo(), click());

        ViewInteraction textInputEditText8 = onView(
                allOf(withId(R.id.news_item_publish_date_end_text_input_edit_text)));
        textInputEditText8.perform(click());

        ViewInteraction materialButton16 = onView(
                allOf(withId(android.R.id.button1)));
        materialButton16.perform(scrollTo(), click());

        ViewInteraction materialButton17 = onView(
                allOf(withId(R.id.filter_button)));
        materialButton17.perform(click());

        ViewInteraction materialButton18 = onView(
                allOf(withId(R.id.filter_news_material_button)));
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

        ViewInteraction materialButton21 = onView(
                allOf(withId(R.id.filter_button)));
        materialButton21.perform(click());

        ViewInteraction recyclerView3 = onView(
                allOf(withId(R.id.news_list_recycler_view)));
        recyclerView3.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction textView9 = onView(
                allOf(withId(R.id.news_item_date_text_view), withText("19.05.2023"),
                        isDisplayed()));

        ViewInteraction materialButton22 = onView(
                allOf(withId(R.id.filter_news_material_button)));
        materialButton22.perform(click());

        ViewInteraction textInputEditText11 = onView(
                allOf(withId(R.id.news_item_publish_date_start_text_input_edit_text)));
        textInputEditText11.perform(click());

        ViewInteraction materialButton23 = onView(
                allOf(withId(android.R.id.button1)));
        materialButton23.perform(scrollTo(), click());

        ViewInteraction textInputEditText12 = onView(
                allOf(withId(R.id.news_item_publish_date_end_text_input_edit_text)));
        textInputEditText12.perform(click());

        ViewInteraction materialButton24 = onView(
                allOf(withId(android.R.id.button1)));
        materialButton24.perform(scrollTo(), click());

        ViewInteraction materialButton25 = onView(
                allOf(withId(R.id.filter_button)));
        materialButton25.perform(click());

        ViewInteraction materialButton26 = onView(
                allOf(withId(R.id.filter_news_material_button)));
        materialButton26.perform(click());

        ViewInteraction textInputEditText13 = onView(
                allOf(withId(R.id.news_item_publish_date_start_text_input_edit_text)));
        textInputEditText13.perform(click());

        ViewInteraction materialButton27 = onView(
                allOf(withId(android.R.id.button1)));
        materialButton27.perform(scrollTo(), click());

        ViewInteraction textInputEditText14 = onView(
                allOf(withId(R.id.news_item_publish_date_end_text_input_edit_text)));
        textInputEditText14.perform(click());

        ViewInteraction materialButton28 = onView(
                allOf(withId(android.R.id.button1)));
        materialButton28.perform(scrollTo(), click());

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.news_item_publish_date_end_text_input_edit_text), withText("16.05.2023"),
                        withParent(withParent(withId(R.id.news_item_publish_date_end_text_input_layout))),
                        isDisplayed()));
        editText3.check(matches(withText("20.05.2023")));

        ViewInteraction textInputEditText15 = onView(
                allOf(withId(R.id.news_item_publish_date_end_text_input_edit_text), withText("16.05.2023")));
        textInputEditText15.perform(click());

        ViewInteraction materialButton29 = onView(
                allOf(withId(android.R.id.button1), withText("OK")));
        materialButton29.perform(scrollTo(), click());

        ViewInteraction textInputEditText16 = onView(
                allOf(withId(R.id.news_item_publish_date_start_text_input_edit_text), withText("18.05.2023")));
        textInputEditText16.perform(click());

        ViewInteraction materialButton30 = onView(
                allOf(withId(android.R.id.button1)));
        materialButton30.perform(scrollTo(), click());

        ViewInteraction materialAutoCompleteTextView4 = onView(
                allOf(withId(R.id.news_item_category_text_auto_complete_text_view)));
        materialAutoCompleteTextView4.perform(click());

        DataInteraction materialTextView5 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(1);
        materialTextView5.perform(click());

        ViewInteraction materialButton31 = onView(
                allOf(withId(R.id.filter_button)));
        materialButton31.perform(click());

        ViewInteraction imageView = onView(
                allOf(withId(R.id.category_icon_image_view), withContentDescription("News category icon")));
        imageView.check(matches(isDisplayed()));

        ViewInteraction materialButton32 = onView(
                allOf(withId(R.id.filter_news_material_button)));
        materialButton32.perform(click());

        ViewInteraction editText5 = onView(
                allOf(withId(R.id.news_item_publish_date_start_text_input_edit_text), withText("DD.MM.YYYY")));
        editText5.check(matches(withText("16.05.2023")));

        ViewInteraction editText6 = onView(
                allOf(withId(R.id.news_item_publish_date_end_text_input_edit_text), withText("DD.MM.YYYY")));
        editText6.check(matches(withText("20.05.2023")));

        ViewInteraction materialButton33 = onView(
                allOf(withId(R.id.cancel_button)));
        materialButton33.perform(click());

        ViewInteraction textView10 = onView(
                allOf(withText("News"),
                        withParent(withParent(withId(R.id.container_list_news_include))),
                        isDisplayed()));
        textView10.check(matches(isDisplayed()));

        ViewInteraction materialButton34 = onView(
                allOf(withId(R.id.edit_news_material_button)));
        materialButton34.perform(click());

        ViewInteraction textView11 = onView(
                allOf(withText("Control panel"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        textView11.check(matches(withText("Control panel")));

        ViewInteraction compoundButton = onView(
                allOf(withId(R.id.sort_news_material_button),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        compoundButton.check(matches(isDisplayed()));

        ViewInteraction compoundButton2 = onView(
                allOf(withId(R.id.filter_news_material_button),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        compoundButton2.check(matches(isDisplayed()));

        ViewInteraction button4 = onView(
                allOf(withId(R.id.add_news_image_view), withContentDescription("Add news button")));
        button4.check(matches(isDisplayed()));

        ViewInteraction cardView = onView(
                allOf(withId(R.id.news_item_material_card_view)));
        cardView.check(matches(isDisplayed()));

        ViewInteraction textView12 = onView(
                allOf(withId(R.id.news_item_publication_date_text_view), withText("01.09.2023")));
        textView12.check(matches(withText("01.09.2023")));

        ViewInteraction materialButton35 = onView(
                allOf(withId(R.id.sort_news_material_button),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                1),
                        isDisplayed()));
        materialButton35.perform(click());

        ViewInteraction recyclerView4 = onView(
                allOf(withId(R.id.news_list_recycler_view),
                        childAtPosition(
                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                0)));
        recyclerView4.perform(actionOnItemAtPosition(306, longClick()));

        ViewInteraction textView13 = onView(
                allOf(withId(R.id.news_item_publication_date_text_view), withText("01.01.001"),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()));
        textView13.check(matches(withText("01.01.001")));

        ViewInteraction materialButton36 = onView(
                allOf(withId(R.id.filter_news_material_button),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                2),
                        isDisplayed()));
        materialButton36.perform(click());

        ViewInteraction textView14 = onView(
                allOf(withId(R.id.filter_news_title_text_view), withText("Filter news"),
                        withParent(withParent(withId(R.id.nav_host_fragment))),
                        isDisplayed()));
        textView14.check(matches(isDisplayed()));

        ViewInteraction editText7 = onView(
                allOf(withId(R.id.news_item_publish_date_end_text_input_edit_text), withText("DD.MM.YYYY"),
                        withParent(withParent(withId(R.id.news_item_publish_date_end_text_input_layout))),
                        isDisplayed()));
        editText7.check(matches(isDisplayed()));

        ViewInteraction checkBox = onView(
                allOf(withId(R.id.filter_news_inactive_material_check_box), withText("Not active"),
                        withParent(withParent(withId(R.id.nav_host_fragment))),
                        isDisplayed()));
        checkBox.check(matches(isDisplayed()));

        ViewInteraction button5 = onView(
                allOf(withId(R.id.filter_button), withText("FILTER"),
                        withParent(withParent(withId(R.id.nav_host_fragment))),
                        isDisplayed()));
        button5.check(matches(isDisplayed()));

        ViewInteraction button6 = onView(
                allOf(withId(R.id.cancel_button), withText("CANCEL"),
                        withParent(withParent(withId(R.id.nav_host_fragment))),
                        isDisplayed()));
        button6.check(matches(isDisplayed()));

        ViewInteraction materialAutoCompleteTextView5 = onView(
                allOf(withId(R.id.news_item_category_text_auto_complete_text_view),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.news_item_category_text_input_layout),
                                        0),
                                0),
                        isDisplayed()));
        materialAutoCompleteTextView5.perform(click());

        DataInteraction materialTextView6 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(2);
        materialTextView6.perform(click());

        ViewInteraction editText8 = onView(
                allOf(withId(R.id.news_item_category_text_auto_complete_text_view), withText("��������"),
                        withParent(withParent(withId(R.id.news_item_category_text_input_layout))),
                        isDisplayed()));
        editText8.check(matches(withText("��������")));
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
