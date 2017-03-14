package com.pragma.data.repository.datasource;

import com.pragma.data.cache.ResultsCache;
import com.pragma.data.entity.Result;
import com.pragma.data.net.RestApi;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by anbu.ezhilan on 13/03/2017.
 */

class CloudResultsDataStore implements ResultDataStore {

    private final RestApi restApi;
    private final ResultsCache resultsCache;


    public CloudResultsDataStore(RestApi restApi, ResultsCache resultsCache) {
        this.restApi = restApi;
        this.resultsCache = resultsCache;
    }

    @Override
    public Observable<List<Result>> restaurantResultEntityList() {
        return this.restApi.restaurantEntityList();
    }

    @Override
    public Observable<Result> restaurantResultEntityDetails(final String restaurantId) {
        return this.restApi.restaurantEntityById(restaurantId);
    }
}
