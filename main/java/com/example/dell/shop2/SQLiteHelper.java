package com.example.dell.shop2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;

    public static final String DATABASE_NAME="MARKETING";

    public static final String TABLE="STOCK";
    public static final String id="ID";
    public static final String name="PRONAME";
    public static final String price="PRICE";
    public static final String unit="UNIT";
    public static final String quantity="QUANTITY";
    public static final String description="DESCRIPTION";

     public SQLiteHelper(Context context){

         super(context, DATABASE_NAME,null,DATABASE_VERSION);
     }

    public void onCreate(SQLiteDatabase db)
    {

        String p="CREATE TABLE "+TABLE+" ("+id+" INTEGER PRIMARY KEY,"+name+" TEXT,"+price+" INTEGER,"+unit+" TEXT,"+quantity+" INTEGER,"+description+" TEXT)";
        db.execSQL(p);

    }

    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion)
    {
      db.execSQL("DROP TABLE IF EXISTS "+TABLE);


        onCreate(db);


    }


}
