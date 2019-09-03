package com.izhandroid.newsbox.ts.telugu;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.izhandroid.newsbox.ts.MainActivity;
import com.izhandroid.newsbox.ts.MyWebView;
import com.izhandroid.newsbox.ts.R;
import com.izhandroid.newsbox.ts.ShareUtils;
import com.izhandroid.newsbox.ts.english.Constanted;
import com.izhandroid.newsbox.ts.english.TOI;



import java.io.File;
import java.util.Calendar;
import java.util.List;

import static com.izhandroid.newsbox.ts.R.id.appbars;
import static com.izhandroid.newsbox.ts.R.id.webviewone;

/**
 * Created by Izhan Ali on 8/7/2018.
 */

public class Sakshi extends AppCompatActivity{
    /** SAKSHI **/

    //public WebView wv;
            public WebView wv;
    //SwipeRefreshLayout swipe;
    private ProgressBar progressBar;
private TextView textView;
    private Button  customPageScreenshot;
    private LinearLayout rootContent;
    private ImageView imageView;
    private FloatingActionButton floatingActionButton;
    private AppBarLayout appBarLayout;
    private RelativeLayout relativeLayout;
    private MaterialCardView cardView;
    private TextView hiddenText;
Snackbar snack;
InterstitialAd mInterstitialAd;
FirebaseAnalytics firebaseAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_one);
       Toolbar mTopToolbar = (Toolbar) findViewById(R.id.toolbars);
        setSupportActionBar(mTopToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if(!isConnected(Sakshi.this)) buildDialog(Sakshi.this).show();
        else {
            Toast.makeText(getApplicationContext(), "Welcomr", Toast.LENGTH_SHORT);

        }

        firebaseAnalytics  = FirebaseAnalytics.getInstance(this);

        rootContent =  findViewById(R.id.weblayoutone);
        appBarLayout = findViewById(appbars);
        relativeLayout = findViewById(R.id.wtrmrkwebone);
        cardView = findViewById(R.id.web_one_txtbelow);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        floatingActionButton = findViewById(R.id.fabone);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                

                //TODO Test code

                takeSS();
            }
        });
        MobileAds.initialize(this,
                "ca-app-pub-6711729529292720~6492881965");
//TODO adid
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-6711729529292720/3126059005");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Your code to show add
                mInterstitialAd.show();
            }
        }, 45000);
        progressBar = (ProgressBar)findViewById(R.id.prg);
textView=(TextView)findViewById(R.id.sakwel);

        LoadWeb();



    }


    /*  Share Screenshot  */

    private void takeSS() {
        Bitmap b = null;

        if ((ContextCompat.checkSelfPermission(Sakshi.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)&&ContextCompat.checkSelfPermission(Sakshi.this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){


                            //


                TedPermission.with(this)
                        .setPermissionListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted() {


                            }

                            @Override
                            public void onPermissionDenied(List<String> deniedPermissions) {


                            }
                        })
                        .setRationaleMessage("We need to storage permission for Saving/Sharing News")
                        .setRationaleConfirmText("OK")
                        .setDeniedMessage("If you deny permission, you can not use this feature\n\nPlease turn on permissions from 'App Settings'")
                        .setDeniedCloseButtonText("Dismiss")
                        .setDeniedTitle("Storage Permission Denied!")
                        .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)


                        .check();


        }else{

// fullPageScreenshot.setVisibility(View.INVISIBLE);//set the visibility to INVISIBLE of first button
            // hiddenText.setVisibility(View.VISIBLE);//set the visibility to VISIBLE of hidden text
appBarLayout.setVisibility(View.INVISIBLE);
floatingActionButton.setVisibility(View.INVISIBLE);
cardView.setVisibility(View.VISIBLE);
relativeLayout.setVisibility(View.VISIBLE);
            b = ShareUtils.getScreenShot(rootContent);
            appBarLayout.setVisibility(View.VISIBLE);
            floatingActionButton.setVisibility(View.VISIBLE);
            cardView.setVisibility(View.INVISIBLE);
            relativeLayout.setVisibility(View.INVISIBLE);
            //After taking screenshot reset the button and view again
            //fullPageScreenshot.setVisibility(View.VISIBLE);//set the visibility to VISIBLE of first button again
            //hiddenText.setVisibility(View.INVISIBLE);//set the visibility to INVISIBLE of hidden text

            //NOTE:  You need to use visibility INVISIBLE instead of GONE to remove the view from frame else it wont consider the view in frame and you will not get screenshot as you required.
            //If bitmap is not null
            if (b != null) {


                // showScreenShotImage(b);//show bitmap over imageview

                File saveFile = ShareUtils.getMainDirectoryName();//get the path to save screenshot
                File file = ShareUtils.store(b, "newsarticle-sakshi" + Calendar.getInstance().getTime().toString() + ".jpg", saveFile);//save the screenshot to selected path
                //shareScreenshot(file, Sakshi.this);//finally share screenshot
                ShareUtils.showSSDialog(b, Sakshi.this, file, firebaseAnalytics);
            } else {
                //If bitmap is null show toast message
                Toast.makeText(this, "failed to share", Toast.LENGTH_SHORT).show();
                Bundle params = new Bundle();
                params.putString("msg", "ss did not took");
                params.putString("title", "bitmap was null");
                firebaseAnalytics.logEvent("ErrorinSSak", params);
            }
        }





        //If Screenshot type is CUSTOM



    }

    private boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
            else return false;
        } else
            return false;
    }


    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setCancelable(true);
        builder.setTitle("No Internet Connection");
        builder.setMessage("Please check your Wifi/Mobile Data and retry");
        builder.setIcon(android.R.drawable.ic_dialog_alert);


        builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
