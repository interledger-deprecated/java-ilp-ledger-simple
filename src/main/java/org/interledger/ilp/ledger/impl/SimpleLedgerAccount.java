package org.interledger.ilp.ledger.impl;


import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;

import org.interledger.ilp.core.InterledgerAddress;
import org.interledger.ilp.ledger.MoneyUtils;
import org.interledger.ilp.ledger.account.LedgerAccount;
import org.javamoney.moneta.Money;

/**
 * Represents a simple ledger account.
 *
 * @author mrmx
 */
public class SimpleLedgerAccount implements LedgerAccount {

    private final InterledgerAddress ilpAddress; 
    private final CurrencyUnit currencyUnit;
    private MonetaryAmount balance;

    public SimpleLedgerAccount(String ilpAddress, CurrencyUnit currencyUnit) {
    	// TODO:(0.5) FIXME: A single account can be associated to different ilpAddresses.
    	//     At this moment ilpAddress is used as account.name
        this.ilpAddress = new InterledgerAddress(ilpAddress); // <-- This is mostly wrong in practice
        this.currencyUnit = currencyUnit;
    }




    @Override
    public String getName() {
        return ilpAddress.toString();
    }
    

	@Override
	public InterledgerAddress getInterledgerAddress() {
		throw new RuntimeException(); // TODO:(0) FIXME: implement
	}

    @Override
    public CurrencyUnit getCurrencyCode() {
        return this.currencyUnit;
    }

    @Override
    public SimpleLedgerAccount setBalance(Number balance) {
        return setBalance(Money.of(balance, currencyUnit));
    }

    @Override
    public SimpleLedgerAccount setBalance(MonetaryAmount balance) {
        this.balance = balance;
        return this;
    }

    @Override
    public MonetaryAmount getBalance() {
        if (balance == null) {
            balance = Money.of(0, currencyUnit);
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
    
    @Override
    public SimpleLedgerAccount credit(Number amount) {
        return credit(Money.of(amount, currencyUnit));
    }

    @Override
    public SimpleLedgerAccount credit(MonetaryAmount amount) {
        setBalance(getBalance().add(amount));
        return this;
    }
    
    @Override
    public SimpleLedgerAccount debit(Number amount) {
        return debit(Money.of(amount, currencyUnit));
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
        return ilpAddress.equals(((SimpleLedgerAccount)obj).getName());
    }
    
    @Override
    public String toString() {
        return "Account["
                + "ilpAddress:" + ilpAddress
                + " balance:" + balance
                + "]";
    }

    protected MonetaryAmount toMonetaryAmount(MonetaryAmount amount) {
        return MoneyUtils.toMonetaryAmount(amount, currencyUnit);
    }

}
