package com.zycus.banking.customer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

/**
 * Servlet implementation class FetchCustomerServlet
 */
@WebServlet("/fetch-customer.do")
public class FetchCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchCustomerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject obj = new JSONObject();
		Customer cust = (new CustomerDAO()).findById(request.getParameter("cust_id"));
		response.addHeader("CONTENT-TYPE", "APPLICATION/JSON");
		if(cust == null) {
			response.setStatus(400);
			obj.put("message", "Customer not found!");
		}else {
			obj.put("firstname", cust.getFirstName());
			obj.put("lastname", cust.getLastName());
			obj.put("title", cust.getTitle());
			obj.put("dob", cust.getDob().toString());
			obj.put("message", "Customer found!");
		}
		
		response.getWriter().append(obj.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
