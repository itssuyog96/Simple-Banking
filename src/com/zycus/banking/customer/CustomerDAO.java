package com.zycus.banking.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONObject;

import com.zycus.banking.util.ConnectionUtil;
import com.zycus.banking.util.userType;

public class CustomerDAO {

	private static final String SQL_SELECT_ID = "SELECT id, title, firstName, lastName, dob FROM customer";
	private static final String SQL_SELECT_UNREGISTERED = "SELECT id, title, firstName, lastName, dob, REG_STATUS FROM customer WHERE TRUE";
	private static final String SQL_INSERT = "INSERT INTO CUSTOMER(id, firstName, lastName, dob, title, REG_STATUS) VALUES(?,?,?,?,?, FALSE)";

	public void create(Customer customer) {
		try (Connection con = ConnectionUtil.getConnection()) {
			PreparedStatement ps = con.prepareStatement(SQL_INSERT);
			ps.setString(1, customer.getId());
			ps.setString(2, customer.getFirstName());
			ps.setString(3, customer.getLastName());
			ps.setDate(4, customer.getDob());
			ps.setString(5, customer.getTitle());
			ps.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	public List<Customer> findAll() {
		List<Customer> customers = new LinkedList<>();
		try (Connection con = ConnectionUtil.getConnection()) {
			PreparedStatement ps = con.prepareStatement(SQL_SELECT_ID);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Customer customer = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getDate(5));
				customers.add(customer);
			}

			return customers;

		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}

	}

	public JSONObject findByAccessType(userType role) {
		String SQL_SELECT;
		JSONObject obj = new JSONObject();

		List<Customer> customers = new LinkedList<>();
		try (Connection con = ConnectionUtil.getConnection()) {

			switch (role) {
			case ADMIN:
				SQL_SELECT = "SELECT id, title, firstName, lastName, dob, username, role, bankcode, branchcode, reg_status FROM customer";
				break;
			case BANK:
				SQL_SELECT = "SELECT id, title, firstName, lastName, dob, username, role, branchcode, reg_status FROM customer WHERE ROLE='BRANCH' OR ROLE='CUSTOMER";
				break;
			case BRANCH:
				SQL_SELECT = "SELECT id, title, firstName, lastName, dob, username. reg_status FROM customer WHERE ROLE='CUSTOMER'";
				break;
			case CUSTOMER:
				return null;
			default:
				return null;
			}

			PreparedStatement ps = con.prepareStatement(SQL_SELECT);
			ResultSet rs = ps.executeQuery();

			obj.put("rs", rs);
			obj.put("meta-count", rs.getMetaData().getColumnCount());
			for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
				obj.put("meta-name-" + i, rs.getMetaData().getColumnName(i));
				obj.put("meta-type-" + i, rs.getMetaData().getColumnClassName(i));
			}

			return obj;

		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}

	}

	public List<Customer> findUnregistered() {
		List<Customer> customers = new LinkedList<>();
		try (Connection con = ConnectionUtil.getConnection()) {
			PreparedStatement ps = con.prepareStatement(SQL_SELECT_UNREGISTERED);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Customer customer = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getDate(5), rs.getBoolean(6));
				customers.add(customer);
			}

			return customers;

		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}

	}

	public Customer findById(String id) {

		try (Connection con = ConnectionUtil.getConnection()) {
			PreparedStatement ps = con
					.prepareStatement("select id, title, firstName, lastName, dob from customer where id = ? ");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			Customer customer = null;

			if (rs.next()) {
				customer = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getDate(5));
			}

			return customer;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public boolean setRegistered(String customerId) {
		try (Connection con = ConnectionUtil.getConnection()) {
			PreparedStatement ps = con.prepareStatement("UPDATE CUSTOMER SET REG_STATUS = TRUE WHERE ID = ?");
			ps.setString(1, customerId);
			ps.executeUpdate();

			return true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean updateCustomer(String id, String username, String role, String bankCode, String branchCode) {
		try (Connection con = ConnectionUtil.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"UPDATE CUSTOMER SET REG_STATUS = TRUE, ROLE=?, USERNAME=?, PASSWORD=?, BANKCODE=?, BRANCHCODE=? WHERE ID = ?");
			ps.setString(1, role);
			ps.setString(2, username);
			ps.setString(3, username);
			ps.setString(4, bankCode);
			ps.setInt(5, Integer.parseInt(branchCode));
			ps.setString(6, id);
			ps.executeUpdate();

			return true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

}