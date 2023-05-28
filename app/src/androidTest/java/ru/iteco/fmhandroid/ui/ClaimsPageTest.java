package ru.iteco.fmhandroid.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.R;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ClaimsPageTest {
    MethodsClass methods = new MethodsClass();
    static String statusOpen = "Open";
    static String statusInProcess = "In progress";
    static String statusCancelled = "Canceled";
    static String statusExecuted = "Executed";

    static String statusToOpen = "Throw off";
    static String statusToInProcess = "take to work";
    static String statusToCancelled = "Cancel";
    static String statusTo = "To execute";

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Test
    public void claimsPageItemsDisplayedTest() {
        methods.fillAuthFormFields("login2", "password2");
        onView(allOf(withId(R.id.trademark_image_view))).check(matches(isDisplayed()));
        methods.chooseMenuItem("Claims");

        onView(allOf(withId(R.id.add_new_claim_material_button))).check(matches(isDisplayed()));
        onView(allOf(withParent(allOf(withId(R.id.claim_list_card))))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.claim_list_card))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.claim_list_recycler_view))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.all_claims_cards_block_constraint_layout))).check(matches(isDisplayed()));
    }

    @Test
    public void checkCreateNewClaim() {
        methods.fillAuthFormFields("login2", "password2");
        onView(allOf(withId(R.id.trademark_image_view))).check(matches(isDisplayed()));
        methods.chooseMenuItem("Claims");

        methods.createNewClaim(methods.getTestNumber(), false);
    }

    @Test
    public void checkEditNewClaim() {
        methods.fillAuthFormFields("login2", "password2");
        onView(allOf(withId(R.id.trademark_image_view))).check(matches(isDisplayed()));
        methods.chooseMenuItem("Claims");
        int firstTest = methods.getTestNumber();
        methods.createNewClaim(firstTest, false);
        int secTest = methods.getTestNumber();
        methods.editClaim(firstTest, secTest, false, false);
    }

    @Test
    public void changeClaimStatusFO2P() {
        methods.fillAuthFormFields("login2", "password2");
        onView(allOf(withId(R.id.trademark_image_view))).check(matches(isDisplayed()));
        methods.chooseMenuItem("Claims");
        int firstTest = methods.getTestNumber();
        methods.createNewClaim(firstTest, false);
        methods.changeStatus(statusOpen, statusInProcess, statusToInProcess, statusCancelled);
    }

    @Test
    public void changeClaimStatusFO2C() {
        methods.fillAuthFormFields("login2", "password2");
        onView(allOf(withId(R.id.trademark_image_view))).check(matches(isDisplayed()));
        methods.chooseMenuItem("Claims");
        int firstTest = methods.getTestNumber();
        methods.createNewClaim(firstTest, false);
        methods.changeStatus(statusOpen, statusCancelled, statusCancelled, statusToInProcess);
    }

    @Test
    public void changeClaimStatusFP2O() {
        methods.fillAuthFormFields("login2", "password2");
        onView(allOf(withId(R.id.trademark_image_view))).check(matches(isDisplayed()));
        methods.chooseMenuItem("Claims");
        int firstTest = methods.getTestNumber();
        methods.createNewClaim(firstTest, true);
        methods.changeStatus(statusInProcess, statusOpen, statusToOpen, statusTo);
    }

    @Test
    public void changeClaimStatusFP2E() {
        methods.fillAuthFormFields("login2", "password2");
        onView(allOf(withId(R.id.trademark_image_view))).check(matches(isDisplayed()));
        methods.chooseMenuItem("Claims");
        int firstTest = methods.getTestNumber();
        methods.createNewClaim(firstTest, true);
        methods.changeStatus(statusInProcess, statusExecuted, statusTo, statusToOpen);
    }

    @Test
    public void addComment() {
        methods.fillAuthFormFields("login2", "password2");
        onView(allOf(withId(R.id.trademark_image_view))).check(matches(isDisplayed()));
        methods.chooseMenuItem("Claims");
        int firstTest = methods.getTestNumber();
        methods.createNewClaim(firstTest, true);
        methods.addComment("comment text");
    }

    @Test
    public void checkErrorOnEmptyFieldsWhileCreatingClaim() {
        methods.fillAuthFormFields("login2", "password2");
        onView(allOf(withId(R.id.trademark_image_view))).check(matches(isDisplayed()));
        methods.chooseMenuItem("Claims");
        onView(allOf(withId(R.id.add_new_claim_material_button)))
                .check(matches(isDisplayed()))
                .perform(click());
        onView(allOf(withId(R.id.save_button)))
                .check(matches(isDisplayed()))
                .perform(click());
        onView(allOf(withId(android.R.id.message))).check(matches(withText("Fill empty fields")));
        onView(allOf(withId(android.R.id.button1))).perform(scrollTo(), click());
    }
    @Test
    public void checkExcessiveFillOfTitleFieldWhileCreatingClaim() {
        methods.fillAuthFormFields("login2", "password2");
        onView(allOf(withId(R.id.trademark_image_view))).check(matches(isDisplayed()));
        methods.chooseMenuItem("Claims");
        int firstTest = methods.getTestNumber();
        methods.createNewClaim(00000000000000000000000000123, true);
    }

    @Test
    public void checkFilterWorksInClaims() {
        methods.fillAuthFormFields("login2", "password2");
        onView(allOf(withId(R.id.trademark_image_view))).check(matches(isDisplayed()));
        methods.chooseMenuItem("Claims");
        methods.chooseFilterCriteriaInClaims(statusOpen);

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