finish();

            }
        });
builder.setNegativeButton("Retry", new DialogInterface.OnClickListener(){
    @Override
    public void onClick(DialogInterface dialog, int which) {
        if(!isConnected(Sakshi.this))
            buildDialog(Sakshi.this).show();
        else {
            wv.reload();

        }
    }
});
        return builder;
    }


String sakshiu = "http://epaper.sakshi.com/";
String lol = "Mozilla/5.0 (Linux; Android 5.1.1; Nexus 5 Build/LMY48B; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/43.0.2357.65 Mobile Safari/537.36";
    String kit = "Mozilla/5.0 (Linux; Android 4.4; Nexus 5 Build/_BuildID_) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/30.0.0.0 Mobile Safari/537.36";
    public void LoadWeb(){

        Bundle params = new Bundle();
        params.putString("msg", "Sakshi loaded from HansAct");
        params.putString("title", "Sakshi was called");
        firebaseAnalytics.logEvent("Sakshi", params);

        wv =  findViewById(webviewone);


        final WebSettings settings = wv.getSettings();
        settings.setJavaScriptEnabled(true);
        wv.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);

        wv.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        wv.getSettings().setAppCacheEnabled(true);
        wv.getSettings().getJavaScriptCanOpenWindowsAutomatically();
        //
        wv.getSettings().setSupportZoom(true);
        wv.getSettings().setLoadsImagesAutomatically(true);
        wv.getSettings().supportMultipleWindows();
        wv.getSettings().getAllowUniversalAccessFromFileURLs();
        wv.getSettings().getLoadWithOverviewMode();
        settings.getMediaPlaybackRequiresUserGesture();

        if (Build.VERSION.SDK_INT >= 21) {
            wv.getSettings().setUserAgentString(lol);
        }else{
            wv.getSettings().setUserAgentString(kit);
        }

        if (Build.VERSION.SDK_INT <= 18) {
            wv.getSettings().setBuiltInZoomControls(true);
             wv.getSettings().setDisplayZoomControls(true);
        }else {
            wv.getSettings().setBuiltInZoomControls(false);
            wv.getSettings().setDisplayZoomControls(false);
            wv.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }
        settings.setAllowFileAccess(true);
        settings.setAllowContentAccess(true);



        settings.setDomStorageEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        settings.setUseWideViewPort(true);
        settings.setEnableSmoothTransition(true);
        wv.setWebViewClient(new WebViewClient());

        /*wv.setOnScrollChangeListener(new MyWebView.OnScrollChangeListener(){
            @Override
            public void onScrollChange(WebView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(scrollX> oldScrollY){
                    //Do stuff
                    Toast.makeText(Sakshi.this, "Up", Toast.LENGTH_SHORT).show();
                    //Do stuff
                }
                else if(scrollX< oldScrollY){
                    Toast.makeText(Sakshi.this, "Down", Toast.LENGTH_SHORT).show();
                }
                Log.d("Scroll","We Scrolled etc...");
            }


        });*/
        //refreshLayout(this);
        wv.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                // swipe.setRefreshing(false);
                if(url.equals(sakshiu)){
                    textView.setVisibility(View.VISIBLE);
                }else {
                    textView.setVisibility(View.INVISIBLE);
                }
                super.onPageFinished(view, url);
                {
                    super.onPageFinished(view, url);
                    if (url.equals(sakshiu)) {
                        textView.setVisibility(View.VISIBLE);
                    }else {
                        textView.setVisibility(View.INVISIBLE);
                    }
                }

               progressBar.setVisibility(View.GONE);
                setTitle(view.getTitle());



            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

              progressBar.setVisibility(View.VISIBLE);
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


        wv.setWebChromeClient(new WebChromeClient(){

            public void onProgressChanged(WebView view, int newProgress){
                // Update the progress bar with page loading progress
                progressBar.setProgress(newProgress);
                if(newProgress == 100){
                    // Hide the progressbar
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

        wv.loadUrl(sakshiu);


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
                if(item.isChecked()){
                    item.setChecked(false);
                    this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


                } else {
                    this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

                    item.setChecked(true);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void Alert(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(Sakshi.this);
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
        builder.setNegativeButton("Retry", new DialogInterface.OnClickListener(){
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
                        snack=Snackbar.make(wv, "Press Again", Snackbar.LENGTH_SHORT);
                        snack.show();

                        View sbarview = snack.getView();
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




/** boolean doubleBackToExitPressedOnce = false;


 public void Exeet() {
 if (doubleBackToExitPressedOnce) {
 finish();
 }

 this.doubleBackToExitPressedOnce = true;


 new Handler().postDelayed(new Runnable() {

 @Override
 public void run() {
 doubleBackToExitPressedOnce=false;
 }
 }, 2000);
 }




 **/




}
