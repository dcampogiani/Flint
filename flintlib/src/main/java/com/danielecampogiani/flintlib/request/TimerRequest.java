package com.danielecampogiani.flintlib.request;

import android.content.Intent;
import android.provider.AlarmClock;
import android.support.annotation.IntRange;

public class TimerRequest extends BaseRequest {

    private static final String action = AlarmClock.ACTION_SET_TIMER;

    private int length = -1;
    private String message = null;

    public TimerRequest length(@IntRange(from = 1, to = 86400) int length) {
        if (length < 1 || length > 86400)
            throw new IllegalArgumentException(Integer.toString(length));
        this.length = length;
        return this;
    }

    public TimerRequest message(String message) {
        if (message == null)
            throw new NullPointerException("message");
        this.message = message;
        return this;
    }

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
