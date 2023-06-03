package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

import ru.iteco.fmhandroid.R;

public class AboutPage {

    public static void getAboutPagesObjects(){
        onView(allOf(withId(R.id.about_version_value_text_view))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.about_privacy_policy_label_text_view))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.about_privacy_policy_value_text_view))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.about_terms_of_use_label_text_view))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.about_terms_of_use_value_text_view))).check(matches(isDisplayed()));
    }
}
