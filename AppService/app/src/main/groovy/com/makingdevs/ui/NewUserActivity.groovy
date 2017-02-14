package com.makingdevs.ui

import android.content.Intent
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
    BankAccountManager bankAccountManager = BankAccountManagerDBImpl.getInstance(applicationContext)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        fButton_save = (FancyButton) findViewById(R.id.btn_save)
        mEdit_name = (EditText) findViewById(R.id.editName)
        mEdit_account = (EditText) findViewById(R.id.editAccount)
        fButton_save.onClickListener = {

            if(mEdit_account.getText().equals("") || mEdit_name.getText().equals("")){
                Toast.makeText(this, "No deje campos vacios", 1).show()
            }
            else if(mEdit_account.getText().length() !=18){
                Toast.makeText(this, "Deben ser 18 digitos ", 1).show()
            }
            else{
                Account newAccount = new Account(name: "${mEdit_name.getText().toString()}", accountNumber: "${mEdit_account.getText().toString()}")
                bankAccountManager.addToAccounts(newAccount)
                fButton_save.setBorderColor(Color.CYAN)
                Intent activityForFragment = new Intent(this, BankAccountListActivity.class)
                startActivity(activityForFragment)
            }


        }

    }

}
