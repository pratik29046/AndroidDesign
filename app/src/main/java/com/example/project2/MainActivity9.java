package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity9 extends AppCompatActivity {
    ImageView imageView,close,img1,pratik;

    TextView textView ,Title,Session,Decs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);
        imageView=findViewById(R.id.img);
        close=findViewById(R.id.close);
        textView=findViewById(R.id.episodesinfo);
        Title=findViewById(R.id.text1);
        Session=findViewById(R.id.text2);
        Decs=findViewById(R.id.text3);
        img1=findViewById(R.id.img1);
        pratik=findViewById(R.id.pratik);

        pratik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent video=new Intent(MainActivity9.this,MainActivity13.class);
                startActivity(video);
            }
        });

        Intent intent=getIntent();
        int img=intent.getIntExtra("img",0);
        String title=intent.getStringExtra("title");
        String session=intent.getStringExtra("session");
        String desc=intent.getStringExtra("decs");
        imageView.setImageResource(img);
        Title.setText(title);
        Session.setText(session);
        Decs.setText(desc);
//        img1.setImageResource(img);


        // for move Episodes
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(MainActivity9.this,MainActivity5.class);
                startActivity(intent1);
            }
        });

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