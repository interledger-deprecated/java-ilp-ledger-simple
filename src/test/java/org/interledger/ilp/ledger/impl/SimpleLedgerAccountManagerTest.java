package org.interledger.ilp.ledger.impl;

import java.util.Collection;
import org.interledger.ilp.ledger.Currencies;
import org.interledger.ilp.ledger.LedgerInfoFactory;
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
        instance = new SimpleLedgerAccountManager(LedgerInfoFactory.from(Currencies.EURO));
    }
    
    /**
     * Test of create method, of class SimpleLedgerAccountManager.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        String name = "alice";        
        LedgerAccount result = instance.create(name);
        assertNotNull(result);
        assertEquals(name,result.getName());
        assertEquals("EUR",result.getCurrencyCode());
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
        LedgerAccount bob = instance.create("bob");
        instance.addAccount(bob);
        instance.addAccount(instance.create("alice"));
        assertEquals(2,instance.getTotalAccounts());                
        LedgerAccount result = instance.getAccountByName("bob");
        assertEquals(bob, result);
    }
    
    /**
     * Test of getAccounts method, of class SimpleLedgerAccountManager.
     */
    @Test
    public void testGetAccounts() {
        System.out.println("testGetAccounts");
        LedgerAccount bob = instance.create("bob");
        instance.addAccount(bob);
        instance.addAccount(instance.create("alice"));
        assertEquals(2,instance.getTotalAccounts());                
        Collection<LedgerAccount> result = instance.getAccounts(1, 1);
        assertEquals(2, result.size());
    }
    
}
