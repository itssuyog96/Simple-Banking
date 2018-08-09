package com.zycus.banking.bank;

public class Bank {
	private String bankName;
	private String bankCode;

	public Bank(String bankCode, String bankName) {
		super();
		this.bankName = bankName;
		this.bankCode = bankCode;
	}

	public Bank() {
		super();

	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	@Override
	public String toString() {
		return bankCode + " | " + bankName;
	}

}
