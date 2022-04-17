package com.example.homesc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;



import java.util.ArrayList;



import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class VendorHomePage extends AppCompatActivity {

    private FirebaseUser vendor;
    //private Firebase
    private DatabaseReference reference;
    private String vendorID;
    private BottomNavigationView bnv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_home_page);

        vendor = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Vendors");
        vendorID = vendor.getUid();
       // bnv=findViewById(R.id.navigationbar);



        final TextView displayName = (TextView) findViewById(R.id.name);

        reference.child(vendorID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Vendor vendorProfileName = snapshot.getValue(Vendor.class);
                if(vendorProfileName != null)
                {
                    String companyName = vendorProfileName.companyName;
                    String phone = vendorProfileName.companyPhone;
                    String email = vendorProfileName.companyEmail;

                    displayName.setText("Welcome " + companyName + "!");

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(VendorHomePage.this, "Error", Toast.LENGTH_SHORT);

            }
        });

        //reference.child()

    }

    //public NavigationBarView
}