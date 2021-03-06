/* Class that handles execution of the transaction to increment existing update requests in the database.
 *
 */
package edu.fairmontstate.softwarefinder;

import android.content.Context;
import android.os.AsyncTask;

import java.net.*;
import java.io.*;

public class IncrementRequestTransaction extends AsyncTask<String, Void, String> {
    Context context;
    URL url;
    URLConnection conn;
    BufferedReader br;

    public IncrementRequestTransaction(Context context) {
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

            return null;
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
        // Nothing to return, this query does not return a result set
    } // end method onPostExecute().
} // end class SoftwareQuery.
