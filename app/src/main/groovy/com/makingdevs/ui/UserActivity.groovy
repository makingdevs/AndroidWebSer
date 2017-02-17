package com.makingdevs.ui

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.Toast
import com.makingdevs.model.Account
import com.makingdevs.modulusuno.R
import com.makingdevs.service.BankAccountManager
import com.makingdevs.service.BankAccountManagerDBImpl
import groovy.transform.CompileStatic
import mehdi.sakout.fancybuttons.FancyButton

@CompileStatic
public class UserActivity extends AppCompatActivity {
    FancyButton fButton_save
    FancyButton fButton_deposit
    FancyButton fButton_delete
    EditText Name_txt_edit
    EditText Account_txt_edit
    String accountCatch
    String nameCatch
    int _id
    int status_EditText = 0
    BankAccountManager bankAccountManager

    static newIntentWithContext(Context ctx, Account account){
        new Intent(ctx, UserActivity)
    }
    static void recoge(Account account){

       // accountCatch =account.accountNumber.toString()
         //nameCatch = account.name.toString()
    }
    private void alert() {
        AlertDialog alert = new AlertDialog.Builder(this).create()
        alert.setTitle("Estás Seguro")
        alert.setMessage("Realmente desea borrar")
        alert.setButton("Si") { DialogInterface dialog, int which ->
            Account deleteAccount = new Account(accountNumber: "${Account_txt_edit.getText().toString()}")
            Boolean savedAccount = bankAccountManager.deleteAccount(deleteAccount)
            Intent activityForFragment = new Intent(this, BankAccountListActivity.class)
            startActivity(activityForFragment)
        } as DialogInterface.OnClickListener
        alert.setButton("No"){DialogInterface dialog, int which ->

        } as DialogInterface.OnClickListener
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        bankAccountManager = BankAccountManagerDBImpl.getInstance(this.applicationContext)

        Name_txt_edit = (EditText) findViewById(R.id.tex_editName)
        Account_txt_edit = (EditText) findViewById(R.id.tex_editA)
        fButton_deposit = (FancyButton) findViewById(R.id.button_deposit)
        fButton_save = (FancyButton) findViewById(R.id.button_edit)
        fButton_delete = (FancyButton) findViewById(R.id.button_delete)
        Name_txt_edit.setEnabled(false)
        Account_txt_edit.setEnabled(false)
        Bundle bundle = getIntent().getExtras()
        if(bundle != null){
        Name_txt_edit.setText(bundle.getString("name"))
        Account_txt_edit.setText(bundle.getString("account"))
            _id = bundle.getInt("id")


        }

        fButton_deposit.onClickListener ={
            Intent activityDeposit = new Intent(this, DepositActivity.class)
            activityDeposit.putExtra("account", Account_txt_edit.getText().toString())
            startActivity(activityDeposit)
        }
        fButton_save.onClickListener ={
            if(status_EditText==0){
                Name_txt_edit.setEnabled(true)
                Account_txt_edit.setEnabled(true)
                fButton_save.setText("Guardar  ")
                status_EditText = 1
                _id = bankAccountManager.getId(Name_txt_edit.getText().toString(),Account_txt_edit.text.toString())
            }
            else{
                if(Account_txt_edit.getText().length() !=18 ){
                    Toast.makeText(this, "Deben ser 18 digitos ", 0).show()
                }
                else{
                    Account updateAccount = new Account(name: "${Name_txt_edit.getText().toString()}", accountNumber: "${Account_txt_edit.getText().toString()}")
                    updateAccount.id = _id
                    bankAccountManager.updateAccount(updateAccount)
                    Name_txt_edit.setEnabled(false)
                    Account_txt_edit.setEnabled(false)
                    fButton_save.setText("Editar       ")
                    status_EditText = 0}
                }

        }
        fButton_delete.onClickListener = {
            Intent activityForFragment = new Intent(this,BankAccountListActivity.class)
            int change = 0
            AlertDialog.Builder clearConfirmDialog = new AlertDialog.Builder(this)
            clearConfirmDialog.setMessage("Estas seguro de querer boorrar el usuario").setCancelable(false)
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                void onClick(DialogInterface dialog, int id) {

                    Account deleteAccount = new Account(accountNumber: "${Account_txt_edit.getText()}")
                   /* println("***********************************************")
                    println deleteAccount.accountNumber
                    println("***********************************************")*/
                    bankAccountManager.deleteAccount(deleteAccount)
                    change = 1




                }
            })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                void onClick(DialogInterface dialog, int id) {
                    //  Action for 'NO' Button
                    dialog.cancel();
                }
            })
            AlertDialog alert = clearConfirmDialog.create()
            alert.setIcon(R.drawable.ic_error_outline_black_24dp);
            alert.setTitle("Alerta")
            alert.show()
            alert.onDismissListener={
                startActivity(activityForFragment)
            }



        }


    }

}
