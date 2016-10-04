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
    private static SimpleLedger simpleLedgerSingleton;

    public static Ledger initialize(LedgerInfo ledgerInfo,String ledgerName) {
    	if (simpleLedgerSingleton!=null) return simpleLedgerSingleton;
    	simpleLedgerSingleton = new SimpleLedger(ledgerInfo,ledgerName);
    	return simpleLedgerSingleton;
    }
    
    public static Ledger getLedger() {
    	if (simpleLedgerSingleton!=null) {
    		throw new RuntimeException("simpleLedgerSingleton == null. At startup "
    				+ "createLedgerSingleton(LedgerInfo ledgerInfo,String ledgerName) must be called to initialize the factory"); 
    	}
    	return simpleLedgerSingleton;
	}
}
    
    
    

