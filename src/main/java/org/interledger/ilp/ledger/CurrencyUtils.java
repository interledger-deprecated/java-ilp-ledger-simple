package org.interledger.ilp.ledger;

import java.util.Currency;
import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.MonetaryAmountFactory;

/**
 *
 * @author Manuel Polo <mistermx@gmail.com>
 */
public class CurrencyUtils {
    
    public static String getSymbol(String currencyCode) {
        return toCurrency(currencyCode).getSymbol();
    }
    
    public static Currency toCurrency(String currencyCode) {
        return Currency.getInstance(currencyCode);
    }

    public static int getPrecision(CurrencyUnit currency) {
        MonetaryAmountFactory<?> amountFactory = Monetary.getDefaultAmountFactory();
        MonetaryAmount amount = amountFactory.setCurrency(currency).create();
        return amount.getNumber().getPrecision();        
    }
    
}
