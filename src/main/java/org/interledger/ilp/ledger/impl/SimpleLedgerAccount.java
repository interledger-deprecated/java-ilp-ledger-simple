package org.interledger.ilp.ledger.impl;

import java.util.Currency;
import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;
import org.apache.commons.lang.StringUtils;
import org.interledger.ilp.ledger.MoneyUtils;
import org.interledger.ilp.ledger.account.LedgerAccount;
import org.javamoney.moneta.Money;

/**
 * Represents a simple ledger account.
 *
 * @author mrmx
 */
public class SimpleLedgerAccount implements LedgerAccount {

    private final String name;
    private final String currencyCode;
    private MonetaryAmount balance;

    public SimpleLedgerAccount(String name, Currency currency) {
        this(name, currency.getCurrencyCode());
    }

    public SimpleLedgerAccount(String name, CurrencyUnit currencyUnit) {
        this(name, currencyUnit.getCurrencyCode());
    }

    public SimpleLedgerAccount(String name, String currencyCode) {
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("empty account name");
        }
        this.name = name;
        this.currencyCode = currencyCode;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getCurrencyCode() {
        return currencyCode;
    }

    @Override
    public SimpleLedgerAccount setBalance(Number balance) {
        return setBalance(Money.of(balance, currencyCode));
    }

    @Override
    public SimpleLedgerAccount setBalance(MonetaryAmount balance) {
        this.balance = balance;
        return this;
    }

    @Override
    public MonetaryAmount getBalance() {
        if (balance == null) {
            balance = Money.of(0, currencyCode);
        }
        return balance;
    }
    
    @Override
    public String getBalanceAsString() {
        return getBalanceAsNumber().toString();
    }

    @Override
    public Number getBalanceAsNumber() {
        return getBalance().getNumber();
    }
    
    public SimpleLedgerAccount credit(String amount) {
        return credit(toMonetaryAmount(amount));
    }

    @Override
    public SimpleLedgerAccount credit(Number amount) {
        return credit(Money.of(amount, currencyCode));
    }

    @Override
    public SimpleLedgerAccount credit(MonetaryAmount amount) {
        setBalance(getBalance().add(amount));
        return this;
    }

    public SimpleLedgerAccount debit(String amount) {
        return debit(toMonetaryAmount(amount));
    }
    
    @Override
    public SimpleLedgerAccount debit(Number amount) {
        return debit(Money.of(amount, currencyCode));
    }

    @Override
    public SimpleLedgerAccount debit(MonetaryAmount amount) {
        setBalance(getBalance().subtract(amount));
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof SimpleLedgerAccount)) {
            return false;
        }
        if(obj == this) {
            return true;
        }
        return name.equalsIgnoreCase(((SimpleLedgerAccount)obj).getName());
    }
    
    @Override
    public String toString() {
        return "Account["
                + "name:" + name
                + " balance:" + balance
                + "]";
    }

    protected MonetaryAmount toMonetaryAmount(String amount) {
        return MoneyUtils.toMonetaryAmount(amount, currencyCode);
    }

}
