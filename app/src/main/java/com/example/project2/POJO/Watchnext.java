package com.example.project2.POJO;

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
public class Watchnext {
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
