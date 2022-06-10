package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity9 extends AppCompatActivity {
    ImageView imageView,close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);
        imageView=findViewById(R.id.img);
        close=findViewById(R.id.close);

        Intent intent=getIntent();
        int img=intent.getIntExtra("img",0);
        imageView.setImageResource(img);


        //close event
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });

    }

    public void back(){
        super.onBackPressed();
    }
}