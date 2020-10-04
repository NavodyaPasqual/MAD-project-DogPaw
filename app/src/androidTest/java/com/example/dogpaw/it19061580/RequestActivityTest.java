package com.example.dogpaw.it19061580;
import androidx.test.rule.ActivityTestRule;


import com.example.dogpaw.QuoteActivity;
import com.example.dogpaw.R;



import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


public class RequestActivityTest {
    @Rule
    public ActivityTestRule<QuoteActivity> mActivityTestRule = new ActivityTestRule<QuoteActivity>(QuoteActivity.class);

    private QuoteActivity mActivity = null;



    @Test
    public void testLaunch()
    {
        onView(withId(R.id.button1)).check(matches(isDisplayed()));
        onView(withId(R.id.button8)).check(matches(isDisplayed()));
    }

}

