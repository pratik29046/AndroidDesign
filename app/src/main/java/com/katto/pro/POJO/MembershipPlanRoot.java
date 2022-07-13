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
public class MembershipPlanRoot {

    @SerializedName("membership_plans")
    @Expose
    public List<MembershipPlan> membership_plans;
}
