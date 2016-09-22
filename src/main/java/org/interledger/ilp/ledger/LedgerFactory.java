package org.interledger.ilp.ledger;

import org.interledger.ilp.core.Ledger;
import org.interledger.ilp.core.LedgerInfo;
import org.interledger.ilp.ledger.impl.SimpleLedger;

/**
 * Ledger factory.
 * 
 * @author mrmx
 */
public class LedgerFactory {
    private static final LedgerFactory instance = new LedgerFactory();

    public static LedgerFactory getInstance() {
        return instance;
    }
        
    public Ledger create(LedgerInfo ledgerInfo,String ledgerName) {
        return new SimpleLedger(ledgerInfo,ledgerName);
    }
}
