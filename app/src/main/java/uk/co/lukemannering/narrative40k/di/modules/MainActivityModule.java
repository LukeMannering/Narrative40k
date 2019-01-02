package uk.co.lukemannering.narrative40k.di.modules;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import uk.co.lukemannering.narrative40k.ui.MainActivity;

@Module
public abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = MainActivityFragmentBuildersModule.class)
    abstract MainActivity contributeMainActivity();
}
