/* The main GUI when the application starts up.
 * Allows the selection of particular software to be found as well as a particular location.
 * Allows the option of submitting an update request form to the IT department of FSU for the particular software.
 */
package edu.fairmontstate.softwarefinder;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.view.View;
import android.content.Intent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
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
    File propertyFile;
    public static final String SOFTWARE_MSG = "edu.fairmontstate.softwarefinder.SOFTWARE_MSG";
    public static final String BUILDING_MSG = "edu.fairmontstate.softwarefinder.BUILDING_MSG";
    public static final String ROOM_MSG = "edu.fairmontstate.softwarefinder.ROOM_MSG";
    HashMap<String, List<String>> softwareMap;
    HashMap<String, List<String>> buildingMap;
    HashMap<String, List<String>> roomMap;

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
    /* Uses a map that stores the key value pairs of the software and building/room.
     * Stores the map in a file native to the Android device, and will check whether the data is already contained in the file.
     */
    public void updateRequestButtonClicked(View v) {
        //Intent intent;
        DialogFragment dialog;

        propertyFile = new File(getFilesDir(), "info.txt");
        if (propertyFile.length() > 0) {
            if (!checkIfAlreadySubmitted()) {
                writeSubmissionToFile();
                dialog = new AlertMessage("Thank you!", "Thank you for submitting your update request form!" +
                                                        " Your request will be approved by the IT Dept. for further review.");
                dialog.show(getFragmentManager(), "AlertMessageFragmentTag");
            }
            else {
                dialog = new AlertMessage("Already submitted.", "Your update request has already been submitted to the IT Dept.");
                dialog.show(getFragmentManager(), "AlertMessageFragmentTag");
            }

        }
        else {
            writeSubmissionToFile();
            dialog = new AlertMessage("Thank you!", "Thank you for submitting your update request form!" +
                    " Your request will be approved by the IT Dept. for further review.");
            dialog.show(getFragmentManager(), "AlertMessageFragmentTag");
        }
        //intent = new Intent(this, RequestFormActivity.class);         // Will use intent if more fields need to be populated
        //intent.setAction(Intent.ACTION_SEND);
        //intent.putExtra(SOFTWARE_MSG, selectedSoftware);
        //intent.putExtra(BUILDING_MSG, selectedBuildingItem);
        //intent.putExtra(ROOM_MSG, selectedRoomItem);
        //intent.setType("text/plain");
        //startActivity(intent);
    } // end method updateRequestButtonClicked().
//================================================================================================================================
    // Method that reads from the file and checks whether or not the software/building/room# request is already in the file.
    @SuppressWarnings("unchecked")
    public boolean checkIfAlreadySubmitted() {
        boolean submitted = false;
        FileInputStream fis;
        ObjectInputStream ois;
        List<String> softwareList;
        List<String> buildingList;
        List<String> roomList;

        try {
            //fis = new FileInputStream(propertyFile);
            fis = openFileInput(propertyFile.getName());
            ois = new ObjectInputStream(fis);
            softwareMap = (HashMap<String, List<String>>) ois.readObject();
            buildingMap = (HashMap<String, List<String>>) ois.readObject();
            roomMap = (HashMap<String, List<String>>) ois.readObject();
            ois.close();

            softwareList = softwareMap.get("Software");
            buildingList = buildingMap.get("Building");
            roomList = roomMap.get("Room");


            if (softwareList.contains(softwareView.getText().toString().trim()) && buildingList.contains(selectedBuildingItem) && roomList.contains(selectedRoomItem)) {
                submitted = true;
            }
            else {
                submitted = false;
            }

        }
        catch (FileNotFoundException fne) {
            fne.printStackTrace();
        }
        catch (StreamCorruptedException sce) {
            sce.printStackTrace();
        }
        catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return submitted;
    } // end method checkIfAlreadySubmitted().
//================================================================================================================================
    // Method that stores the field data in the appropriate hashmap and stores the hashmap to the file.
    public void writeSubmissionToFile() {
        List<String> softwareList;
        List<String> buildingList;
        List<String> roomList;
        String softwareKey = "Software";
        String buildingKey = "Building";
        String roomKey = "Room";
        FileOutputStream fos;
        ObjectOutputStream oos;

        softwareMap = new HashMap<String, List<String>>();
        buildingMap = new HashMap<String, List<String>>();
        roomMap = new HashMap<String, List<String>>();

        softwareList = softwareMap.get(softwareKey);
        buildingList = buildingMap.get(buildingKey);
        roomList = roomMap.get(roomKey);

        if (softwareList == null) {
            softwareList = new ArrayList<String>();
            softwareMap.put(softwareKey, softwareList);
        }
        if (buildingList == null) {
            buildingList = new ArrayList<String>();
            buildingMap.put(buildingKey, buildingList);
        }
        if (roomList == null) {
            roomList = new ArrayList<String>();
            roomMap.put(roomKey, roomList);
        }

        softwareList.add(softwareView.getText().toString().trim());
        buildingList.add(selectedBuildingItem);
        roomList.add(selectedRoomItem);
        try {
            fos = openFileOutput(propertyFile.getName(), Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(softwareMap);
            oos.writeObject(buildingMap);
            oos.writeObject(roomMap);
            oos.close();
        }
        catch (FileNotFoundException fne) {
            fne.printStackTrace();
        }
        catch (StreamCorruptedException sce) {
            sce.printStackTrace();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
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
