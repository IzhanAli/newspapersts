package com.izhandroid.newsbox.ts.english;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.izhandroid.newsbox.ts.R;
import com.izhandroid.newsbox.ts.telugu.MoreTel;
import com.izhandroid.newsbox.ts.telugu.NamasteTel;

public class MoreEng extends AppCompatActivity {
    String ane = "http://arabnews.com/pdfissues/index.php";
    String fex = "http://epaper.financialexpress.com";
    String tie = "https://epaper.telegraphindia.com";
    String th = "https://epaper.thehindu.com";
    InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_eng);


        MobileAds.initialize(this,

                getResources().getString(R.string.pubid));

        mInterstitialAd = new InterstitialAd(this);
        //TODO adint2
        mInterstitialAd.setAdUnitId("ca-app-pub-6711729529292720/2940025048");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        AdView mAdView = findViewById(R.id.adViewxx);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    public void an(View v){
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                Intent a = new Intent(MoreEng.this, TOI.class);
                a.putExtra("nga", th);
                startActivity(a);
                super.onAdClosed();
            }


        });
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else if (mInterstitialAd.isLoading()) {
            Toast.makeText(this, "Try after 4-5 secs..", Toast.LENGTH_SHORT).show();

        } else {
            Intent a = new Intent(MoreEng.this, TOI.class);
            a.putExtra("nga", th);
            startActivity(a);
        }


    }
    public void fe(View v){
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                Intent b = new Intent(MoreEng.this, TOI.class);
                b.putExtra("ngb", fex);
                startActivity(b);
                super.onAdClosed();
            }


        });
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else if (mInterstitialAd.isLoading()) {
            Toast.makeText(this, "Try after 4-5 secs..", Toast.LENGTH_SHORT).show();

        } else {
            Intent b = new Intent(MoreEng.this, TOI.class);
            b.putExtra("ngb", fex);
            startActivity(b);
        }


    }

    public void tc(View v){
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                Intent c = new Intent(MoreEng.this, TOI.class);
                c.putExtra("ngc", tie);
                startActivity(c);
                super.onAdClosed();
            }


        });
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else if (mInterstitialAd.isLoading()) {
            Toast.makeText(this, "Try after 4-5 secs..", Toast.LENGTH_SHORT).show();

        } else {
            Intent c = new Intent(MoreEng.this, TOI.class);
            c.putExtra("ngc", tie);
            startActivity(c);
        }


    }

    public void td(View v) {
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                Intent d = new Intent(MoreEng.this, TOI.class);
                d.putExtra("ngd", ane);
                startActivity(d);
                super.onAdClosed();
            }


        });
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else if (mInterstitialAd.isLoading()) {
            Toast.makeText(this, "Try after 4-5 secs..", Toast.LENGTH_SHORT).show();

        } else {
            Intent d = new Intent(MoreEng.this, TOI.class);
            d.putExtra("ngd", ane);
            startActivity(d);
        }


    }

    @Override
    protected void onResume() {
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        super.onResume();
    }

    public void reqeng(View v){
        mInterstitialAd.show();
        Intent localIntent2 = new Intent("android.intent.action.SENDTO", Uri.fromParts("mailto", "idappshelp@gmail.com", null));
        localIntent2.putExtra("android.intent.extra.SUBJECT", "Requesting to add a Newspaper:ENGLISH");
        localIntent2.putExtra("android.intent.extra.TEXT", "Hi Team,\n \n Newspaper name:");
        startActivity(Intent.createChooser(localIntent2, "Send email..."));
    }

}
