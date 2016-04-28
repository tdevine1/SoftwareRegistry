/* Class that handles execution of the query to get the locations of the specified software.
 *
 */
package edu.fairmontstate.softwarefinder;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Vector;

public class LocationQuery extends AsyncTask<String, Void, Vector<String>> {
    Context context;
    ArrayAdapter<String> arrayAdapter;
    ListView listView;
    Vector<String> locationList;
    URL url;
    URLConnection conn;
    BufferedReader br;
    String line;
    StringBuilder sb;
    String splitStr;
    String[] rowArray;
    String[] rowElements;

    public LocationQuery(Context context, ArrayAdapter<String> arrayAdapter, ListView listView) {
        this.context = context;
        this.arrayAdapter = arrayAdapter;
        this.listView = listView;
    } // end constructor.
//================================================================================================================================
    // Method to get the output of the php execution from the link.
    @Override
    public Vector<String> doInBackground(String... urls) {

        try {
            url = new URL(urls[0]);
            conn = url.openConnection();
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            sb = new StringBuilder();
            locationList = new Vector<String>();

            while ((line = br.readLine()) != null) {
                if (!line.equals("")) {
                    sb.append(line);
                }
            }

            splitStr = sb.toString();
            rowArray = splitStr.split("[$]");

            for (int i = 0; i < rowArray.length - 1; i++) {
                rowElements = rowArray[i].split("[%]");
                locationList.addElement(rowElements[1] + " " + rowElements[0]);
            }
            return locationList;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    } // end method doInBackground().
//================================================================================================================================
    // Method to set the auto complete field with the data.
    @Override
    public void onPostExecute(Vector<String> locationList) {
        arrayAdapter = new ArrayAdapter<String>(context, R.layout.custom_list_view, locationList);
        listView.setAdapter(arrayAdapter);
    } // end method onPostExecute().
}
