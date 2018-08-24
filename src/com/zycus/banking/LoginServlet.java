package com.zycus.banking;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		JSONObject login = LoginDAO.login(username, password);
		if ((boolean) login.get("login")) {
			session.setAttribute("login", "true");
			session.setAttribute("user", login.get("customer"));
			session.setAttribute("role", login.get("role"));
			session.setAttribute("bankCode", login.get("bankCode"));
			session.setAttribute("branchCode", login.get("branchCode"));

			String role = login.get("role").toString();

			if (request.getParameter("role") != null) {
				if (request.getParameter("role").toString().equalsIgnoreCase(role)) {
					System.out.println("Redirecting to " + request.getParameter("redirect-url").toString()
							+ " with role : " + request.getParameter("role"));
					response.sendRedirect(request.getParameter("redirect-url").toString());
					return;
				}
			}
			response.sendRedirect("/" + role.toLowerCase());
			return;

		} else {
			response.sendRedirect("/login");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

		System.out.println(session.toString());
		System.out.println(role);

		switch (role.toLowerCase()) {
		case "admin":
		case "bank":
		case "branch":
		case "customer":
			if (request.getAttribute("role") != null) {
				if (request.getAttribute("role").toString().equalsIgnoreCase(role)) {
					// TODO: Remove this
					System.out.println("HIT");
					response.sendRedirect(request.getAttribute("redirect").toString());
					return;
				}
			}
			response.sendRedirect("/" + role.toLowerCase());
			return;
		}

		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}
}
