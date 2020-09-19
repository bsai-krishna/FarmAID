package com.example.sih;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class APIObject {

    public APIObject(int year, int district_encode) {
        Year = year;
        District_encode = district_encode;
    }

    @SerializedName("Year")
    @Expose
    private int Year;

    @SerializedName("District_encode")
    @Expose
    private int District_encode;

    public int getDistrict_encode() {
        return District_encode;
    }

    public void setDistrict_encode(int district_encode) {
        District_encode = district_encode;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }
}
