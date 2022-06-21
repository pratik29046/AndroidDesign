package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Timer time;
//    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.information_background));
        setContentView(R.layout.activity_main);
         time=new Timer();
//        text=findViewById(R.id.text);
//        text.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent send = new Intent(MainActivity.this, MainActivity2.class);
//                startActivity(send);
//            }
//        });

        time.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent send = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(send);
            }
        },3000);

    }
}