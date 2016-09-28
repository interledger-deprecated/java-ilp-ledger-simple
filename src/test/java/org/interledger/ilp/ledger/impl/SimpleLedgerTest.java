package org.interledger.ilp.ledger.impl;

import org.interledger.cryptoconditions.Fulfillment;
import org.interledger.ilp.core.LedgerInfo;
import org.interledger.ilp.core.LedgerTransfer;
import org.interledger.ilp.core.LedgerTransferRejectedReason;
import org.interledger.ilp.core.events.LedgerEventHandler;
import org.interledger.ilp.ledger.Currencies;
import org.interledger.ilp.ledger.account.LedgerAccount;
import org.javamoney.moneta.Money;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;
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

    /**
     * Test of send method, of class SimpleLedger.
     */
    @Test
    public void testSend() {
        System.out.println("send");
        LedgerAccount alice = new SimpleLedgerAccount("alice", CURRENCY.code()).setBalance(100);
        LedgerAccount bob = new SimpleLedgerAccount("bob", CURRENCY.code()).setBalance(100);
        instance.getLedgerAccountManager().addAccount(alice);
        instance.getLedgerAccountManager().addAccount(bob);
        LedgerTransfer transfer = LedgerTransferBuilder.instance()
                .from(alice)
                .destination("bob@test")
                .amount(Money.of(10, CURRENCY.code()))
                .build();
        instance.send(transfer);
        assertEquals(90, instance.getLedgerAccountManager().getAccountByName("alice").getBalanceAsNumber().intValue());
        assertEquals(110, instance.getLedgerAccountManager().getAccountByName("bob").getBalanceAsNumber().intValue());
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
        LedgerEventHandler<?> handler = null;
        instance.registerEventHandler(handler);
        fail("The test case is a prototype.");
    }

}
