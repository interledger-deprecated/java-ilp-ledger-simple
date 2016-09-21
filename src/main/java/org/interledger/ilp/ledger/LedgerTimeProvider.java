package org.interledger.ilp.ledger;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Encapsulates time adquisition.
 * 
 * @author mrmx
 */
public class LedgerTimeProvider {
    private static final LedgerTimeProvider instance = new LedgerTimeProvider();
    private Calendar calendar;

    protected LedgerTimeProvider() {
        calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
    }    

    public static LedgerTimeProvider getInstance() {
        return instance;
    }
        
    public Date getTime() {
        return calendar.getTime();
    }    
}
