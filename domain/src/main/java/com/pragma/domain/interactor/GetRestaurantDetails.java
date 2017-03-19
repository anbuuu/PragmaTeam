package com.pragma.domain.interactor;

import com.fernandocejas.arrow.checks.Preconditions;
import com.pragma.domain.RestaurantResults;
import com.pragma.domain.executor.PostExecutionThread;
import com.pragma.domain.executor.ThreadExecutor;
import com.pragma.domain.repository.RestaurantResultRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by anbu.ezhilan on 18/03/2017.
 */

public class GetRestaurantDetails extends UseCase<RestaurantResults, GetRestaurantDetails.Params> {

    private final RestaurantResultRepository restaurantResultRepository;

    @Inject
    GetRestaurantDetails(RestaurantResultRepository restaurantResultRepository, ThreadExecutor threadExecutor,
                         PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.restaurantResultRepository = restaurantResultRepository;
    }

    @Override
    Observable<RestaurantResults> buildUseCaseObservable(GetRestaurantDetails.Params params) {
        Preconditions.checkNotNull(params);
        return this.restaurantResultRepository.restaurant(params.restaurantId);
    }

    public static final class Params {
        private final String restaurantId;
        private Params(String restaurantId) { this.restaurantId = restaurantId;}

        public static Params forRestaurant(String restaurantId) { return new Params(restaurantId);}

    }
}
