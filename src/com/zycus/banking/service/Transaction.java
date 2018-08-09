package com.zycus.banking.service;

import static java.lang.System.err;

import com.zycus.banking.account.Account;
import com.zycus.banking.account.status;
import com.zycus.banking.branch.Branch;
import com.zycus.banking.branch.BranchMap;

public class Transaction implements TransactionInterface {
	private static final float MIN_BALANCE = 1000;

	public Transaction(BranchMap branch) {
		super();
	}

	@Override
	public boolean withdraw(Branch branch, int accountNo, float amount) {
		Account account = branch.getAccount(accountNo);

		if (checkAccount(account)) {
			float tempBalance = account.getBalance() - amount;
			if (tempBalance >= MIN_BALANCE) {
				account.setBalance(tempBalance);
				// out.println("Withdrew " + amount + " from AC no." + accountNo);
				return true;
			} else {
				err.println("Insufficient balance.");
			}
		}

		return false;

	}

	@Override
	public boolean deposit(Branch branch, int accountNo, float amount) {
		Account account = branch.getAccount(accountNo);

		if (checkAccount(account)) {
			float tempBalance = account.getBalance() + amount;
			account.setBalance(tempBalance);
			// out.println("Deposited " + amount + " in AC no." + accountNo);
			return true;
		}

		return false;

	}

	@Override
	public void transfer(Branch srcBranch, int sourceAccountNumber, Branch destBranch, int destinationAccountNumber, float amount) {
		if (withdraw(srcBranch, sourceAccountNumber, amount)) {
			if (!deposit(destBranch, destinationAccountNumber, amount)) {
				err.println("Unable to deposit. Rolling back...");
				deposit(srcBranch, sourceAccountNumber, amount);
			}
		}
	}

	private static boolean checkAccount(Account account) {
		if (account != null)
			if (account.getAccountStatus() == status.ACTIVE) {
				return true;
			} else {
				err.println("Account is closed.");
				return false;
			}
		else
			err.println("Account does not exist.");
		return false;
	}

}
