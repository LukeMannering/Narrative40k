package uk.co.atomicmedia.boilerplate.util;

public interface AppLogger{
    void log(String error);
    void log(String logTag, Exception e);
    void log(String logTag, String error);
}
