package com.example.homesc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MenuItem;
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
import com.google.firebase.auth.FirebaseUser;

public class VendorLogin extends AppCompatActivity implements View.OnClickListener{
    public TextView register;
    private EditText editEmail, editPassword;
    private Button signIn;
    private Button VendorsignIn;
    public ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_login);

        getSupportActionBar().setTitle("Vendor Login"); //renames action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //enables back arrow in top bar

        register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(this);
        signIn = (Button) findViewById(R.id.signIN);
        signIn.setOnClickListener(this);
        editEmail = (EditText) findViewById(R.id.email);
        editPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();
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

    public void onClick(View view) {
        if(view.getId() == R.id.register)
        {
            startActivity(new Intent(this, RegisterVendor.class));
        }
        else if(view.getId()==R.id.signIN)
        {
            VendorLogin();
        }
        else if(view.getId()==R.id.forgotPassword)
        {
            startActivity(new Intent(this, forgotPassword.class));
        }
        else
        {


        }


    }


    public void VendorLogin() {
        String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();


        if (email.isEmpty()) {
            editEmail.setError("An email is required");
            editEmail.requestFocus();

        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editEmail.setError("A valid email is required");
            editEmail.requestFocus();
        } else if (password.isEmpty()) {
            editPassword.setError("A password is required");
            editPassword.requestFocus();
        } else {
            progressBar.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {




                    if (task.isSuccessful()) {
                        FirebaseUser vendor = FirebaseAuth.getInstance().getCurrentUser();

                            //go to home page for vendor
                            Intent intent = new Intent(VendorLogin.this, VendorHomePage.class);
                            startActivity(intent);
                            finishAffinity();
                        }
                        else
                        {

                            Toast.makeText(VendorLogin.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        }


                    /*} else {
                        Toast.makeText(VendorLogin.this, "Login Failed", Toast.LENGTH_LONG).show(); */
                    //}
                }
            });
        }
    }
}