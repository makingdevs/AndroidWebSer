package com.makingdevs.service

import com.makingdevs.model.Account
import groovy.transform.CompileStatic

@CompileStatic
class BankAccountManagerImpl implements BankAccountManager {

    static List<Account> accounts = [
            new Account(name:"Juan",accountNumber: "123456", id: System.currentTimeMillis()),
            new Account(name:"Jorge",accountNumber: "123456", id: System.currentTimeMillis()),
            new Account(name:"Carlo",accountNumber: "123456", id: System.currentTimeMillis()),
            new Account(name:"Brandon",accountNumber: "123456", id: System.currentTimeMillis())
    ]

    @Override
    Integer getTotalAccounts() {
        accounts.size()
    }

    @Override
    Account addToAccounts(Account account) {
        account.id = System.currentTimeMillis()
        accounts.add(account)
        account
    }

    @Override
    Boolean deleteAccount(Account account) {
        return null
    }

    @Override
    Boolean updateAccount(Account account) {
        return null
    }

    @Override
    Account getAccountById(Long id) {
        return null
    }

    @Override
    List<Account> retrieveAccounts() {
        accounts
    }
}