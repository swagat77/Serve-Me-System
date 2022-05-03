package com.example.homesc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RegisterVendor extends AppCompatActivity implements View.OnClickListener{

    private TextView registerVendor;
    private EditText editTextFullName, editTextPhoneNumber, editTextEmail, editTextPassword, editTextAddress, editTextCategory;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private CheckBox checkBox;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_vendor);

        getSupportActionBar().setTitle("Vendor Registration"); //renames action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //enables back arrow in top bar

        mAuth = FirebaseAuth.getInstance();

        registerVendor = (Button) findViewById(R.id.registerVendor);
        registerVendor.setOnClickListener(this);

        editTextFullName = (EditText) findViewById(R.id.companyname);
        editTextPhoneNumber = (EditText) findViewById(R.id.companyphonenumber);
        editTextEmail = (EditText) findViewById(R.id.companyemail);
        editTextPassword = (EditText) findViewById(R.id.companypassword);
        editTextAddress = (EditText) findViewById(R.id.companyAddress);
        editTextCategory = (EditText) findViewById(R.id.category);
        checkBox = (CheckBox) findViewById(R.id.checkBox);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }
    public void onClick(View view)
    {
        if (view.getId() == R.id.registerVendor) {
            registerVendor();
        }
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
    public void registerVendor()
    {
        String companyemail = editTextEmail.getText().toString().trim();
        String companypassword = editTextPassword.getText().toString().trim();
        String companyphoneNumber = editTextPhoneNumber.getText().toString().trim();
        String companyFullName = editTextFullName.getText().toString().trim();
        String companyAddress = editTextAddress.getText().toString().trim();
        String category = editTextCategory.getText().toString().trim();

        if(companyFullName.isEmpty())
        {
            editTextFullName.setError("A Company Name is required");
            editTextFullName.requestFocus();
        }
        else if(companyphoneNumber.isEmpty())
        {
            editTextPhoneNumber.setError("A Phone number is required");
            editTextPhoneNumber.requestFocus();
        }
        else if(companypassword.isEmpty())
        {
            editTextPassword.setError("A password is required");
            editTextPassword.requestFocus();
        }
        else if(companyemail.isEmpty())
        {
            editTextEmail.setError("An email is required");
            editTextEmail.requestFocus();
        }
        else if(Patterns.EMAIL_ADDRESS.matcher(companyemail).matches() == false)
        {
            editTextEmail.setError("Please provide a valid email");
            editTextEmail.requestFocus();
        }
        else if(Patterns.PHONE.matcher(companyphoneNumber).matches() == false)
        {
            editTextPhoneNumber.setError("Please provide a valid Phone Number");
            editTextPhoneNumber.requestFocus();
        }
        else if(companypassword.length()<8)
        {
            editTextPassword.setError("Please create a password that is at least 8 characters");
            editTextPassword.requestFocus();

        }
        else if(checkBox.isChecked() == false)
        {
            checkBox.setError("Please agree to the stated terms");
            checkBox.requestFocus();

        }
        else
        {

        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(companyemail, companypassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())
                {

                    Vendor v1 = new Vendor(companyFullName, companyemail, companyphoneNumber, companyAddress, category);
                    FirebaseDatabase.getInstance().getReference("Vendors").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(v1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                //Toast.makeText(RegisterVendor.this, "Vendor has been registered successfully",Toast.LENGTH_LONG).show();
                                Toast.makeText(RegisterVendor.this, "Vendor has been registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterVendor.this, VendorHomePage.class);
                                progressBar.setVisibility(View.GONE);

                            }
                            else
                            {
                                Toast.makeText(RegisterVendor.this, "Failed to register Vendor. Try again", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                            }

                        }
                    });
                }
                else
                {
                    Toast.makeText(RegisterVendor.this, "Failed to register Vendor. Try again", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

    }
}