package ru.iteco.fmhandroid.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
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
public class AboutPageTest {
    MethodsClass methods = new MethodsClass();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Test
    public void aboutPageTest() {
//        на странице всего 2 интерактивных элемента, так что тут только проверки их наличия
        methods.fillAuthFormFields("login2", "password2");
        onView(allOf(withId(R.id.trademark_image_view))).check(matches(isDisplayed()));

        methods.chooseMenuItem("About");

        onView(allOf(withId(R.id.about_version_value_text_view))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.about_privacy_policy_label_text_view))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.about_privacy_policy_value_text_view))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.about_terms_of_use_label_text_view))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.about_terms_of_use_value_text_view))).check(matches(isDisplayed()));
    }
}
