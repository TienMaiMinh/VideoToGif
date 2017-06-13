package com.ads;

import android.content.Context;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;


public class MyAds {
    InterstitialAd mInterstitialAd;
    MyAdsInterface myAction;
    public void init(Context context, String idAds){
        mInterstitialAd = new InterstitialAd(context);
        mInterstitialAd.setAdUnitId(idAds);
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                myAction.beginAction();

            }
        });

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                requestNewInterstitial();
            }

        });
        requestNewInterstitial();
    }

    public void showAds(){
        if (mInterstitialAd.isLoaded()){
            mInterstitialAd.show();
        }else{
            myAction.beginAction();
        }
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mInterstitialAd.loadAd(adRequest);
    }

    public void setAction(MyAdsInterface action){
        myAction = action;
    }


}
