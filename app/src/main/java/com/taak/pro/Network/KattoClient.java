package com.taak.pro.Network;

import com.taak.pro.POJO.EpisodeRoot;
import com.taak.pro.POJO.HomePOJO;
import com.taak.pro.POJO.MoviesRoot;
import com.taak.pro.POJO.SeriesRootnames;
import com.taak.pro.POJO.UpcomingRoot;
import com.taak.pro.POJO.WelcomeRoot;
import com.taak.pro.POJO.MembershipPlanRoot;
import com.taak.pro.POJO.RootSeries;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface KattoClient {
    @GET("api/content/home")
    Call<List<HomePOJO>> getHomePage(@Header("Authorization") String authoriztion, @Header("User-Agent") String useragent, @Header("Accept") String accept);

    @GET("api/content/series")
    Call<List<RootSeries>> getSeriesPage(@Header("Authorization") String authoriztion, @Header("User-Agent") String useragent, @Header("Accept") String accept);

    @GET("api/content/episode")
    Call<List<EpisodeRoot>> getEpisodePage(@Header("Authorization") String authoriztion, @Header("User-Agent") String useragent, @Header("Accept") String accept);

    @GET("api/content/movies/{moviename}")
    Call<List<MoviesRoot>> getMoviePage(@Path("moviename") String moviename, @Header("Authorization") String authoriztion, @Header("User-Agent") String useragent, @Header("Accept") String accept);

    @GET("api/content/series/{seriesname}")
    Call<List<SeriesRootnames>> getSeriesPage(@Path("seriesname") String seriesname, @Header("Authorization") String authoriztion, @Header("User-Agent") String useragent, @Header("Accept") String accept);

    @GET("api/content/Upcoming")
    Call<List<UpcomingRoot>> getUpcomingPage(@Header("Authorization") String authoriztion, @Header("User-Agent") String useragent, @Header("Accept") String accept);

    @GET("api/content/welcome")
    Call<List<WelcomeRoot>> getwelcomePage(@Header("Authorization") String authoriztion, @Header("User-Agent") String useragent, @Header("Accept") String accept);

    @GET("api/memberships/plans")
    Call<List<MembershipPlanRoot>> getplanPage(@Header("Authorization") String authoriztion, @Header("User-Agent") String useragent, @Header("Accept") String accept);



}
