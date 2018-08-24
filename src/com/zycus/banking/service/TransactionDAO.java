package com.zycus.banking.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.zycus.banking.branch.Branch;
import com.zycus.banking.util.ConnectionUtil;

public class TransactionDAO {
	private static final String SQL_INSERT = "INSERT INTO TRANSACTIONS (TID, TIMESTAMP, BANK, BRANCH, ACCOUNTNO, AMOUNT, TYPE, CUST_ID, SUB_TYPE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	public void create(long transactionId, long timestamp, Branch branch, long accountNo, float amount, String type,
			String accountHolder, String subType) {

		try (Connection con = ConnectionUtil.getConnection()) {
			PreparedStatement ps = con.prepareStatement(SQL_INSERT);
			ps.setString(1, String.valueOf(transactionId));
			ps.setTimestamp(2, new java.sql.Timestamp(timestamp));
			ps.setString(3, branch.getBankCode());
			ps.setInt(4, branch.getBranchCode());
			ps.setLong(5, accountNo);
			ps.setFloat(6, amount);
			ps.setString(7, type);
			ps.setString(8, accountHolder);
			ps.setString(9, subType);

			ps.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

}
