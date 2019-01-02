package uk.co.lukemannering.narrative40k.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import uk.co.lukemannering.narrative40k.data.database.AppDatabase;
import uk.co.lukemannering.narrative40k.util.AppLogger;

@Singleton
public class CharacterRepository {
    private static final String LOG_TAG = CharacterRepository.class.getSimpleName();

    @Inject
    AppLogger mLogger;

    //private MutableLiveData<List<Character>> mCharacters;
    private AppDatabase mDb;

    @SuppressWarnings("unchecked")
    @Inject
    public CharacterRepository(AppDatabase db){
//        mCharacters = new MutableLiveData<>();
//        mCharacters.setValue();
        mDb = db;
    }

//    public LiveData<List<Character>> getCharacters(){
//        return mCharacters;
//    }

}
