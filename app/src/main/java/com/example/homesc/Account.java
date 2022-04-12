package com.example.homesc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Account extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        Button Notifications=findViewById(R.id.toNotifications);
        Button PersonalInformation=findViewById(R.id.toPersonalInformation);
        Button Logout=findViewById(R.id.toLogOut);
        Button faq=findViewById(R.id.toFAQ);
       /* Notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Account.this,Notifications.class)
                startActivity(intent);
            }
        });
        PersonalInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Account.this,PersonalInformation.class);
                    startActivity(intent);
                }
            });
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Account.this, Login.class);
                startActivity(intent);
            }
            });
        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Account.this,faq.class);
                startActivity(intent);
            }*/
        }
}