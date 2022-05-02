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

    EditText addrLine1Input;
    EditText addrLine2Input;
    EditText cityInput;
    EditText stateInput;
    //Spinner stateInput;
    EditText zipcodeInput;

    Button dateBtn;
    TextView date;
    DatePickerDialog datePickerDiag;

    Button timeBtn;
    TextView time;
    TimePickerDialog timePickerDiag;
    int vendOpen=8;
    int vendClose=20;
    //currently hard coded, needs to be changed to check vendor data in database when whoever
    //was in charge of that adds the test data



    TextView vendName;

    private Button confirmBtn;

    Calendar calendar=Calendar.getInstance();
    int year=calendar.get(Calendar.YEAR); //sets year to current
    int month= calendar.get(Calendar.MONTH); //sets month to current
    int day= calendar.get(Calendar.DAY_OF_MONTH); //sets day to current

    int AMPM=calendar.get(Calendar.AM_PM);
    int hour=AMPM==1?calendar.get(Calendar.HOUR)-12:calendar.get(Calendar.HOUR);
    int min=calendar.get(Calendar.MINUTE);

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_req);

        getSupportActionBar().setTitle("Set Appointment"); //renames action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //enables back arrow in top bar, parent activity needs to be set in manifest

        addrLine1Input =(EditText)findViewById(R.id.addrLine1);
        addrLine2Input =(EditText)findViewById(R.id.addrLine2);
        cityInput=(EditText)findViewById(R.id.city);
        stateInput=(EditText)findViewById(R.id.state);
        //stateInput=(Spinner)findViewById(R.id.state);
        zipcodeInput=(EditText)findViewById(R.id.zipcode);

        addrLine1Input.addTextChangedListener(addressTextWatch);
        addrLine2Input.addTextChangedListener(addressTextWatch);
        cityInput.addTextChangedListener(addressTextWatch);
        //stateInput.addTextChangedListener(addressTextWatch);
        zipcodeInput.addTextChangedListener(addressTextWatch);

        vendName=(TextView) findViewById(R.id.vendNameText);
        vendorName=getIntent().getStringExtra("vendName");
        vendName.setText(vendorName);

        dateBtn=(Button)findViewById(R.id.dateButton);
        date=(TextView) findViewById(R.id.dateDisp);

        timeBtn=(Button)findViewById(R.id.timeButton);
        time=(TextView) findViewById(R.id.timeDisp);

        //ArrayAdapter state

        /*stateInput.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {

            }
        });*/


        //change time placeholder text to current time+one hour
        if((hour+2)<vendOpen) //checks if current hour+2 is within vendor hours. if before open, sets to when vendor opens
        {
            hour=hour+(vendOpen-hour);
        }
        else if((hour+2)>vendClose)//if after close, sets to morning of day after tomorrow
        {
            day=day+1;
            hour=8;
        }
        else
        {
            hour = hour + 2;
        }

        date.setText((month+1) + "/" + (day+1) + "/" + year); //changes placeholder text showing tomorrow's date

        dayOrNight=hour<12?"AM":"PM"; //checks whether current time is am or pm
        longHour=hour<13?String.valueOf(hour):String.valueOf(hour-12); //formats hour to 12 hour format
        longMin="00"; //rounds to nearest hour

        time.setText(longHour + ":" + longMin + " " + dayOrNight);

        dateBtn.setOnClickListener(new View.OnClickListener(){ //select date button
            @Override
            public void onClick(View v) {
                datePick();
                dateBtn.setText("Change Date");
            }
        });

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

            String addrLine1=addrLine1Input.getText().toString().trim();
            String addrLine2=addrLine2Input.getText().toString().trim();

            if(addrLine2.isEmpty())
            {
                street=addrLine1;
            }
            else
            {
                street=addrLine1+", "+addrLine2;
            }
            city=cityInput.getText().toString().trim();
            state=stateInput.getText().toString().trim();
            //get spinner pos
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
                            dayOrNight=hour<12?"AM":"PM"; //checks whether appointment is am or pm
                            longHour=hour<13?String.valueOf(hour):String.valueOf(hour-12); //formats hour to 12 hour format
                            longMin=min<10?"0"+min: String.valueOf(min); //adds an extra 0 to the front if only single digit
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
