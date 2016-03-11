/* Simple test program to get used to the software.
 */
package edu.fairmontstate.softwarefinder;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // Initialize the activity.
        setContentView(R.layout.activity_main); // Set the activity content from the activity_main layout resource.

        Button okButton = (Button)this.findViewById(R.id.button);   // Construct a button by finding its id from the content_main resource.

        try {
            okButton.setOnClickListener(new Button.OnClickListener() {  // Add a button listener by constructing a concrete instance of
                public void onClick(View view) {                        // the abstract class Button.OnClickListener and overriding the method onClick(View v)
                    EditText textField = (EditText)findViewById(R.id.editText);
                    textField.setText("This was a test!");  // Construct a text field the same way as the button and add some text when the button is clicked.
                }
            });
        }
        catch (NullPointerException npe) {
            npe.printStackTrace();
        }
    }
}
