package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;


public class MainActivity11 extends AppCompatActivity {
   ImageView b1;
    VideoView videoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main11);
        videoView=findViewById(R.id.video);
        String path="android.resource://"+getPackageName()+"/raw/video2";
//        String onlie="http://devimages.apple.com/samplecode/adDemo/ad.m3u8";
        Uri videourl=Uri.parse(path);
        videoView.setVideoURI(videourl);

        videoView.start();




    }

}