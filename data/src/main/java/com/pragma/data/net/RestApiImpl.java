package com.pragma.data.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.pragma.data.entity.RestaurantEntity;
import com.pragma.data.entity.mapper.RestaurantEntityJsonMapper;
import com.pragma.data.exception.ConnectionException;

import java.net.MalformedURLException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by anbu.ezhilan on 12/03/2017.
 */

public class RestApiImpl implements RestApi {

    private final Context context;
    private final RestaurantEntityJsonMapper restaurantEntityJsonMapper;

    /**
     * Constructor of the class
     *
     * @param context {@link android.content.Context}.
     * @param restaurantEntityJsonMapper {@link RestaurantEntityJsonMapper}.
     */
    public RestApiImpl(Context context, RestaurantEntityJsonMapper restaurantEntityJsonMapper) {
        if (context == null || restaurantEntityJsonMapper == null) {
            throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
        }
        this.context = context.getApplicationContext();
        this.restaurantEntityJsonMapper = restaurantEntityJsonMapper;
    }

    /**
     * Checks if the device has any active internet connection.
     *
     * @return true device with internet connection, otherwise false.
     */
    private boolean isThereInternetConnection() {
        boolean isConnected;

        ConnectivityManager connectivityManager =
                (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;
    }

    @Override
    public Observable<List<RestaurantEntity>> restaurantEntityList() {

        return Observable.create(new ObservableOnSubscribe<List<RestaurantEntity>>() {
            @Override
            public void subscribe(ObservableEmitter<List<RestaurantEntity>> emitter) throws Exception {
                if ( RestApiImpl.this.isThereInternetConnection()) {
                    try {
                        String responseRestaurantEntities = RestApiImpl.this.getRestaurantEntitiesFromApi();
                        if ( responseRestaurantEntities != null) {
                            emitter.onNext(restaurantEntityJsonMapper.transformRestaurantEntityCollection(
                                    responseRestaurantEntities));
                            emitter.onComplete();
                        }
                    } catch (Exception ex) {
                        emitter.onError(new ConnectionException(ex.getCause()));
                    }
                } else {
                    emitter.onError(new ConnectionException());
                }
             }
           });
        }

    private String getRestaurantEntitiesFromApi() throws MalformedURLException {
        return ApiConnection.createGET(API_GET_RESTAURANTS_LIST_URL).requestSyncCall();
    }


}
