package org.interledger.ilp.ledger.account;

import java.util.Collection;
import org.interledger.ilp.core.LedgerInfo;

/**
 * Defines account management.
 *
 * @author mrmx
 */
public interface LedgerAccountManager {

    LedgerInfo getLedgerInfo();

    LedgerAccount create(String name);

    int getTotalAccounts();

    void addAccount(LedgerAccount account);

    LedgerAccount getAccountByName(String name) throws AccountNotFoundException;

    Collection<LedgerAccount> getAccounts(int page, int pageSize);
}
