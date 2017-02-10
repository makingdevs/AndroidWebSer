package com.makingdevs.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper

import static com.makingdevs.database.appserviceDb.*
import groovy.transform.CompileStatic;

/**
 * Created by makingdevs on 10/02/17.
 */
@CompileStatic
 class AppserviceOpenHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "appservice.db"
    static final int VERSION = 1

    AppserviceOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION)
    }

    @Override
    void onCreate(SQLiteDatabase db) {
        db.execSQL("""\
      create table ${UserTable.NAME}( _id integer primary key autoincrement,
      ${UserTable.Column.NAME},
      ${UserTable.Column.ACCOUNTNUMBER} )
    """)

    }

    @Override
     void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" drop table if exists ${UserTable.NAME} ")
        onCreate(db)
    }
}
