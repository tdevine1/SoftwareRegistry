/* Class that handles execution of the query to get the building names of the specified software.
 *
 */
package edu.fairmontstate.softwarefinder;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Vector;

public class SoftwareBuildingQuery extends AsyncTask<String, Void, Vector<String>> {
    Context context;
    ArrayAdapter<String> arrayAdapter;
    ListView listView;
    Vector<String> softwareList;
    URL url;
    URLConnection conn;
    BufferedReader br;
    String line;
    StringBuilder sb;
    String splitStr;
    String[] rowArray;

    public SoftwareBuildingQuery(Context context, ArrayAdapter<String> arrayAdapter, ListView listView) {
        this.context = context;
        this.arrayAdapter = arrayAdapter;
        this.listView = listView;
    } // end constructor.
    //================================================================================================================================
    // Method to get the output of the php execution from the link.
    @Override
    public Vector<String> doInBackground(String... urls) {

        try {
            Log.v("URL:", urls[0]);
            url = new URL(urls[0]);
            conn = url.openConnection();
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            sb = new StringBuilder();
            softwareList = new Vector<String>();

            while ((line = br.readLine()) != null) {
                if (!line.equals("")) {
                    sb.append(line);
                }
            }

            splitStr = sb.toString();
            rowArray = splitStr.split("[$]");

            for (int i = 0; i < rowArray.length - 1; i++) {
                softwareList.addElement(rowArray[i]);
            }
            return softwareList;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    } // end method doInBackground().
    //================================================================================================================================
    // Method to set the auto complete field with the data.
    @Override
    public void onPostExecute(Vector<String> softwareList) {
        arrayAdapter = new ArrayAdapter<String>(context, R.layout.custom_list_view, softwareList);
        listView.setAdapter(arrayAdapter);
    } // end method onPostExecute().
}
