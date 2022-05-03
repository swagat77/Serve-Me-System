package com.example.homesc;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PersonalInformation extends AppCompatActivity {
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    /*private TextView fullnametextview;
    private TextView emailtextview;
    private TextView phonenumbertextview;
    private String email, password;
    TextView myTextView;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private static final String USERS = "Users";*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_personal_information);

        getSupportActionBar().setTitle("Personal Information"); //renames action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //enables back arrow in top bar

        user=FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Users");
        userID=user.getUid();
        final TextView fullnameTextView=(TextView) findViewById(R.id.fullName);
        final TextView phonenumberTextView=(TextView) findViewById(R.id.phonenumber);
        final TextView  emailTextView=(TextView) findViewById(R.id.emailAddress);

       // final TextView numberOfPointsAvailableTextView=(TextView) findViewById(R.id.numberOfPointsAvailables);
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if(userProfile != null)
                {
                    String fullName = userProfile.fullName;
                    String userEmail = userProfile.userEmail;
                    int numberOfAvailablePoints = userProfile.numberOfAvailablePoints;
                    String userPhone =userProfile.userPhone;
                    fullnameTextView.setText(fullName);
                    phonenumberTextView.setText(userPhone);
                    emailTextView.setText(userEmail);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(PersonalInformation.this,"Did not work",Toast.LENGTH_SHORT).show();
            }

            //  @Override
                //   public void onCancelled(@NonNull DatabaseError error) {
                //  Toast.makeText(UserHomePage.this, "ERROR, Toast.LENGTH_SHORT").show();

                // }
            });; /* Intent intent = getIntent();
        email = intent.getStringExtra("email");
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userRef = rootRef.child(USERS);
        Log.v("USERID", userRef.getKey());
        fullnametextview = findViewById(R.id.fullname_textview);
        emailtextview = findViewById(R.id.email_textview);
        phonenumbertextview = findViewById(R.id.phonenumber_textview);
        mAuth = FirebaseAuth.getInstance();
        userRef.addValueEventListener(new ValueEventListener() {
            String full_name, email, phonenumber, password;

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot keyId : snapshot.getChildren()) {
                    if (keyId.child("email").getValue().equals(email)) {
                        full_name = keyId.child("Name").getValue(String.class);
                        phonenumber = keyId.child("Phone number").getValue(String.class);
                        break;
                    }
                }

                fullnametextview.setText(full_name);
                emailtextview.setText(email);
                phonenumbertextview.setText(phonenumber);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Cannot read value");
            }
        });*/
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
