package com.izhandroid.newsbox.ts;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.izhandroid.newsbox.ts.english.HansInd;
import com.izhandroid.newsbox.ts.english.IndEP;
import com.izhandroid.newsbox.ts.english.MoreEng;
import com.izhandroid.newsbox.ts.telugu.AndhraBhumi;

import com.izhandroid.newsbox.ts.telugu.Varta;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

/**
 * Created by Izhan Ali on 8/7/2018.
 */

public class FragEng extends Fragment {

    String deccanch = "http://epaper.deccanchronicle.com";
    String thi = "http://epaper.thehansindia.com";
    String teltd = "http://epaper.telanganatoday.com";
    String ie = "http://epaper.newindianexpress.com";
    String dchr = "http:/www.deccanheraldepaper.com";
    String toii = "https://epaper.timesgroup.com";
    String[] titles = {
            "DC", "HANS INDIA",
            "TELANGANA TODAY", "INDIAN EXPRESS",
            "DECCAN HERALD", "TIMES OF INDIA"
    };

    String[] descriptions = {
            "  ", "  ",
            "  ", "  ",
            "  ", "  ",
    };

    String ea = "https://deccanchronicle.com";
    String eb = "https://thehansindia.com";
    String ec ="https://telanganatoday.com";
    String ed ="http://www.newindianexpress.com";
    String ee = "https://deccanherald.com";
    String ef = "https://m.timesofindia.com";


    int[] Pics = {
            R.drawable.dc,
            R.drawable.eng_hans,
            R.drawable.eng_teltod,
            R.drawable.eng_indexp,
            R.drawable.eng_dh,
            R.drawable.eng_toi,


    };
    View v;
    private ListView lv;

    public FragEng() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup
            container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.eng_frag, container, false);
        MobileAds.initialize(getActivity(),

                "ca-app-pub-6711729529292720~6492881965");

        AdView mAdView = rootView.findViewById(R.id.adViewe);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        lv = (ListView) rootView.findViewById(R.id.engList);
        FragEng.MyAdapter adapter = new FragEng.MyAdapter(getActivity(), titles, descriptions, Pics);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch (position) {
                    case 0:
                        //Intent sakshi = new Intent(getActivity(), Sakshi.class);
                        //startActivity(sakshi);
                        Intent sashi = new Intent(getActivity(), AndhraBhumi.class);
                        sashi.putExtra("dc", deccanch);
                        startActivity(sashi);
                        break;


                    case 1:
                        Intent haa = new Intent(getActivity(), HansInd.class);
                        haa.putExtra("hans", thi);
                        startActivity(haa);
                        break;
                    case 2:
                        Intent teee = new Intent(getActivity(), HansInd.class);
                        teee.putExtra("teltod", teltd);
                        startActivity(teee);
                        break;
                    case 3:
                        Intent dd = new Intent(getActivity(), IndEP.class);
                        startActivity(dd);

                        break;
                    case 4:
                        Intent deccc = new Intent(getActivity(), HansInd.class);
                        deccc.putExtra("decher", dchr);
                        startActivity(deccc);
                        break;
                    case 5:

                        openCustomTab(getActivity(), Uri.parse("https://epaper.timesgroup.com"));

                        break;


                }

            }



            @SuppressWarnings("unused")
            public void onClick(View v) {

            }


        });
        Button btnTMore = rootView.findViewById(R.id.moreeng);
        btnTMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent more = new Intent(getActivity(), MoreEng.class);
                startActivity(more);
            }
        });
        return rootView;
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_eng, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.search_en:
                Intent i = new Intent(getActivity(), AllNewsPTel.class);
                startActivity(i);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    class MyAdapter extends ArrayAdapter {
        int[] imageArray;
        String[] titleArray;
        String[] descArray;

        public MyAdapter(Context context, String[] titles1, String[] desc1, int[] img1) {
            super(context, R.layout.list_model, R.id.idTitle, titles1);
            this.imageArray = img1;
            this.titleArray = titles1;
            this.descArray = desc1;


        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.list_model, parent, false);
            ImageView myimg = (ImageView) row.findViewById(R.id.idPic);
            TextView mytitle = (TextView) row.findViewById(R.id.idTitle);
            TextView mydesc = (TextView) row.findViewById(R.id.idDesc);
            TextView mybut = (TextView) row.findViewById(R.id.textView4);
            myimg.setImageResource(imageArray[position]);
            myimg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (position) {
                        case 0:
                            //Intent sakshi = new Intent(getActivity(), Sakshi.class);
                            //startActivity(sakshi);
                            Intent sashi = new Intent(getActivity(), AndhraBhumi.class);
                            sashi.putExtra("dc", deccanch);
                            startActivity(sashi);
                            break;


                        case 1:
                            Intent haa = new Intent(getActivity(), HansInd.class);
                            haa.putExtra("hans", thi);
                            startActivity(haa);
                            break;
                        case 2:
                            Intent teee = new Intent(getActivity(), HansInd.class);
                            teee.putExtra("teltod", teltd);
                            startActivity(teee);
                            break;
                        case 3:
                            Intent dd = new Intent(getActivity(), IndEP.class);
                            startActivity(dd);

                            break;
                        case 4:
                            Intent deccc = new Intent(getActivity(), HansInd.class);
                            deccc.putExtra("decher", dchr);
                            startActivity(deccc);
                            break;
                        case 5:

                            openCustomTab(getActivity(), Uri.parse("https://epaper.timesgroup.com"));

                            break;


                    }

                }
            });

            mytitle.setText(titleArray[position]);


            mytitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (position) {
                        case 0:
                            //Intent sakshi = new Intent(getActivity(), Sakshi.class);
                            //startActivity(sakshi);
                            Intent sashi = new Intent(getActivity(), AndhraBhumi.class);
                            sashi.putExtra("dc", deccanch);
                            startActivity(sashi);
                            break;


                        case 1:
                            Intent haa = new Intent(getActivity(), HansInd.class);
                            haa.putExtra("hans", thi);
                            startActivity(haa);
                            break;
                        case 2:
                            Intent teee = new Intent(getActivity(), HansInd.class);
                            teee.putExtra("teltod", teltd);
                            startActivity(teee);
                            break;
                        case 3:
                            Intent dd = new Intent(getActivity(), IndEP.class);
                            startActivity(dd);

                            break;
                        case 4:
                            Intent deccc = new Intent(getActivity(), HansInd.class);
                            deccc.putExtra("decher", dchr);
                            startActivity(deccc);
                            break;
                        case 5:

                            openCustomTab(getActivity(), Uri.parse("https://epaper.timesgroup.com"));

                            break;


                    }
                }
            });
            mydesc.setText(descArray[position]);
