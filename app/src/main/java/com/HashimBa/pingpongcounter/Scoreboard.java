package com.HashimBa.pingpongcounter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.gms.ads.AdRequest;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.tooltip.Tooltip;

import java.util.List;


public class Scoreboard extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {
    TextView titleForFirstPlayer, PcounterForFirstPlayer, conterFirst, titleForSeconedPlayer, PcounterForSeconedPlayer, conterSecond;
    int firstConter = 0, seconedConter = 0, winFromInt = 99;
    Button seting_btn, restBtn;
    String name1 = "Player1", name2 = "Player2";
    Tooltip tooltip, tooltip2;

    int orginale_winfrom;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //da y5ly alshashh kamlh bdon shry6 aly fog
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //*****************************************
        //dy 78 al onCreate
        setContentView(R.layout.scoreboard);
        //*********************************
        //dy t5ly alshash bal3r9' by defulte
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



        //**********************************************************
        titleForFirstPlayer = findViewById(R.id.titleForFirstPlayer);
        PcounterForFirstPlayer = findViewById(R.id.PcounterForFirstPlayer);
        conterFirst = findViewById(R.id.conterFirst);
        titleForSeconedPlayer = findViewById(R.id.titleForSeconedPlayer);
        PcounterForSeconedPlayer = findViewById(R.id.PcounterForSeconedPlayer);
        conterSecond = findViewById(R.id.conterSecond);
        seting_btn = findViewById(R.id.setingBtn);
        restBtn = findViewById(R.id.restBTN);
        //*******************************************
// dy t8ol LL btn ant ra7 yn9'3'6 3lyk
        PcounterForFirstPlayer.setOnClickListener(Scoreboard.this);
        seting_btn.setOnClickListener(this);
        restBtn.setOnClickListener(this);
        conterFirst.setOnClickListener(Scoreboard.this);
        PcounterForSeconedPlayer.setOnClickListener(Scoreboard.this);
        conterSecond.setOnClickListener(Scoreboard.this);
        titleForFirstPlayer.setOnLongClickListener(this);
        titleForSeconedPlayer.setOnLongClickListener(this);
//*--------------------------------------------------------tooltip*****************************************++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        tooltip = new Tooltip.Builder(restBtn).setText(getResources().getString(R.string.tips))
                .setTextColor(Color.BLACK)
                .setCancelable(true).setGravity(Gravity.END).show();
        tooltip2 = new Tooltip.Builder(seting_btn).setText(getResources().getString(R.string.tips))
                .setTextColor(Color.BLACK)
                .setGravity(Gravity.START).setCancelable(true).show();
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++tooltip++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

     //hda y7l mshklt anh yrstr al mo3lomat mn al9fr da al part 2 alaol t7t
        if (savedInstanceState != null) {
            winFromInt = savedInstanceState.getInt("win_form");
            name1 = savedInstanceState.getString("player1n");
            titleForFirstPlayer.setText(name1);
            name2 = savedInstanceState.getString("player2n");
            titleForSeconedPlayer.setText(name2);
            firstConter = savedInstanceState.getInt("firstcounter");
            conterFirst.setText(firstConter + "");
            seconedConter = savedInstanceState.getInt("seconedcounter");
            conterSecond.setText(seconedConter + "");
        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {


            case R.id.restBTN: {
                //dy t5ly al btn ao ay sh2 tany ysoy anmotion
                YoYo.with(Techniques.RubberBand).duration(700).repeat(0).playOn(restBtn);
                //*--------------------------------------------------------tooltip*****************************************++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                tooltip = new Tooltip.Builder(restBtn).setText(getResources().getString(R.string.tips))
                        .setTextColor(Color.BLACK)
                        .setCancelable(true).setGravity(Gravity.END).show();
                tooltip2 = new Tooltip.Builder(seting_btn).setText(getResources().getString(R.string.tips))
                        .setTextColor(Color.BLACK)
                        .setGravity(Gravity.START).setCancelable(true).show();
                //++++++++++++++++++++++++++++++++++++++++++++++++++++++++tooltip++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


                    firstConter = 0;
                    seconedConter = 0;
                    conterFirst.setText("0");
                    conterSecond.setText("0");
                   if (orginale_winfrom==0){

                       winFromInt =99;
                   }else    {
                       winFromInt = orginale_winfrom;

                   }


                break;

            }


            case R.id.setingBtn: {

               //hna mst5dm startActivityForResult 3shan y3rf anh byrj3 m3h m3lomat da part fy 3'arh part tany
                Intent myIntent = new Intent(Scoreboard.this, MainMenu.class);
                myIntent.putExtra(name1, "name1");
                myIntent.putExtra(name2, "name2");
                startActivityForResult(myIntent, 1);


                break;
            }

            case R.id.PcounterForFirstPlayer: {
                //firstConter = firstConter + 1;
                int value = ++firstConter;
                YoYo.with(Techniques.FadeInUp).duration(300).repeat(0).playOn(conterFirst);
                conterFirst.setText(value + "");

                if (firstConter == winFromInt - 1 && seconedConter == winFromInt - 1) {
                    ++winFromInt;


                } else if (firstConter == winFromInt || firstConter == 99) {
                    Intent myIntent = new Intent(Scoreboard.this, WinerPlace.class);

                    if (name1.equalsIgnoreCase("Player 1")) {
                        myIntent.putExtra("winner", "Player 1");
                    } else {
                        myIntent.putExtra("winner", name1);
                    }
                    startActivity(myIntent);
                    // firstConter = 0;//mn hna abda
                }

                break;
            }
            case R.id.conterFirst: {


                // firstConter = firstConter + 1;
                int value = ++firstConter;
                YoYo.with(Techniques.FadeInUp).duration(300).repeat(0).playOn(conterFirst);
                conterFirst.setText(value + "");
                if (firstConter == winFromInt - 1 && seconedConter == winFromInt - 1) {
                    ++winFromInt;



                } else if (firstConter == winFromInt || firstConter == 99) {
                    Intent myIntent = new Intent(Scoreboard.this, WinerPlace.class);
                    if (name1.equalsIgnoreCase("Player 1")) {
                        myIntent.putExtra("winner", "Player 1");
                    } else {
                        myIntent.putExtra("winner", name1);
                    }
                    startActivity(myIntent);
                    // firstConter = 0;
                }

                break;
            }
            case R.id.conterSecond: {
                //  seconedConter = seconedConter + 1;
                int value = ++seconedConter;
                YoYo.with(Techniques.FadeInUp).duration(300).repeat(0).playOn(conterSecond);
                conterSecond.setText(value + "");
                if (firstConter == winFromInt - 1 && seconedConter == winFromInt - 1) {
                    ++winFromInt;


                } else if (seconedConter == winFromInt || seconedConter == 99) {
                    Intent myIntent = new Intent(Scoreboard.this, WinerPlace.class);
                    if (name2.equalsIgnoreCase("Player 2")) {
                        myIntent.putExtra("winner", "Player 2");
                    } else {
                        myIntent.putExtra("winner", name2);
                    }
                    startActivity(myIntent);

                }

                break;
            }
            case R.id.PcounterForSeconedPlayer: {
                // seconedConter = seconedConter + 1;
                int value = ++seconedConter;
                YoYo.with(Techniques.FadeInUp).duration(300).repeat(0).playOn(conterSecond);
                conterSecond.setText(value + "");
                if (firstConter == winFromInt - 1 && seconedConter == winFromInt - 1) {
                    ++winFromInt;


                } else if (seconedConter == winFromInt || seconedConter == 99) {
                    Intent myIntent = new Intent(Scoreboard.this, WinerPlace.class);
                    if (name2.equalsIgnoreCase("Player 2")) {
                        myIntent.putExtra("winner", "Player 2");
                    } else {
                        myIntent.putExtra("winner", name2);
                    }
                    startActivity(myIntent);
                    //  seconedConter = 0;
                }


                break;
            }

        }
    }

