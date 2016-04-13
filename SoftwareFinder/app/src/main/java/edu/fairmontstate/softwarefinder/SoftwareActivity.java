package edu.fairmontstate.softwarefinder;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Vector;

public class SoftwareActivity extends ActionBarActivity {
    Toolbar toolbar;
    Drawable backArrow;
    Intent intent;
    TextView textView;
    ListView listView;
    String itemSelected;
    Vector<String> locationList;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_software);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Software Finder");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        backArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        backArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(backArrow);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        intent = getIntent();
        itemSelected = intent.getStringExtra(MainActivity.SOFTWARE_MSG);

        textView = (TextView)findViewById(R.id.titleViewSoftware);
        listView = (ListView)findViewById(R.id.listViewSoftware);

        locationList = new Vector<String>();

        if (itemSelected.equals("Visual Studio 2008")) {
            locationList.addElement("110 Engineering & Technology Building");
            locationList.addElement("Library 1st floor");
            locationList.addElement("Library 2nd floor");
            locationList.addElement("417 Engineering & Technology Building");
        }
        else if (itemSelected.equals("Microsoft Office 2010")) {
            locationList.addElement("Library 1st floor");
            locationList.addElement("Library 2nd floor");
            locationList.addElement("204 Engineering & Technology Building");
        }
        else if (itemSelected.equals("Microsoft Office 2007")) {
            locationList.addElement("Library 1st floor");
            locationList.addElement("Library 2nd floor");
            locationList.addElement("204 Engineering & Technology Building");
        }
        else if (itemSelected.equals("Adobe Reader")) {
            locationList.addElement("Library 1st floor");
            locationList.addElement("Library 2nd floor");
            locationList.addElement("204 Engineering & Technology Building");
            locationList.addElement("208 Engineering & Technology Building");
        }

        arrayAdapter = new ArrayAdapter<String>(this, R.layout.custom_list_view, locationList);
        textView.setText("Software " + itemSelected + " Located In:");
        listView.setAdapter(arrayAdapter);
    } // end method onCreate().
} // end class SoftwareActivity.
