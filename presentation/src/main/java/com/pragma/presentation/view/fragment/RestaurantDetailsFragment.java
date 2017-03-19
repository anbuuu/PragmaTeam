package com.pragma.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fernandocejas.arrow.checks.Preconditions;
import com.pragma.presentation.R;
import com.pragma.presentation.model.RestaurantModel;
import com.pragma.presentation.presenter.RestaurantDetailsPresenter;
import com.pragma.presentation.view.RestaurantDetailsView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by anbu.ezhilan on 18/03/2017.
 */

public class RestaurantDetailsFragment extends BaseFragment implements RestaurantDetailsView {

    private static final String PARAM_RESTAURANT_ID = "param_restauant_id";

    @Inject
    RestaurantDetailsPresenter restaurantDetailsPresenter;

    // Restaurant Name, Distance, Rating, Opening Hours
    @Bind(R.id.tv_fullname) TextView tv_fullname;
    @Bind(R.id.tv_rating) TextView tv_rating;
    @Bind(R.id.tv_opening_hours) TextView tv_opening_hours;
    @Bind(R.id.rl_progress) RelativeLayout rl_progress;
    @Bind(R.id.rl_retry) RelativeLayout rl_retry;
    @Bind(R.id.bt_retry) RelativeLayout bt_retry;

    public static RestaurantDetailsFragment forRestaurant(String restaurantId) {
        final RestaurantDetailsFragment restaurantDetailsFragment = new RestaurantDetailsFragment();
        final Bundle arguments = new Bundle();
        arguments.putString(PARAM_RESTAURANT_ID, restaurantId);
        restaurantDetailsFragment.setArguments(arguments);
        return restaurantDetailsFragment;
    }

    public RestaurantDetailsFragment() {
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_restaurant_details, container, false);
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.restaurantDetailsPresenter.setView(this);
        if ( savedInstanceState == null ) {
            this.loadRestaurantDetails();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        this.restaurantDetailsPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.restaurantDetailsPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.restaurantDetailsPresenter.destroy();
    }

    @Override
    public void renderRestaurant(RestaurantModel restaurant) {
        if ( restaurant != null ) {
            this.tv_fullname.setText(restaurant.getName());
            this.tv_rating.setText(restaurant.getRating());
            this.tv_opening_hours.setText(restaurant.getOpeningHours());
        }
    }


    @Override
    public void showLoading() {
        this.rl_progress.setVisibility(View.VISIBLE);
        //TODO fix the deprecated ones
        this.getActivity().setProgressBarIndeterminateVisibility(true);
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
    public void showError(String message) {
        this.showToastMessage(message);
    }

    @Override
    public Context context() {
        return getActivity().getApplicationContext();
    }


    private void loadRestaurantDetails() {
        if ( this.restaurantDetailsPresenter != null ) {
            this.restaurantDetailsPresenter.initialize(currentRestaurantId());
        }
    }

    /**
     * Get Current Restaurant Id
     */
    private String currentRestaurantId() {
        final Bundle arguments = getArguments();
        Preconditions.checkNotNull(arguments, "Fragment Arguments cannot be null");
        return arguments.getString(PARAM_RESTAURANT_ID);
    }

    @OnClick(R.id.bt_retry)
    void onButtonRetryClick() { RestaurantDetailsFragment.this.loadRestaurantDetails();}

}
