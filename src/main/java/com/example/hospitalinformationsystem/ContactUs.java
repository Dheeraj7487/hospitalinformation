package com.example.hospitalinformationsystem;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

public class ContactUs extends AppCompatActivity {

    GridView gridView;
    String [] name={"Call","Facebook","Instagram","Email"};
    int [] image={R.drawable.call,R.drawable.facebook,R.drawable.instagram,R.drawable.gmail};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        gridView = findViewById(R.id.contact_grid);
        getSupportActionBar().hide();
        ContactUsAdapter contactUsAdapter = new ContactUsAdapter(ContactUs.this,image,name);
        gridView.setAdapter(contactUsAdapter);


    }
}