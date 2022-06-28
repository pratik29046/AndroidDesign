package com.example.project2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.project2.Network.WebService;
import com.example.project2.POJO.Category;
import com.example.project2.POJO.MembershipPlan;
import com.example.project2.POJO.MembershipPlanRoot;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.r0adkll.slidr.model.SlidrInterface;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity4 extends AppCompatActivity {
    TextView textView,logout,names,emailid;
    RelativeLayout relativeLayout;
    BottomNavigationView bottomNavigationView;
    GoogleSignInClient mGoogleSignInClient;
    AdapterPlans adapterPlans;
    RecyclerView recyclerView;
    CircleImageView circleImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        textView=findViewById(R.id.sub);
        relativeLayout=findViewById(R.id.back);
        bottomNavigationView=findViewById(R.id.boom);
        bottomNavigationView.setSelectedItemId(R.id.menu);
        logout=findViewById(R.id.logout);
        names=findViewById(R.id.names);
        emailid=findViewById(R.id.emailId);
        circleImageView=findViewById(R.id.profilePic);


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


        GoogleSignInAccount account=GoogleSignIn.getLastSignedInAccount(this);
        if(account!=null){
            String name=account.getDisplayName();
            String email=account.getEmail();

           names.setText(name);
           emailid.setText(email);
            Picasso.get().load(account.getPhotoUrl()).placeholder(R.drawable.ic_baseline_person_24).into(circleImageView);

        }


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


        WebService.getClient().get_planData().enqueue(new Callback<MembershipPlanRoot>() {
            @Override
            public void onResponse(Call<MembershipPlanRoot> call, Response<MembershipPlanRoot> response) {

                Log.d("TAG", "onResponse: "+response.body());
                adp(response.body().membership_plans);
            }

            @Override
            public void onFailure(Call<MembershipPlanRoot> call, Throwable t) {

            }
        });



    }

    public void adp(List<MembershipPlan> membershipPlans){
        recyclerView=findViewById(R.id.plans);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity4.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView
        recyclerView.setItemAnimator(new DefaultItemAnimator() );
        adapterPlans=new AdapterPlans(this,membershipPlans);
        recyclerView.setAdapter(adapterPlans);
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