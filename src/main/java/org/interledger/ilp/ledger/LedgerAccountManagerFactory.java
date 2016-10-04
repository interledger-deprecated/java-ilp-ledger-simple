package org.interledger.ilp.ledger;

import org.interledger.ilp.ledger.account.LedgerAccountManager;
import org.interledger.ilp.ledger.impl.SimpleLedgerAccountManager;

/**
 * Ledger factory.
 * 
 * @author mrmx
 */
public class LedgerAccountManagerFactory {
	private static SimpleLedgerAccountManager singleton;

	public static LedgerAccountManager getAccountManagerSingleton() {
		if (singleton!=null) return singleton;
		singleton = new SimpleLedgerAccountManager();
		return singleton;
	}
}
    
    
    

