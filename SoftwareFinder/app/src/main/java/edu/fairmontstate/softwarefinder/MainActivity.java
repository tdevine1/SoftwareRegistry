/* Simple test program to get used to the software.
 */
package edu.fairmontstate.softwarefinder;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.view.View;
import java.util.*;

public class MainActivity extends Activity {
    Button searchSoftwareButton;
    Button searchBuildingButton;
    Button searchRoomButton;
    Button requestButton;
    Spinner softwareSpinner;
    Spinner buildingSpinner;
    Spinner roomSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // Initialize the activity.
        setContentView(R.layout.activity_main); // Set the activity content from the activity_main layout resource.

        searchSoftwareButton = (Button)this.findViewById(R.id.softwareButton);
        searchBuildingButton = (Button)this.findViewById(R.id.buildingButton);
        searchRoomButton = (Button)this.findViewById(R.id.roomButton);
        requestButton = (Button)this.findViewById(R.id.updateRequestButton);

        softwareSpinner = (Spinner)this.findViewById(R.id.softwareSpinner);
        buildingSpinner = (Spinner)this.findViewById(R.id.buildingSpinner);
        roomSpinner = (Spinner)this.findViewById(R.id.roomSpinner);

        populateSoftwareSpinner();
        populateBuildingSpinner();
        populateRoomSpinner();
        registerButtonClick();
        registerButtonClick();
        registerButtonClick();

    }
//================================================================================================================================
    // Method that registers the appropriate button command.
    public void registerButtonClick() {

        try {
            searchSoftwareButton.setOnClickListener(new Button.OnClickListener() {  // Add a button listener by constructing a concrete instance of
                public void onClick(View view) {    // the abstract class Button.OnClickListener and overriding the method onClick(View v)

                }
            });
            searchBuildingButton.setOnClickListener(new Button.OnClickListener() {  // Add a button listener by constructing a concrete instance of
                public void onClick(View view) {    // the abstract class Button.OnClickListener and overriding the method onClick(View v)

                }
            });
            searchRoomButton.setOnClickListener(new Button.OnClickListener() {  // Add a button listener by constructing a concrete instance of
                public void onClick(View view) {    // the abstract class Button.OnClickListener and overriding the method onClick(View v)

                }
            });
            requestButton.setOnClickListener(new Button.OnClickListener() {  // Add a button listener by constructing a concrete instance of
                public void onClick(View view) {    // the abstract class Button.OnClickListener and overriding the method onClick(View v)

                }
            });
        }
        catch (NullPointerException npe) {
            npe.printStackTrace();
        }
    }
//================================================================================================================================
    // Method that populates the softwareSpinner with data.
    public void populateSoftwareSpinner() {
        Vector<String> itemList = new Vector<String>();
        ArrayAdapter<String> adapter;

        for (int i = 1; i <= 10; i++) {
            itemList.addElement("Item " + i);
        }
       adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, itemList);
       softwareSpinner.setAdapter(adapter);
    }
//================================================================================================================================
    // Method that populates the buildingSpinner with data.
    public void populateBuildingSpinner() {
        Vector<String> itemList = new Vector<String>();
        ArrayAdapter<String> adapter;

        for (int i = 1; i <= 10; i++) {
            itemList.addElement("Item " + i);
        }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, itemList);
        buildingSpinner.setAdapter(adapter);
    }
//================================================================================================================================
    // Method that populates the roomSpinner with data.
    public void populateRoomSpinner() {
        Vector<String> itemList = new Vector<String>();
        ArrayAdapter<String> adapter;

        for (int i = 1; i <= 10; i++) {
            itemList.addElement("Item " + i);
        }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, itemList);
        roomSpinner.setAdapter(adapter);
    }
}
