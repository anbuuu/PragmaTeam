package com.pragma.presentation.view;

import com.pragma.presentation.model.RestaurantModel;

import java.util.Collection;

/**
 * Interface representing a view in a MVP Pattern.
 * In this case is used as a view of representing a list of {@link RestaurantModel}
 */

public interface RestaurantListView extends LoadDataView {

    /**
     * Render a User List in the UI
     * @param restaurantModelCollection The Collection of {@link RestaurantModel}
     */
    void renderRestaurantList(Collection<RestaurantModel> restaurantModelCollection);

    /**
     * View a {@link RestaurantModel} restaurant details.
     *
     * @param restaurantModel The restaurant that will be shown.
     */
    void viewRestaurant(RestaurantModel restaurantModel);

}
