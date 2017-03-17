package com.pragma.presentation.presenter;

/**
 * Interface representing presenter in a MVP Pattern
 */

public interface Presenter {

    /**
     * Method that control of the lifecycle of the view, should be called in
     * the Actiivty/Fragment's onResume() method
     */
    void resume();

    /**
     * Method that control the lifecycle of the view, should be called in the Activity
     * /Fragment's onPause Method
     */
    void pause();

    /**
     * Method that control the lifecycle of the view, should be called in the Activity
     * /Fragment's onDestroy Method
     */
    void destroy();

}
