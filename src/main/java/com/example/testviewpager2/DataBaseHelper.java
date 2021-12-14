package com.example.testviewpager2;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DB_CREATE_TABLE =
            "CREATE TABLE " + DBModel.UserClass.TABLE_NAME + " (" +
                    DBModel.UserClass._ID + " INTEGER PRIMARY KEY," +
                    DBModel.UserClass.COL_NAME + " TEXT," +
                    DBModel.UserClass.COL_COURSE + " INTEGER NOT NULL, check(Course > 0 and Course < 7) )";

    public static final int    DB_VERSION = 1;
    public static final String DB_NAME = "Users.db";

    public DataBaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
