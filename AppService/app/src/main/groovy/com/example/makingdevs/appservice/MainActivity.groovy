package com.example.makingdevs.appservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import groovy.transform.CompileStatic
import mehdi.sakout.fancybuttons.FancyButton;

@CompileStatic
public class MainActivity extends AppCompatActivity {
    EditText mEditamount
    EditText mEditaccount
    EditText mEditdescription
    FancyButton mFancyB
    FancyButton mFancyC




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditaccount = (EditText) findViewById(R.id.editA)
        mEditamount = (EditText) findViewById(R.id.editAm)
        mEditdescription = (EditText) findViewById(R.id.editD)
        mFancyB = (FancyButton) findViewById(R.id.btn_preview)
        mFancyC = (FancyButton) findViewById(R.id.btn_clear)

        mFancyB.setGhost(true)
        mFancyC.setGhost(true)
        mFancyB.onClickListener={
            if(mEditaccount.equals("")){ println "Vacio"}
            String account = mEditaccount.getText().toString()
            if(account.equals("")){println "no debe ser vacío"}
            else{println account}
            Toast.makeText (this ,"Mensaje de prueba", 0).show();

        }

    }
}
