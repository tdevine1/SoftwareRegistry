package edu.fairmontstate.softwarefinder;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/* Simple class that displays an alert message
 * depending on the action taken.
 */
public class AlertMessage extends DialogFragment {
    String title;
    String message;

    public AlertMessage() {
        title = "";
        message = "";
    } // end default constructor AlertDialog
    public AlertMessage(String title, String message) {
        this.title = title;
        this.message = message;
    }
    //================================================================================================================================
    @Override
    public Dialog onCreateDialog(Bundle savedInstance) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface d, int id) {
                // disposes the dialog.
            }
        });
        return builder.create();
    }
}
