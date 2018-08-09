package com.zycus.banking.customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.*;

/**
 * Servlet implementation class PayBills
 */
@WebServlet("/pay-bills.do")
public class PayBills extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PayBills() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String billType = request.getParameter("bill_type");
		String elec_provider = request.getParameter("elec_provider");
		String phone_provider = request.getParameter("phone_provider");
		Float amount = Float.parseFloat(request.getParameter("amount"));
		
		JSONObject obj = new JSONObject();
		
		if(billType != null && !billType.equals("")) {
			switch(billType) {
			case "e":
				//Process electricity bills				
				obj.put("message", "Electricity bill paid successfully");
				obj.put("elec_provider", elec_provider);
				obj.put("e", true);
				obj.put("p", false);
				break;
				
			case "p":
				//Process phone bills
				obj.put("message", "Phone bill paid successfully");
				obj.put("phone_provider", phone_provider);
				obj.put("e", false);
				obj.put("p", true);
				
				break;
			}
			
			//==========================================
			//Start a new thread to start db transaction
			//==========================================
			
			obj.put("amount", amount);
			response.addHeader("CONTENT-TYPE", "APPLICATION/JSON");
			response.getWriter().println(obj);
		}else {
			response.sendError(500);
		}
	}

}
