package uk.co.atomicmedia.boilerplate.ui;

public interface BackPressInterceptor {
    /**
     * Return true if this Fragment has handled the back press.
     * Return false if we want the Activity to handle it.
     * @return
     */
    boolean handleBackPress();
}
