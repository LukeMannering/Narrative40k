package uk.co.lukemannering.narrative40k.viewmodel;

import javax.inject.Inject;

import androidx.lifecycle.ViewModel;
import uk.co.lukemannering.narrative40k.repository.CharacterRepository;
import uk.co.lukemannering.narrative40k.util.AppLogger;

public class CharacterListViewModel extends ViewModel {

    public static final String LOG_TAG = CharacterListViewModel.class.getSimpleName();

    AppLogger mLogger;

   // private MediatorLiveData<List<Characters>> mObservableCharacters = new MediatorLiveData<>();

    private CharacterRepository mCharacterRepository;

    @SuppressWarnings("unchecked")
    @Inject
    public CharacterListViewModel(AppLogger logger, CharacterRepository characterRepository) {
        mLogger = logger;
        mCharacterRepository = characterRepository;
//        mObservableCharacters.setValue(new ArrayList<>());
//        mObservableCharacters.addSource(mCharacterRepository.getCharacters(), characters -> mObservableCharacters.setValue(characters));

    }
}
