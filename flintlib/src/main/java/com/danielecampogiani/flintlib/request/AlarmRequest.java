package com.danielecampogiani.flintlib.request;


import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.provider.AlarmClock;
import android.support.annotation.IntRange;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;

public class AlarmRequest extends BaseRequest {

    private static final HashSet<Integer> validDays;

    static {
        validDays = new HashSet<>();
        validDays.add(Calendar.SUNDAY);
        validDays.add(Calendar.MONDAY);
        validDays.add(Calendar.TUESDAY);
        validDays.add(Calendar.WEDNESDAY);
        validDays.add(Calendar.THURSDAY);
        validDays.add(Calendar.FRIDAY);
        validDays.add(Calendar.SATURDAY);
    }


    private static final String action = AlarmClock.ACTION_SET_ALARM;

    private final int hour;

    private int minutes = 0;
    private final ArrayList<Integer> days = new ArrayList<>(0);
    private String message = null;
    private String ringtone = null;
    private boolean vibrate = true;

    public AlarmRequest(@IntRange(from = 0, to = 23) int hour) {
        if (hour < 0 || hour > 23)
            throw new IllegalArgumentException(Integer.toString(hour));
        this.hour = hour;
    }

    public AlarmRequest minutes(@IntRange(from = 0, to = 59) int minutes) {
        if (minutes < 0 || minutes > 59)
            throw new IllegalArgumentException(Integer.toString(minutes));
        this.minutes = minutes;
        return this;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public AlarmRequest days(int firstDay, int... otherDays) {
        if (isDayValid(firstDay))
            this.days.add(firstDay);
        for (int otherDay : otherDays) {
            if (isDayValid(otherDay))
                this.days.add(otherDay);
        }

        return this;
    }

    public AlarmRequest message(String message) {
        if (message == null)
            throw new NullPointerException("message");
        this.message = message;
        return this;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public AlarmRequest silent() {
        this.ringtone = AlarmClock.VALUE_RINGTONE_SILENT;
        return this;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public AlarmRequest ringtone(String mediaUri) {
        if (mediaUri == null)
            throw new NullPointerException("mediaUri");
        this.ringtone = mediaUri;
        return this;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public AlarmRequest doNotVibrate() {
        this.vibrate = false;
        return this;
    }

    private boolean isDayValid(int dayToValidate) {
        return validDays.contains(dayToValidate);
    }

    @Override
    protected Intent packageIntent() {
        Intent intent = new Intent(action);
        intent.putExtra(AlarmClock.EXTRA_HOUR, this.hour);
        if (this.minutes > 0)
            intent.putExtra(AlarmClock.EXTRA_MINUTES, this.minutes);
        if (this.days.size() > 0)
            intent.putExtra(AlarmClock.EXTRA_DAYS, this.days);
        if (message != null)
            intent.putExtra(AlarmClock.EXTRA_MESSAGE, this.message);
        if (ringtone != null)
            intent.putExtra(AlarmClock.EXTRA_RINGTONE, this.ringtone);
        if (!vibrate)
            intent.putExtra(AlarmClock.EXTRA_VIBRATE, false);
        return intent;
    }
}
