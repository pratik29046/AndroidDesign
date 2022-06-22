package com.example.project2.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Episode {
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("number")
    @Expose
    public String number;  // int to string
    @SerializedName("content_link")
    @Expose
    public String content_link;
    @SerializedName("poster")
    @Expose
    public String poster;
    @SerializedName("duration")
    @Expose
    public String duration;
    @SerializedName("free")
    @Expose
    public boolean free;

}
