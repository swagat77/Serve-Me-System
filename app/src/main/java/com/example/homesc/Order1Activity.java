package com.example.homesc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Order1Activity extends AppCompatActivity {

    Button Order_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order1);


        Order_3 = (Button) findViewById(R.id.Order_3);



        Order_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Order1Activity.this, RateOrder3.class));

            }
        });




    }
}
