package uk.co.lukemannering.narrative40k.di.modules;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.co.lukemannering.narrative40k.data.database.AppDatabase;

@Module()
public class DatabaseModule {

    @Singleton @Provides
    AppDatabase provideDatabase(Application app) {
        return AppDatabase.getInstance(app);
    }
}
