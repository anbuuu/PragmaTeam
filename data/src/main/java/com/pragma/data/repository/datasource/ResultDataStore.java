package com.pragma.data.repository.datasource;

import com.pragma.data.entity.Result;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by anbu.ezhilan on 13/03/2017.
 */

public interface ResultDataStore {

    /**
     * Get an {@link Observable} which will emit a List of {@link Result} //TODO Verify restaurantentity
     *
     */
    Observable<List<Result>> restaurantResultEntityList();

    /**
     * Get an {@link Observable} which will emit a {@link Result} by its Id
     *
     */
    Observable<Result> restaurantResultEntityDetails(final String restaurantId);
}
