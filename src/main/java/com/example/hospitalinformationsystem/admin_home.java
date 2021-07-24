package com.example.hospitalinformationsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class admin_home extends AppCompatActivity {


    private DrawerLayout drawerLayout;
    LinearLayout hospitalLayout;
    LinearLayout requestLayout;
    LinearLayout feedbackLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_home);

        hospitalLayout = findViewById(R.id.hospitalLayout);
        requestLayout = findViewById(R.id.requestLayout);
        feedbackLayout = findViewById(R.id.feedbackLayout);
        hospitalLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hospi = new Intent(admin_home.this, admin_hospital_form.class);
                admin_home.this.startActivity(hospi);
            }
        });

        requestLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent req = new Intent(admin_home.this,requestAcceptReject.class);
                startActivity(req);
            }
        });

        feedbackLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent feed = new Intent(admin_home.this, feedback_display.class);
                admin_home.this.startActivity(feed);
            }
        });

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logmenu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        Intent i=new Intent(admin_home.this,admin_Login.class);
        startActivity(i);
        finish();
        Toast.makeText(this,"Logout Successfully", Toast.LENGTH_LONG).show();

        return super.onOptionsItemSelected(item);
    }
}