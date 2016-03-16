package edu.fairmontstate.softwarefinder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.widget.TextView;

public class SoftwareActivity extends AppCompatActivity {
    Intent intent;
    TextView textView;
    String itemSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_software);

        intent = getIntent();
        itemSelected = intent.getStringExtra(MainActivity.SOFTWARE_MSG);

        textView = (TextView)findViewById(R.id.locationLabel);

        textView.setText(itemSelected);
    } // end method onCreate().
} // end class SoftwareActivity.
