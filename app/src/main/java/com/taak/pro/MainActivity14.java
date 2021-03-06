package com.taak.pro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.taak.pro.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity14 extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main14);

        bottomNavigationView=findViewById(R.id.boom);
        bottomNavigationView.setSelectedItemId(R.id.download);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                try {
                    switch (item.getItemId()){
                        case R.id.menu:
                            startActivity(new Intent(getApplicationContext(),MainActivity4.class));
                            overridePendingTransition(0,0);
                            return true;


                        case R.id.home:
                            startActivity(new Intent(getApplicationContext(),MainActivity2.class));
                            overridePendingTransition(0,0);
                            return true;

                        case R.id.download:
                            return true;

                        case R.id.upcomes:
                            startActivity(new Intent(getApplicationContext(),MainActivity5.class));
                            overridePendingTransition(0,0);
                            return true;

                    }

                }catch (Exception | Error e){
                    e.printStackTrace();
                }

                return false;
            }
        });

    }



    @Override
    public void onBackPressed() {
        try {
            Intent intent= new Intent(getApplicationContext(),MainActivity2.class);
            startActivity(intent);
        }catch (Exception | Error e){
            e.printStackTrace();
        }

    }
}