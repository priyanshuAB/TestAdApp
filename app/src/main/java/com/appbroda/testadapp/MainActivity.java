package com.appbroda.testadapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.appbroda.finaltestlib.MyLogger;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    private Interstitial interstitial;
    private Rewarded rewarded;
    private final MyLogger logger = new MyLogger();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
                Log.d(TAG, "GMA Initialized");
            }
        });
        interstitial = new Interstitial(this);
        rewarded = new Rewarded(this);
        logger.logMessage("log4J - Package Working (:");
    }
    public void showInterstitialAd(View view) {
        openAdActivity();
        interstitial.showInterstitialAd(this);
    }
    public void showRewardedAd(View view) {
        openAdActivity();
        rewarded.showRewardedAd(this);
    }
    private void openAdActivity() {
        Intent intent = new Intent(this, AdActivity.class);
        startActivity(intent);
    }
}