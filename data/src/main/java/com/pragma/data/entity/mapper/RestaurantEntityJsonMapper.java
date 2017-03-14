package com.pragma.data.entity.mapper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pragma.data.entity.RestaurantEntity;
import com.pragma.data.entity.Result;

import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

/*
 * Class used to transform Strings representing Json to Valid Objects
 */

public class RestaurantEntityJsonMapper {

    private final Gson gson;

    @Inject
    public RestaurantEntityJsonMapper() {
        this.gson = new Gson();
    }

    /**
     * Transform from valid json string to List of {@link com.pragma.data.entity.RestaurantEntity}
     * @param restaurantListJsonResponse a Json representing a collection of Restaurants
     * @return list of {@link com.pragma.data.entity.RestaurantEntity}.
     * @throws com.google.gson.JsonSyntaxException if the json string
     * is not a valid json structure
     */
    public List<Result> transformRestaurantEntityCollection(String restaurantListJsonResponse)
            throws JSONException {
        final Type listofRestaurantEntityType = new TypeToken<List<RestaurantEntity>>() { }.getType();
        return this.gson.fromJson(restaurantListJsonResponse, listofRestaurantEntityType);
    }


}
