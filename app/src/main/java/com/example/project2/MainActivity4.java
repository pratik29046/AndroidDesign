package com.example.project2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.r0adkll.slidr.model.SlidrInterface;

public class MainActivity4 extends AppCompatActivity {
    TextView textView,logout;
    RelativeLayout relativeLayout;
    BottomNavigationView bottomNavigationView;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        textView=findViewById(R.id.sub);
        relativeLayout=findViewById(R.id.back);
        bottomNavigationView=findViewById(R.id.boom);
        bottomNavigationView.setSelectedItemId(R.id.menu);
        logout=findViewById(R.id.logout);




        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.menu:
                        return true;

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainActivity2.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.download:
                        startActivity(new Intent(getApplicationContext(),MainActivity14.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.upcomes:
                        startActivity(new Intent(getApplicationContext(),MainActivity5.class));
                        overridePendingTransition(0,0);
                        return true;

                }

                return false;
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });



        //call the method back
//        relativeLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                back();
//            }
//        });




    }


    void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        finish();
                        startActivity(new Intent(MainActivity4.this,MainActivity.class));
                    }
                });
    }

    //method of backpress
    public void back(){
        super.onBackPressed();
    }
}