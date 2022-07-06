package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

public class UpcommingNext extends AppCompatActivity {

    String url = "https://multiplatform-f.akamaihd.net/i/multi/will/bunny/big_buck_bunny_,640x360_400,640x360_700,640x360_1000,950x540_1500,.f4v.csmil/master.m3u8";

    ImageView poster,img1;
    PlayerView playerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcomming_next);
        img1=findViewById(R.id.img34);
        poster=findViewById(R.id.img11);
        playerView=findViewById(R.id.exoplayer1);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"hhhhh",Toast.LENGTH_SHORT).show();
                img1.setVisibility(View.GONE);
                poster.setVisibility(View.GONE);
                playerView.setVisibility(View.VISIBLE);
            }
        });

        SimpleExoPlayer simpleExoPlayer = new SimpleExoPlayer.Builder(this).build();
        PlayerView playerView = (PlayerView) findViewById(R.id.exoplayer1);
        playerView.setPlayer(simpleExoPlayer);

        MediaItem mediaItem = MediaItem.fromUri(url);
        simpleExoPlayer.addMediaItem(mediaItem);
        simpleExoPlayer.prepare();
        simpleExoPlayer.play();



    }
}