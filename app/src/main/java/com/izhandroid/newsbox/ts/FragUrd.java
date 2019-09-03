package com.izhandroid.newsbox.ts;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.izhandroid.newsbox.ts.telugu.MoreTel;
import com.izhandroid.newsbox.ts.telugu.Varta;
import com.izhandroid.newsbox.ts.urdu.Etemaad;
import com.izhandroid.newsbox.ts.urdu.Munsif;
import com.izhandroid.newsbox.ts.urdu.Rehnuma;
import com.izhandroid.newsbox.ts.urdu.Sahara;
import com.izhandroid.newsbox.ts.urdu.Siasat;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
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
import android.widget.Toast;

/**
 * Created by Izhan Ali on 8/7/2018.
 */

public class FragUrd extends Fragment {
    String[] titles = {
            "Siasat", "Etemaad",
            "Munsif", "Sahara",
            "Rehnuma", "Sadae Hussaini"
    };

    String[] descriptions = {
            " ", " ", " ", " ", " ", " "
    };

    int[] Pics = {
            R.drawable.ur_sia,
            R.drawable.ur_et,
            R.drawable.ur_mun,
            R.drawable.ur_sah,
            R.drawable.ur_reh,
            R.drawable.urdu,



    };
    FirebaseAnalytics firebaseAnalytics;
    private String saha = "http://roznamasahara.com/epapermain.aspx";
    String reh ="https://www.yumpu.com/user/therahnumaedeccan";
    String sad = "http://epaper.sadaehussaini.com";
    private ListView lv;
    String ua = "https://www.siasat.com";
    String ub = "https://www.etemaaddaily.com";
    String uc = "https://www.munsifdaily.in";

    String ue = "https://www.therahnuma.com";

    View v;

    public FragUrd() {
    }

    @Override
    public void onCreate (@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup
            container, @Nullable Bundle savedInstanceState){
        final View rootView = inflater.inflate(R.layout.urd_frag, container, false);

        firebaseAnalytics = FirebaseAnalytics.getInstance(getContext());
        MobileAds.initialize(getActivity(),

                "ca-app-pub-6711729529292720~6492881965");

        AdView mAdView = rootView.findViewById(R.id.adViewu);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        lv = (ListView) rootView.findViewById(R.id.urdList);
        FragUrd.MyAdapter adapter = new FragUrd.MyAdapter(getActivity(), titles, descriptions, Pics);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch (position) {
                    case 0:
                        //Intent sakshi = new Intent(getActivity(), Sakshi.class);
                        //startActivity(sakshi);
                        Intent sia = new Intent(getActivity(), Siasat.class);
                        startActivity(sia);
                        Bundle paramdic = new Bundle();
                        paramdic.putString("msg", "Siasat from FraguRd");
                        paramdic.putString("title", "siasat was called");
                        firebaseAnalytics.logEvent("SiasatUr", paramdic);
                        break;


                    case 1:
                        Intent e = new Intent(getActivity(), Etemaad.class);
                        startActivity(e);
                        Bundle paramdi = new Bundle();
                        paramdi.putString("msg", "Etemad from FraguRd");
                        paramdi.putString("title", "etemad was called");
                        firebaseAnalytics.logEvent("EtemadUr", paramdi);
                        break;
                    case 2:
                        Intent m = new Intent(getActivity(), Munsif.class);
                        startActivity(m);
                        Bundle paramdisc = new Bundle();
                        paramdisc.putString("msg", "Munsif from FraguRd");
                        paramdisc.putString("title", "munsif was called");
                        firebaseAnalytics.logEvent("MunsifUr", paramdisc);
                        break;
                    case 3:
                        Intent s = new Intent(getActivity(), Sahara.class);
                        s.putExtra("sah", saha);
                        startActivity(s);
                        Bundle paramc = new Bundle();
                        paramc.putString("msg", "Sahahra from FragUrd to SaharaAct");
                        paramc.putString("title", "sah was called");
                        firebaseAnalytics.logEvent("SaharaUr", paramc);
                        break;
                    case 4:
                        Intent r = new Intent(getActivity(), Sahara.class);
                        r.putExtra("rah", reh);
                        startActivity(r);
                        Bundle paramsc = new Bundle();
                        paramsc.putString("msg", "Rehnuma from FragUrd to SaharaAct");
                        paramsc.putString("title", "rah was called");
                        firebaseAnalytics.logEvent("RehnumaUr", paramsc);
                        break;
                    case 5:
                        Intent d = new Intent(getActivity(), Sahara.class);
                        d.putExtra("sh", sad);
                        startActivity(d);
                        Bundle paramcv = new Bundle();
                        paramcv.putString("msg", "SadaeHus from FragUrd to SaharaAct");
                        paramcv.putString("title", "sad was called");
                        firebaseAnalytics.logEvent("SadaeUr", paramcv);
                        break;



                }

            }

