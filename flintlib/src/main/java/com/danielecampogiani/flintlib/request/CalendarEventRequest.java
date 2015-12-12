package com.danielecampogiani.flintlib.request;


import android.content.Intent;
import android.net.Uri;
import android.provider.CalendarContract;
import android.text.TextUtils;

import java.util.ArrayList;

public class CalendarEventRequest extends BaseRequest {

    private static final String action = Intent.ACTION_INSERT;
    private static final Uri data = CalendarContract.Events.CONTENT_URI;

    private boolean allDay = false;
    private long beginMilliseconds = -1;
    private long endMilliseconds = -1;
    private String title;
    private String description;
    private String location;
    private String[] invitees;

    public CalendarEventRequest allDay() {
        if (endMilliseconds != -1)
            throw new IllegalStateException("End time of the event already present");
        allDay = true;
        return this;
    }

    public CalendarEventRequest begin(long beginMilliseconds) {
        if (beginMilliseconds <= 0)
            throw new IllegalArgumentException("beginMilliseconds");
        this.beginMilliseconds = beginMilliseconds;
        return this;
    }

    public CalendarEventRequest end(long endMilliseconds) {
        if (endMilliseconds <= 0)
            throw new IllegalArgumentException("endMilliseconds");
        if (allDay)
            throw new IllegalStateException("Can't set end time of a allDay event");
        if (this.beginMilliseconds < 0)
            throw new IllegalStateException("You must set begin time before end time");
        if (this.beginMilliseconds >= endMilliseconds)
            throw new IllegalStateException("Event must end after begin time");
        this.endMilliseconds = endMilliseconds;
        return this;
    }

    public CalendarEventRequest title(String title) {
        if (title == null)
            throw new NullPointerException("title");
        this.title = title;
        return this;
    }

    public CalendarEventRequest description(String description) {
        if (description == null)
            throw new NullPointerException("description");
        this.description = description;
        return this;
    }

    public CalendarEventRequest location(String location) {
        if (location == null)
            throw new NullPointerException("location");
        this.location = location;
        return this;
    }

    public CalendarEventRequest invitees(String firstInvitee, String... otherInvitees) {
        ArrayList<String> inviteesList = new ArrayList<>(1);
        if (firstInvitee == null)
            throw new NullPointerException("firstInvitee");
        if (isValidEmailAddress(firstInvitee))
            inviteesList.add(firstInvitee);
        else throw new IllegalArgumentException(firstInvitee + " is not a valid email");
        for (int i = 0; i < otherInvitees.length; i++) {
            if (otherInvitees[i] == null)
                throw new NullPointerException("Invitee at position " + Integer.toString(i));
            if (isValidEmailAddress(otherInvitees[i]))
                inviteesList.add(otherInvitees[i]);
            else throw new IllegalArgumentException(otherInvitees[i] + " is not a valid email");
        }
        this.invitees = inviteesList.toArray(new String[inviteesList.size()]);
        return this;
    }

    private boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    @Override
    protected Intent packageIntent() {
        Intent intent = new Intent(action);
        intent.setData(data);
        if (allDay)
            intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
        if (beginMilliseconds > 0)
            intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginMilliseconds);
        if (endMilliseconds > 0)
            intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endMilliseconds);
        if (title != null)
            intent.putExtra(CalendarContract.Events.TITLE, title);
        if (description != null)
            intent.putExtra(CalendarContract.Events.DESCRIPTION, description);
        if (location != null)
            intent.putExtra(CalendarContract.Events.EVENT_LOCATION, location);
        if (invitees != null && invitees.length > 0) {
            final String commaSeparatedInvitees = TextUtils.join(",", invitees);
            intent.putExtra(Intent.EXTRA_EMAIL, commaSeparatedInvitees);
        }
        return intent;
    }
}
