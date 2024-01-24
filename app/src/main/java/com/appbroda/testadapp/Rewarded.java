package com.appbroda.testadapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

public class Rewarded {
    private RewardedAd mRewardedAd;
    private final String TAG = "Rewarded ";

    public Rewarded(Context context) { loadRewardedAd(context); }

    public void showRewardedAd(Activity activity) {
        if(mRewardedAd != null) {
            mRewardedAd.show(activity, new OnUserEarnedRewardListener() {
                @Override
                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                    Log.i(TAG, "User is Rewarded!");
                }
            });
            loadRewardedAd(activity);
        } else {
            Log.i(TAG, "Rewarded Ad Not Loaded yet!");
        }
    }
    private void loadRewardedAd(Context context) {
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(context, context.getString(R.string.rewarded_adUnit_id), adRequest, new RewardedAdLoadCallback() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                mRewardedAd = null;
                Log.i(TAG, "Rewarded Ad Failed To Load!");
                Log.i(TAG, String.valueOf(loadAdError));
            }

            @Override
            public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                super.onAdLoaded(rewardedAd);
                mRewardedAd = rewardedAd;
                Log.i(TAG, "Rewarded Ad Loaded!");
            }
        });
    }
}
