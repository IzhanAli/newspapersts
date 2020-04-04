package com.izhandroid.newsbox.ts;


import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.analytics.FirebaseAnalytics;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import hotchemi.android.rate.AppRate;
import hotchemi.android.rate.OnClickButtonListener;
import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    FirebaseAnalytics firebaseAnalytics;

    @Override
    protected void onResume() {

        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle("Telangana News");


        firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        //addin frag

        adapter.AddFragment(new FragTel(),"Telugu");
        adapter.AddFragment(new FragEng(),"English");
        adapter.AddFragment(new FragUrd(),"Urdu");



        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        AppRate.with(this)
                .setInstallDays(2) // default 10, 0 means install day.
                .setLaunchTimes(5) // default 10
                .setRemindInterval(20) // default 1
                .setShowLaterButton(true) // default true
                .setDebug(false) // default false
                .setOnClickButtonListener(new OnClickButtonListener() { // callback listener.
                    @Override
                    public void onClickButton(int which) {
                        Log.d(MainActivity.class.getName(), Integer.toString(which));
                    }
                })
                .monitor();

        // Show a dialog if meets conditions
        AppRate.showRateDialogIfMeetsConditions(this);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewPager.setCurrentItem(position,false);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });




    }


    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        // Associate searchable configuration with the SearchView
        return true;
    }
    //MANAGE MENU ITEMS HERE

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {

            case R.id.rate:
                Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.izhandroid.newsbox.ts");
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
                Toast.makeText(this, "Remember to give a 5-star rating", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.update:
                Uri uril = Uri.parse("https://play.google.com/store/apps/details?id=com.izhandroid.newsbox.ts");
                startActivity(new Intent(Intent.ACTION_VIEW, uril));
                return true;
            case R.id.share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.app_sharelinkcont));
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                return true;
                case R.id.feedback:
                    Intent localIntent1 = new Intent("android.intent.action.SENDTO", Uri.fromParts("mailto", "idappshelp@gmail.com", null));
                    localIntent1.putExtra("android.intent.extra.SUBJECT", "Feedback");
                    localIntent1.putExtra("android.intent.extra.TEXT", "Hi Team,");
                    startActivity(Intent.createChooser(localIntent1, "Send email..."));
                    Bundle bundle = new Bundle();
                    bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "txt");
                    bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "main");
                    firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SHARE, bundle);
                return true;
            case R.id.about:


               Intent lcl = new Intent(MainActivity.this, Main2Activity.class);
               startActivity(lcl);

               // WebView wv;

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}

/** comment



 **/