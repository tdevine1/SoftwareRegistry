/* Activity that displays a list of requests that the user has submitted to the IT dept.
 * using a custom list layout.
 */
package edu.fairmontstate.softwarefinder;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class ViewRequestActivity extends ActionBarActivity {
    Toolbar toolbar;
    Drawable backArrow;
    Intent intent;
    ListView listView;
    ArrayList<SoftwarePoint> requestList;
    ArrayAdapter<String> arrayAdapter;
    String[] values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_request);

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

        listView = (ListView)findViewById(R.id.listView);
        requestList = (ArrayList<SoftwarePoint>)intent.getSerializableExtra(MainActivity.REQUEST_MSG);  // the intent sent is a Vector, but getSerializableExtra returns ArrayList.
        values = new String[requestList.size()];

        for (int i = 0; i < values.length; i++) {
            values[i] = requestList.get(i).getSoftwareName() + '\n' + requestList.get(i).getRoomNumber() + " " + requestList.get(i).getBuildingName();
        }

        arrayAdapter = new ArrayAdapter<String>(this, R.layout.custom_list_view, values);
        listView.setAdapter(arrayAdapter);
    } // end method onCreate().
} // end class ViewRequestActivity.
