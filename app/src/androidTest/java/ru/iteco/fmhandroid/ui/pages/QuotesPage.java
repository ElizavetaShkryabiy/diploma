package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

import ru.iteco.fmhandroid.R;

public class QuotesPage {

    public static void goToQuotesPage(){
        onView(allOf(withId(R.id.trademark_image_view))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.our_mission_image_button))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.authorization_image_button))).check(matches(isDisplayed()));

        onView(allOf(withId(R.id.our_mission_image_button))).perform(click());

        onView(allOf(withId(R.id.our_mission_item_list_recycler_view))).perform(actionOnItemAtPosition(0, click()));

        onView(allOf(withId(R.id.our_mission_title_text_view))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.our_mission_item_title_text_view))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.our_mission_item_description_text_view))).check(matches(isDisplayed()));
    }
}
