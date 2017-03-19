package com.pragma.presentation.view;

import com.pragma.presentation.model.RestaurantModel;

/**
 * Created by anbu.ezhilan on 18/03/2017.
 */

public interface RestaurantDetailsView extends LoadDataView {
    /**
     * Render a Restaurant to UI
     * @param restaurant The {@link RestaurantModel} that will be shown
     */
    void renderRestaurant(RestaurantModel restaurant);



}
