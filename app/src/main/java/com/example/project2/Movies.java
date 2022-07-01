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
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Movies extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    AdapterWatchnext adapter;
    ImageView imageView;
    TextView textView,textView1,textView2;
    TextView Title,Dec,Date,Age,Season,Duration,language,starring,genres,directors;
    ImageView img1,back,play;

    List<SlideModel> slideModels=new ArrayList<SlideModel>();
    ImageSlider imageSlider;
    private String url="https://katto.in";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movies);

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
        imageSlider=findViewById(R.id.post);
        textView1=findViewById(R.id.text17);
        textView2=findViewById(R.id.text16);
        textView=findViewById(R.id.moretrailers);
        recyclerView=findViewById(R.id.watchnext);

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView1.setTextColor(Color.parseColor("#FF0000"));
                textView2.setTextColor(Color.parseColor("#FFFFFF"));
               textView.setVisibility(View.GONE);
               recyclerView.setVisibility(View.VISIBLE);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView1.setTextColor(Color.parseColor("#FFFFFF"));
                textView2.setTextColor(Color.parseColor("#FF0000"));
                recyclerView.setVisibility(View.GONE);
                textView.setVisibility(View.VISIBLE);
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
                Picasso.get().load("https://katto.in"+data.v_poster).into(img1);
                Title.setText(data.name);
                Age.setText(data.age_rating);
                Dec.setText(data.description);
                Duration.setText(data.duration);
                language.setText(data.language);
                starring.setText(data.starring);
                genres.setText(data.genres);
                directors.setText(data.directors);

//                for (int i = 0 ; i < data.watch_next.size() ; i ++) {
//                    slideModels.add(new SlideModel(url+data.watch_next.get(i).poster, ScaleTypes.FIT));
////                    response.body().getBanners().get(i).get();
//
//                }
//                imageSlider.setImageList(slideModels);

                adp(data.watch_next);
                Log.d("TAG", "sirsss: "+data.watch_next);

                play.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                show();
                        if(data.membership=="true"){
                            show();
                        }else{
                            shows();
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
        recyclerView=findViewById(R.id.watchnext);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Movies.this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView
        recyclerView.setItemAnimator(new DefaultItemAnimator() );
        adapter=new AdapterWatchnext(this,watchnextmovies);
        recyclerView.setAdapter(adapter);
    }

    public void back(){
        super.onBackPressed();
    }

    public void show(){
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
                Intent start=new Intent(Movies.this,MainActivity13.class);
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
        final AlertDialog.Builder alert = new AlertDialog.Builder(Movies.this);
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
                Intent start=new Intent(Movies.this,MainActivity4.class);
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