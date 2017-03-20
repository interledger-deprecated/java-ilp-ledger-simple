package org.interledger.ilp.ledger;

import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;

import org.javamoney.moneta.Money;

/**
 *
 * @author mrmx
 */
public class MoneyUtils {

    private MoneyUtils() {
    }
    
    public static MonetaryAmount toMonetaryAmount(MonetaryAmount amount, CurrencyUnit currencyCode) {
        return Money.of(amount.getNumber(),currencyCode);
    }
     
}
