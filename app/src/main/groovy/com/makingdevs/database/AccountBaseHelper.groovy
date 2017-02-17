package com.makingdevs.database

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper

import com.makingdevs.database.AccountDbSchema.AccountTable
import groovy.transform.CompileStatic;

/**
 * Created by makingdevs on 10/02/17.
 */
@CompileStatic
class AccountBaseHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "account_base.db"
    static final int VERSION = 1

    AccountBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION)
    }

    @Override
    void onCreate(SQLiteDatabase db) {
        db.execSQL("""\
            create table ${AccountTable.NAME}( _id integer primary key autoincrement,
            ${AccountTable.Column.NAME} varchar(255),
            ${AccountTable.Column.ACCOUNT_NUMBER} varchar(20) )
        """)

    }

    @Override
     void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists ${AccountTable.NAME} ")
        onCreate(db)
    }

}
