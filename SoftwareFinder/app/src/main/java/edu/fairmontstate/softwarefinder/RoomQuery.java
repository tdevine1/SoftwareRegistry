/* Class that handles execution of the query to get the rooms in the database.
 *
 */
package edu.fairmontstate.softwarefinder;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Vector;

public class RoomQuery extends AsyncTask<String, Void, Vector<String>> {
    Context context;
    Vector<String> roomList;
    URL url;
    URLConnection conn;
    BufferedReader br;
    String line;
    StringBuilder sb;
    String splitStr;
    String[] rowArray;

    public RoomQuery(Context context) {
        this.context = context;
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
            roomList = new Vector<String>();
            roomList.addElement("<Select Room#>");

            while ((line = br.readLine()) != null) {
                if (!line.equals("")) {
                    sb.append(line);
                }
            }

            splitStr = sb.toString();
            rowArray = splitStr.split("[$]");

            for (int i = 0; i < rowArray.length - 1; i++) {
                roomList.addElement(rowArray[i]);
            }
            return roomList;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    } // end method doInBackground().
    //================================================================================================================================
    // Method to set the auto complete field with the data.
    @Override
    public void onPostExecute(Vector<String> buildingList) {
        // adapters are handled in MainActivity.
    } // end method onPostExecute().
}
