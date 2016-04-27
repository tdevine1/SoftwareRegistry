/* Class that handles execution of the query to get the software names.
 *
 */
package edu.fairmontstate.softwarefinder;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.net.*;
import java.io.*;
import java.util.Vector;

public class SoftwareQuery extends AsyncTask<String, Void, Vector<String>> {
    Context context;
    ArrayAdapter<String> softwareAdapter;
    AutoCompleteTextView softwareView;
    URL url;
    URLConnection conn;
    BufferedReader br;
    String line;
    StringBuilder sb;
    String splitStr;
    Vector<String> itemList;
    int index;
    String placeHolder;

    public SoftwareQuery(Context context, ArrayAdapter<String> softwareAdapter, AutoCompleteTextView softwareView) {
        try {
            this.context = context;
            this.softwareAdapter = softwareAdapter;
            this.softwareView = softwareView;
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // end constructor.
//================================================================================================================================
    // Method to get the output of the php execution from the link.
    @Override
    protected Vector<String> doInBackground(String... urls) {

        try {
            url = new URL(urls[0]);
            conn = url.openConnection();
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            sb = new StringBuilder();
            itemList = new Vector<String>();

            while ((line = br.readLine()) != null) {
                if (!line.equals("")) {
                    sb.append(line);
                }
            }
            splitStr = sb.toString();

            while (index < splitStr.length()) {
                if (splitStr.charAt(index) != '$') {
                    placeHolder = placeHolder + splitStr.charAt(index);
                } else {
                    if (placeHolder.startsWith("null")) {
                        placeHolder = placeHolder.substring(placeHolder.indexOf("null") + 4, placeHolder.length());
                    }
                    itemList.addElement(placeHolder);
                    Log.v("Name", placeHolder);
                    placeHolder = "";
                }
                index++;
            }
            return itemList;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    } // end method doInBackground().
//================================================================================================================================
    // Method to set the auto complete field with the data.
    @Override
    protected void onPostExecute(Vector<String> itemList) {
        softwareAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, itemList);
        softwareView.setAdapter(softwareAdapter);
        setItemList(itemList);
    } // end method onPostExecute().
//================================================================================================================================
    // Method to set the current item list.
    private void setItemList(Vector<String> itemList) {
        this.itemList = itemList;
    } // end method setItemList().
//================================================================================================================================
    // Method to return the item list to the main activity.
    public Vector<String> getItemList() {
        return itemList;
    } // end method getItemList().
} // end class SoftwareQuery.
