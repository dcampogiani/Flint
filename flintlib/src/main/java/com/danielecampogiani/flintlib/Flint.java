package com.danielecampogiani.flintlib;


import android.support.annotation.IntRange;

import com.danielecampogiani.flintlib.request.AlarmRequest;

public class Flint {

    public static AlarmRequest alarm(@IntRange(from = 0, to = 23) int hour) {
        return new AlarmRequest(hour);
    }
}
