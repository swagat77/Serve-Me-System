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
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

       /* reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if(userProfile != null)
                {
                    String fullName = userProfile.fullName;
                    String userEmail = userProfile.userEmail;
                    int numberOfAvailablePoints = userProfile.numberOfAvailablePoints;

                }

            }

          //  @Override
         //   public void onCancelled(@NonNull DatabaseError error) {
             //  Toast.makeText(UserHomePage.this, "ERROR, Toast.LENGTH_SHORT").show();

           // }
        });*/

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userhomepage);
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
                startActivity(new Intent(UserHomePage.this, Account.class));
                Intent intent = new Intent(UserHomePage.this, Account.class);
                startActivity(intent);
            }
        });
        appliances.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserHomePage.this, Appliances.class));
                Intent intent = new Intent(UserHomePage.this, Appliances.class);
                startActivity(intent);
            }
        });
        plumbing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserHomePage.this, Plumbing.class));
                Intent intent = new Intent(UserHomePage.this, Plumbing.class);
                startActivity(intent);
            }
        });
        electrical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserHomePage.this, Electrical.class));
                Intent intent = new Intent(UserHomePage.this,Electrical.class);
                startActivity(intent);
            }
        });
        tutoring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserHomePage.this, Tutoring.class));
                Intent intent = new Intent(UserHomePage.this, Tutoring.class);
                startActivity(intent);
            }
        });
        cleaning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserHomePage.this, cleaning.class));
                Intent intent = new Intent(UserHomePage.this, cleaning.class);
                startActivity(intent);
            }
        });
        lawnmower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserHomePage.this, Lawnmower.class));
                Intent intent = new Intent(UserHomePage.this, Lawnmower.class);
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserHomePage.this, UserHomePage.class));
                Intent intent = new Intent(UserHomePage.this, UserHomePage.class);
                startActivity(intent);
            }
        });
       /* Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserHomePage.this, Search.class));
                Intent intent = new Intent(UserHomePage.this, Search.class);
                startActivity(intent);
            }
        });
        Order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserHomePage.this, Order.class));
                Intent intent = new Intent(UserHomePage.this, Order.class);
                startActivity(intent);
            }
        });*/
    }}