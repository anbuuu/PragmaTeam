package com.pragma.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.Window;

import com.pragma.presentation.R;
import com.pragma.presentation.internal.di.HasComponent;
import com.pragma.presentation.internal.di.components.RestaurantResultsComponent;
import com.pragma.presentation.model.RestaurantModel;
import com.pragma.presentation.view.fragment.RestaurantListFragment;

/**
 * Created by anbu.ezhilan on 19/03/2017.
 */

public class RestaurantListActivity extends BaseActivity implements HasComponent<RestaurantResultsComponent>,
        RestaurantListFragment.RestaurantListListener {

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, RestaurantListActivity.class);
    }

    private RestaurantResultsComponent restaurantResultsComponent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        // TODO -- check again
        super.onCreate(savedInstanceState, persistentState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_layout);

        this.initializeInjector();
        if ( savedInstanceState == null ) {
            addFragment(R.id.fragmentContainer, new RestaurantListFragment());
        }
    }

    private void initializeInjector() {
        // TODO this.restaurantResultsComponent = DaggerRestaurantResultsComponent.
    }

    @Override
    public RestaurantResultsComponent getComponent() {
        return restaurantResultsComponent;
    }

    @Override
    public void onRestaurantCilcked(RestaurantModel restaurantModel) {
        this.navigator.navigateToRestaurantList(this);

    }
}
