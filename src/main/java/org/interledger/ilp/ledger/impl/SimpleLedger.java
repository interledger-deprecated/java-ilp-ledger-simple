package org.interledger.ilp.ledger.impl;

import javax.money.MonetaryAmount;
import org.interledger.ilp.core.ledger.model.LedgerInfo;
import org.interledger.ilp.core.ledger.model.LedgerTransfer;

import org.interledger.ilp.core.exceptions.InsufficientAmountException;
import org.interledger.ilp.ledger.Currencies;
import org.interledger.ilp.ledger.LedgerAccountManagerFactory;
import org.interledger.ilp.ledger.LedgerInfoFactory;
import org.interledger.ilp.ledger.MoneyUtils;
import org.interledger.ilp.ledger.account.LedgerAccount;
import org.interledger.ilp.ledger.account.LedgerAccountManager;
/**
 * Simple in-memory ledger implementation
 *
 * @author earizon
 */
public class SimpleLedger /* implements Ledger, TODO:(0) Still pertinent???. What it really implements now??? */ {

    private LedgerInfo info;
    private String name;
      private LedgerAccountManager accountManager;
   
    public SimpleLedger(Currencies currency, String name) {
        this(org.interledger.ilp.ledger.LedgerInfoFactory.from(currency), name);
    }

    public SimpleLedger(String currencyCode, String name) {
        this(LedgerInfoFactory.from(currencyCode), name);
    }

    public SimpleLedger(LedgerInfo info, String name) {
        this.info = info;
        this.name = name;
    }

    public LedgerAccountManager getLedgerAccountManager() {
        return accountManager;
    }

    public LedgerInfo getInfo() {
        return info;
    }

    public String getName() {
        return name;
    }

    public void send(LedgerTransfer transfer) {
    	LedgerAccountManager accountManager = LedgerAccountManagerFactory.getAccountManagerSingleton();
        LedgerAccount from = accountManager.getAccountByILPAddress(transfer.getFromAccount());
        LedgerAccount to = accountManager.getAccountByILPAddress(transfer.getToAccount());
        if (to.equals(from)) {
            throw new RuntimeException("accounts are the same");
        }
        MonetaryAmount amount = MoneyUtils.toMonetaryAmount(transfer.getAmount(), info.getCurrencyUnit());
        if (from.getBalance().isGreaterThanOrEqualTo(amount)) {
            from.debit(amount);
            to.credit(amount);
        } else {
            throw new InsufficientAmountException(amount.toString());
        }
    }

}
