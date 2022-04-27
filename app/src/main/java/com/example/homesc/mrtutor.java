
package com.example.homesc;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.Query;
        import com.google.firebase.database.ValueEventListener;

public class mrtutor extends AppCompatActivity {
    String vendUID="LirqOIhWskSPdvkmIUz6ngKmO9K2"; //value currently hardcoded, should be passed in by previous activity
    int vendID=1001;

    TextView vendName;
    TextView vendAddr;
    TextView vendMail;
    TextView vendPhone;
    TextView vendRating;
    TextView vendOrders;

    String s;

    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mrtutor);

        getSupportActionBar().setTitle("Vendor's Profile"); //renames action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //enables back arrow in top bar

        vendName=(TextView) findViewById(R.id.vendNameText);
        vendAddr=(TextView) findViewById(R.id.vendAddr);
        vendMail=(TextView) findViewById(R.id.vendMail);
        vendPhone=(TextView) findViewById(R.id.vendPhone);
        vendRating=(TextView) findViewById(R.id.vendRating);
        vendOrders=(TextView) findViewById(R.id.vendOrders);

        DatabaseReference database= FirebaseDatabase.getInstance().getReference();
        Query q=database.child("Vendors").orderByChild(vendUID);

        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    s=dataSnapshot.child(vendUID).child("companyName").getValue().toString();

                    vendName.setText(s);
                    vendAddr.setText("Address: "+dataSnapshot.child(vendUID).child("address").getValue().toString());
                    vendMail.setText("Email: "+dataSnapshot.child(vendUID).child("companyEmail").getValue().toString());
                    vendPhone.setText("Phone: "+dataSnapshot.child(vendUID).child("companyPhone").getValue().toString());
                    //vendRating.setText();
                    //vendOrders.setText();
                }
                else{
                    Toast.makeText(mrtutor.this, "Error retrieving vendor information", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(mrtutor.this, "Error retrieving vendor information", Toast.LENGTH_LONG).show();
            }
        });


        /*button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openPlaceReq();
            }
        });*/

    }

   /* public void openPlaceReq()
    {
        Intent intent=new Intent(this,placeReq.class);
        intent.putExtra("vendName",s);
        startActivity(intent);
    }*/
}