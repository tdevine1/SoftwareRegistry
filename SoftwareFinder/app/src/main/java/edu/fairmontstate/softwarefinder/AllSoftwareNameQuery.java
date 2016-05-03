/* Class that handles execution of the query to get ALL of the software names (including unapproved).
 *
 */
package edu.fairmontstate.softwarefinder;

import android.content.Context;
import android.os.AsyncTask;
import java.net.*;
import java.io.*;
import java.util.Vector;

public class AllSoftwareNameQuery extends AsyncTask<String, Void, Vector<String>> {
    Context context;
    URL url;
    URLConnection conn;
    BufferedReader br;
    String line;
    StringBuilder sb;
    String splitStr;
    Vector<String> itemList;
    String[] rowArray;

    public AllSoftwareNameQuery(Context context) {
        this.context = context;
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
            rowArray = splitStr.split("[$]");

            for (int i = 0; i < rowArray.length - 1; i++) {
                itemList.addElement(rowArray[i]);
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
        // itemList is returned to the caller.
    } // end method onPostExecute().
} // end class SoftwareQuery.
