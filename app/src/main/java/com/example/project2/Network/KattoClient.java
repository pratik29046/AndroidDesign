package com.example.project2.Network;

import com.example.project2.POJO.EpisodeRoot;
import com.example.project2.POJO.HomePOJO;
import com.example.project2.POJO.MembershipPlanRoot;
import com.example.project2.POJO.MoviesRoot;
import com.example.project2.POJO.RootSeries;
import com.example.project2.POJO.SeriesRootnames;
import com.example.project2.POJO.Upcoming;
import com.example.project2.POJO.UpcomingRoot;
import com.example.project2.POJO.WelcomeRoot;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface KattoClient {
    @GET("/content/home")
    Call<List<HomePOJO>> getHomePage(@Header("Authorization") String authoriztion, @Header("User-Agent") String useragent, @Header("Accept") String accept);

    @GET("/content/series")
    Call<List<RootSeries>> getSeriesPage(@Header("Authorization") String authoriztion, @Header("User-Agent") String useragent, @Header("Accept") String accept);

    @GET("/content/episode")
    Call<List<EpisodeRoot>> getEpisodePage(@Header("Authorization") String authoriztion, @Header("User-Agent") String useragent, @Header("Accept") String accept);

    @GET("/content/movies/{moviename}")
    Call<List<MoviesRoot>> getMoviePage(@Path("moviename") String moviename, @Header("Authorization") String authoriztion, @Header("User-Agent") String useragent, @Header("Accept") String accept);

    @GET("/content/series/{seriesname}")
    Call<List<SeriesRootnames>> getSeriesPage(@Path("seriesname") String seriesname, @Header("Authorization") String authoriztion, @Header("User-Agent") String useragent, @Header("Accept") String accept);

    @GET("/content/Upcoming")
    Call<List<UpcomingRoot>> getUpcomingPage(@Header("Authorization") String authoriztion, @Header("User-Agent") String useragent, @Header("Accept") String accept);

    @GET("/content/welcome")
    Call<List<WelcomeRoot>> getwelcomePage(@Header("Authorization") String authoriztion, @Header("User-Agent") String useragent, @Header("Accept") String accept);

    @GET("memberships/plans")
    Call<List<MembershipPlanRoot>> getplanPage(@Header("Authorization") String authoriztion, @Header("User-Agent") String useragent, @Header("Accept") String accept);

}
