package org.interledger.ilp.ledger.account;

import org.interledger.ilp.core.exceptions.InterledgerException;

/**
 *
 * @author mrmx
 */
public class AccountNotFoundException extends InterledgerException {

    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of {@code AccountNotFoundException} without
     * detail message.
     */
    public AccountNotFoundException() {
    }

    /**
     * Constructs an instance of {@code AccountNotFoundException} with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public AccountNotFoundException(String msg) {
        super(msg);
    }
}
