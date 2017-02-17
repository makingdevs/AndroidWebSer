package com.makingdevs.service;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo
import groovy.transform.CompileStatic;

/**
 * Created by makingdevs on 17/02/17.
 */
@CompileStatic
public class AccesNetwork {
    /*public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE)
        NetworkInfo netInfo = cm.getActiveNetworkInfo()
        return netInfo != null && netInfo.isConnectedOrConnecting()
    }*/

    static Boolean isOnlineNet() {
        try {
            Process p = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.es")
            int val           = p.waitFor()
            boolean reachable = (val == 0)
            return reachable;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }
        return false
    }
}
