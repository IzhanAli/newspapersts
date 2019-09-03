package com.izhandroid.newsbox.ts.telugu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.izhandroid.newsbox.ts.R;

public class MoreTel extends AppCompatActivity {
    String ta="http://epaper.prabhanews.com";
    String tb="http://epaper.prajasakti.com";
    String tc="http://epaper.suryaa.com";
    String td="http://epaper.aadabhyderabad.in";
    String te="http://telugutimes.net/home/epapers";
    String tf="http://epaper.greatandhra.com";
    String tg="https://www.readwhere.com/newspaper/namasthe-hyderabad/Namasthe-Hyderabad/11826";

    /* Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;*/
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_tel);
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        /*btn1 =(Button) findViewById(R.id.but_tela);
        btn2 =(Button) findViewById(R.id.but_telb);
        btn3 =(Button) findViewById(R.id.but_telc);*/


        MobileAds.initialize(this,

                        "ca-app-pub-6711729529292720~6492881965");

      InterstitialAd  mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(
                "ca-app-pub-6711729529292720/5020050017");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        if(mInterstitialAd.isLoaded()){
            mInterstitialAd.show();
        }

        AdView mAdView = findViewById(R.id.adViewfs);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
    public void ta(View v){
        Intent a = new Intent(this, NamasteTel.class);
        a.putExtra("keya",ta);
        startActivity(a);
    }
    public void tb(View v){
        Intent b = new Intent(this, NamasteTel.class);
        b.putExtra("keyb",tb);
        startActivity(b);
    }
    public void tc(View v){
        Intent c = new Intent(this, NamasteTel.class);
        c.putExtra("keyc",tc);
        startActivity(c);
    }
    public void td(View v){
        Intent d = new Intent(this, NamasteTel.class);
        d.putExtra("keyd",td);
        startActivity(d);
    }
    public void te(View v){
        Intent e = new Intent(this, NamasteTel.class);
        e.putExtra("keye",te);
        startActivity(e);
    }
    public void tf(View v){
        Intent f = new Intent(this, NamasteTel.class);
        f.putExtra("keyf",tf);
        startActivity(f);
    }
    public void tg(View v){
        Intent g = new Intent(this, NamasteTel.class);
        g.putExtra("keyg",tg);
        startActivity(g);
    }

    public void reqtel(View v){
        Intent localIntent2 = new Intent("android.intent.action.SENDTO", Uri.fromParts("mailto", "idappshelp@gmail.com", null));
        localIntent2.putExtra("android.intent.extra.SUBJECT", "Requesting to add a Newspaper:TELUGU");
        localIntent2.putExtra("android.intent.extra.TEXT", "Hi Team,\n \n Newspaper name:");
        startActivity(Intent.createChooser(localIntent2, "Send email..."));
    }


}
