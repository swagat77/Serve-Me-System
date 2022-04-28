package com.example.homesc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;



public class openvendororders extends AppCompatActivity implements View.OnClickListener {
    LinearLayout layoutList;
    public Button complete;
    DatabaseReference database;
    public Button logout;
    private Button home;


    private FirebaseUser vendor;
    //private Firebase
    private DatabaseReference reference;
    private String vendorID;

    ArrayList<String> name = new ArrayList<String>();

    static ArrayList<String> str = new ArrayList<String>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_openvendororders);
        layoutList = findViewById(R.id.linearLayout);
        complete = (Button) findViewById(R.id.complete);
        complete.setOnClickListener(this);
        home = (Button) findViewById(R.id.back);
        home.setOnClickListener(this);

        vendor = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Vendors");
        vendorID = vendor.getUid();



        reference.child(vendorID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Vendor vendorProfileName = snapshot.getValue(Vendor.class);
                if (vendorProfileName != null) {
                    String comp = vendorProfileName.companyName;
                    //function(comp);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        DatabaseReference database = FirebaseDatabase.getInstance().getReference();


        Query q = database.child("Orders").orderByChild("vendName").equalTo("Swagat's Bulbs"); //change string under orderByChild to search for diff value i.e. companyName or email


        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())//checks if database snapshot is available to pull info from
                {
                    for (DataSnapshot child : snapshot.getChildren())
                    // makes new DataSnapshot var named child to pull info from iterator (snapshot.getChildren() in this case)
                    {

                        String childID = child.getKey();
                        str.add(childID);


                        //String statemp = snapshot.child("apptAddr").getValue().toString();
                        String statemp = snapshot.child(childID).child("completed").getValue().toString();
                        int status = Integer.parseInt(statemp);
                        if (status == 0) //)//status == 0)
                        {
                            String s = snapshot.child(childID).child("apptAddr").getValue().toString();
                            String d = snapshot.child(childID).child("apptDate").getValue().toString();
                            String a = snapshot.child(childID).child("apptTime").getValue().toString();
                            String cro = s + "   "+ d  + "  " + a;

                            View newv = getLayoutInflater().inflate(R.layout.orderstocomplete, null, false);
                            TextView displayOrder = (TextView) newv.findViewById(R.id.orderInfo);
                            displayOrder.setText(cro);
                            layoutList.addView(newv);
                        } else {
                            View newv = getLayoutInflater().inflate(R.layout.orderstocomplete, null, false);
                            TextView displayOrder = (TextView) newv.findViewById(R.id.orderInfo);
                            displayOrder.setText("Order Completed");
                            layoutList.addView(newv);

                        }

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void onClick(View view) {
        if (view.getId() == R.id.complete) {
            updateStatus();
            Intent intent = new Intent(openvendororders.this, openvendororders.class);
            startActivity(intent);
        }
        else if(view.getId() == R.id.back)
        {
            Intent intent = new Intent(openvendororders.this, VendorHomePage.class);
            startActivity(intent);

        }
    }


    private void updateStatus() {

        database = FirebaseDatabase.getInstance().getReference("Orders");
        for (int x = 0; x < str.size(); x++) {
            database.child(str.get(x)).child("completed").setValue(1);
        }


    }



}
