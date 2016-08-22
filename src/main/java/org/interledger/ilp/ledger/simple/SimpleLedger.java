package org.interledger.ilp.ledger.simple;

import java.util.HashMap;
import java.util.Map;
import javax.money.MonetaryAmount;
import org.interledger.cryptoconditions.Fullfilment;
import org.interledger.ilp.core.Ledger;
import org.interledger.ilp.core.LedgerInfo;
import org.interledger.ilp.core.LedgerTransfer;
import org.interledger.ilp.core.LedgerTransferRejectedReason;
import org.interledger.ilp.core.events.LedgerEventHandler;
import org.interledger.ilp.core.exceptions.InsufficientAmountException;
import org.interledger.ilp.ledger.Currencies;
import org.interledger.ilp.ledger.LedgerInfoFactory;
import org.interledger.ilp.ledger.MoneyUtils;

/**
 * Simple in-memory ledger implementation
 *
 * @author Manuel Polo <mistermx@gmail.com>
 */
public class SimpleLedger implements Ledger {

    private LedgerInfo info;
    private String name;
    private Map<String, Account> accountMap;

    public SimpleLedger(Currencies currency,String name) {
        this(LedgerInfoFactory.from(currency),name);
    }

    public SimpleLedger(String currencyCode,String name) {
        this(LedgerInfoFactory.from(currencyCode),name);
    }

    public SimpleLedger(LedgerInfo info,String name) {
        this.info = info;
        this.name = name;
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

    public String getName() {
        return name;
    }
    
    public void send(LedgerTransfer transfer) {        
        Account from = getAcccount(transfer.getFromAccount());
        if (from == null) {
            throw new AccountNotFoundException(transfer.getFromAccount());
        }
        Account to = getAcccount(transfer.getToAccount());
        if (to == null) {
            throw new AccountNotFoundException(transfer.getToAccount());
        }
        if(to.equals(from)) {
            throw new RuntimeException("accounts are the same");
        }
        MonetaryAmount amount = MoneyUtils.toMonetaryAmount(transfer.getAmount(),info.getCurrencyCode());        
        if(from.getBalance().isGreaterThanOrEqualTo(amount)) {
            from.debit(amount);
            to.credit(amount);
        } else {
            throw new InsufficientAmountException(amount.toString());
        }
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
