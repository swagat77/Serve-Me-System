package com.example.homesc;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;



import java.util.ArrayList;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class VendorHomePage extends AppCompatActivity implements View.OnClickListener{

    private FirebaseUser vendor;
    //private Firebase
    private DatabaseReference reference;
    private String vendorID;
    private BottomNavigationView bnv;
    static String email;
    static String companyName;
    private Button button;
    private Button toOrder;
    private Button toHome;
    private Button toAccount;
    static int NumberOfVendors;
    //LinearLayout layoutList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_home_page);


        toOrder = (Button) findViewById(R.id.toOrder);
        toOrder.setOnClickListener(this);
        toHome = (Button) findViewById(R.id.Home);
        toHome.setOnClickListener(this);
        toAccount = (Button) findViewById(R.id.Account);
        toAccount.setOnClickListener(this);

        reference = FirebaseDatabase.getInstance().getReference("Vendors");

        //layoutl = findViewById(R.id.lay);




        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(VendorHomePage.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });



        ArrayList<String> newList = new ArrayList<>();
        vendor = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Vendors");
        vendorID = vendor.getUid();
        //bnv=findViewById(R.id.navigationbar);



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
                Toast.makeText(VendorHomePage.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });


        //reference.child()

    }
    public void onClick(View view)
    {
        if(view.getId() == R.id.toOrder)
        {
            Intent intent = new Intent(VendorHomePage.this, openvendororders.class);
            startActivity(intent);
        }
        else if(view.getId() == R.id.Account)
        {
            Intent intento = new Intent(VendorHomePage.this, vendoraccountpage.class );
            startActivity(intento);
        }
        else if(view.getId() == R.id.Home)
        {
            Intent inten = new Intent(VendorHomePage.this, VendorHomePage.class);
            startActivity(inten);

        }
        else
        {

        }

    }
    /*
    if(the thing  is in the data base then do add view)
    {

    } */



    //public NavigationBarView
}