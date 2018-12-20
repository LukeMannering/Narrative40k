package uk.co.atomicmedia.boilerplate.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import uk.co.atomicmedia.boilerplate.JPApplication;
import uk.co.atomicmedia.boilerplate.di.modules.AppModule;
import uk.co.atomicmedia.boilerplate.di.modules.MainActivityModule;
import uk.co.atomicmedia.boilerplate.di.modules.ViewModelModule;

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
    void inject(JPApplication githubApp);
}
