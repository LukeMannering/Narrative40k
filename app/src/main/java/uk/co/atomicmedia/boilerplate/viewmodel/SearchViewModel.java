package uk.co.atomicmedia.boilerplate.viewmodel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import uk.co.atomicmedia.boilerplate.data.entities.Auction;
import uk.co.atomicmedia.boilerplate.repository.SearchRepository;
import uk.co.atomicmedia.boilerplate.util.AppLogger;

public class SearchViewModel extends ViewModel {

    public static final String LOG_TAG = SearchViewModel.class.getSimpleName();

    AppLogger mLogger;

    public MutableLiveData<String> searchTerms = new MutableLiveData<>();

    private MediatorLiveData<List<Auction>> mObservableAuctions = new MediatorLiveData<>();

    private SearchRepository mSearchRepository;

    @SuppressWarnings("unchecked")
    @Inject
    public SearchViewModel(AppLogger logger, SearchRepository searchRepository) {
        mLogger = logger;
        mSearchRepository = searchRepository;
        mObservableAuctions.setValue(new ArrayList<>());


        mObservableAuctions.addSource(mSearchRepository.getAuctions(), auctions -> mObservableAuctions.setValue(auctions));

    }
}
