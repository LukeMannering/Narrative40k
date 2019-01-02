package uk.co.lukemannering.narrative40k.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import uk.co.lukemannering.narrative40k.Narrative40App;
import uk.co.lukemannering.narrative40k.di.modules.AppModule;
import uk.co.lukemannering.narrative40k.di.modules.MainActivityModule;
import uk.co.lukemannering.narrative40k.di.modules.ViewModelModule;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        ViewModelModule.class,
        AppModule.class,
        MainActivityModule.class
})
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }
    void inject(Narrative40App native40kApp);
}
