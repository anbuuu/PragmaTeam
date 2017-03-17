package com.pragma.presentation.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.pragma.presentation.R;
import com.pragma.presentation.internal.di.components.RestaurantResultsComponent;
import com.pragma.presentation.model.RestaurantModel;
import com.pragma.presentation.presenter.RestaurantListPresenter;
import com.pragma.presentation.view.RestaurantListView;
import com.pragma.presentation.view.adapter.RestaurantsAdapter;
import com.pragma.presentation.view.adapter.RestaurantsLayoutManager;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @Bind(R.id.rv_restaurants) RecyclerView rv_restaurants;
    @Bind(R.id.rl_progress) RelativeLayout rl_progress;
    @Bind(R.id.rl_retry) RelativeLayout rl_retry;
    @Bind(R.id.bt_retry) Button bt_retry;

    private RestaurantListListener restaurantListListener;

    public RestaurantListFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Activity activity) { //TODO Verify deprecated method
        super.onAttach(activity);
        this.restaurantListListener = (RestaurantListListener) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(RestaurantResultsComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View fragmentView = layoutInflater.inflate(R.layout.fragment_restaurant_list,
                container, false);
        ButterKnife.bind(this, fragmentView);
        setupRecyclerView();
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.restaurantListPresenter.setView(this);
        if ( savedInstanceState == null ) {
            this.loadRestaurantList();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        this.restaurantListPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.restaurantListPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        rv_restaurants.setAdapter(null);
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.restaurantListPresenter.destroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.restaurantListPresenter = null;
    }

    @Override
    public void showLoading() {
        this.rl_progress.setVisibility(View.VISIBLE);
        this.getActivity().setProgressBarIndeterminateVisibility(true);
        // TODO Fix the deprecated method
    }

    @Override
    public void hideLoading() {
        this.rl_progress.setVisibility(View.GONE);
        this.getActivity().setProgressBarIndeterminateVisibility(false);
    }

    @Override
    public void showRetry() {
        this.rl_retry.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        this.rl_retry.setVisibility(View.GONE);
    }

    @Override
    public void renderRestaurantList(Collection<RestaurantModel> restaurantModelCollection) {
        if ( restaurantModelCollection != null ) {
            this.restaurantsAdapter.setRestaurantsCollection(restaurantModelCollection);
        }
    }

    @Override
    public void viewRestaurant(RestaurantModel restaurantModel) {
        if ( this.restaurantListListener != null ) {
            this.restaurantListListener.onRestaurantCilcked(restaurantModel);
        }
    }

    @Override
    public void showError(String message) {
        this.showToastMessage(message);
    }

    @Override
    public Context context() {
        return this.getActivity().getApplicationContext();
    }

    private void setupRecyclerView() {
        this.restaurantsAdapter.setOnItemClickListener(onItemClickListener);
        this.rv_restaurants.setLayoutManager(new RestaurantsLayoutManager(context()));
        this.rv_restaurants.setAdapter(restaurantsAdapter);
    }

    /**
     * Loads all Restaurants
     */
    private void loadRestaurantList() {
        this.restaurantListPresenter.initialize();
    }

    @OnClick(R.id.bt_retry)
    void onButtonRetryClick() {
        RestaurantListFragment.this.loadRestaurantList();
    }

    private RestaurantsAdapter.OnItemClickListener onItemClickListener =
            new RestaurantsAdapter.OnItemClickListener() {
                @Override
                public void onRestaurantItemClicked(RestaurantModel restaurantModel) {
                    if ( RestaurantListFragment.this.restaurantListPresenter != null &&
                            restaurantModel != null) {
                        RestaurantListFragment.this.restaurantListPresenter.onRestaurantClicked(restaurantModel);
                    }
                }
            };

}
