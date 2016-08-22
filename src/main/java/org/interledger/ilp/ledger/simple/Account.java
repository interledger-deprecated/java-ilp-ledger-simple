package org.interledger.ilp.ledger.simple;

import java.math.BigDecimal;
import java.util.Currency;
import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;
import org.interledger.ilp.ledger.MoneyUtils;
import org.javamoney.moneta.Money;

/**
 * Represents a ledger account
 *
 * @author Manuel Polo <mistermx@gmail.com>
 */
public class Account {

    private final String name;
    private final String currencyCode;
    private MonetaryAmount balance;

    public Account(String name, Currency currency) {
        this(name, currency.getCurrencyCode());
    }

    public Account(String name, CurrencyUnit currencyUnit) {
        this(name, currencyUnit.getCurrencyCode());
    }

    public Account(String name, String currencyCode) {
        if (name == null || name.trim().length() == 0) {
            throw new IllegalArgumentException("empty account name");
        }
        this.name = name;
        this.currencyCode = currencyCode;
    }

    public String getName() {
        return name;
    }

    public Account setBalance(Number balance) {
        return setBalance(Money.of(balance, currencyCode));
    }

    public Account setBalance(MonetaryAmount balance) {
        this.balance = balance;
        return this;
    }

    public MonetaryAmount getBalance() {
        if (balance == null) {
            balance = Money.of(0, currencyCode);
        }
        return balance;
    }
    
    public Account credit(String amount) {
        return credit(toMonetaryAmount(amount));
    }

    public Account credit(Number amount) {
        return credit(Money.of(amount, currencyCode));
    }

    public Account credit(MonetaryAmount amount) {
        setBalance(getBalance().add(amount));
        return this;
    }

    public Account debit(String amount) {
        return debit(toMonetaryAmount(amount));
    }
    
    public Account debit(Number amount) {
        return debit(Money.of(amount, currencyCode));
    }

    public Account debit(MonetaryAmount amount) {
        setBalance(getBalance().subtract(amount));
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof Account)) {
            return false;
        }
        if(obj == this) {
            return true;
        }
        return name.equalsIgnoreCase(((Account)obj).getName());
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
