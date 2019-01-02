package uk.co.lukemannering.narrative40k.di.modules;


import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.co.lukemannering.narrative40k.R;
import uk.co.lukemannering.narrative40k.databinding.AppDataBindingComponent;
import uk.co.lukemannering.narrative40k.databinding.CustomDataBinding;
import uk.co.lukemannering.narrative40k.util.AnalyticsLogger;
import uk.co.lukemannering.narrative40k.util.AppLogger;
import uk.co.lukemannering.narrative40k.util.DLog;
import uk.co.lukemannering.narrative40k.util.FirebaseAnalyticsLogger;

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
    CustomDataBinding provideCustomDataBinding(Picasso picasso){
        return new CustomDataBinding(picasso);
    }

    @Provides
    @Singleton
    AppDataBindingComponent provideAppDataBindingComponent(CustomDataBinding customDataBinding,
                                                           AppLogger appLogger){
        return new AppDataBindingComponent(customDataBinding, appLogger);
    }
}
