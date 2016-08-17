package org.interledger.ilp.ledger.simple;

import org.interledger.cryptoconditions.Fullfilment;
import org.interledger.ilp.core.Ledger;
import org.interledger.ilp.core.LedgerInfo;
import org.interledger.ilp.core.LedgerTransfer;
import org.interledger.ilp.core.LedgerTransferRejectedReason;
import org.interledger.ilp.core.events.LedgerEventHandler;
import org.interledger.ilp.ledger.Currencies;
import org.interledger.ilp.ledger.LedgerInfoFactory;

/**
 * Simple in-memory ledger implementation
 * 
 * @author Manuel Polo <mistermx@gmail.com>
 */
public class SimpleLedger implements Ledger{
    private LedgerInfo info;

    public SimpleLedger(Currencies currency) {
        this(LedgerInfoFactory.from(currency));
    }
    
    public SimpleLedger(String currencyCode) {
        this(LedgerInfoFactory.from(currencyCode));
    }

    public SimpleLedger(LedgerInfo info) {
        this.info = info;
    }

    public LedgerInfo getInfo() {
        return info;
    }    

    public void send(LedgerTransfer transfer) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public void rejectTransfer(LedgerTransfer transfer, LedgerTransferRejectedReason reason) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public void fulfillCondition(Fullfilment fulfillment) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public void registerEventHandler(LedgerEventHandler<?> handler) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }    
}