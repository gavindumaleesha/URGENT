package com.ousl.urgent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    // create database
    public static final String DATABASE_NAME = "urgent.db";

    // set database version
    public static final int DATABASE_VERSION = 1;


    // database schema for emergency table
    public static final String EMERGENCY_TABLE = "emergency_table";
    public static final String COL_1 = "ID" ;
    public static final String COL_2 = "DATE" ;
    public static final String COL_3 = "TIME" ;
    public static final String COL_4 = "BLOOD_GROUP" ;
    public static final String COL_5 = "REASON" ;


    // database schema for donor table
    public static final String DONOR_TABLE = "donor_table";
    public static final String DONOR_COL1 = "NAME";
    public static final String DONOR_COL2 = "ADDRESS";
    public static final String DONOR_COL3 = "AGE";
    public static final String DONOR_COL4 = "PHONE";
    public static final String DONOR_COL5 = "WEIGHT";
    public static final String DONOR_COL6 = "BLOOD_GROUP";
    public static final String DONOR_COL7 = "USERNAME";
    public static final String DONOR_COL8 = "PASSWORD";


    // database schema for organization
    public static final String ORG_TABLE = "organization_table";
    public static final String ORG_COL1 = "ORG_NAME";
    public static final String ORG_COL2 = "ORG_ADDRESS";
    public static final String ORG_COL3 = "ORG_PHONE";
    public static final String ORG_COL4 = "ORG_DISTRICT";
    public static final String ORG_COL5 = "ORG_USERNAME";
    public static final String ORG_COL6 = "ORG_PASSWORD";


    // database schema for new camp
    public static final String CAMP_TABLE = "camp_table";
    public static final String CAMP_COL1 = "camp_ID";
    public static final String CAMP_COL2 = "camp_name";
    public static final String CAMP_COL3 = "camp_phone";
    public static final String CAMP_COL4 = "camp_date";
    public static final String CAMP_COL5 = "camp_time";
    public static final String CAMP_COL6 = "camp_location";
    public static final String CAMP_COL7 = "camp_description";


    // database schema for post table
    public static final String POST_TABLE = "post_table";
    public static final String POST_COL1 = "post_topic";
    public static final String POST_COL2 = "post_des";
    public static final String POST_COL3 = "post_image";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // emergency table
        db.execSQL(" create table " + EMERGENCY_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "DATE TEXT, TIME TEXT, BLOOD_GROUP TEXT, REASON TEXT)");

        // donor table
        db.execSQL(" create table " + DONOR_TABLE + "(USERNAME TEXT PRIMARY KEY, " +
                " NAME TEXT,ADDRESS TEXT, AGE INTEGER, PHONE TEXT, WEIGHT TEXT, BLOOD_GROUP TEXT, PASSWORD TEXT )");

        // organization table
        db.execSQL(" create table " + ORG_TABLE + "(ORG_USERNAME TEXT PRIMARY KEY, " +
                " ORG_NAME TEXT,ORG_ADDRESS TEXT, ORG_PHONE TEXT, ORG_DISTRICT TEXT, ORG_PASSWORD TEXT )");

        // new camp table
        db.execSQL("CREATE TABLE " + CAMP_TABLE + " (CAMP_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "CAMP_NAME TEXT, CAMP_PHONE TEXT, CAMP_DATE TEXT, CAMP_TIME TEXT, CAMP_LOCATION TEXT, CAMP_DESCRIPTION TEXT)");

        // post table
        db.execSQL(" create table " + POST_TABLE + "(POST_TOPIC TEXT PRIMARY KEY, " + " POST_DES TEXT ,POST_IMAGE BLOB )");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // emergency table
        db.execSQL("DROP TABLE IF EXISTS " + EMERGENCY_TABLE);

        // donor table
        db.execSQL(" DROP TABLE IF EXISTS " + DONOR_TABLE);

        // organization table
        db.execSQL(" DROP TABLE IF EXISTS " + ORG_TABLE);

        // new camp table
        db.execSQL(" DROP TABLE IF EXISTS " + CAMP_TABLE);

        // new post table
        db.execSQL(" DROP TABLE IF EXISTS " + POST_TABLE);

        onCreate(db);
    }

    // insert emergency method
    public boolean insertData(String date, String time, String bloodGroup, String reason){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,date);
        contentValues.put(COL_3,time);
        contentValues.put(COL_4,bloodGroup);
        contentValues.put(COL_5,reason);
        long result = db.insert(EMERGENCY_TABLE,null,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    // view emergency method
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("Select * from " + EMERGENCY_TABLE,null);
        return result;
    }

    // delete emergency method
    public  Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return  db.delete(EMERGENCY_TABLE, "ID = ?", new String[] {id});
    }


    // insert donor method
    public boolean insertDonor(String name,String address,String age,String  phone,String weight,String bloodType,String username,String  password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DONOR_COL1,name);
        contentValues.put(DONOR_COL2,address);
        contentValues.put(DONOR_COL3,age);
        contentValues.put(DONOR_COL4,phone);
        contentValues.put(DONOR_COL5,weight);
        contentValues.put(DONOR_COL6,bloodType);
        contentValues.put(DONOR_COL7,username);
        contentValues.put(DONOR_COL8,password);
        long result = db.insert(DONOR_TABLE,null,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    // check donor existing the database
    public boolean checkUsername(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery(" Select * from " + DONOR_TABLE + " where username = ?",new String[] {username});
        if (result.getCount() > 0)
            return true;
        else
            return false;
    }

    // check donor's username and password
    public boolean checkUsernamePassword (String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("Select * from " +DONOR_TABLE +" where username = ? and password = ? ", new String[] {username, password});

        if (result.getCount() > 0)
            return true;
        else
            return false;
    }

    // insert organization
    public boolean insertOrg(String orgName,String orgAddress,String orgPhone,String  orgDistrict,String orgUsername,String orgPassword){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ORG_COL1,orgName);
        contentValues.put(ORG_COL2,orgAddress);
        contentValues.put(ORG_COL3,orgPhone);
        contentValues.put(ORG_COL4,orgDistrict);
        contentValues.put(ORG_COL5,orgUsername);
        contentValues.put(ORG_COL6,orgPassword);
        long result = db.insert(ORG_TABLE,null,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    // check org existing the database
    public boolean checkOrgUsername(String orgUsername){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery(" Select * from " + ORG_TABLE + " where ORG_USERNAME = ?",new String[] {orgUsername});
        if (result.getCount() > 0)
            return true;
        else
            return false;
    }

    // check org username and password
    public boolean checkOrgUsernamePassword (String orgUsername, String orgPassword){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("Select * from " + ORG_TABLE +" where ORG_USERNAME = ? and ORG_PASSWORD = ? ", new String[] {orgUsername, orgPassword});
        if (result.getCount() > 0)
            return true;
        else
            return false;
    }




    // insert post
    public boolean insertPost(String topic, String postDes, byte[] image){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(POST_COL1,topic);
        contentValues.put(POST_COL2,postDes);
        contentValues.put(POST_COL3,image);
        long result = db.insert(POST_TABLE,null,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    // get all post
    public  Cursor getPost(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("Select * from " + POST_TABLE,null);
        return result;
    }


    // insert camp
    public boolean insertCamp(String campName,String campPhone,String campDate,String  campTime,String campLocation,String campDescription){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CAMP_COL2,campName);
        contentValues.put(CAMP_COL3,campPhone);
        contentValues.put(CAMP_COL4,campDate);
        contentValues.put(CAMP_COL5,campTime);
        contentValues.put(CAMP_COL6,campLocation);
        contentValues.put(CAMP_COL7,campDescription);
        long result = db.insert(CAMP_TABLE,null,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    // view camp method
    public Cursor getAllCamp(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("Select * from " + CAMP_TABLE,null);
        return result;
    }

    // delete camp method
    public  Integer deleteCamp(String camp_id){
        SQLiteDatabase db = this.getWritableDatabase();
        return  db.delete(CAMP_TABLE, "camp_ID = ?", new String[] {camp_id});
    }

    // update camp method
    public boolean updateCamp(String camp_id,String campName,String campPhone,String campDate,String  campTime,String campLocation, String campDescription){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CAMP_COL1,camp_id);
        contentValues.put(CAMP_COL2,campName);
        contentValues.put(CAMP_COL3,campPhone);
        contentValues.put(CAMP_COL4,campDate);
        contentValues.put(CAMP_COL5,campTime);
        contentValues.put(CAMP_COL6,campLocation);
        contentValues.put(CAMP_COL7,campDescription);
        db.update(CAMP_TABLE, contentValues, "camp_ID = ?",new String[] {camp_id});
        return true;
    }

    // update donor pw method
    public boolean updateDonorPw(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DONOR_COL7,username);
        contentValues.put(DONOR_COL8,password);
        db.update(DONOR_TABLE, contentValues, "USERNAME = ?",new String[] {username});
        return true;
    }


}
