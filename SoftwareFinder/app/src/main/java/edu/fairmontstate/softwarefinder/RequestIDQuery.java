/* Class that handles execution of the query to get the request ID of a particular software in a particular location.
 *
 */
package edu.fairmontstate.softwarefinder;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.net.*;
import java.io.*;

public class RequestIDQuery extends AsyncTask<String, Void, String> {
    Context context;
    URL url;
    URLConnection conn;
    BufferedReader br;
    String line;
    StringBuilder sb;
    String result;

    public RequestIDQuery(Context context) {
        this.context = context;
    } // end constructor.
    //================================================================================================================================
    // Method to get the output of the php execution from the link.
    @Override
    protected String doInBackground(String... urls) {

        try {
            url = new URL(urls[0]);
            conn = url.openConnection();
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            sb = new StringBuilder();

            while ((line = br.readLine()) != null) {
                if (!line.equals("")) {
                    sb.append(line);
                }
            }
            result = sb.toString();
            result = result.substring(0, result.indexOf("<"));
            return result;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    } // end method doInBackground().
    //================================================================================================================================
    // Method to set the auto complete field with the data.
    @Override
    protected void onPostExecute(String result) {
        // Handling of the contents of result is done from parent
    } // end method onPostExecute().
} // end class SoftwareQuery.
