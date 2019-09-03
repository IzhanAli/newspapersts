package com.izhandroid.newsbox.ts;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
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
import com.google.firebase.analytics.FirebaseAnalytics;
import com.izhandroid.newsbox.ts.telugu.AndhraBhumi;

import com.izhandroid.newsbox.ts.telugu.MoreTel;
import com.izhandroid.newsbox.ts.telugu.NamasteTel;
import com.izhandroid.newsbox.ts.telugu.Sakshi;
import com.izhandroid.newsbox.ts.telugu.Varta;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

/**
 * Created by Izhan Ali on 8/7/2018.
 */

public class FragTel extends Fragment {

    FirebaseAnalytics firebaseAnalytics;
    Resources res;

    String[] titles = {"SAKSHI", "EENADU", "NAMASTE TG", "ANDHRAJYOTI", "ANDHRABHOOMI", "VARTHA", "MANA TG", "NAVA TG",};

    String[] descriptions = {
            "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ",
    };

    int[] Pics = {
            R.drawable.tel_sakshi,
            R.drawable.eenadu,
            R.drawable.tel_namastetel,
            R.drawable.tel_ajyoti,
            R.drawable.tel_andhrabhoomi,
            R.drawable.tel_varta,
            R.drawable.tel_manate,
            R.drawable.tel_navatel,


    };
    String anbhst = "http://epaper.andhrabhoomi.net/andhrabhoomi.aspx?id=TS";
    String anbhsa = "http://epaper.andhrabhoomi.net/andhrabhoomi.aspx?id=AP";
    String nt = "http://epaper.ntnews.com";
    String aj = "https://epaper.andhrajyothy.com";
    String vt = "http://epaper.vaartha.com";
    String mt = "http://epaper.manatelangana.news";
    String navt = "http://epaper.navatelangana.com";

    String ta = "https://www.sakshi.com";
    String tb = "http://www.eenadu.net";
    String tc = "https://www.ntnews.com";
    String td = "http://www.andhrajyothy.com";
    String te = "http://www.andhrabhoomi.net";
    String tf = "http://www.vaartha.com/category/news";
    String tg = "http://www.manatelangana.news";
    String th = "http://www.navatelangana.com";
    View v;
    private ListView lv;
    private Button btnTMore;

