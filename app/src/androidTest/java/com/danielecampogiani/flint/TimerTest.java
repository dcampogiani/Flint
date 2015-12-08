package com.danielecampogiani.flint;

import android.provider.AlarmClock;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.app.AppCompatActivity;
import android.test.suitebuilder.annotation.LargeTest;

import com.danielecampogiani.flintlib.Flint;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;

@SuppressWarnings("unchecked")
@RunWith(AndroidJUnit4.class)
@LargeTest
public class TimerTest {

    @Rule
    public IntentsTestRule<MainActivity> mActivityRule = new IntentsTestRule(MainActivity.class);

    private static final int hour = 1;

    @Test
    @Ignore
    public void testBaseTimer() throws Exception {
        AppCompatActivity activity = mActivityRule.getActivity();
        Flint.timer().start(activity);
        intended(allOf(
                hasAction(equalTo(AlarmClock.ACTION_SET_TIMER))
        ));
    }

    @Test
    @Ignore
    public void testLengthTimer() throws Exception {
        AppCompatActivity activity = mActivityRule.getActivity();
        final int length = 3;
        Flint.timer().length(length).start(activity);
        intended(allOf(
                hasAction(equalTo(AlarmClock.ACTION_SET_TIMER)),
                hasExtra(AlarmClock.EXTRA_LENGTH, length)
        ));
    }

    @Test
    public void testMessageTimer() throws Exception {
        AppCompatActivity activity = mActivityRule.getActivity();
        final String message = "Flint";
        Flint.timer().message(message).start(activity);
        intended(allOf(
                hasAction(equalTo(AlarmClock.ACTION_SET_TIMER)),
                hasExtra(AlarmClock.EXTRA_MESSAGE, message)
        ));
    }

}