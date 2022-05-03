package com.example.homesc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class vendoraccountpage extends AppCompatActivity implements View.OnClickListener {

    public Button logout;
    public Button toHome, toOrder, toAccount;

    public DatabaseReference reference;
    public String userID;
    public FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendoraccountpage);

        logout = (Button) findViewById(R.id.Logout);
        logout.setOnClickListener(this);
        toOrder = (Button) findViewById(R.id.toOrder);
        toOrder.setOnClickListener(this);
        toHome = (Button) findViewById(R.id.Home);
        toHome.setOnClickListener(this);
        toAccount = (Button) findViewById(R.id.Account);
        toAccount.setOnClickListener(this);

        /*logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(vendoraccountpage.this, VendorLogin.class);
                startActivity(intent);
            }
        }); */


        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Vendors");
        userID = user.getUid();


        final TextView displayName = (TextView) findViewById(R.id.blankName);
        final TextView displayEmail = (TextView) findViewById(R.id.blankEmail);
        final TextView displayPhone = (TextView) findViewById(R.id.blankNumber);


        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Vendor vendorProfileName = snapshot.getValue(Vendor.class);
                if (vendorProfileName != null) {
                    String companyName = vendorProfileName.companyName;
                    String phone = vendorProfileName.companyPhone;
                    String email = vendorProfileName.companyEmail;

                    displayName.setText(companyName);
                    displayEmail.setText(email);
                    displayPhone.setText(phone);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(vendoraccountpage.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void onClick(View view) {
        if(view.getId() == R.id.Logout)
        {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(vendoraccountpage.this, VendorLogin.class);
            finishAffinity();
            startActivity(intent);

        }

        else if(view.getId() == R.id.toOrder)
        {
            Intent intent = new Intent(vendoraccountpage.this, openvendororders.class);
            startActivity(intent);
        }
        else if(view.getId() == R.id.Account)
        {
            Intent intento = new Intent(vendoraccountpage.this, vendoraccountpage.class );
            startActivity(intento);
        }
        else if(view.getId() == R.id.Home)
        {
            Intent inten = new Intent(vendoraccountpage.this, VendorHomePage.class);
            finishAffinity();
            startActivity(inten);

        }
        else
        {

        }

    }

}
