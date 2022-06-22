package com.example.project2.Network;

import com.example.project2.POJO.Episode;
import com.example.project2.POJO.EpisodeRoot;
import com.example.project2.POJO.HomePOJO;
import com.example.project2.POJO.MoviesRoot;
import com.example.project2.POJO.Root;
import com.example.project2.POJO.RootSeries;
import com.example.project2.POJO.Watchnext;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface KattoClient {
    @GET("/content/home")
    Call<List<HomePOJO>> getHomePage(@Header("Authorization") String authoriztion, @Header("User-Agent") String useragent, @Header("Accept") String accept);

    @GET("/content/series")
    Call<List<RootSeries>> getSeriesPage(@Header("Authorization") String authoriztion, @Header("User-Agent") String useragent, @Header("Accept") String accept);

    @GET("/content/episode")
    Call<List<EpisodeRoot>> getEpisodePage(@Header("Authorization") String authoriztion, @Header("User-Agent") String useragent, @Header("Accept") String accept);

    @GET("/content/movie")
    Call<List<MoviesRoot>> getMoviePage(@Header("Authorization") String authoriztion, @Header("User-Agent") String useragent, @Header("Accept") String accept);

    @GET("/content/movie/moiveroot")
    Call<List<Watchnext>> getMoiverootPage(@Header("Authorization") String authoriztion, @Header("User-Agent") String useragent, @Header("Accept") String accept);
}
