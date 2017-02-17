package com.makingdevs.ui

import android.content.Intent
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

// Usa estas versiones para no tener problemas con la compatibilidad
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.getbase.floatingactionbutton.FloatingActionButton
import com.makingdevs.modulusuno.R
import com.makingdevs.service.BankAccountManagerDBImpl
import groovy.transform.CompileStatic

/**
 * Created by makingdevs on 07/02/17.
 */
@CompileStatic
class BankAccountFragment extends Fragment {

    private static final String TAG = "BankAccountFragment"

    FloatingActionButton fB_new
    FloatingActionButton fB_deposit
    RecyclerView mListAccounts
    BankAccountAdapter mBankAccountAdapter

    BankAccountFragment(){
    }

    @Override
    View onCreateView(LayoutInflater inflater, // Metodo para ir haciendo crecer la lista en cuestion a la vista
                      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bank_account, container, false) // Indicamos el Activity que hará referencia a nuestro fragmento
        mListAccounts = view.findViewById(R.id.list_accounts) as RecyclerView
        mListAccounts.setLayoutManager(new LinearLayoutManager(getActivity()))
        updateUI()
        setHasOptionsMenu(false)
        fB_deposit = (FloatingActionButton) view.findViewById(R.id.fb_deposit_user) // Se  necesita indicar view antes del metdo find ya que es nuestra variable con la que interactuamos en el fragmento
        fB_new = (FloatingActionButton) view.findViewById(R.id.fb_new_user)
        fB_deposit.onClickListener = {
            Intent activityForDeposit = new Intent(getActivity(),DepositActivity.class)
            startActivity(activityForDeposit)
        }
        fB_new.onClickListener = {
            Intent activityForUserEdit = new Intent(getActivity(),NewAccountActivity.class) // Se usa getActivity porque estamos en un fragemento así que necesitamos indicar que queremos obtener
            startActivity(activityForUserEdit)
        }
        view

    }

    void updateUI() { // Actualiza la vista, según la modificacion que se vaya realizando
        //BankAccountManager bam = new BankAccountManagerImpl() // Se crea un metodo de la clase, para saber si hay cuentas
        Log.d(TAG, "Counter ${BankAccountManagerDBImpl.getInstance(this.context).totalAccounts}")
        if(!mBankAccountAdapter){
            mBankAccountAdapter = new BankAccountAdapter(getActivity(), BankAccountManagerDBImpl.getInstance(this.context).retrieveAccounts())
            mListAccounts.adapter = mBankAccountAdapter // Se asigna la lista del account
        } else {
            mBankAccountAdapter.setmAccounts(BankAccountManagerDBImpl.getInstance(this.context).retrieveAccounts()) // le indica la lista
            mBankAccountAdapter.notifyDataSetChanged() // Forza a actualizar la vista en cada cambio y lo hace automaticamanete
        }
    }

    @Override
    void onResume() { // Actualiza nuevamente por si las dudas
        super.onResume()
        updateUI()
    }

}
