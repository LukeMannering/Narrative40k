package uk.co.atomicmedia.boilerplate.di.modules;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import uk.co.atomicmedia.boilerplate.ui.search.SearchFragment;

@Module
public abstract class MainActivityFragmentBuildersModule {
    @ContributesAndroidInjector
    abstract SearchFragment contributeSearchFragment();

}
