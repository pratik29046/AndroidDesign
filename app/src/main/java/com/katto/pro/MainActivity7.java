package com.katto.pro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;

import com.katto.pro.R;

public class MainActivity7 extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
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

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
    }

    public void back(){
        try {
            super.onBackPressed();
        }catch (Exception| Error e){
            e.printStackTrace();
        }

    }
}