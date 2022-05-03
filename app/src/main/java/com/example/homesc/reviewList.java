package com.example.homesc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class reviewList extends AppCompatActivity {

    String vendUID;
    String vendName;

    TextView reviewLabel;
    ListView reviewList;

    DatabaseReference database= FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_list);

        vendUID=getIntent().getStringExtra("vendUID");
        vendName=getIntent().getStringExtra("vendName");

        reviewLabel=(TextView) findViewById(R.id.reviewListLabel);
        reviewList=(ListView) findViewById(R.id.reviewList);

        reviewLabel.setText("All Reviews for "+vendName);

        Query ordQuery=database.child("Orders").orderByChild(vendUID);

        ordQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    ArrayList<reviewItem> reviews=new ArrayList<>();
                    for(DataSnapshot order:snapshot.getChildren())
                    {
                        if(vendUID.equals(order.child("vendUID").getValue().toString()))
                        {
                            if(Float.parseFloat(order.child("rating").getValue().toString())>=0)
                            {
                                reviewItem reviewItem=new reviewItem("placeholder",
                                        Float.parseFloat(order.child("rating").getValue().toString()),
                                        order.child("review").getValue().toString());
                                reviews.add(reviewItem);

                                /*if(order.child("review").getValue().toString()==null)
                                {
                                    reviewItem reviewItem=new reviewItem("placeholder",
                                            Float.parseFloat(order.child("rating").getValue().toString()),
                                            "");
                                    reviews.add(reviewItem);
                                }
                                else
                                {
                                    reviewItem reviewItem=new reviewItem("placeholder",
                                            Float.parseFloat(order.child("rating").getValue().toString()),
                                            order.child("review").getValue().toString());
                                    reviews.add(reviewItem);
                                }*/

                            }
                            else
                            {
                                continue;//not rated yet
                            }

                        }
                        else
                        {
                            continue;//not vendor's review
                        }
                    }

                    reviewListAdapter reviewListAdapter=new reviewListAdapter(reviewList.this,R.layout.adapter_view_layout_review_list,reviews);
                    reviewList.setAdapter(reviewListAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(reviewList.this, "Error retrieving reviews", Toast.LENGTH_LONG).show();
            }
        });

    }
}