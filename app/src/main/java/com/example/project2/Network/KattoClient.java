package com.example.project2.Network;

import com.example.project2.POJO.HomePOJO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface KattoClient {
    @GET("/content/home")
    Call<List<HomePOJO>> getHomePage(@Header("Authorization") String authoriztion, @Header("User-Agent") String useragent,@Header("Accept") String accept);
}
