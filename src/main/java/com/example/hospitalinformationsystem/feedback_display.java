package com.example.hospitalinformationsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class feedback_display extends AppCompatActivity {

    RecyclerView req_list;
    UserDB mydb;
    feedbackdisplaymodel[] record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_display);


        mydb=new UserDB(this);

        record=mydb.selectfeedback();

        if(record == null) {
            Toasty.warning(feedback_display.this, "No record Found", Toast.LENGTH_SHORT).show();
        }
        else
        {
            for(feedbackdisplaymodel req:record)
            {
                Log.d("name",req.getName());
                Log.d("email",req.getEmail());
                Log.d("mobile",req.getMobile());
                Log.d("writefeedback",req.getWritefeedback());

            }


            feedbackdisplayadapter rvadapter = new feedbackdisplayadapter();
            rvadapter.setItems(record);
            req_list = findViewById(R.id.reqview);
            Log.d("Length", String.valueOf(rvadapter.getItemCount()));
            req_list.setAdapter(rvadapter);
        }


    }
}