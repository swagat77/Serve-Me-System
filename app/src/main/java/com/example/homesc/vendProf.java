package com.example.homesc;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class vendProf extends AppCompatActivity {

    String vendUID;

    TextView vendName;
    TextView vendHours;
    TextView vendAddr;
    TextView vendMail;
    TextView vendPhone;
    TextView vendRating;
    RatingBar vendStars;
    TextView vendOrders;

    String vendorName;

    order order=new order();

    DatabaseReference database=FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vend_prof);

        getSupportActionBar().setTitle("Vendor's Profile"); //renames action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //enables back arrow in top bar

        vendUID=getIntent().getStringExtra("vendUID");

        vendName=(TextView) findViewById(R.id.vendNameText);
        vendHours=(TextView)findViewById(R.id.hours);
        vendAddr=(TextView) findViewById(R.id.vendAddr);
        vendMail=(TextView) findViewById(R.id.vendMail);
        vendPhone=(TextView) findViewById(R.id.vendPhone);
        vendRating=(TextView) findViewById(R.id.vendRating);
        vendStars=(RatingBar)findViewById(R.id.ratingStars);
        vendOrders=(TextView) findViewById(R.id.vendOrders);

        Query vendQuery=database.child("Vendors").orderByChild(vendUID);

        vendQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    vendorName=dataSnapshot.child(vendUID).child("companyName").getValue().toString();

                    /*
                    int vendOpen=dataSnapshot.child(vendUID).child();
                    int vendClose=dataSnapshot.child(vendUID).child();
                    //currently hard coded, needs to be changed to check vendor data in database when whoever
                    //was in charge of that adds the test data

                    //needs to be formatted consistently with app
                    //will it be formatted as a string (08:00 AM - 08:00 PM) or as two ints/strings?
                    //if latter, needs to be reformatted. grab from placeReq
                     */

                    vendName.setText(vendorName);
                    vendHours.setText("Hours: "+"08:00 AM - 08:00 PM");
                    vendAddr.setText("Address: "+dataSnapshot.child(vendUID).child("address").getValue().toString());
                    vendMail.setText("Email: "+dataSnapshot.child(vendUID).child("companyEmail").getValue().toString());
                    vendPhone.setText("Phone: "+dataSnapshot.child(vendUID).child("companyPhone").getValue().toString());
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

        Query ordCompQuery=database.child("Orders").orderByChild("vendUID").equalTo(vendUID);

        ordCompQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    int ordersComp=0;
                    List ratings=new ArrayList<Float>();
                    float avgRating = 0;

                    for(DataSnapshot order:snapshot.getChildren())
                    {
                        if(Integer.parseInt(order.child("completed").getValue().toString())==1)
                        {
                            ordersComp++;

                            if(Float.parseFloat(order.child("rating").getValue().toString())>=0)
                            {
                                ratings.add(Float.parseFloat(order.child("rating").getValue().toString()));
                            }

                        }
                        else
                        {
                            continue;//order cancelled or not fulfilled
                        }
                    }

                    vendOrders.setText("Orders Completed: "+ ordersComp);

                    if(!ratings.isEmpty())
                    {
                        for(Object stars:ratings)
                        {
                            avgRating=avgRating+(float)stars;
                        }
                        avgRating=avgRating/ratings.size();
                        vendStars.setRating(avgRating);
                    }
                    else if(ratings.isEmpty())
                    {
                        vendStars.setRating(0);
                    }
                }
                else
                {
                    Toast.makeText(vendProf.this, "Error retrieving vendor information", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //Toast.makeText(vendProf.this, "Error retrieving vendor information", Toast.LENGTH_LONG).show();
            }
        });

        Button revButt=(Button)findViewById(R.id.reviewButt);//review button
        revButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openReviewList();
            }
        });

        Button confButt = (Button) findViewById(R.id.confirmButt);//schedule button
        confButt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openPlaceReq();
            }
        });
    }
    public boolean onOptionsItemSelected(MenuItem item) //enables action bar back button functionality
    {
        switch(item.getItemId())
        {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void openReviewList()
    {
        Intent review=new Intent(this, reviewList.class);
        review.putExtra("vendUID",vendUID);
        review.putExtra("vendName",vendorName);
        startActivity(review);
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
    public void openOrderDetail(String orderUID)
    {
        Intent order=new Intent(this,orderDetails.class);
        order.putExtra("orderUID",orderUID);
        startActivityForResult(order,3);
    }
    public void uploadOrder()
    {
        String key=database.child("Orders").push().getKey();

        order.setUserUID(FirebaseAuth.getInstance().getCurrentUser().getUid());

        order.setVendorName(vendorName);
        order.setVendUID(vendUID);
        order.setStatus(0);

        if(order.uploadOrder(database,key))
        {
            Toast.makeText(this, "Order submitted", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this, "Error submitting order", Toast.LENGTH_LONG).show();
        }

        openOrderDetail(key);

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

                        order.setPrice(Float.parseFloat("50.25"));
                        //hardcoded, needs to check from database or previous activity when whoever in charge implements functionality

                        uploadOrder();
                        break;
                    case 2:
                        Toast.makeText(this, "Cancelled Order", Toast.LENGTH_LONG).show();
                        break;
                    default:
                        Toast.makeText(this, "Error retrieving information", Toast.LENGTH_LONG).show();
                        break;
                }
                break;
            case 3: //orderDetails
                switch(resCode)
                {
                    case RESULT_OK: //changed or cancelled
                        Toast.makeText(this, "Changes saved successfully", Toast.LENGTH_LONG).show();
                        break;
                    case 2: //user presses back
                        break;
                    case 3:
                        Toast.makeText(this, "Your order has been cancelled", Toast.LENGTH_LONG).show();
                        break;
                    default:
                        Toast.makeText(this, "An error occurred. Please try again", Toast.LENGTH_LONG).show();
                        break;
                }
                break;
            default:
                Toast.makeText(this, "An error occurred, please try again", Toast.LENGTH_LONG).show();
                break;
        }
    }

}