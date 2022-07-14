package com.katto.pro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.katto.pro.POJO.Banner;
import com.katto.pro.POJO.Root;
import com.katto.pro.POJO.TokenID;
import com.denzcoskun.imageslider.models.SlideModel;
import com.katto.pro.Network.WebService;
import com.katto.pro.POJO.Category;
import com.katto.pro.POJO.RawData_POJO;
import com.katto.pro.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import com.CustomAdapter;


public class MainActivity2 extends AppCompatActivity {
    RecyclerView recyclerView, recyclerView1;
    LinearLayoutManager layoutManager;
    Adapter adapter;
    AdapterBanners adapterBanners;
    RelativeLayout popup;
    LinearLayout linearLayoutplay;
    List<SlideModel> slideModels = new ArrayList<SlideModel>();
    BottomNavigationView bottomNavigationView;
    ImageView search_btn;
    private String url = "https://katto.in";
    GoogleSignInClient mGoogleSignInClient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        recyclerView = findViewById(R.id.review);
        recyclerView1 = findViewById(R.id.banner);
        popup = findViewById(R.id.continues);
        linearLayoutplay = findViewById(R.id.plays);
//        ImageSlider imageSlider=findViewById(R.id.post);
        search_btn = findViewById(R.id.search_btn);


        try {
            WebService.getClient().get_HomeData().enqueue(new Callback<Root>() {
                @Override
                public void onResponse(Call<Root> call, Response<Root> response) {
                    List<Category> categories = new ArrayList<>();
//                for (int i = 0 ; i < response.body().banners.size() ; i ++) {
//                    slideModels.add(new SlideModel(url+response.body().banners.get(i).poster, ScaleTypes.FIT));
////                    response.body().getBanners().get(i).get();
//
//                }

                    adp(response.body().categories);
                    adp2(response.body().banners);
                    Log.d("TAG", "onResponse: " + response.body().toString());
//                imageSlider.setImageList(slideModels);

                }

                @Override
                public void onFailure(Call<Root> call, Throwable t) {

                }
            });


        }catch (Exception | Error e){
            e.printStackTrace();
        }

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

            GoogleSignInAccount account=GoogleSignIn.getLastSignedInAccount(this);
            String id=account.getIdToken();
            Log.d("TAG", "onCreate: "+id);
            WebService.getClient().post_idData(new RawData_POJO( id, "", ""
            )).enqueue(new Callback<TokenID>() {
                @Override
                public void onResponse(Call<TokenID> call, Response<TokenID> response) {

                }
                @Override
                public void onFailure(Call<TokenID> call, Throwable t) {

                }
            });

        bottomNavigationView = findViewById(R.id.boom);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.menu:
                        startActivity(new Intent(getApplicationContext(), MainActivity4.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.home:
                        return true;

                    case R.id.download:
                        startActivity(new Intent(getApplicationContext(), MainActivity14.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.upcomes:
                        startActivity(new Intent(getApplicationContext(), MainActivity5.class));
                        overridePendingTransition(0, 0);
                        return true;

                }

                return false;
            }
        });


        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),Searchbar.class);
                startActivity(intent);
            }
        });



//        linearLayoutplay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(getApplicationContext(),MainPlayerActivity.class);
//                startActivity(intent);
//            }
//        });  // go to play video

    }

    public void adp(List<Category> categories) {
        recyclerView = findViewById(R.id.review);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity2.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new Adapter(this, categories);
        recyclerView.setAdapter(adapter);
    }

    public void adp2(List<Banner> banners) {
        recyclerView1 = findViewById(R.id.banner);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity2.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView1.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView
        adapterBanners = new AdapterBanners(banners, this);
        recyclerView1.setAdapter(adapterBanners);

        LinearSnapHelper linearSnapHelper = new LinearSnapHelper();
        linearSnapHelper.attachToRecyclerView(recyclerView1);

//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                if (linearLayoutManager.findFirstCompletelyVisibleItemPosition() < (adapterBanners.getItemCount() - 1)) {
//                    linearLayoutManager.smoothScrollToPosition(recyclerView1, new RecyclerView.State(), linearLayoutManager.findFirstCompletelyVisibleItemPosition() + 1);
//                } else if (linearLayoutManager.findFirstCompletelyVisibleItemPosition() < (adapterBanners.getItemCount() - 1)) {
//                    linearLayoutManager.smoothScrollToPosition(recyclerView1, new RecyclerView.State(), 0);
//                }
//            }
//        }, 0, 200);


    }


    @Override
    public void onBackPressed() {

        try {

            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity2.this, R.style.BottomSheetDialogTheme);

            View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(
                    R.layout.bottomsheet, (RelativeLayout) findViewById(R.id.hello)
            );
            bottomSheetView.findViewById(R.id.b2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bottomSheetDialog.dismiss();
                }
            });
            bottomSheetView.findViewById(R.id.b1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finishAffinity();
                }
            });

            bottomSheetView.findViewById(R.id.t1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bottomSheetDialog.dismiss();
                }
            });


            bottomSheetDialog.setContentView(bottomSheetView);
            bottomSheetDialog.show();


        }catch (Exception | Error e){
            e.printStackTrace();
        }

    }

}