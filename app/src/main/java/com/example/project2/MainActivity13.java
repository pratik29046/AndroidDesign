package com.example.project2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GestureDetectorCompat;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.Image;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerControlView;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.material.progressindicator.LinearProgressIndicator;

public class MainActivity13 extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private boolean isShowingTrackSelectionDialog;
    private DefaultTrackSelector trackSelector;

    String[] speed = {"0.25x","0.5x","Normal","1.5x","2x"};
    //demo url
    String url1 = "https://multiplatform-f.akamaihd.net/i/multi/will/bunny/big_buck_bunny_,640x360_400,640x360_700,640x360_1000,950x540_1500,.f4v.csmil/master.m3u8";

    PlayerView playerView;
    SimpleExoPlayer simpleExoPlayer;
    Boolean isLock= false;


    private GestureDetectorCompat gestureDetectorCompat;
    private int brightnesss=0;


    ImageView close;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main13);


        trackSelector = new DefaultTrackSelector(this);
        simpleExoPlayer = new SimpleExoPlayer.Builder(this).setTrackSelector(trackSelector).build();
        playerView = findViewById(R.id.exoPlayerView);
        playerView.setPlayer(simpleExoPlayer);
        MediaItem mediaItem = MediaItem.fromUri(url1);
        simpleExoPlayer.addMediaItem(mediaItem);
        simpleExoPlayer.prepare();
        simpleExoPlayer.play();


        ImageView farwordBtn = playerView.findViewById(R.id.fwd);
        ImageView rewBtn = playerView.findViewById(R.id.rew);
        ImageView setting = playerView.findViewById(R.id.exo_track_selection_view);
        ImageView speedBtn = playerView.findViewById(R.id.exo_playback_speed);
        TextView speedTxt = playerView.findViewById(R.id.speed);
        ImageView lock = playerView.findViewById(R.id.lock);
        RelativeLayout name=playerView.findViewById(R.id.names);
        Button button=playerView.findViewById(R.id.brightness);
        ImageView close=playerView.findViewById(R.id.back);





        gestureDetectorCompat= new GestureDetectorCompat(this,this);
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetectorCompat.onTouchEvent(event);
                Toast.makeText(getApplicationContext(),"Hello",Toast.LENGTH_SHORT).show();
                return true;

            }
        });




        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });







        speedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity13.this);
                builder.setTitle("Set Speed");
                builder.setItems(speed, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // the user clicked on colors[which]

                        if (which==0){

                            speedTxt.setVisibility(View.VISIBLE);
                            speedTxt.setText("0.25X");
                            PlaybackParameters param = new PlaybackParameters(0.5f);
                            simpleExoPlayer.setPlaybackParameters(param);


                        }  if (which==1){

                            speedTxt.setVisibility(View.VISIBLE);
                            speedTxt.setText("0.5X");
                            PlaybackParameters param = new PlaybackParameters(0.5f);
                            simpleExoPlayer.setPlaybackParameters(param);


                        }
                        if (which==2){

                            speedTxt.setVisibility(View.GONE);
                            PlaybackParameters param = new PlaybackParameters(1f);
                            simpleExoPlayer.setPlaybackParameters(param);


                        }
                        if (which==3){
                            speedTxt.setVisibility(View.VISIBLE);
                            speedTxt.setText("1.5X");
                            PlaybackParameters param = new PlaybackParameters(1.5f);
                            simpleExoPlayer.setPlaybackParameters(param);

                        }
                        if (which==4){


                            speedTxt.setVisibility(View.VISIBLE);
                            speedTxt.setText("2X");

                            PlaybackParameters param = new PlaybackParameters(2f);
                            simpleExoPlayer.setPlaybackParameters(param);



                        }



                    }
                });
                builder.show();

            }
        });



        lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isLock){
                    lock.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_baseline_lock_24));
                    lockScreen(isLock);
                }else{
                    lock.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_baseline_lock_open_24));
                    lockScreen(!isLock);
                }
//
                if(isLock =!isLock) {
                    
                    lockScreen(isLock);

                }

            }

        });



        farwordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                simpleExoPlayer.seekTo(simpleExoPlayer.getCurrentPosition() + 10000);

            }
        });
        rewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                long num = simpleExoPlayer.getCurrentPosition() - 10000;
                if (num < 0) {

                    simpleExoPlayer.seekTo(0);


                } else {

                    simpleExoPlayer.seekTo(simpleExoPlayer.getCurrentPosition() - 10000);

                }


            }
        });

