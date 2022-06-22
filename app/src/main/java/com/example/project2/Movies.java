package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project2.Network.WebService;
import com.example.project2.POJO.Content;
import com.example.project2.POJO.MoviesRoot;
import com.google.gson.Gson;

import java.util.List;

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
    TextView Title,Dec,Date,Age,Season;
    ImageView img1,img2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movies);

        img1=findViewById(R.id.img3);
        Title=findViewById(R.id.text2);
        Date=findViewById(R.id.text4);
        Age=findViewById(R.id.text5);
        Season=findViewById(R.id.text6);
        Dec=findViewById(R.id.text11);

        WebService.getClient().get_MovieData().enqueue(new Callback<MoviesRoot>() {
            @Override
            public void onResponse(Call<MoviesRoot> call, Response<MoviesRoot> response) {
                Title.setText(response.body().num_pages);


            }

            @Override
            public void onFailure(Call<MoviesRoot> call, Throwable t) {

            }
        });




//        Intent intent=getIntent();
//        Content content=new Gson().fromJson(intent.getStringExtra("content"), Content.class);
//        Title.setText(content.name);
//        Age.setText(content.age_rating);



    }
}