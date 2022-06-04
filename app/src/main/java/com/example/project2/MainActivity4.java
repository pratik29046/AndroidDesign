package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.r0adkll.slidr.model.SlidrInterface;

public class MainActivity4 extends AppCompatActivity {
    TextView textView;
    RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        textView=findViewById(R.id.sub);
        relativeLayout=findViewById(R.id.back);
//        String text="<font color=#F40529>SUBSCRIBE</font>"
//                "<font color=#F40529> SUBSCRIBE</font>";
//        textView.setText(Html.fromHtml(text));

        relativeLayout.setOnClickListener(new View.OnClickListener() {
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