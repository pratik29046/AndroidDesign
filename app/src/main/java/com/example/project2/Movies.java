package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project2.Network.WebService;
import com.example.project2.POJO.Content;
import com.example.project2.POJO.MovieRootnames;
import com.example.project2.POJO.MovieRootnames;
import com.example.project2.POJO.MoviesRoot;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Movies extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<ModelClass2> userList2;
    Adapters adapter;
    ImageView imageView;
    TextView textView;
    TextView Title,Dec,Date,Age,Season,Duration,language,starring,genres,directors;
    ImageView img1,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movies);

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
        WebService.getClient().getMoviePage(content.name.toLowerCase(Locale.ROOT)).enqueue(new Callback<MovieRootnames>() {
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

            }
            @Override
            public void onFailure(Call<MovieRootnames> call, Throwable t) {
//                Log.d("TAG", "onFailure: "+t.getLocalizedMessage());
            }
        });


    }

    public void back(){
        super.onBackPressed();
    }

}