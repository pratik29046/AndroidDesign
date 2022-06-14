package com.example.project2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.StyledPlayerControlView;
import com.google.android.exoplayer2.ui.StyledPlayerView;


public class MainActivity11 extends AppCompatActivity  {
   ImageView b1;
    VideoView videoView;
   StyledPlayerView styledPlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main11);
//        videoView=findViewById(R.id.video);
//        videoView.setMediaController(new MediaController(this));
//        videoView.setOnCompletionListener(this);
//        String path="android.resource://"
//                + getPackageName() + "/" +R.raw.video2;
//        String online="https://multiplatform-f.akamaihd.net/i/multi/will/bunny/big_buck_bunny_,640x360_400,640x360_700,640x360_1000,950x540_1500,.f4v.csmil/master.m3u8";
//        Uri uri = Uri.parse(online);
//        videoView.setVideoURI(uri);
//        videoView.start();
        
        styledPlayerView=findViewById(R.id.exo_plays);

        SimpleExoPlayer simpleExoPlayer=new SimpleExoPlayer.Builder(this).build();
        
        styledPlayerView.setPlayer(simpleExoPlayer);
        MediaItem mediaItem = MediaItem.fromUri("https://multiplatform-f.akamaihd.net/i/multi/will/bunny/big_buck_bunny_,640x360_400,640x360_700,640x360_1000,950x540_1500,.f4v.csmil/master.m3u8");
        simpleExoPlayer.addMediaItem(mediaItem);
        simpleExoPlayer.prepare();
        simpleExoPlayer.play();


    }
    
//    @Override
//    public void onCompletion(MediaPlayer mp) {
//
//
//    }
    
    
    
    
    
}