package com.pragma.presentation.presenter;

import android.support.annotation.NonNull;

import com.pragma.domain.RestaurantResults;
import com.pragma.domain.exception.ErrorBundle;
import com.pragma.domain.interactor.DefaultObserver;
import com.pragma.domain.interactor.GetRestaurantDetails;
import com.pragma.presentation.exception.ErrorMessageFactory;
import com.pragma.presentation.internal.di.PerActivity;
import com.pragma.presentation.mapper.RestaurantModelDataWrapper;
import com.pragma.presentation.model.RestaurantModel;
import com.pragma.presentation.view.RestaurantDetailsView;

/**
 * Created by anbu.ezhilan on 18/03/2017.
 */

@PerActivity
public class RestaurantDetailsPresenter implements Presenter{

    private RestaurantDetailsView viewRestaurantDetailsView;

    private final GetRestaurantDetails getRestaurantDetailsUseCase;
    private final RestaurantModelDataWrapper restaurantModelDataWrapper;

    public RestaurantDetailsPresenter(GetRestaurantDetails getRestaurantDetailsUseCase,
                                      RestaurantModelDataWrapper restaurantModelDataWrapper) {
        this.getRestaurantDetailsUseCase = getRestaurantDetailsUseCase;
        this.restaurantModelDataWrapper = restaurantModelDataWrapper;
    }

    public void setView(@NonNull RestaurantDetailsView view) { this.viewRestaurantDetailsView = view;}

    /**
     * Initialize the presenter by showing/hiding proper views and retrieving user details
     */
    public void initialize(String restaurantId) {
        this.hideViewRetry();
        this.showViewLoading();
        this.getRestaurantDetails(restaurantId);
    }

    private void showViewLoading() {
        this.viewRestaurantDetailsView.showLoading();
    }

    private void hideViewLoading() {
        this.viewRestaurantDetailsView.hideLoading();
    }
    private void hideViewRetry() {
        this.viewRestaurantDetailsView.hideRetry();
    }

    private void showViewRetry() {
        this.viewRestaurantDetailsView.showRetry();
    }


    private void showErrorMessage(ErrorBundle errorbundle) {
        String errorMessage = ErrorMessageFactory.create(this.viewRestaurantDetailsView.context(),
                                    errorbundle.getException());
        this.viewRestaurantDetailsView.showError(errorMessage);
    }


    private void getRestaurantDetails(String restaurantId) {
        this.getRestaurantDetailsUseCase.execute(new RestaurantDetailsObserver(),
                GetRestaurantDetails.Params.forRestaurant(restaurantId));
    }

    private void showRestaurantDetailsInView(RestaurantResults restaurantResults) {
        final RestaurantModel restaurantModel = this.restaurantModelDataWrapper.transform(restaurantResults);
        this.viewRestaurantDetailsView.renderRestaurant(restaurantModel);

    }

    private final class RestaurantDetailsObserver extends DefaultObserver<RestaurantResults> {
        @Override
        public void onNext(RestaurantResults restaurantResults) {
            RestaurantDetailsPresenter.this.showRestaurantDetailsInView(restaurantResults);

        }

        @Override
        public void onComplete() {
            RestaurantDetailsPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable exception) {
            RestaurantDetailsPresenter.this.hideViewLoading();
            // TODO RestaurantDetailsPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) ex));
            RestaurantDetailsPresenter.this.showViewRetry();
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
        this.getRestaurantDetailsUseCase.dispose();
        this.viewRestaurantDetailsView = null;
    }



}
