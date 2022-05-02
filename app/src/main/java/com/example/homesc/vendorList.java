package com.example.homesc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import java.util.List;

public class vendorList extends AppCompatActivity {

    String category;
    TextView categoryLabel;
    ListView vendList;

    List vendNames=new ArrayList<String>();
    List vendUID=new ArrayList<String>();

    DatabaseReference database= FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_list);

        categoryLabel=(TextView) findViewById(R.id.categoryLabel);
        vendList=(ListView) findViewById(R.id.vendList);

        category=getIntent().getStringExtra("category");
        categoryLabel.setText(category);

        Query vendQuery=database.child("Vendors").orderByChild("category").equalTo(category);
        vendQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        vendNames.add(child.child("companyName").getValue().toString());
                        vendUID.add(child.getKey());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(vendorList.this, android.R.layout.simple_list_item_1,vendNames);

                    vendList.setAdapter(adapter);

                    vendList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> arg0, View view, int pos,
                                                long id) {
                            Intent intent=new Intent(vendorList.this, vendProf.class);
                            intent.putExtra("vendUID", (String) vendUID.get(pos));
                            startActivity(intent);
                            finish();
                        }

                    });
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(vendorList.this,"Error retrieving vendor information",Toast.LENGTH_LONG);
            }
        });

    }
}