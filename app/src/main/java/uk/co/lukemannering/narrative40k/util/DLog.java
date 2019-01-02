package uk.co.lukemannering.narrative40k.util;

import android.content.Context;
import android.util.Log;

import com.crashlytics.android.Crashlytics;

import java.util.ArrayList;

import uk.co.lukemannering.narrative40k.R;

/**
 * Centralised logging methods.
 */
public class DLog implements AppLogger{

    private boolean mLoggingIsEnabled;
    private boolean mCrashlyticsExceptionLoggingEnabled;
    private String mLogTagBase;

    private DLog(boolean mLoggingIsEnabled, boolean crashlyticsExceptionLoggingEnabled, String mLogTagBase) {
        this.mLoggingIsEnabled = mLoggingIsEnabled;
        this.mCrashlyticsExceptionLoggingEnabled = crashlyticsExceptionLoggingEnabled;
        this.mLogTagBase = mLogTagBase;
    }

    /**
     * @param error : Message string to log
     */
    public void log(String error){
        if(mLoggingIsEnabled){
            Log.d(mLogTagBase, error);
        }
    }

    /**
     * @param logTag : Log tag for Log() method
     * @param e : Any exception
     */
    public void log(String logTag, Exception e){
        if(mLoggingIsEnabled){

            String stackTrace = "";
            for (StackTraceElement element :e.getStackTrace() ) {
                stackTrace += element.toString() + "\n";
            }

            Log.e(mLogTagBase + logTag,
                    "Exception: "  + "\n" +
                            e.toString() + "\n" +
                            "Message: " + e.getMessage() + "\n" +
                            "Trace: " + stackTrace);
        }

        // If Crashlytics is enabled, record this as a non-fatal exception.
        if(mCrashlyticsExceptionLoggingEnabled && exceptionShouldBeSentToCrashlytics(e)){
            try{
                Crashlytics.logException(e);
            }catch (Exception ignored){}
        }
    }

    /**
     * @param logTag : Log tag for Log() method
     * @param error : Error/message string
     */
    public void log(String logTag, String error){
        if(mLoggingIsEnabled){
            Log.d(mLogTagBase + logTag, error);
        }
    }

    /**
     * A way to whitelist some exceptions that are known to occur and
     * are not detrimental to the app
     * @param e : Any exception
     * @return boolean: whether we should send this exception to
     */
    private static boolean exceptionShouldBeSentToCrashlytics(Exception e){
        try {
            ArrayList<String> ignoreMessagesContaining = new ArrayList<>();
            //ignoreMessagesContaining.add("Some message string that we don't need to log to Crashlytics");

            for(String substr : ignoreMessagesContaining){
                if(e.getMessage() != null && e.getMessage().toLowerCase().contains(substr.toLowerCase())){
                    return false;
                }
            }
        } catch (Exception ignored) {}

        return true;
    }

    /**
     * Builder pattern
     */
    public static class Builder {
        private boolean mLoggingIsEnabled = false;
        private boolean mCrashlyticsIsEnabled = false;
        private String mLogTagBase = "";

        public Builder setLoggingEnabled(boolean loggingIsEnabled) {
            this.mLoggingIsEnabled = loggingIsEnabled;
            return this;
        }

        public Builder setLogTagBase(String logTagBase) {
            this.mLogTagBase = logTagBase;
            return this;
        }

        public Builder setCrashlyticsExceptionLoggingEnabled(boolean isEnabled) {
            this.mCrashlyticsIsEnabled = isEnabled;
            return this;
        }

        public DLog create() {
            return new DLog(mLoggingIsEnabled, mCrashlyticsIsEnabled, mLogTagBase);
        }

        public DLog createWithContextDefaults(Context context) {
            return new DLog(context.getResources().getBoolean(R.bool.send_custom_logging_to_logcat),
                    context.getResources().getBoolean(R.bool.send_custom_exceptions_to_crashlytics_as_non_fatal),
                    context.getString(R.string.log_tag_base));
        }
    }
}