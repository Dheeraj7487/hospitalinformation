package com.example.hospitalinformationsystem;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import es.dmoral.toasty.Toasty;

public class admin_Login extends AppCompatActivity {

    EditText hospitalemail,password;
    Button btnLogin1;
    TextView register_txt;

    AdminDB myDB;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_login);

        //getSupportActionBar().setTitle("Login Form");

        hospitalemail = findViewById(R.id.LoginHospitalEmail);
        password = findViewById(R.id.LoginPassword);

        btnLogin1 = findViewById(R.id.btnLogin1);
        register_txt = findViewById(R.id.register_textview);


        myDB = new AdminDB(this);

        sharedPreferences = getSharedPreferences("PREF",MODE_PRIVATE);
        editor = sharedPreferences.edit();



        if (sharedPreferences.getBoolean("loggedin", false)) {
            startActivity(new Intent(admin_Login.this, Home.class));
            editor.clear();
            editor.apply();
            finish();

        }


        btnLogin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email1 = hospitalemail.getText().toString();
                String password1 = password.getText().toString();

                if (email1.equals("") || password1.equals("")){
                    Toasty.info(admin_Login.this, "Please Enter the Crendentials.. ", Toast.LENGTH_SHORT).show();

                }
                else {
                    Boolean result = myDB.checkemailpassword(email1,password1);
                    if (result== true){
                        Toasty.success(admin_Login.this, "Login successfully..", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(admin_Login.this, admin_home.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toasty.error(admin_Login.this, "Invalid Crendentials..", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        register_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin_Login.this, admin_SignUp.class);
                startActivity(intent);
            }
        });
    }

}