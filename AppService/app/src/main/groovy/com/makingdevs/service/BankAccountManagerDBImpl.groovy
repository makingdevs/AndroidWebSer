package com.makingdevs.service

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.makingdevs.database.AccountBaseHelper
import com.makingdevs.database.AccountDbSchema
import com.makingdevs.model.Account
import groovy.transform.CompileStatic

@CompileStatic
class BankAccountManagerDBImpl implements BankAccountManager {

    private static final String TAG = "BankAccountManagerDBImpl"

    private static BankAccountManagerDBImpl bankAccountManagerDB
    private Context mContext
    private SQLiteDatabase mDatabase

    static BankAccountManagerDBImpl getInstance(Context context){ // Tiene que ser static porque el context no puede cambiar
        if(bankAccountManagerDB == null)
            bankAccountManagerDB = new BankAccountManagerDBImpl(context)
        return bankAccountManagerDB
    }

    private BankAccountManagerDBImpl(Context context){
        this.mContext = context.getApplicationContext()
        this.mDatabase = new AccountBaseHelper(mContext).getWritableDatabase()
    }

    @Override
    Integer getTotalAccounts() {
        Cursor c = mDatabase.rawQuery("SELECT * FROM accounts", null);
        c.moveToNext();

        int query = c.getCount();
        return query;
    }

    @Override
    Account addToAccounts(Account account) {
        SQLiteDatabase db = mDatabase;
        ContentValues item = new ContentValues()
        item.put(AccountDbSchema.AccountTable.Column.NAME,account.name)
        item.put(AccountDbSchema.AccountTable.Column.ACCOUNT_NUMBER,account.accountNumber)
        db.insert(AccountDbSchema.AccountTable.NAME,null,item)

        account
    }

    @Override
    Boolean updateAccount(Account account) {
        return null
    }

    @Override
    Boolean deleteAccount(Account account) {
        return null
    }

    @Override
    Account getAccountById(Long id) {
        Account a = new Account()
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM accounts WHERE id = ${id}", null)
        cursor.moveToNext()
        a.id = cursor.getInt(0)
        a.name = cursor.getString(1)
        a.accountNumber = cursor.getString(2)
        a
    }

    @Override
    List<Account> retrieveAccounts() {
        List<Account> accounts = []
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM accounts", null)
        while(cursor.moveToNext()){
            Account a = new Account()
            a.name = cursor.getString(1)
            a.accountNumber = cursor.getString(2)
            accounts << a
        }
        accounts
    }
}