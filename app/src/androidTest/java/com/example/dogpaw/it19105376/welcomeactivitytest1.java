package com.example.dogpaw.it19105376;

import androidx.test.rule.ActivityTestRule;

import com.example.dogpaw.R;
import com.example.dogpaw.WelcomeActivity;


import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;



public class welcomeactivitytest1 {
    @Rule
    public ActivityTestRule<WelcomeActivity> mActivityTestRule = new ActivityTestRule<WelcomeActivity>(WelcomeActivity.class);

    private WelcomeActivity mActivity = null;



    @Test
    public void testLaunch()
    {
       onView(withId(R.id.customer_welcome_btn)).check(matches(isDisplayed()));
        onView(withId(R.id.driver_welcome_btn)).check(matches(isDisplayed()));
    }

}