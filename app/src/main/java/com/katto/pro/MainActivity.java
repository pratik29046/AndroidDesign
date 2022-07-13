package com.katto.pro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.katto.pro.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    Timer time;
    AdapterWelcome adapterWelcome;
    RecyclerView recyclerView;

    AppCompatButton signInButton;
    GoogleSignInClient mGoogleSignInClient;
    static final int RC_SIGN_IN =1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.information_background));
        setContentView(R.layout.activity_main);
        signInButton = findViewById(R.id.login);

//        time=new Timer();
//        time.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                Intent send = new Intent(MainActivity.this, MainActivity2.class);
//                startActivity(send);
//            }
//        },2000);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("7654007831-971a1qee7lcgrt19ph2cbsd413qjdkik.apps.googleusercontent.com")
                .requestEmail()
                .requestServerAuthCode("7654007831-971a1qee7lcgrt19ph2cbsd413qjdkik.apps.googleusercontent.com")
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


//        WebService.getClient().get_welcomeData().enqueue(new Callback<WelcomeRoot>() {
//            @Override
//            public void onResponse(Call<WelcomeRoot> call, Response<WelcomeRoot> response) {
//                adp(response.body().welcome_banners);
//            }
//
//            @Override
//            public void onFailure(Call<WelcomeRoot> call, Throwable t) {
//
//            }
//        });

    }


//    public void adp(List<WelcomeBanner> welcomeBanners){
//        recyclerView=findViewById(R.id.Welcome);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false);
//        recyclerView.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView
//        recyclerView.setItemAnimator(new DefaultItemAnimator() );
//        adapterWelcome=new AdapterWelcome(this,welcomeBanners);
//        recyclerView.setAdapter(adapterWelcome);
//    }

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
                Toast.makeText(getApplicationContext(),"Login unsuccessful check your Internet Connection",Toast.LENGTH_SHORT).show();
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
        finishAffinity();
    }
}