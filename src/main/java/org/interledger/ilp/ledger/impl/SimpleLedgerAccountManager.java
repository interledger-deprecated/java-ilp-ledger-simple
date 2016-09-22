package org.interledger.ilp.ledger.impl;

import java.util.HashMap;
import java.util.Map;
import org.interledger.ilp.ledger.account.LedgerAccount;
import org.interledger.ilp.ledger.account.LedgerAccountManager;

/**
 * Simple in-memory {@code LedgerAccountManager}.
 *
 * @author mrmx
 */
public class SimpleLedgerAccountManager implements LedgerAccountManager {

    private Map<String, LedgerAccount> accountMap;

    public SimpleLedgerAccountManager() {
        accountMap = new HashMap<String, LedgerAccount>();
    }

    @Override
    public LedgerAccount create(String name, String currencyCode) {
        return new SimpleLedgerAccount(name, currencyCode);
    }

    public void addAccounts(SimpleLedgerAccount... accounts) {
        for (SimpleLedgerAccount account : accounts) {
            addAccount(account);
        }
    }

    @Override
    public void addAccount(LedgerAccount account) {
        accountMap.put(account.getName(), account);
    }

    @Override
    public LedgerAccount getAccountByName(String name) {
        return accountMap.get(name);
    }

    @Override
    public int getTotalAccounts() {
        return accountMap.size();
    }
    
    

}
