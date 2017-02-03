package com.makingdevs.transaction.appservice

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.getbase.floatingactionbutton.FloatingActionButton
import groovy.transform.CompileStatic

@CompileStatic
class ListActivity extends AppCompatActivity {


    FloatingActionButton fB_new
    FloatingActionButton fB_deposit

    void alert(){
        AlertDialog alert = new AlertDialog.Builder(this).create()
        alert.setTitle("Estás Seguro")
        alert.setMessage("Realmente desea borrar")
        alert.setButton("Si") { DialogInterface dialog, int which ->
            // TODO: Confirmar el borrado
        } as DialogInterface.OnClickListener
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        fB_deposit = (FloatingActionButton) findViewById(R.id.fb_deposit_user)
        fB_new = (FloatingActionButton) findViewById(R.id.fb_new_user)

        fB_deposit.onClickListener = {
            Intent activityForDeposit = new Intent(this,DepositActivity.class)
            startActivity(activityForDeposit)
        }
        fB_new.onClickListener = {
            Intent activityForUser = new Intent(this,UserActivity.class)
            startActivity(activityForUser)
        }



    }
}
