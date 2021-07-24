package com.example.hospitalinformationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class Feedback extends AppCompatActivity {

    EditText name,email,mobile_number,feedback_write;
    Button feedback_submit_btn;
    ImageView toolbar_back;
    RatingBar feedback_rating;
    UserDB myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        getSupportActionBar().hide();

        name = findViewById(R.id.feedback_name);
        email = findViewById(R.id.feedback_email);
        mobile_number = findViewById(R.id.feedback_number);
        feedback_write = findViewById(R.id.feedback_write);
        feedback_submit_btn = findViewById(R.id.feedback_submit_btn);
        feedback_rating = findViewById(R.id.feedback_rating);
        toolbar_back = findViewById(R.id.toolbar_back);

         myDB = new UserDB(this);

        toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Feedback.this,Home.class);
                startActivity(intent);
                finish();
            }
        });

        feedback_submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name1 = name.getText().toString();
                String email1 = email.getText().toString();
                String mobile1 = mobile_number.getText().toString();
                String writefeedback = feedback_write.getText().toString();

                if (name1.equals("") || email1.equals("") ||mobile1.equals("") || writefeedback.equals("")){
                    Toasty.warning(Feedback.this, "Fill all the fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean result = myDB.insertfeedback(name1,email1,mobile1,writefeedback);
                    if (result == true){
                        Toasty.success(Feedback.this, "Feedback Add Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Feedback.this,Home.class);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Toasty.error(Feedback.this, "failed", Toast.LENGTH_SHORT).show();
                            }
                }
            }
        });
    }
}