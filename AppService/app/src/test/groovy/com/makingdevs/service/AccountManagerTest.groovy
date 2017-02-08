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
        assert totalAccounts == 4
    }

    @Test
    void test1AddOneAccount(){
        Account newAccount = new Account(name: "Jose Juan Reyes", accountNumber: "123456789012345678")
        Account savedAccount = bankAccountManager.addToAccounts(newAccount)
        assert savedAccount
        assert savedAccount.id
        int totalAccounts = bankAccountManager.totalAccounts
        assert totalAccounts == 5
    }

    @Test
    void test2AddAnotherAccount(){
        Account newAccount = new Account(name: "Brandon Vergara", accountNumber: "098765432112345678")
        Account savedAccount = bankAccountManager.addToAccounts(newAccount)
        assert savedAccount
        assert savedAccount.id
        int totalAccounts = bankAccountManager.totalAccounts
        assert totalAccounts == 6
        long id = savedAccount.id
        println id
    }
    @Test
    void test3DelateAccount(){
        int totalAccounts = bankAccountManager.totalAccounts
        println totalAccounts
        Account deleteAccount = new Account(accountNumber: "098765432112345678")
        Boolean savedAccount = bankAccountManager.deleteAccount(deleteAccount)
        totalAccounts = bankAccountManager.totalAccounts
        println totalAccounts
        assert savedAccount
    }
    @Test
    void test4DelateAccount(){
        int totalAccounts = bankAccountManager.totalAccounts
        println totalAccounts
        //Account account = new Account(accountNumber: "123456789012345678")
        Account updateAccount = new Account(name: "Jose Jose", accountNumber: "1234567890123456780")
        Boolean editAccount = bankAccountManager.updateAccount(updateAccount)
        totalAccounts = bankAccountManager.totalAccounts
        println totalAccounts
        assert editAccount
    }

}