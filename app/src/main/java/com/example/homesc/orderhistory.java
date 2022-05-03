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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderhistory);

        getSupportActionBar().setTitle("Order History"); //renames action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //enables back arrow in top bar

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
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}