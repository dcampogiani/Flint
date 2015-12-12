package com.danielecampogiani.flintlib;


import android.Manifest;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.CheckResult;
import android.support.annotation.IntRange;
import android.support.annotation.RequiresPermission;

import com.danielecampogiani.flintlib.request.AlarmRequest;
import com.danielecampogiani.flintlib.request.CalendarEventRequest;
import com.danielecampogiani.flintlib.request.ShowAlarmsRequest;
import com.danielecampogiani.flintlib.request.TimerRequest;

public class Flint {

    @RequiresPermission(Manifest.permission.SET_ALARM)
    @CheckResult
    public static AlarmRequest alarm(@IntRange(from = 0, to = 23) int hour) {
        return new AlarmRequest(hour);
    }

    @RequiresPermission(Manifest.permission.SET_ALARM)
    @TargetApi(Build.VERSION_CODES.KITKAT)
    @CheckResult
    public static TimerRequest timer() {
        return new TimerRequest();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @CheckResult
    public static ShowAlarmsRequest showAlarms() {
        return new ShowAlarmsRequest();
    }

    @CheckResult
    public static CalendarEventRequest calendarEvent() {
        return new CalendarEventRequest();
    }
}
