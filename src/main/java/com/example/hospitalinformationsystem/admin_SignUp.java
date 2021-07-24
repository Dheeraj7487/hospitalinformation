package com.example.hospitalinformationsystem;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import es.dmoral.toasty.Toasty;

public class admin_SignUp extends AppCompatActivity {

    EditText hospitalname,adminname,adminemail,password,confirmpassword;
    Button btnSignup;
    AdminDB myDB;
    TextView login_textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_sign_up);

//        getSupportActionBar().setTitle("Signup Form");

        hospitalname = findViewById(R.id.HospitalName);

        adminname = findViewById(R.id.AdminName);
        adminemail = findViewById(R.id.AdminEmail);
        password = findViewById(R.id.Password);
        confirmpassword = findViewById(R.id.ConfirmPassword);
        btnSignup = findViewById(R.id.btnSignup);
        login_textview = findViewById(R.id.login_textview);


        myDB = new AdminDB(this);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hospitalname1 = hospitalname.getText().toString();
                String adminname1 = adminname.getText().toString();
                String adminemail1 = adminemail.getText().toString();
                String password1 = password.getText().toString();
                String confirmpassword1 = confirmpassword.getText().toString();

                if (hospitalname1.equals("") || adminname1.equals("") ||adminemail1.equals("") || password1.equals("") || confirmpassword1.equals("")){
                    Toasty.info(admin_SignUp.this, "Fill All the Fields..", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (password1.equals(confirmpassword1)){
                        Boolean checkuser = myDB.checkemail(adminemail1);
                        if (checkuser == false){
                            Boolean result = myDB.insertadmin(adminname1,hospitalname1,adminemail1,password1);
                            if (result == true){
                                Toasty.success(admin_SignUp.this, "Registration Successfully..", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(admin_SignUp.this, admin_Login.class);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Toasty.error(admin_SignUp.this, "Registration Failed..", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toasty.info(admin_SignUp.this, "Already Exists..", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toasty.info(admin_SignUp.this, "Password did not Match..", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        login_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin_SignUp.this, admin_Login.class);
                startActivity(intent);
                finish();
            }
        });

    }
}