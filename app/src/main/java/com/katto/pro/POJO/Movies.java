package com.katto.pro.POJO;

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
public class Movies {
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("poster")
    @Expose
    public String poster;
    @SerializedName("v_poster")
    @Expose
    public String v_poster;
    @SerializedName("url")
    @Expose
    public String url;
}

