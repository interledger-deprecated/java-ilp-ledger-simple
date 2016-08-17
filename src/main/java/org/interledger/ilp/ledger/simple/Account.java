package org.interledger.ilp.ledger.simple;

import javax.money.MonetaryAmount;

/**
 * Represents a ledger account
 * 
 * @author Manuel Polo <mistermx@gmail.com>
 */
public class Account {    
    private final String name;
    private final MonetaryAmount balance;

    public Account(String name, MonetaryAmount balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public MonetaryAmount getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Account[" +
                "name:" + name +
                "balance:" + balance +
                "]"
                ;
    }
    
}