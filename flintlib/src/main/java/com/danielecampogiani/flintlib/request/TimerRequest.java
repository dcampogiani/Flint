package com.danielecampogiani.flintlib.request;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.provider.AlarmClock;
import android.support.annotation.CheckResult;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

public class TimerRequest extends BaseRequest {

    @SuppressLint("InlinedApi")
    private static final String action = AlarmClock.ACTION_SET_TIMER;

    private int length = -1;
    private String message = null;

    @CheckResult
    public TimerRequest length(@IntRange(from = 1, to = 86400) int length) {
        if (length < 1 || length > 86400)
            throw new IllegalArgumentException(Integer.toString(length));
        this.length = length;
        return this;
    }

    @CheckResult
    public TimerRequest message(@NonNull String message) {
        this.message = message;
        return this;
    }

    @SuppressLint("InlinedApi")
    @Override
    protected Intent packageIntent() {
        Intent intent = new Intent(action);
        if (this.length > -1)
            intent.putExtra(AlarmClock.EXTRA_LENGTH, this.length);
        if (message != null)
            intent.putExtra(AlarmClock.EXTRA_MESSAGE, this.message);
        return intent;
    }
}
