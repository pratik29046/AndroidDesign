package com.taak.pro.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Upcoming {

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("description")
    @Expose
    public String description;

    @SerializedName("directors")
    @Expose
    public String directors;

    @SerializedName("starring")
    @Expose
    public String starring;

    @SerializedName("poster")
    @Expose
    public String poster;

    @SerializedName("v_poster")
    @Expose
    public String v_poster;

    @SerializedName("language")
    @Expose
    public String language;

    @SerializedName("content_type")
    @Expose
    public String content_type;

    @SerializedName("age_rating")
    @Expose
    public String age_rating;

    @SerializedName("genres")
    @Expose
    public String genres;

    @SerializedName("trailer_link")
    @Expose
    public String trailer_link;

    @SerializedName("release_date")
    @Expose
    public String release_date;
}
