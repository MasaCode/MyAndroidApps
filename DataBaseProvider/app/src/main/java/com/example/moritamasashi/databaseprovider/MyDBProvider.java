package com.example.moritamasashi.databaseprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

/**
 * Created by moritamasashi on 16/02/05.
 */
public class MyDBProvider extends ContentProvider {
    private final static String DB_NAME = "test.db";
    private final static String DB_TABLE ="test";
    private final static int DB_VERSION = 1;
    private SQLiteDatabase db;

    @Override
    public boolean onCreate(){
        DBHelper dbHelper = new DBHelper(getContext());
        db = dbHelper.getWritableDatabase();
        return (db != null);
    }

    @Override
    public Cursor query(Uri uri,String[] columns,String selection,String[] selectionArgs, String sortOrder){
        return db.query(DB_TABLE,columns,selection,selectionArgs,null,null,null);
    }

    @Override
    public int update(Uri uri,ContentValues values,String selection, String[] selectionArgs){
        return db.update(DB_TABLE, values, null, null);
    }

    @Override
    public Uri insert(Uri uri,ContentValues values){
        db.insert(DB_TABLE,"",values);
        return uri;
    }

    @Override
    public int delete(Uri uri,String selection,String[] selectionArgs){
        return 0;
    }

    @Override
    public String getType(Uri uri){
        return null;
    }

    private static class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context){
            super(context,DB_NAME,null,DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL("create table if not exists " + DB_TABLE + "(id text primary key,info text)");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
            db.execSQL("drop table if exists " + DB_TABLE);
            onCreate(db);
        }


    }



}
