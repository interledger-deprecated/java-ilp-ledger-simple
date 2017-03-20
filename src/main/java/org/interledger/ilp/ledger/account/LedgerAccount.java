package org.interledger.ilp.ledger.account;

import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;

import org.interledger.ilp.core.InterledgerAddress;

/**
 * This interface defines a ledger account.
 *
 * @author mrmx
 */

// TODO:(0) Recheck. Reuse interfaces in java-ilp-core
public interface LedgerAccount {

    String getName(); // TODO:(0) FIXME: Remove and replace references with getILPAddress ?
    
    InterledgerAddress getInterledgerAddress();

    CurrencyUnit getCurrencyCode(); // TODO:(0) REFACTOR: Rename as getCurrencyUnit

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
