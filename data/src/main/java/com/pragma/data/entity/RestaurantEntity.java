package com.pragma.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class RestaurantEntity {

    @SerializedName("html_attributions")
    @Expose
    private List<Object> htmlAttributions = null;
    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    @SerializedName("status")
    @Expose
    private String status;

    /**
     * No args constructor for use in serialization
     */
    public RestaurantEntity() {
    }

    /**
     * @param results
     * @param status
     * @param htmlAttributions
     */
    public RestaurantEntity(List<Object> htmlAttributions, List<Result> results, String status) {
        super();
        this.htmlAttributions = htmlAttributions;
        this.results = results;
        this.status = status;
    }

    public List<Object> getHtmlAttributions() {
        return htmlAttributions;
    }

    public void setHtmlAttributions(List<Object> htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }


}
