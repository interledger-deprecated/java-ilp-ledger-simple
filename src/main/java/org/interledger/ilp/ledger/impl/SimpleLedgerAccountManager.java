package org.interledger.ilp.ledger.impl;

import java.util.Collection;

import java.util.HashMap;
import java.util.Map;
import org.interledger.ilp.ledger.LedgerFactory;
import org.interledger.ilp.ledger.account.AccountNotFoundException;
import org.interledger.ilp.ledger.account.LedgerAccount;
import org.interledger.ilp.ledger.account.LedgerAccountManager;

/**
 * Simple in-memory {@code LedgerAccountManager}.
 *
 * @author mrmx
 */
public class SimpleLedgerAccountManager implements LedgerAccountManager {
	private Map<String, LedgerAccount> accountMap;

	public SimpleLedgerAccountManager() {
		accountMap = new HashMap<String, LedgerAccount>();
	}


	@Override
	public LedgerAccount create(String name) {
		return new SimpleLedgerAccount(name, LedgerFactory.getLedger().getInfo().getCurrencyCode());
	}

	@Override
	public void addAccount(LedgerAccount account) {
		accountMap.put(account.getName(), account);
	}

	@Override
	public LedgerAccount getAccountByName(String name) throws AccountNotFoundException {
		if (!accountMap.containsKey(name)) {
			throw new AccountNotFoundException(name);
		}
		return accountMap.get(name);
	}

	@Override
	public Collection<LedgerAccount> getAccounts(int page, int pageSize) {
		// TODO
		return accountMap.values();
	}

	@Override
	public int getTotalAccounts() {
		return accountMap.size();
	}

}
