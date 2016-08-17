package org.interledger.ilp.ledger;

import java.util.Currency;

/**
 * Common used currencies 
 * 
 * @author Manuel Polo <mistermx@gmail.com>
 */
public enum Currencies {
    EURO("EUR"),
    US_DOLLAR("USD"),
    POUND("GBP"),    
    YEN("JPY"),
    YUAN("CNY")
    ;
    
    private String code;

    private Currencies(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }  
    
    public Currency toCurrency() {
        return Currency.getInstance(code);
    }    
}