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
public class RootSeries{
    @SerializedName("num_pages")
    @Expose
    public int num_pages;

    @SerializedName("count")
    @Expose
    public int count;

    @SerializedName("series")
    @Expose
    public List<Series> series;
}
