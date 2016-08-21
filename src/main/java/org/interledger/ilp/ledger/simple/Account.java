package org.interledger.ilp.ledger.simple;

import java.util.Currency;
import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;
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
		return setBalance(Money.of(balance,currencyCode));
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
	
	public Account add(Number amount) {
		return add(Money.of(amount, currencyCode));		
	}
	
	public Account add(MonetaryAmount amount) {
		setBalance(getBalance().add(amount));
		return this;
	}
	
	public Account sub(Number amount) {
		return sub(Money.of(amount, currencyCode));
	}
	
	public Account sub(MonetaryAmount amount) {
		setBalance(getBalance().subtract(amount));
		return this;
	}

	@Override
	public String toString() {
		return "Account["
				+ "name:" + name
				+ " balance:" + balance
				+ "]";
	}

}