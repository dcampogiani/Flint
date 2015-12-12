package com.danielecampogiani.flint;

import android.content.Intent;
import android.provider.CalendarContract;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.app.AppCompatActivity;
import android.test.suitebuilder.annotation.LargeTest;

import com.danielecampogiani.flintlib.Flint;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;

import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;

@SuppressWarnings("unchecked")
@RunWith(AndroidJUnit4.class)
@LargeTest
public class CalendarEventTest {

    @Rule
    public IntentsTestRule<MainActivity> mActivityRule = new IntentsTestRule(MainActivity.class);

    @Test
    @Ignore
    public void testBaseEvent() throws Exception {
        AppCompatActivity activity = mActivityRule.getActivity();
        Flint.calendarEvent().start(activity);
        intended(allOf(
                hasAction(equalTo(Intent.ACTION_INSERT)),
                hasData(CalendarContract.Events.CONTENT_URI)
        ));
    }

    @Test
    @Ignore
    public void testAllDayEvent() throws Exception {
        AppCompatActivity activity = mActivityRule.getActivity();
        Flint.calendarEvent().allDay().start(activity);
        intended(allOf(
                hasAction(equalTo(Intent.ACTION_INSERT)),
                hasData(CalendarContract.Events.CONTENT_URI),
                hasExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true)
        ));
    }

    @Test(expected = IllegalArgumentException.class)
    @Ignore
    public void testInvalidBeginTime() throws Exception {
        AppCompatActivity activity = mActivityRule.getActivity();
        Flint.calendarEvent().begin(-2).start(activity);
        intended(allOf(
                hasAction(equalTo(Intent.ACTION_INSERT)),
                hasData(CalendarContract.Events.CONTENT_URI)
        ));
    }

    @Test
    @Ignore
    public void testBeginTime() throws Exception {
        Calendar beginTime = Calendar.getInstance();
        beginTime.set(2015, 12, 12);
        long beginMillis = beginTime.getTimeInMillis();
        AppCompatActivity activity = mActivityRule.getActivity();
        Flint.calendarEvent().begin(beginMillis).start(activity);
        intended(allOf(
                hasAction(equalTo(Intent.ACTION_INSERT)),
                hasData(CalendarContract.Events.CONTENT_URI),
                hasExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginMillis)
        ));
    }

    @Test(expected = IllegalArgumentException.class)
    @Ignore
    public void testInvalidEndTime() throws Exception {
        AppCompatActivity activity = mActivityRule.getActivity();
        Flint.calendarEvent().end(-2).start(activity);
        intended(allOf(
                hasAction(equalTo(Intent.ACTION_INSERT)),
                hasData(CalendarContract.Events.CONTENT_URI)
        ));
    }

    @Test(expected = IllegalStateException.class)
    @Ignore
    public void testEndTimeWithoutBeginTime() throws Exception {
        Calendar endTime = Calendar.getInstance();
        endTime.set(2015, 12, 14);
        long endMillis = endTime.getTimeInMillis();
        AppCompatActivity activity = mActivityRule.getActivity();
        Flint.calendarEvent().end(endMillis).start(activity);
        intended(allOf(
                hasAction(equalTo(Intent.ACTION_INSERT)),
                hasData(CalendarContract.Events.CONTENT_URI)
        ));
    }

    @Test(expected = IllegalStateException.class)
    @Ignore
    public void testEndTimeAndAllDayEvent() throws Exception {
        Calendar endTime = Calendar.getInstance();
        endTime.set(2015, 12, 14);
        long endMillis = endTime.getTimeInMillis();
        AppCompatActivity activity = mActivityRule.getActivity();
        Flint.calendarEvent().allDay().end(endMillis).start(activity);
        intended(allOf(
                hasAction(equalTo(Intent.ACTION_INSERT)),
                hasData(CalendarContract.Events.CONTENT_URI)
        ));
    }

    @Test(expected = IllegalStateException.class)
    @Ignore
    public void testEndTimeBeforeBeginTime() throws Exception {
        Calendar beginTime = Calendar.getInstance();
        beginTime.set(2015, 12, 14);
        long beginMillis = beginTime.getTimeInMillis();
        Calendar endTime = Calendar.getInstance();
        endTime.set(2015, 12, 12);
        long endMillis = endTime.getTimeInMillis();
        AppCompatActivity activity = mActivityRule.getActivity();
        Flint.calendarEvent().begin(beginMillis).end(endMillis).start(activity);
        intended(allOf(
                hasAction(equalTo(Intent.ACTION_INSERT)),
                hasData(CalendarContract.Events.CONTENT_URI)
        ));
    }

    @Test
    @Ignore
    public void testEndTime() throws Exception {
        Calendar beginTime = Calendar.getInstance();
        beginTime.set(2015, 12, 12);
        long beginMillis = beginTime.getTimeInMillis();
        Calendar endTime = Calendar.getInstance();
        endTime.set(2015, 12, 14);
        long endMillis = endTime.getTimeInMillis();
        AppCompatActivity activity = mActivityRule.getActivity();
        Flint.calendarEvent().begin(beginMillis).end(endMillis).start(activity);
        intended(allOf(
                hasAction(equalTo(Intent.ACTION_INSERT)),
                hasData(CalendarContract.Events.CONTENT_URI),
                hasExtra(CalendarContract.EXTRA_EVENT_END_TIME, endMillis)
        ));
    }

    @Test
    @Ignore
    public void testTitleEvent() throws Exception {
        AppCompatActivity activity = mActivityRule.getActivity();
        final String title = "title";
        Flint.calendarEvent().title(title).start(activity);
        intended(allOf(
                hasAction(equalTo(Intent.ACTION_INSERT)),
                hasData(CalendarContract.Events.CONTENT_URI),
                hasExtra(CalendarContract.Events.TITLE, title)
        ));
    }

    @Test
    @Ignore
    public void testDescriptionEvent() throws Exception {
        AppCompatActivity activity = mActivityRule.getActivity();
        final String description = "description";
        Flint.calendarEvent().description(description).start(activity);
        intended(allOf(
                hasAction(equalTo(Intent.ACTION_INSERT)),
                hasData(CalendarContract.Events.CONTENT_URI),
                hasExtra(CalendarContract.Events.DESCRIPTION, description)
        ));
    }

    @Test
    @Ignore
    public void testLocationEvent() throws Exception {
        AppCompatActivity activity = mActivityRule.getActivity();
        final String location = "location";
        Flint.calendarEvent().location(location).start(activity);
        intended(allOf(
                hasAction(equalTo(Intent.ACTION_INSERT)),
                hasData(CalendarContract.Events.CONTENT_URI),
                hasExtra(CalendarContract.Events.EVENT_LOCATION, location)
        ));
    }

    @Test(expected = IllegalArgumentException.class)
    @Ignore
    public void testInvalidInvitee() throws Exception {
        AppCompatActivity activity = mActivityRule.getActivity();
        final String invitee = "this.is.not.an.email";
        Flint.calendarEvent().invitees(invitee).start(activity);
        intended(allOf(
                hasAction(equalTo(Intent.ACTION_INSERT)),
                hasData(CalendarContract.Events.CONTENT_URI)
        ));
    }

    @Test
    @Ignore
    public void testSingleInvitee() throws Exception {
        AppCompatActivity activity = mActivityRule.getActivity();
        final String invitee = "daniele.campogiani@gmail.com";
        Flint.calendarEvent().invitees(invitee).start(activity);
        intended(allOf(
                hasAction(equalTo(Intent.ACTION_INSERT)),
                hasData(CalendarContract.Events.CONTENT_URI),
                hasExtra(Intent.EXTRA_EMAIL, invitee)
        ));
    }

    @Test
    public void testInvitees() throws Exception {
        AppCompatActivity activity = mActivityRule.getActivity();
        final String pippo = "pippo@gmail.com";
        final String pluto = "pluto@gmail.com";
        final String paperino = "paperino@gmail.com";
        final String commaSeparatedInvitees = pippo + "," + pluto + "," + paperino;
        Flint.calendarEvent().invitees(pippo, pluto, paperino).start(activity);
        intended(allOf(
                hasAction(equalTo(Intent.ACTION_INSERT)),
                hasData(CalendarContract.Events.CONTENT_URI),
                hasExtra(Intent.EXTRA_EMAIL, commaSeparatedInvitees)
        ));
    }
}