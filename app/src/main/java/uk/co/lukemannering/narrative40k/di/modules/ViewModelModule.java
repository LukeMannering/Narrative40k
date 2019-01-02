package uk.co.lukemannering.narrative40k.di.modules;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import uk.co.lukemannering.narrative40k.viewmodel.CharacterListViewModel;
import uk.co.lukemannering.narrative40k.viewmodel.ViewModelFactory;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CharacterListViewModel.class)
    abstract ViewModel bindCharacterListViewModel(CharacterListViewModel userViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
