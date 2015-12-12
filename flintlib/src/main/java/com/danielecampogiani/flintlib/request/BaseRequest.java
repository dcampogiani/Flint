package com.danielecampogiani.flintlib.request;


import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

abstract class BaseRequest {

    public void start(@NonNull Activity activity) {
        Intent intent = packageIntent();
        if (intent.resolveActivity(activity.getPackageManager()) != null)
            activity.startActivity(intent);
    }

    public void start(@NonNull AppCompatActivity appCompatActivity) {
        Intent intent = packageIntent();
        if (intent.resolveActivity(appCompatActivity.getPackageManager()) != null)
            appCompatActivity.startActivity(intent);
    }

    public void start(@NonNull Fragment fragment) {
        Intent intent = packageIntent();
        if (intent.resolveActivity(fragment.getActivity().getPackageManager()) != null)
            fragment.startActivity(intent);
    }

    public void start(@NonNull android.support.v4.app.Fragment fragment) {
        Intent intent = packageIntent();
        if (intent.resolveActivity(fragment.getActivity().getPackageManager()) != null)
            fragment.startActivity(intent);
    }

    protected abstract Intent packageIntent();
}
