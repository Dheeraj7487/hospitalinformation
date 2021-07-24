package com.example.hospitalinformationsystem;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

class UserDB extends SQLiteOpenHelper {


    public UserDB(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("create Table users(name Text primary key, email Text, mobile Text, password Text)");
        myDB.execSQL("create Table Profile(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,email TEXT,mobile INTEGER,age INTEGER,blood_group TEXT)");
        myDB.execSQL("create Table feedback(id INTEGER PRIMARY KEY AUTOINCREMENT,name Text, email Text, number Text, writefeedback Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int i, int i1) {
        myDB.execSQL("drop Table if exists users");
        myDB.execSQL("drop Table if exists Profile");
    }

    public Boolean insertuser (String name, String email,String mobile,String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name" , name);
        contentValues.put("email" , email);
        contentValues.put("mobile" , mobile);
        contentValues.put("password" , password);

        long result = myDB.insert("users",null,contentValues);
        if (result==-1){
            return false;
        }
        else {
            return true;
        }
    }
    public Boolean profileData(String name,String email,String mobile,String age,String blood_group)
    {
        SQLiteDatabase MYDB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",name);
        contentValues.put("email",email);
        contentValues.put("mobile",mobile);
        contentValues.put("age",age);
        contentValues.put("blood_group",blood_group);

        long result2 = MYDB.insert("Profile",null,contentValues);
        if (result2==-1){
            return false;
        }
        else {
            return true;
        }
    }

    public Boolean checkemail(String email){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from users where email = ?", new String[]{email});
        if (cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean checkemailpassword(String email, String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from users where email = ? and password = ?", new String[]{email,password});
        if (cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }

    Cursor selectdataprofile(){
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor result=sqLiteDatabase.rawQuery("select * from users",null);
        return result;
    }

    Cursor selectEmailIdName(){
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor result=sqLiteDatabase.rawQuery("select name,email from users",null);
        return result;
    }



    public Boolean insertfeedback (String name, String email,String mobile,String feedback){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name" , name);
        contentValues.put("email" , email);
        contentValues.put("number" , mobile);
        contentValues.put("writefeedback" , feedback);

        long result3 = myDB.insert("feedback",null,contentValues);
        if (result3==-1){
            return false;
        }
        else {
            return true;
        }
    }

    @SuppressLint("Range")
    feedbackdisplaymodel[] selectfeedback() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor result3 = sqLiteDatabase.rawQuery("select * from feedback", null);

        if (result3.getCount() == 0) {
            return null;
        } else {
            feedbackdisplaymodel[] feedbacklist = new feedbackdisplaymodel[result3.getCount()];
            int ctr = 0;
            result3.moveToFirst();
            while (!result3.isAfterLast()) {
                feedbackdisplaymodel req = new feedbackdisplaymodel();
                req.setName(result3.getString(result3.getColumnIndex("name")));
                req.setEmail(result3.getString(result3.getColumnIndex("email")));
                req.setMobile(result3.getString(result3.getColumnIndex("number")));
                req.setWritefeedback(result3.getString(result3.getColumnIndex("writefeedback")));

                feedbacklist[ctr++] = req;

                result3.moveToNext();
            }

            return feedbacklist;
        }


    }

}
