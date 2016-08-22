package org.interledger.ilp.ledger.simple;

import org.interledger.ilp.core.exceptions.InterledgerException;

/**
 *
 * @author Manuel Polo <mistermx@gmail.com>
 */
public class LedgerAddressParserException extends InterledgerException {

    /**
     * Constructs an instance of <code>LedgerAddressParserException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public LedgerAddressParserException(String msg) {
        super(msg);
    }

    /**
     * Constructs an instance of <code>LedgerAddressParserException</code> with
     * the specified detail message and <code>Throwable</code> cause.
     *
     * @param msg the detail message.
     * @param cause the <code>Throwable</code> cause.
     */
    public LedgerAddressParserException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
