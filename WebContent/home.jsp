
<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.zycus.banking.util.ConnectionUtil"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.zycus.banking.customer.Customer"%>
<%@page import="com.zycus.banking.util.Template"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%!String pageId = "home";%>
<%!String role = "default";%>
<%
	role = "default";
	boolean flag = false;
%>
<%
	if (session != null && session.getAttribute("login") != null) {
		if (session.getAttribute("login").toString().equalsIgnoreCase("true")) {
			String newRole = (String) session.getAttribute("role");
			if (newRole != null) {
				flag = true;
				role = newRole;
			}
		}
	}
%>

<%=Template.getHeader(pageId)%>
<%=Template.getMenu(role, pageId)%>
<!-- MAIN BODY -->

<style>
h2 {
	margin-top: 25%;
}

.center {
	text-align: center;
	width: 100%
}
</style>

<div class="row">
	<h2>
		Welcome
		<% if(session.getAttribute("user") != null){
			out.println(flag ? ((Customer) session.getAttribute("user")).getFirstName().substring(0, 1)
							+ ((Customer) session.getAttribute("user")).getFirstName().substring(1).toLowerCase() + "!" : "to Banking");
							
		}
							%>
</h2>
</div>

<!-- END OF MAIN BODY -->
<%=Template.getFooter()%>
