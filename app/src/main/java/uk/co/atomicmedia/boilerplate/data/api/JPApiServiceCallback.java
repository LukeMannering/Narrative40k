package uk.co.atomicmedia.boilerplate.data.api;

public interface JPApiServiceCallback<T> {
    void onError(Exception e);
    void onSuccess(T results);
    default void onComplete(T results, Exception e){
        // do nothing
    }
}
