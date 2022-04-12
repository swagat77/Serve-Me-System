package com.example.homesc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button Account = findViewById(R.id.LinktoAccount);
        Button Order = findViewById(R.id.LinktoOrderpage);
        Button Search = findViewById(R.id.LinktoSearchPage);
        Button home = findViewById(R.id.home);
        Button cleaning = findViewById(R.id.tocleaning);
        Button appliances = findViewById(R.id.toappliances);
        Button plumbing = findViewById(R.id.toplumbing);
        Button electrical = findViewById(R.id.toelectrical);
        Button tutoring = findViewById(R.id.totutoring);
        Button lawnmower = findViewById(R.id.toLawnmower);
        Account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Account.class));
                Intent intent = new Intent(MainActivity.this, Account.class);
                startActivity(intent);
            }
        });
        appliances.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Appliances.class));
                Intent intent = new Intent(MainActivity.this, Appliances.class);
                startActivity(intent);
            }
        });
        plumbing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Plumbing.class));
                Intent intent = new Intent(MainActivity.this, Plumbing.class);
                startActivity(intent);
            }
        });
        electrical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Electrical.class));
                Intent intent = new Intent(MainActivity.this,Electrical.class);
                startActivity(intent);
            }
        });
        tutoring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Tutoring.class));
                Intent intent = new Intent(MainActivity.this, Tutoring.class);
                startActivity(intent);
            }
        });
        cleaning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, cleaning.class));
                Intent intent = new Intent(MainActivity.this, cleaning.class);
                startActivity(intent);
            }
        });
        lawnmower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Lawnmower.class));
                Intent intent = new Intent(MainActivity.this, Lawnmower.class);
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
       /* Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Search.class));
                Intent intent = new Intent(MainActivity.this, Search.class);
                startActivity(intent);
            }
        });
        Order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Order.class));
                Intent intent = new Intent(MainActivity.this, Order.class);
                startActivity(intent);
            }
        });*/
    }}