package uk.co.atomicmedia.boilerplate.di.modules;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import uk.co.atomicmedia.boilerplate.ui.MainActivity;

@Module
public abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = MainActivityFragmentBuildersModule.class)
    abstract MainActivity contributeMainActivity();
}
