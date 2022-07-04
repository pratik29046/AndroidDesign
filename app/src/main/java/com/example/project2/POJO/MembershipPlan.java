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
public class MembershipPlan {

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("slug")
    @Expose
    public String slug;

    @SerializedName("price")
    @Expose
    public float price;

    @SerializedName("validity")
    @Expose
    public int validity;

    @SerializedName("purchase_url")
    @Expose
    public String purchase_url;
}
