package com.example.hospitalinformationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import es.dmoral.toasty.Toasty;

public class BedRequest extends AppCompatActivity {

    String tohos;
    String area;
    Button req;
    EditText pname;
    RequestRes mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bed_request);

        req=findViewById(R.id.req_btn);
        pname=findViewById(R.id.pname);

        Intent extradata=getIntent();
        tohos=extradata.getStringExtra("Hospital Name");
        area=extradata.getStringExtra("Area");

        mydb=new RequestRes(this);

        req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String currentDateandTime = sdf.format(new Date());


                Boolean Result=mydb.insertReq(currentDateandTime,pname.getText().toString(),"Bed Request","Pending",tohos,area);

                if (Result == true){
                    Toasty.success(BedRequest.this, "Request Added Successful", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toasty.error(BedRequest.this, "Registration failed", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }
}