    //lltn8y9 dy al method
    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {
            case R.id.titleForFirstPlayer: {
                YoYo.with(Techniques.FadeInDown).duration(300).repeat(0).playOn(conterFirst);

                if (firstConter == winFromInt - 2 && seconedConter == winFromInt - 2) {
                    winFromInt = (winFromInt - 1);
                    conterFirst.setText((firstConter = firstConter - 1) + "");


                } else {
                    conterFirst.setText((firstConter = firstConter - 1) + "");
                }

                break;


            }

            case R.id.titleForSeconedPlayer: {
                YoYo.with(Techniques.FadeInDown).duration(300).repeat(0).playOn(conterSecond);

                if (firstConter == winFromInt - 2 && seconedConter == winFromInt - 2) {
                    winFromInt = winFromInt - 1;

                    conterSecond.setText((seconedConter = seconedConter - 1) + "");


                } else {
                    conterSecond.setText((seconedConter = seconedConter - 1) + "");
                }

                break;
            }


        }
        return false;
    }
// dy almothoud hya almkan aly ytrsl fyh alm3lomat aly tjy mn al option
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                if (data.getStringExtra("firstplayername").equalsIgnoreCase("Player 1") || data.getStringExtra("seconedplayername").equalsIgnoreCase("Player 2")) {

                    name1 = "Player 1";
                    titleForFirstPlayer.setText(name1);

                    name2 = "Player 2";
                    titleForSeconedPlayer.setText(name2);

                } else if (data.getStringExtra("firstplayername").equalsIgnoreCase("") || data.getStringExtra("seconedplayername").equalsIgnoreCase("")) {

                    //********************************
                    //do nothing
                    //********************************

                } else {
                    name1 = data.getStringExtra("firstplayername");
                    titleForFirstPlayer.setText(name1);

                    name2 = data.getStringExtra("seconedplayername");
                    titleForSeconedPlayer.setText(name2);
                }


                //**************converting**************************
                String winfromstr = data.getStringExtra("winfromstr");

                if (!winfromstr.equalsIgnoreCase("")) {

                    winFromInt = Integer.parseInt(winfromstr);
                    orginale_winfrom = winFromInt;
                    if (winFromInt == 0) {
                        winFromInt = 99;
                        orginale_winfrom = winFromInt;
                    }
                }
                //**************************************************
                boolean isChanged = data.getBooleanExtra("is_changed", false);
                if (isChanged == true) {

                    conterFirst.setText("0");
                    firstConter = 0;
                    conterSecond.setText("0");
                    seconedConter = 0;
                }
            }

        }
    }
//dy part 1 mn almouthd aly t7tf9' bal m3lomat la ttraster
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("win_form", winFromInt);
        outState.putString("player1n", name1);
        outState.putString("player2n", name2);
        outState.putInt("firstcounter", firstConter);
        outState.putInt("seconedcounter", seconedConter);

    }

//permission
    public void checkPermission(View view){
        PermissionListener permissionListener=new PermissionListener() {
            @Override
            public void onPermissionGranted() {

            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {

            }
        };

        TedPermission.with(Scoreboard.this).setPermissionListener(permissionListener).setPermissions(Manifest.permission.INTERNET).check();

    }
}