package com.pragma.data.repository.datasource;

import com.pragma.data.entity.mapper.RestaurantEntityDataMapper;
import com.pragma.domain.RestaurantResults;
import com.pragma.domain.repository.RestaurantResultRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * {@link com.pragma.domain.repository.RestaurantResultRepository} for retrieving User Data
 */

@Singleton
public class ResultDataRepository implements RestaurantResultRepository {


    private final ResultsDataStoreFactory resultsDataStoreFactory;
    private final RestaurantEntityDataMapper restaurantEntityDataMapper;

    /**
     * Constructs a {@link RestaurantResultRepository}
     *
     * @param dataStoreFactory A factory to construct different data source implementations
     * @param restaurantEntityDataMapper {@link RestaurantEntityDataMapper}
     *
     */
    @Inject
    ResultDataRepository(ResultsDataStoreFactory dataStoreFactory,
                         RestaurantEntityDataMapper restaurantEntityDataMapper) {
        this.resultsDataStoreFactory = dataStoreFactory;
        this.restaurantEntityDataMapper = restaurantEntityDataMapper;
    }


    @Override
    public Observable<List<RestaurantResults>> restaurantResults() {
        return null;
    }

    @Override
    public Observable<RestaurantResults> restaurant(String restaurantId) {
        return null;
    }
}
