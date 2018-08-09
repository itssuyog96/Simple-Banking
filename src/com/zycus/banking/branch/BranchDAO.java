package com.zycus.banking.branch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.zycus.banking.util.ConnectionUtil;

public class BranchDAO {

	private static final String SQL_SELECT_ID = "SELECT BRANCHCODE, BANKCODE FROM BRANCH";
	private static final String SQL_SELECT_BANK = "SELECT BRANCHCODE, BANKCODE FROM BRANCH WHERE BANKCODE=?";
	private static final String SQL_INSERT = "INSERT INTO BRANCH(BRANCHCODE, BANKCODE) VALUES(?,?)";

	public void create(Branch branch) {
		try (Connection con = ConnectionUtil.getConnection()) {
			PreparedStatement ps = con.prepareStatement(SQL_INSERT);
			ps.setInt(1, branch.getBranchCode());
			ps.setString(2, branch.getBankCode());
			ps.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	public List<Branch> findAll() {
		List<Branch> branches = new LinkedList<>();
		try (Connection con = ConnectionUtil.getConnection()) {
			PreparedStatement ps = con.prepareStatement(SQL_SELECT_ID);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				branches.add(new BranchMap(rs.getString(2), rs.getInt(1)));
			}

			return branches;

		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}

	}

	public List<Branch> findAllByBank(String bankCode) {
		List<Branch> branches = new LinkedList<>();
		try (Connection con = ConnectionUtil.getConnection()) {
			PreparedStatement ps = con.prepareStatement(SQL_SELECT_BANK);

			ps.setString(1, bankCode);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				branches.add(new BranchMap(rs.getString(2), rs.getInt(1)));
			}

			return branches;

		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}

	}

	public Branch findById(int id) {

		try (Connection con = ConnectionUtil.getConnection()) {
			PreparedStatement ps = con
					.prepareStatement("select BRANCHCODE, BANKCODE from BRANCH where BRANCH_CODE = ? ");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			Branch branch = null;

			if (rs.next()) {
				branch = new BranchMap(rs.getString(2), Integer.parseInt(rs.getString(1)));
			}

			return branch;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}

}