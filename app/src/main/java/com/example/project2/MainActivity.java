package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project2.Network.WebService;
import com.example.project2.POJO.Category;
import com.example.project2.POJO.RawData_POJO;
import com.example.project2.POJO.TokenID;
import com.example.project2.POJO.WelcomeBanner;
import com.example.project2.POJO.WelcomeRoot;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.sql.Time;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Timer time;
    AdapterWelcome adapterWelcome;
    RecyclerView recyclerView;

    SignInButton signInButton;
    GoogleSignInClient mGoogleSignInClient;
    static final int RC_SIGN_IN =1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.information_background));
        setContentView(R.layout.activity_main);
        signInButton = findViewById(R.id.login);


//         time=new Timer();
//        time.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                Intent send = new Intent(MainActivity.this, MainActivity2.class);
//                startActivity(send);
//            }
//        },8000);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("7654007831-971a1qee7lcgrt19ph2cbsd413qjdkik.apps.googleusercontent.com")
                .requestEmail()
                .requestIdToken("7654007831-971a1qee7lcgrt19ph2cbsd413qjdkik.apps.googleusercontent.com")
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            navigateToSecodeActivity();

        }
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });


        WebService.getClient().get_welcomeData().enqueue(new Callback<WelcomeRoot>() {
            @Override
            public void onResponse(Call<WelcomeRoot> call, Response<WelcomeRoot> response) {
                adp(response.body().welcome_banners);
            }

            @Override
            public void onFailure(Call<WelcomeRoot> call, Throwable t) {

            }
        });

    }


    public void adp(List<WelcomeBanner> welcomeBanners){
        recyclerView=findViewById(R.id.Welcome);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView
        recyclerView.setItemAnimator(new DefaultItemAnimator() );
        adapterWelcome=new AdapterWelcome(this,welcomeBanners);
        recyclerView.setAdapter(adapterWelcome);
    }



    void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                navigateToSecodeActivity();

            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(),"wrong",Toast.LENGTH_SHORT).show();
            }
        }
    }

    void navigateToSecodeActivity() {
        finish();
        Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        ((Activity)getApplicationContext()).finish();
    }
}