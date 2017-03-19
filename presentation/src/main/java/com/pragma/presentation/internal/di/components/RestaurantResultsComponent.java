package com.pragma.presentation.internal.di.components;

import com.pragma.presentation.internal.di.PerActivity;
import com.pragma.presentation.internal.di.modules.ActivityModule;
import com.pragma.presentation.internal.di.modules.RestaurantModule;
import com.pragma.presentation.view.fragment.RestaurantDetailsFragment;
import com.pragma.presentation.view.fragment.RestaurantListFragment;

import dagger.Component;

/**
 * Created by anbu.ezhilan on 15/03/2017.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, RestaurantModule.class})
public interface RestaurantResultsComponent extends ActivityComponent {
    void inject(RestaurantListFragment restaurantListFragment);
    void inject(RestaurantDetailsFragment restaurantDetailFragment);


}

