package com.pragma.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;


public class Southwest {

    @SerializedName("lat")
    @Expose
    private Float lat;
    @SerializedName("lng")
    @Expose
    private Float lng;

    /**
     * No args constructor for use in serialization
     */
    public Southwest() {
    }

    /**
     * @param lng
     * @param lat
     */
    public Southwest(Float lat, Float lng) {
        super();
        this.lat = lat;
        this.lng = lng;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLng() {
        return lng;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
