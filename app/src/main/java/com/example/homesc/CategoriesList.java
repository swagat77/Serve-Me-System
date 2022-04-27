/*package com.example.homesc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CategoriesList extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_list);
         database = FirebaseDatabase.getInstance();
        category = database.getReference("Vendors").child(Constant.VendorSelected).child("detail").child("Category");
        Object firebaseRecyclerOptions = new Builder<Category>().setQuery(category, Category.class).build();

    }

    private class Builder<T> {
    }
}*/