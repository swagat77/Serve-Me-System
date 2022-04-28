package com.example.homesc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class placeReq extends AppCompatActivity {

    String vendorName;

    EditText streetInput;
    EditText cityInput;
    EditText stateInput;
    EditText zipcodeInput;

    Button dateBtn;
    TextView date;
    DatePickerDialog datePickerDiag;

    Button timeBtn;
    TextView time;
    TimePickerDialog timePickerDiag;
    int vendOpen=8; //set to check from firebase or smth
    int vendClose=20; //same as above

    TextView vendName;

    private Button confirmBtn;

    Calendar calendar=Calendar.getInstance();
    int year=calendar.get(Calendar.YEAR); //sets year to current
    int month= calendar.get(Calendar.MONTH); //sets month to current
    int day= calendar.get(Calendar.DAY_OF_MONTH); //sets day to current

    String apptTime;
    String apptDate;

    String street;
    String city;
    String state;
    String zipcode;

    String address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_req);

        getSupportActionBar().setTitle("Set Appointment"); //renames action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //enables back arrow in top bar

        streetInput=(EditText)findViewById(R.id.street);
        cityInput=(EditText)findViewById(R.id.city);
        stateInput=(EditText)findViewById(R.id.state);
        zipcodeInput=(EditText)findViewById(R.id.zipcode);

        streetInput.addTextChangedListener(addressTextWatch);
        cityInput.addTextChangedListener(addressTextWatch);
        stateInput.addTextChangedListener(addressTextWatch);
        zipcodeInput.addTextChangedListener(addressTextWatch);

        vendName=(TextView) findViewById(R.id.vendNameText);
        vendorName =getIntent().getStringExtra("vendName");
        vendName.setText(vendorName);

        dateBtn=(Button)findViewById(R.id.dateButton);
        date=(TextView) findViewById(R.id.dateDisp);

        date.setText((month+1) + "/" + (day+1) + "/" + year); //changes placeholder text showing tomorrow's date

        dateBtn.setOnClickListener(new View.OnClickListener(){ //select date button
            @Override
            public void onClick(View v) {
                datePick();
                dateBtn.setText("Change Date");
            }
        });

        timeBtn=(Button)findViewById(R.id.timeButton);
        time=(TextView) findViewById(R.id.timeDisp);

        timeBtn.setOnClickListener(new View.OnClickListener(){ //select time button
            @Override
            public void onClick(View v){
                timePick();
                timeBtn.setText("Change Time");
            }
        });

        confirmBtn=(Button)findViewById(R.id.confirmButton);
        confirmBtn.setOnClickListener(new View.OnClickListener(){ //confirm button
            @Override
            public void onClick(View v){
                if(zipcodeInput.getText().toString().length()==5)
                {
                    openNextAct();
                }
                else
                {
                    Toast.makeText(placeReq.this, "Invalid zipcode. Please Re-enter", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    private TextWatcher addressTextWatch=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            street=streetInput.getText().toString().trim();
            city=cityInput.getText().toString().trim();
            state=stateInput.getText().toString().trim();
            zipcode=zipcodeInput.getText().toString().trim();

            address=street+", "+city+", "+state+", "+zipcode;
            confirmBtn.setEnabled(!(street.isEmpty()&&city.isEmpty()&&state.isEmpty()&&zipcode.isEmpty()));
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    public void datePick() //date picker dialog
    {
        datePickerDiag = new DatePickerDialog(placeReq.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        apptDate=(month+1) + "/" + day + "/" + year;

                        date.setText(apptDate); //changes date textView to what user selected
                    }
                }, year, month, day);
        datePickerDiag.show();
        datePickerDiag.getDatePicker().setMinDate(System.currentTimeMillis()+24*60*60*1000); //sets minimum date in dialog to tomorrow, no time travelling
    }

    public void timePick()
    {
        timePickerDiag=new TimePickerDialog(placeReq.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int min) {
                        if(hour<vendOpen||hour>vendClose) //checks if time selected is within business hours
                        {
                            Toast.makeText(placeReq.this, "Vendor will be closed, select a different time", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            final String dayOrNight=hour<12?"AM":"PM"; //checks whether appointment is am or pm
                            final String longHour=hour<13?String.valueOf(hour):String.valueOf(hour-12); //formats hour to 12 hour format
                            final String longMin=min<10?"0"+min: String.valueOf(min); //adds an extra 0 to the front if only single digit
                            apptTime=longHour + ":" + longMin + " " + dayOrNight;

                            time.setText(apptTime); //does not change textview until valid hours are selected
                        }
                    }
                },0,0,false);

        timePickerDiag.show();
    }
    public void openNextAct()
    {
        String apptTime=time.getText().toString();
        String apptDate=date.getText().toString();

        if(!(street.isEmpty()&&city.isEmpty()&&state.isEmpty()&&zipcode.isEmpty()&&apptTime.isEmpty()&&apptDate.isEmpty()))
        {
            Intent intent = new Intent();
            intent.putExtra("address", address);
            intent.putExtra("time",apptTime);
            intent.putExtra("date",apptDate);

            setResult(RESULT_OK, intent);
        }
        else
        {
            setResult(RESULT_CANCELED);
        }
        finish();
    }

}
