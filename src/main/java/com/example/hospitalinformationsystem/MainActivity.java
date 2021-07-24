package com.example.hospitalinformationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView hospital_logo;
    TextView hospital_txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hospital_logo = findViewById(R.id.activity_main_img);
        hospital_txt = findViewById(R.id.activity_main_txt);
        getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,StartActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
        alphaAnimation.setDuration(2000);
        hospital_logo.setAnimation(alphaAnimation);
        alphaAnimation.start();
    }
}