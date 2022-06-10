package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity6 extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<ModelClass2> userList2;
    Adapters adapter;
    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        recyclerView=findViewById(R.id.seasons);
        imageView=findViewById(R.id.img1);
        textView=findViewById(R.id.text18);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start=new Intent(MainActivity6.this,MainActivity7.class);
                startActivity(start);
            }
        });

        data1();
        adp4();

    }
    public void adp4(){
        recyclerView=findViewById(R.id.seasons);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new Adapters(userList2,this);
        recyclerView.setAdapter(adapter);

    }
    public void data1(){
        userList2=new ArrayList<ModelClass2>();
        userList2.add(new ModelClass2("R.raw.videos" ,"1. The pratik \n 33m",R.drawable.download5,"Video is an electronic medium for the recording, "));
        userList2.add(new ModelClass2("R.raw.videos" ,"2. She \n 38m",R.drawable.download5,"Video is an electronic medium for the recording, "));
        userList2.add(new ModelClass2("R.raw.videos" ,"3. The pickup \n 34m",R.drawable.download5,"Video is an electronic medium for the recording, "));
        userList2.add(new ModelClass2("R.raw.videos" ,"4. The pickup \n 27m",R.drawable.download5,"Video is an electronic medium for the recording, "));
        userList2.add(new ModelClass2("R.raw.videos" ,"5. The pickup \n 3m",R.drawable.download5,"Video is an electronic medium for the recording,"));
        userList2.add(new ModelClass2("R.raw.videos" ,"6. The pickup \n 43m",R.drawable.download5,"Video is an electronic medium for the recording,"));
        userList2.add(new ModelClass2("R.raw.videos" ,"7. The pickup \n 83m",R.drawable.download5,"Video is an electronic medium for the recording, "));
        userList2.add(new ModelClass2("R.raw.videos" ,"8. The pickup \n 43m",R.drawable.download5,"Video is an electronic medium for the recording, "));
        userList2.add(new ModelClass2("R.raw.videos" ,"9. The pickup \n 39m",R.drawable.download5,"Video is an electronic medium for the recording, "));
        userList2.add(new ModelClass2("R.raw.videos" ,"10. The pickup \n 31m",R.drawable.download5,"Video is an electronic medium for the recording, "));
        userList2.add(new ModelClass2("R.raw.videos" ,"11. The pickup \n 32m",R.drawable.download5,"Video is an electronic medium for the recording,"));
        userList2.add(new ModelClass2("R.raw.videos" ,"12. The pickup \n 34m",R.drawable.download5,"Video is an electronic medium for the recording,"));

    }

    public void back(){
        super.onBackPressed();
    }
}