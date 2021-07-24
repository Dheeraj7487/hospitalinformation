package com.example.hospitalinformationsystem;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class RequestRes extends SQLiteOpenHelper {

    public RequestRes(@Nullable Context context) {
        super(context,"Request.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("create Table Requests(id INTEGER PRIMARY KEY AUTOINCREMENT,date Text ,name  Text,reqtype Text,status Text,Hname Text,area Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int i, int i1) {
        myDB.execSQL("drop Table if exists Requests");
    }

    public boolean cancelreq(Request req)
    {
        SQLiteDatabase db=getWritableDatabase();
        String[] reqid=new String[1];
        reqid[0]= String.valueOf(req.getId());

        return db.delete("Requests","id=?",reqid)!=0;
    }

    public boolean acceptstat(Request req){

        String[] reqid=new String[1];
        reqid[0]= String.valueOf(req.getId());
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues newValues = new ContentValues();
        newValues.put("status", "Accepted");
        return myDB.update("Requests",newValues,"id=?",reqid)!=0;
    }


    public boolean rejectstat(Request req){


        String[] reqid=new String[1];
        reqid[0]= String.valueOf(req.getId());
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues newValues = new ContentValues();
        newValues.put("status", "Rejected");
        return myDB.update("Requests",newValues,"id=?",reqid)!=0;

    }

    public boolean insertReq(String date, String name,String type,String status,String hname,String area)
    {

        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("date" ,date);
        contentValues.put("name" ,name );
        contentValues.put("reqtype" ,type);
        contentValues.put("status" ,status);
        contentValues.put("Hname",hname);
        contentValues.put("area" ,area);


        long result = myDB.insert("Requests",null,contentValues);
        if (result==-1){
            return false;
        }
        else {
            return true;
        }
    }

    @SuppressLint("Range")
    Request[] selectdataprofile(){
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor result=sqLiteDatabase.rawQuery("select * from Requests",null);

        if(result.getCount()==0)
        {return null;}
        else
        {
            Request[] userlist = new Request[result.getCount()];
            int ctr=0;
            result.moveToFirst();
            while(!result.isAfterLast())
            {
                Request req=new Request();
                req.setId(result.getInt(result.getColumnIndex("id")));
                req.setDate(result.getString(result.getColumnIndex("date")));
                req.setPname(result.getString(result.getColumnIndex("name")));
                req.setReqtype(result.getString(result.getColumnIndex("reqtype")));
                req.setStatus(result.getString(result.getColumnIndex("status")));
                req.setHname(result.getString(result.getColumnIndex("Hname")));
                req.setArea(result.getString(result.getColumnIndex("area")));
                userlist[ctr++]=req;

                result.moveToNext();

            }

            return userlist;
        }
    }

}

