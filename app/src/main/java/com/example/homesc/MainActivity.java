package com.example.homesc;

import androidx.annotation.NonNull;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public TextView register, forgotPassword;
    private EditText editEmail, editPassword;
    private Button signIn;
    private Button VendorsignIn;
    public ProgressBar progressBar;
    private FirebaseAuth mAuth;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userhomepage);



        VendorsignIn = (Button) findViewById(R.id.VendorLogin);
        VendorsignIn.setOnClickListener(this);

        forgotPassword = (TextView) findViewById(R.id.forgotPassword);
        forgotPassword.setOnClickListener(this);


        register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(this);
        signIn = (Button) findViewById(R.id.signIN);
        signIn.setOnClickListener(this);
        editEmail = (EditText) findViewById(R.id.email);
        editPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();


    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.register)
        {
            startActivity(new Intent(this, RegisterUser.class));
        }
        else if(view.getId()==R.id.signIN)
        {
            userLogin();
        }
       else if(view.getId() == R.id.VendorLogin)
        {
            startActivity(new Intent(this, VendorLogin.class));
        }
       else if(view.getId() == R.id.forgotPassword)
        {
            startActivity(new Intent(this, forgotPassword.class));
        }
       else
        {

        }


    }
    public void userLogin() {
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
                        //go to home page for user

                            Intent intent = new Intent(MainActivity.this, UserHomePage.class);
                            startActivity(intent);
                        }

                     else {
                        Toast.makeText(MainActivity.this, "Login Failed ", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);



                    }

                }
            });
        }
    }
}