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
public class WelcomeRoot {

    @SerializedName("welcome_banners")
    @Expose
    public List<WelcomeBanner> welcome_banners;
}
