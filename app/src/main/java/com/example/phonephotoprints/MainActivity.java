package com.example.phonephotoprints;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    double numEntered; // number of prints to be entered by the user
    double smallSize = 0.19; // prices of different sizes
    double mediumSize = 0.49;
    double largeSize = 0.79;
    double totalPrice; // to be evaluated later in code

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // changing color of action bar (Top bar that's usually purple)
        ActionBar actionBar; // Define action bar object
        actionBar = getSupportActionBar();
        // define color drawable and parse color method, use hex color as the param
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#d1d1e0"));
        // Set background drawable
        actionBar.setBackgroundDrawable(colorDrawable);

        // Get app icon on the action bar when the app is running
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher_round);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        // edit text extracts value from the user input for number of prints
        final EditText hint = (EditText) findViewById(R.id.hint);
        final RadioButton rad4x6 = (RadioButton) findViewById(R.id.rad4x6); // Final means to assigned value to variable once throughout the class
        final RadioButton rad5x7 = (RadioButton) findViewById(R.id.rad5x7);
        final RadioButton rad8x10 = (RadioButton) findViewById(R.id.rad8x10);
        final TextView result = (TextView) findViewById(R.id.txtResults); // instantiated txtResult to the variable result
        Button convert = (Button) findViewById(R.id.btnCalculate); // button instantiated

        convert.setOnClickListener(new View.OnClickListener() { // this is the onClick listener that waits for the action to occur
            @Override
            public void onClick(View v) { // on click of the button, the code calculates as follow
                numEntered = Double.parseDouble(hint.getText().toString()); // converts input into double & converts input into Integer data type
                DecimalFormat tenth = new DecimalFormat("$##.#"); // formats the result into one place past the decimal point
                // A statement that tests the radio button is called conditional statement
                if (rad4x6.isChecked()) { // If first radio button is clicked
                    if (numEntered <= 50) { // nested loop, if a radio button is clicked, and if it is less than 51, then proceed execution
                        totalPrice = numEntered * smallSize;
                        result.setText(tenth.format(totalPrice) + " is the total cost"); // print result of the price
                    }
                    else { // if input more than 50, print a validation message
                        Toast.makeText(MainActivity.this,"Prints cannot exceed 50", Toast.LENGTH_LONG).show();
                    }
                }
                // Second if statement, if the medium size radio button is selected
                if (rad5x7.isChecked()) { // isChecked method tests of the radio button object is selected
                    if (numEntered <= 50) {
                        totalPrice = numEntered * mediumSize; // multiplies input by the rate of medium size print
                        result.setText("The order cost is " + tenth.format(totalPrice));
                    }
                    else {
                        Toast.makeText(MainActivity.this,"Prints cannot exceed 50 per order", Toast.LENGTH_LONG).show();
                    } // Toast displays a validation warning
                }
                // If large print is selected
                if (rad8x10.isChecked()) {
                    if (numEntered <= 50) { // number of print must be less than or equal to 50
                        totalPrice = numEntered * largeSize;
                        result.setText("The order cost is " + tenth.format(totalPrice));
                    }
                    else {
                        Toast.makeText(MainActivity.this,"Prints cannot exceed 50 per order", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }
}