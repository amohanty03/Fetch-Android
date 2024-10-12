package com.example.fetch_android;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class NetworkAvailableTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void networkAvailableTest() throws Exception {
        // Click the refresh button
        onView(allOf(withId(R.id.refreshButton), withText("Refresh")))
                .perform(click());

        // Enable Wi-Fi
        Runtime.getRuntime().exec("svc wifi enable");
        Thread.sleep(10000);

        // Wait for the RecyclerView to become visible
        onView(withId(R.id.recyclerView))
                .check(matches(isDisplayed()));
    }
}
