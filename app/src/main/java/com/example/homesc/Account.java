package com.example.homesc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Account extends AppCompatActivity {
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        getSupportActionBar().setTitle("Account"); //renames action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //enables back arrow in top bar

        Button Notifications = findViewById(R.id.toNotifications);
        Button PersonalInformation = findViewById(R.id.toPersonalInformation);
        Button Logout = findViewById(R.id.toLogOut);
        Button faq = findViewById(R.id.toFAQ);
        Button orderhistory=findViewById(R.id.orderhistory);
        Button Settings = findViewById(R.id.Settings);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                //startActivity(new Intent(Account.this, MainActivity.class));
                //above does not clear stack, user can still access app
                Intent logout=new Intent(Account.this,MainActivity.class);
                finishAffinity();
                startActivity(logout);
            }
           /* @Override
            public void onClick(View view) {
                Intent intent=new Intent(Account.this,Notifications.class)
                startActivity(intent);
            }*/
        });
        PersonalInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Account.this, PersonalInformation.class);
                startActivity(intent);
            }
        });
        Settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Account.this, Settings.class);
                startActivity(intent);
            }
        });


         orderhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        Intent intent = new Intent(Account.this, orderhistory.class);
         startActivity(intent);
        }
         });


        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Account.this, faq.class);
                startActivity(intent);
            }
        });
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

