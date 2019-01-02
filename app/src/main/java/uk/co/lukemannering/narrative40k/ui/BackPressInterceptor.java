package uk.co.lukemannering.narrative40k.ui;

public interface BackPressInterceptor {
    /**
     * Return true if this Fragment has handled the back press.
     * Return false if we want the Activity to handle it.
     * @return
     */
    boolean handleBackPress();
}
