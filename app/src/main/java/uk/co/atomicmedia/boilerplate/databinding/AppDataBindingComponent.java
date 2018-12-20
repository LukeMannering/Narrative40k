package uk.co.atomicmedia.boilerplate.databinding;

import javax.inject.Inject;

import uk.co.atomicmedia.boilerplate.util.AppLogger;

public class AppDataBindingComponent implements androidx.databinding.DataBindingComponent {

    private CustomDataBinding mCustomDataBinding;
    private AppLogger mLogger;

    @Inject
    public AppDataBindingComponent(CustomDataBinding customDataBinding, AppLogger logger) {
        mCustomDataBinding = customDataBinding;
        mLogger = logger;
    }

    @Override
    public CustomDataBinding getCustomDataBinding(){
        return mCustomDataBinding;
    }

    @Override
    public CustomAnimationDataBinding getCustomAnimationDataBinding(){
        return new CustomAnimationDataBinding(mLogger);
    }
}