package com.zycus.banking.bank;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteBankServlet
 */
@WebServlet("/delete-bank.do")
public class DeleteBankServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteBankServlet() {
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
		String bankCode = request.getParameter("bankCode");
		String bankName = request.getParameter("bankName");

		if (bankCode != null && bankName != null) {
			response.setContentType("application/json");
			int count = (new BankDAO()).deleteBank(new Bank(bankCode, bankName));
			if (count > 0) {
				response.getWriter().println("{\"data\" : " + count + "}");
				return;
			} else {
				response.getWriter().println("{\"data\" : " + 0 + "}");
				return;
			}

		}

		request.setAttribute("error-message", "500 : Error occured while deleting bank!");
		request.setAttribute("role", request.getSession().getAttribute("role"));
		request.getRequestDispatcher("/error.jsp").forward(request, response);
		return;

	}

}
