package com.zycus.banking.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class BranchRouter
 */
@WebServlet("/branch/*")
public class BranchRouter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BranchRouter() {
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
		if (role.equalsIgnoreCase("branch")) {

			switch (path) {
			case "/":
			case "/home":
				request.getRequestDispatcher("/home.jsp").forward(request, response);
				return;
			case "/create-account":
				request.getRequestDispatcher("/branch-pages/create-account.jsp").forward(request, response);
				return;
			case "/branch-transactions":
				request.getRequestDispatcher("/branch-pages/branch-transactions.jsp").forward(request, response);
				return;
			case "/accounts-explorer":
				request.getRequestDispatcher("/branch-pages/accounts-explorer.jsp").forward(request, response);
				return;
			case "/view-users":
				request.getRequestDispatcher("/branch-pages/view-users.jsp").forward(request, response);
				return;
			default:
				request.setAttribute("error-message", "404 : The page you are looking for not found!");
				request.setAttribute("role", role);
				request.getRequestDispatcher("/error.jsp").forward(request, response);
				return;

			}
		} else {
			response.sendRedirect("/login?role=branch&redirect-url=" + "/branch/"
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
