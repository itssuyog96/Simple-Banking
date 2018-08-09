package com.zycus.banking.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.zycus.banking.util.ConnectionUtil;

public class AccountDAO {

	private static final String SQL_INSERT = "INSERT INTO ACCOUNT(ACCOUNTNO, BRANCHCODE, ACCOUNTTYPE, BALANCE, CUST_ID, BANKCODE) VALUES(?, ?, ?, ?, ?, ?)";
	// private static final String SQL_SELECT_ID = "SELECT BRANCHCODE, BANKCODE FROM
	// BRANCH";
	// private static final String SQL_SELECT_BANK = "SELECT BRANCHCODE, BANKCODE
	// FROM BRANCH WHERE BANKCODE=?";

	public void create(Account account) {
		try (Connection con = ConnectionUtil.getConnection()) {
			PreparedStatement ps = con.prepareStatement(SQL_INSERT);

			ps.setLong(1, account.getAccountNumber());
			ps.setInt(2, account.getBranchCode());
			ps.setInt(3, account.getAccountType().ordinal());
			ps.setFloat(4, account.getBalance());
			ps.setString(5, account.getAccountHolder().getId());
			ps.setString(6, account.getBankCode());
			ps.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}
	//
	// public List<Branch> findAll() {
	// List<Branch> branches = new LinkedList<>();
	// try (Connection con = ConnectionUtil.getConnection()) {
	// PreparedStatement ps = con.prepareStatement(SQL_SELECT_ID);
	//
	// ResultSet rs = ps.executeQuery();
	//
	// while (rs.next()) {
	// branches.add(new BranchMap(rs.getString(2), rs.getInt(1)));
	// }
	//
	// return branches;
	//
	// } catch (SQLException ex) {
	// ex.printStackTrace();
	// return null;
	// }
	//
	// }
	//
	// public List<Branch> findAllByBank(String bankCode) {
	// List<Branch> branches = new LinkedList<>();
	// try (Connection con = ConnectionUtil.getConnection()) {
	// PreparedStatement ps = con.prepareStatement(SQL_SELECT_BANK);
	//
	// ps.setString(1, bankCode);
	// ResultSet rs = ps.executeQuery();
	//
	// while (rs.next()) {
	// branches.add(new BranchMap(rs.getString(2), rs.getInt(1)));
	// }
	//
	// return branches;
	//
	// } catch (SQLException ex) {
	// ex.printStackTrace();
	// return null;
	// }
	//
	// }
	//
	// public Branch findById(int id) {
	//
	// try (Connection con = ConnectionUtil.getConnection()) {
	// PreparedStatement ps = con
	// .prepareStatement("select BRANCHCODE, BANKCODE from BRANCH where BRANCH_CODE
	// = ? ");
	// ps.setInt(1, id);
	// ResultSet rs = ps.executeQuery();
	// Branch branch = null;
	//
	// if (rs.next()) {
	// branch = new BranchMap(rs.getString(2), Integer.parseInt(rs.getString(1)));
	// }
	//
	// return branch;
	// } catch (SQLException ex) {
	// ex.printStackTrace();
	// return null;
	// }
	// }

}