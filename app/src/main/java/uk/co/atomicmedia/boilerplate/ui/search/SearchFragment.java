package uk.co.atomicmedia.boilerplate.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import uk.co.atomicmedia.boilerplate.R;
import uk.co.atomicmedia.boilerplate.databinding.FragmentSearchBinding;
import uk.co.atomicmedia.boilerplate.di.Injectable;
import uk.co.atomicmedia.boilerplate.ui.BackPressInterceptor;
import uk.co.atomicmedia.boilerplate.ui.search.handlers.SearchFragmentEventListener;
import uk.co.atomicmedia.boilerplate.util.AnalyticsLogger;
import uk.co.atomicmedia.boilerplate.util.AppLogger;
import uk.co.atomicmedia.boilerplate.viewmodel.SearchViewModel;

import static uk.co.atomicmedia.boilerplate.util.FirebaseAnalyticsLogger.SCREEN_HOME;

public class SearchFragment extends Fragment implements Injectable,
        SearchFragmentEventListener, BackPressInterceptor {

    public static final String LOG_TAG = SearchFragment.class.getSimpleName();

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Inject
    AppLogger mLogger;

    @Inject
    AnalyticsLogger mAnalytics;

    private SearchViewModel mViewModel;
    private FragmentSearchBinding mBinding;

    public SearchFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_search, container, false);

        mAnalytics.logScreenView(SCREEN_HOME);

        mBinding = DataBindingUtil.bind(view);

        if(mBinding != null){
            mBinding.setLifecycleOwner(this);
        }

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(getActivity(), mViewModelFactory).get(SearchViewModel.class);

        mBinding.setViewModel(mViewModel);
        mBinding.setHandlers(this);

        subscribeUi(mViewModel);
    }

    private void subscribeUi(SearchViewModel viewModel) {
        viewModel.searchTerms.observe(this, terms -> {

        });
    }

    @Override
    public boolean handleBackPress() {
        // Do something here and return true if you don't want the activity to handle the back press
        return false;
    }

}
