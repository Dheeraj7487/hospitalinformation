package com.example.hospitalinformationsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Hospital_Info extends AppCompatActivity {
    AdminDB db;
    RecyclerView hosinfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_info);

        hosinfo=findViewById(R.id.hoslist);

        Intent extradata=getIntent();
        String area=extradata.getStringExtra("area");
        TextView select_area=findViewById(R.id.selectarea);
        select_area.append(area);



        db=new AdminDB(Hospital_Info.this);

        Hospital[] hos=db.selecthosp(area);
        HospAdapter hospada=new HospAdapter();

        hospada.setItems(hos);
        hosinfo.setAdapter(hospada);
    }
}