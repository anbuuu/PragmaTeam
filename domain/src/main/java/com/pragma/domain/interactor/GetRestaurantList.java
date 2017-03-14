package com.pragma.domain.interactor;

import com.pragma.domain.RestaurantResults;
import com.pragma.domain.executor.PostExecutionThread;
import com.pragma.domain.executor.ThreadExecutor;
import com.pragma.domain.repository.RestaurantResultRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by anbu.ezhilan on 15/03/2017.
 */

public class GetRestaurantList extends UseCase<List<RestaurantResults>, Void> {


    private final RestaurantResultRepository restaurantResultRepository;

    @Inject
    GetRestaurantList(RestaurantResultRepository restaurantResultRepository,
                      ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.restaurantResultRepository = restaurantResultRepository;
    }

    @Override
    Observable<List<RestaurantResults>> buildUseCaseObservable(Void aVoid) {
        return null;
    }
}
