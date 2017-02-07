package com.makingdevs.ui

import android.os.Bundle
import android.support.annotation.Nullable

import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.makingdevs.service.BankAccountManager
import com.makingdevs.service.BankAccountManagerImpl
import com.makingdevs.transaction.appservice.R
import groovy.transform.CompileStatic

/**
 * Created by makingdevs on 07/02/17.
 */
@CompileStatic
class BankAccountFragment extends Fragment {

    RecyclerView mListAccounts
    BankAccountAdapter mBankAccountAdapter

    BankAccountFragment(){}

    @Override
    View onCreateView(LayoutInflater inflater,
                      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bank_account, container, false)
        mListAccounts = view.findViewById(R.id.list_accounts) as RecyclerView
        mListAccounts.setLayoutManager(new LinearLayoutManager(getActivity()))
        updateUI()
        setHasOptionsMenu(false)
        view
    }

    void updateUI() {
        BankAccountManager bam = new BankAccountManagerImpl()
        if(!mBankAccountAdapter){
            mBankAccountAdapter = new BankAccountAdapter(getActivity(), bam.retrieveAccounts())
            mListAccounts.adapter = mBankAccountAdapter
        } else {
            mBankAccountAdapter.setmAccounts(bam.retrieveAccounts())
            mBankAccountAdapter.notifyDataSetChanged()
        }
    }

    @Override
    void onResume() {
        super.onResume()
        updateUI()
    }

}
