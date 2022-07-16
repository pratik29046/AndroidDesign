package com.taak.pro;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.Cursor;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.taak.pro.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.TracksInfo;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultAllocator;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;


public class MainPlayerActivity extends AppCompatActivity {

    private static final String KEY_TRACK_SELECTION_PARAMETERS = "track_selection_parameters";
    //Minimum Video you want to buffer while Playing
    public static final int MIN_BUFFER_DURATION = 2000;
    //Max Video you want to buffer during PlayBack
    public static final int MAX_BUFFER_DURATION = 5000;
    //Min Video you want to buffer before start Playing it
    public static final int MIN_PLAYBACK_START_BUFFER = 1500;
    //Min video You want to buffer when user resumes video
    public static final int MIN_PLAYBACK_RESUME_BUFFER = 2000;


    private boolean isShowingTrackSelectionDialog;
    private DefaultTrackSelector trackSelector;
    private DefaultTrackSelector.Parameters trackSelectionParameters;
    private TracksInfo lastSeenTracksInfo;

    private ImageView playpausebutton, forward10Sec, rewind10Sec, setting, backarrow;
    private float downy, endheight, diffheight, lastx, putx, puty,
            trackx, scaleFactor = 1.0f, savebright = -1.0f;

    private int currentprogress, currentbrightprogress, currentseek, lastprogress, currentitem,
            ontouchpos, selected = 0, resizemode = 0, playbackspeed = 5;

    private Handler handler, hidehandler;

    private long lasttime, currentitemseek;

    private boolean isshow, isdonebyus, isplaybackground, isorientionchange,
            first = true, second = true, third = true, isscalegesture, islock;

    private PlayerView playerView;
    private ExoPlayer player;
    private SeekBar dragseek;
    private TextView aspecttext, videotitle;
    private String name = "";

    //    private ArrayList<VideoModel> videoModels = new ArrayList<>();
//String url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";
//    String url = "https://multiplatform-f.akamaihd.net/i/multi/april11/sintel/sintel-hd_,512x288_450_b,640x360_700_b,768x432_1000_b,1024x576_1400_m,.mp4.csmil/master.m3u8";
////    String url1 = "https://play.dyntube.io/videos/E9nTksPigkCzsfSBsrJP4w"; //Kempe Gowda

    String url = "http://devimages.apple.com/samplecode/adDemo/ad.m3u8";
    String url1 = "https://play.dyntube.io/videos/E9nTksPigkCzsfSBsrJP4w"; //Kempe Gowda

    private final String[] aspectmode = {
            "FIT",
            "FILL",
            "ZOOM",
            "FIXED HEIGHT",
            "FIXED WIDTH"
    };

    private final int[] resource = {
            R.drawable.ic_zoom_stretch,
            R.drawable.ic_baseline_crop_3_2_24,
            R.drawable.ic_crop_white_24dp,
            R.drawable.ic_zoom_inside,
            R.drawable.ic_zoom_original
    };

    protected PowerManager.WakeLock mWakeLock;


    @SuppressLint("InvalidWakeLockTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

            /* This code together with the one in onDestroy()
             * will make the screen be always on until this Activity gets destroyed. */
            final PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
            this.mWakeLock = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "My Tag");
            this.mWakeLock.acquire();

            Log.d("Player oncreate", "oncreate");
            url = getIntent().getStringExtra("link");
            name = getIntent().getStringExtra("name");
            Log.d("PlayerPATH :", "App :" + url + ":" + name);

