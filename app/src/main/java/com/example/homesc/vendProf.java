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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class vendProf extends AppCompatActivity {

    String vendUID;

    TextView vendName;
    TextView vendAddr;
    TextView vendMail;
    TextView vendPhone;
    TextView vendRating;
    TextView vendOrders;

    String vendorName;

    //add new field to order class for review and rating
    //figure out why username not being uploaded

    order order=new order();

    DatabaseReference database=FirebaseDatabase.getInstance().getReference();

    private static final DecimalFormat dfZero=new DecimalFormat("0.00");
    String fullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vend_prof);

        getSupportActionBar().setTitle("Vendor's Profile"); //renames action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //enables back arrow in top bar

        vendUID=getIntent().getStringExtra("vendUID");

        vendName=(TextView) findViewById(R.id.vendNameText);
        vendAddr=(TextView) findViewById(R.id.vendAddr);
        vendMail=(TextView) findViewById(R.id.vendMail);
        vendPhone=(TextView) findViewById(R.id.vendPhone);
        vendRating=(TextView) findViewById(R.id.vendRating);
        vendOrders=(TextView) findViewById(R.id.vendOrders);

        Query vendQuery=database.child("Vendors").orderByChild(vendUID);

        vendQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    vendorName=dataSnapshot.child(vendUID).child("companyName").getValue().toString();

                    vendName.setText(vendorName);
                    vendAddr.setText("Address: "+dataSnapshot.child(vendUID).child("address").getValue().toString());
                    vendMail.setText("Email: "+dataSnapshot.child(vendUID).child("companyEmail").getValue().toString());
                    vendPhone.setText("Phone: "+dataSnapshot.child(vendUID).child("companyPhone").getValue().toString());
                    //vendRating.setText();
                    //vendOrders.setText();
                }
                else{
                    Toast.makeText(vendProf.this, "Error retrieving vendor information", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(vendProf.this, "Error retrieving vendor information", Toast.LENGTH_LONG).show();
            }
        });

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openPlaceReq();
            }
        });
    }
    public void openPlaceReq()
    {
        Intent schedule=new Intent(this,placeReq.class);
        schedule.putExtra("vendName", vendorName);
        startActivityForResult(schedule,1);
    }
    public void openPayment()
    {
        Intent payment=new Intent(this,Payment.class);
        startActivityForResult(payment,2);
    }
    public void uploadOrder()
    {
        String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
        Query userQuery = database.child("Users").orderByChild(uid);

        userQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    fullName=snapshot.child(uid).child("fullName").getValue().toString();
                    order.setUser(fullName);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(vendProf.this, "Error retrieving user info", Toast.LENGTH_LONG).show();
            }
        });

        order.setUserUID(uid);

        order.setVendorName(vendorName);
        order.setVendUID(vendUID);
        order.setStatus(0);

        String key=database.child("Orders").push().getKey();
        Map<String, Object> orderDetails=order.toMap();
        Map<String, Object> childUpdate=new HashMap<>();
        childUpdate.put("/Orders/"+key,orderDetails);

        database.updateChildren(childUpdate);
        Toast.makeText(this, "Order submitted", Toast.LENGTH_LONG).show();
        finish();
    }
    protected void onActivityResult(int reqCode, int resCode, Intent data) {
        super.onActivityResult(reqCode, resCode, data);
        switch(reqCode)
        {
            case 1: //placeReq
                switch(resCode)
                {
                    case RESULT_OK:
                        String address=data.getStringExtra("address");
                        String time=data.getStringExtra("time");
                        String date=data.getStringExtra("date");

                        order.setAppt(date,time);
                        order.setAddress(address);

                        openPayment();
                        break;
                    case RESULT_CANCELED:
                        Toast.makeText(this, "Order Cancelled", Toast.LENGTH_LONG).show();
                        break;
                    default:
                        Toast.makeText(this, "Error confirming appointment details, please try again", Toast.LENGTH_LONG).show();
                        break;
                }
                break;
            case 2: //payment
                switch(resCode)
                {
                    case RESULT_OK:
                        /*float tempPrice=(float)data.getFloatExtra("payAmount",0f);
                        String priceStr;
                        priceStr=dfZero.format(tempPrice);*/
                        order.setPrice(Float.parseFloat("50.25"));

                        uploadOrder();
                        break;
                    case 2:
                        Toast.makeText(this, "Cancelled Order", Toast.LENGTH_LONG).show();
                        break;
                    default:
                        Toast.makeText(this, "Error retrieving information", Toast.LENGTH_LONG).show();
                        break;
                }
            default:
                Toast.makeText(this, "An error occurred, please try again", Toast.LENGTH_LONG).show();
                break;
        }
    }

}