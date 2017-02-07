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
    Integer getTotalAccounts() { // Metodo implementado de la interface que creamos anteriormente
        accounts.size()
    }

    @Override
    Account addToAccounts(Account account) { // Metodo implementado de la interface que creamos anteriormente
        account.id = System.currentTimeMillis() // Se asigan la id de maenera alatoria porque nose pide
        accounts.add(account)
        account
    }

    @Override
    Boolean deleteAccount(Account account) {
        accounts.each { if(it.accountNumber == account.accountNumber){
            return true
        }
         else {
            return false
        }
        }
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