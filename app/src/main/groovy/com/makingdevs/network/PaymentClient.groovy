package com.makingdevs.network

import android.util.Log
import groovy.json.JsonSlurper
import groovy.transform.TypeChecked;

/**
 * Created by makingdevs on 10/02/17.
 */

class PaymentClient {

    private static final String TAG = "PaymentClient"

    static final String API_URL = "http://impulsomx-api-:environment.modulusuno.com/STP/stpDepositNotification"

    String numberAccount
    String amount
    String description
    String environment
    Closure onSuccess
    Closure onError
    String name

    String doPayment(){
        Fluent.async {
            makeConnection()
        } then { Map response ->
            Log.d(TAG, "RESPONSE: ${response}")
            this.name = response.getAt("response")
            switch (response["responseCode"]){
                case 201:
                    onSuccess(name)
                    break
                default:
                    onError()
                    break
            }
        }
       true
    }

    private Map makeConnection(){
        HttpURLConnection connection = API_URL.replace(":environment",environment).toURL().openConnection() as HttpURLConnection
        connection.doOutput = true
        connection.outputStream.withWriter { Writer writter -> writter << "notification=${createXmlDocumentForPayment()}" }
        println createXmlDocumentForPayment()
        String responseText = connection.inputStream.withReader { Reader reader -> reader.text }
        String response = new JsonSlurper().parseText(responseText)
        [response:response, method: connection.requestMethod,responseCode:connection.responseCode]
    }

    @TypeChecked
    String createXmlDocumentForPayment() {
        Integer clave = Math.abs(new Random().nextInt() % 600 + 1)
        """\
<Abono>
<Clave>${clave}</Clave>
<FechaOperacion>${new Date().format("yyyyMMdd")}</FechaOperacion>
<InstitucionOrdenante clave="846"/>
<InstitucionBeneficiaria clave="90646"/>
<ClaveRastreo>GEM804</ClaveRastreo>
<Monto>${amount}</Monto>
<NombreBeneficiario>Techminds</NombreBeneficiario>
<TipoCuentaBeneficiario clave="40"/>
<CuentaBeneficiario>${numberAccount}</CuentaBeneficiario>
<RfcCurpBeneficiario>RFC121212ABC</RfcCurpBeneficiario>
<ConceptoPago>${description}</ConceptoPago>
<ReferenciaNumerica>27122016</ReferenciaNumerica>
<Empresa>STP</Empresa>
</Abono>
"""
    }


}
