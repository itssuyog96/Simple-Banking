package com.zycus.banking.account;

public class Account {
	private long accountNumber;
	private int branchCode;
	private String accountHolder;
	private accountType accountType;
	private float balance;
	private String bankCode;
	private status status;

	public Account(long accountNumber, String accountHolder, com.zycus.banking.account.accountType accountType,
			float balance, String bankCode, int branchCode) {
		super();
		this.accountNumber = accountNumber;
		this.accountHolder = accountHolder;
		this.accountType = accountType;
		this.balance = balance;
		this.bankCode = bankCode;
		this.branchCode = branchCode;
		this.status = com.zycus.banking.account.status.ACTIVE;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public int getBranchCode() {
		return branchCode;
	}

	public String getAccountHolder() {
		return accountHolder;
	}

	public accountType getAccountType() {
		return accountType;
	}

	public float getBalance() {
		return balance;
	}

	void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	void setBranchCode(int branchCode) {
		this.branchCode = branchCode;
	}

	void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}

	void setAccountType(accountType accountType) {
		this.accountType = accountType;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public void setAccountStatus(status status) {
		this.status = status;
	}

	public status getAccountStatus() {
		return this.status;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (accountNumber ^ (accountNumber >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountNumber != other.accountNumber)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return accountNumber + "\t\t" + branchCode + "\t\t\t" + accountHolder + "\t\t" + accountType + "\t\t   "
				+ balance + "\t\t\t" + status;
	}

}
