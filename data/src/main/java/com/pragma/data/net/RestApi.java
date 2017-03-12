package com.pragma.data.net;

import com.pragma.data.entity.RestaurantEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * Rest API for Retrieving Data from Network
 */

public interface RestApi {

    String GOOGLE_API_TOKEN = "AIzaSyA9WH79INIgCahuse5rrt0xIwI2o_7lRpk";
    String API_GET_RESTAURANTS_LIST_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.890370,151.199404&radius=1000&type=restaurant&keyword=cruise&key=" + GOOGLE_API_TOKEN;

    /**
     * Retrieves an {@link Observable} which will emit a List of {@link RestaurantEntity}.
     */
    Observable<List<RestaurantEntity>> restaurantEntityList();


}
