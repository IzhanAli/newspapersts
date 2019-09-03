package com.izhandroid.newsbox.ts.english;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.izhandroid.newsbox.ts.R;
import com.izhandroid.newsbox.ts.telugu.NamasteTel;

public class MoreEng extends AppCompatActivity {
    String ane = "http://arabnews.com/pdfissues/index.php";
    String fex = "http://epaper.financialexpress.com";
    String tie = "https://epaper.telegraphindia.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_eng);


        MobileAds.initialize(this,

                "ca-app-pub-6711729529292720~6492881965");

        InterstitialAd mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(
                "ca-app-pub-6711729529292720/2940025048");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        if(mInterstitialAd.isLoaded()){
            mInterstitialAd.show();
        }
        AdView mAdView = findViewById(R.id.adViewxx);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    public void an(View v){
        Intent a = new Intent(this, TOI.class);
        a.putExtra("nga",ane);
        startActivity(a);
    }
    public void fe(View v){
        Intent b = new Intent(this, TOI.class);
        b.putExtra("ngb",fex);
        startActivity(b);
    }
    public void tc(View v){
        Intent c = new Intent(this, TOI.class);
        c.putExtra("ngc",tie);
        startActivity(c);
    }

    public void reqeng(View v){
        Intent localIntent2 = new Intent("android.intent.action.SENDTO", Uri.fromParts("mailto", "idappshelp@gmail.com", null));
        localIntent2.putExtra("android.intent.extra.SUBJECT", "Requesting to add a Newspaper:ENGLISH");
        localIntent2.putExtra("android.intent.extra.TEXT", "Hi Team,\n \n Newspaper name:");
        startActivity(Intent.createChooser(localIntent2, "Send email..."));
    }
}
