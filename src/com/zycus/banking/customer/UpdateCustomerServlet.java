package com.zycus.banking.customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zycus.banking.account.AccountDAO;
import com.zycus.banking.account.accountType;
import com.zycus.banking.branch.BranchMap;

/**
 * Servlet implementation class UpdateCustomerServlet
 */
@WebServlet("/update-customer.do")
public class UpdateCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateCustomerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String role = request.getParameter("role");
		String bankCode = request.getParameter("bankCode");
		String branchCode = request.getParameter("branchCode");
		String id = request.getParameter("id");
		String username = request.getParameter("username");
		String accountTypex = request.getParameter("accountType");
		String origin = request.getParameter("origin");

		if (role != null && !role.equalsIgnoreCase("null")) {
			switch (role) {
			case "ADMIN":
			case "BANK":
			case "BRANCH":
				new CustomerDAO().updateCustomer(id, username, role, bankCode, branchCode);
				break;
			case "CUSTOMER":
				if (origin != null && origin.equalsIgnoreCase("branch"))
					new AccountDAO().create((new BranchMap(bankCode, Integer.parseInt(branchCode)))
							.openNewAccount(accountType.valueOf(accountTypex.toUpperCase()), 0f, id));
				new CustomerDAO().updateCustomer(id, username, role, bankCode, branchCode);
			default:
			}
		}
	}

}
