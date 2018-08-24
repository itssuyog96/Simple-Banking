package com.zycus.banking.service;

import static java.lang.System.err;

import com.zycus.banking.account.Account;
import com.zycus.banking.account.AccountDAO;
import com.zycus.banking.account.status;
import com.zycus.banking.branch.Branch;
import com.zycus.banking.branch.BranchMap;

public class Transaction implements TransactionInterface {
	private static final float MIN_BALANCE = 1000;
	private boolean isTransfer = false;
	private long transactionId;

	public Transaction(BranchMap branch) {
		super();
	}

	@Override
	public boolean withdraw(Branch branch, long accountNo, float amount) throws Exception {
		Account account = branch.getAccount(accountNo);
		if (!isTransfer)
			transactionId = System.currentTimeMillis();

		if (checkAccount(account)) {
			System.out.println("Current Balance" + account.getBalance());
			float tempBalance = account.getBalance() - amount;
			if (tempBalance >= MIN_BALANCE) {
				account.setBalance(tempBalance);
				new AccountDAO().updateBalance(branch, accountNo, tempBalance);
				new TransactionDAO().create(transactionId, System.currentTimeMillis(), branch, accountNo, amount, "W",
						account.getAccountHolder(), (isTransfer ? "T" : null));
				// out.println("Withdrew " + amount + " from AC no." + accountNo);
				return true;
			} else {
				throw new Exception("Insufficient balance");
			}
		}
		throw new Exception("Account does not exists");
	}

	@Override
	public boolean deposit(Branch branch, long accountNo, float amount) throws Exception {
		Account account = branch.getAccount(accountNo);
		if (!isTransfer)
			transactionId = System.currentTimeMillis();

		if (checkAccount(account)) {
			float tempBalance = account.getBalance() + amount;
			account.setBalance(tempBalance);
			new AccountDAO().updateBalance(branch, accountNo, tempBalance);
			new TransactionDAO().create(transactionId, System.currentTimeMillis(), branch, accountNo, amount, "D",
					account.getAccountHolder(), (isTransfer ? "T" : null));
			// out.println("Deposited " + amount + " in AC no." + accountNo);
			return true;
		}

		throw new Exception("Account does not exists");
	}

	@Override
	public void transfer(Branch srcBranch, long sourceAccountNumber, Branch destBranch, long destinationAccountNumber,
			float amount) throws Exception {
		isTransfer = true;
		transactionId = System.currentTimeMillis();
		if (withdraw(srcBranch, sourceAccountNumber, amount)) {
			if (!deposit(destBranch, destinationAccountNumber, amount)) {
				err.println("Unable to deposit. Rolling back...");
				deposit(srcBranch, sourceAccountNumber, amount);
			}
		}
		isTransfer = false;
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
