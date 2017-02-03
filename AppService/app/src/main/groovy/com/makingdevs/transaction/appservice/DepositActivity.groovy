package com.makingdevs.transaction.appservice


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.makingdevs.transaction.common.Fluent
import groovy.json.JsonSlurper
import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import groovy.transform.TypeCheckingMode
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

    String generator(String NumberAccount, String amount, String Description){
        Date fecha = new Date();
        System.out.println(fecha.getDateString());
        def template = """\
<Abono>
<Clave>1101</Clave>
<FechaOperacion>${fecha.format("yyyyMMdd")}</FechaOperacion>
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


    @TypeChecked(TypeCheckingMode.SKIP)
    boolean connection(String Method){
        try {
            String xml = generator(NumberAccount, amount.toString(), Description);
            Fluent.async {
                def jsonSlurper = new JsonSlurper()
                URLConnection connection
                connection = new URL("http://impulsomx-api-${Method}.modulusuno.com/STP/stpDepositNotification").openConnection()
                connection.requestMethod = 'POST'
                connection.doOutput = true
                def writer = new OutputStreamWriter(connection.outputStream)
                writer.write("notification=${xml}")
                writer.flush()
                writer.close()
                connection.connect()
                println connection
                def response = connection.content.text
                println response

                jsonSlurper.parseText(response)

            } then { result ->

                println result.properties
                //println result['message']

            }
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

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
        mFancyB.onClickListener={

           if(mEditaccount.getText().toString().equals("") || mEditamount.getText().toString().equals("") || mEditdescription.getText().toString().equals("") ){
                println "no deje vaio"
                Toast.makeText (this ,"No dejé Campos vacíos ", 0).show()
            }
           else if(mEditaccount.getText().length()!=18){
               Toast.makeText (this ,"Ingrese 18 Digitos en Cuenta ", 0).show()
           }
            else{

                NumberAccount = mEditaccount.getText()
                Description = mEditdescription.getText()
                amount = mEditamount.getText().toFloat()
                println "Número de cuenta: ${NumberAccount} Monto: ${amount} Descripcion: ${Description}"
               String Method = SMethod.getSelectedItem().toString()
               if(connection(Method)){Toast.makeText (this ,"Transacción exitosa ", 0).show() }
               else{Toast.makeText (this ,"Paso algo inesperado", 0).show()}
               println generator(NumberAccount, amount.toString(), Description)
               Toast.makeText (this ,"", 0).show()


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
            println SMethod.getSelectedItem().toString()

        }





    }
}
