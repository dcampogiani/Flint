package com.danielecampogiani.flint;

import android.provider.AlarmClock;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.app.AppCompatActivity;
import android.test.suitebuilder.annotation.LargeTest;

import com.danielecampogiani.flintlib.Flint;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;

@SuppressWarnings("unchecked")
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ShowAlarmsTest {

    @Rule
    public IntentsTestRule<MainActivity> mActivityRule = new IntentsTestRule(MainActivity.class);

    @Test
    public void testShowAlarms() throws Exception {
        AppCompatActivity activity = mActivityRule.getActivity();
        Flint.showAlarms().start(activity);
        intended(allOf(
                hasAction(equalTo(AlarmClock.ACTION_SHOW_ALARMS))
        ));
    }
}