package com.riis.simple.etaandroid;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class CompanyActivityTest {

    @Rule
    public ActivityTestRule<CompanyActivity> mActivityTestRule = new ActivityTestRule<>(CompanyActivity.class);

    @Test
    public void companyActivityTest() {
        ViewInteraction appCompatTextView = onView(
                allOf(withId(android.R.id.text1), withText("SmartBus"),
                        childAtPosition(
                                withId(com.riis.simple.etaandroid.R.id.busses),
                                0),
                        isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(android.R.id.text1), withText("280"),
                        childAtPosition(
                                withId(com.riis.simple.etaandroid.R.id.routes),
                                7),
                        isDisplayed()));
        appCompatTextView2.perform(click());

        ViewInteraction toggleButton = onView(
                allOf(withId(com.riis.simple.etaandroid.R.id.directionButton), withText("Northbound"), isDisplayed()));
        toggleButton.perform(click());

        ViewInteraction toggleButton2 = onView(
                allOf(withId(com.riis.simple.etaandroid.R.id.directionButton), withText("Southbound"), isDisplayed()));
        toggleButton2.perform(click());

        ViewInteraction toggleButton3 = onView(
                allOf(withId(com.riis.simple.etaandroid.R.id.directionButton), withText("Northbound"), isDisplayed()));
        toggleButton3.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(android.R.id.text1), withText("FORD + INKSTER"),
                        childAtPosition(
                                allOf(withId(com.riis.simple.etaandroid.R.id.stops),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                1)),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("FORD + INKSTER")));

        ViewInteraction textView2 = onView(
                allOf(withId(android.R.id.text1), withText("MERRIMAN + ECORSE"),
                        childAtPosition(
                                allOf(withId(com.riis.simple.etaandroid.R.id.stops),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                1)),
                                4),
                        isDisplayed()));
        textView2.check(matches(withText("MERRIMAN + ECORSE")));

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
