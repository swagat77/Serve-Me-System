package com.example.homesc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class orderChange extends AppCompatActivity {
    String orderUID;
    String vendName;
    String apptAddr;

    EditText addrLine1Input;
    EditText addrLine2Input;
    EditText cityInput;
    EditText stateInput;
    EditText zipcodeInput;

    Button dateBtn;
    TextView date;
    DatePickerDialog datePickerDiag;

    Button timeBtn;
    TextView time;
    TimePickerDialog timePickerDiag;
    int vendOpen = 8;
    int vendClose = 20;

    TextView vendNameLabel;

    private Button confirmBtn;

    Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR); //sets year to current
    int month = calendar.get(Calendar.MONTH); //sets month to current
    int day = calendar.get(Calendar.DAY_OF_MONTH); //sets day to current

    String dayOrNight;
    String longHour;
    String longMin;

    String apptTime;
    String apptDate;

    String street;
    String city;
    String state;
    String zipcode;

    String address;

    DatabaseReference database= FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_change);

        getSupportActionBar().setTitle("Edit Appointment"); //renames action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //enables back arrow in top bar

        addrLine1Input = (EditText) findViewById(R.id.addrLine1);
        addrLine2Input=(EditText)findViewById(R.id.addrLine2);
        cityInput = (EditText) findViewById(R.id.city);
        stateInput = (EditText) findViewById(R.id.state);
        zipcodeInput = (EditText) findViewById(R.id.zipcode);

        orderUID = getIntent().getStringExtra("orderUID");
        apptAddr=getIntent().getStringExtra("apptAddr");
        apptDate=getIntent().getStringExtra("apptDate");
        apptTime=getIntent().getStringExtra("apptTime");
        vendName =getIntent().getStringExtra("vendName");

        String addrSplit[]=apptAddr.split(", ");

        //tokenize address
        switch (addrSplit.length)
        {
            case 4:
                addrLine1Input.setText(addrSplit[0]);
                cityInput.setText(addrSplit[1]);
                stateInput.setText(addrSplit[2]);
                zipcodeInput.setText(addrSplit[3]);
                break;
            case 5:
                addrLine1Input.setText(addrSplit[0]);
                addrLine2Input.setText(addrSplit[1]);
                cityInput.setText(addrSplit[2]);
                stateInput.setText(addrSplit[3]);
                zipcodeInput.setText(addrSplit[4]);
                break;
            default:
                Toast.makeText(this, "Error retrieving address info", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }

        vendNameLabel = (TextView) findViewById(R.id.vendNameText);

        vendNameLabel.setText(vendName);

        dateBtn = (Button) findViewById(R.id.dateButton);
        date = (TextView) findViewById(R.id.dateDisp);

        timeBtn=(Button)findViewById(R.id.timeButton);
        time=(TextView) findViewById(R.id.timeDisp);

        date.setText(apptDate); //changes text to apptDate
        time.setText(apptTime);

        dateBtn.setOnClickListener(new View.OnClickListener() { //select date button
            @Override
            public void onClick(View v) {
                datePick();
                dateBtn.setText("Change Date");
            }
        });

        timeBtn.setOnClickListener(new View.OnClickListener() { //select time button
            @Override
            public void onClick(View v) {
                timePick();
                timeBtn.setText("Change Time");
            }
        });

        confirmBtn = (Button) findViewById(R.id.confirmButton);
        confirmBtn.setOnClickListener(new View.OnClickListener() { //confirm button
            @Override
            public void onClick(View v) {
                street = addrLine1Input.getText().toString().trim()+ addrLine2Input.getText().toString().trim();
                city = cityInput.getText().toString().trim();
                state = stateInput.getText().toString().trim();
                zipcode = zipcodeInput.getText().toString().trim();

                address = street + ", " + city + ", " + state + ", " + zipcode;

                if(!(street.isEmpty()&&city.isEmpty()&&state.isEmpty()&&zipcode.isEmpty()))
                {
                    uplUpd();
                }
                else
                {
                    Toast.makeText(orderChange.this, "Invalid input", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void uplUpd()
    {
        database.child("Orders").child(orderUID).child("apptAddr").setValue(address);
        database.child("Orders").child(orderUID).child("apptDate").setValue(date.getText().toString());
        database.child("Orders").child(orderUID).child("apptTime").setValue(time.getText().toString());

        Toast.makeText(this, "Order updated", Toast.LENGTH_LONG).show();
        finish();
    }

    public void datePick() //date picker dialog
    {
        datePickerDiag = new DatePickerDialog(orderChange.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        apptDate = (month + 1) + "/" + day + "/" + year;

                        date.setText(apptDate); //changes date textView to what user selected
                    }
                }, year, month, day);
        datePickerDiag.show();
        datePickerDiag.getDatePicker().setMinDate(System.currentTimeMillis() + 24 * 60 * 60 * 1000); //sets minimum date in dialog to tomorrow, no time travelling
    }
    public void timePick() {
        timePickerDiag = new TimePickerDialog(orderChange.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int min) {
                        time.setText(apptTime);
                        if (hour < vendOpen || hour > vendClose) //checks if time selected is within business hours
                        {
                            Toast.makeText(orderChange.this, "Vendor will be closed, select a different time", Toast.LENGTH_LONG).show();
                        } else {
                            dayOrNight = hour < 12 ? "AM" : "PM"; //checks whether appointment is am or pm
                            longHour = hour < 13 ? String.valueOf(hour) : String.valueOf(hour - 12); //formats hour to 12 hour format
                            longMin = min < 10 ? "0" + min : String.valueOf(min); //adds an extra 0 to the front if only single digit
                            apptTime = longHour + ":" + longMin + " " + dayOrNight;

                            time.setText(apptTime); //does not change textview until valid hours are selected
                        }
                    }
                }, 0, 0, false);

        timePickerDiag.show();
    }
    @Override
    public void onBackPressed() {
        setResult(2);
        super.onBackPressed();
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case android.R.id.home:
                setResult(2);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}