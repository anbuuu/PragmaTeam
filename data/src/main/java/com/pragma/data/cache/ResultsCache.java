package com.pragma.data.cache;

import com.pragma.data.entity.RestaurantEntity;
import com.pragma.data.entity.Result;

import io.reactivex.Observable;

/**
 * An interface representing a Restaurant Cache.
 */

public interface ResultsCache {


    /**
     * Gets an {@link Observable} which will emit a {@link RestaurantEntity}.
     *
     * @param restaurantId The user id to retrieve data.
     */
    //TODO not sure whether restaurantEntity or Result ..
    Observable<Result> get(final String restaurantId);

    /**
     * Puts an element into the ache
     * @param resultEntity to insert into the cache
     */
    void put(Result resultEntity);

    /**
     * Checks if an element (User) exists in the cache.
     *
     * @param restaurantId The id used to look for inside the cache.
     * @return true if the element is cached, otherwise false.
     */
    boolean isCached(final String restaurantId);

    /**
     * Checks if the cache is expired.
     *
     * @return true, the cache is expired, otherwise false.
     */
    boolean isExpired();

    /**
     * Evict all elements of the cache.
     */
    void evictAll();



}
