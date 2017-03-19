package com.pragma.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.pragma.presentation.R;
import com.pragma.presentation.internal.di.HasComponent;
import com.pragma.presentation.internal.di.components.RestaurantResultsComponent;
import com.pragma.presentation.view.fragment.RestaurantDetailsFragment;

/**
 * Created by anbu.ezhilan on 19/03/2017.
 */

public class RestaurantDetailsActivity extends BaseActivity implements HasComponent<RestaurantResultsComponent> {

    private static final String INTENT_EXTRA_PARAM_RESTAURANT_ID = "com.pragma.INTENT_PARAM_RESTAURANT_ID";
    private static final String INTENT_STATE_PARAM_RESTAURANT_ID = "com.pragma.INTENT_STATE_RESTAURANT_ID";

    public static Intent getCallingIntent(Context context, String restaurantId) {
        Intent callingIntent = new Intent(context, RestaurantDetailsActivity.class);
        callingIntent.putExtra(INTENT_EXTRA_PARAM_RESTAURANT_ID, restaurantId);
        return callingIntent;
    }

    private String restaurantId;
    private RestaurantResultsComponent resultsComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_layout);

        this.initializeActivity(savedInstanceState);
        this.initializeInjector();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if ( outState != null) {
            outState.putString(INTENT_STATE_PARAM_RESTAURANT_ID, this.restaurantId);
        }
        super.onSaveInstanceState(outState);
    }

    /**
     * Initialize this Activity
     */
    private void initializeActivity(Bundle savedInstanceState) {
        if ( savedInstanceState == null ) {
            this.restaurantId = getIntent().getStringExtra(INTENT_EXTRA_PARAM_RESTAURANT_ID);
            addFragment(R.id.fragmentContainer, RestaurantDetailsFragment.forRestaurant(restaurantId));
        } else {
            this.restaurantId = savedInstanceState.getString(INTENT_STATE_PARAM_RESTAURANT_ID);
        }
    }

    private void initializeInjector() {
        //this.resultsComponent = Dagger
    }

    @Override
    public RestaurantResultsComponent getComponent() {
        return resultsComponent;
    }
}
