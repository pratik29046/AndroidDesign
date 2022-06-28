package com.example.project2.Network;

import com.example.project2.POJO.Episode;
import com.example.project2.POJO.EpisodeRoot;
import com.example.project2.POJO.HomePOJO;
import com.example.project2.POJO.MembershipPlanRoot;
import com.example.project2.POJO.MovieRootnames;
import com.example.project2.POJO.MovieRootnames;
import com.example.project2.POJO.MoviesRoot;
import com.example.project2.POJO.Root;
import com.example.project2.POJO.RootSeries;
import com.example.project2.POJO.SeriesRootnames;
import com.example.project2.POJO.Upcoming;
import com.example.project2.POJO.UpcomingRoot;
import com.example.project2.POJO.WelcomeRoot;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;

import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Url;

public class WebService {
    private static WebServiceInterface webApiInterface;

    public static WebServiceInterface getClient()
    {
        if (webApiInterface == null) {

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient okclient = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .connectTimeout(10, TimeUnit.MINUTES)
                    .readTimeout(10, TimeUnit.MINUTES)
                    .writeTimeout(10, TimeUnit.MINUTES)
                    .build();

            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .setLenient()
                    .create();

            // Post post = gson.fromJson(reader, Post.class);

            Retrofit client = new Retrofit.Builder()
                    .baseUrl("https://katto.in/api/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okclient)
                    .build();

            webApiInterface = client.create(WebServiceInterface.class);
        }
        return webApiInterface;
    }


    private static Retrofit retrofit = null;
    static Retrofit getetrofitInistance() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        retrofit = new Retrofit.Builder()
                .baseUrl("https://katto.in/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }

    public interface WebServiceInterface {

        @GET("content/home")
        @Headers({
                "Accept: application/json; version=1",
                "User-Agent: Thunder Client (https://www.thunderclient.com)",
                "Authorization: Token 7708605ec0bdd64e1cc1fcfcc84179595b874700 "
        })
        Call<Root> get_HomeData();

        @GET("content/series")
        @Headers({
                "Accept: application/json; version=1",
                "User-Agent: Thunder Client (https://www.thunderclient.com)",
                "Authorization: Token 7708605ec0bdd64e1cc1fcfcc84179595b874700 "
        })
        Call<RootSeries> get_SerisData();


        @GET("content/episode")
        @Headers({
                "Accept: application/json; version=1",
                "User-Agent: Thunder Client (https://www.thunderclient.com)",
                "Authorization: Token 7708605ec0bdd64e1cc1fcfcc84179595b874700 "
        })
        Call<Episode> get_EpisodeData();

        @GET("content/movie")
        @Headers({
                "Accept: application/json; version=1",
                "User-Agent: Thunder Client (https://www.thunderclient.com)",
                "Authorization: Token 7708605ec0bdd64e1cc1fcfcc84179595b874700 "
        })
        Call<MoviesRoot> get_MovieData();


        @GET("content/movies/{moviename}")
        @Headers({
                "Accept: application/json; version=1",
                "User-Agent: Thunder Client (https://www.thunderclient.com)",
                "Authorization: Token 7708605ec0bdd64e1cc1fcfcc84179595b874700"
        })
        Call<MovieRootnames>getMoviePage(@Path("moviename") String moviename);


        @GET("content/series/{seriesname}")
        @Headers({
                "Accept: application/json; version=1",
                "User-Agent: Thunder Client (https://www.thunderclient.com)",
                "Authorization: Token 7708605ec0bdd64e1cc1fcfcc84179595b874700"
        })
        Call<SeriesRootnames>getSeriesPage(@Path("seriesname") String seriesname);




        @GET("content/movie/moiveroot")
        @Headers({
                "Accept: application/json; version=1",
                "User-Agent: Thunder Client (https://www.thunderclient.com)",
                "Authorization: Token 7708605ec0bdd64e1cc1fcfcc84179595b874700"
        })
        Call<MoviesRoot> get_MovierootData();


        @GET("content/upcoming")
        @Headers({
                "Accept: application/json; version=1",
                "User-Agent: Thunder Client (https://www.thunderclient.com)",
                "Authorization: Token 7708605ec0bdd64e1cc1fcfcc84179595b874700"
        })
        Call<UpcomingRoot> get_upcomingData();

        @GET("content/welcome")
        @Headers({
                "Accept: application/json; version=1",
                "User-Agent: Thunder Client (https://www.thunderclient.com)",
                "Authorization: Token 7708605ec0bdd64e1cc1fcfcc84179595b874700"
        })
        Call<WelcomeRoot> get_welcomeData();

        @GET("memberships/plans")
        @Headers({
                "Accept: application/json; version=1",
                "User-Agent: Thunder Client (https://www.thunderclient.com)",
                "Authorization: Token 7708605ec0bdd64e1cc1fcfcc84179595b874700"
        })
        Call<MembershipPlanRoot> get_planData();




      /*  @POST("insertData.php")
        @FormUrlEncoded
        Call<RagisterPOJO> Ragister(
                @Field("contact") String contact,
                @Field("name") String name,
                @Field("shop") String shop,
                @Field("area") String area,
                @Field("city") String city,
                @Field("village") String village,
                @Field("status") String status,
                @Field("user_status") String user_status,
                @Field("u_id") String token

        );*/

    }
}