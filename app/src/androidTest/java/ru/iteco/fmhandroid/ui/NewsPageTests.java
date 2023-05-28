package ru.iteco.fmhandroid.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNot.not;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.R;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class NewsPageTests {

    MethodsClass methods = new MethodsClass();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Test
    public void everythingDisplayedOnNewsPage() {
        methods.fillAuthFormFields("login2", "password2");
        onView(allOf(withId(R.id.trademark_image_view))).check(matches(isDisplayed()));
        methods.chooseMenuItem("News");
        onView(allOf(withText("News"),
                withParent(withParent(withId(R.id.container_list_news_include))),
                isDisplayed())).check(matches(withText("News")));

        onView(allOf(withId(R.id.sort_news_material_button), withContentDescription("Sort news list button"))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.filter_news_material_button))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.edit_news_material_button))).check(matches(isDisplayed()));
        onView(allOf(withParent(allOf(withId(R.id.news_item_material_card_view),
                        withParent(withId(R.id.news_list_recycler_view)))),
                isDisplayed())).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.news_list_recycler_view))).perform(actionOnItemAtPosition(1, click()));
        onView(allOf(withId(R.id.news_item_description_text_view))).check(matches(isDisplayed()));
    }

    @Test
    public void sortWorksNewsPageTest() {
        methods.fillAuthFormFields("login2", "password2");
        onView(allOf(withId(R.id.trademark_image_view))).check(matches(isDisplayed()));
        methods.chooseMenuItem("News");
        onView(allOf(withText("News"),
                withParent(withParent(withId(R.id.container_list_news_include))),
                isDisplayed())).check(matches(withText("News")));

        onView(allOf(withId(R.id.sort_news_material_button), withContentDescription("Sort news list button")))
                .check(matches(isDisplayed()))
                .perform(click())
                .perform(scrollToPosition(0));
        onView(allOf(withId(R.id.news_item_date_text_view))).check(matches(not(withText(methods.getToday()))));
    }

    @Test
    public void filterWorksNewsPageTest() {
        methods.fillAuthFormFields("login2", "password2");
        onView(allOf(withId(R.id.trademark_image_view))).check(matches(isDisplayed()));
        methods.chooseMenuItem("News");
        onView(allOf(withText("News"),
                withParent(withParent(withId(R.id.container_list_news_include))),
                isDisplayed())).check(matches(withText("News")));

        methods.filterNews("День рождения");

    }

    @Test
    public void controlPanelOpensWithAllElements() {
        methods.fillAuthFormFields("login2", "password2");
        onView(allOf(withId(R.id.trademark_image_view))).check(matches(isDisplayed()));
        methods.chooseMenuItem("News");
        onView(allOf(withId(R.id.edit_news_material_button))).perform(click());
        methods.controlPanelElementsDisplayed();
        onView(allOf(withId(android.R.id.button2), withText("Cancel"))).perform(scrollTo(), click());

    }

    @Test
    public void createNewsItem() {
        methods.fillAuthFormFields("login2", "password2");
        onView(allOf(withId(R.id.trademark_image_view))).check(matches(isDisplayed()));
        methods.chooseMenuItem("News");
        onView(allOf(withId(R.id.edit_news_material_button))).perform(click());
        methods.controlPanelElementsDisplayed();
        methods.createNewNewsItem(methods.getTestNumber());

    }
    @Test
    public void editNewsItem() {
        methods.fillAuthFormFields("login2", "password2");
        onView(allOf(withId(R.id.trademark_image_view))).check(matches(isDisplayed()));
        methods.chooseMenuItem("News");
        onView(allOf(withId(R.id.edit_news_material_button))).perform(click());
        methods.controlPanelElementsDisplayed();
        int testN = methods.getTestNumber();
        methods.createNewNewsItem(testN);
        int testN2 = methods.getTestNumber();
        methods.editNewsItems(testN2, true, true, true, false);
        int testN3 = methods.getTestNumber();
        methods.editNewsItems(testN3, true, false, true, true);
    }
}
