package org.interledger.ilp.ledger.account;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("balance")
    String getBalanceAsString();

    Number getBalanceAsNumber();

    LedgerAccount credit(Number amount);

    LedgerAccount credit(MonetaryAmount amount);

    LedgerAccount debit(Number amount);

    LedgerAccount debit(MonetaryAmount amount);
}
