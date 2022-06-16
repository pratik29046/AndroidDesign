package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity7 extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<ModelClass3> userList3;
    Adapters1 adapter;
    ImageView imageView,imageView1;
    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        recyclerView=findViewById(R.id.season1);
        imageView=findViewById(R.id.img1);
        imageView1=findViewById(R.id.img3);

        //get data
        Intent intent=getIntent();
        int vid=intent.getIntExtra("vid",0);
        imageView1.setImageResource(vid);

        data1();
        adp4();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
    }


    public void adp4(){
        recyclerView=findViewById(R.id.season1);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new Adapters1(userList3,this);
        recyclerView.setAdapter(adapter);
    }


    public void data1(){
        userList3=new ArrayList<ModelClass3>();
        userList3.add(new ModelClass3(R.drawable.p3 ,"1. The pratik \n 33m",R.drawable.download_recy,"Video is an electronic medium for the recording,hdhhdh dhdh dd jd jdj hfh jjfj fjfj jfjfjb jfj pratik "));
        userList3.add(new ModelClass3(R.drawable.p3 ,"2. She \n 38m",R.drawable.download_recy,"Video is an electronic medium for the recording, "));
        userList3.add(new ModelClass3(R.drawable.p3 ,"3. The pickup \n 34m",R.drawable.download_recy,"Video is an electronic medium for the recording, "));
        userList3.add(new ModelClass3(R.drawable.p3 ,"4. The pickup \n 27m",R.drawable.download_recy,"Video is an electronic medium for the recording, "));
        userList3.add(new ModelClass3(R.drawable.p3 ,"5. The pickup \n 3m",R.drawable.download_recy,"Video is an electronic medium for the recording,"));
        userList3.add(new ModelClass3(R.drawable.p3 ,"6. The pickup \n 43m",R.drawable.download_recy,"Video is an electronic medium for the recording,"));
        userList3.add(new ModelClass3(R.drawable.p3 ,"7. The pickup \n 83m",R.drawable.download_recy,"Video is an electronic medium for the recording, "));
        userList3.add(new ModelClass3(R.drawable.p3 ,"8. The pickup \n 43m",R.drawable.download_recy,"Video is an electronic medium for the recording, "));
        userList3.add(new ModelClass3(R.drawable.p3 ,"9. The pickup \n 39m",R.drawable.download_recy,"Video is an electronic medium for the recording, "));
        userList3.add(new ModelClass3(R.drawable.p3 ,"10. The pickup \n 31m",R.drawable.download_recy,"Video is an electronic medium for the recording, "));
        userList3.add(new ModelClass3(R.drawable.p3 ,"11. The pickup \n 32m",R.drawable.download_recy,"Video is an electronic medium for the recording,"));
        userList3.add(new ModelClass3(R.drawable.p3 ,"12. The pickup \n 34m",R.drawable.download_recy,"Video is an electronic medium for the recording,"));
    }

    public void back(){
        super.onBackPressed();
    }
}