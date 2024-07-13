package com.vlite.app.sample;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import com.vlite.sdk.application.ActivityCallbackDelegate;


public class SampleActivityCallbackDelegate extends ActivityCallbackDelegate {

    private static final String TAG = "ACallbackDelegate";

    @Override
    public void onRequestPermissionsResult(Activity activity, String[] permissions, int[] grantResults) {
        for (int i = 0; i < permissions.length; i++) {
            Log.d(TAG,"onRequestPermissionsResult : permission: "+permissions[i] + " grantResult "+grantResults[i] +" activity: "+activity);
        }

    }

    @Override
    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
    }

    @Override
    public boolean onConfigurationChanged(Activity activity, Configuration configuration) {
        Activity fontActivity = ActivityLifecycleManager.getInstance().peekActivity();
        return fontActivity == activity;
    }

}
