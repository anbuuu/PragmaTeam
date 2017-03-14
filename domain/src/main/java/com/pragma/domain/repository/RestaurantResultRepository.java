package com.pragma.domain.repository;

import com.pragma.domain.RestaurantResults;

import java.util.List;

import io.reactivex.Observable;

/**
 * Interface that represents a Repository for getting {@link com.pragma.domain.RestaurantResults}
 */

public interface RestaurantResultRepository {

    /**
     * Get an {@link io.reactivex.Observable} which will emit a List of {@link com.pragma.domain.RestaurantResults}
     *
     */
    Observable<List<RestaurantResults>> restaurantResults();

    /**
     * @param restaurantId The restaurant ID used to retrieve User Data
     */
    Observable<RestaurantResults> restaurant(final String restaurantId);

}
