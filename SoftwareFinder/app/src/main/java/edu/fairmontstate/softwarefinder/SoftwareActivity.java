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

public class SoftwareActivity extends ActionBarActivity {
    public final String locationQueryLink = "http://fsu-software-finder.net16.net/softwareLocationQuery.php?softwareName=";
    Toolbar toolbar;
    Drawable backArrow;
    Intent intent;
    TextView textView;
    ListView listView;
    String itemSelected;
    ArrayAdapter<String> arrayAdapter;
    LocationQuery locationQuery;

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

        textView.setText("Software " + itemSelected + " Located In:");

        // Replace all spaces with '%20' for URL syntax.
        itemSelected = itemSelected.replace(" ", "%20");

        locationQuery = new LocationQuery(this, arrayAdapter, listView);
        locationQuery.execute(locationQueryLink + itemSelected);
    } // end method onCreate().
} // end class SoftwareActivity.
