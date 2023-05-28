package ru.iteco.fmhandroid.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.R;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class HeaderAndQuotesTests {
    MethodsClass methods = new MethodsClass();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Test
    public void quotesPagesTests()  {
//        checks everything present, just 2 actions
        methods.fillAuthFormFields("login2", "password2");
        onView(allOf(withId(R.id.trademark_image_view))).check(matches(isDisplayed()));

        methods.chooseMenuItem("Main");

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
