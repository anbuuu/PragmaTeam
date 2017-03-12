package com.pragma.domain;

/**
 * Created by anbu.ezhilan on 12/03/2017.
 */

public class RestaurantResults {

    private final String id;

    public RestaurantResults(String id) {
        this.id = id;
    }

    // restaurant Name, Distance, Rating, Opening hours, image URL, Link

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    private String openingHours;
    private int rating;


}
