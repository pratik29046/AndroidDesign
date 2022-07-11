package com.example.project2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.project2.Network.WebService;
import com.example.project2.POJO.Category;
import com.example.project2.POJO.Content;
import com.example.project2.POJO.MovieRootnames;
import com.example.project2.POJO.MovieRootnames;
import com.example.project2.POJO.MoviesRoot;
import com.example.project2.POJO.Watchnextmovies;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Movies extends AppCompatActivity {

    RelativeLayout relativeLayout1;
    RecyclerView recyclerView;
    AdapterWatchnext adapter;
    TextView Title,Dec,Date,Age,Season,Duration,language,starring,genres,directors,trailerNames;
    ImageView img1,back,play,trailer,moretrailers,trailersPlay;
    TabLayout tab;
    TabItem t1,t2;
    ImageSlider imageSlider;
    private String url="https://katto.in";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movies);

        relativeLayout1=findViewById(R.id.tra1);
        trailer=findViewById(R.id.img6);
        play=findViewById(R.id.img5);
        back=findViewById(R.id.img1);
        img1=findViewById(R.id.img3);
        Title=findViewById(R.id.text2);
        Date=findViewById(R.id.text4);
        Age=findViewById(R.id.text5);
        Season=findViewById(R.id.text6);
        Dec=findViewById(R.id.text9);
        Duration=findViewById(R.id.text24);
        language=findViewById(R.id.text20);
        starring=findViewById(R.id.text11);
        genres=findViewById(R.id.text22);
        directors=findViewById(R.id.text13);
//        imageSlider=findViewById(R.id.post);
        moretrailers=findViewById(R.id.moretrailers);
        recyclerView=findViewById(R.id.watchnext);
        t1=findViewById(R.id.b1);
        t2=findViewById(R.id.b2);
        tab=findViewById(R.id.text17);
        trailerNames=findViewById(R.id.textTr);
        trailersPlay=findViewById(R.id.trailersPlay);

        tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==0){
                    recyclerView.setVisibility(View.GONE);
                    relativeLayout1.setVisibility(View.VISIBLE);
                }else if(tab.getPosition()==1){
                    relativeLayout1.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });

        Intent intent=getIntent();
        Content content=new Gson().fromJson(intent.getStringExtra("content"), Content.class);
//        Title.setText(content.name);
//        Age.setText(content.age_rating);
//        Log.d("TAG", "onCreate: "+content.name.toLowerCase(Locale.ROOT));

        WebService.getClient().getMoviePage(content.name.toLowerCase(Locale.ROOT).replace(" ","-")).enqueue(new Callback<MovieRootnames>() {
            @Override
            public void onResponse(Call<MovieRootnames> call, Response<MovieRootnames> response) {
//                Log.d("TAG", "onResponse: "+response.body());
                MovieRootnames data=response.body();
                Picasso.get().load("https://katto.in"+data.poster).into(img1);
                Title.setText(data.name);
                Age.setText(data.age_rating);
                Dec.setText(data.description);
                Duration.setText(data.duration);
                language.setText(data.language);
                starring.setText(data.starring);
                genres.setText(data.genres);
                directors.setText(data.directors);
                trailerNames.setText(data.name);
                Picasso.get().load("https://katto.in"+data.poster).into(moretrailers);

                adp(data.watch_next);

                play.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(data.membership=="false"){
                            try {
                                final AlertDialog.Builder alert = new AlertDialog.Builder(Movies.this);
                                View mView = getLayoutInflater().inflate(R.layout.popups, null);
                                TextView btn_cancel = (TextView) mView.findViewById(R.id.b1);
                                TextView btn_okay = (TextView) mView.findViewById(R.id.b2);

                                alert.setView(mView);
                                final AlertDialog alertDialog = alert.create();
                                alertDialog.setCanceledOnTouchOutside(false);
                                Window window = alertDialog.getWindow();
                                WindowManager.LayoutParams wlp = window.getAttributes();
                                wlp.gravity = Gravity.BOTTOM;
                                wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                                window.setAttributes(wlp);

                                btn_cancel.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent start = new Intent(Movies.this, MainPlayerActivity.class);
                                        start.putExtra("link", data.content_link);
                                        start.putExtra("name", data.name);
                                        startActivity(start);
                                        alertDialog.dismiss();
                                    }
                                });
                                btn_okay.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        alertDialog.dismiss();
                                    }

                                });
                                alertDialog.show();
                            }catch (Exception|Error e){
                                e.printStackTrace();
                            }

                        }else{
                            shows();
                        }
                    }
                });


                trailersPlay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            Intent intent1 = new Intent(getApplicationContext(), MainPlayerActivity.class);
                            intent1.putExtra("link", data.trailer_link);
                            intent1.putExtra("name",data.name);
                            startActivity(intent1);
                        }catch (Exception| Error e){
                            e.printStackTrace();
                        }
                    }
                });

            }
            @Override
            public void onFailure(Call<MovieRootnames> call, Throwable t) {
//                Log.d("TAG", "onFailure: "+t.getLocalizedMessage());
            }
        });


    }



    public void adp(List<Watchnextmovies> watchnextmovies){
        try {
            recyclerView = findViewById(R.id.watchnext);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Movies.this, LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            adapter = new AdapterWatchnext(this, watchnextmovies);
            recyclerView.setAdapter(adapter);
        }catch (Exception|Error e){
            e.printStackTrace();
        }
    }

    public void back(){
        try {
            super.onBackPressed();
        }catch (Exception|Error e){
            e.printStackTrace();
        }
    }

  /*  public void show(){
        final AlertDialog.Builder alert = new AlertDialog.Builder(Movies.this);
        View mView = getLayoutInflater().inflate(R.layout.popups,null);
        TextView btn_cancel = (TextView) mView.findViewById(R.id.b1);
        TextView btn_okay = (TextView)mView.findViewById(R.id.b2);

        alert.setView(mView);
        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);
        Window window = alertDialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent start=new Intent(Movies.this,MainPlayerActivity.class);
//                startActivity(start);
                alertDialog.dismiss();
            }
        });
        btn_okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }

        });
        alertDialog.show();
    }*/

    public void shows(){
        try {
            final AlertDialog.Builder alert = new AlertDialog.Builder(Movies.this);
            View mView = getLayoutInflater().inflate(R.layout.popups_subscribe, null);
            TextView btn_cancel = (TextView) mView.findViewById(R.id.b1);
            TextView btn_okay = (TextView) mView.findViewById(R.id.b2);

            alert.setView(mView);
            final AlertDialog alertDialog = alert.create();
            alertDialog.setCanceledOnTouchOutside(false);
            Window window = alertDialog.getWindow();
            WindowManager.LayoutParams wlp = window.getAttributes();
            wlp.gravity = Gravity.BOTTOM;
            wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
            window.setAttributes(wlp);
            btn_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent start = new Intent(Movies.this, MainActivity4.class);
                    startActivity(start);
                    alertDialog.dismiss();
                }
            });
            btn_okay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                }

            });
            alertDialog.show();
        }catch (Exception| Error e){
            e.printStackTrace();
        }
    }

}