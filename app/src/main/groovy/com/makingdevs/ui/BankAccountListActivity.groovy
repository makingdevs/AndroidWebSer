package com.makingdevs.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.makingdevs.modulusuno.R
import com.makingdevs.service.AccesNetwork
import groovy.transform.CompileStatic

@CompileStatic
class BankAccountListActivity extends AppCompatActivity {

    @Override
    void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        getSupportActionBar().setTitle("  Beneficiarios")
        getSupportActionBar().setIcon(R.drawable.ic_people_black_24dp)
        getSupportActionBar().setDisplayShowHomeEnabled(true)
        FragmentManager fm =  getSupportFragmentManager()
        Fragment fragment = fm.findFragmentById(R.id.container)
        AccesNetwork obj = new AccesNetwork()

        if(!fragment)
            fm.beginTransaction().add(R.id.container, createFragment()).commit()
    }

    Fragment createFragment() {
         new BankAccountFragment() // Se crea el fragmento en base al contenedor
    }

}