            @SuppressWarnings("unused")
            public void onClick(View v) {

            }


        });
      Button btnTMore = rootView.findViewById(R.id.moreeurd);
        btnTMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent localIntent2 = new Intent("android.intent.action.SENDTO", Uri.fromParts("mailto", "idappshelp@gmail.com", null));
                localIntent2.putExtra("android.intent.extra.SUBJECT", "Requesting to add a Newspaper:URDU");
                localIntent2.putExtra("android.intent.extra.TEXT", "Hi Team,\n \n Newspaper name:");
                startActivity(Intent.createChooser(localIntent2, "Send email..."));
            }
        });
        return rootView;
    }


    @Override
    public void onCreateOptionsMenu (Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.menu_urdu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.search_ur:
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
                            Intent sia = new Intent(getActivity(), Siasat.class);
                            startActivity(sia);
                            Bundle paramdic = new Bundle();
                            paramdic.putString("msg", "Siasat from FraguRd");
                            paramdic.putString("title", "siasat was called");
                            firebaseAnalytics.logEvent("SiasatUr", paramdic);
                            break;


                        case 1:
                            Intent e = new Intent(getActivity(), Etemaad.class);
                            startActivity(e);
                            Bundle paramdi = new Bundle();
                            paramdi.putString("msg", "Etemad from FraguRd");
                            paramdi.putString("title", "etemad was called");
                            firebaseAnalytics.logEvent("EtemadUr", paramdi);
                            break;
                        case 2:
                            Intent m = new Intent(getActivity(), Munsif.class);
                            startActivity(m);
                            Bundle paramdisc = new Bundle();
                            paramdisc.putString("msg", "Munsif from FraguRd");
                            paramdisc.putString("title", "munsif was called");
                            firebaseAnalytics.logEvent("MunsifUr", paramdisc);
                            break;
                        case 3:
                            Intent s = new Intent(getActivity(), Sahara.class);
                            s.putExtra("sah", saha);
                            startActivity(s);
                            Bundle paramc = new Bundle();
                            paramc.putString("msg", "Sahahra from FragUrd to SaharaAct");
                            paramc.putString("title", "sah was called");
                            firebaseAnalytics.logEvent("SaharaUr", paramc);
                            break;
                        case 4:
                            Intent r = new Intent(getActivity(), Sahara.class);
                            r.putExtra("rah", reh);
                            startActivity(r);
                            Bundle paramsc = new Bundle();
                            paramsc.putString("msg", "Rehnuma from FragUrd to SaharaAct");
                            paramsc.putString("title", "rah was called");
                            firebaseAnalytics.logEvent("RehnumaUr", paramsc);
                            break;
                        case 5:
                            Intent d = new Intent(getActivity(), Sahara.class);
                            d.putExtra("sh", sad);
                            startActivity(d);
                            Bundle paramcv = new Bundle();
                            paramcv.putString("msg", "SadaeHus from FragUrd to SaharaAct");
                            paramcv.putString("title", "sad was called");
                            firebaseAnalytics.logEvent("SadaeUr", paramcv);
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
                            Intent sia = new Intent(getActivity(), Siasat.class);
                            startActivity(sia);
                            Bundle paramdic = new Bundle();
                            paramdic.putString("msg", "Siasat from FraguRd");
                            paramdic.putString("title", "siasat was called");
                            firebaseAnalytics.logEvent("SiasatUr", paramdic);
                            break;


                        case 1:
                            Intent e = new Intent(getActivity(), Etemaad.class);
                            startActivity(e);
                            Bundle paramdi = new Bundle();
                            paramdi.putString("msg", "Etemad from FraguRd");
                            paramdi.putString("title", "etemad was called");
                            firebaseAnalytics.logEvent("EtemadUr", paramdi);
                            break;
                        case 2:
                            Intent m = new Intent(getActivity(), Munsif.class);
                            startActivity(m);
                            Bundle paramdisc = new Bundle();
                            paramdisc.putString("msg", "Munsif from FraguRd");
                            paramdisc.putString("title", "munsif was called");
                            firebaseAnalytics.logEvent("MunsifUr", paramdisc);
                            break;
                        case 3:
                            Intent s = new Intent(getActivity(), Sahara.class);
                            s.putExtra("sah", saha);
                            startActivity(s);
                            Bundle paramc = new Bundle();
                            paramc.putString("msg", "Sahahra from FragUrd to SaharaAct");
                            paramc.putString("title", "sah was called");
                            firebaseAnalytics.logEvent("SaharaUr", paramc);
                            break;
                        case 4:
                            Intent r = new Intent(getActivity(), Sahara.class);
                            r.putExtra("rah", reh);
                            startActivity(r);
                            Bundle paramsc = new Bundle();
                            paramsc.putString("msg", "Rehnuma from FragUrd to SaharaAct");
                            paramsc.putString("title", "rah was called");
                            firebaseAnalytics.logEvent("RehnumaUr", paramsc);
                            break;
                        case 5:
                            Intent d = new Intent(getActivity(), Sahara.class);
                            d.putExtra("sh", sad);
                            startActivity(d);
                            Bundle paramcv = new Bundle();
                            paramcv.putString("msg", "SadaeHus from FragUrd to SaharaAct");
                            paramcv.putString("title", "sad was called");
                            firebaseAnalytics.logEvent("SadaeUr", paramcv);
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
                            Intent sia = new Intent(getActivity(), Siasat.class);
                            startActivity(sia);
                            Bundle paramdic = new Bundle();
                            paramdic.putString("msg", "Siasat from FraguRd");
                            paramdic.putString("title", "siasat was called");
                            firebaseAnalytics.logEvent("SiasatUr", paramdic);
                            break;


                        case 1:
                            Intent e = new Intent(getActivity(), Etemaad.class);
                            startActivity(e);
                            Bundle paramdi = new Bundle();
                            paramdi.putString("msg", "Etemad from FraguRd");
                            paramdi.putString("title", "etemad was called");
                            firebaseAnalytics.logEvent("EtemadUr", paramdi);
                            break;
                        case 2:
                            Intent m = new Intent(getActivity(), Munsif.class);
                            startActivity(m);
                            Bundle paramdisc = new Bundle();
                            paramdisc.putString("msg", "Munsif from FraguRd");
                            paramdisc.putString("title", "munsif was called");
                            firebaseAnalytics.logEvent("MunsifUr", paramdisc);
                            break;
                        case 3:
                            Intent s = new Intent(getActivity(), Sahara.class);
                            s.putExtra("sah", saha);
                            startActivity(s);
                            Bundle paramc = new Bundle();
                            paramc.putString("msg", "Sahahra from FragUrd to SaharaAct");
                            paramc.putString("title", "sah was called");
                            firebaseAnalytics.logEvent("SaharaUr", paramc);
                            break;
                        case 4:
                            Intent r = new Intent(getActivity(), Sahara.class);
                            r.putExtra("rah", reh);
                            startActivity(r);
                            Bundle paramsc = new Bundle();
                            paramsc.putString("msg", "Rehnuma from FragUrd to SaharaAct");
                            paramsc.putString("title", "rah was called");
                            firebaseAnalytics.logEvent("RehnumaUr", paramsc);
                            break;
                        case 5:
                            Intent d = new Intent(getActivity(), Sahara.class);
                            d.putExtra("sh", sad);
                            startActivity(d);
                            Bundle paramcv = new Bundle();
                            paramcv.putString("msg", "SadaeHus from FragUrd to SaharaAct");
                            paramcv.putString("title", "sad was called");
                            firebaseAnalytics.logEvent("SadaeUr", paramcv);
                            break;



                    }
                }
            });

            mybut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position == 0) {
                        Intent int0 = new Intent(getActivity(), Varta.class);
                        int0.putExtra("ua", ua);

                        startActivity(int0);
                    }
                    if (position == 1) {
                        Intent int1 = new Intent(getActivity(), Varta.class);
                        int1.putExtra("ub", ub);

                        startActivity(int1);
                    }
                    if (position == 2) {
                        Intent int2 = new Intent(getActivity(), Varta.class);
                        int2.putExtra("uc", uc);

                        startActivity(int2);
                    }
                    if (position == 3) {
                      Toast t =  Toast.makeText(getActivity(), "Not available from this publisher", Toast.LENGTH_LONG  );
                      t.setGravity(Gravity.CENTER,0,0);
                      t.show();
                    }
                    if (position == 4) {
                        Intent int4 = new Intent(getActivity(), Rehnuma.class);


                        startActivity(int4);
                    }
                    if (position == 5) { Toast t =  Toast.makeText(getActivity(), "Not available from this publisher", Toast.LENGTH_LONG  );
                        t.setGravity(Gravity.CENTER,0,0);
                        t.show();
                    }
                }
            });
            return row;
        }
    }
}
