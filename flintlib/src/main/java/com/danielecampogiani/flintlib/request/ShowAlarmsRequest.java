package com.danielecampogiani.flintlib.request;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.provider.AlarmClock;

public class ShowAlarmsRequest extends BaseRequest {

    @SuppressLint("InlinedApi")
    private static final String action = AlarmClock.ACTION_SHOW_ALARMS;

    @Override
    protected Intent packageIntent() {
        return new Intent(action);
    }
}
