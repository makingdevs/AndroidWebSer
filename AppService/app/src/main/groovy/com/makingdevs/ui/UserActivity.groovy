package com.makingdevs.ui

import android.content.Context
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle
import android.widget.EditText
import com.makingdevs.model.Account
import com.makingdevs.service.BankAccountManager
import com.makingdevs.service.BankAccountManagerImpl
import com.makingdevs.transaction.appservice.R
import groovy.transform.CompileStatic
import mehdi.sakout.fancybuttons.FancyButton;
@CompileStatic
public class UserActivity extends AppCompatActivity {
    FancyButton fButton_save
    FancyButton fButton_deposit
    EditText Name_txt_edit
    EditText Account_txt_edit
    String accountCatch
    String nameCatch
    BankAccountManager bankAccountManager = new BankAccountManagerImpl()

    static newIntentWithContext(Context ctx, Account account){
        new Intent(ctx, UserActivity)
    }
    static void recoge(Account account){

       // accountCatch =account.accountNumber.toString()
         //nameCatch = account.name.toString()
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Name_txt_edit = (EditText) findViewById(R.id.tex_editName)
        Account_txt_edit = (EditText) findViewById(R.id.tex_editA)
        fButton_deposit = (FancyButton) findViewById(R.id.button_deposit)
        fButton_save = (FancyButton) findViewById(R.id.button_edit)
        Bundle bundle = getIntent().getExtras()
        if(bundle != null){
        Name_txt_edit.setText(bundle.getString("name"))
        Account_txt_edit.setText(bundle.getString("account"))}

        fButton_deposit.onClickListener ={
            Intent activityDeposit = new Intent(this, DepositActivity.class)
            activityDeposit.putExtra("account", Account_txt_edit.getText().toString())
            startActivity(activityDeposit)
        }
        fButton_save.onClickListener ={
            //Account updateAccount = new Account(name: "${Account_txt_edit.getText().toString()}", accountNumber: "1234567890123456780")
            Account updateAccount = new Account(name: "${Name_txt_edit.getText().toString()}", accountNumber: "${Account_txt_edit.getText().toString()}")
            bankAccountManager.updateAccount(updateAccount)
        }


    }

}
