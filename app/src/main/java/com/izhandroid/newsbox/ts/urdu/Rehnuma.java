package com.izhandroid.newsbox.ts.urdu;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
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

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.izhandroid.newsbox.ts.R;
import com.izhandroid.newsbox.ts.ShareUtils;
import com.izhandroid.newsbox.ts.english.TOI;
import com.izhandroid.newsbox.ts.telugu.AndhraBhumi;
import com.izhandroid.newsbox.ts.telugu.Varta;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import static com.izhandroid.newsbox.ts.R.id.webviewtwo;

/**
 * Created by Izhan Ali on 8/16/2018.
 */

public class Rehnuma extends AppCompatActivity {
    String urla;
    public WebView wv;

    private ProgressBar progressBarb;
    Boolean finishs = true;
    private FloatingActionButton floatingActionButton;
    private AppBarLayout appBarLayout;
    private RelativeLayout relativeLayout;
    private MaterialCardView cardView;
    FirebaseAnalytics firebaseAnalytics;
    private LinearLayout rootContent;
    Snackbar snack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_two);
        Toolbar mTopToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mTopToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (!isConnected(Rehnuma.this))
            buildDialog(Rehnuma.this).show();
        else {
            Toast.makeText(getApplicationContext(), "Welcomr", Toast.LENGTH_SHORT);

        }

        firebaseAnalytics = FirebaseAnalytics.getInstance(this);

        rootContent = findViewById(R.id.weblayouttwo);
        appBarLayout = findViewById(R.id.appbar);
        relativeLayout = findViewById(R.id.wtrmrkwebtwo);
        cardView = findViewById(R.id.web_two_txtbelow);

        floatingActionButton = findViewById(R.id.fabtwo);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareUtils.chkntake(Rehnuma.this, appBarLayout, rootContent, relativeLayout, cardView, floatingActionButton, "newsarticle-rehnuma", firebaseAnalytics);
            }
        });
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        progressBarb = (ProgressBar) findViewById(R.id.progr);
        LoadWeb();


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

                if (!isConnected(Rehnuma.this))
                    buildDialog(Rehnuma.this).show();
                else {
                    wv.reload();

                }
            }
        });
        return builder;
    }

    String sakshei = "http://epaper.ntnews.com/";
    String lol = "Mozilla/5.0 (Linux; Android 5.1.1; Nexus 5 Build/LMY48B; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/43.0.2357.65 Mobile Safari/537.36";
    String kit = "Mozilla/5.0 (Linux; Android 4.4; Nexus 5 Build/_BuildID_) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/30.0.0.0 Mobile Safari/537.36";

    public void LoadWeb() {

        wv = (WebView) findViewById(webviewtwo);
        final WebSettings settings = wv.getSettings();
        settings.setJavaScriptEnabled(true);
        wv.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        wv.loadUrl("https://therahnuma.com");
        wv.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        wv.getSettings().setAppCacheEnabled(true);
        wv.getSettings().getJavaScriptCanOpenWindowsAutomatically();
        //
        wv.getSettings().getDisplayZoomControls();
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(true);
        wv.getSettings().setSupportZoom(true);
        wv.getSettings().setLoadsImagesAutomatically(true);
        wv.getSettings().supportMultipleWindows();
        wv.getSettings().getAllowUniversalAccessFromFileURLs();
        wv.getSettings().getLoadWithOverviewMode();
        settings.getMediaPlaybackRequiresUserGesture();

        if (Build.VERSION.SDK_INT >= 21) {
            wv.getSettings().setUserAgentString(lol);
        } else {
            wv.getSettings().setUserAgentString(kit);
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
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        settings.setUseWideViewPort(true);
        settings.setEnableSmoothTransition(true);
        wv.setWebViewClient(new WebViewClient());

        //refreshLayout(this);
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBarb.setVisibility(View.GONE);
                setTitle(view.getTitle());
                if (finishs) {
                    wv.loadUrl("javascript:document.getElementsById('gtranslate_selector).value='en|ur';.doGTranslate(this);");
                    finishs = false;
                }

            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                progressBarb.setVisibility(View.VISIBLE);
                setTitle("Loading...Please wait");
                super.onPageStarted(view, url, favicon);


            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

                // wv.loadUrl("file:///android_asset/error.html");
                Alert(view);

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }


        });


        wv.setWebChromeClient(new WebChromeClient() {

            public void onProgressChanged(WebView view, int newProgress) {
                // Update the progress bar with page loading progress
                progressBarb.setProgress(newProgress);
                if (newProgress == 100) {
                    // Hide the progressbar
                    progressBarb.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_webtwo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.reload:
                wv.reload();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void Alert(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Rehnuma.this);
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (wv.canGoBack()) {

                        wv.goBack();
                        snack = Snackbar.make(wv, "Press Again", Snackbar.LENGTH_SHORT);
                        snack.show();

                        View sbarview = snack.getView();
                        sbarview.setBackgroundColor(getResources().getColor(R.color.colorAccentDark));
                    } else {
                        finish();


                    }

                    return true;


            }
        }
        return super.onKeyDown(keyCode, event);
    }
}