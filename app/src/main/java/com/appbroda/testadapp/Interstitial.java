// InterstitialAdHelper.java

package com.appbroda.testadapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class Interstitial {
    private InterstitialAd mInterstitialAd;
    private final String TAG = "InterstitialAdHelper";

    public Interstitial(Context context) {
        loadInterstitialAd(context);
    }

    public void showInterstitialAd(Activity activity) {
        if (mInterstitialAd != null) {
            mInterstitialAd.show(activity);
        } else {
            Log.i(TAG, "Ad Failed to Load!");
        }
    }
    private void loadInterstitialAd(Context context) {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(context, context.getString(R.string.interstitial_adUnit_id), adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                Log.i(TAG, "Interstitial Ad failed to load!");
                mInterstitialAd = null;
            }

            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                super.onAdLoaded(interstitialAd);
                Log.i(TAG, "Loaded the Interstitial Ad!");
                mInterstitialAd = interstitialAd;
                mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdClicked() {
                        super.onAdClicked();
                        Log.i(TAG, "Interstitial Ad Clicked!");
                    }

                    @Override
                    public void onAdDismissedFullScreenContent() {
                        super.onAdDismissedFullScreenContent();
                        Log.i(TAG, "Interstitial Ad Dismissed!");
                        mInterstitialAd = null;
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                        super.onAdFailedToShowFullScreenContent(adError);
                        Log.i(TAG, "Interstitial Ad failed to show fullscreen content!");
                        mInterstitialAd = null;
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        super.onAdShowedFullScreenContent();
                        Log.i(TAG, "Interstitial Ad showed fullscreen content!");
                        mInterstitialAd = null;
                    }
                });
            }
        });
    }
}