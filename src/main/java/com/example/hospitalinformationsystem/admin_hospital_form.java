package com.example.hospitalinformationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class admin_hospital_form extends AppCompatActivity {
    EditText areaname,noOfbed,noOfOxegen,noOambulance,noOfDoctor,noOfNurse;
    Button addhospital,updatedata;
    Spinner sellogo;
    AdminDB DB;
    String hospitalname;
    ArrayList<LogoItem> logolist;
    int logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_hospital_form);

        areaname=findViewById(R.id.addAreaName);
        noOfbed=findViewById(R.id.addNumOfBed);
        noOfOxegen=findViewById(R.id.addNumOfOxygen);
        noOambulance=findViewById(R.id.addNumOfAmbulance);
        noOfDoctor=findViewById(R.id.addNumOfDoctor);
        noOfNurse=findViewById(R.id.addNumOfNurse);
        addhospital=findViewById(R.id.AddHospitalData);
        updatedata=findViewById(R.id.UpdateHospitalData);

        sellogo=findViewById(R.id.logoselect);
        initList();
        sellogo.setAdapter(new logoAdapter(admin_hospital_form.this,logolist));

        sellogo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                LogoItem clickedItem = (LogoItem) adapterView.getItemAtPosition(i);
                logo= clickedItem.getLogo();
                hospitalname=clickedItem.getLogoname();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        addhospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HospitalData();
            }
        });

        updatedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            UpdateData();
            }
        });

    }
    public void HospitalData()
    {
        DB=new AdminDB(this);
        String HOSPITALNAME=hospitalname;
        String AREANAME=areaname.getText().toString();
        String BED=noOfbed.getText().toString();
        String OXEGEN=noOfOxegen.getText().toString();
        String AMBULANCE=noOambulance.getText().toString();
        String DOCTOR=noOfDoctor.getText().toString();
        String NURSE=noOfNurse.getText().toString();



        if (HOSPITALNAME.equals("")||AREANAME.equals("")||BED.equals("")||OXEGEN.equals("")||AMBULANCE.equals("")||DOCTOR.equals("")||NURSE.equals(""))
        {
            Toasty.info(admin_hospital_form.this, "ENTER ALL FIELD", Toast.LENGTH_LONG).show();
        }
        else
        {
            Boolean result=DB.inserHospital(logo,HOSPITALNAME,AREANAME,BED,OXEGEN,AMBULANCE,DOCTOR,NURSE);
            if (result==true)
            {
                Toasty.success(admin_hospital_form.this,"Sucessfully added Hospital",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(admin_hospital_form.this,admin_home.class);
                startActivity(intent);
                finish();
            }
            else
            {
                Toasty.error(admin_hospital_form.this,"Error insert Data",Toast.LENGTH_LONG).show();
            }
        }
    }


    public void UpdateData()
    {
        DB=new AdminDB(this);
        String HOSPITALNAME=hospitalname;
        String AREANAME=areaname.getText().toString();
        String BED=noOfbed.getText().toString();
        String OXEGEN=noOfOxegen.getText().toString();
        String AMBULANCE=noOambulance.getText().toString();
        String DOCTOR=noOfDoctor.getText().toString();
        String NURSE=noOfNurse.getText().toString();



        if (HOSPITALNAME.equals("")||AREANAME.equals("")||BED.equals("")||OXEGEN.equals("")||AMBULANCE.equals("")||DOCTOR.equals("")||NURSE.equals(""))
        {
            Toasty.info(admin_hospital_form.this, "ENTER ALL FIELD", Toast.LENGTH_LONG).show();
        }
        else
        {
            Boolean result=DB.updatehosp(HOSPITALNAME,AREANAME,BED,OXEGEN,AMBULANCE,DOCTOR,NURSE);
            if (result==true)
            {
                Toasty.success(admin_hospital_form.this,"Sucessfully updated Hospital Data",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(admin_hospital_form.this,admin_home.class);
                startActivity(intent);
                finish();
            }
            else
            {
                Toasty.error(admin_hospital_form.this,"Error insert Data",Toast.LENGTH_LONG).show();
            }
        }
    }

    private void initList() {
        logolist = new ArrayList<>();

        logolist.add(new LogoItem("Apollo Hospital", R.drawable.apollo));
        logolist.add(new LogoItem("Shalby Hospital", R.drawable.shalby));
        logolist.add(new LogoItem("Rajasthan Hospital", R.drawable.rajasthanhosp));
        logolist.add(new LogoItem("Star Hospital", R.drawable.starhosp));
        logolist.add(new LogoItem("Civil Hospital",R.drawable.civil));

    }
}