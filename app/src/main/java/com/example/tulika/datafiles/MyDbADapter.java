package com.example.tulika.datafiles;

/**
 * Created by Tulika on 1/22/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Tulika on 6/29/2016.
 */
public class MyDbADapter extends SQLiteOpenHelper {
    public static final String DB_NAME = "Spidersdb";
    public static final String TAB_NAME = "emptable";
    public static final String ROWID = "rowid";
    public static final int DB_VERSION = 1;


    public static final String COL_PACKAGE = "pack";

    public static final String COL_COMMAND = "command";
    public static final String COL_REPLY = "reply";


    String CREATE_TABLE = "create table emptable(rowid integer primary key autoincrement,pack text not null,command text not null,reply text not null)";


    SQLiteDatabase db;

    public MyDbADapter(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }



    public Cursor getvalues(){

        Cursor q= db.rawQuery("Select reply from emptable where command=a",null);
        return q;

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void Opendb() {
        db = this.getWritableDatabase();
    }

    public void Closedb() {
        db.close();
    }

    public void Insert(String command,String reply,String pack) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_PACKAGE,pack);
        contentValues.put(COL_COMMAND, command);
        contentValues.put(COL_COMMAND, reply);
        db.insert(TAB_NAME, null, contentValues);


    }

}
