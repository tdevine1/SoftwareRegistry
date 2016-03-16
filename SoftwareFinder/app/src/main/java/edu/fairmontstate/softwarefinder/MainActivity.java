/* The main GUI when the application starts up.
 * Allows the selection of particular software to be found as well as a particular location.
 * Allows the option of submitting an update request form to the IT department of FSU for the particular software.
 */
package edu.fairmontstate.softwarefinder;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.view.View;
import android.content.Intent;
import java.util.*;

public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener, TextWatcher {
    Button searchSoftwareButton;
    Button searchBuilding_RoomButton;
    Button requestButton;
    AutoCompleteTextView softwareView;
    Spinner buildingSpinner;
    Spinner roomSpinner;
    ArrayAdapter<String> softwareAdapter;
    ArrayAdapter<String> buildingAdapter;
    ArrayAdapter<String> roomAdapter;
    String selectedBuildingItem;
    String selectedRoomItem;
    public static final String SOFTWARE_MSG = "edu.fairmontstate.softwarefinder.SOFTWARE_MSG";
    public static final String BUILDING_MSG = "edu.fairmontstate.softwarefinder.BUILDING_MSG";
    public static final String ROOM_MSG = "edu.fairmontstate.softwarefinder.ROOM_MSG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // Initialize the activity.
        setContentView(R.layout.activity_main); // Set the activity content from the activity_main layout resource.

        searchSoftwareButton = (Button)this.findViewById(R.id.softwareButton);
        searchBuilding_RoomButton = (Button)this.findViewById(R.id.building_roomButton);
        requestButton = (Button)this.findViewById(R.id.updateRequestButton);

        searchSoftwareButton.setEnabled(false);
        requestButton.setEnabled(false);

        softwareView = (AutoCompleteTextView)this.findViewById(R.id.softwareAutoComplete);
        buildingSpinner = (Spinner)this.findViewById(R.id.buildingSpinner);
        roomSpinner = (Spinner)this.findViewById(R.id.roomSpinner);

        softwareView.addTextChangedListener(this);
        buildingSpinner.setOnItemSelectedListener(this);
        roomSpinner.setOnItemSelectedListener(this);

        populateAutoCompleteField();
        populateBuildingSpinner();
        populateRoomSpinner();

    } // end method onCreate().
//================================================================================================================================
    // Method that populates the softwareSpinner with data.
    public void populateAutoCompleteField() {
        Vector<String> itemList = new Vector<String>();

        itemList.addElement("Microsoft Office 2011");   // test data.
        itemList.addElement("Microsoft Office 2013");
        itemList.addElement("Visual Studio 2008");
        itemList.addElement("Adobe Reader");

        softwareAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, itemList);
        softwareView.setAdapter(softwareAdapter);
    } // end method populateAutoCompleteField().
//================================================================================================================================
    // Method that populates the buildingSpinner with data.
    public void populateBuildingSpinner() {
        Vector<String> itemList = new Vector<String>();

        itemList.addElement("Engineering & Technology Building");   // test data.
        itemList.addElement("Library");
        itemList.addElement("Falcon Center");

        buildingAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, itemList);
        buildingSpinner.setAdapter(buildingAdapter);
    } // end method populateBuildingSpinner().
//================================================================================================================================
    // Method that populates the roomSpinner with data.
    public void populateRoomSpinner() {
        Vector<String> itemList = new Vector<String>();

        itemList.addElement("110");     // test data.
        itemList.addElement("213");
        itemList.addElement("302");
        itemList.addElement("417");

        roomAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, itemList);
        roomSpinner.setAdapter(roomAdapter);
    } // end method populateRoomSpinner().
//================================================================================================================================
    // Method that invokes the SoftwareActivity when the search software button is clicked.
    public void softwareButtonClicked(View v) {
        Intent intent;
        String selectedSoftware;

        selectedSoftware = softwareView.getText().toString().trim();
        intent = new Intent(this, SoftwareActivity.class);
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(SOFTWARE_MSG, selectedSoftware);
        intent.setType("text/plain");
        startActivity(intent);
    } // end method softwareButtonClicked().
//================================================================================================================================
    // Method that invokes the LocationActivity when the search building & room button is clicked.
    public void building_roomButtonClicked(View v) {
        Intent intent;

        intent = new Intent(this, LocationActivity.class);
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(BUILDING_MSG, selectedBuildingItem);
        intent.putExtra(ROOM_MSG, selectedRoomItem);
        intent.setType("text/plain");
        startActivity(intent);
    } // end method building_roomButtonClicked().
//================================================================================================================================
    // Method that invokes the RequestFormActivity when the submit update request button is clicked.
    public void updateRequestButtonClicked(View v) {
        Intent intent;
        String selectedSoftware;

        selectedSoftware = softwareView.getText().toString().trim();
        intent = new Intent(this, RequestFormActivity.class);
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(SOFTWARE_MSG, selectedSoftware);
        intent.putExtra(BUILDING_MSG, selectedBuildingItem);
        intent.putExtra(ROOM_MSG, selectedRoomItem);
        intent.setType("text/plain");
        startActivity(intent);
    } // end method updateRequestButtonClicked().
//================================================================================================================================
    // Method that listens for items selected in any of the spinners.
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        String selectedItem;

        selectedItem = parent.getItemAtPosition(pos).toString().trim();
        if (buildingAdapter.getPosition(selectedItem) >= 0)
            selectedBuildingItem = selectedItem;
        else if (roomAdapter.getPosition(selectedItem) >= 0)
            selectedRoomItem = selectedItem;
    } // end method onItemSelected().
//================================================================================================================================
    public void onNothingSelected(AdapterView<?> parent) {} // end method onNothingSelected().
//================================================================================================================================
    // Method that validates the input of the auto-complete field and enables the button if the field content is valid.
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        boolean found = false;
        int index = 0;
        String fieldItem;
        String listItem;

        fieldItem = softwareView.getText().toString().trim();
        while (!found && index < softwareAdapter.getCount()) {
            listItem = softwareAdapter.getItem(index).trim();
            if (fieldItem.equals(listItem)) {
                searchSoftwareButton.setEnabled(true);
                requestButton.setEnabled(true);
                found = true;
            }
            else {
                searchSoftwareButton.setEnabled(false);
                requestButton.setEnabled(false);
                index++;
            }
        }
    } // end method onTextChanged().
//================================================================================================================================
    public void afterTextChanged(Editable s) {} // end method afterTextChanged().
//================================================================================================================================
    public void beforeTextChanged(CharSequence s, int start, int before, int count) {} // end method beforeTextChanged().
} // end class MainActivity.
