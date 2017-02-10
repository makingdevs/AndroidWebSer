package com.makingdevs.service

import com.makingdevs.model.Account
import groovy.transform.CompileStatic

@CompileStatic
class BankAccountManagerImpl implements BankAccountManager {

    static List<Account> accounts = [
            new Account(name:"QA",accountNumber: "646180132406300019", id: System.currentTimeMillis()),
            new Account(name:"Prueba",accountNumber: "1343452222", id: System.currentTimeMillis()),
            new Account(name:"Jorge",accountNumber: "646180132406300006", id: System.currentTimeMillis()),
            new Account(name:"Stage",accountNumber: "646180132406600010", id: System.currentTimeMillis()),
            new Account(name:"Brandon",accountNumber: "646180132407000004", id: System.currentTimeMillis())
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
        boolean found = false
        println account.accountNumber

        accounts = accounts - accounts.find(){if(it.accountNumber == account.accountNumber){
            found = true
        }
        }

        return found

    }

    @Override
    Boolean updateAccount(Account account) {

        if(accounts.accountNumber.contains(account.accountNumber) || accounts.name.contains(account.name)){
            accounts.each {if(it.accountNumber == account.accountNumber || it.name == account.name){
                it.name = account.name
                it.accountNumber = account.accountNumber
            }
        }
            println accounts.name
            println(accounts.accountNumber)
            println "Actualiza"
        return true
        }
        println accounts.name
        println "No entre"
        return false

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