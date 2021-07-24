package com.example.hospitalinformationsystem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

import es.dmoral.toasty.Toasty;

public class
Profile extends AppCompatActivity {

    ImageView profile_image, toolbar_back;
    TextView name, email, mobile,age,blood_group;
    Button add;
    UserDB myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();

        profile_image = findViewById(R.id.profile_image);
        name = findViewById(R.id.profile_name);
        email = findViewById(R.id.profile_email);
        mobile = findViewById(R.id.profile_number);
        age = findViewById(R.id.profile_age);
        blood_group = findViewById(R.id.profile_blood_group);
        add = findViewById(R.id.profile_add_btn);

        toolbar_back = findViewById(R.id.toolbar_back);

        myDB=new UserDB(this);

        toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this,Home.class);
                startActivity(intent);
                finish();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String NAME = name.getText().toString();
                String EMAIL = email.getText().toString();
                String MOBILE = mobile.getText().toString();
                String AGE = age.getText().toString();
                String BLOOD = blood_group.getText().toString();

                if (NAME.equals("") || EMAIL.equals("") || MOBILE.equals("") || BLOOD.equals(""))
                {
                    Toasty.warning(Profile.this,"Pless enter the all field",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Boolean result2=myDB.profileData(NAME,EMAIL,MOBILE,AGE,BLOOD);
                    if (result2==true)
                    {
                        Toasty.success(Profile.this, "Sucessfully save Profile", Toasty.LENGTH_SHORT).show();
                        Intent intent=new Intent(Profile.this,Home.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Toasty.error(Profile.this, "Profile data failed", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");

                startActivityForResult(Intent.createChooser(intent,"Pick an image"),1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 1) {
            try {
                InputStream inputStream = getContentResolver().openInputStream(data.getData());

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                profile_image.setImageBitmap(bitmap);
            }
            catch (FileNotFoundException ex){
                ex.printStackTrace();
            }
        }
    }


}


