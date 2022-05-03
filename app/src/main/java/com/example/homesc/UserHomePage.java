package com.example.homesc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class    UserHomePage extends AppCompatActivity {
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userhomepage);

        getSupportActionBar().setTitle("Homepage"); //renames action bar

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
                //startActivity(new Intent(UserHomePage.this, Account.class));
                Intent intent = new Intent(UserHomePage.this, Account.class); //this line does nothing if above is true
                startActivity(intent);
            }
        });

        appliances.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserHomePage.this, vendorList.class);
                intent.putExtra("category","Appliances");
                startActivity(intent);
            }
        });
        plumbing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserHomePage.this, vendorList.class);
                intent.putExtra("category","Plumbing");
                startActivity(intent);
            }
        });
        electrical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserHomePage.this,vendorList.class);
                intent.putExtra("category","Electrical");
                startActivity(intent);
            }
        });
        tutoring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserHomePage.this, vendorList.class);
                intent.putExtra("category","Tutoring");
                startActivity(intent);
            }
        });
        cleaning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserHomePage.this, vendorList.class);
                intent.putExtra("category","Cleaning");
                startActivity(intent);
            }
        });
        lawnmower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserHomePage.this, vendorList.class);
                intent.putExtra("category","Lawnmower");
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(UserHomePage.this, UserHomePage.class));
                Intent intent = new Intent(UserHomePage.this, UserHomePage.class);
                startActivity(intent);
                finish();
            }
        });
        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserHomePage.this, Search.class);
                startActivity(intent);
            }
        });
        Order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserHomePage.this, orderList.class);
                startActivity(intent);
            }
        });
    }}