package com.example.homesc;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener{

    private TextView  registerUser, registerVendor;
    private EditText editTextFullName, editTextPhoneNumber, editTextEmail, editTextPassword;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    public int userID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mAuth = FirebaseAuth.getInstance();

        registerVendor = (Button) findViewById(R.id.BecomeAvendor);
        registerVendor.setOnClickListener(this);

        registerUser = (Button) findViewById(R.id.registerUser);
        registerUser.setOnClickListener(this);






        editTextFullName = (EditText) findViewById(R.id.fullname);
        editTextPhoneNumber = (EditText) findViewById(R.id.phonenumber);
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.password);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);






    }

    public void onClick(View view)
    {
        if(view.getId() == R.id.registerUser)
        {
            registerUser();
        }
        else if(view.getId() == R.id.BecomeAvendor)
        {
            Intent intent = new Intent(this,RegisterVendor.class);
            startActivity(intent);
       // startActivity(new Intent(this, RegisterVendor.class));
        }
        else
        {

        }

    }

    public void registerUser()
    {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String phoneNumber = editTextPhoneNumber.getText().toString().trim();
        String FullName = editTextFullName.getText().toString().trim();
       int  numberOfPointsAvailable=0;

        if(FullName.isEmpty())
        {
            editTextFullName.setError("Full name is required");
            editTextFullName.requestFocus();
        }
        else if(phoneNumber.isEmpty())
        {
            editTextPhoneNumber.setError("Phone number is required");
            editTextPhoneNumber.requestFocus();
        }
        else if(password.isEmpty())
        {
            editTextPassword.setError("A password is required");
            editTextPassword.requestFocus();
        }
        else if(email.isEmpty())
        {
            editTextEmail.setError("An email is required");
            editTextEmail.requestFocus();
        }
        else if(Patterns.EMAIL_ADDRESS.matcher(email).matches() == false)
        {
            editTextEmail.setError("Please provide a valid email");
            editTextEmail.requestFocus();
        }
        else if(Patterns.PHONE.matcher(phoneNumber).matches() == false)
        {
            editTextPhoneNumber.setError("Please provide a valid Phone Number");
            editTextPhoneNumber.requestFocus();

        }
        else if(password.length()<8)
        {
            editTextPassword.setError("Please create a password that is at least 8 characters");
            editTextPassword.requestFocus();

        }
        else
        {

        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())
                {

                    User u1 = new User(FullName, email, phoneNumber, numberOfPointsAvailable);
                    FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(u1).addOnCompleteListener(new OnCompleteListener<Void>() {


                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(RegisterUser.this, "User has been registered successfully", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);

                            }
                            else
                            {
                                Toast.makeText(RegisterUser.this, "Failed to register User. Try again", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                            }

                        }
                    });
                }
                else
                {
                    Toast.makeText(RegisterUser.this, "Failed to register User. Try again", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });





    }



}