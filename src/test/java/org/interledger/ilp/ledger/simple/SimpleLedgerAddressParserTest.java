package org.interledger.ilp.ledger.simple;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * SimpleLedgerAddressParser tests
 *
 * @author Manuel Polo <mistermx@gmail.com>
 */
public class SimpleLedgerAddressParserTest {

    SimpleLedgerAddressParser instance;

    @Before
    public void setUp() {
        instance = new SimpleLedgerAddressParser();
    }

    /**
     * Test of parse method, of class SimpleLedgerAddressParser.
     */
    @Test
    public void testParse() {
        System.out.println("parse");
        String address = "alice@ledger";
        instance.parse(address);
        assertEquals("Account", "alice", instance.getAccountName());
        assertEquals("Ledger", "ledger", instance.getLedgerName());
    }

    /**
     * Test of parse method, of class SimpleLedgerAddressParser.
     */
    @Test(expected = LedgerAddressParserException.class)
    public void testParseMalformedAccount() {
        System.out.println("parse malformed account");
        String address = "@ledger";
        instance.parse(address);
        fail("Expected exception!");
    }

    /**
     * Test of parse method, of class SimpleLedgerAddressParser.
     */
    @Test(expected = LedgerAddressParserException.class)
    public void testParseMalformedLedger() {
        System.out.println("parse malformed ledger");
        String address = "account@";
        instance.parse(address);
        fail("Expected exception!");
    }    
    
    /**
     * Test of parse method, of class SimpleLedgerAddressParser.
     */
    @Test(expected = LedgerAddressParserException.class)
    public void testParseMalformedAddress() {
        System.out.println("parse malformed address");
        String address = "account.ledger";
        instance.parse(address);
        fail("Expected exception!");
    }        
}
