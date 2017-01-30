package com.example.makingdevs.appservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.makingdevs.common.Fluent
import groovy.json.JsonSlurper
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
    Button mPrueba

    String generator(String NumberAccount, String amount, String Description){
        def template = """\
<Abono>
<Clave>1101</Clave>
<FechaOperacion>20100323</FechaOperacion>
<InstitucionOrdenante clave="846"/>
<InstitucionBeneficiaria clave="90646"/>
<ClaveRastreo>GEM801</ClaveRastreo>
<Monto>${amount}</Monto>
<NombreBeneficiario>Techminds</NombreBeneficiario>
<TipoCuentaBeneficiario clave="40"/>
<CuentaBeneficiario>${NumberAccount}</CuentaBeneficiario>
<RfcCurpBeneficiario>RFC121212ABC</RfcCurpBeneficiario>
<ConceptoPago>${Description}</ConceptoPago>
<ReferenciaNumerica>27122016</ReferenciaNumerica>
<Empresa>STP</Empresa>
</Abono>

"""
        return template
    }

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
        mPrueba =  (Button) findViewById(R.id.prueba)

        mFancyB.setGhost(true)
        mFancyC.setGhost(true)
        mFancyB.onClickListener={

           if(mEditaccount.getText().toString().equals("") || mEditamount.getText().toString().equals("") || mEditdescription.getText().toString().equals("") ){
                println "no deje vaio"
                Toast.makeText (this ,"No dejé Campos vacíos ", 0).show()
            }
            else{

                NumberAccount = mEditaccount.getText()
                Description = mEditdescription.getText()
                amount = mEditamount.getText().toFloat()
                println "Número de cuenta: ${NumberAccount} Monto: ${amount} Descripcion: ${Description}"



            }
           /* if(mEditaccount.equals("")){ println "Vacioss"
                Toast.makeText (this ,"No dejé Campos vacíos ", 0).show()}
            String account = mEditaccount.getText().toString()
            if(account.equals("")){println "no debe ser vacío"}
            else{println account}*/






        }
        mFancyC.onClickListener = {
            mEditaccount.setText("")
            mEditdescription.setText("")
            mEditamount.setText("")
        }

        mPrueba.onClickListener = {
            Fluent.async {
                def jsonSlurper = new JsonSlurper()
                def httpConnection = new URL("http://emailerv2.modulusuno.com/countTotal")
                jsonSlurper.parseText(httpConnection.text)
            } then { result ->
                println result.properties
                println result['count']
            }

            println generator(NumberAccount, amount.toString(), Description)
        }



    }
}
