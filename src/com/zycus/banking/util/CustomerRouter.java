package com.zycus.banking.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zycus.banking.account.AccountDAO;
import com.zycus.banking.customer.Customer;

/**
 * Servlet implementation class CustomerRouter
 */
@WebServlet("/customer/*")
public class CustomerRouter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerRouter() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getPathInfo());

		String role = "default";
		HttpSession session = request.getSession();

		if (session != null && session.getAttribute("login") != null) {
			if (session.getAttribute("login").toString().equalsIgnoreCase("true")) {
				String newRole = (String) session.getAttribute("role");
				if (newRole != null) {
					role = newRole;
				}
			}
		}

		System.out.println(role);
		String path = request.getPathInfo() == null ? "/" : request.getPathInfo();
		if (role.equalsIgnoreCase("customer")) {

			switch (path) {
			case "/":
			case "/home":
				request.getRequestDispatcher("/home.jsp").forward(request, response);
				return;
			/*
			 * case "/new-account":
			 * request.getRequestDispatcher("/customer-pages/new-account.jsp").forward(
			 * request, response); return;
			 */
			case "/my-transactions":
				request.getRequestDispatcher("/customer-pages/my-transactions.jsp").forward(request, response);
				return;
			case "/account-details":
				request.getRequestDispatcher("/customer-pages/account-details.jsp").forward(request, response);
				return;
			case "/transaction":
				try {
					request.setAttribute("accounts",
							new AccountDAO().findByCustomer(((Customer) session.getAttribute("user")).getId()));
					request.getRequestDispatcher("/customer-pages/transaction.jsp").forward(request, response);
				} catch (NullPointerException e) {
					request.setAttribute("error-message",
							"403: Unauthorized access! <a href=\"/logout\">Try Again.</a>");
					request.setAttribute("role", role);
					request.getRequestDispatcher("/error.jsp").forward(request, response);
				}
				return;
			default:
				request.setAttribute("error-message", "404 : The page you are looking for not found!");
				request.setAttribute("role", role);
				request.getRequestDispatcher("/error.jsp").forward(request, response);
				return;

			}
		} else {
			response.sendRedirect("/login?role=customer&redirect-url=" + "/customer/"
					+ (path.startsWith("/") ? path.substring(1) : path));
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
