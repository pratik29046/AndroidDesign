package com.example.project2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.project2.Network.WebService;
import com.example.project2.POJO.Content;
import com.example.project2.POJO.MovieRootnames;
import com.example.project2.POJO.Upcoming;
import com.example.project2.POJO.UpcomingRoot;
import com.example.project2.POJO.Watchnextmovies;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity5 extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    RecyclerView recyclerView;
    AdapterUpcoming adapterUpcoming;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        bottomNavigationView=findViewById(R.id.booms);
        bottomNavigationView.setSelectedItemId(R.id.upcomes);
        recyclerView=findViewById(R.id.upData);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.menu:
                        startActivity(new Intent(getApplicationContext(),MainActivity4.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainActivity2.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.download:
                        startActivity(new Intent(getApplicationContext(),MainActivity14.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.upcomes:
                        return true;

                }
                return false;
            }
        });


        WebService.getClient().get_upcomingData().enqueue(new Callback<UpcomingRoot>() {
            @Override
            public void onResponse(Call<UpcomingRoot> call, Response<UpcomingRoot> response){
                Log.d("TAG", "onCreate: "+response);
                adps(response.body().contents);
            }
            @Override
            public void onFailure(Call<UpcomingRoot> call, Throwable t){
            }
        });

    }


    public void adps(List<Upcoming> upcomings){
        try {
            recyclerView = findViewById(R.id.upData);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity5.this, LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            adapterUpcoming = new AdapterUpcoming(this, upcomings);
            recyclerView.setAdapter(adapterUpcoming);
        }catch (Exception|Error e){
           e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        try {
            Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
            startActivity(intent);
        }catch (Exception|Error e){
            e.printStackTrace();
        }
    }


}