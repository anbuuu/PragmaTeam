package com.pragma.presentation.mapper;

import com.pragma.domain.RestaurantResults;
import com.pragma.presentation.internal.di.PerActivity;
import com.pragma.presentation.model.RestaurantModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

/**
 * Mapper class used to transform {@link com.pragma.domain.RestaurantResults} in the
 * domain layer to {@link com.pragma.presentation.model.RestaurantModel} in
 * Presentation Layer
 */

@PerActivity
public class RestaurantModelDataWrapper {

    @Inject
    public RestaurantModelDataWrapper() {}

    /**
     * Transform a {@link RestaurantResults} into an {@link com.pragma.presentation.model.RestaurantModel}
     *
     * @param restaurantResults object to be transformed
     * @return {@link com.pragma.presentation.model.RestaurantModel}
     *
     */
    public RestaurantModel transform(RestaurantResults restaurantResults) {
        if ( restaurantResults == null) {
            throw new IllegalArgumentException("Cannot Transform a Null Value");
        }

        final RestaurantModel restaurantModel = new RestaurantModel(restaurantResults.getId());
        restaurantModel.setName(restaurantResults.getName());
        restaurantModel.setOpeningHours(restaurantResults.getOpeningHours());
        restaurantModel.setRating(restaurantResults.getRating());


        return restaurantModel;

    }

    /**
     * Transform a Collection of {@link RestaurantResults} into a collection of
     * {@link RestaurantModel}
     *
     * @param restaurantResultsCollection Objects to be transformed
     * @return List of {@link RestaurantModel}
     *
     */
    public Collection<RestaurantModel> transform(Collection<RestaurantResults>
                                                         restaurantResultsCollection) {

        Collection<RestaurantModel> restaurantModelCollection;

        if ( restaurantResultsCollection != null &&
                !restaurantResultsCollection.isEmpty()) {
            restaurantModelCollection = new ArrayList<>();
            for ( RestaurantResults restaurantResults : restaurantResultsCollection) {
                restaurantModelCollection.add(transform(restaurantResults));
            }
        } else {
            restaurantModelCollection = Collections.emptyList();
        }
        return restaurantModelCollection;
    }

}
