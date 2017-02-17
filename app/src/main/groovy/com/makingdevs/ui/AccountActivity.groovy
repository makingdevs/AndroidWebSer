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
public class AccountActivity extends AppCompatActivity {

    FancyButton fButtonSave
    FancyButton fButtonDeposit
    FancyButton fButtonDelete
    EditText mTextName
    EditText mTextAccountNumber
    static Account account
    Long _id
    int status_EditText = 0
    BankAccountManager bankAccountManager

    static newIntentWithContext(Context ctx, Account account){
        this.account = account
        new Intent(ctx, AccountActivity)
    }

    private void alert() {
        AlertDialog alert = new AlertDialog.Builder(this).create()
        alert.setTitle("Estás Seguro")
        alert.setMessage("Realmente desea borrar")
        alert.setButton("Si") { DialogInterface dialog, int which ->
            Account deleteAccount = new Account(accountNumber: "${mTextAccountNumber.getText().toString()}")
            Boolean savedAccount = bankAccountManager.deleteAccount(deleteAccount)
            Intent activityForFragment = new Intent(this, BankAccountListActivity.class)
            startActivity(activityForFragment)
        } as DialogInterface.OnClickListener
        alert.setButton("No"){DialogInterface dialog, int which ->

        } as DialogInterface.OnClickListener
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        bankAccountManager = BankAccountManagerDBImpl.getInstance(this.applicationContext)

        mTextName = (EditText) findViewById(R.id.tex_editName)
        mTextAccountNumber = (EditText) findViewById(R.id.tex_editA)
        fButtonDeposit = (FancyButton) findViewById(R.id.button_deposit)
        fButtonSave = (FancyButton) findViewById(R.id.button_edit)
        fButtonDelete = (FancyButton) findViewById(R.id.button_delete)
        mTextName.setEnabled(false)
        mTextAccountNumber.setEnabled(false)

        mTextName.text = account.name
        mTextAccountNumber.text = account.accountNumber
        _id = account.id

        fButtonDeposit.onClickListener ={
            Intent activityDeposit = new Intent(this, DepositActivity.class)
            activityDeposit.putExtra("account", mTextAccountNumber.getText().toString())
            startActivity(activityDeposit)
        }
        fButtonSave.onClickListener = {
            if(status_EditText == 0){
                mTextName.setEnabled(true)
                mTextAccountNumber.setEnabled(true)
                fButtonSave.setText("Guardar  ")
                status_EditText = 1
                _id = account.id
            }
            else{
                if(mTextAccountNumber.getText().length() !=18 ){
                    Toast.makeText(this, "Deben ser 18 digitos ", 0).show()
                }
                else{
                    Account updateAccount = new Account(name: "${mTextName.getText().toString()}", accountNumber: "${mTextAccountNumber.getText().toString()}")
                    updateAccount.id = _id
                    bankAccountManager.updateAccount(updateAccount)
                    mTextName.setEnabled(false)
                    mTextAccountNumber.setEnabled(false)
                    fButtonSave.setText("Editar       ")
                    status_EditText = 0}
            }

        }
        fButtonDelete.onClickListener = {
            Intent activityForFragment = new Intent(this,BankAccountListActivity)
            int change = 0

            AlertDialog.Builder clearConfirmDialog = new AlertDialog.Builder(this)
            clearConfirmDialog.setMessage("Estas seguro de querer boorrar el usuario").setCancelable(false)
                    .setPositiveButton("Si", { DialogInterface dialog, int id ->
                Account deleteAccount = new Account(accountNumber: "${mTextAccountNumber.getText()}")
                bankAccountManager.deleteAccount(deleteAccount)
                change = 1
            } as DialogInterface.OnClickListener)
                    .setNegativeButton("No", { DialogInterface dialog, int id ->
                dialog.cancel()
            } as DialogInterface.OnClickListener)
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
