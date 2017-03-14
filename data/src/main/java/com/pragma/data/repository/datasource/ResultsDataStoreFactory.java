package com.pragma.data.repository.datasource;

import android.content.Context;
import android.support.annotation.NonNull;

import com.pragma.data.cache.ResultsCache;
import com.pragma.data.entity.mapper.RestaurantEntityJsonMapper;
import com.pragma.data.net.RestApi;
import com.pragma.data.net.RestApiImpl;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link  RepositoryDataStore }
 */

@Singleton
class ResultsDataStoreFactory {

    private final Context context;
    private final ResultsCache resultsCache;

    @Inject
    ResultsDataStoreFactory(@NonNull Context context, @NonNull ResultsCache resultsCache) {
        this.context = context;
        this.resultsCache = resultsCache;
    }

    /**
     * Create {@link ResultDataStore} from a Restaurant ID
     *
     */
    public ResultDataStore create(String restaurantId) {
        ResultDataStore resultDataStore;

        if ( !this.resultsCache.isExpired() && this.resultsCache.isCached(restaurantId)) {
            resultDataStore = new DiskResultsDataStore(this.resultsCache);
        } else {
            resultDataStore = createCloudDataStore();
        }

        return resultDataStore;

    }

    /**
     * Create {@link ResultDataStore} to retrieve data from Cloud
     */
    public ResultDataStore createCloudDataStore() {
        //TODO Seems like this should be result not restaurantentity
        final RestaurantEntityJsonMapper restaurantEntityJsonMapper = new RestaurantEntityJsonMapper();
        final RestApi restApi = new RestApiImpl(this.context, restaurantEntityJsonMapper);

        return new CloudResultsDataStore(restApi, this.resultsCache);
    }

}
