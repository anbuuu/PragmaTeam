package com.pragma.presentation.view.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.pragma.presentation.R;
import com.pragma.presentation.model.RestaurantModel;
import com.pragma.presentation.presenter.RestaurantListPresenter;
import com.pragma.presentation.view.RestaurantListView;
import com.pragma.presentation.view.adapter.RestaurantsAdapter;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Fragment that shows a list of Restaurants
 */

public class RestaurantListFragment extends BaseFragment implements RestaurantListView {


    /**
     * Interface for Listening User List Events
     * TODO Add the click implementation for booking the restaurant as well
     */
    public interface RestaurantListListener {
        void onRestaurantCilcked(final RestaurantModel restaurantModel);
    }

    @Inject RestaurantListPresenter restaurantListPresenter;
    @Inject RestaurantsAdapter restaurantsAdapter;

    @Bind(R.id.rv_restaurants) RecyclerView rv_Restaurants;
    @Bind(R.id.rl_progress) RelativeLayout rl_progress;
    @Bind(R.id.rl_retry) RelativeLayout rl_retry;
    @Bind(R.id.bt_retry) Button bt_retry;


    private RestaurantListListener restaurantListListener;

    public RestaurantListFragment() {
        setRetainInstance(true);
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public Context context() {
        return null;
    }

    @Override
    public void renderRestaurantList(Collection<RestaurantModel> restaurantModelCollection) {

    }

    @Override
    public void viewRestaurant(RestaurantModel restaurantModel) {

    }
}
