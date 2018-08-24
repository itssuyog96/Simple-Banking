package com.zycus.banking.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AdminRouter
 */
@WebServlet("/admin/*")
public class AdminRouter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminRouter() {
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

		String path = request.getPathInfo() == null ? "/" : request.getPathInfo();
		if (role.equalsIgnoreCase("admin")) {
			switch (path) {

			case "/":
			case "/home":
				request.getRequestDispatcher("/home.jsp").forward(request, response);
				return;
			case "/create-bank":
				request.getRequestDispatcher("/admin-pages/create-bank.jsp").forward(request, response);
				return;
			case "/all-transactions":
				request.getRequestDispatcher("/admin-pages/all-transactions.jsp").forward(request, response);
				return;
			case "/create-user":
				request.getRequestDispatcher("/admin-pages/create-user.jsp").forward(request, response);
				return;
			case "/view-users":
				request.getRequestDispatcher("/admin-pages/view-users.jsp").forward(request, response);
				return;
			default:
				request.setAttribute("error-message", "404 : The page you are looking for not found!");
				request.setAttribute("role", role);
				request.getRequestDispatcher("/error.jsp").forward(request, response);
				return;

			}
		} else {
			response.sendRedirect(
					"/login?role=admin&redirect-url=" + "/admin/" + (path.startsWith("/") ? path.substring(1) : path));
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