            scaleFactor = 1.0f;
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                currentitem = bundle.getInt("pos", 0);
                Gson gson = new Gson();
            }
            if (savedInstanceState != null) {
                currentitem = savedInstanceState.getInt("currentitem", 0);
                currentitemseek = savedInstanceState.getLong("currentitemseek", 0);
                savebright = savedInstanceState.getFloat("windowbright", -1.0f);

            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {

                getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;

            }

            if (savedInstanceState != null) {
                // Restore as DefaultTrackSelector.Parameters in case ExoPlayer specific parameters were set.
                trackSelectionParameters =
                        DefaultTrackSelector.Parameters.CREATOR.fromBundle(
                                savedInstanceState.getBundle(KEY_TRACK_SELECTION_PARAMETERS));
            } else {
                trackSelectionParameters =
                        new DefaultTrackSelector.ParametersBuilder(
                                this).build();
            }

            initview();
            intializePlayer();
            startPlayer();
        } catch (Exception | Error e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        try {
            if (!islock) {
                int orientation = MainPlayerActivity.this.getResources().getConfiguration().orientation;
                if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
                }
                finish();
            }
        } catch (Exception | Error e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        try {
            super.onSaveInstanceState(outState);
            updateTrackSelectorParameters();
            outState.putBundle(KEY_TRACK_SELECTION_PARAMETERS, trackSelectionParameters.toBundle());
            outState.putInt("currentitem", currentitem);
            outState.putFloat("windowbright", getWindow().getAttributes().screenBrightness);
            if (isorientionchange) outState.putLong("currentitemseek", player.getCurrentPosition());
        } catch (Exception | Error e) {
            e.printStackTrace();
        }
    }


    public void initview() {
        try {
            setContentView(R.layout.main_player_activity);

            trackSelector = new DefaultTrackSelector(this);

            backarrow = findViewById(R.id.backarrow);
            backarrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
            if (savebright >= 0) {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.screenBrightness = savebright;
                getWindow().setAttributes(lp);
            }


            View speedView = findViewById(R.id.speedview);
            isshow = false;
            hidehandler = new Handler();


            videotitle = findViewById(R.id.videotitle);

            LinearLayout unlockbtn = findViewById(R.id.llLockLayout);
            dragseek = findViewById(R.id.dragseek);
            playpausebutton = findViewById(R.id.imageButton);
            rewind10Sec = findViewById(R.id.exo_rew_1);
            forward10Sec = findViewById(R.id.exo_ffwd_1);

            setting = findViewById(R.id.exo_track_selection_view);
            TextView currentprogresslbl = findViewById(R.id.currentprogress);
            TextView endprogresslbl = findViewById(R.id.endprogress);
            View bottom = findViewById(R.id.bottom);
            View speeder = findViewById(R.id.speeder);
            View touchview = findViewById(R.id.toucher);
            View seeklay = findViewById(R.id.seeklay);
            TextView seektime = findViewById(R.id.seektime);
            TextView seekdelay = findViewById(R.id.seekdelay);
            ImageView aspectbtn = findViewById(R.id.aspectbtn);
            handler = new Handler();
            trackSelector = new DefaultTrackSelector(/* context= */ this);
            lastSeenTracksInfo = TracksInfo.EMPTY;
            LoadControl loadControl = new DefaultLoadControl.Builder()
                    .setAllocator(new DefaultAllocator(true, 16))
                    .setBufferDurationsMs(MIN_BUFFER_DURATION,
                            MAX_BUFFER_DURATION,
                            MIN_PLAYBACK_START_BUFFER,
                            MIN_PLAYBACK_RESUME_BUFFER)
                    .setTargetBufferBytes(-1)
                    .setPrioritizeTimeOverSizeThresholds(true).createDefaultLoadControl();
            player =
                    new ExoPlayer.Builder(/* context= */ this)
                            .setTrackSelector(trackSelector)
                            .setLoadControl(loadControl)
                            .build();
            player.setTrackSelectionParameters(trackSelectionParameters);

            playerView = findViewById(R.id.player);
            playerView.setPlayer(player);


            playpausebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (player.isPlaying()) {
                        player.pause();
                    } else {
                        player.play();
                    }
                }
            });

            forward10Sec.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        int p = (int) player.getCurrentPosition();

                        Log.d("Player ", "forward10Sec bef->" + p);
                        if (p > 1000) {
                            p = p + 10000;
                        } else {
                            p = 0;
                        }
                        // for rewind use -5000
                        Log.d("Player ", "forward10Sec aft->" + p);
                        player.seekTo(p);
                        dragseek.setProgress(p);
                        seektime.setText(milltominute(dragseek.getProgress()));
                        seekdelay.setText("[" + milltominute(p) + "]");
                    } catch (Exception | Error e) {
                        e.printStackTrace();
                    }
                }
            });

            rewind10Sec.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        int p = (int) player.getCurrentPosition();
                        Log.d("Player ", "rewind10Sec before ->" + p);
                        if (p > 10000) {
                            p = p - 10000; // for rewind use -5000
                        } else {
                            p = 0;
                        }
                        Log.d("Player ", "rewind10Sec after->" + p);
                        player.seekTo(p);
                        dragseek.setProgress(p);
                        seektime.setText(milltominute(dragseek.getProgress()));
                        seekdelay.setText("[" + milltominute(p) + "]");
                    } catch (Exception | Error e) {
                        e.printStackTrace();
                    }
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
                                        /* onDismissListener= */
                                        dismissedDialog ->
                                                isShowingTrackSelectionDialog = false);
                        trackSelectionDialog.show(getSupportFragmentManager(), /* tag= */ null);
                    }
                }
            });


            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    if (player == null) return;

                    if (!isdonebyus) dragseek.setProgress((int) player.getCurrentPosition());
                    //  currentprogresslbl.setText(milltominute(player.getCurrentPosition()));
                    handler.postDelayed(this::run, 1000);
                }
            };
            Runnable hiderunnable = new Runnable() {
                @Override
                public void run() {
                    if (player == null) return;
                    if (player.isPlaying()) {
                        hideSystemUI();
                        bottom.setVisibility(View.GONE);
                        speeder.setVisibility(View.GONE);
                    }
                }
            };
            aspectbtn.setImageResource(resource[resizemode % 5]);
            aspecttext = findViewById(R.id.aspecttext);
            aspectbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    resizemode++;
                    aspecttext.setVisibility(View.VISIBLE);
                    if ((resizemode % 5) != 2) {
                        playerView.setScaleX(1.0f);
                        playerView.setScaleY(1.0f);

                    } else {
                        playerView.setScaleX(scaleFactor);
                        playerView.setScaleY(scaleFactor);
                    }
                    aspecttext.setText(aspectmode[resizemode % 5]);
                    aspectbtn.setImageResource(resource[resizemode % 5]);
                    playerView.setResizeMode(resizemode % 5);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            aspecttext.setVisibility(View.GONE);

                        }
                    }, 300);
                    hidehandler.removeCallbacks(hiderunnable);
                    hidehandler.postDelayed(hiderunnable, 4000);
                }
            });
            player.addListener(new Player.Listener() {
                @Override
                public void onIsPlayingChanged(boolean isPlaying) {
                    if (isPlaying) {
                        playpausebutton.setImageResource(R.drawable.ic_baseline_pause_24);
                        endprogresslbl.setText(milltominute(player.getDuration()));
                        dragseek.setMax((int) player.getDuration());
                        handler.postDelayed(runnable, 0);
                    } else {
                        playpausebutton.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                        handler.removeCallbacks(runnable);
                    }
                }


                @Override
                public void onPlaybackStateChanged(int playbackState) {
                    if (playbackState == Player.STATE_ENDED) {
                        Log.d("PlayerEnded", "end");

                    }
                }


            });
            player.setSeekParameters(SeekParameters.CLOSEST_SYNC);
            dragseek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                    if (b) {

                        player.seekTo(i);
                        seektime.setText(milltominute(i));
                        seekdelay.setText("[" + milltominute(i - ontouchpos) + "]");
                    }
                    currentprogresslbl.setText(milltominute(i));


                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                    ontouchpos = seekBar.getProgress();
                    seeklay.setVisibility(View.VISIBLE);
                    player.pause();
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    seeklay.setVisibility(View.GONE);
                    player.play();
                }
            });
            AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            SoundView soundView = findViewById(R.id.volumeview);
            TextView progresstext = findViewById(R.id.progresstext);
            ImageView volumeview = findViewById(R.id.volumeicon);
            View volumecontainerView = findViewById(R.id.volumecontainer);
