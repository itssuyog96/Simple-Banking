package com.zycus.banking.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zycus.banking.branch.Branch;
import com.zycus.banking.branch.BranchMap;

/**
 * Servlet implementation class TransactionServlet
 */
@WebServlet("/transaction.do")
public class TransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TransactionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String type = request.getParameter("type");
		String account, amount, bank;
		Transaction transaction;
		Branch branch;
		try {
			bank = request.getParameter("bank");
			branch = new BranchMap(bank, Integer.parseInt(request.getParameter("branch")));
			transaction = new Transaction(new BranchMap(bank, branch.getBranchCode()));
		} catch (Exception e) {
			response.setStatus(500);
			response.getWriter().println(e.getMessage());
			return;
		}

		if (type != null) {
			switch (type) {
			case "w":
				account = request.getParameter("account");
				amount = request.getParameter("amount");
				System.out.println(account + "|" + amount);

				try {
					Float amountF = Float.parseFloat(amount);
					Long accountL = Long.parseLong(account);

					// Perform transaction
					transaction.withdraw(branch, accountL, amountF);

				} catch (Exception e) {
					response.setStatus(500);
					response.getWriter().println(e.getMessage());
					return;
				}
				response.setContentType("application/json");
				response.getWriter()
						.println("{'data': 'Withdraw of " + amount + " for account " + account + " successful!'}");

				break;
			case "d":
				account = request.getParameter("account");
				amount = request.getParameter("amount");
				System.out.println(account + "|" + amount);

				try {
					Float amountF = Float.parseFloat(amount);
					Long accountL = Long.parseLong(account);

					// Perform transaction
					transaction.deposit(branch, accountL, amountF);

				} catch (Exception e) {
					response.setStatus(500);
					response.getWriter().println(e.getMessage());
					return;
				}
				response.setContentType("application/json");
				response.getWriter()
						.println("{'data': 'Deposit of " + amount + " for account " + account + " successful!'}");

				break;
			case "t":
				account = request.getParameter("f-account");
				amount = request.getParameter("amount");
				String b_account = request.getParameter("t-account");
				String ifsc = request.getParameter("ifsc");
				System.out.println(account + "|amt:" + amount + "|bacc:" + b_account + "|ifsc:" + ifsc);

				try {
					Float amountF = Float.parseFloat(amount);
					Long sourceAccount = Long.parseLong(account);
					String destBank = ifsc.substring(0, 4);
					int destBranchCode = Integer.parseInt(ifsc.substring(5, 9));
					Branch destBranch = new BranchMap(destBank, destBranchCode);
					Long destAccount = Long.parseLong(b_account);

					System.out.println(
							branch + "|" + sourceAccount + "|" + destBranch + "|" + destAccount + "|" + amountF);

					// Perform transaction
					transaction.transfer(branch, sourceAccount, destBranch, destAccount, amountF);

				} catch (Exception e) {
					e.printStackTrace();
					response.setStatus(500);
					response.getWriter().println(e.getMessage());
					return;
				}
				response.setContentType("application/json");
				response.getWriter()
						.println("{'data': 'Deposit of " + amount + " for account " + account + " successful!'}");

				break;
			}
		}
	}

}
