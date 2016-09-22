package org.interledger.ilp.ledger.impl;

import javax.money.MonetaryAmount;
import org.interledger.cryptoconditions.Fulfillment;
import org.interledger.ilp.core.Ledger;
import org.interledger.ilp.core.LedgerInfo;
import org.interledger.ilp.core.LedgerTransfer;
import org.interledger.ilp.core.LedgerTransferRejectedReason;
import org.interledger.ilp.core.events.LedgerEventHandler;
import org.interledger.ilp.core.exceptions.InsufficientAmountException;
import org.interledger.ilp.ledger.Currencies;
import org.interledger.ilp.ledger.LedgerInfoFactory;
import org.interledger.ilp.ledger.MoneyUtils;
import org.interledger.ilp.ledger.account.LedgerAccount;
import org.interledger.ilp.ledger.account.LedgerAccountManager;
import org.interledger.ilp.ledger.account.LedgerAccountManagerAware;

/**
 * Simple in-memory ledger implementation
 *
 * @author mrmx
 */
public class SimpleLedger implements Ledger, LedgerAccountManagerAware {

    private LedgerInfo info;
    private String name;
    private SimpleLedgerAccountManager accountManager;

    public SimpleLedger(Currencies currency, String name) {
        this(LedgerInfoFactory.from(currency), name);
    }

    public SimpleLedger(String currencyCode, String name) {
        this(LedgerInfoFactory.from(currencyCode), name);
    }

    public SimpleLedger(LedgerInfo info, String name) {
        this.info = info;
        this.name = name;
        accountManager = new SimpleLedgerAccountManager();
    }

    @Override
    public LedgerAccountManager getLedgerAccountManager() {
        return accountManager;
    }

    public LedgerInfo getInfo() {
        return info;
    }

    public String getName() {
        return name;
    }

    public void send(LedgerTransfer transfer) {
        LedgerAccount from = accountManager.getAccountByName(transfer.getFromAccount());
        LedgerAccount to = accountManager.getAccountByName(transfer.getToAccount());
        if (to.equals(from)) {
            throw new RuntimeException("accounts are the same");
        }
        MonetaryAmount amount = MoneyUtils.toMonetaryAmount(transfer.getAmount(), info.getCurrencyCode());
        if (from.getBalance().isGreaterThanOrEqualTo(amount)) {
            from.debit(amount);
            to.credit(amount);
        } else {
            throw new InsufficientAmountException(amount.toString());
        }
    }

    public void rejectTransfer(LedgerTransfer transfer, LedgerTransferRejectedReason reason) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void fulfillCondition(Fulfillment fulfillment) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void registerEventHandler(LedgerEventHandler<?> handler) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
