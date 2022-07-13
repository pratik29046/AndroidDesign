package com.katto.pro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;

import com.katto.pro.POJO.Content;
import com.katto.pro.POJO.Root;
import com.katto.pro.Network.WebService;
import com.katto.pro.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Searchbar extends AppCompatActivity {
    RecyclerView recyclerView;
    AdaptersSearchNames adaptersSearchNames;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchbar);
        recyclerView=findViewById(R.id.recycler_search);
        searchView=findViewById(R.id.search);
        searchView.clearFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                try {
                    adaptersSearchNames.getFilter().filter(newText);
                }catch (Exception|Error e){
                    e.printStackTrace();
                }
                return false;
            }
        });

        WebService.getClient().get_HomeData().enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {

                /*for (int i = 0 ; i < response.body().categories.size(); i ++){
                    adp(response.body().categories.get(i).content);

                }*/

                adp(response.body().categories.get(response.body().categories.size()-2).content);

            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {

            }
        });

    }


    public void adp(List<Content> contents){
        try {
            recyclerView = findViewById(R.id.recycler_search);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Searchbar.this, LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            adaptersSearchNames = new AdaptersSearchNames(contents, this);
            recyclerView.setAdapter(adaptersSearchNames);
        }catch (Exception|Error e){
            e.printStackTrace();
        }
    }


}

