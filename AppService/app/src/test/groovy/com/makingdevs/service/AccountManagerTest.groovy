package com.makingdevs.service

import com.makingdevs.model.Account
import com.makingdevs.service.BankAccountManager
import groovy.transform.CompileStatic
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@CompileStatic
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class AccountManagerTest {

    BankAccountManager bankAccountManager = new BankAccountManagerImpl()

    @Test
    void test0InitialAccountsInManager(){
        int totalAccounts = bankAccountManager.totalAccounts
        assert totalAccounts == 0
    }

    @Test
    void test1AddOneAccount(){
        Account newAccount = new Account(name: "Jose Juan Reyes", accountNumber: "123456789012345678")
        Account savedAccount = bankAccountManager.addToAccounts(newAccount)
        assert savedAccount
        assert savedAccount.id
        int totalAccounts = bankAccountManager.totalAccounts
        assert totalAccounts == 1
    }

    @Test
    void test2AddAnotherAccount(){
        Account newAccount = new Account(name: "Brandon Verfara", accountNumber: "098765432112345678")
        Account savedAccount = bankAccountManager.addToAccounts(newAccount)
        assert savedAccount
        assert savedAccount.id
        int totalAccounts = bankAccountManager.totalAccounts
        assert totalAccounts == 2
        long id = savedAccount.id
        println id
    }
}