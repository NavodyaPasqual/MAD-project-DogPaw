package com.example.dogpaw.it19950600;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import com.example.dogpaw.MA5;
import com.example.dogpaw.R;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class MA5Test {

    @Rule
    public ActivityTestRule<MA5> mActivityTestRule = new ActivityTestRule<MA5>(MA5.class);
    private MA5 ma5 = null;

    @Test
    public void testLaunch(){
        onView(ViewMatchers.withId(R.id.button18)).check(matches(isDisplayed()));
        onView(withId(R.id.button17)).check(matches(isDisplayed()));
        onView(withId(R.id.button4)).check(matches(isDisplayed()));

    }

}