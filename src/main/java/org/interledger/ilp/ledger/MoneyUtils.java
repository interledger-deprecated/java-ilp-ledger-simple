package org.interledger.ilp.ledger;

import java.math.BigDecimal;
import javax.money.MonetaryAmount;
import org.javamoney.moneta.Money;

/**
 *
 * @author Manuel Polo <mistermx@gmail.com>
 */
public class MoneyUtils {

    private MoneyUtils() {
    }
    
    public static MonetaryAmount toMonetaryAmount(String amount,String currencyCode) {
        return Money.of(new BigDecimal(amount),currencyCode);
    }
     
}
