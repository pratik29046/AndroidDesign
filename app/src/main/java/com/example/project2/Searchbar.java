package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.project2.Network.WebService;
import com.example.project2.POJO.Category;
import com.example.project2.POJO.Content;
import com.example.project2.POJO.Root;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Searchbar extends AppCompatActivity {
    RecyclerView recyclerView;
    AdaptersSearchNames adaptersSearchNames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchbar);
        recyclerView=findViewById(R.id.recycler_search);

        WebService.getClient().get_HomeData().enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {

                for (int i = 0 ; i < response.body().categories.size() ; i ++){
                    adp(response.body().categories.get(i).content);
                }

            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {

            }
        });


    }

    public void adp(List<Content> contents){
        recyclerView=findViewById(R.id.recycler_search);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Searchbar.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView
        recyclerView.setItemAnimator(new DefaultItemAnimator() );
        adaptersSearchNames=new AdaptersSearchNames(contents,this);
        recyclerView.setAdapter(adaptersSearchNames);
    }

}