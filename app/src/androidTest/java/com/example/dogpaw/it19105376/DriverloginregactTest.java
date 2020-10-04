package com.example.dogpaw.it19105376;


import androidx.test.rule.ActivityTestRule;

import com.example.dogpaw.DriverLoginRegisterActivity;
import com.example.dogpaw.R;
import com.example.dogpaw.WelcomeActivity;


import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;



public class DriverloginregactTest {
    @Rule
    public ActivityTestRule<DriverLoginRegisterActivity> mActivityTestRule = new ActivityTestRule<DriverLoginRegisterActivity>(DriverLoginRegisterActivity.class);

    private DriverLoginRegisterActivity mActivity = null;



    @Test
    public void testLaunch()
    {
        onView(withId(R.id.login_driver_btn)).check(matches(isDisplayed()));
        onView(withId(R.id.driver_register_link)).check(matches(isDisplayed()));
    }

}