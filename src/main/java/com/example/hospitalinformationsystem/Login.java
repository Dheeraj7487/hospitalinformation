package com.example.hospitalinformationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;


public class Login extends AppCompatActivity {

    EditText email,password;
    TextView register_txt;
    Button login_btn;
    CheckBox checkBox;
    UserDB myDB;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        login_btn = findViewById(R.id.login_btn);
        register_txt = findViewById(R.id.register_textview);
        checkBox = findViewById(R.id.checkbox);
        getSupportActionBar().hide();

        myDB = new UserDB(this);
        sharedPreferences = getSharedPreferences("PREF",MODE_PRIVATE);
        editor = sharedPreferences.edit();



        if (sharedPreferences.getBoolean("loggedin", false)) {
            startActivity(new Intent(Login.this, Home.class));
            editor.clear();
            editor.apply();
            finish();

        }


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email1 = email.getText().toString();
                String password1 = password.getText().toString();

                if (email1.equals("") || password1.equals("")){
                    Toasty.warning(Login.this, "Please enter the crendentials", Toasty.LENGTH_SHORT).show();

                }
                else {
                    Boolean result = myDB.checkemailpassword(email1,password1);
                    if (result== true){
                        Toasty.success(Login.this, "Login successfull", Toasty.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, Home.class);
                        startActivity(intent);
                        finish();

                        if (checkBox.isChecked()) {
                            sharedPreferences.edit().putBoolean("loggedin", true).commit();

                        }

                    }
                    else {
                        Toasty.error(Login.this, "Invalid crendentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        register_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
    }
}