package com.izhandroid.newsbox.ts.telugu;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;


import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.webkit.WebSettingsCompat;
import androidx.webkit.WebViewFeature;

import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.izhandroid.newsbox.ts.R;
import com.izhandroid.newsbox.ts.ShareUtils;
import com.izhandroid.newsbox.ts.english.Constanted;
import com.izhandroid.newsbox.ts.english.TOI;

import static com.izhandroid.newsbox.ts.R.id.appbars;
import static com.izhandroid.newsbox.ts.R.id.webviewtwo;

/**
 * Created by Izhan Ali on 8/7/2018.
 */

public class AndhraBhumi extends AppCompatActivity {


    private String urla;
    private WebView wv;
    private FloatingActionButton floatingActionButton;
    private AppBarLayout appBarLayout;
    private RelativeLayout relativeLayout;
    private MaterialCardView cardView;
    FirebaseAnalytics firebaseAnalytics;
    private LinearLayout rootContent;

    private ProgressBar progressBarD;
    private Snackbar snacki;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_two);
        Toolbar mTopToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mTopToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (!isConnected(AndhraBhumi.this))
            buildDialog(AndhraBhumi.this).show();
        else {
            Toast.makeText(getApplicationContext(), "Welcomr", Toast.LENGTH_SHORT);

        }
        firebaseAnalytics = FirebaseAnalytics.getInstance(this);

        rootContent = findViewById(R.id.weblayouttwo);
        appBarLayout = findViewById(R.id.appbar);
        relativeLayout = findViewById(R.id.wtrmrkwebtwo);
        cardView = findViewById(R.id.web_two_txtbelow);
        this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        floatingActionButton = findViewById(R.id.fabtwo);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareUtils.chkntake(AndhraBhumi.this, appBarLayout, rootContent, relativeLayout, cardView, floatingActionButton, "newsarticle-ab", firebaseAnalytics);
            }
        });

        progressBarD = findViewById(R.id.progr);

        progressBarD.setMax(100);

        LoadWeb();


    }

    private boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if ((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting()))
                return true;
            else return false;
        } else
            return false;
    }


    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setCancelable(true);
        builder.setTitle("No Internet Connection");
        builder.setMessage("Please turn on Wifi/Mobile Data and retry");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();

            }
        });
        builder.setNegativeButton("Retry", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (!isConnected(AndhraBhumi.this))
                    buildDialog(AndhraBhumi.this).show();
                else {
                    wv.reload();

                }
            }
        });
        return builder;
    }

    String lol = "Mozilla/5.0 (Linux; Android 5.1.1; Nexus 5 Build/LMY48B; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/43.0.2357.65 Mobile Safari/537.36";
    String kit = "Mozilla/5.0 (Linux; Android 4.4; Nexus 5 Build/_BuildID_) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/30.0.0.0 Mobile Safari/537.36";

    public void LoadWeb() {

        wv = (WebView) findViewById(webviewtwo);
        final WebSettings settings = wv.getSettings();
        settings.setJavaScriptEnabled(true);
        wv.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);

        wv.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        wv.getSettings().setAppCacheEnabled(true);
        wv.getSettings().getJavaScriptCanOpenWindowsAutomatically();
        wv.setInitialScale(1);

        wv.getSettings().setUseWideViewPort(true);
        wv.getSettings().setLoadWithOverviewMode(true);
        wv.getSettings().getDisplayZoomControls();
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(true);
        wv.getSettings().setSupportZoom(true);
        wv.getSettings().setLoadsImagesAutomatically(true);
        wv.getSettings().supportMultipleWindows();
        wv.getSettings().getAllowUniversalAccessFromFileURLs();
        //wv.getSettings().getLoadWithOverviewMode();
        settings.getMediaPlaybackRequiresUserGesture();

        if (Build.VERSION.SDK_INT >= 21) {
            wv.getSettings().setUserAgentString(lol);
        } else {
            wv.getSettings().setUserAgentString(kit);
        }
        if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK)) {
            WebSettingsCompat.setForceDark(settings, WebSettingsCompat.FORCE_DARK_ON);
        }
        if (Build.VERSION.SDK_INT <= 18) {
            wv.getSettings().setBuiltInZoomControls(true);
            wv.getSettings().setDisplayZoomControls(true);
        } else {
            wv.getSettings().setBuiltInZoomControls(true);
            wv.getSettings().setDisplayZoomControls(true);
            wv.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }
        settings.setAllowFileAccess(true);
        settings.setAllowContentAccess(true);


        settings.setDomStorageEnabled(true);
        //settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        // settings.setUseWideViewPort(true);

        wv.setWebChromeClient(new WebChromeClient() {

            public void onProgressChanged(WebView view, int newProgress) {
                // Update the progress bar with page loading progress
                progressBarD.setProgress(newProgress);
                if (newProgress == 100) {
                    // Hide the progressbar
                    progressBarD.setVisibility(View.GONE);
                }
            }
        });

        //refreshLayout(this);
        wv.setWebViewClient(new WebViewClient() {

            public void onProgressChanged(WebView view, int newProgress) {
                // Update the progress bar with page loading progress
                progressBarD.setProgress(newProgress);
                final Snackbar snackbar = Snackbar.make(wv, "TIP: DOUBLE TAP OR PINCH TO ZOOM", Snackbar.LENGTH_INDEFINITE);
                snackbar.setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbar.dismiss();
                    }
                });
                if (newProgress == 90) {
                    // Hide the progressbar
                    snackbar.show();
                }
            }


            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBarD.setVisibility(View.GONE);

                setTitle(view.getTitle());
                SharedPreferences settings = getSharedPreferences(Constanted.PREF_NAMEAB, 0);

                if (settings.getBoolean("my_first_time", true)) {
                    //the app is being launched for first time, do something
                    Toast t = Toast.makeText(AndhraBhumi.this, "Tap on the article to read", Toast.LENGTH_LONG);
                    t.setGravity(Gravity.CENTER, 0, 0);
                    t.show();
                    // first time task


                    // record the fact that the app has been started at least once
                    settings.edit().putBoolean("my_first_time", false).commit();
                }
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                progressBarD.setVisibility(View.VISIBLE);
                setTitle("Loading...Please wait");
                super.onPageStarted(view, url, favicon);

            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

                wv.loadUrl("file:///android_asset/error.html");
                Alert(view);

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });


        Intent intent = this.getIntent();

        if (intent != null) {

            Bundle data = getIntent().getExtras();


            if (data != null && data.containsKey("dc")) {
                urla = data.getString("dc");
                wv.loadUrl(urla);
                Bundle params = new Bundle();
                params.putString("msg", "Deccn Chncl loaded from ABact");
                params.putString("title", "dc was called");
                firebaseAnalytics.logEvent("DcEn", params);
            }
            if (data != null && data.containsKey("abts")) {
                urla = data.getString("abts");
                wv.loadUrl(urla);
                Bundle params = new Bundle();
                params.putString("msg", "AndhraBhumi TS loaded from ABact");
                params.putString("title", "abts was called");
                firebaseAnalytics.logEvent("ABTS", params);
            }
            if (data != null && data.containsKey("abap")) {
                urla = data.getString("abap");
                wv.loadUrl(urla);
                Bundle params = new Bundle();
                params.putString("msg", "AndhraBhumi AP loaded from ABact");
                params.putString("title", "abap was called");
                firebaseAnalytics.logEvent("ABAP", params);
            }
            if (data != null && data.containsKey("toi")) {
                urla = data.getString("toi");
                wv.loadUrl(urla);
                Bundle params = new Bundle();
                params.putString("msg", "TOI from ABAct");
                params.putString("title", "toi was called");
                firebaseAnalytics.logEvent("TOI", params);
            }

        }

        //if (urla=)
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_web, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.reloadwv:
                LoadWeb();
                return true;
            case R.id.back:
                wv.goBack();
                return true;
            case R.id.cachecl:
                wv.clearCache(true);
                wv.reload();
                return true;
            case R.id.screenon:
                     /*isCheckeded = item.isChecked();
                     item.setChecked(isCheckeded);


                    this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);*/
                if (item.isChecked()) {
                    item.setChecked(false);
                    this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


                } else {
                    this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

                    item.setChecked(true);
                }
                return true;
            case R.id.darkmode:

                if (item.isChecked()) {

                    if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK)) {
                        item.setChecked(false);
                        WebSettingsCompat.setForceDark(wv.getSettings(), WebSettingsCompat.FORCE_DARK_OFF);
                    } else {
                        item.setChecked(false);
                        item.setEnabled(false);
                        Toast.makeText(this, "Dark Pages are not supported to your device", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK)) {
                        WebSettingsCompat.setForceDark(wv.getSettings(), WebSettingsCompat.FORCE_DARK_ON);
                        item.setChecked(true);
                    } else {
                        item.setChecked(false);
                        item.setEnabled(false);
                        Toast.makeText(this, "Dark Pages are not supported to your device", Toast.LENGTH_SHORT).show();
                    }
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void Alert(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(AndhraBhumi.this);
        builder.setCancelable(true);
        builder.setTitle("Sorry, an error occured");
        builder.setMessage("Please retry or check the internet connection");
        builder.setIcon(android.R.drawable.ic_dialog_info);


        builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }
        });
        builder.setNegativeButton("Retry", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                LoadWeb();
            }
        });


    }

    @Override
    protected void onPause() {
        wv.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        wv.onResume();
        super.onResume();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (wv.canGoBack()) {

                        wv.goBack();
                        snacki = Snackbar.make(wv, "Press Again", Snackbar.LENGTH_SHORT);
                        snacki.show();

                        View sbarview = snacki.getView();
                        sbarview.setBackgroundColor(getResources().getColor(R.color.colorAccentDark));
                    } else {
                        finish();
                        wv.clearCache(true);


                    }

                    return true;


            }
        }
        return super.onKeyDown(keyCode, event);
    }
}