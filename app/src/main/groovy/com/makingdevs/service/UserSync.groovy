package com.makingdevs.service

import android.content.ContentValues;
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log;

import static com.makingdevs.database.AccountDbSchema.*
import com.makingdevs.database.AccountBaseHelper
import com.makingdevs.model.Account
import groovy.transform.CompileStatic;

/**
 * Created by makingdevs on 10/02/17.
 */
@CompileStatic
public class UserSync {

    private Context mContext
    private SQLiteDatabase db
    private AccountBaseHelper mopenhelper


    UserSync(Context context) {
        mContext = context.getApplicationContext()
        mopenhelper = new AccountBaseHelper(mContext)
        db = mopenhelper.getWritableDatabase()
    }
    Account accountAdd(Account account){
        ContentValues item = new ContentValues()
        //item.put(AccountTable.Column.NAME,account.name)
        //item.put(AccountTable.Column.ACCOUNTNUMBER,account.accountNumber)
        //db.insert(AccountTable.NAME,null,item)
        //account
        null
    }

    void findById(Long id){
        Cursor c = db.rawQuery("SELECT * FROM users WHERE _id = '$id'", null);
        c.moveToNext();
        Log.d("Repository",c.getString(c.getColumnIndex("name")))
    }

    Account getCurrentUser(){
        Cursor c = db.rawQuery("SELECT * FROM users", null);
        c.moveToNext();
        new Account(name: c.getString(c.getColumnIndex("name")),accountNumber: c.getString(c.getColumnIndex("accountnumber")))
    }

    Boolean isCurrentUser(){
        Cursor c = db.rawQuery("SELECT * FROM users", null);
        !c.getCount() == 0
    }
}