package com.example.project2;

import  androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

//import com.CustomAdapter;


public class MainActivity2 extends AppCompatActivity implements Recycler{
    ImageView menu,bro;
    RecyclerView recyclerView,recyclerView1,recyclerView2;
    LinearLayoutManager layoutManager;
    List<ModelClass>userList;
    List<ModelClass>userList1;
    List<ModelClass4>userList4;
    Adapter adapter;
    Adapters2 adapters;
    RelativeLayout popup;
    LinearLayout linearLayoutplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        recyclerView=findViewById(R.id.review);
        recyclerView1=findViewById(R.id.review1);
        recyclerView2=findViewById(R.id.post);
        menu=findViewById(R.id.menu);
        popup=findViewById(R.id.continues);
        bro=findViewById(R.id.bro);
        linearLayoutplay=findViewById(R.id.plays);



//        popup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(MainActivity2.this,R.style.BottomSheetDialogTheme);
////
////                View bottomSheetView= LayoutInflater.from(getApplicationContext()).inflate(
////                        R.layout.bottomsheet,(RelativeLayout)findViewById(R.id.hello)
////                );
////
////                bottomSheetView.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////                        bottomSheetDialog.dismiss();
////                    }
////                });
////                bottomSheetDialog.setContentView(bottomSheetView);
////                bottomSheetDialog.show();
//
//
//            }
//        });







//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
//        r.setLayoutManager(linearLayoutManager);
//        show();



        linearLayoutplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity13.class);
                startActivity(intent);
            }
        });  // go to play video

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
        adp2();
    }

    public void adp(){
        recyclerView=findViewById(R.id.review);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity2.this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView
        recyclerView.setItemAnimator(new DefaultItemAnimator() );
        adapter=new Adapter(userList,this,this);
        recyclerView.setAdapter(adapter);
    }

    public void adp1(){
        recyclerView1=findViewById(R.id.review1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity2.this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView1.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView
        recyclerView1.setItemAnimator(new DefaultItemAnimator() );
        adapter=new Adapter(userList1,this,this);
        recyclerView1.setAdapter(adapter);

    }

    public void adp2(){
        recyclerView2=findViewById(R.id.post);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity2.this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView2.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView
        recyclerView2.setItemAnimator(new DefaultItemAnimator() );
        adapters= new Adapters2(userList4,this);
        recyclerView2.setAdapter(adapters);

    }




    public void data(){
        userList=new ArrayList<>();
        userList.add(new ModelClass(R.drawable.p3,"Titanic","2020","22+","Season 1","hello ddh hdh jjdj jjdjj jdjd jjdj jdn jjdjdj jdjdj jdjdj jdjdj jdjdj jdjdddjj jdjd jdjd jdj jdjjd jdjd jdjdjdjdjdj djdjdj djjddj djd jdjjd jdjjdj jdjdj djdjj jddjjd"));
        userList.add(new ModelClass(R.drawable.extraction,"War","2022","42+","Season 2","hello ddh hdh jjdj jjdjj jdjd jjdj jdn jjdjdj jdjdj jdjdj jdjdj jdjdj jdjdddjj jdjd jdjd jdj jdjjd jdjd jdjdjdjdjdj djdjdj djjddj djd jdjjd jdjjdj jdjdj djdjj jddjjd"));
        userList.add(new ModelClass(R.drawable.war,"Extraction","2025","82+","Season 5","hello ddh hdh jjdj jjdjj jdjd jjdj jdn jjdjdj jdjdj jdjdj jdjdj jdjdj jdjdddjj jdjd jdjd jdj jdjjd jdjd jdjdjdjdjdj djdjdj djjddj djd jdjjd jdjjdj jdjdj djdjj jddjjd"));
        userList.add(new ModelClass(R.drawable.p3,"Titanic","2020","22+","Season 1","hello ddh hdh jjdj jjdjj jdjd jjdj jdn jjdjdj jdjdj jdjdj jdjdj jdjdj jdjdddjj jdjd jdjd jdj jdjjd jdjd jdjdjdjdjdj djdjdj djjddj djd jdjjd jdjjdj jdjdj djdjj jddjjd"));
        userList.add(new ModelClass(R.drawable.extraction,"War","2022","42+","Season 2","hello ddh hdh jjdj jjdjj jdjd jjdj jdn jjdjdj jdjdj jdjdj jdjdj jdjdj jdjdddjj jdjd jdjd jdj jdjjd jdjd jdjdjdjdjdj djdjdj djjddj djd jdjjd jdjjdj jdjdj djdjj jddjjd"));
        userList.add(new ModelClass(R.drawable.war,"Extraction","2025","82+","Season 5","hello ddh hdh jjdj jjdjj jdjd jjdj jdn jjdjdj jdjdj jdjdj jdjdj jdjdj jdjdddjj jdjd jdjd jdj jdjjd jdjd jdjdjdjdjdj djdjdj djjddj djd jdjjd jdjjdj jdjdj djdjj jddjjd"));




        userList1=new ArrayList<>();
        userList1.add(new ModelClass(R.drawable.p3,"Titanic","2020","22+","season 1","hello ddh hdh jjdj jjdjj jdjd jjdj jdn jjdjdj jdjdj jdjdj jdjdj jdjdj jdjdddjj jdjd jdjd jdj jdjjd jdjd jdjdjdjdjdj djdjdj djjddj djd jdjjd jdjjdj jdjdj djdjj jddjjd"));
        userList1.add(new ModelClass(R.drawable.extraction,"War","2022","42","season 2","hello ddh hdh jjdj jjdjj jdjd jjdj jdn jjdjdj jdjdj jdjdj jdjdj jdjdj jdjdddjj jdjd jdjd jdj jdjjd jdjd jdjdjdjdjdj djdjdj djjddj djd jdjjd jdjjdj jdjdj djdjj jddjjd"));
        userList1.add(new ModelClass(R.drawable.war,"Extraction","2025","82","season 5","hello ddh hdh jjdj jjdjj jdjd jjdj jdn jjdjdj jdjdj jdjdj jdjdj jdjdj jdjdddjj jdjd jdjd jdj jdjjd jdjd jdjdjdjdjdj djdjdj djjddj djd jdjjd jdjjdj jdjdj djdjj jddjjd"));
        userList1.add(new ModelClass(R.drawable.p3,"Titanic","2020","22","season 1","hello ddh hdh jjdj jjdjj jdjd jjdj jdn jjdjdj jdjdj jdjdj jdjdj jdjdj jdjdddjj jdjd jdjd jdj jdjjd jdjd jdjdjdjdjdj djdjdj djjddj djd jdjjd jdjjdj jdjdj djdjj jddjjd"));
        userList1.add(new ModelClass(R.drawable.extraction,"War","2022","42","season 2","hello ddh hdh jjdj jjdjj jdjd jjdj jdn jjdjdj jdjdj jdjdj jdjdj jdjdj jdjdddjj jdjd jdjd jdj jdjjd jdjd jdjdjdjdjdj djdjdj djjddj djd jdjjd jdjjdj jdjdj djdjj jddjjd"));
        userList1.add(new ModelClass(R.drawable.war,"Extraction","2025","82","season 5","hello ddh hdh jjdj jjdjj jdjd jjdj jdn jjdjdj jdjdj jdjdj jdjdj jdjdj jdjdddjj jdjd jdjd jdj jdjjd jdjd jdjdjdjdjdj djdjdj djjddj djd jdjjd jdjjdj jdjdj djdjj jddjjd"));

        userList4=new ArrayList<>();
        userList4.add(new ModelClass4(R.drawable.image2));
        userList4.add(new ModelClass4(R.drawable.image2));
        userList4.add(new ModelClass4(R.drawable.image2));
        userList4.add(new ModelClass4(R.drawable.image2));
        userList4.add(new ModelClass4(R.drawable.image2));
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