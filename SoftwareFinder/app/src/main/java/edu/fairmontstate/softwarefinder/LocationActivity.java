package edu.fairmontstate.softwarefinder;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Vector;

public class LocationActivity extends ActionBarActivity {
    Toolbar toolbar;
    Drawable backArrow;
    Intent intent;
    TextView textView;
    String buildingItem;
    String roomItem;
    Vector<String> softwareList;
    ArrayAdapter<String> arrayAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

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
        buildingItem = intent.getStringExtra(MainActivity.BUILDING_MSG);
        roomItem = intent.getStringExtra(MainActivity.ROOM_MSG);

        softwareList = new Vector<String>();

        if (buildingItem.equals("Engineering & Technology Building") && roomItem.equals("110")) {
            softwareList.addElement("Adobe Reader");
            softwareList.addElement("Microsoft Office 2010");
            softwareList.addElement("Visual Studio 2008");
        }
        if (buildingItem.equals("Engineering & Technology Building") && roomItem.equals("302")) {
            softwareList.addElement("Adobe Reader");
            softwareList.addElement("Microsoft Office 2010");
            softwareList.addElement("Visual Studio 2008");
        }
        if (buildingItem.equals("Library") && roomItem.equals("213")) {
            softwareList.addElement("Adobe Reader");
            softwareList.addElement("Microsoft Office 2007");
            softwareList.addElement("Microsoft Office 2010");
            softwareList.addElement("Visual Studio 2008");
        }

        arrayAdapter = new ArrayAdapter<String>(this, R.layout.custom_list_view, softwareList);
        textView = (TextView)findViewById(R.id.titleViewLocation);
        listView = (ListView)findViewById(R.id.listViewLocation);
        textView.setText("Software in Location" +  ": " + buildingItem + " " + roomItem);
        listView.setAdapter(arrayAdapter);
    } // end method onCreate().
} // end class LocationActivity.
