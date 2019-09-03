package com.izhandroid.newsbox.ts;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.analytics.FirebaseAnalytics;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO version
        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle paramc = new Bundle();
        paramc.putString("msg", "About loaded");
        paramc.putString("title", "Main2 was called");
        firebaseAnalytics.logEvent("About", paramc);
        Element versionElement = new Element();
        versionElement.setTitle("Version 199.6.1");

        Element disclaimer = new Element();
        disclaimer.setTitle("Disclaimer");
        disclaimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(Main2Activity.this);
                builder.setCancelable(true);
                builder.setTitle("Disclaimer!");
                builder.setMessage("Rights belong to the respective owners. No copyright infringement intended. \n The developer of the app is not responsible for the content published here, nor we hold or own any content. \n Any dispute can be resolved by contacting us!");
                builder.setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.create();
                builder.show();


            }
        });

        Element more =new Element();
        more.setTitle("More Apps");
        more.setIconDrawable(R.drawable.ic_play_for_work_black_24dp);

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/dev?id=6073004400609392754"));
                startActivity(n);
            }
        });


        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setImage(R.mipmap.ic_launcher)
                .setDescription("An app handcrafted for news lovers :)")

                .addItem(versionElement)
                .addPlayStore("com.izhandroid.newsbox.ts", "Rate us!")

                .addWebsite("https://sites.google.com/view/newsboxtsprivacypolicy", "Privacy Policy")
                .addItem(more)
                .addItem(disclaimer)
                .addGroup("Connect with us!")

                .addEmail("idappshelp@gmail.com", "Suggestions? Queries?")

                .addFacebook("izhandroid", "Like us on Facebook")
                .addInstagram("izhandroid", "Follow us on Instagram")
                .addWebsite("http://izhandroidapps.gq/", "Website")
                .create();

        setContentView(aboutPage);
    }
}
