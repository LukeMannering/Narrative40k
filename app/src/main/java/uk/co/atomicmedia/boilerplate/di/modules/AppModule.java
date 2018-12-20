package uk.co.atomicmedia.boilerplate.di.modules;


import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.co.atomicmedia.boilerplate.R;
import uk.co.atomicmedia.boilerplate.data.api.JPApiService;
import uk.co.atomicmedia.boilerplate.data.api.JPParseApiService;
import uk.co.atomicmedia.boilerplate.databinding.AppDataBindingComponent;
import uk.co.atomicmedia.boilerplate.databinding.CustomDataBinding;
import uk.co.atomicmedia.boilerplate.util.AnalyticsLogger;
import uk.co.atomicmedia.boilerplate.util.AppLogger;
import uk.co.atomicmedia.boilerplate.util.DLog;
import uk.co.atomicmedia.boilerplate.util.FirebaseAnalyticsLogger;

@Module(includes = {DatabaseModule.class})
public class AppModule {

    @Singleton @Provides
    SharedPreferences provideSharedPreferences(Application app) {
        return app.getSharedPreferences(app.getString(R.string.shared_preference_file), Context.MODE_PRIVATE);
    }

    @Singleton @Provides
    AppLogger provideAppLogger(Application app){
        return new DLog.Builder()
                .setLoggingEnabled(app.getResources().getBoolean(R.bool.send_custom_logging_to_logcat))
                .setCrashlyticsExceptionLoggingEnabled(app.getResources().getBoolean(R.bool.send_custom_exceptions_to_crashlytics_as_non_fatal))
                .setLogTagBase(app.getString(R.string.log_tag_base))
                .create();
    }

    @Singleton @Provides
    JPApiService provideJPApiService(AppLogger logger) {
        return new JPParseApiService(logger);
    }

    @Provides
    @Singleton
    public Picasso providePicasso(Application app){
        return Picasso.with(app.getApplicationContext());
    }

    @Provides
    @Singleton
    public AnalyticsLogger provideAnalyticsLogger (Application app){
        return new FirebaseAnalyticsLogger(app);
    }

    @Provides
    @Singleton
    CustomDataBinding provideRecyclerViewDataBinding(Picasso picasso){
        return new CustomDataBinding(picasso);
    }

    @Provides
    @Singleton
    AppDataBindingComponent provideAppDataBindingComponent(CustomDataBinding customDataBinding,
                                                           AppLogger appLogger){
        return new AppDataBindingComponent(customDataBinding, appLogger);
    }
}
