package com.example.makingdevs.appservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
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
    String NumberAccount
    String Description
    float amount





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Ocultar teclado virtual



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

           /* if(mEditaccount.getText()=="6"){
                println "no deje vaio"
                Toast.makeText (this ,"No dejé Campos vacíos ", 0).show()
            }
            else{
                println "hola"
                NumberAccount = mEditaccount.getText()
                Description = mEditdescription.getText()


                println "${amount}  ${Description} ${NumberAccount}"
            }*/
            if(mEditaccount.equals("")){ println "Vacioss"
                Toast.makeText (this ,"No dejé Campos vacíos ", 0).show()}
            String account = mEditaccount.getText().toString()
            if(account.equals("")){println "no debe ser vacío"}
            else{println account}




        }
        mFancyC.onClickListener = {
            mEditaccount.setText("")
            mEditdescription.setText("")
            mEditamount.setText("")
        }




    }
}
