package edu.fairmontstate.softwarefinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class LocationActivity extends AppCompatActivity {
    Intent intent;
    TextView textView;
    String buildingItem;
    String roomItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        intent = getIntent();
        buildingItem = intent.getStringExtra(MainActivity.BUILDING_MSG);
        roomItem = intent.getStringExtra(MainActivity.ROOM_MSG);

        textView = (TextView)findViewById(R.id.locationLabel);

        textView.setText(buildingItem + " " + roomItem);
    } // end method onCreate().
} // end class LocationActivity.
