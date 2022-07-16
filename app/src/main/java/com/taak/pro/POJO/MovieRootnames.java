package com.taak.pro.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieRootnames{
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("content_rating")
    @Expose
    public String content_rating;
    @SerializedName("age_rating")
    @Expose
    public String age_rating;
    @SerializedName("language")
    @Expose
    public String language;
    @SerializedName("directors")
    @Expose
    public String directors;
    @SerializedName("starring")
    @Expose
    public String starring;
    @SerializedName("genres")
    @Expose
    public String genres;
    @SerializedName("poster")
    @Expose
    public String poster;
    @SerializedName("v_poster")
    @Expose
    public String v_poster;
    @SerializedName("duration")
    @Expose
    public String duration;
    @SerializedName("free")
    @Expose
    public String free;
    @SerializedName("created_at")
    @Expose
    public String created_at;
    @SerializedName("trailer_link")
    @Expose
    public String trailer_link;
    @SerializedName("content_link")
    @Expose
    public String content_link;
    @SerializedName("membership")
    @Expose
    public String membership;
    @SerializedName("watch_next")
    @Expose
    public List<Watchnextmovies> watch_next;

}