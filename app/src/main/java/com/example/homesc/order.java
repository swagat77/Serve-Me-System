package com.example.homesc;

import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class order{
    float price;
    String date;
    String time;
    String address;
    String vendorName;
    String vendUID;
    String userUID;
    int rating;
    String review;
    int status; //0 in progress, 1 finished, -1 cancelled

    public order()
    {
        this.price=0;
        this.date=null;
        this.time=null;
        this.address=null;
        this.vendorName=null;
        this.vendUID=null;
        this.userUID=null;
        this.rating=0;
        this.review="";
        this.status=0;
    }

    public void setPrice(float servPrice)
    {
        price=servPrice;
    }
    public void setAppt(String apptDate, String apptTime)
    {
        date=apptDate;
        time=apptTime;
    }
    public void setAddress(String addr)
    {
        address=addr;
    }
    public void setVendorName(String vendName)
    {
        vendorName=vendName;
    }
    public void setVendUID(String vendorUID)
    {
        vendUID=vendorUID;
    }
    public void setUserUID(String userUID)
    {
        userUID=userUID;
    }
    public void setRating(int stars)
    {
        rating=stars;
    }
    public void setReview(String comment)
    {
        review=comment;
    }
    public void setStatus(int stat)
    {
        status=stat;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object>newOrder=new HashMap<>();
        newOrder.put("price",price);
        newOrder.put("apptDate",date);
        newOrder.put("apptTime",time);
        newOrder.put("apptAddr",address);
        newOrder.put("vendName",vendorName);
        newOrder.put("vendUID",vendUID);
        newOrder.put("userUID",userUID);
        newOrder.put("rating",rating);
        newOrder.put("review",review);
        newOrder.put("completed",status);

        return newOrder;
    }

    public boolean uploadOrder(DatabaseReference database, String key)
    {
        final boolean[] success = {false};
        Map<String,Object> orderDetails=toMap();
        Map<String,Object> childUpdate=new HashMap<>();
        childUpdate.put("/Orders/"+key,orderDetails);

        database.updateChildren(childUpdate).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                success[0] =true;
            }
        });
        return success[0];
    }
}