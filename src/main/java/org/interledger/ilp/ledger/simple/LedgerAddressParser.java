package org.interledger.ilp.ledger.simple;

/**
 * Parses addresses into account and ledger
 * 
 * @author Manuel Polo <mistermx@gmail.com>
 */
interface LedgerAddressParser {
   void parse(String address) throws LedgerAddressParserException;
   String getAccountName();
   String getLedgerName();
}
