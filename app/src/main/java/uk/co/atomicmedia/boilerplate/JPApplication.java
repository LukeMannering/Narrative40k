package uk.co.atomicmedia.boilerplate;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import com.crashlytics.android.Crashlytics;

import javax.inject.Inject;

import androidx.databinding.DataBindingUtil;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import io.fabric.sdk.android.Fabric;
import uk.co.atomicmedia.boilerplate.databinding.AppDataBindingComponent;
import uk.co.atomicmedia.boilerplate.di.AppInjector;
import uk.co.atomicmedia.boilerplate.di.DaggerAppComponent;

public class JPApplication extends Application implements HasActivityInjector {

    public static final String LOG_TAG =  JPApplication.class.getSimpleName();

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Inject
    AppDataBindingComponent mAppDataBindingComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initDependencyInjection();

        // Enable Crashlytics
        try {
            if(getResources().getBoolean(R.bool.crashlytics_enabled)){
                Fabric.with(this, new Crashlytics());
            }
        }catch (Exception e){
            Log.d(LOG_TAG, e.getMessage());
        }

        // setup the default databinding component
        DataBindingUtil.setDefaultComponent(mAppDataBindingComponent);
    }

    /**
     * Separated into own methods so it can be overriden in unit testing Application class,
     * which extends this one.
     */
    protected void initDependencyInjection(){

        DaggerAppComponent.builder().application(this)
                .build().inject(this);

        AppInjector.init(this);
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
