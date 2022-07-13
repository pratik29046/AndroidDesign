package com.katto.pro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.katto.pro.R;

public class MainActivity8 extends AppCompatActivity {
     TextView textView,textView1;
     ImageView videoView;
     ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        textView=findViewById(R.id.name);
//        textView1=findViewById(R.id.desc);
        imageView=findViewById(R.id.img);
        videoView=findViewById(R.id.video1);

//        Intent intent=getIntent();
//        int vid=intent.getIntExtra("vid",0);
//        String name=intent.getStringExtra("name");
//        int img=intent.getIntExtra("img",0);
//        String desc=intent.getStringExtra("desc");
//
//        videoView.setImageResource(vid);
//        textView.setText(name);
//        imageView.setImageResource(img);
//        textView1.setText(desc);

    }
}