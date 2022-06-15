package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity5 extends AppCompatActivity {
    TextView textView,Title,Dec,Session;
    ImageView imageView, img1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        textView=findViewById(R.id.text15);
        imageView=findViewById(R.id.img1);
        img1=findViewById(R.id.img3);
        Title=findViewById(R.id.text2);
        Session=findViewById(R.id.text4);
        Dec=findViewById(R.id.text11);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent season= new Intent(MainActivity5.this,MainActivity6.class);
                startActivity(season);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });


        Intent intent=getIntent();
        int img=intent.getIntExtra("img",0);
        String title=intent.getStringExtra("title");
        String session=intent.getStringExtra("session");
        String desc=intent.getStringExtra("decs");
        img1.setImageResource(img);
        Title.setText(title);
        Session.setText(session);
        Dec.setText(desc);






    }

    public void back(){
       super.onBackPressed();
    }
}