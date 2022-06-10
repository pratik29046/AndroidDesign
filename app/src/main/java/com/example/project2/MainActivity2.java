package com.example.project2;

import  androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import com.CustomAdapter;


public class MainActivity2 extends AppCompatActivity implements Recycler{
    ImageView menu,bro;
    RecyclerView recyclerView,recyclerView1,recyclerView2;
    LinearLayoutManager layoutManager;
    List<ModelClass>userList;
    List<ModelClass>userList1;
    Adapter adapter;
    RelativeLayout popup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        recyclerView=findViewById(R.id.review);
        recyclerView1=findViewById(R.id.review1);
        menu=findViewById(R.id.menu);
        popup=findViewById(R.id.continues);
        bro=findViewById(R.id.bro);


        popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(MainActivity2.this,R.style.BottomSheetDialogTheme);
//
//                View bottomSheetView= LayoutInflater.from(getApplicationContext()).inflate(
//                        R.layout.bottomsheet,(RelativeLayout)findViewById(R.id.hello)
//                );
//
//                bottomSheetView.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        bottomSheetDialog.dismiss();
//                    }
//                });
//                bottomSheetDialog.setContentView(bottomSheetView);
//                bottomSheetDialog.show();


            }
        });







//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
//        r.setLayoutManager(linearLayoutManager);
//        show();

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menu=new Intent(MainActivity2.this,MainActivity4.class);
                startActivity(menu);
            }
        });

        bro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bro=new Intent(MainActivity2.this,MainActivity5.class);
                startActivity(bro);
            }
        });



        data();
        adp();
        adp1();
    }

    public void adp(){
        recyclerView=findViewById(R.id.review);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity2.this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView
        recyclerView.setItemAnimator(new DefaultItemAnimator() );
        adapter=new Adapter(userList,this);
        recyclerView.setAdapter(adapter);
    }

    public void adp1(){
        recyclerView1=findViewById(R.id.review1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity2.this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView1.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView
        recyclerView1.setItemAnimator(new DefaultItemAnimator() );
        adapter=new Adapter(userList1,this);
        recyclerView1.setAdapter(adapter);

    }
    public void data(){
        userList=new ArrayList<>();
        userList.add(new ModelClass(R.drawable.titanic));
        userList.add(new ModelClass(R.drawable.war));
        userList.add(new ModelClass(R.drawable.extraction));
        userList.add(new ModelClass(R.drawable.movie));
        userList.add(new ModelClass(R.drawable.titanic));
        userList.add(new ModelClass(R.drawable.war));
        userList.add(new ModelClass(R.drawable.extraction));



        userList1=new ArrayList<>();
        userList1.add(new ModelClass(R.drawable.titanic));
        userList1.add(new ModelClass(R.drawable.war));
        userList1.add(new ModelClass(R.drawable.extraction));
        userList1.add(new ModelClass(R.drawable.movie));
        userList1.add(new ModelClass(R.drawable.titanic));
        userList1.add(new ModelClass(R.drawable.war));
        userList1.add(new ModelClass(R.drawable.extraction));



    }

    @Override
    public void onItemClick(int position) {

        BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(MainActivity2.this,R.style.BottomSheetDialogTheme);

        View bottomSheetView= LayoutInflater.from(getApplicationContext()).inflate(
                R.layout.bottomsheet,(RelativeLayout)findViewById(R.id.hello)
        );

        bottomSheetView.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();

    }

}