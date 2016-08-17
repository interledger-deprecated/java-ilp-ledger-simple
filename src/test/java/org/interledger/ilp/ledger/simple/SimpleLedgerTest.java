package org.interledger.ilp.ledger.simple;

import org.interledger.cryptoconditions.Fullfilment;
import org.interledger.ilp.core.LedgerInfo;
import org.interledger.ilp.core.LedgerTransfer;
import org.interledger.ilp.core.LedgerTransferRejectedReason;
import org.interledger.ilp.core.events.LedgerEventHandler;
import org.interledger.ilp.ledger.Currencies;
import org.javamoney.moneta.Money;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 * Simple ledger tests
 * 
 * @author Manuel Polo <mistermx@gmail.com>
 */
public class SimpleLedgerTest {
    Currencies CURRENCY = Currencies.EURO;
    SimpleLedger instance;
    
    @Before
    public void setUp() {
        instance = new SimpleLedger(CURRENCY);
    }    

    /**
     * Test of addAccount method, of class SimpleLedger.
     */
    @Test
    public void testAddAccount() {
        System.out.println("addAccount");
        Account account = new Account("test", Money.zero(CURRENCY.toCurrencyUnit()));        
        instance.addAccount(account);
    }

    /**
     * Test of getAcccount method, of class SimpleLedger.
     */
    @Test
    public void testGetAcccount() {
        System.out.println("getAcccount");
        String name = "test";
        instance.addAccount(new Account("test", Money.zero(CURRENCY.toCurrencyUnit())) );                        
        Account result = instance.getAcccount(name);
        assertNotNull(result);
        assertEquals(name, result.getName());
        assertEquals(CURRENCY.code() + " 0", result.getBalance().toString());
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

    /**
     * Test of send method, of class SimpleLedger.
     */
    @Test
    public void testSend() {
        System.out.println("send");
        LedgerTransfer transfer = null;        
        instance.send(transfer);
    }

    /**
     * Test of rejectTransfer method, of class SimpleLedger.
     */
    @Ignore
    @Test
    public void testRejectTransfer() {
        System.out.println("rejectTransfer");
        LedgerTransfer transfer = null;
        LedgerTransferRejectedReason reason = null;
        instance.rejectTransfer(transfer, reason);
        fail("The test case is a prototype.");
    }

    /**
     * Test of fulfillCondition method, of class SimpleLedger.
     */
    @Ignore
    @Test
    public void testFulfillCondition() {
        System.out.println("fulfillCondition");
        Fullfilment fulfillment = null;
        instance.fulfillCondition(fulfillment);        
        fail("The test case is a prototype.");
    }

    /**
     * Test of registerEventHandler method, of class SimpleLedger.
     */
    @Ignore
    @Test
    public void testRegisterEventHandler() {
        System.out.println("registerEventHandler");
        LedgerEventHandler handler = null;        
        instance.registerEventHandler(handler);        
        fail("The test case is a prototype.");
    }
    
}