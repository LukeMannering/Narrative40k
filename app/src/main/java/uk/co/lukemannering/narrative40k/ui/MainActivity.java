package uk.co.lukemannering.narrative40k.ui;

import android.os.Bundle;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import uk.co.lukemannering.narrative40k.R;
import uk.co.lukemannering.narrative40k.util.AppLogger;

public class MainActivity extends AppCompatActivity implements
        HasSupportFragmentInjector{

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Inject
    DispatchingAndroidInjector<Fragment> mDispatchingAndroidInjector;

    @Inject
    AppLogger mLogger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return mDispatchingAndroidInjector;
    }

    /**
     * If the current fragment implements BackPressInterceptor, give it the
     * opportunity to handle the back press instead of this activity.
     */
    @Override
    public void onBackPressed() {

        try {
            NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.nav_host_fragment);

            Fragment currentFragment = null;

            if(navHostFragment != null){
                currentFragment = navHostFragment.getChildFragmentManager().getFragments().get(0);
            }

            if(currentFragment instanceof BackPressInterceptor && ((BackPressInterceptor) currentFragment).handleBackPress()){
                return;
            }

        } catch (Exception e) {
            mLogger.log(LOG_TAG, e);
        }

        super.onBackPressed();
    }
}
