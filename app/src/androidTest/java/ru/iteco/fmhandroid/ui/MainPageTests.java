package ru.iteco.fmhandroid.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
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
public class MainPageTests {
    MethodsClass methods = new MethodsClass();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Test
    public void checkEverythingExistsInMainPage() {
        methods.fillAuthFormFields("login2", "password2");

        onView(allOf(withId(R.id.news_list_recycler_view))).check(matches(isDisplayed()))
                .perform(actionOnItemAtPosition(0, click()));
        onView(allOf(withId(R.id.all_news_text_view))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.news_item_description_text_view))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.container_list_news_include_on_fragment_main))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.expand_material_button))).check(matches(isDisplayed()));

        onView(allOf(withId(R.id.container_list_claim_include_on_fragment_main))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.expand_material_button))).check(matches(isDisplayed()));

        onView(allOf(withId(R.id.all_claims_text_view))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.claim_list_card))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.claim_bottom_divider_image_view))).check(matches(isDisplayed()));
    }

    @Test
    public void checkRedirectToAllClaimsPage(){
        methods.fillAuthFormFields("login2", "password2");
        onView(allOf(withId(R.id.all_claims_text_view))).check(matches(isDisplayed())).perform(click());
        onView(allOf(withText("Claims"),
                        withParent(withParent(withId(R.id.container_list_claim_include))),
                        isDisplayed())).check(matches(withText("Claims")));
    }

    @Test
    public void checkRedirectToCreateClaimPage(){
        methods.fillAuthFormFields("login2", "password2");
        methods.createNewClaim(1, false);
    }

    @Test
    public void checkRedirectToAllNewsPage(){
        methods.fillAuthFormFields("login2", "password2");
        onView(allOf(withId(R.id.all_news_text_view))).check(matches(isDisplayed())).perform(click());
        onView(allOf(withText("News"),
                withParent(withParent(withId(R.id.container_list_news_include))),
                isDisplayed())).check(matches(withText("News")));
    }
}
