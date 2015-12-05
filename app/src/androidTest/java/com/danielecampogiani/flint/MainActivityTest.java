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

import java.util.ArrayList;
import java.util.Calendar;

import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;

@SuppressWarnings("unchecked")
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public IntentsTestRule<MainActivity> mActivityRule = new IntentsTestRule(MainActivity.class);

    private static final int hour = 1;

    @Test
    @Ignore
    public void testBaseAlarm() throws Exception {
        AppCompatActivity activity = mActivityRule.getActivity();
        Flint.alarm(hour).start(activity);
        intended(allOf(
                hasAction(equalTo(AlarmClock.ACTION_SET_ALARM)),
                hasExtra(AlarmClock.EXTRA_HOUR, hour)
        ));
    }

    @Test
    @Ignore
    public void testMessageAlarm() throws Exception {
        final String message = "message";
        AppCompatActivity activity = mActivityRule.getActivity();
        Flint.alarm(hour).message(message).start(activity);
        intended(allOf(
                hasAction(equalTo(AlarmClock.ACTION_SET_ALARM)),
                hasExtra(AlarmClock.EXTRA_HOUR, hour),
                hasExtra(AlarmClock.EXTRA_MESSAGE, message)
        ));
    }

    @Test
    @Ignore
    public void testMinutesAlarm() throws Exception {
        final int minutes = 2;
        AppCompatActivity activity = mActivityRule.getActivity();
        Flint.alarm(hour).minutes(minutes).start(activity);
        intended(allOf(
                hasAction(equalTo(AlarmClock.ACTION_SET_ALARM)),
                hasExtra(AlarmClock.EXTRA_HOUR, hour),
                hasExtra(AlarmClock.EXTRA_MINUTES, minutes)
        ));
    }

    @Test
    @Ignore
    public void testDoNotVibrateAlarm() throws Exception {
        AppCompatActivity activity = mActivityRule.getActivity();
        Flint.alarm(hour).doNotVibrate().start(activity);
        intended(allOf(
                hasAction(equalTo(AlarmClock.ACTION_SET_ALARM)),
                hasExtra(AlarmClock.EXTRA_HOUR, hour),
                hasExtra(AlarmClock.EXTRA_VIBRATE, false)
        ));
    }

    @Test
    @Ignore
    public void testOneDayAlarm() throws Exception {
        AppCompatActivity activity = mActivityRule.getActivity();
        ArrayList<Integer> expectedDays = new ArrayList<>(1);
        expectedDays.add(Calendar.MONDAY);

        Flint.alarm(hour).days(Calendar.MONDAY).start(activity);
        intended(allOf(
                hasAction(equalTo(AlarmClock.ACTION_SET_ALARM)),
                hasExtra(AlarmClock.EXTRA_HOUR, hour),
                hasExtra(AlarmClock.EXTRA_DAYS, expectedDays)
        ));
    }

    @Test
    @Ignore
    public void testMoreDayAlarm() throws Exception {
        AppCompatActivity activity = mActivityRule.getActivity();
        ArrayList<Integer> expectedDays = new ArrayList<>(3);
        expectedDays.add(Calendar.MONDAY);
        expectedDays.add(Calendar.WEDNESDAY);
        expectedDays.add(Calendar.THURSDAY);

        Flint.alarm(hour).days(Calendar.MONDAY, Calendar.WEDNESDAY, Calendar.THURSDAY).start(activity);
        intended(allOf(
                hasAction(equalTo(AlarmClock.ACTION_SET_ALARM)),
                hasExtra(AlarmClock.EXTRA_HOUR, hour),
                hasExtra(AlarmClock.EXTRA_DAYS, expectedDays)
        ));
    }

    @Test
    @Ignore
    public void testSilentAlarm() throws Exception {
        AppCompatActivity activity = mActivityRule.getActivity();

        Flint.alarm(hour).silent().start(activity);
        intended(allOf(
                hasAction(equalTo(AlarmClock.ACTION_SET_ALARM)),
                hasExtra(AlarmClock.EXTRA_HOUR, hour),
                hasExtra(AlarmClock.EXTRA_RINGTONE, AlarmClock.VALUE_RINGTONE_SILENT)
        ));
    }

    @Test
    public void testRingtoneAlarm() throws Exception {
        AppCompatActivity activity = mActivityRule.getActivity();
        String ringtone = "Flint-Rulez";

        Flint.alarm(hour).ringtone(ringtone).start(activity);
        intended(allOf(
                hasAction(equalTo(AlarmClock.ACTION_SET_ALARM)),
                hasExtra(AlarmClock.EXTRA_HOUR, hour),
                hasExtra(AlarmClock.EXTRA_RINGTONE, ringtone)
        ));
    }


}