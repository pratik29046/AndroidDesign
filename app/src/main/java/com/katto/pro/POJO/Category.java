package com.katto.pro.POJO;
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
public class Category{
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("vertical")
    @Expose
    public boolean vertical;
    //see_more_url

    @SerializedName("see_more_url")
    @Expose
    public String seeMoreUrl;

    @SerializedName("content")
    @Expose
    public List<Content> content;
}
