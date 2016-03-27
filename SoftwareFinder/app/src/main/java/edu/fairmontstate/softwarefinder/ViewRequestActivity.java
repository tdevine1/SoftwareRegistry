/* Activity that displays a list of requests that the user has submitted to the IT dept.
 * using a custom list layout.
 */
package edu.fairmontstate.softwarefinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class ViewRequestActivity extends AppCompatActivity {
    Intent intent;
    ListView listView;
    ArrayList<SoftwarePoint> requestList;
    ArrayAdapter<String> arrayAdapter;
    String[] values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_request);

        intent = getIntent();

        listView = (ListView)findViewById(R.id.listView);
        requestList = (ArrayList<SoftwarePoint>)intent.getSerializableExtra(MainActivity.REQUEST_MSG);  // the intent sent is a Vector, but getSerializableExtra returns ArrayList.
        values = new String[requestList.size()];

        for (int i = 0; i < values.length; i++) {
            values[i] = requestList.get(i).getSoftwareName() + '\n' + requestList.get(i).getBuildingName() + " " + requestList.get(i).getRoomNumber();
        }

        arrayAdapter = new ArrayAdapter<String>(this, R.layout.custom_list_view, values);
        listView.setAdapter(arrayAdapter);
    } // end method onCreate().
} // end class ViewRequestActivity.
