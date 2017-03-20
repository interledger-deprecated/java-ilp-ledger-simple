package org.interledger.ilp.ledger.impl;

/**
 * Parses addresses into account and ledger
 * 
 * @author mrmx
 */

// TODO:(0) FIXME. Remove or move to core. 
interface LedgerAddressParser {
   void parse(String address) throws LedgerAddressParserException;
   String getAccountName();
   String getLedgerName();
}
