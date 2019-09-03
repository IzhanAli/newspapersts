package com.izhandroid.newsbox.ts;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.hardware.input.InputManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuItemCompat;
import com.arlib.floatingsearchview.FloatingSearchView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.izhandroid.newsbox.ts.english.HansInd;
import com.izhandroid.newsbox.ts.english.IndEP;
import com.izhandroid.newsbox.ts.english.TOI;
import com.izhandroid.newsbox.ts.telugu.AndhraBhumi;
import com.izhandroid.newsbox.ts.telugu.NamasteTel;
import com.izhandroid.newsbox.ts.telugu.Sakshi;
import com.izhandroid.newsbox.ts.urdu.Etemaad;
import com.izhandroid.newsbox.ts.urdu.Munsif;
import com.izhandroid.newsbox.ts.urdu.Sahara;
import com.izhandroid.newsbox.ts.urdu.Siasat;

import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;



public class AllNewsPTel extends AppCompatActivity {
List<String> liste;
    TextView mEmptyView;
    ListView listView;
    ArrayAdapter adapter;
    FloatingSearchView searchView;
    String anbhst = "http://epaper.andhrabhoomi.net/andhrabhoomi.aspx?id=TS";
    String anbhsa = "http://epaper.andhrabhoomi.net/andhrabhoomi.aspx?id=AP";
    String nt = "http://epaper.ntnews.com";
    String aj = "https://epaper.andhrajyothy.com";
    String vt = "http://epaper.vaartha.com";
    String mt = "http://epaper.manatelangana.news";
    String navt = "http://epaper.navatelangana.com";
    String ta="http://epaper.prabhanews.com";
    String tb="http://epaper.prajasakti.com";
    String tc="http://epaper.suryaa.com";
    String td="http://epaper.aadabhyderabad.in";
    String te="http://telugutimes.net/home/epapers";
    String tf="http://epaper.greatandhra.com";
    String tg="https://www.readwhere.com/newspaper/namasthe-hyderabad/Namasthe-Hyderabad/11826";
    String deccanch = "http://epaper.deccanchronicle.com";
    String thi = "http://epaper.thehansindia.com";
    String teltd = "http://epaper.telanganatoday.com";
    String ie = "http://epaper.newindianexpress.com";
    String dchr = "http:/www.deccanheraldepaper.com";
    String ane = "http://arabnews.com/pdfissues/index.php";
    String fex = "http://epaper.financialexpress.com";
    String tie = "https://epaper.telegraphindia.com";
    private String saha = "http://roznamasahara.com/epapermain.aspx";
    String reh ="https://www.yumpu.com/user/therahnumaedeccan";
    String sad = "http://epaper.sadaehussaini.com";
    String ua = "https://www.siasat.com";
    String ub = "https://www.etemaaddaily.com";
    String uc = "https://www.munsifdaily.in";
    ArrayAdapter<String> adapteri;
FloatingSearchView fs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle paramc = new Bundle();
        paramc.putString("msg", "All loaded");
        paramc.putString("title", "Search was called");
        firebaseAnalytics.logEvent("Searched", paramc);

        listView = (ListView) findViewById(R.id.listSearch);

prepareData();



        adapter = new ArrayAdapter (this, android.R.layout.simple_list_item_1, liste);
        listView.setAdapter(adapter);



        searchView = (FloatingSearchView) findViewById(R.id.searchView);

        searchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener(){
            @Override
            public void onSearchTextChanged(String oldQuery, String newQuery){
                List<String> newList = new ArrayList<>();
                for (int i = 0; i<liste.size(); i++){
                    if (liste.get(i).contains(newQuery)){
                        newList.add(liste.get(i));
                    }
                    adapter =new ArrayAdapter(AllNewsPTel.this, android.R.layout.simple_list_item_1, newList);
                    listView.setAdapter(adapter);
                }
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (adapter.getItem(position).toString()){
                    case "sakshi":

                        Intent sakshi = new Intent(AllNewsPTel.this, Sakshi.class);
                        startActivity(sakshi);

                        Toast.makeText(AllNewsPTel.this, String.valueOf(id), Toast.LENGTH_SHORT).show();
                        break;
                    case "eenadu":
                        openCustomTab(AllNewsPTel.this, Uri.parse("https://epaper.eenadu.net"));
                    break;
                    case "namasthe telangana":
                        Intent namste = new Intent(AllNewsPTel.this, NamasteTel.class);
                        namste.putExtra("ntnews", nt);

                        startActivity(namste);
                        Toast.makeText(AllNewsPTel.this, String.valueOf(id), Toast.LENGTH_SHORT).show();
                        break;
                    case "andhrajyoti":
                        Intent anjy = new Intent(AllNewsPTel.this, NamasteTel.class);
                        anjy.putExtra("ajyothi", aj);
                        startActivity(anjy);
                        break;
                    case "andhrabhoomi":
                        LayoutInflater inflater1 = LayoutInflater.from(AllNewsPTel.this);
                        final View view1 = inflater1.inflate(R.layout.custom_ab, null);
                        AlertDialog.Builder builder = new AlertDialog.Builder(AllNewsPTel.this);
                        builder.setView(view1);
                        //btn
                        Button btte = view1.findViewById(R.id.bute);
                        Button btap = view1.findViewById(R.id.buap);
                        final AlertDialog alertDialog = builder.create();

                        alertDialog.show();
                        btte.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent chah = new Intent(AllNewsPTel.this, AndhraBhumi.class);
                                chah.putExtra("abts", anbhst);
                                startActivity(chah);
                            }
                        });
                        btap.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent cah = new Intent(AllNewsPTel.this, AndhraBhumi.class);
                                cah.putExtra("abap", anbhsa);
                                startActivity(cah);
                            }
                        });
                        break;
                    case "vartha":
                        Intent vart = new Intent(AllNewsPTel.this, NamasteTel.class);
                        vart.putExtra("vartha", vt);
                        startActivity(vart);
                        break;
                    case "mana telangana":
                        Intent mana = new Intent(AllNewsPTel.this, NamasteTel.class);
                        mana.putExtra("manatel", mt);
                        startActivity(mana);
                        break;
                    case "nava telangana":
                        Intent nava = new Intent(AllNewsPTel.this, NamasteTel.class);
                        nava.putExtra("navatel", navt);
                        startActivity(nava);
                        break;
                    case "prabha news":
                        Intent a = new Intent(AllNewsPTel.this, NamasteTel.class);
                        a.putExtra("keya",ta);
                        startActivity(a);
                        break;
                    case "praja shakti":
                        Intent b = new Intent(AllNewsPTel.this, NamasteTel.class);
                        b.putExtra("keyb",tb);
                        startActivity(b);
                        break;
                    case "surya":
                        Intent c = new Intent(AllNewsPTel.this, NamasteTel.class);
                        c.putExtra("keyc",tc);
                        startActivity(c);
                        break;
                    case "aadab hyderabad":
                        Intent d = new Intent(AllNewsPTel.this, NamasteTel.class);
                        d.putExtra("keyd",td);
                        startActivity(d);
                        break;
                    case "telugu times":
                        Intent e = new Intent(AllNewsPTel.this, NamasteTel.class);
                        e.putExtra("keye",te);
                        startActivity(e);
                        break;
                    case "great andhra":
                        Intent f = new Intent(AllNewsPTel.this, NamasteTel.class);
                        f.putExtra("keyf",tf);
                        startActivity(f);
                        break;
                        case "namasthe hyderabad":
                            Intent g = new Intent(AllNewsPTel.this, NamasteTel.class);
                            g.putExtra("keyg",tg);
                            startActivity(g);
                        break;
                    case "deccan chronicle":
                        Intent sashi = new Intent(AllNewsPTel.this, AndhraBhumi.class);
                        sashi.putExtra("dc", deccanch);
                        startActivity(sashi);
                        break;
                    case "the hans":
                        Intent haa = new Intent(AllNewsPTel.this, HansInd.class);
                        haa.putExtra("hans", thi);
                        startActivity(haa);
                        break;
                    case "telangana today":
                        Intent teee = new Intent(AllNewsPTel.this, HansInd.class);
                        teee.putExtra("teltod", teltd);
                        startActivity(teee);
                        break;
                    case "indian express":
                        Intent dd = new Intent(AllNewsPTel.this, IndEP.class);
                        startActivity(dd);

                        break;
                    case "deccan herald":
                        Intent deccc = new Intent(AllNewsPTel.this, HansInd.class);
                        deccc.putExtra("decher", dchr);
                        startActivity(deccc);
                        break;
                    case "times of india":
                        openCustomTab(AllNewsPTel.this, Uri.parse("https://epaper.timesgroup.com"));

                        break;
                    case "arab news":
                        Intent da = new Intent(AllNewsPTel.this, TOI.class);
                        da.putExtra("nga",ane);
                        startActivity(da);
                        break;
                    case "financial express":
                        Intent db = new Intent(AllNewsPTel.this, TOI.class);
                        db.putExtra("ngb",fex);
                        startActivity(db);
                        break;
                    case "telegraph india":
                        Intent dc = new Intent(AllNewsPTel.this, TOI.class);
                        dc.putExtra("ngc",tie);
                        startActivity(dc);
                        break;

                    case "siasat":
                        //Intent sakshi = new Intent(getActivity(), Sakshi.class);
                        //startActivity(sakshi);
                        Intent sia = new Intent(AllNewsPTel.this, Siasat.class);
                        startActivity(sia);
                        break;


                    case "etemaad":
                        Intent de = new Intent(AllNewsPTel.this, Etemaad.class);
                        startActivity(de);                        break;
                    case "munsif":
                        Intent m = new Intent(AllNewsPTel.this, Munsif.class);
                        startActivity(m);
                        break;
                    case "sahara":
                        Intent s = new Intent(AllNewsPTel.this, Sahara.class);
                        s.putExtra("sah", saha);
                        startActivity(s);                        break;
                    case "rehnuma":
                        Intent r = new Intent(AllNewsPTel.this, Sahara.class);
                        r.putExtra("rah", reh);
                        startActivity(r);                           break;
                    case "sadae hussaini":
                        Intent dv = new Intent(AllNewsPTel.this, Sahara.class);
                        dv.putExtra("sh", sad);
                        startActivity(dv);                         break;
                        default:
                            Toast.makeText(AllNewsPTel.this, "An error occurred", Toast.LENGTH_SHORT).show();

                }

                /*if(id==0){
                    Intent sakshi = new Intent(AllNewsPTel.this, Sakshi.class);
                    startActivity(sakshi);

                }else if(id==1){
                    openCustomTab(AllNewsPTel.this, Uri.parse("https://epaper.eenadu.net"));
                }else if(id==2){
                    Intent namste = new Intent(AllNewsPTel.this, NamasteTel.class);
                    namste.putExtra("ntnews", nt);

                    startActivity(namste);
                }else if(id==3){
                    Intent anjy = new Intent(AllNewsPTel.this, NamasteTel.class);
                    anjy.putExtra("ajyothi", aj);
                    startActivity(anjy);
                }else if(id==4){
                    LayoutInflater inflater1 = LayoutInflater.from(AllNewsPTel.this);
                    final View view1 = inflater1.inflate(R.layout.custom_ab, null);
                    AlertDialog.Builder builder = new AlertDialog.Builder(AllNewsPTel.this);
                    builder.setView(view1);
                    //btn
                    Button btte = view1.findViewById(R.id.bute);
                    Button btap = view1.findViewById(R.id.buap);
                    final AlertDialog alertDialog = builder.create();

                    alertDialog.show();
                    btte.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent chah = new Intent(AllNewsPTel.this, AndhraBhumi.class);
                            chah.putExtra("abts", anbhst);
                            startActivity(chah);
                        }
                    });
                    btap.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent cah = new Intent(AllNewsPTel.this, AndhraBhumi.class);
                            cah.putExtra("abap", anbhsa);
                            startActivity(cah);
                        }
                    });
                }else if(id==5){
                    Intent vart = new Intent(AllNewsPTel.this, NamasteTel.class);
                    vart.putExtra("vartha", vt);
                    startActivity(vart);
                }else if(id==6){
                    Intent mana = new Intent(AllNewsPTel.this, NamasteTel.class);
                    mana.putExtra("manatel", mt);
                    startActivity(mana);
                }else if(id==7){
                    Intent nava = new Intent(AllNewsPTel.this, NamasteTel.class);
                    nava.putExtra("navatel", navt);
                    startActivity(nava);
                }else if(id==8){
                    Intent a = new Intent(AllNewsPTel.this, NamasteTel.class);
                    a.putExtra("keya",ta);
                    startActivity(a);
                }else if(id==9){
                    Intent b = new Intent(AllNewsPTel.this, NamasteTel.class);
                    b.putExtra("keyb",tb);
                    startActivity(b);
                }else if(id==10){
                    Intent c = new Intent(AllNewsPTel.this, NamasteTel.class);
                    c.putExtra("keyc",tc);
                    startActivity(c);
                }else if(id==11){
                    Intent d = new Intent(AllNewsPTel.this, NamasteTel.class);
                    d.putExtra("keyd",td);
                    startActivity(d);
                }else if(id==12){
                    Intent e = new Intent(AllNewsPTel.this, NamasteTel.class);
                    e.putExtra("keye",te);
                    startActivity(e);
                }else if(id==13){
                    Intent e = new Intent(AllNewsPTel.this, NamasteTel.class);
                    e.putExtra("keye",te);
                    startActivity(e);
                }else if(id==14){
                    Intent g = new Intent(AllNewsPTel.this, NamasteTel.class);
                    g.putExtra("keyg",tg);
                    startActivity(g);
                }else if(id==15){
                    Intent sashi = new Intent(AllNewsPTel.this, AndhraBhumi.class);
                    sashi.putExtra("dc", deccanch);
                    startActivity(sashi);
                }else if(id==16){
                    Intent haa = new Intent(AllNewsPTel.this, HansInd.class);
                    haa.putExtra("hans", thi);
                    startActivity(haa);
                }else if(id==17){
                    Intent teee = new Intent(AllNewsPTel.this, HansInd.class);
                    teee.putExtra("teltod", teltd);
                    startActivity(teee);
                }else if(id==18){
                    Intent dd = new Intent(AllNewsPTel.this, IndEP.class);
                    startActivity(dd);

                }else if(id==19){
                    Intent deccc = new Intent(AllNewsPTel.this, HansInd.class);
                    deccc.putExtra("decher", dchr);
                    startActivity(deccc);
                }else if(id==20){
                    openCustomTab(AllNewsPTel.this, Uri.parse("https://epaper.timesgroup.com"));

                }else if(id==21){
                    Intent sia = new Intent(AllNewsPTel.this, Siasat.class);
                    startActivity(sia);
                }else if(id==22){
                    Intent db = new Intent(AllNewsPTel.this, TOI.class);
                    db.putExtra("ngb",fex);
                    startActivity(db);
                }else if(id==23){
                    Intent dc = new Intent(AllNewsPTel.this, TOI.class);
                    dc.putExtra("ngc",tie);
                    startActivity(dc);
                }else if(id==24){
                    Intent sia = new Intent(AllNewsPTel.this, Siasat.class);
                    startActivity(sia);
                }else if(id==25){
                    Intent de = new Intent(AllNewsPTel.this, Etemaad.class);
                    startActivity(de);
                }else if(id==26){
                    Intent m = new Intent(AllNewsPTel.this, Munsif.class);
                    startActivity(m);
                }else if(id==27){
                    Intent s = new Intent(AllNewsPTel.this, Sahara.class);
                    s.putExtra("sah", saha);
                    startActivity(s);
                }else if(id==28){
                    Intent r = new Intent(AllNewsPTel.this, Sahara.class);
                    r.putExtra("rah", reh);
                    startActivity(r);
                }else if(id==29){
                    Intent dv = new Intent(AllNewsPTel.this, Sahara.class);
                    dv.putExtra("sh", sad);
                    startActivity(dv);
                }*/
            }
        });


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
    protected void onPause() {
        if(fs!=null){
            fs.clearSearchFocus();
        }
        super.onPause();
    }

    @Override
    protected void onStop() {
        if(fs!=null){
            fs.clearSearchFocus();
        }
        super.onStop();
    }

    private void prepareData() {
        liste=new  ArrayList<>();
        liste.add("sakshi"); //0
        liste.add("eenadu"); //1
        liste.add("namasthe telangana"); //2
        liste.add("andhrajyoti");  //3
        liste.add("andhrabhoomi"); //4
        liste.add("vartha"); //5
        liste.add("mana telangana"); //6
        liste.add("nava telangana"); //7
        liste.add("prabha news"); //8
        liste.add("praja shakti"); //9
        liste.add("surya"); //10
        liste.add("aadab hyderabad"); //11
        liste.add("telugu times"); //12
        liste.add("great andhra"); //13
        liste.add("namasthe hyderabad"); //14

        liste.add("deccan chronicle"); //15
        liste.add("the hans"); //15
        liste.add("telangana today"); //15
        liste.add("indian express"); //15
        liste.add("deccan herald"); //15
        liste.add("times of india"); //15
        liste.add("arab news"); //15
        liste.add("financial express"); //15
        liste.add("telegraph india"); //15

        liste.add("siasat"); //0
        liste.add("etemaad"); //1
        liste.add("munsif"); //2
        liste.add("sahara");  //3
        liste.add("rehnuma"); //4
        liste.add("sadae hussaini"); //5










    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem searchViewItem = menu.findItem(R.id.search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
             /*   if(list.contains(query)){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(MainActivity.this, "No Match found",Toast.LENGTH_LONG).show();
                }
                return false;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapteri.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }*/


}
