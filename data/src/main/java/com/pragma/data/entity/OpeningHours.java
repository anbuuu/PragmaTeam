package com.pragma.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class OpeningHours {

    @SerializedName("exceptional_date")
    @Expose
    private List<Object> exceptionalDate = null;
    @SerializedName("open_now")
    @Expose
    private Boolean openNow;
    @SerializedName("weekday_text")
    @Expose
    private List<Object> weekdayText = null;

    /**
     * No args constructor for use in serialization
     */
    public OpeningHours() {
    }

    /**
     * @param weekdayText
     * @param exceptionalDate
     * @param openNow
     */
    public OpeningHours(List<Object> exceptionalDate, Boolean openNow, List<Object> weekdayText) {
        super();
        this.exceptionalDate = exceptionalDate;
        this.openNow = openNow;
        this.weekdayText = weekdayText;
    }

    public List<Object> getExceptionalDate() {
        return exceptionalDate;
    }

    public void setExceptionalDate(List<Object> exceptionalDate) {
        this.exceptionalDate = exceptionalDate;
    }

    public Boolean getOpenNow() {
        return openNow;
    }

    public void setOpenNow(Boolean openNow) {
        this.openNow = openNow;
    }

    public List<Object> getWeekdayText() {
        return weekdayText;
    }

    public void setWeekdayText(List<Object> weekdayText) {
        this.weekdayText = weekdayText;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
