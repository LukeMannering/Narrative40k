package uk.co.atomicmedia.boilerplate.databinding;

import android.os.Handler;
import android.view.View;

import androidx.constraintlayout.widget.Group;
import androidx.databinding.BindingAdapter;
import uk.co.atomicmedia.boilerplate.util.AppLogger;

public class CustomAnimationDataBinding {

    private static final String LOG_TAG = CustomAnimationDataBinding.class.getSimpleName();
    private AppLogger mLogger;

    public CustomAnimationDataBinding(AppLogger logger) {
        mLogger = logger;
    }

    @BindingAdapter({"custom_delayedAppearanceVisibility"})
    public void bind(Group group, int visibility) {
        try {
            int oldVisibility = group.getVisibility();

            if (oldVisibility == visibility) {

                // if we've changed the visibility back before the animation took place, we need to cancel it
                Handler animationHandler = (Handler)group.getTag();

                if(animationHandler != null){
                    animationHandler.removeCallbacksAndMessages(null);
                }

                // visibility hasn't changed
                return;
            }

            if (visibility == View.VISIBLE) {
                Handler animationHandler = new Handler();

                group.setTag(animationHandler);

                animationHandler.postDelayed(() -> group.setVisibility(visibility), 300);
            } else {
                group.setVisibility(visibility);
            }

        } catch (Exception e) {
            group.setVisibility(visibility);
            mLogger.log(LOG_TAG, e);
        }
    }
}
