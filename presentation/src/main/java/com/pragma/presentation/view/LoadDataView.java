package com.pragma.presentation.view;

import android.content.Context;

/**
 * Interface representing a View that will use to load data
 */

public interface LoadDataView {

    /**
     * Show a view with a progress bar indicating a loading process
     */
    void showLoading();

    /**
     * Hide a loading view
     */
    void hideLoading();

    /**
     * Show a Retry View in case of an error while retrieving Data
     */
    void showRetry();

    /**
     * Show a Retry view in case of error when retrieving data
     */
    void hideRetry();

    /**
     * Show an Error Message
     */
    void showError(String message);

    /**
     * Get a {@link android.content.Context}
     */
    Context context();

}
