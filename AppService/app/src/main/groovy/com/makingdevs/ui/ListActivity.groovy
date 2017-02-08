package com.makingdevs.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import com.getbase.floatingactionbutton.FloatingActionButton
import com.makingdevs.model.Account
import com.makingdevs.transaction.appservice.R
import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import groovy.transform.TypeCheckingMode

@CompileStatic
class ListActivity extends AppCompatActivity {


    FloatingActionButton fB_new
    FloatingActionButton fB_deposit

    @TypeChecked(TypeCheckingMode.SKIP)
    private void alert() {
        AlertDialog alert = new AlertDialog.Builder(this).create()
        alert.setTitle("Estás Seguro")
        alert.setMessage("Realmente desea borrar")
        alert.setButton("Si") { DialogInterface dialog, int which ->
            // TODO: Confirmar el borrado
        } as DialogInterface.OnClickListener
    }


    @Override
    @TypeChecked(TypeCheckingMode.SKIP)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        fB_deposit = (FloatingActionButton) findViewById(R.id.fb_deposit_user)
        fB_new = (FloatingActionButton) findViewById(R.id.fb_new_user)
        Bundle bundle = getIntent().getExtras()
        if (bundle != null) {
            String name = bundle.getString("name")
            String account = bundle.getString("account")
        }

        fB_deposit.onClickListener = {
            Intent activityForDeposit = new Intent(this, DepositActivity.class)
            startActivity(activityForDeposit)
        }
        fB_new.onClickListener = {
            Intent activityForUserEdit = new Intent(this, NewUserActivity.class)
            startActivity(activityForUserEdit)
        }
        ListView list_user = (ListView) findViewById(R.id.list_user)
        ArrayList<Account> arrayList = new ArrayList<Account>()
        Account account
        account = new Account(Photo: getDrawable(R.drawable.ic_user_24dp), Name: "Diego Vergara Magaña", Acoount: "987654321987654321")

        arrayList.add(account)
        account = new Account(Photo: getDrawable(R.drawable.ic_user_24dp), Name: "Brandon Vergara Magaña", Acoount: "123456789123456789")
        arrayList.add(account)

        AdapterDatos adapter = new AdapterDatos(this, arrayList)
        list_user.setAdapter(adapter)

        list_user.onItemClickListener = new AdapterView.OnItemClickListener() {
            @Override

            void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {

                    case 0:
                        Intent activityForUser = new Intent(view.getContext(), UserActivity.class)
                        activityForUser.putExtra("name", adapter.items[0].Name.toString())
                        activityForUser.putExtra("account", adapter.items[0].Acoount.toString())
                        startActivityForResult(activityForUser, 0)
                        println arrayList[1].Name.toString()
                        //send.putExtra("name",arrayList[1].Name.toString())
                        break;
                    case 1:
                        println "Hola"
                        break;


                }
            }
        }

    }
}
