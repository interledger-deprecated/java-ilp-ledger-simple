package org.interledger.ilp.ledger.api;

import java.util.Date;

public class DTTMClock {

    /*
     * Useful class to isolate the Date/Time used in the ledger (and ledger ddbb)
     * from the one in the running server.
     * For now it just limits to return the server time.
     */
    public static Date getCurrentTime() {
        return new Date();
    }
}
