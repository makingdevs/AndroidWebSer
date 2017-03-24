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
    //static final String API_URL = "http://192.168.1.244:8080//STP/stpDepositNotification"
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
       // HttpURLConnection connection = API_URL.toURL().openConnection() as HttpURLConnection
        connection.doOutput = true
        connection.setRequestProperty("Content-Type", "application/json")
        connection.setRequestProperty("Accept", "application/json")
        connection.outputStream.withWriter { Writer writter -> writter << "${createXmlDocumentForPayment()}" }
        println createXmlDocumentForPayment()
        String responseText = connection.inputStream.withReader { Reader reader -> reader.text }
        println responseText
        String response = new JsonSlurper().parseText(responseText)
        [response:response, method: connection.requestMethod,responseCode:connection.responseCode]
    }

    @TypeChecked
    String createXmlDocumentForPayment() {
        Integer clave = Math.abs(new Random().nextInt() % 600 + 1)
        """\
{
  "clave": "${clave}",
  "claveInstitucionBeneficiaria": "90646",
  "claveInstitucionOrdenante": "846",
  "claveRastreo": "201703100010",
  "claveTipoCuentaBeneficiario": "40",
  "conceptoPago": "${description}",
  "cuentaBeneficiario": "${numberAccount}",
  "empresa": "TECHMINDS",
  "fechaOperacion": "${new Date().format("yyyyMMdd")}",
  "monto": "${amount}",
  "nombreBeneficiario": "Soft-Temoc SA",
  "nombreOrdenante": "MakingDevs",
  "referenciaNumerica": "201703100010",
  "rfcCurpBeneficiario": "PAG770214SFT"
}
"""
    }


}
