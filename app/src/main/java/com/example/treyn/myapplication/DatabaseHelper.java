package com.example.treyn.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "data.db";
    private static final String USER_TABLE = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String CAL_TABLE = "calories";
    private static final String COLUMN_GAIN = "totalgained";
    private static final String COLUMN_LOSS = "totallost";
    private static final String COLUMN_NET = "netgain";
    
    SQLiteDatabase db;
    SQLiteDatabase caldb;
    /*private static final String TABLE_CREATE = "CREATE TABLE " + USER_TABLE + "(" + COLUMN_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT " + COLUMN_EMAIL + " TEXT " +
            COLUMN_USERNAME + " TEXT " + COLUMN_PASSWORD + " TEXT " + ");";*/


    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + USER_TABLE + "(" + COLUMN_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_EMAIL + " TEXT, " +
                COLUMN_USERNAME + " TEXT, " + COLUMN_PASSWORD + " TEXT " + ");";
        db.execSQL(query);
        this.db = db;

    }

    public void reCreate(){
        SQLiteDatabase caldb = this.getWritableDatabase();
        caldb.execSQL("DROP TABLE IF EXISTS " + CAL_TABLE);
        String cal = "CREATE TABLE " + CAL_TABLE + "(" + COLUMN_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_GAIN +
                " INTEGER, " + COLUMN_LOSS + " INTEGER, " +
                COLUMN_NET + " INTEGER " + ");";
        caldb.execSQL(cal);
        caldb.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase caldb, int oldVersion, int newVersion) {
        String cal = "CREATE TABLE " + CAL_TABLE + "(" + COLUMN_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_GAIN +
                " INTEGER, " + COLUMN_LOSS + " INTEGER, " +
                COLUMN_NET + " INTEGER " + ");";
        caldb.execSQL(cal);
        this.caldb = caldb;
    }

    public void insertUser(User u){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_EMAIL, u.getEmail());
        values.put(COLUMN_USERNAME, u.getUsername());
        values.put(COLUMN_PASSWORD, u.getPassword());



        db.insert(USER_TABLE, null, values);
        db.close();
    }

    public void insertCalories(Calories calories){
        caldb = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_GAIN, calories.getGain());
        values.put(COLUMN_LOSS, calories.getLoss());
        values.put(COLUMN_NET, calories.getTotal());

        caldb.insert(CAL_TABLE, null, values);
        caldb.close();
    }

    public String searchPass(String uname){
        db = this.getReadableDatabase();
        String query = "select username, password from " + USER_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        String u, p;
        p = "not found";

        if(cursor.moveToFirst()){
            do{
                u = cursor.getString(0);

                if(u.equals(uname)) {
                    p = cursor.getString(1);
                    break;
                }
            }while(cursor.moveToNext());

        }

        return p;
    }

    public int getTotalIntake(){
        int totalIntake = 0;

        caldb = this.getReadableDatabase();
        String query = "select totalgained from " + CAL_TABLE;
        Cursor cursor = caldb.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                totalIntake = totalIntake + cursor.getInt(0);

            }while(cursor.moveToNext());
        }
        return totalIntake;
    }

    public int getTotalLoss(){
        int totalLoss = 0;

        caldb = this.getReadableDatabase();
        String query = "select totallost from " + CAL_TABLE;
        Cursor cursor = caldb.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                totalLoss = totalLoss + cursor.getInt(0);

            }while(cursor.moveToNext());
        }
        return totalLoss;
    }

    public int getTotal(){
        int total = 0;

        caldb = this.getReadableDatabase();
        String query = "select netgain from " + CAL_TABLE;
        Cursor cursor = caldb.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                total = total + cursor.getInt(0);

            }while(cursor.moveToNext());
        }
        return total;
    }


}
