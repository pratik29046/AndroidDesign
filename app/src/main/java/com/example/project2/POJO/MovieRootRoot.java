package com.example.project2.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class MovieRootRoot {

    @SerializedName("name")
    @Expose
        public String name;
    @SerializedName("description")
    @Expose
        public String description;
    @SerializedName("content_rating")
    @Expose
        public int content_rating;
    @SerializedName("age_rating")
    @Expose
        public String age_rating;
    @SerializedName("language")
    @Expose
        public String language;
    @SerializedName("directors")
    @Expose
        public String directors;
    @SerializedName("name")
    @Expose
        public String starring;
    @SerializedName("genres")
    @Expose
        public String genres;
    @SerializedName("poster")
    @Expose
        public int poster;
    @SerializedName("v_poster")
    @Expose
        public String v_poster;
    @SerializedName("duration")
    @Expose
        public String duration;
    @SerializedName("free")
    @Expose
        public boolean free;
    @SerializedName("created_at")
    @Expose
        public Date created_at;
    @SerializedName("trailer_link")
    @Expose
        public String trailer_link;
    @SerializedName("content_link")
    @Expose
        public String content_link;
    @SerializedName("membership")
    @Expose
        public boolean membership;
    @SerializedName("watch_next")
    @Expose
        public List<Watchnext> watch_next;

}
