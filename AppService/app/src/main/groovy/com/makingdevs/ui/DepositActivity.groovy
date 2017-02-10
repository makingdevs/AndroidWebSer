package com.makingdevs.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.makingdevs.network.PaymentClient
import com.makingdevs.transaction.appservice.R
import groovy.transform.CompileStatic
import mehdi.sakout.fancybuttons.FancyButton;


@CompileStatic
public class DepositActivity extends AppCompatActivity {
    EditText mEditamount
    EditText mEditaccount
    EditText mEditdescription
    FancyButton mFancyB
    FancyButton mFancyC
    String NumberAccount
    String Description
    float amount
    TextView contador
    Spinner SMethod



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Ocultar teclado virtual
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);
        mEditaccount = (EditText) findViewById(R.id.editA)
        mEditamount = (EditText) findViewById(R.id.editAm)
        mEditdescription = (EditText) findViewById(R.id.editD)
        mFancyB = (FancyButton) findViewById(R.id.btn_preview)
        mFancyC = (FancyButton) findViewById(R.id.btn_clear)
        SMethod = (Spinner) findViewById(R.id.spinner_method)
        contador = (TextView) findViewById(R.id.texto_contador);
        Bundle bundle = getIntent().getExtras()
        if (bundle != null) {
            mEditaccount.setText(bundle.getString("account"))
        }


        mEditdescription.addTextChangedListener(new TextWatcher() {
            @Override
            void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            void afterTextChanged(Editable s) {

                String tamanoString = String.valueOf(s.length()); //255 como maximo
                Integer a = 255 - tamanoString.toInteger()
                contador.setText(a.toString());

            }
        })
        mFancyB.onClickListener = {

            if (mEditaccount.getText().toString().equals("") || mEditamount.getText().toString().equals("") || mEditdescription.getText().toString().equals("")) {
                println "no deje vacio"
                Toast.makeText(this, "No dejé Campos vacíos ", 0).show()
            } else if (mEditaccount.getText().length() != 18) {
                Toast.makeText(this, "Ingrese 18 Digitos en Cuenta ", 0).show()
            } else {

                NumberAccount = mEditaccount.getText()
                Description = mEditdescription.getText()
                amount = mEditamount.getText().toFloat()
                String Method = SMethod.getSelectedItem().toString()
                PaymentClient paymentClient = new PaymentClient(numberAccount:NumberAccount,amount:"${amount}",description:Description, environment:Method)
                paymentClient.onSuccess = {
                    Toast.makeText(this, "Transacción exitosa !!!", 1).show()
                    Intent activityList = new Intent(this, BankAccountListActivity.class)
                    startActivity(activityList)
                }
                paymentClient.onError = {
                    Toast.makeText(this, "Paso algo inesperado", 0).show()
                }
                paymentClient.doPayment()


            }
            /* if(mEditaccount.equals("")){ println "Vacioss"
                 Toast.makeText (this ,"No dejé Campos vacíos ", 0).show()}
             String Account = mEditaccount.getText().toString()
             if(Account.equals("")){println "no debe ser vacío"}
             else{println Account}*/


        }

        mFancyC.onClickListener = {
            mEditaccount.setText("")
            mEditdescription.setText("")
            mEditamount.setText("")
            println SMethod.getSelectedItem().toString()

        }


    }
}