mydesc.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        switch (position) {
            case 0:
                //Intent sakshi = new Intent(getActivity(), Sakshi.class);
                //startActivity(sakshi);
                Intent sashi = new Intent(getActivity(), AndhraBhumi.class);
                sashi.putExtra("dc", deccanch);
                startActivity(sashi);
                break;


            case 1:
                Intent haa = new Intent(getActivity(), HansInd.class);
                haa.putExtra("hans", thi);
                startActivity(haa);
                break;
            case 2:
                Intent teee = new Intent(getActivity(), HansInd.class);
                teee.putExtra("teltod", teltd);
                startActivity(teee);
                break;
            case 3:
                Intent dd = new Intent(getActivity(), IndEP.class);
                startActivity(dd);

                break;
            case 4:
                Intent deccc = new Intent(getActivity(), HansInd.class);
                deccc.putExtra("decher", dchr);
                startActivity(deccc);
                break;
            case 5:

                openCustomTab(getActivity(), Uri.parse("https://epaper.timesgroup.com"));

                break;


        }
    }
});

mybut.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if (position == 0) {
            Intent int0 = new Intent(getActivity(), Varta.class);
            int0.putExtra("ea", ea);

            startActivity(int0);
        }
        if (position == 1) {
            Intent int1 = new Intent(getActivity(), Varta.class);
            int1.putExtra("eb", eb);

            startActivity(int1);
        }
        if (position == 2) {
            Intent int2 = new Intent(getActivity(), Varta.class);
            int2.putExtra("ec", ec);

            startActivity(int2);
        }
        if (position == 3) {
            Intent int3 = new Intent(getActivity(), Varta.class);
            int3.putExtra("ed", ed);

            startActivity(int3);
        }
        if (position == 4) {
            Intent int4 = new Intent(getActivity(), Varta.class);
            int4.putExtra("ee", ee);

            startActivity(int4);
        }
        if (position == 5) {
            Intent int5 = new Intent(getActivity(), Varta.class);
            int5.putExtra("ef", ef);

            startActivity(int5);
        }
    }
});
            return row;
        }
    }
}
