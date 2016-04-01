/* The main GUI when the application starts up.
 * Allows the selection of particular software to be found as well as a particular location.
 * Allows the option of submitting an update request form to the IT department of FSU for the particular software,
 * as well as allowing the user to view their request history.
 */
package edu.fairmontstate.softwarefinder;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.*;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.widget.*;
import android.view.View;
import android.content.Intent;
import android.widget.Toolbar;

import java.io.*;
import java.util.*;

public class MainActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener, TextWatcher {
    android.support.v7.widget.Toolbar toolbar;
    Button searchSoftwareButton;
    Button searchBuilding_RoomButton;
    Button requestButton;
    Button requestHistoryButton;
    AutoCompleteTextView softwareView;
    Spinner buildingSpinner;
    Spinner roomSpinner;
    ArrayAdapter<String> softwareAdapter;
    ArrayAdapter<String> buildingAdapter;
    ArrayAdapter<String> roomAdapter;
    String selectedBuildingItem;
    String selectedRoomItem;
    File propertyFile;
    public static final String SOFTWARE_MSG = "edu.fairmontstate.softwarefinder.SOFTWARE_MSG";
    public static final String BUILDING_MSG = "edu.fairmontstate.softwarefinder.BUILDING_MSG";
    public static final String ROOM_MSG = "edu.fairmontstate.softwarefinder.ROOM_MSG";
    public static final String REQUEST_MSG = "edu.fairmontstate.softwarefinder.REQUEST_MSG";
    SoftwarePoint softwarePoint;
    Vector<SoftwarePoint> requestList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // Initialize the activity.
        setContentView(R.layout.activity_main); // Set the activity content from the activity_main layout resource.

        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Software Finder");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        searchSoftwareButton = (Button)this.findViewById(R.id.softwareButton);
        searchBuilding_RoomButton = (Button)this.findViewById(R.id.building_roomButton);
        requestButton = (Button)this.findViewById(R.id.updateRequestButton);
        requestHistoryButton = (Button)this.findViewById(R.id.requestHistoryButton);

        searchSoftwareButton.setEnabled(false);

        softwareView = (AutoCompleteTextView)this.findViewById(R.id.softwareAutoComplete);
        softwareView.setInputType(InputType.TYPE_CLASS_TEXT);
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

        itemList.addElement("<Select Building>");
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

