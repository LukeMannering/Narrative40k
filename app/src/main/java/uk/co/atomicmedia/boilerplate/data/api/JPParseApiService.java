package uk.co.atomicmedia.boilerplate.data.api;

import javax.inject.Inject;

import uk.co.atomicmedia.boilerplate.data.api.response.ApiResultSearch;
import uk.co.atomicmedia.boilerplate.util.AppLogger;

public class JPParseApiService implements JPApiService {

    private final static String LOG_TAG = JPParseApiService.class.getSimpleName();

    AppLogger mLogger;

    @Inject
    public JPParseApiService(AppLogger logger) {
        this.mLogger = logger;
    }

    @Override
    public void search(final String searchTerms, final JPApiServiceCallback<ApiResultSearch> callback) {

    }

}
