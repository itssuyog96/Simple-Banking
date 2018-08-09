package com.zycus.banking.branch;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import com.zycus.banking.account.Account;
import com.zycus.banking.account.accountType;
import com.zycus.banking.account.status;
import com.zycus.banking.customer.Customer;

public class BranchMap extends Branch {

	private Map<Long, Account> allAccounts = new HashMap<>();

	public BranchMap(String bankCode, int branchCode) {
		super(bankCode, branchCode);
	}

	@Override
	public Account openNewAccount(accountType accountType, float balance, Customer accountHolder) {

		return new Account((new Date().getTime() % 10000) + branchCode * 1000000, accountHolder, accountType, balance,
				bankCode, branchCode);

	}

	public Account getAccount(long accountNo) {

		// TODO: Call DAO for action
		return allAccounts.get(accountNo);

	}

	public void closeAccount(long accountNo) {
		try {
			allAccounts.get(accountNo).setAccountStatus(status.CLOSED);
		} catch (NoSuchElementException | NullPointerException e) {
			System.err.println("Account does not exists");
		}
	}

}
