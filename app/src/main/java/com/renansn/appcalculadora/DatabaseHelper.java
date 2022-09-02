package com.renansn.appcalculadora;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Mycals.db";
    public static final String TABLE_NAME = "calculos";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Valora";
    public static final String COL_3 = "Valorb";
    public static final String COL_4 = "Resultado";
    public static final String COL_5 = "Date";
    public static final String COL_6 = "Operacao";
    public static final String LBR = "(";
    public static final String RBR = ")";
    public static final String COM = ",";

    public DatabaseHelper(Context context) {
        super( context, DATABASE_NAME, null, 9 );

    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL( "create table " + TABLE_NAME + LBR + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT" + COM +
                COL_2 + " TEXT" + COM + COL_3 + " TEXT" + COM + COL_4 + " INTEGER" + COM + COL_5 + " INTEGER" + COM + COL_6 + " TEXT" +RBR );



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL( "DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate( db );

    }

    public boolean instertData(Integer valora, Integer valorb, Integer resultado, String date, String operacao){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put( COL_2, valora );
        contentValues.put( COL_3, valorb );
        contentValues.put( COL_4, resultado );
        contentValues.put( COL_5, date);
        contentValues.put( COL_6, operacao);


        long result = db.insert( TABLE_NAME, null, contentValues );

        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getData(){

        SQLiteDatabase db = getWritableDatabase();
        Cursor res = db.rawQuery( "select * from " + TABLE_NAME, null );
        return res;
    }


}