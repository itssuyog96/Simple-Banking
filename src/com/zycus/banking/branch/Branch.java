package com.zycus.banking.branch;

import com.zycus.banking.account.Account;
import com.zycus.banking.account.accountType;

public abstract class Branch {
	protected int branchCode;
	protected String bankCode;

	public Branch() {
		// Empty constructor to initialize as a superclass;
	}

	public Branch(String bankCode, int branchCode) {
		this.bankCode = bankCode;
		this.branchCode = branchCode;
	}

	public abstract Account openNewAccount(accountType accountType, float balance, String accountHolder);

	public abstract Account getAccount(long accountNo) throws Exception;

	public abstract void closeAccount(long accountNo);

	public int getBranchCode() {
		return this.branchCode;
	}

	public String getBankCode() {
		return this.bankCode;
	}

	@Override
	public String toString() {
		return branchCode + "";
	}

}
