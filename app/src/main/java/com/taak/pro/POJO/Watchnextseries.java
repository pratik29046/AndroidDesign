package com.taak.pro.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Watchnextseries {
    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("url")
    @Expose
    public String url;

    @SerializedName("poster")
    @Expose
    public String poster;

    @SerializedName("v_poster")
    @Expose
    public String v_poster;

    @SerializedName("age_rating")
    @Expose
    public String age_rating;
}
