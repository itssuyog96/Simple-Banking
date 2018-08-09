package com.zycus.banking.bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.zycus.banking.util.ConnectionUtil;

public class BankDAO {

	private static final String SQL_SELECT_ID = "SELECT BANKCODE, BANKNAME FROM BANK";
	private static final String SQL_INSERT = "INSERT INTO BANK(BANKCODE, BANKNAME) VALUES(?,?)";

	public void create(Bank bank) {
		try (Connection con = ConnectionUtil.getConnection()) {
			PreparedStatement ps = con.prepareStatement(SQL_INSERT);
			ps.setString(1, bank.getBankCode());
			ps.setString(2, bank.getBankName());
			ps.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	public List<Bank> findAll() {
		List<Bank> banks = new LinkedList<>();
		try (Connection con = ConnectionUtil.getConnection()) {
			PreparedStatement ps = con.prepareStatement(SQL_SELECT_ID);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				banks.add(new Bank(rs.getString(1), rs.getString(2)));
			}

			return banks;

		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}

	}

	public Bank findById(int id) {

		try (Connection con = ConnectionUtil.getConnection()) {
			PreparedStatement ps = con.prepareStatement("select BANKCODE, BANKNAME from BANK where BANKCODE = ? ");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			Bank bank = null;

			if (rs.next()) {
				bank = new Bank(rs.getString(2), rs.getString(1));
			}

			return bank;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}

}