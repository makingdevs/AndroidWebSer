package com.makingdevs.service

import android.database.Cursor
import com.makingdevs.model.Account

/**
 * Created by makingdevs on 07/02/17.
 */
interface BankAccountManager {
    // Metodos de la interface en consecuencia a lista que necesitamos
    Integer getTotalAccounts()
    Account addToAccounts(Account account)
    Boolean updateAccount(Account account)
    Boolean deleteAccount(Account account)
    List<Account> retrieveAccounts()
    Boolean accountAlreadyExists(Account account)
}