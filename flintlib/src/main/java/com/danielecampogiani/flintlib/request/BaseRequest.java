package com.danielecampogiani.flintlib.request;


import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

abstract class BaseRequest {

    public void start(Activity activity) {
        activity.startActivity(packageIntent());
    }

    public void start(AppCompatActivity appCompatActivity) {
        appCompatActivity.startActivity(packageIntent());
    }

    public void start(Fragment fragment) {
        fragment.startActivity(packageIntent());
    }

    public void start(android.support.v4.app.Fragment fragment) {
        fragment.startActivity(packageIntent());
    }

    protected abstract Intent packageIntent();
}
