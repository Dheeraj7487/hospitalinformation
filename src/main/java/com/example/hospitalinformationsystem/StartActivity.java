package com.example.hospitalinformationsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {
    LinearLayout USer,Admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        USer=findViewById(R.id.UserPanel);
        Admin=findViewById(R.id.adminpanel);


        USer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StartActivity.this, Login.class);
                startActivity(intent);

            }
        });

        Admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StartActivity.this,admin_Login.class);
                startActivity(intent);

            }
        });
    }
}