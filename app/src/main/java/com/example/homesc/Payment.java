package com.example.homesc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Payment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Button button = findViewById(R.id.tosuccessful);
        EditText creditNumber=findViewById(R.id.spaceforcardno);
        EditText name=findViewById(R.id.spaceforname);
        EditText expdate=findViewById(R.id.spaceforexpdate);
        EditText cvv=findViewById(R.id.spaceforcvv);
        EditText zipcode=findViewById(R.id.spaceforzipcode);
        Button cancel=findViewById(R.id.cancelbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String number = creditNumber.getText().toString().replaceAll("[^\\d]","");
                String threeDigit = cvv.getText().toString().replaceAll("[^\\d]","");
                String exp = expdate.getText().toString().replaceAll("[^\\d]","");

                if(number.length() != 16){
                    creditNumber.setError("Invalid Card Number");
                    setResult(RESULT_CANCELED);
                }
                else if (threeDigit.length() != 3){
                    cvv.setError("Invalid CVV");
                    setResult(RESULT_CANCELED);
                }
                else if(exp.length() != 6){
                    expdate.setError("Invalid Expiration");
                    setResult(RESULT_CANCELED);
                }
                else if(zipcode.length()!=5){
                    zipcode.setError("Invalid Zipcode");
                    setResult(RESULT_CANCELED);
                }
                else{
                    setResult(RESULT_OK);
                    finish();
                }

            }}
        );
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(2);
                finish();
            }
        } );

    }
}