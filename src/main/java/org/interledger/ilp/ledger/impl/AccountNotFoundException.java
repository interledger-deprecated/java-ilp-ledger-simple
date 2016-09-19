package org.interledger.ilp.ledger.impl;

import org.interledger.ilp.core.exceptions.InterledgerException;

/**
 *
 * @author mrmx
 */
public class AccountNotFoundException extends InterledgerException {

    /**
     * Creates a new instance of <code>AccountNotFoundException</code> without
     * detail message.
     */
    public AccountNotFoundException() {
    }

    /**
     * Constructs an instance of <code>AccountNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public AccountNotFoundException(String msg) {
        super(msg);
    }
}
