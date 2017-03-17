package com.pragma.presentation.presenter;

import android.support.annotation.NonNull;

import com.pragma.domain.RestaurantResults;
import com.pragma.domain.exception.DefaultErrorBundle;
import com.pragma.domain.exception.ErrorBundle;
import com.pragma.domain.interactor.DefaultObserver;
import com.pragma.domain.interactor.GetRestaurantList;
import com.pragma.presentation.exception.ErrorMessageFactory;
import com.pragma.presentation.internal.di.PerActivity;
import com.pragma.presentation.mapper.RestaurantModelDataWrapper;
import com.pragma.presentation.model.RestaurantModel;
import com.pragma.presentation.view.RestaurantListView;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by anbu.ezhilan on 16/03/2017.
 */
@PerActivity
public class RestaurantListPresenter implements Presenter {

    private RestaurantListView restaurantListView;

    private final GetRestaurantList getRestaurantList;
    private final RestaurantModelDataWrapper restaurantModelDataWrapper;


    @Inject
    public RestaurantListPresenter(GetRestaurantList getRestaurantList,
                                   RestaurantModelDataWrapper restaurantModelDataWrapper) {
        this.getRestaurantList = getRestaurantList;
        this.restaurantModelDataWrapper = restaurantModelDataWrapper;
    }

    public void setView(@NonNull RestaurantListView view) {
        this.restaurantListView = view;
    }


    /**
     * Initialize the presenter by retrieving the Restaurant List
     */
    public void initialize() {
        this.loadRestaurantList();
    }

    /**
     * Loads all Restaurants
     */
    private void loadRestaurantList() {
        this.hideViewRetry();
        this.showViewLoading();
        this.getRestaurantList();
    }

    public void onRestaurantClicked(RestaurantModel restaurantModel) {
        this.restaurantListView.viewRestaurant(restaurantModel);
    }

    private void showViewLoading() {
        this.restaurantListView.showLoading();
    }

    private void hideViewLoading() {
        this.restaurantListView.hideLoading();
    }

    private void showViewRetry() {
        this.restaurantListView.showRetry();
    }

    private void hideViewRetry() {
        this.restaurantListView.hideRetry();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(
                this.restaurantListView.context(),
                errorBundle.getException());
        this.restaurantListView.showError(errorMessage);
    }

    private void showRestaurantsCollectionInView(
                    Collection<RestaurantResults> restaurantsCollection) {
        final Collection<RestaurantModel> restaurantModelsCollection =
                this.restaurantModelDataWrapper.transform(restaurantsCollection);
        this.restaurantListView.renderRestaurantList(restaurantModelsCollection);
    }

    private void getRestaurantList() {
        this.getRestaurantList.execute(new RestaurantListObserver(), null);
    }

    private final class RestaurantListObserver extends
            DefaultObserver<List<RestaurantResults>> {

        @Override
        public void onComplete() {
            RestaurantListPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            RestaurantListPresenter.this.hideViewLoading();
            RestaurantListPresenter.this.showErrorMessage(
                    new DefaultErrorBundle((Exception) e));

            RestaurantListPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(List<RestaurantResults> restaurants)
        {
            RestaurantListPresenter.this.showRestaurantsCollectionInView(restaurants);
        }
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.getRestaurantList.dispose();
        this.restaurantListView = null;
    }
}
