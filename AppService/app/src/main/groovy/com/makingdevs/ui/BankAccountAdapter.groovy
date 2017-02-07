package com.makingdevs.ui

import android.content.Context
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
@CompileStatic
class BankAccountAdapter extends RecyclerView.Adapter<BankAccountViewHolder> {

    Context mContext
    List<Account> mAccounts

    BankAccountAdapter(Context context, List<Account> accounts){
        mContext = context
        mAccounts = accounts
    }

    @Override
    BankAccountViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_user, parent ,false)
        new BankAccountViewHolder(view)
    }

    @Override
    void onBindViewHolder(BankAccountViewHolder holder, int position) {
        Account account = mAccounts[position]
        holder.bind(account)
    }

    @Override
    int getItemCount() {
        mAccounts.size()
    }

    class BankAccountViewHolder extends RecyclerView.ViewHolder {

        TextView mUsername
        TextView mAccountNumber

        BankAccountViewHolder(View itemView) {
            super(itemView)
            mUsername = (TextView) itemView.findViewById(R.id.txt_Name_User)
            mAccountNumber = (TextView) itemView.findViewById(R.id.txt_Name_Account)
        }

        void bind(Account account){
            mUsername.text = account.name
            mAccountNumber.text = account.accountNumber
        }
    }
}
