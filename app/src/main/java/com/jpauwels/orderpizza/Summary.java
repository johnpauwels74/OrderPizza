package com.jpauwels.orderpizza;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Summary extends AppCompatActivity {
    //declare variables to receive data from previous screen
    private String firstName = "";
    private String lastName = "";
    private String address = "";
    private String city = "";
    private String state = "";
    private String zip = "";
    private String phone = "";
    private Double totalcost = 0.0;
    private Boolean delivery = false;
    private Boolean ccpaytype = false;
    private String summaryString;
    private String nameString;
    private String addressString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        //create connections to interface components
        final TextView txtName = (TextView) findViewById(R.id.txtName);
        final TextView txtAddress = (TextView) findViewById(R.id.txtAddress);
        final TextView txtSummary = (TextView) findViewById(R.id.txtSummary);
        final TextView txtPhone = (TextView) findViewById(R.id.txtPhone);
        DecimalFormat currency = new DecimalFormat("$###,###.##");

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
            delivery = extras.getBoolean("Delivery");
            ccpaytype = extras.getBoolean("CCPayType");
        }

        //perform name and address concatenation for summary output
        nameString = firstName + " " + lastName;
        addressString = address + "\n" +
                city + ", " + state + " " + zip;

        //concatenate final summary message that shows payment amount and methods
        if (delivery && ccpaytype){
            summaryString = "You've chosen a credit card payment and delivery. \n" +
                    "Your credit card will be charged a total of " + currency.format(totalcost) + ".\n" +
                    "Your pizza will be delivered within 30 minutes.";
        } else if(delivery && !ccpaytype){
            summaryString = "You've chosen to pay with cash and requested delivery. \n" +
                    "Your pizza will be delivered within 30 minutes.\n" +
                    "Your total comes to " + currency.format(totalcost) + ".\n" +
                    "Please have your payment ready when your pizza is delivered.";
        } else if(!delivery && ccpaytype){
            summaryString = "You've chosen to pay with a credit card. \n" +
                    "Your credit card will be charged a total of " + currency.format(totalcost) + ".\n" +
                    "Your pizza will be ready for pickup in about 20 minutes.";
        } else if(!delivery && !ccpaytype){
            summaryString = "You've chosen to pay with cash. \n" +
                    "Your total comes to " + currency.format(totalcost) + ".\n" +
                    "Your pizza will be ready for pickup in about 20 minutes.\n" +
                    "Please pay at the counter when you pick up your pizza.";
        }

        //display final summary output on screen
        txtSummary.setText(summaryString);
        txtName.setText(nameString);
        txtAddress.setText(addressString);
        txtPhone.setText(phone);
    }
}