        itemList.addElement("<Select Room#>");
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
    /* Method that uses a SoftwarePoint object that stores the values of the software and building/room.
     * Stores the object in a file native to the Android device, and will check whether the data is already contained in the file.
     */
    public void updateRequestButtonClicked(View v) throws IOException, ClassNotFoundException{
        DialogFragment dialog;

        propertyFile = new File(getFilesDir(), "info.txt");
        if (!fieldsComplete()) {
            if (propertyFile.length() > 0) {
                readFile();
                if (!alreadySubmitted()) {
                    writeSubmissionToFile();
                    dialog = new AlertMessage("Thank you!", "Thank you for submitting your update request form!" +
                            " Your request will be approved by the IT Dept. for further review.");
                    dialog.show(getFragmentManager(), "AlertMessageFragmentTag");
                } else {
                    dialog = new AlertMessage("Already submitted.", "Your update request has already been submitted to the IT Dept.");
                    dialog.show(getFragmentManager(), "AlertMessageFragmentTag");
                }
            } else {
                writeSubmissionToFile();
                dialog = new AlertMessage("Thank you!", "Thank you for submitting your update request form!" +
                        " Your request will be approved by the IT Dept. for further review.");
                dialog.show(getFragmentManager(), "AlertMessageFragmentTag");
            }
        }
        else {
            dialog = new AlertMessage("Fields incomplete.", "All fields must have a value before submitting a request.");
            dialog.show(getFragmentManager(), "AlertMessageFragmentTag");
        }
        //propertyFile.delete();
    } // end method updateRequestButtonClicked().
//================================================================================================================================
    // Method that invokes the ViewRequestActivity when the view request button is clicked.
    public void viewRequestHistory(View v) throws IOException, ClassNotFoundException {
        Intent intent;
        DialogFragment dialog;

        intent = new Intent(this, ViewRequestActivity.class);
        intent.setAction(Intent.ACTION_SEND);

        propertyFile = new File(getFilesDir(), "info.txt");
        if (propertyFile.length() > 0) {
            readFile();
            intent.putExtra(REQUEST_MSG, requestList);
            intent.setType("text/plain");
            startActivity(intent);
        }
        else {
            dialog = new AlertMessage("No Request History", "You don't have a history of requests.");
            dialog.show(getFragmentManager(), "AlertMessageFragmentTag");
        }
    } // end method viewRequestHistory().
//================================================================================================================================
    // Method that reads from the file and stores the data into the request list.
    @SuppressWarnings("unchecked")
    public void readFile() throws IOException, ClassNotFoundException {
        FileInputStream fis;
        ObjectInputStream ois;
        requestList = new Vector<SoftwarePoint>();

        fis = openFileInput(propertyFile.getName());
        ois = new ObjectInputStream(fis);
        try {
            while (true) {
                softwarePoint = (SoftwarePoint)ois.readObject();
                requestList.addElement(softwarePoint);
            }
        }
        catch (EOFException eof) {
            // end of file reached, data has been populated accordingly.
        }
        finally {
            ois.close();
        }
    } // end method readFile().
//================================================================================================================================
    // Method that checks whether or not the fields are completely filled so that a request can be submitted.
    public boolean fieldsComplete() {

        if (!softwareView.getText().toString().equals("") && !selectedBuildingItem.equals("<Select Building>") && !selectedRoomItem.equals("<Select Room#>"))
            return false;
        else
            return true;
    } // end method isValidRequest().
//================================================================================================================================
    // Method that checks whether or not the software/building/room# request is already in the file.
    @SuppressWarnings("unchecked")
    public boolean alreadySubmitted() {
        String softwareName;
        String buildingName;
        String roomNumber;

        softwareName = softwareView.getText().toString().trim();
        buildingName = selectedBuildingItem.trim();
        roomNumber = selectedRoomItem.trim();

        for (SoftwarePoint s : requestList) {
            if (s.getSoftwareName().equals(softwareName) && s.getBuildingName().equals(buildingName) && s.getRoomNumber().equals(roomNumber)) {
                return true;
            }
        }
        return false;
    } // end method checkIfAlreadySubmitted().
//================================================================================================================================
    // Method that stores the field data in the appropriate SoftwarePoint and stores the SoftwarePoint to the file.
    public void writeSubmissionToFile() throws IOException {
        FileOutputStream fos;
        SoftwareOutputStream sos;
        ObjectOutputStream oos;
        String softwareName;
        String buildingName;
        String roomNumber;

        softwarePoint = new SoftwarePoint();
        softwareName = softwareView.getText().toString().trim();
        buildingName = selectedBuildingItem.trim();
        roomNumber = selectedRoomItem.trim();

        softwarePoint.setSoftwareName(softwareName);
        softwarePoint.setBuildingName(buildingName);
        softwarePoint.setRoomNumber(roomNumber);

        fos = openFileOutput(propertyFile.getName(), Context.MODE_APPEND);
        if (propertyFile.length() > 0) {
            sos = new SoftwareOutputStream(fos);
            sos.writeObject(softwarePoint);
            sos.close();
        }
        else {
            oos = new ObjectOutputStream(fos);
            oos.writeObject(softwarePoint);
            oos.close();
        }
    } // end method writeSubmissionToFile().
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
            if (fieldItem.toUpperCase().equals(listItem.toUpperCase())) {
                searchSoftwareButton.setEnabled(true);
                found = true;
            }
            else {
                searchSoftwareButton.setEnabled(false);
                index++;
            }
        }
    } // end method onTextChanged().
//================================================================================================================================
    public void afterTextChanged(Editable s) {} // end method afterTextChanged().
//================================================================================================================================
    public void beforeTextChanged(CharSequence s, int start, int before, int count) {} // end method beforeTextChanged().
} // end class MainActivity.
