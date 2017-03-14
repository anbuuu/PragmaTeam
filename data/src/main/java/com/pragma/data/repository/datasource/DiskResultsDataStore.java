package com.pragma.data.repository.datasource;

import com.pragma.data.cache.ResultsCache;
import com.pragma.data.entity.Result;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by anbu.ezhilan on 13/03/2017.
 */

class DiskResultsDataStore implements ResultDataStore {

    private final ResultsCache resultsCache;

    /**
     * Construct a {@link ResultDataStore} based file system data store
     * @param resultsCache A {@link ResultsCache} to cache data retrieved from api.
     */
    DiskResultsDataStore(ResultsCache resultsCache) {
        this.resultsCache = resultsCache;
    }


    @Override
    public Observable<List<Result>> restaurantResultEntityList() {
        throw new UnsupportedOperationException("Operation is not available");
    }

    @Override
    public Observable<Result> restaurantResultEntityDetails(final String restaurantId) {
        return this.resultsCache.get(restaurantId);
    }

}
