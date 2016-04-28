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

public class LocationActivity extends ActionBarActivity {
    public final String softwareQueryLink = "http://fsu-software-finder.net16.net/locationSoftwareQuery.php?buildingName=";
    Toolbar toolbar;
    Drawable backArrow;
    Intent intent;
    TextView textView;
    String buildingItem;
    String roomItem;
    ArrayAdapter<String> arrayAdapter;
    ListView listView;
    SoftwareLocatedQuery softwareLocatedQuery;

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

        textView = (TextView)findViewById(R.id.titleViewLocation);
        listView = (ListView)findViewById(R.id.listViewLocation);

        textView.setText("Software in Location" +  ": " + buildingItem + " " + roomItem);

        // Replace all spaces with '%20' for URL syntax.
        buildingItem = buildingItem.replace(" ", "%20");
        roomItem = roomItem.replace(" ", "%20");

        softwareLocatedQuery = new SoftwareLocatedQuery(this, arrayAdapter, listView);
        softwareLocatedQuery.execute(softwareQueryLink + buildingItem + "&roomNumber=" + roomItem);
    } // end method onCreate().
} // end class LocationActivity.
