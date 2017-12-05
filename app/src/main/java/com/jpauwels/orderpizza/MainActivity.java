package com.jpauwels.orderpizza;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //create connections to interface components
        final Button btnNext = (Button) findViewById(R.id.btnNext);
        final EditText firstName = (EditText) findViewById(R.id.txtFirstName);
        final EditText lastName = (EditText) findViewById(R.id.txtLastName);
        final EditText address = (EditText) findViewById(R.id.txtAddress);
        final EditText city = (EditText) findViewById(R.id.txtCity);
        final EditText state = (EditText) findViewById(R.id.txtState);
        final EditText zip = (EditText) findViewById(R.id.txtZip);
        final EditText phone = (EditText) findViewById(R.id.txtPhone);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //move on to next screen
                if (checkData()) {
                    //pass data in variables to next screen
                    Intent nextScreen = new Intent(MainActivity.this, PizzaInfo.class);
                    nextScreen.putExtra("FirstName", firstName.getText().toString());
                    nextScreen.putExtra("LastName", lastName.getText().toString());
                    nextScreen.putExtra("StreetAddress", address.getText().toString());
                    nextScreen.putExtra("City", city.getText().toString());
                    nextScreen.putExtra("State", state.getText().toString());
                    nextScreen.putExtra("Zip", zip.getText().toString());
                    nextScreen.putExtra("Phone", phone.getText().toString());

                    //Start Activity
                    startActivity(nextScreen);
                }
            }
        });
    }

            private boolean checkData() {
        //perform checks to validate input
                final EditText firstName = (EditText) findViewById(R.id.txtFirstName);
                final EditText lastName = (EditText) findViewById(R.id.txtLastName);
                final EditText address = (EditText) findViewById(R.id.txtAddress);
                final EditText city = (EditText) findViewById(R.id.txtCity);
                final EditText state = (EditText) findViewById(R.id.txtState);
                final EditText zip = (EditText) findViewById(R.id.txtZip);
                final EditText phone = (EditText) findViewById(R.id.txtPhone);


                if (firstName.getText().toString().isEmpty()) {
                    //error
                    firstName.setError("Invalid First Name");
                    firstName.requestFocus();
                    return false;
                }

                if (lastName.getText().toString().isEmpty()) {
                    //error
                    lastName.setError("Invalid Last Name");
                    lastName.requestFocus();
                    return false;
                }

                if (address.getText().toString().isEmpty()) {
                    address.setError("Invalid Street Address");
                    address.requestFocus();
                    return false;
                }

                if (city.getText().toString().isEmpty()) {
                    city.setError("Invalid City");
                    city.requestFocus();
                    return false;
                }

                if (state.getText().toString().isEmpty()) {
                    state.setError("Invalid State");
                    state.requestFocus();
                    return false;
                }

                if (zip.getText().toString().isEmpty()) {
                    zip.setError("Invalid Zip Code");
                    zip.requestFocus();
                    return false;
                }

                if (phone.getText().toString().isEmpty()) {
                    phone.setError("Invalid Phone Number");
                    phone.requestFocus();
                    return false;
                }

                return true;
            }
        }
