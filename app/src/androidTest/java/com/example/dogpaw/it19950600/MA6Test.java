package com.example.dogpaw.it19950600;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import com.example.dogpaw.MA6;
import com.example.dogpaw.R;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class MA6Test {

    @Rule
    public ActivityTestRule<MA6> mActivityTestRule = new ActivityTestRule<MA6>(MA6.class);
    private MA6 ma6 = null;

    @Test
    public void testLaunch(){
        onView(ViewMatchers.withId(R.id.editTextTextPersonName)).check(matches(isDisplayed()));
        onView(withId(R.id.editTextTextPersonName2)).check(matches(isDisplayed()));
        onView(withId(R.id.editTextNumber)).check(matches(isDisplayed()));
        onView(withId(R.id.editTextTextPersonName6)).check(matches(isDisplayed()));
        onView(withId(R.id.editTextTextPassword)).check(matches(isDisplayed()));
        onView(withId(R.id.button2)).check(matches(isDisplayed()));

    }


}