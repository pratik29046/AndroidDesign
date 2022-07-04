package com.example.project2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import  androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.project2.Network.WebService;
import com.example.project2.POJO.Category;
import com.example.project2.POJO.HomePOJO;
import com.example.project2.POJO.Root;
import com.example.project2.POJO.RootSeries;
import com.example.project2.POJO.Series;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import com.CustomAdapter;


public class MainActivity2 extends AppCompatActivity {
    RecyclerView recyclerView,recyclerView1,recyclerView2;
    LinearLayoutManager layoutManager;
    Adapter adapter;
    RelativeLayout popup;
    LinearLayout linearLayoutplay;
    List<SlideModel> slideModels=new ArrayList<SlideModel>();
    BottomNavigationView bottomNavigationView;

    private String url="https://katto.in";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        recyclerView=findViewById(R.id.review);
        popup=findViewById(R.id.continues);
        linearLayoutplay=findViewById(R.id.plays);
        ImageSlider imageSlider=findViewById(R.id.post);

        WebService.getClient().get_HomeData().enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                List<Category> categories=new ArrayList<>();
                for (int i = 0 ; i < response.body().banners.size() ; i ++) {
                    slideModels.add(new SlideModel(url+response.body().banners.get(i).poster, ScaleTypes.FIT));
//                    response.body().getBanners().get(i).get();

                }
                adp(response.body().categories);
                Log.d("TAG", "onResponse: "+response.body().toString());
                imageSlider.setImageList(slideModels);

            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {

            }
        });


        bottomNavigationView=findViewById(R.id.boom);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.menu:
                        startActivity(new Intent(getApplicationContext(),MainActivity4.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.home:
                        return true;

                    case R.id.download:
                        startActivity(new Intent(getApplicationContext(),MainActivity14.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.upcomes:
                        startActivity(new Intent(getApplicationContext(),MainActivity5.class));
                        overridePendingTransition(0,0);
                        return true;

                }

                return false;
            }
        });


        linearLayoutplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(getApplicationContext(),MainActivity13.class);
//                startActivity(intent);
            }
        });  // go to play video

    }

    public void adp(List<Category> categories){
        recyclerView=findViewById(R.id.review);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity2.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView
        recyclerView.setItemAnimator(new DefaultItemAnimator() );
        adapter=new Adapter(this,categories);
        recyclerView.setAdapter(adapter);
    }



    @Override
    public void onBackPressed() {

        BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(MainActivity2.this,R.style.BottomSheetDialogTheme);

                View bottomSheetView= LayoutInflater.from(getApplicationContext()).inflate(
                        R.layout.bottomsheet,(RelativeLayout)findViewById(R.id.hello)
                );
                bottomSheetView.findViewById(R.id.b2).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetView.findViewById(R.id.b1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                finishAffinity();
            }
                });

                bottomSheetView.findViewById(R.id.t1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });


                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
    }

}