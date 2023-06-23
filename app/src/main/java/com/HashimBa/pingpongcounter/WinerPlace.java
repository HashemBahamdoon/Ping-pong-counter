package com.HashimBa.pingpongcounter;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class WinerPlace extends AppCompatActivity implements View.OnClickListener {
Button homeBTN;
    private AdView mAdView;
  TextView winerName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.winer_place);
      this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        //************************adds here**********************************
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        //*******************************************************************
    homeBTN=findViewById(R.id.btnBackHome);
    homeBTN.setOnClickListener(this);
   winerName=findViewById(R.id.winerName);
        Bundle extra = getIntent().getExtras();
        String winner =extra.getString("winner");

        winerName.setText(winner);


    }

    @Override
    public void onClick(View v) {
       if (v.getId() ==R.id.btnBackHome){
           Intent myIntent = new Intent(WinerPlace.this, Scoreboard.class);
           startActivity(myIntent);



       }
    }
}
