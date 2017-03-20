package org.interledger.ilp.ledger.impl;


import org.interledger.ilp.core.ledger.model.LedgerInfo;
import org.interledger.ilp.ledger.Currencies;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Simple ledger tests
 *
 * @author mrmx
 */
public class SimpleLedgerTest {

    Currencies CURRENCY = Currencies.EURO;
    SimpleLedger instance;

    @Before
    public void setUp() {
        instance = new SimpleLedger(CURRENCY, "test");
    }

    /**
     * Test of getInfo method, of class SimpleLedger.
     */
    @Test
    public void testGetInfo() {
        System.out.println("getInfo");
        LedgerInfo result = instance.getInfo();
        assertNotNull(result);
    }

}
