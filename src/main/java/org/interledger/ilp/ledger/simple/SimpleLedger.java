package org.interledger.ilp.ledger.simple;

import java.util.HashMap;
import java.util.Map;
import org.interledger.cryptoconditions.Fullfilment;
import org.interledger.ilp.core.Ledger;
import org.interledger.ilp.core.LedgerInfo;
import org.interledger.ilp.core.LedgerTransfer;
import org.interledger.ilp.core.LedgerTransferRejectedReason;
import org.interledger.ilp.core.events.LedgerEventHandler;
import org.interledger.ilp.ledger.Currencies;
import org.interledger.ilp.ledger.LedgerInfoFactory;

/**
 * Simple in-memory ledger implementation
 *
 * @author Manuel Polo <mistermx@gmail.com>
 */
public class SimpleLedger implements Ledger {

    private LedgerInfo info;

    private Map<String, Account> accountMap;

    public SimpleLedger(Currencies currency) {
        this(LedgerInfoFactory.from(currency));
    }

    public SimpleLedger(String currencyCode) {
        this(LedgerInfoFactory.from(currencyCode));
    }

    public SimpleLedger(LedgerInfo info) {
        this.info = info;
        accountMap = new HashMap<String, Account>();
    }

    public void addAccounts(Account... accounts) {
        for (Account account : accounts) {
            accountMap.put(account.getName(), account);
        }
    }

    public void addAccount(Account account) {
        accountMap.put(account.getName(), account);
    }

    public Account getAcccount(String name) {
        return accountMap.get(name);
    }

    public LedgerInfo getInfo() {
        return info;
    }

    public void send(LedgerTransfer transfer) {
        System.out.println("sending " + transfer);
        Account from = getAcccount(transfer.getFromAccount());
        if (from == null) {
            throw new AccountNotFoundException(transfer.getFromAccount());
        }
        //WIP
    }

    public void rejectTransfer(LedgerTransfer transfer, LedgerTransferRejectedReason reason) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void fulfillCondition(Fullfilment fulfillment) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void registerEventHandler(LedgerEventHandler<?> handler) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
