package uk.co.lukemannering.narrative40k.ui.characterlist;

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
import uk.co.lukemannering.narrative40k.R;
import uk.co.lukemannering.narrative40k.databinding.FragmentCharacterListBinding;
import uk.co.lukemannering.narrative40k.di.Injectable;
import uk.co.lukemannering.narrative40k.ui.BackPressInterceptor;
import uk.co.lukemannering.narrative40k.ui.characterlist.handlers.CharacterListFragmentEventListener;
import uk.co.lukemannering.narrative40k.util.AnalyticsLogger;
import uk.co.lukemannering.narrative40k.util.AppLogger;
import uk.co.lukemannering.narrative40k.viewmodel.CharacterListViewModel;

import static uk.co.lukemannering.narrative40k.util.FirebaseAnalyticsLogger.SCREEN_HOME;

public class CharacterListFragment extends Fragment implements Injectable,
        CharacterListFragmentEventListener, BackPressInterceptor {

    public static final String LOG_TAG = CharacterListFragment.class.getSimpleName();

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Inject
    AppLogger mLogger;

    @Inject
    AnalyticsLogger mAnalytics;

    private CharacterListViewModel mViewModel;
    private FragmentCharacterListBinding mBinding;

    public CharacterListFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_character_list, container, false);

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

        mViewModel = ViewModelProviders.of(getActivity(), mViewModelFactory).get(CharacterListViewModel.class);

        mBinding.setViewModel(mViewModel);
        mBinding.setHandlers(this);

        subscribeUi(mViewModel);
    }

    private void subscribeUi(CharacterListViewModel viewModel) {
//        viewModel.characters.observe(this, terms -> {
//
//        });
    }

    @Override
    public boolean handleBackPress() {
        // Do something here and return true if you don't want the activity to handle the back press
        return false;
    }

}
