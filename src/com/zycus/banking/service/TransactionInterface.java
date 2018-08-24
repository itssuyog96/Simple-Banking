package com.zycus.banking.service;

import com.zycus.banking.branch.Branch;

public interface TransactionInterface {
	public boolean withdraw(Branch branch, long accountNumber, float amount) throws Exception;

	public boolean deposit(Branch branch, long accountNumber, float amount) throws Exception;

	public void transfer(Branch srcBranch, long sourceAccountNumber, Branch destBranch, long destinationAccountNumber,
			float amount) throws Exception;

}
