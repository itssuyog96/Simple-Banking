package com.zycus.banking.account;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.zycus.banking.customer.Customer;
import com.zycus.banking.customer.CustomerDAO;

/**
 * Servlet implementation class NewAccountServlet
 */
@WebServlet(description = "Open new account", urlPatterns = { "/new-account.do" })
public class NewAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewAccountServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("index.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		String title = request.getParameter("title");		
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String dob = request.getParameter("dob");
		
		JSONObject obj = new JSONObject();
		
		if(title == null || firstName == null || lastName == null || dob == null || title.equals("") || firstName.equals("") || lastName.equals("") || dob.equals("")) {
			response.addHeader("CONTENT-TYPE", "APPLICATION/JSON");
			response.setStatus(400);
			obj.put("message", "Invalid/Empty input!");
			response.getWriter().append(obj.toJSONString());
			
			return;
		}
		
		if(firstName.length() < 2 || lastName.length() < 2) {
			response.addHeader("CONTENT-TYPE", "APPLICATION/JSON");
			response.setStatus(400);
			obj.put("message", "Name should contain equal to or more than 2 characters!");

		}else {			
			//Writing to database
			new CustomerDAO().create(new Customer(title, firstName, lastName, Date.valueOf(dob)));
			obj.put("message", String.format("Hi %s. %s, your account creation is under process.", title, firstName));
			response.addHeader("CONTENT-TYPE", "APPLICATION/JSON");
		}
		response.getWriter().append(obj.toJSONString());	
	}

}
