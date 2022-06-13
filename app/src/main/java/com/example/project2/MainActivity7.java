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
    ImageView imageView;
    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        recyclerView=findViewById(R.id.season1);
        imageView=findViewById(R.id.img1);
        videoView=findViewById(R.id.img3);

        //get data
        Intent intent=getIntent();
        String vid=intent.getStringExtra("vid");
        videoView.setVideoPath(vid);

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
        userList3.add(new ModelClass3("R.raw.videos" ,"1. The pickup \n 100m",R.drawable.download5,"Video is an electronic medium for the recording, "));
        userList3.add(new ModelClass3("R.raw.videos" ,"2. She \n 38m",R.drawable.download5,"Video is an electronic medium for the recording, "));
        userList3.add(new ModelClass3("R.raw.videos" ,"3. The pickup \n 34m",R.drawable.download5,"Video is an electronic medium for the recording, "));
        userList3.add(new ModelClass3("R.raw.videos" ,"4. The pickup \n 27m",R.drawable.download5,"Video is an electronic medium for the recording, "));
        userList3.add(new ModelClass3("R.raw.videos" ,"5. The pickup \n 3m",R.drawable.download5,"Video is an electronic medium for the recording,"));
        userList3.add(new ModelClass3("R.raw.videos" ,"6. The pickup \n 43m",R.drawable.download5,"Video is an electronic medium for the recording,"));
        userList3.add(new ModelClass3("R.raw.videos" ,"7. The pickup \n 83m",R.drawable.download5,"Video is an electronic medium for the recording, "));
        userList3.add(new ModelClass3("R.raw.videos" ,"8. The pickup \n 43m",R.drawable.download5,"Video is an electronic medium for the recording, "));
        userList3.add(new ModelClass3("R.raw.videos" ,"9. The pickup \n 39m",R.drawable.download5,"Video is an electronic medium for the recording, "));
        userList3.add(new ModelClass3("R.raw.videos" ,"10. The pickup \n 31m",R.drawable.download5,"Video is an electronic medium for the recording, "));
        userList3.add(new ModelClass3("R.raw.videos" ,"11. The pickup \n 32m",R.drawable.download5,"Video is an electronic medium for the recording,"));
        userList3.add(new ModelClass3("R.raw.videos" ,"12. The pickup \n 34m",R.drawable.download5,"Video is an electronic medium for the recording,"));
    }

    public void back(){
        super.onBackPressed();
    }
}