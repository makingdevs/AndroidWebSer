package com.makingdevs.transaction.appservice


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.makingdevs.transaction.common.Fluent
import groovy.json.JsonSlurper
import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import groovy.transform.TypeCheckingMode
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
    TextView contador

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
    boolean connection(){
        try {
            String xml = generator(NumberAccount, amount.toString(), Description);
            Fluent.async {
                def jsonSlurper = new JsonSlurper()
                URLConnection connection = new URL("http://impulsomx-api-qa.modulusuno.com/STP/stpDepositNotification").openConnection()
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
        setContentView(R.layout.activity_main);
        mEditaccount = (EditText) findViewById(R.id.editA)
        mEditamount = (EditText) findViewById(R.id.editAm)
        mEditdescription = (EditText) findViewById(R.id.editD)
        mFancyB = (FancyButton) findViewById(R.id.btn_preview)
        mFancyC = (FancyButton) findViewById(R.id.btn_clear)
        mFancyB.setGhost(true)
        mFancyC.setGhost(true)
        contador = (TextView) findViewById(R.id.texto_contador);
        // mEditdescription.setInputType(android.text.InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS)
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
                contador.setText(tamanoString);

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
               if(connection()){Toast.makeText (this ,"Transacción exitosa ", 0).show() }
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

        }





    }
}