//            View muteview = findViewById(R.id.muteview);
            SoundView brightView = findViewById(R.id.brightview);
            TextView brightprogresstext = findViewById(R.id.brightprogresstext);
            View brightcontainer = findViewById(R.id.brightcontainer);


//            muteview.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (soundView.getProgress() == 0) {
//                        muteview.setBackgroundResource(R.drawable.roundbg);
//                        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 7, 0);
//                        soundView.setProgress(7);
//                    } else {
//                        muteview.setBackgroundResource(R.drawable.colorroundbg);
//                        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
//                        soundView.setProgress(0);
//                    }
//
//
//                }
//            });
            brightView.setOnsoundProgressChangeListner(new SoundProgressChangeListner() {
                @Override
                public void onchange(int progress) {
                    brightprogresstext.setText(progress + "");

                }
            });
            soundView.setOnsoundProgressChangeListner(new SoundProgressChangeListner() {
                @Override
                public void onchange(int progress) {
                    Log.d("Playerchange", progress + "");
                    progresstext.setText(progress + "");
                    if (progress == 0) {
                        volumeview.setImageResource(R.drawable.ic_baseline_volume_off_24);

                    } else {
                        volumeview.setImageResource(R.drawable.ic_baseline_volume_up_24);
//                        muteview.setBackgroundResource(R.drawable.roundbg);
                    }


                }
            });


            ScaleGestureDetector scaleGestureDetector = new ScaleGestureDetector(this, new MyOnScaleGestureListener());
            touchview.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    Log.d("Playerpointer", motionEvent.getPointerCount() + "," + motionEvent.getAction());
                    if (!islock) scaleGestureDetector.onTouchEvent(motionEvent);
                    if (motionEvent.getAction() == MotionEvent.ACTION_POINTER_2_DOWN && motionEvent.getPointerCount() == 2) {
                        isscalegesture = true;
                    }
                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN && !isscalegesture) {
                        lastx = motionEvent.getX();
                        downy = motionEvent.getY();
                        putx = motionEvent.getX();
                        puty = motionEvent.getY();
                        lasttime = System.currentTimeMillis();
                        endheight = downy - getResources().getDimensionPixelSize(R.dimen.widthmeasure);
                        diffheight = endheight - downy;
                        currentprogress = soundView.getProgress();
                        currentbrightprogress = brightView.getProgress();
                        first = true;
                        second = true;
                        third = true;
                        selected = 0;
                        isdonebyus = false;
                        trackx = motionEvent.getX();
                        currentseek = dragseek.getProgress();

                        Log.d("Playerwidth", touchview.getWidth() + "");

                    } else if (motionEvent.getAction() == MotionEvent.ACTION_MOVE && !isscalegesture) {
                        if (islock) return false;
                        Log.d("Playermyrb", "x=" + lastx + "y=" + downy + "");
                        Log.d("Playermyrb", "x=" + motionEvent.getX() + "y=" + motionEvent.getY() + "," + motionEvent.getAction());
                        float xdistance = motionEvent.getX() - lastx;
                        float ydistance = motionEvent.getY() - downy;
                        if (first && Math.abs(xdistance) == 0 && Math.abs(ydistance) == 0) {


                        } else if ((second && Math.abs(xdistance) < Math.abs(ydistance)) || selected == 1) {
                            if (selected == 0) {
                                selected = 1;
                                first = false;
                                second = true;
                                third = false;
                                if (motionEvent.getX() > view.getWidth() / 2.0f) {
                                    volumecontainerView.setVisibility(View.VISIBLE);
                                } else {
                                    brightcontainer.setVisibility(View.VISIBLE);
                                }
                            }
                            float tempwidth = endheight - motionEvent.getY();
                            float progress = (tempwidth * soundView.getMaxprogess()) / diffheight;
                            Log.d("Playerprogress", (soundView.getMaxprogess() - progress) + "");
                            int jprogress = (int) (soundView.getMaxprogess() - progress);
                            if (volumecontainerView.getVisibility() == View.VISIBLE) {
                                int prog = currentprogress + jprogress;
                                if (prog > soundView.getMaxprogess())
                                    soundView.setProgress(soundView.getMaxprogess());
                                else if (prog < 0) soundView.setProgress(0);
                                else {
                                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, prog, 0);
                                    soundView.setProgress(prog);
                                }

                            } else {
                                int prog = currentbrightprogress + jprogress;
                                if (prog > brightView.getMaxprogess())
                                    brightView.setProgress(brightView.getMaxprogess());
                                else if (prog < 0) brightView.setProgress(0);
                                else {
                                    float brightness = prog / 15.0f;
                                    WindowManager.LayoutParams lp = getWindow().getAttributes();
                                    lp.screenBrightness = brightness;
                                    getWindow().setAttributes(lp);

                                    brightView.setProgress(prog);
                                }
                            }

                            Log.d("Playerscroll", "vertical");
                        } else if (third || selected == 2) {

                            if (selected == 0) {
                                if (player.isPlaying()) {
                                    isdonebyus = true;
                                    player.pause();
                                }
                                second = false;
                                first = false;
                                third = true;
                                selected = 2;
                                playpausebutton.setVisibility(View.GONE);
                                bottom.setVisibility(View.VISIBLE);
                                speeder.setVisibility(View.VISIBLE);
                                //toolbar.setVisibility(View.GONE);
                                seeklay.setVisibility(View.VISIBLE);

                            }

                            int progress = (int) ((60000 * (motionEvent.getX() - trackx)) / touchview.getWidth());

                            if (lastprogress != progress) {
                                player.seekTo(currentseek + progress);
                                dragseek.setProgress(currentseek + progress);
                                seektime.setText(milltominute(dragseek.getProgress()));
                                seekdelay.setText("[" + milltominute(progress) + "]");
                                // Log.d("Playerscroll","horizontal"+milltominute(dragseek.getProgress())+","+milltominute(progress));
                            }
                            lastprogress = progress;


                        }
                        lastx = motionEvent.getX();
                        downy = motionEvent.getY();
                    } else if (motionEvent.getPointerCount() == 1 && motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        if (isscalegesture) {
                            isscalegesture = false;
                        } else {
                            seeklay.setVisibility(View.GONE);
                            if (isdonebyus) player.play();
                            isdonebyus = false;
                            if (islock) {
                                if (unlockbtn.getVisibility() == View.GONE) {
                                    unlockbtn.setVisibility(View.VISIBLE);
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            unlockbtn.setVisibility(View.GONE);
                                        }
                                    }, 2000);
                                }
                            } else {
                                if (motionEvent.getX() == putx && motionEvent.getY() == puty && System.currentTimeMillis() - lasttime <= 1000) {
                                    speedView.setVisibility(View.GONE);
                                    if (isshow) {
                                        hideSystemUI();
                                        bottom.setVisibility(View.GONE);
                                        speeder.setVisibility(View.GONE);
//                                    toolbar.setVisibility(View.GONE);
                                    } else {
                                        showSystemUI();
                                        playpausebutton.setVisibility(View.VISIBLE);
                                        bottom.setVisibility(View.VISIBLE);
                                        speeder.setVisibility(View.VISIBLE);
//                                    toolbar.setVisibility(View.VISIBLE);
                                        hidehandler.postDelayed(hiderunnable, 4000);

                                    }
                                } else {
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {

                                            hideSystemUI();
                                            volumecontainerView.setVisibility(View.INVISIBLE);
                                            brightcontainer.setVisibility(View.INVISIBLE);
                                            bottom.setVisibility(View.GONE);
                                            speeder.setVisibility(View.GONE);
//                                        toolbar.setVisibility(View.GONE);
                                            playpausebutton.setVisibility(View.VISIBLE);

                                        }
                                    }, 300);
                                }
                            }
                        }


                    } else if (isscalegesture) {

                    }


                    return false;
                }
            });
            findViewById(R.id.lockbtn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    islock = true;
                    hideSystemUI();
                    bottom.setVisibility(View.GONE);
                    speeder.setVisibility(View.GONE);
//                toolbar.setVisibility(View.GONE);
                    unlockbtn.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            unlockbtn.setVisibility(View.GONE);
                        }
                    }, 2000);

                }
            });
            unlockbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    islock = false;
                    unlockbtn.setVisibility(View.GONE);
                    showSystemUI();
                    bottom.setVisibility(View.VISIBLE);
                    speeder.setVisibility(View.VISIBLE);
