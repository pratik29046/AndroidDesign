package com.example.project2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity6 extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<ModelClass2> userList2;
    Adapters adapter;
    ImageView imageView;
    TextView textView;
    TextView Title, Dec, Date, Age, Season;
    ImageView img1, img2;

    private String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        recyclerView = findViewById(R.id.seasons);
        imageView = findViewById(R.id.img1);
        textView = findViewById(R.id.text14);
        img2 = findViewById(R.id.img5);

        img1 = findViewById(R.id.img3);
        Title = findViewById(R.id.text2);
        Date = findViewById(R.id.text4);
        Age = findViewById(R.id.text5);
        Season = findViewById(R.id.text6);
        Dec = findViewById(R.id.text11);

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

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });


//        WebService.getClient().get_EpisodeData().enqueue(new Callback<EpisodeRoot>() {
//            @Override
//            public void onResponse(Call<EpisodeRoot> call, Response<EpisodeRoot> response) {
//               adp4(response.body().episodes);
//
//
//            }
//
//            @Override
//            public void onFailure(Call<EpisodeRoot> call, Throwable t) {
//
//            }
//        });


        Intent intent = getIntent();
//        int img=intent.getIntExtra("img",0);
//        String title=intent.getStringExtra("title");
//        String date=intent.getStringExtra("date");
        Content content = new Gson().fromJson(intent.getStringExtra("content"), Content.class);
//        Series series = new Gson().fromJson(intent.getStringExtra("series"), Series.class);

//        img1.setImageResource(content.poster);
        Title.setText(content.name);
////        Date.setText(content.);
        Age.setText(content.age_rating);
//        Season.setText(content.);
//        Dec.setText(content.);

//        Title.setText(series.name);
//        Age.setText(series.name);


//        if(Objects.equals(content.url, "/api/content/movies/nitesh")){
//            Toast.makeText(getApplicationContext(),"hshhshshsh",Toast.LENGTH_SHORT).show();
//        }else{
//
//        }


//        data1();





}

    public void adp4(List<Episode>episode) {
        recyclerView = findViewById(R.id.seasons);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapters(episode, this);
        recyclerView.setAdapter(adapter);
    }

    public void data1(){
        userList2=new ArrayList<ModelClass2>();
        userList2.add(new ModelClass2(R.drawable.p3 ,"1. The pratik \n 33m",R.drawable.download_recy,"Video is an electronic medium for the recording,hdhhdh dhdh dd jd jdj hfh jjfj fjfj jfjfjb jfj pratik "));
        userList2.add(new ModelClass2(R.drawable.p3 ,"2. She \n 38m",R.drawable.download_recy,"Video is an electronic medium for the recording, "));
        userList2.add(new ModelClass2(R.drawable.p3 ,"3. The pickup \n 34m",R.drawable.download_recy,"Video is an electronic medium for the recording, "));
        userList2.add(new ModelClass2(R.drawable.p3 ,"4. The pickup \n 27m",R.drawable.download_recy,"Video is an electronic medium for the recording, "));
        userList2.add(new ModelClass2(R.drawable.p3 ,"5. The pickup \n 3m",R.drawable.download_recy,"Video is an electronic medium for the recording,"));
        userList2.add(new ModelClass2(R.drawable.p3 ,"6. The pickup \n 43m",R.drawable.download_recy,"Video is an electronic medium for the recording,"));
        userList2.add(new ModelClass2(R.drawable.p3 ,"7. The pickup \n 83m",R.drawable.download_recy,"Video is an electronic medium for the recording, "));
        userList2.add(new ModelClass2(R.drawable.p3 ,"8. The pickup \n 43m",R.drawable.download_recy,"Video is an electronic medium for the recording, "));
        userList2.add(new ModelClass2(R.drawable.p3 ,"9. The pickup \n 39m",R.drawable.download_recy,"Video is an electronic medium for the recording, "));
        userList2.add(new ModelClass2(R.drawable.p3 ,"10. The pickup \n 31m",R.drawable.download_recy,"Video is an electronic medium for the recording, "));
        userList2.add(new ModelClass2(R.drawable.p3 ,"11. The pickup \n 32m",R.drawable.download_recy,"Video is an electronic medium for the recording,"));
        userList2.add(new ModelClass2(R.drawable.p3 ,"12. The pickup \n 34m",R.drawable.download_recy,"Video is an electronic medium for the recording,"));

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
                Intent start=new Intent(MainActivity6.this,MainActivity13.class);
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