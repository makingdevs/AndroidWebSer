package com.makingdevs.ui

import android.content.Context
import android.content.Intent
import android.support.v7.view.menu.MenuView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.makingdevs.model.Account
import com.makingdevs.transaction.appservice.R
import groovy.transform.CompileStatic

/**
 * Created by makingdevs on 07/02/17.
 */
class BankAccountAdapter extends RecyclerView.Adapter<BankAccountViewHolder> { // Se genera sin el Holder

    Context mContext
    List<Account> mAccounts


    BankAccountAdapter(Context context, List<Account> accounts){
        mContext = context // Contexto donde se esta trabajando el fragmento en este caso seria para nuestro activity del item
        mAccounts = accounts // asignamos la lista
    }

    @Override
    BankAccountViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { // este metodo se genera sin el Holder, nostros lo implementamos despues
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_user, parent ,false)

        new BankAccountViewHolder(view)

    }


    @Override
    void onBindViewHolder(BankAccountViewHolder holder, int position) {// Asigna la posicion de la cuenta
        Account account = mAccounts[position]
        holder.bind(account)
    }

    @Override
    int getItemCount() { // Obtiene el tamaño de la lista
        mAccounts.size()
    }

    class BankAccountViewHolder extends RecyclerView.ViewHolder { // El ViewHoldes es el que se encarga de mapear la vista

        TextView mUsername // Trabajar los datos de los itemView
        TextView mAccountNumber
        Account mAccount // Se crea un objeto para despues ser empatado

        BankAccountViewHolder(View itemView) {
            super(itemView)
            mUsername = (TextView) itemView.findViewById(R.id.txt_Name_User) // Utilizando la estructura de el ItemView Activity
            mAccountNumber = (TextView) itemView.findViewById(R.id.txt_Name_Account)
        }

        void bind(Account account){
            mAccount = account  // Le pasamos la posicion del objeto
            mUsername.text = mAccount.name // Empatamos variables
            mAccountNumber.text = mAccount.accountNumber
            itemView.onClickListener = {
                mContext.startActivity(new Intent(mContext,UserActivity.class));


                println mAccount.id
            }
        }

    }
}

