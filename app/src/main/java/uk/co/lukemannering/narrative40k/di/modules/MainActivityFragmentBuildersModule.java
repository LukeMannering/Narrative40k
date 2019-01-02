package uk.co.lukemannering.narrative40k.di.modules;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import uk.co.lukemannering.narrative40k.ui.characterlist.CharacterListFragment;

@Module
public abstract class MainActivityFragmentBuildersModule {
    @ContributesAndroidInjector
    abstract CharacterListFragment characterListFragment();

}
