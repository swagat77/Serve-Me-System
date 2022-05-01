package com.example.homesc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class orderhistory extends AppCompatActivity {

    List orders=new ArrayList<String>();
    List orderUID=new ArrayList<String>();

    DatabaseReference database= FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderhistory);

        ListView orderhistory = findViewById(R.id.orderhistory);

        List<String> orders = new ArrayList<>();
        orders.add("Order 1003439256");
        orders.add("Order 1007872374");
        orders.add("Order 1002838748");

        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, orders);

        orderhistory.setAdapter(arrayAdapter);

        orderhistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if(i==0) {

                    startActivity(new Intent(orderhistory.this, Order1Activity.class));

                }
                else if(i==1) {
                    startActivity(new Intent(orderhistory.this, Order2Activity.class));
                }
                else if(i==2){
                    startActivity(new Intent(orderhistory.this, Order3Activity.class));
                }
            }
        });
    }


}