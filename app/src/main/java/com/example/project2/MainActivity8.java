package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import org.w3c.dom.Text;

public class MainActivity8 extends AppCompatActivity {
     TextView textView,textView1;
     VideoView videoView;
     ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        textView=findViewById(R.id.name);
        textView1=findViewById(R.id.desc);
        imageView=findViewById(R.id.img);
        videoView=findViewById(R.id.video1);

        Intent intent=getIntent();
        String vid=intent.getStringExtra("vid");
        String name=intent.getStringExtra("name");
        int img=intent.getIntExtra("img",0);
        String desc=intent.getStringExtra("desc");

        videoView.setVideoPath(vid);
        textView.setText(name);
        imageView.setImageResource(img);
        textView1.setText(desc);

    }
}