package com.jpauwels.orderpizza;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;

public class PizzaInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_info);

        final RadioButton round = (RadioButton) findViewById(R.id.radRound);
        final RadioButton square = (RadioButton) findViewById(R.id.radSquare);
        final Spinner spnRoundSize = (Spinner) findViewById(R.id.spnRoundSize);
        final Spinner spnSquareSize = (Spinner) findViewById(R.id.spnSquareSize);


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
    }
    }
