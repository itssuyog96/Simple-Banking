package com.zycus.banking.branch;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

/**
 * Servlet implementation class getBankByBranchCode
 */
@WebServlet("/get-branch-by-bank-code.do")
public class getBranchByBankCode extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getBranchByBankCode() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.addHeader("CONTENT-TYPE", "TEXT/HTML");
		List<Branch> branches = (new BranchDAO()).findAllByBank(request.getParameter("bankCode"));
		String str = "";
		JSONObject obj = new JSONObject();

		try {
			for (Branch branch : branches) {
				if (branch == null)
					break;
				str += "<option value=\"" + branch.getBranchCode() + "\">" + branch.getBranchCode() + "</option>";
			}
		} catch (NullPointerException e) {
			str += "<option disabled>No branch for selected branch</option>";
		}
		obj.put("data", str);
		out.println(obj.toString());
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
