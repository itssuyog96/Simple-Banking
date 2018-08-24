<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.zycus.banking.util.ConnectionUtil"%>
<%@page import="java.util.LinkedList"%>
<%@page import="com.zycus.banking.customer.Customer"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="com.zycus.banking.util.userType"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.zycus.banking.customer.CustomerDAO"%>
<%@page import="com.zycus.banking.util.Template"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%! String pageId = "view-users";  %>
<%! String role = "bank";  %>

<%= Template.getHeader(pageId) %>
<%= Template.getMenu(role, pageId) %>
<!-- MAIN BODY -->

<div class="row table-responsive">
	<table class="table">
		<thead>
		<%
		
			String SQL_SELECT = "";
			JSONObject obj = new JSONObject();
			boolean flag = true;
			Connection con;
			try {
				con = ConnectionUtil.getConnection();
				userType rolex = userType.valueOf(role.toUpperCase());
				switch (rolex.ordinal()) {
				case 0:
					SQL_SELECT = "SELECT id, title, firstName, lastName, dob, username, role, bankcode, branchcode, reg_status FROM customer";
					break;
				case 1:
					SQL_SELECT = "SELECT id, title, firstName, lastName, dob, username, role, branchcode, reg_status FROM customer WHERE ROLE='BRANCH' OR ROLE='CUSTOMER'";
					break;
				case 2:
					SQL_SELECT = "SELECT id, title, firstName, lastName, dob, username, reg_status FROM customer WHERE ROLE='CUSTOMER'";
					break;
				case 3:
					flag = false;
				default:
					flag = false;
				}
				if(flag){					
					PreparedStatement ps = con.prepareStatement(SQL_SELECT);
					ResultSet rs = ps.executeQuery();
					
					out.println("<tr>");
					for(int j = 1; j <= rs.getMetaData().getColumnCount(); j++){
						out.println("<th>" + rs.getMetaData().getColumnName(j) + "</th>");
					}
					out.println("</tr>");
	
		%>
		</thead>
		<tbody>
		<%
					
					while(rs.next()){
					out.println("<tr>");
						for(int i = 1; i <= rs.getMetaData().getColumnCount(); i++)
							out.println("<td>" + rs.getString(i) + "</td>");
					out.println("</tr>");
					}
					
				}
	
			} catch (SQLException ex) {
				ex.printStackTrace();
				
			}
			
		%>
		</tbody>
	</table>
</div>

<!-- END OF MAIN BODY -->
<%= Template.getFooter() %>
