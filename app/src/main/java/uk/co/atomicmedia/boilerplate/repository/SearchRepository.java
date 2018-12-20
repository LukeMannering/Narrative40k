package uk.co.atomicmedia.boilerplate.repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import uk.co.atomicmedia.boilerplate.data.api.JPApiService;
import uk.co.atomicmedia.boilerplate.data.database.JPDatabase;
import uk.co.atomicmedia.boilerplate.data.entities.Auction;
import uk.co.atomicmedia.boilerplate.util.AppLogger;

@Singleton
public class SearchRepository {
    private static final String LOG_TAG = SearchRepository.class.getSimpleName();

    @Inject
    AppLogger mLogger;

    private MutableLiveData<List<Auction>> mAuctions;
    private JPDatabase mDb;
    private final JPApiService mApi;

    @SuppressWarnings("unchecked")
    @Inject
    public SearchRepository(JPApiService apiService, JPDatabase db){
        mApi = apiService;
        mAuctions = new MutableLiveData<>();
//        mAuctions.setValue();
        mDb = db;
    }

    public LiveData<List<Auction>> getAuctions(){
        return mAuctions;
    }

}
