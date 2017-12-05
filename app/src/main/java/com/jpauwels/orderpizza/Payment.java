package com.jpauwels.orderpizza;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class Payment extends AppCompatActivity {
    //declare variables to receive data from previous screen
    private String firstName = "";
    private String lastName = "";
    private String address = "";
    private String city = "";
    private String state = "";
    private String zip = "";
    private String phone = "";
    private Double totalcost = 0.0;
    private Double finalcost = 0.0;
    private Boolean delivery = false;
    private Boolean ccpaytype = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        //create connections to interface components
        final RadioButton rdCash = (RadioButton) findViewById(R.id.rdCash);
        final RadioButton rdCredit = (RadioButton) findViewById(R.id.rdCredit);
        final RadioButton rdDelYes = (RadioButton) findViewById(R.id.rdDelYes);
        final RadioButton rdDelNo = (RadioButton) findViewById(R.id.rdDelNo);
        final Button btnNext = (Button) findViewById(R.id.btnNext);
        final TextView txtCash = (TextView) findViewById(R.id.txtCash);
        final TextView txtCSC = (TextView) findViewById(R.id.txtCSC);
        final TextView txtExpiration = (TextView) findViewById(R.id.txtExpiration);
        final EditText editCCNum = (EditText) findViewById(R.id.editCCNum);
        final EditText editCCMon = (EditText) findViewById(R.id.editCCMon);
        final EditText editCCYear = (EditText) findViewById(R.id.editCCYear);
        final EditText editCCCSC = (EditText) findViewById(R.id.editCCCSC);
        final TextView txtPayTypeError = (TextView) findViewById(R.id.txtPayTypeError);


        //input data from previous screen into variables
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            firstName = extras.getString("FirstName");
            lastName = extras.getString("LastName");
            address = extras.getString("StreetAddress");
            city = extras.getString("City");
            state = extras.getString("State");
            zip = extras.getString("Zip");
            phone = extras.getString("Phone");
            totalcost = extras.getDouble("Total");
        }

        //check if cash button is selected and display appropriate message
        rdCash.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    txtCash.setVisibility(View.VISIBLE);
                    txtCSC.setVisibility(View.GONE);
                    txtExpiration.setVisibility(View.GONE);
                    editCCNum.setVisibility(View.GONE);
                    editCCMon.setVisibility(View.GONE);
                    editCCYear.setVisibility(View.GONE);
                    editCCCSC.setVisibility(View.GONE);
                    txtPayTypeError.setVisibility(View.GONE);
                } else {
                    txtCash.setVisibility(View.GONE);
                    txtCSC.setVisibility(View.VISIBLE);
                    txtExpiration.setVisibility(View.VISIBLE);
                    editCCNum.setVisibility(View.VISIBLE);
                    editCCMon.setVisibility(View.VISIBLE);
                    editCCYear.setVisibility(View.VISIBLE);
                    editCCCSC.setVisibility(View.VISIBLE);
                    txtPayTypeError.setVisibility(View.GONE);
                }
            }
        });

        //check if credit button is selected and display credit card input fields
        rdCredit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    txtCash.setVisibility(View.VISIBLE);
                    txtCSC.setVisibility(View.GONE);
                    txtExpiration.setVisibility(View.GONE);
                    editCCNum.setVisibility(View.GONE);
                    editCCMon.setVisibility(View.GONE);
                    editCCYear.setVisibility(View.GONE);
                    editCCCSC.setVisibility(View.GONE);
                    txtPayTypeError.setVisibility(View.GONE);
                } else {
                    txtCash.setVisibility(View.GONE);
                    txtCSC.setVisibility(View.VISIBLE);
                    txtExpiration.setVisibility(View.VISIBLE);
                    editCCNum.setVisibility(View.VISIBLE);
                    editCCMon.setVisibility(View.VISIBLE);
                    editCCYear.setVisibility(View.VISIBLE);
                    editCCCSC.setVisibility(View.VISIBLE);
                    txtPayTypeError.setVisibility(View.GONE);
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //perform data validation
                if (checkData()) {
                    //if delivery button is selected, add 5 to total
                    if (rdDelYes.isChecked()) {
                        finalcost = totalcost + 5.0;
                        delivery = true;
                    }else {
                        finalcost = totalcost;
                    }

                    //if credit is selected, enter into variable to pass to next screen
                    if (rdCredit.isChecked()){
                        ccpaytype = true;
                    } else {
                        ccpaytype = false;
                    }

                    //pass data to next screen in variables
                    Intent nextScreen = new Intent(Payment.this, Summary.class);
                    nextScreen.putExtra("FirstName", firstName);
                    nextScreen.putExtra("LastName", lastName);
                    nextScreen.putExtra("StreetAddress", address);
                    nextScreen.putExtra("City", city);
                    nextScreen.putExtra("State", state);
                    nextScreen.putExtra("Zip", zip);
                    nextScreen.putExtra("Phone", phone);
                    nextScreen.putExtra("Total", finalcost);
                    nextScreen.putExtra("Delivery", delivery);
                    nextScreen.putExtra("CCPayType", ccpaytype);

                    //Start Activity
                    startActivity(nextScreen);
                }
            }

        });


    }

    //function to perform data validation
    private boolean checkData() {
        final RadioButton rdCash = (RadioButton) findViewById(R.id.rdCash);
        final RadioButton rdCredit = (RadioButton) findViewById(R.id.rdCredit);
        final TextView txtPayTypeError = (TextView) findViewById(R.id.txtPayTypeError);
        final EditText editCCNum = (EditText) findViewById(R.id.editCCNum);
        final EditText editCCMon = (EditText) findViewById(R.id.editCCMon);
        final EditText editCCYear = (EditText) findViewById(R.id.editCCYear);
        final EditText editCCCSC = (EditText) findViewById(R.id.editCCCSC);

        if (!rdCash.isChecked() && !rdCredit.isChecked()) {
            //error
            txtPayTypeError.setVisibility(View.VISIBLE);
            return false;
        }
        if (!rdCash.isChecked() && rdCredit.isChecked()) {
            if (editCCNum.getText().toString().isEmpty()) {
                editCCNum.setError("Enter credit card number.");
                editCCNum.requestFocus();
                return false;
            }
            if (editCCMon.getText().toString().isEmpty()) {
                editCCMon.setError("Enter expiration month.");
                editCCMon.requestFocus();
                return false;
            }
            if (editCCYear.getText().toString().isEmpty()) {
                editCCYear.setError("Enter expiration year.");
                editCCYear.requestFocus();
                return false;
            }
            if (editCCCSC.getText().toString().isEmpty()) {
                editCCCSC.setError("Enter CSC code.");
                editCCCSC.requestFocus();
                return false;
            }
        }
        return true;
    }
}
