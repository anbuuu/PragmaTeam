package com.pragma.data.entity.mapper;

import com.pragma.data.entity.RestaurantEntity;
import com.pragma.data.entity.Result;
import com.pragma.domain.RestaurantResults;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by anbu.ezhilan on 12/03/2017.
 */

public class RestaurantEntityDataMapper {

    @Inject
    RestaurantEntityDataMapper() {

    }

    /**
     * Transform a List of {@link com.pragma.data.entity.RestaurantEntity}
     * @param resultCollection object Collection to be transformed
     * @return {@link com.pragma.data.entity.Result}
     */
    public List<RestaurantResults> transform(Collection<Result> resultCollection) {
        final List<RestaurantResults> restaurantResultsList = new ArrayList<>(20); //TODO
        for ( Result result : resultCollection) {
            final RestaurantResults restaurantResults = transform(result);
            if ( restaurantResults != null) {
                restaurantResultsList.add(restaurantResults);
            }
        }
        return restaurantResultsList;
    }

    /**
     * Transform a {@link RestaurantEntity} into an {@link RestaurantResults}.
     *
     * @param result Object to be transformed.
     * @return {@link RestaurantResults} if valid {@link Result} otherwise null.
     */
    public RestaurantResults transform(Result result) {
        RestaurantResults restaurantResults = null;

        if ( result != null ) {
            restaurantResults = new RestaurantResults(result.getId());
            restaurantResults.setName(result.getName());
            restaurantResults.setRating(result.getRating());
            //TODO restaurantResults.getOpeningHours(result.getOpeningHours().getOpenNow());
        }
        return restaurantResults;
    }


}
