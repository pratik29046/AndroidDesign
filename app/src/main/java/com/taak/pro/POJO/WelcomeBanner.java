package com.taak.pro.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WelcomeBanner {

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("poster")
    @Expose
    public String poster;

    @SerializedName("description")
    @Expose
    public String description;

    @SerializedName("created_at")
    @Expose
    public Date created_at;

}
