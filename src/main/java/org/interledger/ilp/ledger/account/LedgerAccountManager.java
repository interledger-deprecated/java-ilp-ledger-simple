package org.interledger.ilp.ledger.account;

import java.util.Collection;

import org.interledger.ilp.core.InterledgerAddress;
import org.interledger.ilp.core.ledger.model.LedgerInfo;

/**
 * Defines account management.
 *
 * @author mrmx
 */
public interface LedgerAccountManager {

    LedgerAccount create(String name);

    int getTotalAccounts();

    void addAccount(LedgerAccount account);

    LedgerAccount getAccountByILPAddress(InterledgerAddress address) throws AccountNotFoundException;

    Collection<LedgerAccount> getAccounts(int page, int pageSize);
    
    
}
