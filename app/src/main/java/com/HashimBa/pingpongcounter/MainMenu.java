package com.HashimBa.pingpongcounter;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainMenu extends AppCompatActivity implements View.OnClickListener {
    EditText firstName, seconedName, winFrom;
    TextView resoultOfTossUp;
    Button btnTossUp, btnLetGo;
    String valueOfFirstName, valueOfseconedName, winfromSTR;
    boolean is_changed;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main_menu);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);



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

        //+++++++++++++finedViewById+++++++++++++++++++++++++++++
        firstName = findViewById(R.id.firstName);
        seconedName = findViewById(R.id.seconedName);
        resoultOfTossUp = findViewById(R.id.resoultOfTossUp);
        btnLetGo = findViewById(R.id.btnLetGo);
        btnTossUp = findViewById(R.id.btnTossUp);
        winFrom = findViewById(R.id.winFrom);

        //+++++++++++++++++++++++++++++++++++++++++++++++++++


        btnTossUp.setOnClickListener(this);
        btnLetGo.setOnClickListener(this);
        Bundle bundle = getIntent().getExtras();
        valueOfFirstName = bundle.getString("name1");
        valueOfseconedName = bundle.getString("name2");

    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {


            case R.id.btnTossUp: {
                YoYo.with(Techniques.Shake).duration(1500).repeat(0).playOn(btnTossUp);
                valueOfFirstName = firstName.getText().toString();
                valueOfseconedName = seconedName.getText().toString();
                if (valueOfFirstName.equalsIgnoreCase("Player 1") || valueOfseconedName.equalsIgnoreCase("Player 2") || valueOfFirstName.equalsIgnoreCase("") || valueOfseconedName.equalsIgnoreCase("")) {
                    Toast.makeText(this, "Please Enter Names To Do Tossing Up", Toast.LENGTH_LONG).show();
                } else {

                    int randNum = (int) (Math.random() * 101);
                    if (randNum % 2 == 0) {

                        resoultOfTossUp.setText(getResources().getString(R.string.who_serve_first) +" "+ valueOfseconedName+"");
                        resoultOfTossUp.setBackgroundColor(Color.parseColor("#00CED1"));

                    } else {
                        resoultOfTossUp.setText(getResources().getString(R.string.who_serve_first)+" " + valueOfFirstName);
                        resoultOfTossUp.setBackgroundColor(Color.parseColor("#FF4500"));
                    }

                }

                break;
            }

            case R.id.btnLetGo: {

                valueOfFirstName = firstName.getText().toString();
                valueOfseconedName = seconedName.getText().toString();
                winfromSTR = winFrom.getText().toString();

                if (valueOfFirstName.equalsIgnoreCase("Player 1") || valueOfseconedName.equalsIgnoreCase("Player 2")) {
//                    Toast.makeText(this, "Please Enter Names To Continue", Toast.LENGTH_LONG).show();
                    if (winfromSTR.isEmpty()) {
                        is_changed = false;
                    } else {
                        is_changed = true;
                    }
                    Intent myIntent = new Intent(MainMenu.this, Scoreboard.class);

                    myIntent.putExtra("firstplayername", "Player 1");
                    myIntent.putExtra("seconedplayername", "Player 2");
                    myIntent.putExtra("winfromstr", winfromSTR);
                    myIntent.putExtra("is_changed", is_changed);
                    setResult(RESULT_OK, myIntent);
                    finish();

                } else if (valueOfFirstName.equalsIgnoreCase("")&& !valueOfFirstName.isEmpty() || valueOfseconedName.equalsIgnoreCase("")&&! valueOfseconedName.isEmpty()) {



                    if (winfromSTR.isEmpty()) {
                        is_changed = false;
                    } else {
                        is_changed = true;
                    }
                    Intent myIntent = new Intent(MainMenu.this, Scoreboard.class);

                    myIntent.putExtra("firstplayername", valueOfFirstName);
                    myIntent.putExtra("seconedplayername", valueOfseconedName);
                    myIntent.putExtra("winfromstr", winfromSTR);
                    myIntent.putExtra("is_changed", is_changed);
                    setResult(RESULT_OK, myIntent);
                    finish();


                } else {


                    if (winfromSTR.isEmpty()) {
                        is_changed = false;
                    } else {
                        is_changed = true;
                    }
                    Intent myIntent = new Intent(MainMenu.this, Scoreboard.class);

                    myIntent.putExtra("firstplayername", valueOfFirstName);
                    myIntent.putExtra("seconedplayername", valueOfseconedName);
                    myIntent.putExtra("winfromstr", winfromSTR);
                    myIntent.putExtra("is_changed", is_changed);
                    setResult(RESULT_OK, myIntent);
                    finish();

                }
                break;


            }
        }
    }
}
