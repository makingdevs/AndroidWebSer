package com.makingdevs.ui

import android.content.Intent
import android.database.Cursor
import android.graphics.Color
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.makingdevs.model.Account
import com.makingdevs.service.BankAccountManager
import com.makingdevs.service.BankAccountManagerDBImpl
import com.makingdevs.service.BankAccountManagerImpl
import com.makingdevs.transaction.appservice.R
import groovy.transform.CompileStatic
import mehdi.sakout.fancybuttons.FancyButton;
@CompileStatic
public class NewUserActivity extends AppCompatActivity {
    FancyButton fButton_save
    EditText mEdit_name
    EditText mEdit_account
    Integer position
    BankAccountManager bankAccountManager

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bankAccountManager = BankAccountManagerDBImpl.getInstance(getApplicationContext()) // Lo cambie de lugar porque debe obtener el contexto del onCreate
        setContentView(R.layout.activity_new_user);
        fButton_save = (FancyButton) findViewById(R.id.btn_save)
        mEdit_name = (EditText) findViewById(R.id.editName)
        mEdit_account = (EditText) findViewById(R.id.editAccount)
        fButton_save.onClickListener = {

            if(!mEdit_account.text || !mEdit_name.text){
                Toast.makeText(this, "No deje campos vacios", 1).show()
            }
            else if(mEdit_account.text.length() != 18){
                Toast.makeText(this, "Deben ser 18 digitos ", 1).show()
            }
            else{
                Account newAccount = new Account(name: "${mEdit_name.text.toString()}", accountNumber: "${mEdit_account.text.toString()}")
                Cursor c = bankAccountManager.AccountAlreadyExists(newAccount)
                if(c.count !=0){
                    Toast.makeText(this, "La cuenta ya existe",1).show()

                }
                else {
                    bankAccountManager.addToAccounts(newAccount)
                }

                println(newAccount.id)
                fButton_save.setBorderColor(Color.CYAN)
                Intent activityForFragment = new Intent(this, BankAccountListActivity.class)
                startActivity(activityForFragment)
            }


        }

    }

}
