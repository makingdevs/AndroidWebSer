package com.makingdevs.ui

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle
import android.widget.EditText
import com.makingdevs.transaction.appservice.R
import groovy.transform.CompileStatic
import mehdi.sakout.fancybuttons.FancyButton;
@CompileStatic
public class UserActivity extends AppCompatActivity {
    FancyButton fButton_save
    FancyButton fButton_deposit
    EditText Name_txt_edit
    EditText Account_txt_edit

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Name_txt_edit = (EditText) findViewById(R.id.tex_editName)
        Account_txt_edit = (EditText) findViewById(R.id.tex_editA)
        fButton_deposit = (FancyButton) findViewById(R.id.button_deposit)
        Bundle bundle = getIntent().getExtras()
        Name_txt_edit.setText(bundle.getString("name"))
        Account_txt_edit.setText(bundle.getString("account"))

        fButton_deposit.onClickListener ={
            Intent activityDeposit = new Intent(this, DepositActivity.class)
            activityDeposit.putExtra("account", Account_txt_edit.getText().toString())
            startActivity(activityDeposit)
        }


    }
}
