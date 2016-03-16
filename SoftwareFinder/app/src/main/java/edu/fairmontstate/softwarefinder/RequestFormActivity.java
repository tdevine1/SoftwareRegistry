package edu.fairmontstate.softwarefinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class RequestFormActivity extends AppCompatActivity {
    Intent intent;
    TextView softwareText;
    TextView locationText;
    String softwareItem;
    String buildingItem;
    String roomItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_form);

        intent = getIntent();
        softwareItem = intent.getStringExtra(MainActivity.SOFTWARE_MSG);
        buildingItem = intent.getStringExtra(MainActivity.BUILDING_MSG);
        roomItem = intent.getStringExtra(MainActivity.ROOM_MSG);

        softwareText = (TextView)findViewById(R.id.softwareText);
        locationText = (TextView)findViewById(R.id.locationText);

        softwareText.setText(softwareItem);
        locationText.setText(buildingItem + " " + roomItem);
    } // end method onCreate().
} // end class RequestFormActivity.
