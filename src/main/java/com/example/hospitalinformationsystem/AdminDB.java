package com.example.hospitalinformationsystem;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class AdminDB extends SQLiteOpenHelper {
    public AdminDB(Context context) {
        super(context, "Hospital.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("create table admin(adminname Text ,hospitalname Text,adminemail Text ,password Text )");
        myDB.execSQL("create table Hospitaldata(HospLogo int,HospitalName Text,Area Text,Bed INTEGER,Oxegen INTEGER,Ambulance INTEGER,Doctor INTEGER,Nurse INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int oldVersion, int newVersion) {
        myDB.execSQL("drop Table if exists admin");
        myDB.execSQL("drop Table if exists Hospitaldata");
    }




    public Boolean insertadmin (String adminname,String hospitalname,String adminemail,String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("adminname" , adminname);
        contentValues.put("hospitalname",hospitalname);
        contentValues.put("adminemail" , adminemail);
        contentValues.put("password" , password);

        long result = myDB.insert("admin",null,contentValues);
        if (result==-1){
            return false;
        }
        else {
            return true;
        }
    }
    public  boolean inserHospital(int Logo,String HospitalName,String Area,String Bed ,String Oxegen,String Ambulance,String Doctor,String Nurse)
    {
        SQLiteDatabase MYDB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("HospLogo",Logo);
        contentValues.put("HospitalName",HospitalName);
        contentValues.put("Area",Area);
        contentValues.put("Bed",Bed);
        contentValues.put("Oxegen",Oxegen);
        contentValues.put("Ambulance",Ambulance);
        contentValues.put("Doctor",Doctor);
        contentValues.put("Nurse",Nurse);

        long result= MYDB.insert("Hospitaldata",null,contentValues);
        if (result==-1)
            return false;
        else
            return true;

    }



    public boolean updatehosp(String HospitalName,String Area,String Bed ,String Oxegen,String Ambulance,String Doctor,String Nurse){

        String[] reqid=new String[2];
        reqid[0]= HospitalName;
        reqid[1]= Area;

        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Bed",Bed);
        contentValues.put("Oxegen",Oxegen);
        contentValues.put("Ambulance",Ambulance);
        contentValues.put("Doctor",Doctor);
        contentValues.put("Nurse",Nurse);
        return myDB.update("Hospitaldata",contentValues,"HospitalName=? AND Area=?",reqid)!=0;
    }
/*
    public void bed(String HospitalName,String Area){

        String[] reqid=new String[2];
        reqid[0]= HospitalName;
        reqid[1]= Area;

        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor result1=db.rawQuery("select Bed from Hospitaldata where Area = '"+Area+"'AND HospitalName ='"+ HospitalName +"'" ,null);

        @SuppressLint("Range") int perbed=result1.getInt(result1.getColumnIndex("Bed"));
        int Bed= perbed-1;

        contentValues.put("Bed",Bed);
        myDB.update("Hospitaldata",contentValues,"HospitalName=? AND Area=?",reqid);
    }
*/


    public Boolean checkemail(String adminemail){
        SQLiteDatabase myDB = this.getWritableDatabase();


        Cursor cursor = myDB.rawQuery("select * from admin where adminemail = ?", new String[]{adminemail});
        if (cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean checkemailpassword(String adminemail, String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from admin where adminemail = ? and password = ?", new String[]{adminemail,password});
        if (cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }

    @SuppressLint("Range")
    List<String> area(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor result=db.rawQuery("select DISTINCT Area from Hospitaldata",null);
        if(result.getCount()==0)
        {return null;}
        else
        {
            List<String> alist=new ArrayList<String>();
            result.moveToFirst();
            while(!result.isAfterLast()) {
                alist.add(result.getString(result.getColumnIndex("Area")));
                result.moveToNext();
            }
            return alist;
            }
    }

    @SuppressLint("Range")
    Hospital[] selecthosp(String area){

        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor result=sqLiteDatabase.rawQuery("select * from Hospitaldata where Area = '"+area+"'",null);

        if(result.getCount()==0)
        {return null;}
        else
        {
            Hospital[] hlist = new Hospital[result.getCount()];
            int ctr=0;
            result.moveToFirst();
            while(!result.isAfterLast())
            {
                Hospital req=new Hospital();
                req.setLogo(result.getInt(result.getColumnIndex("HospLogo")));
                req.setHospName(result.getString(result.getColumnIndex("HospitalName")));
                req.setArea(result.getString(result.getColumnIndex("Area")));
                req.setAmtbed(result.getInt(result.getColumnIndex("Bed")));
                req.setAmtamb(result.getInt(result.getColumnIndex("Ambulance")));
                req.setAmtoxy(result.getInt(result.getColumnIndex("Oxegen")));
                req.setDoc(result.getInt(result.getColumnIndex("Doctor")));
                req.setNur(result.getInt(result.getColumnIndex("Nurse")));


                hlist[ctr++]=req;

                result.moveToNext();

            }

            return hlist;
        }
    }



}