//                toolbar.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            hideSystemUI();
                            bottom.setVisibility(View.GONE);
                            speeder.setVisibility(View.GONE);
//                        toolbar.setVisibility(View.GONE);
                        }
                    }, 4000);
                }
            });
            soundView.setMaxprogress(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
            soundView.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));

            float tempbright = getWindow().getAttributes().screenBrightness;
            if (tempbright < 0) tempbright = 0.5f;
            int mybright = (int) (15 * tempbright);
            brightView.setMaxprogress(soundView.getMaxprogess());
            brightView.setProgress(mybright);

            intializePlayer();
            Button speedbtn = findViewById(R.id.speedbtn);
            speedbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    speedView.setVisibility(View.VISIBLE);
                }
            });
            SeekBar speedseekbar = findViewById(R.id.speedseekbar);
            TextView speedtextview = findViewById(R.id.speedtextview);
            speedseekbar.setProgress(playbackspeed);
            speedbtn.setText(0.5f + (playbackspeed / 10.0f) + "X");
            speedtextview.setText(0.5f + (playbackspeed / 10.0f) + "X");
            speedseekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                    playbackspeed = i;
                    PlaybackParameters param = new PlaybackParameters(0.5f + (playbackspeed / 10.0f));
                    player.setPlaybackParameters(param);
                    speedbtn.setText(0.5f + (playbackspeed / 10.0f) + "X");
                    speedtextview.setText(0.5f + (playbackspeed / 10.0f) + "X");
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            speedView.setVisibility(View.GONE);
                        }
                    }, 3000);
                }
            });

            startPlayer();
        } catch (Exception | Error e) {
            e.printStackTrace();
        }
    }



    public void intializePlayer() {
        try {
            Uri uri = Uri.parse(url);
            MediaItem mediaItem = MediaItem.fromUri(uri);
            player.setMediaItem(mediaItem, currentitemseek);
            currentitemseek = 0;
            videotitle.setText(name);

            DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(
                    MainPlayerActivity.this,
                    Util.getUserAgent(MainPlayerActivity.this, getString(R.string.app_name)),
                    new DefaultBandwidthMeter()
            );
            MediaSource mediaSource = new HlsMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(MediaItem.fromUri(uri));
            player.prepare(mediaSource, true, false);

        } catch (Exception | Error e) {
            e.printStackTrace();

        }
    }


    public class MyOnScaleGestureListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            try {
                scaleFactor *= detector.getScaleFactor();
                if (scaleFactor > 4 || scaleFactor < 0.25) {
                    return true;
                } else {
                    playerView.setScaleX(scaleFactor);
                    playerView.setScaleY(scaleFactor);
                    float per = (1000 * scaleFactor) / 4.5f;
                    aspecttext.setText((int) per + "%");
                    Log.d("Playerzoomper", per + "");
                    if (detector.getScaleFactor() > 1) {
                        Log.d("Playerzoomout", scaleFactor + "");
                    } else {
                        Log.d("Playerzoomin", scaleFactor + "");
                    }
                }
            } catch (Exception | Error e) {
                e.printStackTrace();
            }
            return true;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            try {
                aspecttext.setVisibility(View.VISIBLE);
                scaleFactor = playerView.getScaleX();
                return true;
            } catch (Exception | Error e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            try {
                aspecttext.setVisibility(View.GONE);
                scaleFactor = playerView.getScaleX();
            } catch (Exception | Error e) {
                e.printStackTrace();
            }
        }
    }


    public String milltominute(long milliseconds) {
        try {    // milliseconds to minutes.
            boolean v = false;
            if (milliseconds < 0) {
                v = true;
                milliseconds = Math.abs(milliseconds);
            }
            int seconds = (int) (milliseconds / 1000) % 60;
            int minutes = (int) ((milliseconds / (1000 * 60)) % 60);
            int hours = (int) ((milliseconds / (1000 * 60 * 60)) % 24);
            String time = "";
            if (hours == 0) {
                time = String.format("%02d:%02d", minutes, seconds);
            } else {
                time = String.format("%02d:%02d:%02d", hours, minutes, seconds);
            }

            if (v) return "-" + time;
            else return time;

        } catch (Exception | Error e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    protected void onPause() {
        try {
            super.onPause();
            if (!isplaybackground) pausePlayer();
        } catch (Exception | Error e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getAllMedia() {
        try {
            HashSet<String> videoItemHashSet = new HashSet<>();
            String[] projection = {MediaStore.Video.VideoColumns.DATA, MediaStore.Video.Media.DISPLAY_NAME};
            Cursor cursor = getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, projection, null, null, null);
            try {
                cursor.moveToFirst();
                do {
                    String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
                    String[] divide = path.split("/");
                    videoItemHashSet.add(path);
                } while (cursor.moveToNext());

                cursor.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            ArrayList<String> downloadedList = new ArrayList<>(videoItemHashSet);
            return downloadedList;
        } catch (Exception | Error e) {
            e.printStackTrace();
            return null;
        }
    }

    private void updateTrackSelectorParameters() {
        try {
            if (player != null) {
                // Until the demo app is fully migrated to TrackSelectionParameters, rely on ExoPlayer to use
                // DefaultTrackSelector by default.
                trackSelectionParameters =
                        (DefaultTrackSelector.Parameters) player.getTrackSelectionParameters();
            }
        } catch (Exception | Error e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        try {
            super.onRestoreInstanceState(savedInstanceState);

            Log.d("Playerrestorecall", currentitem + "");
        } catch (Exception | Error e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        try {
            super.onResume();
            hideSystemUI();
        } catch (Exception | Error e) {
            e.printStackTrace();
        }
    }

    private void pausePlayer() {
        try {
            if (player == null) return;
            player.setPlayWhenReady(false);
            player.getPlaybackState();
        } catch (Exception | Error e) {
            e.printStackTrace();
        }
    }

    private void startPlayer() {
        try {
            player.play();
            Log.d("Playersize", "startplayer");
        } catch (Exception | Error e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        try {
            hideSystemUI();
            if (player != null) {
                player.stop();
                player.setVideoSurface(null);
                player.release();
                trackSelector = null;
            }

            Log.d("Playerdestroy", "destroy");
            //player.setVideoSurface(null);
            if (mWakeLock != null) {
                this.mWakeLock.release();
            }
            super.onDestroy();
        } catch (Exception | Error e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDetachedFromWindow() {
        try {
            Log.d("Playerdetach", "onDetachedFromWindow");
//
            super.onDetachedFromWindow();
        } catch (Exception | Error e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        try {
            super.onWindowFocusChanged(hasFocus);
            Log.d("PlayerFocus", hasFocus + "");
            if (hasFocus) {
                //
            }
        } catch (Exception | Error e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAttachedToWindow() {
        try {
            super.onAttachedToWindow();
            Log.d("PlayerFocus", "onAttachedToWindow");
            //  hideSystemUI();
        } catch (Exception | Error e) {
            e.printStackTrace();
        }
    }


    private void hideSystemUI() {
        try {
            backarrow.setVisibility(View.GONE);
            videotitle.setVisibility(View.GONE);

            isshow = false;
            // Enables regular immersive mode.
            // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
            // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_IMMERSIVE
                            // Set the content to appear under the system bars so that the
                            // content doesn't resize when the system bars hide and show.
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            // Hide the nav bar and status bar
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN);
        } catch (Exception | Error e) {
            e.printStackTrace();
        }
    }

    private void showSystemUI() {
        try {
            backarrow.setVisibility(View.VISIBLE);
            videotitle.setVisibility(View.VISIBLE);
            isshow = true;
//
            Log.d("Playerhide", "show");
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        } catch (Exception | Error e) {
            e.printStackTrace();
        }
    }
}