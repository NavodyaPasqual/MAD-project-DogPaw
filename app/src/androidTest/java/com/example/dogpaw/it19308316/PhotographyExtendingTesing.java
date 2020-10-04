package com.example.dogpaw.it19308316;

import com.example.dogpaw.PhotographyBooking;
import com.example.dogpaw.R;

import androidx.test.espresso.ViewAction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

//Testing for Buttons in Photography Booking Start page
public class PhotographyExtendingTesing {
    @Rule
    public ActivityTestRule<PhotographyBooking> photographyBookingActivityTestRule = new ActivityTestRule<PhotographyBooking>(PhotographyBooking.class);
    private PhotographyBooking photographyBooking = null;

    @Test
    public void testLaunch(){
        onView(withId(R.id.button1)).check(matches(isDisplayed()));
        onView(withId(R.id.button2)).check((matches(isDisplayed())));

    }
}



