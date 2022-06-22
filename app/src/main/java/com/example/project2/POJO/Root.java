package com.example.project2.POJO;
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
public class Root{
    @SerializedName("banners")
    @Expose
    public List<Banner> banners;

    @SerializedName("categories")
    @Expose
    public List<Category> categories;
}
