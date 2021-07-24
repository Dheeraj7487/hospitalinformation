package com.example.hospitalinformationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import es.dmoral.toasty.Toasty;

public class Case_Transfer extends AppCompatActivity {
    String tohos;
    String area;
    TextView tohosp;
    EditText patname;
    Button transfer;
    RequestRes mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_transfer);
        Intent extradata=getIntent();
        tohos=extradata.getStringExtra("Hospital Name");
        area=extradata.getStringExtra("Area");
        tohosp=findViewById(R.id.tohos);
        tohosp.setText(tohos);
        mydb=new RequestRes(this);
        patname=findViewById(R.id.pname);
        transfer=findViewById(R.id.case_btn);
        transfer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String currentDateandTime = sdf.format(new Date());


                Boolean Result=mydb.insertReq(currentDateandTime,patname.getText().toString(),"Case Transfer","Pending",tohos,area);
                if (Result == true){
                    Toasty.success(Case_Transfer.this, "Request Added Successful", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toasty.error(Case_Transfer.this, "Registration failed", Toast.LENGTH_SHORT).show();

                }


            }
        });
    }
}