package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.project2.Network.WebService;
import com.example.project2.POJO.Category;
import com.example.project2.POJO.WelcomeBanner;
import com.example.project2.POJO.WelcomeRoot;

import java.sql.Time;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Timer time;
    AdapterWelcome adapterWelcome;
    RecyclerView recyclerView;
//    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.information_background));
        setContentView(R.layout.activity_main);
         time=new Timer();
//        text=findViewById(R.id.text);
//        text.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent send = new Intent(MainActivity.this, MainActivity2.class);
//                startActivity(send);
//            }
//        });

        time.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent send = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(send);
            }
        },8000);


        WebService.getClient().get_welcomeData().enqueue(new Callback<WelcomeRoot>() {
            @Override
            public void onResponse(Call<WelcomeRoot> call, Response<WelcomeRoot> response) {
                adp(response.body().welcome_banners);
            }

            @Override
            public void onFailure(Call<WelcomeRoot> call, Throwable t) {

            }
        });

    }

    public void adp(List<WelcomeBanner> welcomeBanners){
        recyclerView=findViewById(R.id.Welcome);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView
        recyclerView.setItemAnimator(new DefaultItemAnimator() );
        adapterWelcome=new AdapterWelcome(this,welcomeBanners);
        recyclerView.setAdapter(adapterWelcome);
    }


}