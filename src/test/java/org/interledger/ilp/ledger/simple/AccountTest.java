package org.interledger.ilp.ledger.simple;

import javax.money.MonetaryAmount;
import org.javamoney.moneta.Money;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Account tests
 *
 * @author Manuel Polo <mistermx@gmail.com>
 */
public class AccountTest {

    static final String CURRENCY_CODE = "EUR";
    Account instance;

    @Before
    public void setUp() {
        instance = new Account("test", CURRENCY_CODE);
    }

    /**
     * Test of getName method, of class Account.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String expResult = "test";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setBalance method, of class Account.
     */
    @Test
    public void testSetBalance_Number() {
        System.out.println("setBalance");
        Number balance = 123f;
        MonetaryAmount expResult = Money.of(balance, CURRENCY_CODE);
        MonetaryAmount result = instance.setBalance(balance).getBalance();
        assertEquals(expResult, result);
    }

    /**
     * Test of setBalance method, of class Account.
     */
    @Test
    public void testSetBalance_MonetaryAmount() {
        System.out.println("setBalance");
        MonetaryAmount balance = Money.of(1, CURRENCY_CODE);
        MonetaryAmount expResult = Money.of(1, CURRENCY_CODE);
        MonetaryAmount result = instance.setBalance(balance).getBalance();
        assertEquals(expResult, result);
    }

    /**
     * Test of getBalance method, of class Account.
     */
    @Test
    public void testGetBalance() {
        System.out.println("getBalance");
        MonetaryAmount expResult = Money.of(0, CURRENCY_CODE);
        MonetaryAmount result = instance.getBalance();
        assertEquals(expResult, result);
    }

    /**
     * Test of credit method, of class Account.
     */
    @Test
    public void testCredit_String() {
        System.out.println("credit string");
        String amount = "1234567890123";
        MonetaryAmount expResult = Money.of(Double.parseDouble(amount), CURRENCY_CODE);
        MonetaryAmount result = instance.credit(amount).getBalance();
        assertEquals(expResult, result);
    }

    /**
     * Test of credit method, of class Account.
     */
    @Test
    public void testCredit_Number() {
        System.out.println("credit");
        Number amount = 123;
        MonetaryAmount expResult = Money.of(amount, CURRENCY_CODE);
        MonetaryAmount result = instance.credit(amount).getBalance();
        assertEquals(expResult, result);
    }

    /**
     * Test of credit method, of class Account.
     */
    @Test
    public void testCredit_MonetaryAmount() {
        System.out.println("credit");
        MonetaryAmount amount = Money.of(123, CURRENCY_CODE);
        MonetaryAmount expResult = Money.of(123, CURRENCY_CODE);
        MonetaryAmount result = instance.credit(amount).getBalance();
        assertEquals(expResult, result);
    }

    /**
     * Test of debit method, of class Account.
     */
    @Test
    public void testDebit_String() {
        System.out.println("debit String");
        instance.setBalance(100);
        String amount = "50";
        MonetaryAmount expResult = Money.of(Double.valueOf(amount), CURRENCY_CODE);
        MonetaryAmount result = instance.debit(amount).getBalance();
        assertEquals(expResult, result);
    }

    /**
     * Test of debit method, of class Account.
     */
    @Test
    public void testDebit_Number() {
        System.out.println("debit");
        instance.setBalance(100);
        Number amount = 50;
        MonetaryAmount expResult = Money.of(amount, CURRENCY_CODE);
        MonetaryAmount result = instance.debit(amount).getBalance();
        assertEquals(expResult, result);
    }

    /**
     * Test of debit method, of class Account.
     */
    @Test
    public void testDebit_MonetaryAmount() {
        System.out.println("debit");
        instance.setBalance(100);
        MonetaryAmount amount = Money.of(25, CURRENCY_CODE);
        MonetaryAmount expResult = Money.of(75, CURRENCY_CODE);
        MonetaryAmount result = instance.debit(amount).getBalance();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Account.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Account other = new Account("other", CURRENCY_CODE);
        assertNotEquals(instance, other);
        assertNotEquals(instance, null);
        assertEquals(instance, instance);
    }

}
