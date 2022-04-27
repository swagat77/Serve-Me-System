package com.example.homesc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class  Payment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.tosucccessful);
        EditText creditNumber=findViewById(R.id.spaceforcardno);
        EditText name=findViewById(R.id.spaceforname);
        EditText expdate=findViewById(R.id.spaceforexpdate);
        EditText cvv=findViewById(R.id.spaceforcvv);
        EditText zipcode=findViewById(R.id.spaceforzipcode);
        Button cancel=findViewById(R.id.cancelbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // startActivity(new Intent(MainActivity.this, successful.class));
                // Intent intent = new Intent(MainActivity.this, successful.class);
                //startActivity(intent);
                //  String number=creditNumber.getText().toString().replaceAll()("[^\\d"]","")
                String number = creditNumber.getText().toString().replaceAll("[^\\d]","");
                String threeDigit = cvv.getText().toString().replaceAll("[^\\d]","");
                String exp = expdate.getText().toString().replaceAll("[^\\d]","");
                //check that credit number is 16 digits, pin is 3 digits, exp date is MM/YYYY
                if(number.length() != 16){
                    creditNumber.setError("Incorrect information");
                }
                else if (threeDigit.length() != 3){
                    cvv.setError("Incorrect information");
                }
                else if(exp.length() != 6){
                    expdate.setError("Incorrect information");
                }
                else if(zipcode.length()!=5){
                    zipcode.setError("Incorrect information");
                }
                else{
                    startActivity(new Intent(Payment.this, successful.class));
                    Intent intent = new Intent(Payment.this, successful.class);
                    startActivity(intent);
                }

            }}
        );
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Payment.this, UserHomePage.class));
                Intent intent = new Intent(Payment.this, UserHomePage.class);
                startActivity(intent);
            }
        } );

    }
}