//        ImageView fullscreenButton = playerView.findViewById(R.id.fullscreen);
//        fullscreenButton.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("SourceLockedOrientationActivity")
//            @Override
//            public void onClick(View view) {
//
//
//                int orientation = MainActivity13.this.getResources().getConfiguration().orientation;
//                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
//                    // code for portrait mode
//
//                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//
//
//                } else {
//                    // code for landscape mode
//
//                    Toast.makeText(MainActivity13.this, "Land", Toast.LENGTH_SHORT).show();
//                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//
//                }
//
//
//            }
//        });


        findViewById(R.id.exo_play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                simpleExoPlayer.play();

            }
        });
        findViewById(R.id.exo_pause).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                simpleExoPlayer.pause();

            }
        });


        simpleExoPlayer.addListener(new Player.Listener() {
            @Override
            public void onPlaybackStateChanged(int state) {
                if (state == ExoPlayer.STATE_ENDED) {

                }

            }
        });


        playerView.setControllerVisibilityListener(new PlayerControlView.VisibilityListener() {
            @Override
            public void onVisibilityChange(int visibility) {

            }
        });


        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isShowingTrackSelectionDialog
                        && TrackSelectionDialog.willHaveContent(trackSelector)) {
                    isShowingTrackSelectionDialog = true;
                    TrackSelectionDialog trackSelectionDialog =
                            TrackSelectionDialog.createForTrackSelector(
                                    trackSelector,
                                    /* onDismissListener= */ dismissedDialog -> isShowingTrackSelectionDialog = false);
                    trackSelectionDialog.show(getSupportFragmentManager(), /* tag= */ null);


                }


            }
        });



    }

    protected void releasePlayer() {
        if (simpleExoPlayer != null) {
            simpleExoPlayer.release();
            simpleExoPlayer = null;
            trackSelector = null;
        }

    }

       private void lockScreen(boolean lock){
//        ImageView one=playerView.findViewById(R.id.rew);
//        ImageView two=playerView.findViewById(R.id.fwd);
//        ImageView three=playerView.findViewById(R.id.exo_play);
        RelativeLayout four=playerView.findViewById(R.id.names);
//        ImageView five=playerView.findViewById(R.id.exo_track_selection_view);
//        ImageView six=playerView.findViewById(R.id.exo_playback_speed);
//        ImageView seven=playerView.findViewById(R.id.speed);

        if(lock){
//            one.setVisibility(View.GONE);
//            two.setVisibility(View.GONE);
//            three.setVisibility(View.GONE);
            four.setVisibility(View.GONE);
//            five.setVisibility(View.GONE);
//            six.setVisibility(View.GONE);
//            seven.setVisibility(View.GONE);
        }else
        {
//            one.setVisibility(View.VISIBLE);
//            two.setVisibility(View.VISIBLE);
//            three.setVisibility(View.VISIBLE);
            four.setVisibility(View.VISIBLE);
//            five.setVisibility(View.GONE);
//            six.setVisibility(View.GONE);
//            seven.setVisibility(View.GONE);

        }

    }


    @Override
    public void onStop() {
        super.onStop();
        releasePlayer();
    }







    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }


    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Button button1=playerView.findViewById(R.id.brightness);
        float sWidth= Resources.getSystem().getDisplayMetrics().widthPixels;
        if(Math.abs(distanceX) <= Math.abs(distanceY)){
            if((e1.getXPrecision()) < (sWidth/2)){
                button1.setVisibility(View.VISIBLE);
//                Toast.makeText(getApplicationContext(),"Hello hhddhdhdhhdhd",Toast.LENGTH_SHORT).show();
                boolean inc=distanceY > 0;
                int i;
                if(inc) {
                   i=brightnesss++;

                    Toast.makeText(getApplicationContext(),"11111111",Toast.LENGTH_SHORT).show();
                }else{
                   i=brightnesss--;
                    Toast.makeText(getApplicationContext(),"00000000",Toast.LENGTH_SHORT).show();
                }
                setbright(i);


            }else{
                button1.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(),"Hello Javatpoint",Toast.LENGTH_SHORT).show();
            }
//            button1.setVisibility(View.VISIBLE);


        }
        return true;
    }

    private void setbright(int value){
        float d=1.0f/30;
        WindowManager.LayoutParams lp=this.getWindow().getAttributes();
        lp.screenBrightness=d * value;
        this.getWindow().setAttributes(lp);  // change the side
//        Toast.makeText(getApplicationContext(),d,Toast.LENGTH_SHORT).show();


    }



    public void back(){
        super.onBackPressed();
    }


}