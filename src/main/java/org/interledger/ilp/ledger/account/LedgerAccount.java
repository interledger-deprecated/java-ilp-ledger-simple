package org.interledger.ilp.ledger.account;

import javax.money.MonetaryAmount;

/**
 * This interface defines a ledger account.
 *
 * @author mrmx
 */
public interface LedgerAccount {

    String getName();

    String getCurrencyCode();

    LedgerAccount setBalance(Number balance);

    LedgerAccount setBalance(MonetaryAmount balance);

    MonetaryAmount getBalance();

    String getBalanceAsString();

    Number getBalanceAsNumber();

    LedgerAccount credit(Number amount);

    LedgerAccount credit(MonetaryAmount amount);

    LedgerAccount debit(Number amount);

    LedgerAccount debit(MonetaryAmount amount);
}
