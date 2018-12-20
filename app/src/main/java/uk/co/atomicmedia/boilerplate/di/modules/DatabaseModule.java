package uk.co.atomicmedia.boilerplate.di.modules;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.co.atomicmedia.boilerplate.data.database.JPDatabase;

@Module()
public class DatabaseModule {

    @Singleton @Provides
    JPDatabase provideDatabase(Application app) {
        return JPDatabase.getInstance(app);
    }
}