    public FragTel() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.tel_frag, container, false);
        // RelativeLayout rl = (RelativeLayout) rootView.findViewById(R.id.telrela);

        lv = (ListView) rootView.findViewById(R.id.telList);

        firebaseAnalytics = FirebaseAnalytics.getInstance(getContext());
        MobileAds.initialize(getActivity(),

                        "ca-app-pub-6711729529292720~6492881965");

       AdView mAdView = rootView.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        MyAdapter adapter = new MyAdapter(getActivity(), titles, descriptions, Pics);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {


                switch (position) {
                    case 0:
                        Intent sakshi = new Intent(getActivity(), Sakshi.class);
                        startActivity(sakshi);
                        break;

                    case 1:
                        Bundle params = new Bundle();
                        params.putString("msg", "EENADU hyd loaded from Fragtel");
                        params.putString("title", "eenadu was called");
                        firebaseAnalytics.logEvent("EENADU", params);
                        openCustomTab(getActivity(), Uri.parse("https://epaper.eenadu.net"));

                        break;
                    case 2:
                        Intent namste = new Intent(getActivity(), NamasteTel.class);
                        namste.putExtra("ntnews", nt);

                        startActivity(namste);
                        break;
                    case 3:
                        Intent anjy = new Intent(getActivity(), NamasteTel.class);
                        anjy.putExtra("ajyothi", aj);
                        startActivity(anjy);
                        break;
                    case 4:

                        LayoutInflater inflater1 = LayoutInflater.from(getContext());
                        final View view1 = inflater1.inflate(R.layout.custom_ab, null);
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setView(view1);
                        //btn
                        Button btte = view1.findViewById(R.id.bute);
                        Button btap = view1.findViewById(R.id.buap);
                        final AlertDialog alertDialog = builder.create();

                        alertDialog.show();
                        btte.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent chah = new Intent(getActivity(), AndhraBhumi.class);
                                chah.putExtra("abts", anbhst);
                                startActivity(chah);
                            }
                        });
                        btap.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent cah = new Intent(getActivity(), AndhraBhumi.class);
                                cah.putExtra("abap", anbhsa);
                                startActivity(cah);
                            }
                        });
                        break;
                    case 5:
                        Intent vart = new Intent(getActivity(), NamasteTel.class);
                        vart.putExtra("vartha", vt);
                        startActivity(vart);
                        break;
                    case 6:
                        Intent mana = new Intent(getActivity(), NamasteTel.class);
                        mana.putExtra("manatel", mt);
                        startActivity(mana);
                        break;
                    case 7:
                        Intent nava = new Intent(getActivity(), NamasteTel.class);
                        nava.putExtra("navatel", navt);
                        startActivity(nava);

                        break;
                    /**case 8:
                     NN
                     break;
                     **/

                }

            }

            @SuppressWarnings("unused")
            public void onClick(View v) {

            }


        });


        btnTMore = rootView.findViewById(R.id.moretel);
        btnTMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent more = new Intent(getActivity(), MoreTel.class);
                startActivity(more);
            }
        });

        // lv.addFooterView(btnAddMore);


        /**Button button = (Button) rootView.findViewById(R.id.btn1);
         button.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
        Intent sakshi = new Intent(getActivity(), Sakshi.class);
        startActivity(sakshi);
        }
        });

         Button button1 = (Button) rootView.findViewById(R.id.btn2);
         button1.setOnClickListener(new View.OnClickListener(){
        @Override public void onClick(View v) {
        Intent eenadu = new Intent(getActivity(), Eenadu.class);
        startActivity(eenadu);
        }
        });  **/



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
        inflater.inflate(R.menu.menu_tel, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.search:
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
            final View row = inflater.inflate(R.layout.list_model, parent, false);
            ImageView myimg =  row.findViewById(R.id.idPic);
            TextView mytitle = (TextView) row.findViewById(R.id.idTitle);
            TextView mydesc = (TextView) row.findViewById(R.id.idDesc);
            TextView mybut =  row.findViewById(R.id.textView4);
            myimg.setImageResource(imageArray[position]);
            myimg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (position) {
                        case 0:
                            Intent sakshi = new Intent(getActivity(), Sakshi.class);
                            startActivity(sakshi);
                            break;

                        case 1:
                            Bundle params = new Bundle();
                            params.putString("msg", "EENADU hyd loaded from Fragtel");
                            params.putString("title", "eenadu was called");
                            firebaseAnalytics.logEvent("EENADU", params);
                         openCustomTab(getActivity(), Uri.parse("https://epaper.eenadu.net"));

                            break;
                        case 2:
                            Intent namste = new Intent(getActivity(), NamasteTel.class);
                            namste.putExtra("ntnews", nt);

                            startActivity(namste);
                            Bundle paramsd = new Bundle();
                            paramsd.putString("msg", "NamasteTEl from Fragtel to Namste Telact");
                            paramsd.putString("title", "ntnews was called");
                            firebaseAnalytics.logEvent("NTTe", paramsd);
                            break;
                        case 3:
                            Intent anjy = new Intent(getActivity(), NamasteTel.class);
                            anjy.putExtra("ajyothi", aj);
                            startActivity(anjy);
                            Bundle paramd = new Bundle();
                            paramd.putString("msg", "AndJyo from Fragtel to Namste Telact");
                            paramd.putString("title", "ajyothi was called");
                            firebaseAnalytics.logEvent("AJyothiTe", paramd);
                            break;
                        case 4:
                            LayoutInflater inflater1 = LayoutInflater.from(getContext());
                            final View view1 = inflater1.inflate(R.layout.custom_ab, null);
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setView(view1);
                            //btn
                            Button btte = view1.findViewById(R.id.bute);
                            Button btap = view1.findViewById(R.id.buap);
                            final AlertDialog alertDialog = builder.create();

                            alertDialog.show();
                            btte.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent chah = new Intent(getActivity(), AndhraBhumi.class);
                                    chah.putExtra("abts", anbhst);
                                    startActivity(chah);
                                }
                            });
                            btap.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent cah = new Intent(getActivity(), AndhraBhumi.class);
                                    cah.putExtra("abap", anbhsa);
                                    startActivity(cah);
                                }
                            });
                            break;
                        case 5:
                            Intent vart = new Intent(getActivity(), NamasteTel.class);
                            vart.putExtra("vartha", vt);
                            startActivity(vart);
                            Bundle parae = new Bundle();
                            parae.putString("msg", "Vartha from Fragtel to Namste Telact");
                            parae.putString("title", "varta was called");
                            firebaseAnalytics.logEvent("VartaTe", parae);
                            break;
                        case 6:
                            Intent mana = new Intent(getActivity(), NamasteTel.class);
                            mana.putExtra("manatel", mt);
                            startActivity(mana);
                            Bundle paramdi = new Bundle();
                            paramdi.putString("msg", "ManaTel from Fragtel to Namste Telact");
                            paramdi.putString("title", "manatel was called");
                            firebaseAnalytics.logEvent("ManaTe", paramdi);
                            break;
                        case 7:
                            Intent nava = new Intent(getActivity(), NamasteTel.class);
                            nava.putExtra("navatel", navt);
                            startActivity(nava);
                            Bundle paramdic = new Bundle();
                            paramdic.putString("msg", "NavaTel from Fragtel to Namste Telact");
                            paramdic.putString("title", "navatel was called");
                            firebaseAnalytics.logEvent("navaTe", paramdic);
                            break;
                        /*case 8:
                            Intent more = new Intent(getActivity(), MoreTel.class);
                            startActivity(more);
                            break;*/

                    }

                }
            });
            mytitle.setText(titleArray[position]);
            mytitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (position) {
                        case 0:
                            Intent sakshi = new Intent(getActivity(), Sakshi.class);
                            startActivity(sakshi);
                            break;

                        case 1:
                            Bundle params = new Bundle();
                            params.putString("msg", "EENADU hyd loaded from Fragtel");
                            params.putString("title", "eenadu was called");
                            firebaseAnalytics.logEvent("EENADUTe", params);
                             openCustomTab(getActivity(), Uri.parse("https://epaper.eenadu.net/"));

                            break;
                        case 2:
                            Intent namste = new Intent(getActivity(), NamasteTel.class);
                            namste.putExtra("ntnews", nt);
                            Bundle paramsd = new Bundle();
                            paramsd.putString("msg", "NamasteTEl from Fragtel to Namste Telact");
                            paramsd.putString("title", "ntnews was called");
                            firebaseAnalytics.logEvent("NTTe", paramsd);
                            startActivity(namste);
                            break;
                        case 3:
                            Intent anjy = new Intent(getActivity(), NamasteTel.class);
                            anjy.putExtra("ajyothi", aj);
                            Bundle paramd = new Bundle();
                            paramd.putString("msg", "AndJyo from Fragtel to Namste Telact");
                            paramd.putString("title", "ajyothi was called");
                            firebaseAnalytics.logEvent("AJyothiTe", paramd);
                            startActivity(anjy);
                            break;
                        case 4:
                            LayoutInflater inflater1 = LayoutInflater.from(getContext());
                            final View view1 = inflater1.inflate(R.layout.custom_ab, null);
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setView(view1);
                            //btn
                            Button btte = view1.findViewById(R.id.bute);
                            Button btap = view1.findViewById(R.id.buap);
                            final AlertDialog alertDialog = builder.create();

                            alertDialog.show();
                            btte.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent chah = new Intent(getActivity(), AndhraBhumi.class);
                                    chah.putExtra("abts", anbhst);
                                    startActivity(chah);
                                }
                            });
                            btap.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent cah = new Intent(getActivity(), AndhraBhumi.class);
                                    cah.putExtra("abap", anbhsa);
                                    startActivity(cah);
                                }
                            });

                            break;
                        case 5:
                            Intent vart = new Intent(getActivity(), NamasteTel.class);
                            vart.putExtra("vartha", vt);
                            startActivity(vart);
                            Bundle parae = new Bundle();
                            parae.putString("msg", "Vartha from Fragtel to Namste Telact");
                            parae.putString("title", "varta was called");
                            firebaseAnalytics.logEvent("VartaTe", parae);
                            break;
                        case 6:
                            Intent mana = new Intent(getActivity(), NamasteTel.class);
                            mana.putExtra("manatel", mt);
                            startActivity(mana);
                            Bundle paramdi = new Bundle();
                            paramdi.putString("msg", "ManaTel from Fragtel to Namste Telact");
                            paramdi.putString("title", "manatel was called");
                            firebaseAnalytics.logEvent("ManaTe", paramdi);
                            break;
                        case 7:
                            Intent nava = new Intent(getActivity(), NamasteTel.class);
                            nava.putExtra("navatel", navt);
                            startActivity(nava);
                            Bundle paramdic = new Bundle();
                            paramdic.putString("msg", "NavaTel from Fragtel to Namste Telact");
                            paramdic.putString("title", "navatel was called");
                            firebaseAnalytics.logEvent("navaTe", paramdic);
                            break;
                    }
                }
            });
            mydesc.setText(descArray[position]);
            mybut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position == 0) {
                        Intent int0 = new Intent(getActivity(), Varta.class);
                        int0.putExtra("ta", ta);

                        startActivity(int0);
                    }
                    if (position == 1) {
                        Intent int1 = new Intent(getActivity(), Varta.class);
                        int1.putExtra("tb", tb);

                        startActivity(int1);
                    }
                    if (position == 2) {
                        Intent int2 = new Intent(getActivity(), Varta.class);
                        int2.putExtra("tc", tc);

                        startActivity(int2);
                    }
                    if (position == 3) {
                        Intent int3 = new Intent(getActivity(), Varta.class);
                        int3.putExtra("td", td);

                        startActivity(int3);
                    }
                    if (position == 4) {
                        Intent int4 = new Intent(getActivity(), Varta.class);
                        int4.putExtra("te", te);

                        startActivity(int4);
                    }
                    if (position == 5) {
                        Intent int5 = new Intent(getActivity(), Varta.class);
                        int5.putExtra("tf", tf);

                        startActivity(int5);
                    }
                    if (position == 6) {
                        Intent int6 = new Intent(getActivity(), Varta.class);
                        int6.putExtra("tg", tg);

                        startActivity(int6);
                    }
                    if (position == 7) {
                        Intent int7 = new Intent(getActivity(), Varta.class);
                        int7.putExtra("th", th);

                        startActivity(int7);
                    }

                }
            });


            return row;
        }
    }


}

