package uk.co.atomicmedia.boilerplate.util;

import android.content.Context;
import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;

import uk.co.atomicmedia.boilerplate.R;

public class FirebaseAnalyticsLogger implements AnalyticsLogger{

    private FirebaseAnalytics mFirebaseAnalytics;
    private boolean mIsAnalyticsEnabled;

    public static final String SCREEN_HOME = "homescreen";

    public FirebaseAnalyticsLogger(Context context) {
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(context);
        mIsAnalyticsEnabled = context.getResources().getBoolean(R.bool.firebase_analytics_enabled);
    }

    @Override
    public void logScreenView(String screen) {

        if(!mIsAnalyticsEnabled || screen == null || screen.trim().length() < 1){
            return;
        }

        Bundle params = new Bundle();
        params.putString("screen", screen);
        mFirebaseAnalytics.logEvent("screen_view", params);
    }
}
