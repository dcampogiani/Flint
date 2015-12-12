package com.danielecampogiani.flintlib;


import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.IntRange;

import com.danielecampogiani.flintlib.request.AlarmRequest;
import com.danielecampogiani.flintlib.request.CalendarEventRequest;
import com.danielecampogiani.flintlib.request.ShowAlarmsRequest;
import com.danielecampogiani.flintlib.request.TimerRequest;

public class Flint {

    public static AlarmRequest alarm(@IntRange(from = 0, to = 23) int hour) {
        return new AlarmRequest(hour);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static TimerRequest timer() {
        return new TimerRequest();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static ShowAlarmsRequest showAlarms() {
        return new ShowAlarmsRequest();
    }

    public static CalendarEventRequest calendarEvent() {
        return new CalendarEventRequest();
    }
}
