package com.izhandroid.newsbox.ts.english;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.snackbar.Snackbar;
import com.izhandroid.newsbox.ts.R;
import com.izhandroid.newsbox.ts.telugu.AndhraBhumi;

import java.util.ArrayList;
import java.util.List;

public class IndEP extends AppCompatActivity {
List<String> list;
    String iehy="http://epaper.newindianexpress.com/t/3381";
ListView listView;
    String iewa="http://epaper.newindianexpress.com/t/8426";
    String ievj="http://epaper.newindianexpress.com/t/3464";
    String ievs="http://epaper.newindianexpress.com/t/3463";
    String ieti="http://epaper.newindianexpress.com/t/3511";
    String iean="http://epaper.newindianexpress.com/t/8680";
    String ieta="http://epaper.newindianexpress.com/t/8681";
    String ie="http://epaper.newindianexpress.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ind_ep);

        dprepare();


        listView = findViewById(R.id.listofexpress);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(IndEP.this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        //Intent sakshi = new Intent(getActivity(), Sakshi.class);
                        //startActivity(sakshi);
                        Intent sashi = new Intent(IndEP.this, HansInd.class);

                        sashi.putExtra("iexhy", iehy);
                        startActivity(sashi);
                        break;


                    case 1:
                        Intent sashis = new Intent(IndEP.this, HansInd.class);
                        sashis.putExtra("iexwa", iewa);
                        startActivity(sashis);
                        break;
                    case 2:
                        Intent sash = new Intent(IndEP.this, HansInd.class);
                        sash.putExtra("iexvj", ievj);
                        startActivity(sash);
                        break;
                    case 3:
                        Intent sasdh = new Intent(IndEP.this, HansInd.class);
                        sasdh.putExtra("iexvs", ievs);
                        startActivity(sasdh);                        break;
                    case 4:
                        Intent asash = new Intent(IndEP.this, HansInd.class);
                        asash.putExtra("iexti", ieti);
                        startActivity(asash);
                        break;
                    case 5:

                        Intent bsash = new Intent(IndEP.this, HansInd.class);
                        bsash.putExtra("iexan", iean);
                        startActivity(bsash);
                        break;
                    case 6:

                        Intent ssash = new Intent(IndEP.this, HansInd.class);
                        ssash.putExtra("iexta", ieta);
                        startActivity(ssash);
                        break;

                    case 7:

                        Intent absash = new Intent(IndEP.this, HansInd.class);
                        absash.putExtra("iex", ie);
                        startActivity(absash);
                        break;
                }
            }
        });

    }

    private void dprepare() {
        list = new ArrayList<>();
        list.add("Hyderabad");
        list.add("Warangal");
        list.add("Vijaywada");
        list.add("Vishakapatnam");
        list.add("Tirupati");
        list.add("Anantapur");
        list.add("Tadepalligudem");
        list.add("ALL EDITIONS");

    }
}
