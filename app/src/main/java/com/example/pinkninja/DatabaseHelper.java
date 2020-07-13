package com.example.pinkninja;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "customerRef.db";
    public static final String CUSTOMER_TABLE = "registerTable";
    public static final String SCHOOL_LIST_TABLE = "schoolList";
    public static final String  LOC_LIST_TABLE = "locationList";
    public static final String  STUDENT_LIST_TABLE = "studentList";
    public static final String  TEACHER_LIST_TABLE = "teacherList";
    //public static final String  WORKER_LIST_TABLE = "workerList";
    public static final String col_1 = "ID", col_2 = "NAME", col_3 = "EMAIL", col_4 = "PASSWORD";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table " + CUSTOMER_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT , NAME" +
                        " TEXT , EMAIL TEXT ,PASSWORD TEXT)");
        db.execSQL(
                "create table " + SCHOOL_LIST_TABLE + " (CDE INTEGER PRIMARY KEY AUTOINCREMENT , DECODE" +
                        " TEXT , PIN_CDE INTEGER,CLS_UPTO TEXT, NO_OF_SEC INTEGER, AFFLTD TEXT ,MEDIUM TEXT)");
        db.execSQL(
                "create table " + LOC_LIST_TABLE + " (CDE INTEGER PRIMARY KEY, DECODE" +
                        " TEXT)");
        db.execSQL(
                "create table " + STUDENT_LIST_TABLE + " (ADM_NO INTEGER PRIMARY KEY AUTOINCREMENT , NAME" +
                        " TEXT , EMAIL TEXT ,FATHER_NME TEXT,MOTHER_NME TEXT, AGE INTEGER, SCHL_NAME TEXT,CURR_CLS TEXT,CURR_SEC TEXT,STRT_CLS TEXT,STRT_SEC TEXT)");
        db.execSQL(
                "create table " + TEACHER_LIST_TABLE +" (EMP_ID INTEGER PRIMARY KEY, NAME" +
                        " TEXT , EMAIL TEXT,SUB TEXT,AGE INTEGER,MEDIUM TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table IF EXISTS " + CUSTOMER_TABLE);
        db.execSQL("drop table IF EXISTS " + SCHOOL_LIST_TABLE);
        db.execSQL("drop table IF EXISTS " + STUDENT_LIST_TABLE);
        db.execSQL("drop table IF EXISTS " + TEACHER_LIST_TABLE);
        db.execSQL("drop table IF EXISTS " + LOC_LIST_TABLE);
        onCreate(db);

    }

    public boolean addUser(CustomerCreateAccountBean bean) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("NAME", bean.getUserName());
        content.put("EMAIL", bean.getEmail());
        content.put("PASSWORD", bean.getPassword());
        long success = db.insert(CUSTOMER_TABLE, null, content);
        db.close();
        if (success != -1) {
            return true;
        }
        return false;
    }

    public boolean isUserPresent(CustomerCreateAccountBean user) {
        String[] columns = {col_1};
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = col_2 + " = ? and " + col_4 + " = ?";
        String[] selectionArgs = {user.getUserName(), user.getPassword()};
        Cursor cursor = db
                .query(CUSTOMER_TABLE, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        if (count != 0) {
            return true;
        }
        return false;
    }
    public boolean addSchool(SchoolDetailBean schoolBean){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("DECODE", schoolBean.getSchoolDecode());
        content.put("PIN_CDE", schoolBean.getPinCode());
        content.put("CLS_UPTO",schoolBean.getClasses());
        content.put("NO_OF_SEC",schoolBean.getNumberOfSection());
        content.put("AFFLTD",schoolBean.getAffiliation());
        content.put("MEDIUM",schoolBean.getMedium());
        long success = db.insert(SCHOOL_LIST_TABLE, null, content);
        db.close();
        if (success != -1) {
            return true;
        }
        return false;
    }
    public boolean isSchoolPresent(SchoolDetailBean schoolbean){
        String[] columns = {"CDE","DECODE","PIN_CDE"};
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = "DECODE" + " = ? and " + "PIN_CDE" + " = ? ";
        String[] selectionArgs = {schoolbean.getSchoolDecode(),String.valueOf(schoolbean.getPinCode())};
        Cursor cursor = db
                .query(SCHOOL_LIST_TABLE, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        if (count != 0) {
            return true;
        }
        return false;
    }

    public ArrayList<SchoolDetailBean> getSchoolsAreaWise(String loc){
        ArrayList<SchoolDetailBean> schoolList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        return schoolList;
    }

    public boolean addLocation(int pin,String decode){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("CDE", pin);
        content.put("DECODE",decode);
        long success = db.insert(LOC_LIST_TABLE, null, content);
        db.close();
        if (success != -1) {
            return true;
        }
        return false;
    }

    public ArrayList<LocationBean> getLocations(){
        String query;
        ArrayList<LocationBean> locationList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        query ="SELECT * FROM " + LOC_LIST_TABLE;
        Cursor cursor = db.rawQuery(query,null,null);
        if (cursor.moveToFirst()) {
            do {
                LocationBean locBean = new LocationBean();
                locBean.setPinCode(cursor.getInt(0));
                locBean.setCityName(cursor.getString(1));
                locationList.add(locBean);
            } while (cursor.moveToNext());
        }
        return locationList;
    }
    public boolean isLocationPresent(SchoolDetailBean schoolbean){
        String[] columns = {"CDE"};
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = "DECODE" + " = ? ";
        String[] selectionArgs = {String.valueOf(schoolbean.getPinCode())};
        Cursor cursor = db
                .query(LOC_LIST_TABLE, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        if (count != 0) {
            return true;
        }
        return false;
    }

    public void deleteTable(String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP table if exists " + tableName);
    }
}
