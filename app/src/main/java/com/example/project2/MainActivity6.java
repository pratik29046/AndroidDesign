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
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.project2.Network.WebService;
import com.example.project2.POJO.Content;
import com.example.project2.POJO.Episode;
import com.example.project2.POJO.EpisodeRoot;
import com.example.project2.POJO.RootSeries;
import com.example.project2.POJO.Series;
import com.example.project2.POJO.SeriesRootnames;
import com.example.project2.POJO.TokenID;
import com.example.project2.POJO.Watchnextmovies;
import com.example.project2.POJO.Watchnextseries;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity6 extends AppCompatActivity {
    RecyclerView recyclerView,recyclerView1;
    LinearLayoutManager layoutManager;
    AdapterEpisodes adapter;
    AdapterWachnextSeries adapterWachnextSeries;
    ImageView imageView;
    TextView textView,text15,text17;
    TextView Title, Dec, Date, Age, Season,language,desc,genres,starring,directors;
    ImageView img1, img2;
    TabLayout tab;
    TabItem t1,t2;
    private String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        recyclerView = findViewById(R.id.seasons);
        recyclerView1=findViewById(R.id.seriesnextss);
        imageView = findViewById(R.id.img1);
        textView = findViewById(R.id.text14);
        img2 = findViewById(R.id.img5);
        img1 = findViewById(R.id.img3);
        Title = findViewById(R.id.text2);
        Age = findViewById(R.id.text5);
        language=findViewById(R.id.text20);
        genres=findViewById(R.id.text22);
        Dec=findViewById(R.id.text9);
        starring=findViewById(R.id.text11);
        directors=findViewById(R.id.text13);
//        text15=findViewById(R.id.text15);
//        text17=findViewById(R.id.text17);
        t1=findViewById(R.id.b1);
        t2=findViewById(R.id.b2);
        tab=findViewById(R.id.text17);


//        text15.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                text15.setTextColor(Color.parseColor("#FF0000"));
//                text17.setTextColor(Color.parseColor("#FFFFFF"));
//                recyclerView.setVisibility(View.VISIBLE);
//                recyclerView1.setVisibility(View.GONE);
//            }
//        });
//        text17.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                text15.setTextColor(Color.parseColor("#FFFFFF"));
//                text17.setTextColor(Color.parseColor("#FF0000"));
//                recyclerView.setVisibility(View.GONE);
//                recyclerView1.setVisibility(View.VISIBLE);
//            }
//        });


        tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==0){
                    recyclerView.setVisibility(View.VISIBLE);
                    recyclerView1.setVisibility(View.GONE);
                }else if(tab.getPosition()==1){
                    recyclerView.setVisibility(View.GONE);
                    recyclerView1.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });








        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start = new Intent(MainActivity6.this, MainActivity7.class);
                startActivity(start);
            }
        });

      /*  img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });*/


        Intent intent = getIntent();
//        int img=intent.getIntExtra("img",0);
//        String title=intent.getStringExtra("title");
//        String date=intent.getStringExtra("date");
        Content content = new Gson().fromJson(intent.getStringExtra("content"), Content.class);
//        Series series = new Gson().fromJson(intent.getStringExtra("series"), Series.class);
//        img1.setImageResource(content.poster);

        SeriesRootnames seriesRootnames = new Gson().fromJson(intent.getStringExtra("names"), SeriesRootnames.class);
//        data1();\

        /*img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                show();
                if(seriesRootnames.membership.equals("true") ){
                    show();
                }else{
                    Toast.makeText(getApplicationContext(),"hello false",Toast.LENGTH_SHORT).show();
                }
            }
        });*/


//        Log.d("TAG", "onCreate: "+content.name.toLowerCase(Locale.ROOT).replace(" ","-"));
        WebService.getClient().getSeriesPage(content.name.toLowerCase(Locale.ROOT).replace(" ","-")).enqueue(new Callback<SeriesRootnames>() {
            @Override
            public void onResponse(Call<SeriesRootnames> call, Response<SeriesRootnames> response) {
                Log.d("TAG", "onResponse: "+response.body());
                SeriesRootnames data=response.body();
                Picasso.get().load("https://katto.in"+data.poster).into(img1);
                Title.setText(data.name);
                Age.setText(data.age_rating);
                language.setText(data.language);
                genres.setText(data.genres);
                Dec.setText(data.description);
                starring.setText(data.starring);
                directors.setText(data.directors);
                adp1(data.watch_next);
                adp(data.episodes);
//                Log.d("TAG", "sirsss: "+data.episodes);


                img2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                show();
                        if(data.membership=="false"){
                            show();
                        }else{
                            shows();
                        }
                    }
                });

            }
            @Override
            public void onFailure(Call<SeriesRootnames> call, Throwable t) {
                Log.d("TAG", "onFailure: "+t.getLocalizedMessage());
            }
        });



}

    public void adp(List<Episode> episodes){
        recyclerView=findViewById(R.id.seasons);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity6.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView
        recyclerView.setItemAnimator(new DefaultItemAnimator() );
        adapter=new AdapterEpisodes(episodes,this);
        recyclerView.setAdapter(adapter);
    }


    public void adp1(List<Watchnextseries> watchnextseries){
        recyclerView1=findViewById(R.id.seriesnextss);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity6.this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView1.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView
        recyclerView1.setItemAnimator(new DefaultItemAnimator() );
        adapterWachnextSeries=new AdapterWachnextSeries(this,watchnextseries);
        recyclerView1.setAdapter(adapterWachnextSeries);
    }

    // back to last activity
    public void back(){
        super.onBackPressed();
    }

    public void show(){
        final AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity6.this);
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
                Intent start=new Intent(MainActivity6.this,MainPlayerActivity.class);
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
    }

    public void shows(){
        final AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity6.this);
        View mView = getLayoutInflater().inflate(R.layout.popups_subscribe,null);
        TextView btn_cancel = (TextView) mView.findViewById(R.id.b1);
        TextView btn_okay = (TextView)mView.findViewById(R.id.b2);

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
                Intent start=new Intent(MainActivity6.this,MainActivity4.class);
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
    }
}