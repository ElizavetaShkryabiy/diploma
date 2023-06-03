package ru.iteco.fmhandroid.ui.data;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import java.text.SimpleDateFormat;
import java.util.Date;

import ru.iteco.fmhandroid.R;

public class MethodsClass {
    static int number = 0;

    public static String getToday() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date date = new Date();
        String today = formatter.format(date).toString();
        return (today);
    }

    public static int getTestNumber() {
        number = number + 1;
        return number;
    }

    public static void chooseMenuItem(String item) {
        onView(allOf(withId(R.id.main_menu_image_button)))
                .check(matches(isDisplayed()))
                .perform(click());

        onView(allOf(withId(android.R.id.title), withText(item)))
                .check(matches(isDisplayed()))
                .check(matches(withText(item)))
                .perform(click());
        onView(allOf(withText(item),
                withParent(withParent(withId(R.id.container_list_news_include)))))
                .check(matches(isDisplayed()))
                .check(matches(withText(item)));

    }

}
