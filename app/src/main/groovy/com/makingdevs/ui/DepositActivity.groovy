package com.makingdevs.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.makingdevs.modulusuno.R
import com.makingdevs.network.PaymentClient
import groovy.transform.CompileStatic
import mehdi.sakout.fancybuttons.FancyButton

@CompileStatic
public class DepositActivity extends AppCompatActivity {

    private static final String TAG = "DepositActivity"

    EditText mEditamount
    EditText mEditaccount
    EditText mEditdescription
    FancyButton mFancyB
    FancyButton mFancyC
    String numberAccount
    String description
    float amount
    TextView contador
    Spinner sMethod

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Ocultar teclado virtual
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);
        getSupportActionBar().setTitle("  Depósito");
        getSupportActionBar().setIcon(R.drawable.ic_cash)
        getSupportActionBar().setDisplayShowHomeEnabled(true)
        mEditaccount = (EditText) findViewById(R.id.textNameBeneficiary)
        mEditamount = (EditText) findViewById(R.id.textAmount)
        mEditdescription = (EditText) findViewById(R.id.textDescription)
        mFancyB = (FancyButton) findViewById(R.id.buttonDeposit)
        mFancyC = (FancyButton) findViewById(R.id.buttonDelete)
        sMethod = (Spinner) findViewById(R.id.spinnerMethod)
        contador = (TextView) findViewById(R.id.textCont);
        Bundle bundle = getIntent().getExtras()
        if (bundle != null) {
            mEditaccount.setText(bundle.getString("account"))
        }

        Map<String, Closure> textChangedListener = [
                beforeTextChanged: { CharSequence s, int start, int count, int after -> },
                onTextChanged: { CharSequence s, int start, int before, int count ->},
                afterTextChanged: { Editable s ->
                    String tamanoString = String.valueOf(s.length()); //255 como maximo
                    Integer a = 255 - tamanoString.toInteger()
                    contador.setText(a.toString());
                }
        ]

        mEditdescription.addTextChangedListener(textChangedListener as TextWatcher)

        mFancyB.onClickListener = {
            if (mEditaccount.getText().toString().equals("") || mEditamount.getText().toString().equals("") || mEditdescription.getText().toString().equals("")) {
                Log.d TAG, "No dejar vacío"
                Toast.makeText(this, "No dejé Campos vacíos ", 0).show()
            } else if (mEditaccount.getText().length() != 18) {
                Toast.makeText(this, "Ingrese 18 Digitos en Cuenta ", 0).show()
            } else {

                numberAccount = mEditaccount.getText()
                description = mEditdescription.getText()
                amount = mEditamount.getText().toFloat()
                String Method = sMethod.getSelectedItem().toString()
                PaymentClient paymentClient = new PaymentClient(numberAccount:numberAccount,amount:"${amount}",description:description, environment:Method)
                paymentClient.onSuccess = {
                    Toast.makeText(this, "Transacción exitosa !!!", 1).show()
                    onBackPressed()
                }
                paymentClient.onError = {
                    Toast.makeText(this, "Paso algo inesperado", 0).show()
                }
                paymentClient.doPayment()


            }

        }

        mFancyC.onClickListener = {
            mEditaccount.setText("")
            mEditdescription.setText("")
            mEditamount.setText("")
            Log.d TAG, "${sMethod.getSelectedItem()}"

        }


    }
}
