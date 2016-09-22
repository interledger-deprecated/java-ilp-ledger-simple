package org.interledger.ilp.ledger.account;

/**
 * Defines account management.
 * 
 * @author mrmx
 */
public interface LedgerAccountManager {
    LedgerAccount create(String name, String currencyCode);
    int getTotalAccounts();
    void addAccount(LedgerAccount account);
    LedgerAccount getAccountByName(String name) throws AccountNotFoundException;
}
