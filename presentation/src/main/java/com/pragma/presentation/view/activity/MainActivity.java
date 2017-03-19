package com.pragma.presentation.view.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.pragma.presentation.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Main Application Screen -- App Entry Point
 */

public class MainActivity extends BaseActivity {

    @Bind(R.id.btn_LoadData)
    Button btn_LoadData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        //TODO verify this
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    /**
     * Goes to Restaurant List Screen
     */
    @OnClick(R.id.btn_LoadData)
    void navigateToRestaurantList() {
        this.navigator.navigateToRestaurantList(this);
    }

}
