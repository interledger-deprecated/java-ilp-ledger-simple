package org.interledger.ilp.ledger.simple;

import org.interledger.cryptoconditions.Fulfillment;
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
        instance = new SimpleLedger(CURRENCY, "test");
    }

    /**
     * Test of addAccount method, of class SimpleLedger.
     */
    @Test
    public void testAddAccount() {
        System.out.println("addAccount");
        instance.addAccount(new Account("test", CURRENCY.code()));
    }

    /**
     * Test of getAcccount method, of class SimpleLedger.
     */
    @Test
    public void testGetAcccount() {
        System.out.println("getAcccount");
        String name = "test";
        instance.addAccount(new Account("test", CURRENCY.code()));
        Account result = instance.getAcccount(name);
        assertNotNull(result);
        assertEquals(name, result.getName());
        assertTrue(result.getBalance().isZero());
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
        Account alice = new Account("alice", CURRENCY.code()).setBalance(100);
        Account bob = new Account("bob", CURRENCY.code()).setBalance(100);
        instance.addAccounts(alice, bob);
        LedgerTransfer transfer = LedgerTransferBuilder.instance()
                .from(alice)
                .destination("bob@test")
                .amount(Money.of(10, CURRENCY.code()))
                .build();
        instance.send(transfer);
        assertEquals(90, instance.getAcccount("alice").getBalanceAsNumber().intValue());
        assertEquals(110, instance.getAcccount("bob").getBalanceAsNumber().intValue());
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
        Fulfillment fulfillment = null;
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
