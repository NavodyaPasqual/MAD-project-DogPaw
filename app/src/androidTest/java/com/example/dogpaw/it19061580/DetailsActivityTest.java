package com.example.dogpaw.it19061580;

import androidx.test.rule.ActivityTestRule;

import com.example.dogpaw.DetailsActivity;
import com.example.dogpaw.R;



import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class DetailsActivityTest {

    @Rule
    public ActivityTestRule<DetailsActivity> mActivityTestRule = new ActivityTestRule<DetailsActivity>(DetailsActivity.class);

    private DetailsActivity mActivity = null;



    @Test
    public void testLaunch()
    {
        onView(withId(R.id.butSave)).check(matches(isDisplayed()));
        onView(withId(R.id.butShow)).check(matches(isDisplayed()));
        onView(withId(R.id.butUpdate)).check(matches(isDisplayed()));
        onView(withId(R.id.butDelete)).check(matches(isDisplayed()));
    }

}

