package com.example.hospitalinformationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.List;

public class AreaSelect extends AppCompatActivity {
    AdminDB adminDB;
    List<String> arealist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_select);

        adminDB=new AdminDB(AreaSelect.this);
        Spinner spinarea=findViewById(R.id.AreaSpin);

        arealist=adminDB.area();
        if(arealist.isEmpty())
        {arealist.add("None");}


        spinarea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                Log.d("item Selected",item);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Button taketolist=findViewById(R.id.taketolist);


        ArrayAdapter<String> dataAdapter =new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,arealist);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinarea.setAdapter(dataAdapter);

        taketolist.setOnClickListener(view -> {
            Intent takelist;
            takelist = new Intent(AreaSelect.this,Hospital_Info.class);
            takelist.putExtra("area",String.valueOf(spinarea.getSelectedItem()));
            startActivity(takelist);
        });

    }
}