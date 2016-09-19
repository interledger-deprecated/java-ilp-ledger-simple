package org.interledger.ilp.ledger;

import java.util.Currency;
import javax.money.CurrencyUnit;
import javax.money.Monetary;

/**
 * Common used currencies
 *
 * @author mrmx
 */
public enum Currencies {
    EURO("EUR"),
    US_DOLLAR("USD"),
    POUND("GBP"),
    YEN("JPY"),
    YUAN("CNY");

    private String code;

    private Currencies(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }

    public String getCode() {
        return code;
    }

    public Currency toCurrency() {
        return Currency.getInstance(code);
    }

    public CurrencyUnit toCurrencyUnit() {
        return Monetary.getCurrency(code);
    }
}
