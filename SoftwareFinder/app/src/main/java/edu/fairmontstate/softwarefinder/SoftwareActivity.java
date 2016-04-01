package edu.fairmontstate.softwarefinder;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class SoftwareActivity extends ActionBarActivity {
    Toolbar toolbar;
    Drawable backArrow;
    Intent intent;
    TextView textView;
    String itemSelected;

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

        textView = (TextView)findViewById(R.id.locationLabel);

        textView.setText(itemSelected);
    } // end method onCreate().
} // end class SoftwareActivity.
