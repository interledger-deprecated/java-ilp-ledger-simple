package org.interledger.ilp.ledger.impl;

import org.interledger.ilp.ledger.account.LedgerAccount;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mrmx
 */
public class SimpleLedgerAccountManagerTest {
    
    SimpleLedgerAccountManager instance;
    
    @Before
    public void setUp() {
        instance = new SimpleLedgerAccountManager();
    }
    
    /**
     * Test of create method, of class SimpleLedgerAccountManager.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        String name = "alice";
        String currencyCode = "EUR";                
        LedgerAccount result = instance.create(name, currencyCode);
        assertNotNull(result);
        assertEquals(name,result.getName());
    }

    /**
     * Test of addAccounts method, of class SimpleLedgerAccountManager.
     */
    @Test
    public void testAddAccounts() {
        System.out.println("addAccounts");
        assertEquals(0,instance.getTotalAccounts());
        instance.addAccounts(
                new SimpleLedgerAccount("alice", "EUR"),
                new SimpleLedgerAccount("bob", "EUR")
        );
        assertEquals(2,instance.getTotalAccounts());
    }

    /**
     * Test of addAccount method, of class SimpleLedgerAccountManager.
     */
    @Test
    public void testAddAccount() {
        System.out.println("addAccount");
        System.out.println("addAccount");
        assertEquals(0,instance.getTotalAccounts());
        instance.addAccount(new SimpleLedgerAccount("alice", "EUR"));
        assertEquals(1,instance.getTotalAccounts());
    }

    /**
     * Test of getAccountByName method, of class SimpleLedgerAccountManager.
     */
    @Test
    public void testGetAccountByName() {
        System.out.println("getAccountByName");
        SimpleLedgerAccount bob = new SimpleLedgerAccount("bob", "EUR");
        instance.addAccounts(new SimpleLedgerAccount("alice", "EUR"),bob);
        assertEquals(2,instance.getTotalAccounts());                
        LedgerAccount result = instance.getAccountByName("bob");
        assertEquals(bob, result);
    }
    
}
