package com.example.homesc;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

public class RateOrder3 extends AppCompatActivity {

    TextView rateCount, showRating;
    EditText review;
    Button submit;
    RatingBar ratingBar;
    float rateValue; String temp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_order1);

        rateCount = findViewById(R.id.rateCount);
        ratingBar = findViewById(R.id.ratingBar);
        review = findViewById(R.id.review);
        submit = findViewById(R.id.submitButton);
        showRating = findViewById(R.id.showRating);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

                rateValue = ratingBar.getRating();

                if (rateValue<=5 && rateValue>0)
                    rateCount.setText("Rated: " +rateValue+ "/5");

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                temp = rateCount.getText().toString();
                showRating.setText("Your Rating: \n" +temp+ "\n" +review.getText());
                review.setText("");
                ratingBar.setRating(0);
                rateCount.setText("");
            }
        });
    }
}