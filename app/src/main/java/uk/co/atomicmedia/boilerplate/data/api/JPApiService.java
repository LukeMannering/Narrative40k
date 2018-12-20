package uk.co.atomicmedia.boilerplate.data.api;

import uk.co.atomicmedia.boilerplate.data.api.response.ApiResultSearch;

public interface JPApiService {

    void search(String searchTerms, JPApiServiceCallback<ApiResultSearch> callback);
}
