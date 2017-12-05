package com.jpauwels.orderpizza;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;

import java.security.KeyStore;

public class PizzaInfo extends AppCompatActivity {
    //declare variables to receive data from previous screen
    private String firstName = "";
    private String lastName = "";
    private String address = "";
    private String city = "";
    private String state = "";
    private String zip = "";
    private String phone = "";
    private Double totalcost = 0.0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_info);
        //create connections to interface components
        final RadioButton round = (RadioButton) findViewById(R.id.radRound);
        final RadioButton square = (RadioButton) findViewById(R.id.radSquare);
        final Spinner spnRoundSize = (Spinner) findViewById(R.id.spnRoundSize);
        final Spinner spnSquareSize = (Spinner) findViewById(R.id.spnSquareSize);
        final CheckBox chkPepperoni = (CheckBox) findViewById(R.id.chkPepperoni);
        final CheckBox chkBacon = (CheckBox) findViewById(R.id.chkBacon);
        final CheckBox chkGreenPepper = (CheckBox) findViewById(R.id.chkGreenPepper);
        final CheckBox chkMushroom = (CheckBox) findViewById(R.id.chkMushroom);
        final CheckBox chkOnion = (CheckBox) findViewById(R.id.chkOnion);
        final CheckBox chkSausage = (CheckBox) findViewById(R.id.chkSausage);
        final Button btnNext = (Button) findViewById(R.id.btnNext);

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
        }


        //set pizza size based on square vs round
        round.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    spnRoundSize.setVisibility(View.VISIBLE);
                    spnSquareSize.setVisibility(View.GONE);
                } else {
                    spnRoundSize.setVisibility(View.GONE);
                    spnSquareSize.setVisibility(View.VISIBLE);
                }
            }
        });


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set base price for pizza
                if (round.isChecked())
                {
                    if (spnRoundSize.getSelectedItem().toString().equals("8 Inch - $8.25")){
                        totalcost = 8.25;
                    }
                    else if (spnRoundSize.getSelectedItem().toString().equals("10 Inch - $10.00")){
                        totalcost = 10.00;
                    }
                    else if (spnRoundSize.getSelectedItem().toString().equals("12 Inch - $13.00")){
                        totalcost = 13.00;
                    }
                    else if (spnRoundSize.getSelectedItem().toString().equals("14 Inch - $16.00")){
                        totalcost = 16.00;
                    }
                }else if (square.isChecked())
                {
                    if (spnRoundSize.getSelectedItem().toString().equals("8 Inch - $8.25")){
                        totalcost = 8.25;
                    }
                    else if (spnRoundSize.getSelectedItem().toString().equals("12 Inch - $14.00")){
                        totalcost = 14.00;
                    }
                    else if (spnRoundSize.getSelectedItem().toString().equals("16 Inch - $17.00")){
                        totalcost = 17.00;
                    }
                }

                //add .50 per topping selected
                if (chkPepperoni.isChecked()){
                    totalcost += 0.50;
                }
                if (chkBacon.isChecked()){
                    totalcost += 0.50;
                }
                if (chkGreenPepper.isChecked()){
                    totalcost += 0.50;
                }
                if (chkMushroom.isChecked()){
                    totalcost += 0.50;
                }
                if (chkOnion.isChecked()){
                    totalcost += 0.50;
                }
                if (chkSausage.isChecked()){
                    totalcost += 0.50;
                }

                //pass data to next screen in variables
                Intent nextScreen = new Intent(PizzaInfo.this, Payment.class);
                nextScreen.putExtra("FirstName", firstName);
                nextScreen.putExtra("LastName", lastName);
                nextScreen.putExtra("StreetAddress", address);
                nextScreen.putExtra("City", city);
                nextScreen.putExtra("State", state);
                nextScreen.putExtra("Zip", zip);
                nextScreen.putExtra("Phone", phone);
                nextScreen.putExtra("Total", totalcost);

                //Start Activity
                startActivity(nextScreen);
            }

        });
    }
}
