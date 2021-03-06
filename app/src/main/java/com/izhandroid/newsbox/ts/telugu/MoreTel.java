package com.izhandroid.newsbox.ts.telugu;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.izhandroid.newsbox.ts.CustomTabsHelper;
import com.izhandroid.newsbox.ts.R;

public class MoreTel extends AppCompatActivity {
    String ta="http://epaper.prabhanews.com";
    String tb="http://epaper.prajasakti.com";
    String tc="http://epaper.suryaa.com";
    String td="http://epaper.aadabhyderabad.in";
    String te="http://telugutimes.net/home/epapers";
    String tf="http://epaper.greatandhra.com";
    String tg="https://www.readwhere.com/newspaper/namasthe-hyderabad/Namasthe-Hyderabad/11826";
    String th = "https://epaper.v6velugu.com/";
    InterstitialAd mInterstitialAd;
    FirebaseDatabase database;
    DatabaseReference myRef;
    String endu;
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

        endu = "v";

        MobileAds.initialize(this, getResources().getString(R.string.pubid));
//TODO adint5
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-6711729529292720/5020050017");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("eenaduenabled");
// Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                endu = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
        AdView mAdView = findViewById(R.id.adViewfs);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
    public void ta(View v){
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else if (mInterstitialAd.isLoading()) {
            Toast.makeText(this, "Please install EENADU App from Play Store\nInconvenience regretted!", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Please install EENADU App from Play Store\nInconvenience regretted!", Toast.LENGTH_SHORT).show();


        }
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                if (endu.equals("no")) {
                    Toast.makeText(MoreTel.this, "Please install EENADU App from Play Store\nInconvenience regretted!", Toast.LENGTH_SHORT).show();
                } else if (endu.equals("yes")) {
                    openCustomTab(MoreTel.this, Uri.parse("https://epaper.eenadu.net"));
                } else {
                    Toast.makeText(MoreTel.this, "Please install EENADU App from Play Store\nInconvenience regretted!", Toast.LENGTH_SHORT).show();

                }
                super.onAdClosed();
            }


        });
    }
    public void tb(View v){
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else if (mInterstitialAd.isLoading()) {
            Toast.makeText(this, "Try after 4-5 secs..", Toast.LENGTH_SHORT).show();

        } else {
            Intent b = new Intent(MoreTel.this, NamasteTel.class);
            b.putExtra("keyb", tb);
            startActivity(b);
        }
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                Intent b = new Intent(MoreTel.this, NamasteTel.class);
                b.putExtra("keyb", tb);
                startActivity(b);
                super.onAdClosed();
            }


        });

    }
    public void tc(View v){
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else if (mInterstitialAd.isLoading()) {
            Toast.makeText(this, "Try after 4-5 secs..", Toast.LENGTH_SHORT).show();

        } else {
            Intent c = new Intent(MoreTel.this, NamasteTel.class);
            c.putExtra("keyc", tc);
            startActivity(c);
        }
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                Intent c = new Intent(MoreTel.this, NamasteTel.class);
                c.putExtra("keyc", tc);
                startActivity(c);
                super.onAdClosed();
            }


        });

    }
    public void td(View v){
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else if (mInterstitialAd.isLoading()) {
            Toast.makeText(this, "Try after 4-5 secs..", Toast.LENGTH_SHORT).show();

        } else {
            Intent d = new Intent(this, NamasteTel.class);
            d.putExtra("keyd", th);
            startActivity(d);
        }
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                Intent d = new Intent(MoreTel.this, NamasteTel.class);
                d.putExtra("keyd", th);
                startActivity(d);
                super.onAdClosed();
            }


        });

    }
    public void te(View v){
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else if (mInterstitialAd.isLoading()) {
            Toast.makeText(this, "Try after 4-5 secs..", Toast.LENGTH_SHORT).show();

        } else {
            Intent e = new Intent(this, NamasteTel.class);
            e.putExtra("keye", te);
            startActivity(e);
        }
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                Intent e = new Intent(MoreTel.this, NamasteTel.class);
                e.putExtra("keye", te);
                startActivity(e);
                super.onAdClosed();
            }


        });

    }
    public void tf(View v){
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else if (mInterstitialAd.isLoading()) {
            Toast.makeText(this, "Try after 4-5 secs..", Toast.LENGTH_SHORT).show();

        } else {
            Intent f = new Intent(this, NamasteTel.class);
            f.putExtra("keyf", tf);
            startActivity(f);
        }
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                Intent f = new Intent(MoreTel.this, NamasteTel.class);
                f.putExtra("keyf", tf);
                startActivity(f);
                super.onAdClosed();
            }


        });

    }
    public void tg(View v){
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else if (mInterstitialAd.isLoading()) {
            Toast.makeText(this, "Try after 4-5 secs..", Toast.LENGTH_SHORT).show();

        } else {
            Intent g = new Intent(this, NamasteTel.class);
            g.putExtra("keyg", tg);
            startActivity(g);
        }
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                Intent g = new Intent(MoreTel.this, NamasteTel.class);
                g.putExtra("keyg", tg);
                startActivity(g);
                super.onAdClosed();
            }


        });

    }

    public void th(View v) {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else if (mInterstitialAd.isLoading()) {
            Toast.makeText(this, "Try after 4-5 secs..", Toast.LENGTH_SHORT).show();

        } else {
            Intent h = new Intent(this, NamasteTel.class);
            h.putExtra("keyh", td);
            startActivity(h);
        }
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                Intent h = new Intent(MoreTel.this, NamasteTel.class);
                h.putExtra("keyh", td);
                startActivity(h);
                super.onAdClosed();
            }


        });

    }

    public void reqtel(View v){
        mInterstitialAd.show();
        Intent localIntent2 = new Intent("android.intent.action.SENDTO", Uri.fromParts("mailto", "idappshelp@gmail.com", null));
        localIntent2.putExtra("android.intent.extra.SUBJECT", "Requesting to add a Newspaper:TELUGU");
        localIntent2.putExtra("android.intent.extra.TEXT", "Hi Team,\n \n Newspaper name:");
        startActivity(Intent.createChooser(localIntent2, "Send email..."));
    }

    @Override
    protected void onResume() {
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        super.onResume();
    }

    private void openCustomTab(Activity activity,
                               Uri uri) {
        // Here is a method that returns the chrome package name
        String packageName = CustomTabsHelper.getPackageNameToUse(activity);

        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent mCustomTabsIntent = builder
                .setShowTitle(true)
                .build();
        builder.setToolbarColor(ContextCompat.getColor(activity, R.color.colorPrimary));

        if (packageName != null) {
            mCustomTabsIntent.intent.setPackage(packageName);
        }
        mCustomTabsIntent.launchUrl(activity, uri);
    }
}
