package com.zycus.banking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONObject;

import com.zycus.banking.customer.Customer;
import com.zycus.banking.util.ConnectionUtil;

public class LoginDAO {

	public static JSONObject login(String username, String password) {
		JSONObject obj = new JSONObject();
		try (Connection con = ConnectionUtil.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"SELECT ID, TITLE, FIRSTNAME, LASTNAME, DOB, REG_STATUS, BANKCODE, BRANCHCODE, ROLE FROM CUSTOMER WHERE USERNAME=? AND PASSWORD=?");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Customer customer = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getDate(5), rs.getBoolean(6));
				obj.put("login", true);
				obj.put("bankCode", rs.getString(7));
				obj.put("branchCode", rs.getInt(8));
				obj.put("role", rs.getString(9));
				obj.put("customer", customer);
				return obj;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			obj.put("login", false);
			return obj;
		}

		obj.put("login", false);
		return obj;
	}
}
